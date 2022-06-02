package com.fc.servlet;

import com.fc.annotation.Controller;
import com.fc.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.net.URL;
import java.util.*;

public class DispatcherServlet extends HttpServlet {
    // 指定包下的所有类完整的路径【通过包扫描到的所有内容】
    private final List<String> classUrls = new ArrayList<>();
    // ioc容器，键是BeanName，值就是Bean对象
    private final Map<String, Object> ioc = new HashMap<>();
    // 存放映射地址和对象的容器
    private final Map<String, Object> urlHandlers = new HashMap<>();

    // 初始化时完成
    @Override
    public void init(ServletConfig config) throws ServletException {
        doScanPackage("com.fc"); // 扫描指定包下的所有的类，将路径加载到classUrls中
        inversionOfControl(); // 把携带指定注解的类放到ioc容器中
        System.out.println("---------");
        //dependencyInjection(); // 把具有依赖关系的对象进行赋值
        //urlMapping(); // 将请求的路径和对象方法映射到一起
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

                    ioc.put(beanName, controllerObject);

                } else if (clazz.isAnnotationPresent(Service.class)) {
                    // 如果有这个注解，说明一定是Service实现类的对象，直接创建出来
                    Object serviceObject = clazz.newInstance();

                    // 获取Service注解
                    Service serviceAnnotation = clazz.getAnnotation(Service.class);

                    // 获取Service注解中的值
                    String beanName = getBeanName(clazz, serviceAnnotation.value());

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
