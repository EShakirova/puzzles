<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 16.10.17
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
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
        } else if (/^[a-zA-Z1-9]+$/.test(elems.login.value) == false) {
            showError(elems.login.parentNode, ' Логин должен содержаьб только лат. буквы и цифры');
            value = false;
        } else if (elems.login.value.length < 4 || elems.login.value.length > 20) {
            showError(elems.login.parentNode,' В логине должно быть от 4 до 20 символов');
            value = false;
        }else if (parseInt(elems.login.value.substr(0, 1))) {
            showError(elems.login.parentNode,' Логин должен начинаться с буквы');
            value = false;
        }

        resetError(elems.password.parentNode);
        if (!elems.password.value) {
            showError(elems.password.parentNode, ' Введите пароль');
            value = false;
        }else if (elems.password.value.length < 4 || elems.password.value.length > 20) {
            showError(elems.password.value.parentNode,' В пароле должно быть от 4 до 20 символов');
            value = false;
        }else if (elems.password.value != elems.password2.value) {
            showError(elems.password.parentNode, ' Пароли не совпадают');
            value = false;
        }else if (/^[a-zA-Z1-9]+$/.test(elems.password.value) == false) {
            showError(elems.password.value.parentNode, ' Пароль должен содержать только лат. буквы и цифры');
            value = false;
        }

        resetError(elems.firstName.parentNode);
        if (!elems.firstName.value) {
            showError(elems.firstName.parentNode, ' Ввведите имя пользователя');
            value = false;
        }

        resetError(elems.email.parentNode);
        if (!elems.email.value) {
            showError(elems.email.parentNode, ' Введите адрес электронной почты');
            value = false;
        } else if (elems.email.value.indexOf("@") == -1) {
            showError(elems.email.parentNode, ' Некорректный адрес электронной почты');
            value = false;
        }
        return value;
    }
</script>

<table style="margin: 0 auto;">
    <form id="myForm" method="get" action="/puzzles/registration" onsubmit="return validate()" onclick="return validate()">
        <tr>
            <td align="right"><label>Имя*</label></td>
            <td  width="80%"><input type="text" name="firstName"/></td>
        </tr>
        <tr>
            <td align="right"><label>Фамилия</label></td>
            <td width="80%"><input type="text" name="lastName"/></td>
        </tr>
        <tr>
            <td align="right"><label>Логин*</label></td>
            <td width="80%"><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td align="right"><label>Адрес*</label></td>
            <td width="80%"><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td align="right"><label>Пароль*</label></td>
            <td width="80%"><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td align="right"><label>Пароль еще раз*</label></td>
            <td width="80%"><input type="password" name="password2"/></td>
        </tr>
        <tr>
            <td align="right"><label></label></td>
            <td><input type="submit" name="newUser" onsubmit="return validate()" onclick="return validate()" value="ok"></td>
        </tr>
    </form>
</table>
</body>
</html>
