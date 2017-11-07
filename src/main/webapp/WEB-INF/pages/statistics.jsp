<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 28.10.2017
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Статистика ответов</title>
</head>
<body>
<table width="60%" align="center" style="margin: 0 auto;">
    <tr>
        <th bgcolor="#a9a9a9">Вопрос</th>
        <th bgcolor="#a9a9a9">Уровень сложности</th>
        <th bgcolor="#a9a9a9">Кол-во попыток</th>
        <th bgcolor="#a9a9a9">Затрачено времени, с</th>
        <th bgcolor="#a9a9a9">Решено</th>
        <th bgcolor="#a9a9a9"></th>
    </tr>
    <c:forEach  items="${puzzles}" var="item">
        <tr>
            <td width="10%" align="center" > <c:out value="${item.question}"></c:out></td>
            <td width="10%" align="center"> <c:out value="${item.difficultyLevel.name}"></c:out> </td>
            <td width="10%" align="center"> <c:out value="${item.unsuccessfulAttemptsCount}"></c:out> </td>
            <td width="10%" align="center"> <c:out value="${item.elapsedTime}"></c:out> </td>
            <td width="10%" align="center"> <c:out value="${item.isSolved()}">решено</c:out> </td>

            <form id="myFormEdit" method="get" action="/puzzles/answer">
                <input type="hidden" name="puzzleID" value = '<c:out  value="${item.id}"></c:out>'/>
                    <%--<input type="submit" name="puzzleID" value="Edit"/>--%>
            </form>
            <td width="10%" align="center">
                <c:if test="${item.isSolved()}">
                    <a href="#" onclick="document.getElementById('myFormEdit').submit()">Open</a>
                </c:if>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
