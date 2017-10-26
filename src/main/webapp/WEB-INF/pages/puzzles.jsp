<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 16.10.17
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Ребусы и загадки</title>
</head>

<body>

    <table width="60%" align="center" style="margin: 0 auto;">
        <tr>
            <th bgcolor="#696969">ID</th>
            <th bgcolor="#696969">Вопрос</th>
            <th bgcolor="#696969">Уровень сложности</th>
            <th bgcolor="#696969">Кол-во ответов пользователей</th>
            <th bgcolor="#696969"></th>
            <th bgcolor="#696969"></th>
        </tr>
        <c:forEach  items="${puzzles}" var="item">
            <tr>
                <td width="10%" align="center" > <c:out value="${item.id}"></c:out></td>
                <td width="50%" align="left">    <c:out value="${item.question}"></c:out> </td>
                <td width="10%" align="center"> <c:out value="${item.difficultyLevel}"></c:out> </td>
                <td width="10%" align="center"> <c:out value="${item.statisticCount}"></c:out> </td>

                     <form id="myFormEdit" method="get" action="/puzzles/puzzleEdit">
                        <input type="hidden" name="puzzleID" value = '<c:out  value="${item.id}"></c:out>'/>
                        <%--<input type="submit" name="puzzleID" value="Edit"/>--%>
                    </form>



                    <form id="myFormDelete" method="get" action="/puzzles/puzzleDelete">
                        <input type="hidden" name="puzzleID" value = '<c:out  value="${item.id}"></c:out>'/>
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
