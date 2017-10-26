<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 17.10.17
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Восстановленная база</title>
</head>

<body>

<table style="margin: 0 auto;">
    <tr><td width="10%" valign="center">База успешно восстановлена</td></tr>
    <tr>
        <td width="10%" valign="center">
            <form id ="MyForm" method="get" action="/puzzles/puzzle">
                <input type="submit" name="action" value="Список загадок">
            </form>
        </td>
    </tr>

</table>
</body>
</html>
