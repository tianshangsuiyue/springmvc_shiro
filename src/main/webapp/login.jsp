<%--
  Created by IntelliJ IDEA.
  User: 97590
  Date: 2018/1/28
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/login/dologin.html" method="post" name="loginform">
        <div>
            <label> 用户名： </label>
            <input name="username" type="text">
            <label> 密码： </label>
            <input name="pwd" type="text">
            <input type="submit" value="submint"/>
        </div>

    </form>
</body>
</html>
