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
<h1 align="center">Пользователи</h1>
<table  width="60%" style="margin: 0 auto;">
        <tr>
            <th bgcolor="#a9a9a9">ID</th>
            <th bgcolor="#a9a9a9">Имя</th>
            <th bgcolor="#a9a9a9">Фамилия</th>
            <th bgcolor="#a9a9a9">Логин</th>
            <th bgcolor="#a9a9a9">Адрес эл.почты</th>
            <th bgcolor="#a9a9a9">Роль</th>
            <th bgcolor="#a9a9a9"></th>
            <th bgcolor="#a9a9a9"></th>
        </tr>
    <c:forEach  items="${users}" var="item">
    <tr>
            <td width="10%" align="center"> <a href="registration.jsp"><c:out value="${item.id}"></c:out></a> </td>
            <td width="20%" align="center"> <c:out value="${item.firstName}"></c:out> </td>
            <td width="15%" align="center"> <c:out value="${item.lastName}"></c:out> </td>
            <td width="15%" align="center"> <c:out value="${item.login}"></c:out> </td>
            <td width="20%" align="center"> <c:out value="${item.emailaddress}"></c:out> </td>
            <td width="15%" align="center"> <c:if test="${item.isAdmin()}">Админ</c:if>
                                            <c:if test="${!item.isAdmin()}">Пользователь</c:if>   </td>

        <form id="myFormEdit" method="post" action="editUser">
            <input type="hidden" name="userID" value = '<c:out  value="${item.id}"></c:out>'/>
                <%--<input type="submit" name="puzzleID" value="Edit"/>--%>
        </form>
        <form id="myFormDelete" method="get" action="userDelete">
            <input type="hidden" name="userID" value = '<c:out  value="${item.id}"></c:out>'/>
            <!--<input type="submit" name="puzzleID" value="Delete"/>-->
        </form>
        <td width="10%" align="center">
            <a href="#" onclick="document.getElementById('myFormEdit').submit()">Edit</a>
        </td>
        <td width="10%" align="center">
            <a href="#" onclick="document.getElementById('myFormDelete').submit()">Delete</a>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
