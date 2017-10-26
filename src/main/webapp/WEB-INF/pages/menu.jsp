<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 22.10.17
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Меню</title>
</head>
<body>
<table width="50%" style="margin: 0 auto;">
    <tr>
<c:forEach  items="${menu}" var="item">
    <td><a methods="get" href="puzzles/".<c:out value="${item.ahref}"></c:out> >
        <c:out value="${item.itemName}"></c:out> </a></td>
</c:forEach>
    <td width="10%">Выход</td></tr>
</table>

</body>
</html>
