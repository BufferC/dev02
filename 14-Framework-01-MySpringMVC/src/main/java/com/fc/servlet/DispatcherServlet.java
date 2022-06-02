package com.fc.servlet;

import com.fc.annotation.*;
import com.fc.handler.UrlHandler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DispatcherServlet extends HttpServlet {
    // 指定包下的所有类完整的路径【通过包扫描到的所有内容】
    private final List<String> classUrls = new ArrayList<>();
    // ioc容器，键是BeanName，值就是Bean对象
    private final Map<String, Object> ioc = new HashMap<>();
    // 存放映射地址和对象的容器
    private final Map<String, UrlHandler> urlHandlers = new HashMap<>();

    // 初始化时完成
    @Override
    public void init(ServletConfig config) throws ServletException {
        doScanPackage("com.fc"); // 扫描指定包下的所有的类，将路径加载到classUrls中
        inversionOfControl(); // 把携带指定注解的类放到ioc容器中
        dependencyInjection(); // 把具有依赖关系的对象进行赋值
        urlMapping(); // 将请求的路径和对象方法映射到一起
    }

    // 这里就是项目启动后需要执行的逻辑
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // 这个方法用来获取不带根路径的HTTP请求URL，就是mapping的路径
        String servletPath = req.getServletPath();

        UrlHandler urlHandler = urlHandlers.get(servletPath);

        if (urlHandler == null) {
            resp.getWriter().write("404了，没有这个路径");
            return;
        }

        // 从映射器中获取对应的方法和对象
        Method method = urlHandler.getMethod();
        Object obj = urlHandler.getObj();

        // 执行一个方法需要三个因素：方法、方法所在类的对象、方法所需要的参数
        Object[] params;

        try {
            // 这个方法用于获取方法的入参
            params = getMethodParams(req, resp, method);

            // 执行方法
            method.invoke(obj, params);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 这个方法用于获取方法中的请求参数
    private Object[] getMethodParams(HttpServletRequest req, HttpServletResponse resp, Method method) {
        // 获取该方法的所有入参的数据类型
        Class<?>[] parameterTypes = method.getParameterTypes();

        // 根据入参的个数，准备一个数组用于存放所有的参数
        Object[] params = new Object[parameterTypes.length];

        // 用于表示数组的位置
        int paramIndex = 0;
        int index = 0;

        // 获取所有入参的类型
        for (Class<?> parameterType : parameterTypes) {
            // 判断一下当前参数的类型是否是请求对象
            if (ServletRequest.class.isAssignableFrom(parameterType)) {
                // 如果是请求对象，那么直接给你一个请求对象
                params[paramIndex++] = req;
            }

            // 判断一下当前参数的类型是否是响应对象
            if (ServletResponse.class.isAssignableFrom(parameterType)) {
                // 如果是请求对象，那么直接给你一个请求对象
                params[paramIndex++] = resp;
            }

            // 这个方法是获取当前方法的第几个形参的所有注解
            // 因为一个方法有多个参数，一个参数还可能会有多个注解
            // 一维数组对应第几个参数
            // 二维数组对应该参数上的几个注解
            Annotation[] parameterAnnotations = method.getParameterAnnotations()[index++];

            // 判断第N个参数上是否有注解，如果有得话这里应该是大于0的，没有就算了，我们不需要进行操作
            if (parameterAnnotations.length > 0) {
                // 获取该参数上的所有注解
                for (Annotation parameterAnnotation : parameterAnnotations) {
                    // 判断是不是RequestParam注解
                    if (RequestParam.class.isAssignableFrom(parameterAnnotation.getClass())) {
                        // 强转
                        RequestParam requestParam = (RequestParam) parameterAnnotation;

                        // 从前端获取请求参数
                        String parameter = req.getParameter(requestParam.value());

                        // 判断一下入参的类型是否是Integer
                        if (Integer.class.isAssignableFrom(parameterType)) {
                            try {
                                int integerValue = Integer.parseInt(parameter);
                                params[paramIndex++] = integerValue;
                            } catch (NumberFormatException e) {
                                try {
                                    resp.getWriter().print("当前的类型不匹配：" + parameterType.getTypeName());

                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                        } else {
                            params[paramIndex++] = parameter;
                        }
                    }
                }
            }
        }

        return params;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void urlMapping() {
        // 从ioc容器中获取所有的对象
        for (Object iocObject : ioc.values()) {
            // 获取字节码对象
            Class<?> clazz = iocObject.getClass();

            // 判断当前对象是否是Controller对象，因为只有Controller才需要进行URL映射
            if (clazz.isAnnotationPresent(Controller.class)) {
                // 获取类上的RequestMapping注解
                RequestMapping requestMappingAnnotation = clazz.getAnnotation(RequestMapping.class);

                // 类上的请求路径【一级路径】
                String classUrl = "";
                // 方法上的请求路径【二级路径】
                String methodUrl;

                // 如果这个注解不为空
                if (requestMappingAnnotation != null) {
                    // 获取类上的请求路径
                    classUrl = requestMappingAnnotation.value();

                    // 判断是否是以/开头，如果不是就添加上
                    if (!classUrl.startsWith("/")) {
                        classUrl = "/" + classUrl;
                    }
                }

                // 接下来的操作就是核心的对方法的操作
                // 获取Controller中的所有方法
                Method[] methods = clazz.getMethods();

                // 迭代所有的方法
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        // 获取方法上面的RequestMapping注解
                        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);

                        methodUrl = methodAnnotation.value();

                        if (!methodUrl.startsWith("/")) {
                            methodUrl = "/" + methodUrl;
                        }

                        // 这个时候我们已经获取到了映射的路径了，接下来就需要把我们的处理器和映射的路径进行绑定
                        urlHandlers.put(classUrl + methodUrl, new UrlHandler(method, iocObject));
                    }
                }
            }
        }
    }

    // 完成ioc容器中依赖的注入操作
    private void dependencyInjection() {
        // 首先要获取ioc容器中的对象
        for (Map.Entry<String, Object> objectEntry : ioc.entrySet()) {
            // 这是我们容器中存放的对象：UserController对象和UserServiceImpl对象
            Object obj = objectEntry.getValue();

            // 获取字节码对象
            Class<?> clazz = obj.getClass();

            // 如果携带了Controller注解
            if (clazz.isAnnotationPresent(Controller.class)) {
                // 获取当前类中的所有成员变量
                Field[] fields = clazz.getDeclaredFields();

                // 迭代所有的成员变量
                for (Field field : fields) {
                    // 如果成员变量上有Autowired注解，说明需要被依赖注入
                    if (field.isAnnotationPresent(Autowired.class)) {
                        // 获取Autowired注解
                        Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);

                        // 获取BeanName
                        String beanName = autowiredAnnotation.value();

                        // 从容器中获取到的Bean对象
                        Object iocObject = null;

                        // 如果BeanName不为空，那么直接从容器中获取就行了
                        if (!beanName.equals("")) {
                            iocObject = ioc.get(beanName);
                            // 如果没有指定BeanName。只能通过类型去获取了
                        } else {
                            // 获取容器中所有的对象
                            for (Object value : ioc.values()) {
                                // 判断容器中的对象是否是当前成员变量的类型的对象，或者实现类对象
                                // 判断UserServiceImpl是否是UserService的对象或者实现类的对象
                                if (field.getType().isInstance(value)) {
                                    iocObject = value;
                                    // 直接break掉，因为单例的，只能赋值一次
                                    break;
                                }
                            }
                        }

                        // 开启暴力反射，可以直接对private修饰的成员进行操作
                        field.setAccessible(true);

                        // obj是Controller对象，iocObject是Service对象
                        // 把Controller对象的Service属性赋值为Service对象
                        try {
                            field.set(obj, iocObject);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    // 实例化加上了指定注解的对象并放在ioc容器中
    private void inversionOfControl() {
        try {
            // 迭代所有的路径
            for (String classUrl : classUrls) {
                // 通过完整全限定名获取反射字节码对象
                Class<?> clazz = Class.forName(classUrl);

                // 判断当前字节码对象中是否包含了Controller注解
                if (clazz.isAnnotationPresent(Controller.class)) {
                    // 如果有这个注解，说明一定是Controller类的对象，直接创建出来
                    Object controllerObject = clazz.newInstance();

                    // 获取Controller注解
                    Controller controllerAnnotation = clazz.getAnnotation(Controller.class);

                    // 获取Controller注解中的值
                    String beanName = getBeanName(clazz, controllerAnnotation.value());

                    // 放到ioc容器中
                    ioc.put(beanName, controllerObject);

                } else if (clazz.isAnnotationPresent(Service.class)) {
                    // 如果有这个注解，说明一定是Service实现类的对象，直接创建出来
                    Object serviceObject = clazz.newInstance();

                    // 获取Service注解
                    Service serviceAnnotation = clazz.getAnnotation(Service.class);

                    // 获取Service注解中的值
                    String beanName = getBeanName(clazz, serviceAnnotation.value());

                    // 放到ioc容器中
                    ioc.put(beanName, serviceObject);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 根据注解获取BeanName
    private String getBeanName(Class<?> clazz, String beanName) {
        // 如果没有指定BeanName
        if (beanName.equals("")) {
            // 获取类名
            String typeName = clazz.getSimpleName();

            // 首字母转小写，并添加上/
            beanName = "/" + typeName.substring(0, 1).toLowerCase() + typeName.substring(1);
        } else if (!beanName.startsWith("/")) {
            // 如果没有加/我们帮他加上
            beanName = "/" + beanName;
        }
        return beanName;
    }

    // 扫描指定包下的所有类，并把类的完全的全限定名放到classUrls容器中
    private void doScanPackage(String packagePath) {
        // 我们传递的包名用的是.
        // 但是文件分隔符用的是/
        // 所以第一步我们需要把路径给改一下
        // 这里获取到的是编译之后的.class文件所在的根路径
        URL resource = this.getClass().getClassLoader().getResource(packagePath.replaceAll("\\.", "/"));

        // 判断根路径是否为null
        String filePath = Objects.requireNonNull(resource).getFile();

        // 获取根路径的文件对象
        File file = new File(filePath);

        // 获取com.fc下的所有文件
        String[] list = file.list();

        // 如果list对象不为null，就进行遍历
        for (String subPath : list != null ? list : new String[0]) {
            // 获取com.fc下面的所有文件的File对象
            File subFile = new File(filePath + "/" + subPath);

            // 如果是文件夹，递归执行
            if (subFile.isDirectory()) {
                doScanPackage(packagePath + "." + subPath);

                // 只要不是文件夹就一定是.class文件
            } else {
                // 把class文件的后缀名去掉，再和前面的包名进行拼接
                classUrls.add(packagePath + "." + subPath.replace(".class", ""));
            }
        }
    }
}
