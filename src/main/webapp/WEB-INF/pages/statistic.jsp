<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 28.10.2017
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Ответ</title>
</head>
<body>
<table  width="40%" style="margin: 0 auto;">
    <form id="myForm" method="post" action="/puzzles/statisticSave">
        <tr>
            <td>
                Уровень сложности ${puzzle.difficultyLevel.name}
            </td>
        </tr>
        <tr>
            <td>
                <fieldset>
                    <legend>Вопрос</legend>
                    <p>${puzzle.question}</p>
                </fieldset>
            </td>
        </tr>
        <c:forEach  items="${puzzle.answerSet}" var="item">
        <tr>
            <td width="80%">
                <input type="hidden" name="answerID" value="${item.id}"/>
                <input name="answer" type="radio" value="true">${item.answerText}
            </td>
        </tr>
        </c:forEach>
        <tr>
            <td align="center">
                <input type="hidden" name="puzzleID" value="${puzzle.id}"/>
                <input type="submit" name="statisticSave"  value="Сохранить"></td>
        </tr>
    </form>
</table>

</body>
</html>
