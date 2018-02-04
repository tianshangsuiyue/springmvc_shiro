<%--
  Created by IntelliJ IDEA.
  User: 97590
  Date: 2018/2/1
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>fail</title>
</head>
<body>
<p>fail:  </p>
<input type="text" value="${request.faiMsg}">
<p>-----------------</p>
<p>
    <%=request.getAttribute("faiMsg")%>
</p>
</body>
</html>
