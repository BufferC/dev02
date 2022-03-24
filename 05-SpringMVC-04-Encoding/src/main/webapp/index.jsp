<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form method="post" action="/user/getUser">
    <table align="center">
        <tr>
            <td>姓名</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input type="text" name="age"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="gender" value="男"> 男
                <input type="radio" name="gender" value="女" checked> 女
            </td>
        </tr>
        <tr>
            <td>日期</td>
            <td><input type="date" name="birthday"></td>
        </tr>
        <tr>
            <td>信息</td>
            <td><input type="text" name="info"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="reset" value="重置">
                <input type="submit" value="注册">
            </td>
        </tr>
    </table>
</form>
</body>
</html>