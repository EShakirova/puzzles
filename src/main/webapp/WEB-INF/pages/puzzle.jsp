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

<table style="margin: 0 auto;">
    <form id="myForm" method="post" action="/puzzles/puzzleEdit" onsubmit="return validate()" onclick="return validate()">
        <tr>
            <td align="right"><label>ID</label></td>
            <td  width="80%"><label><c:out value="${puzzle.id}"></c:out></label></td>
        </tr>
        <tr>
            <td align="right"><label>Уровень сложности</label></td>
            <td width="80%"><select size="2" multiple name="difficultyLevel">
                <option value='<c:out value="${puzzle.difficultyLevel}"></c:out>'/>
            </select>
            </td>
        </tr>
        <tr>
            <td align="right"><label>Тип вопроса</label></td>
            <td width="80%"><select size="2" multiple name="behavior">
                <option value='<c:out value="${puzzle.behavior}"></c:out>'/>
            </select>
            </td>
        </tr>
        <tr>
            <td align="right"><label>Текст вопроса</label></td>
            <td width="80%"><textarea name="question" rows="5" cols="40">
                                <c:out value="${puzzle.question}"></c:out>
                            </textarea>
            </td>
        </tr>
        <tr>
            <td align="right"><label>Ответ</label></td>
            <td width="80%"><input type="text" name="answer"
                                   value='<c:out value="${puzzle.answer}"></c:out>'/>
            </td>
        </tr>
        <tr>
            <td align="right"><label></label></td>
            <td><input type="submit" name="save" onsubmit="return validate()" onclick="return validate()" value="ok"></td>
        </tr>
    </form>
</table>

</body>
</html>
