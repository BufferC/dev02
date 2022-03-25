<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
    <img src="http://localhost:8081/upload/${img}">
    <form method="post" action="/file/download2?filename=${img}">
        <input type="submit" value="下载">
    </form>
</body>
</html>