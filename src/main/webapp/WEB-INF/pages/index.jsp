<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 16.10.17
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Ребусы и загадки</title>
</head>
<body>
<script>
    function showError(container, errorMessage) {
        container.className = 'error';
        var msgElem = document.createElement('span');
        msgElem.className = "error-message";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
    }

    function resetError(container) {
        container.className = '';
        if (container.lastChild.className == "error-message") {
            container.removeChild(container.lastChild);
        }
    }

    function validate() {
        value = true
        var elems = myForm.elements;

        resetError(elems.login.parentNode);
        if (!elems.login.value) {
            showError(elems.login.parentNode, ' Введите логин ');
            value = false;
        } else if (elems.login.value.length < 4 || elems.login.value.length > 20) {
            showError(elems.login.parentNode, ' Логин должен содержать 4-20 символов');
            value = false;
        } else if (parseInt(elems.login.value.substr(0, 1))) {
            showError(elems.login.parentNode, ' Логин должен начинаться с буквы');
            value = false;
        }

        resetError(elems.password.parentNode);
        if (!elems.password.value) {
            showError(elems.password.parentNode, ' Введите пароль.');
            value = false;
        } else if (elems.password.value.length < 4 || elems.password.value.length > 20) {
            showError(elems.password.parentNode, ' Пароль должен содержать 4-20 символов');
            value = false;
        }

        return value;
    }
</script>

<table style="margin: 0 auto;">

    <form id="myForm" method="post" action="/puzzles/auth" onsubmit="return validate()">
        <tr><td></td>
            <td>
            </td>
        </tr>
        <tr>
            <td align="right"><label>Логин</label></td>
            <td width="80%"><input type="text" name="login"
                                   value='<c:out value="${login}"></c:out>'/>
            </td>
        </tr>
        <tr>
            <td align="right"><label>Пароль</label></td>
            <td width="80%"><input type="password" align="right" name="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" onclick="return validate()" value="login"/></td>
        </tr>

    </form>
<tr>
    <td>
        <form id="myFormReg" method="post" action="/puzzles/registration">
            <input type="submit"  value="registration"/>
        </form>
    </td>
</tr>

</table>
</body>
</html>