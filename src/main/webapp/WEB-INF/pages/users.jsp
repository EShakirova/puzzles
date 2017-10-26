<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 22.10.17
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Пользователи</title>
</head>
<body>

<table  width="50%" style="margin: 0 auto;">
        <tr>
            <th bgcolor="#696969">ID</th>
            <th bgcolor="#696969">Фамилия</th>
            <th bgcolor="#696969">Имя</th>
            <th bgcolor="#696969">Логин</th>
            <th bgcolor="#696969">Адрес эл.почты</th>
            <th bgcolor="#696969">Роль</th>
        </tr>
    <c:forEach  items="${users}" var="item">
    <tr>
        <td width="10%"  valign="center"> <a href="registration.jsp"><c:out value="${item.id}"></c:out></a> </td>
            <td width="20%" valign="center"> <c:out value="${item.lastName}"></c:out> </td>
            <td width="15%" valign="center"> <c:out value="${item.firstName}"></c:out> </td>
            <td width="10%" valign="center"> <c:out value="${item.login}"></c:out> </td>
            <td width="20%" valign="center"> <c:out value="${item.emailaddress}"></c:out> </td>
            <td width="10%"  valign="center">    <c:if test="${item.isAdmin()}">Админ</c:if>
                                                <c:if test="${!item.isAdmin()}">Пользователь</c:if>   </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
