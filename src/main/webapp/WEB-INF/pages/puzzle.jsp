<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 22.10.17
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Ребус/загадка</title>
</head>
<body>

<table  style="margin: 0 auto;">
    <form id="myForm" method="post" action="/puzzles/editPuzzle">
        <tr>
            <td align="right"><label>ID</label></td>
            <td  width="80%"><label><c:out value="${puzzle.id}"></c:out></label></td>
        </tr>
        <tr>
            <td align="right"><label>Уровень сложности</label></td>
            <td width="80%">
                <select name="difficultyLevel[]">
                    <c:forEach var="item" items="${diflevels}">
                        <option>${item.name}</option>
                        <%--<option selected value="${puzzle.difficultyLevel.name}">${puzzle.difficultyLevel.name}</option>
                   --%> </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td align="right"><label>Тип вопроса</label></td>
            <td width="80%"><select  name="behavior[]">
                <option selected value="${puzzle.behavior}">${puzzle.behavior}</option>
                <option>${!puzzle.behavior}</option>
            </select>
            </td>
        </tr>
        <tr>
            <td align="right"><label>Текст вопроса</label></td>
            <td width="80%"><textarea name="question" rows="5" cols="80" maxlength="200">${puzzle.question}</textarea>
            </td>
        </tr>
        <tr>
            <td align="right"><label>Ответы</label></td>
            <td width="80%"><input type="text" name="answer"/>
            </td>
        </tr>
        <tr>
            <td align="right"><label></label></td>
            <td><input type="submit" name="save"  value="Сохранить"></td>
        </tr>
    </form>
</table>

</body>
</html>
