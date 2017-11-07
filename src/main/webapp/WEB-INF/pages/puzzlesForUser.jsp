<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 28.10.2017
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Ребусы и загадки (без ответов)</title>
</head>
<body>
<table width="60%" align="center" style="margin: 0 auto;">
    <tr>
        <th bgcolor="#a9a9a9">Вопрос</th>
        <th bgcolor="#a9a9a9">Уровень сложности</th>
        <th bgcolor="#a9a9a9"></th>
    </tr>
    <c:forEach  items="${puzzles}" var="item">
        <tr>
            <td width="50%" align="left"><c:out value="${item.question}"></c:out> </td>
            <td width="50%" align="left"><c:out value="${item.difficultyLevel.name}"></c:out> </td>

            <form id="myFormEdit" method="get" action="/puzzles/statistic">
                <input type="hidden" name="puzzleID" value = '<c:out  value="${item.id}"></c:out>'/>
                    <%--<input type="submit" name="puzzleID" value="Edit"/>--%>
            </form>
            <td width="10%" align="center">
                <a href="#" onclick="document.getElementById('myFormEdit').submit()">Open</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
