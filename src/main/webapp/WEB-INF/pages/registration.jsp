<%--
  Created by IntelliJ IDEA.
  User: sa
  Date: 16.10.17
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<c:if test="${empty user}"><h1>Регистрация</h1></c:if>
<c:if test="${not empty user}"><h1>Редактирование</h1></c:if>

<table style="margin: 0 auto;">
    <form id="myForm"
          <c:if test="${empty user}">action="/puzzles/registration"</c:if>
          <c:if test="${not empty user}">action="/puzzles/saveUser"</c:if>
          <c:if test="${empty user}">method="get" </c:if>
          <c:if test="${not empty user}">method="post" </c:if>
          onsubmit="return validate()" onclick="return validate()">
        <tr>
            <td align="right"><label>Имя*</label></td>
            <td  width="80%"><input type="text" name="firstName"
                                    value='<c:if test="${not empty user}">${user.firstName}</c:if>'/></td>
        </tr>
        <tr>
            <td align="right"><label>Фамилия</label></td>
            <td width="80%"><input type="text" name="lastName"
                                   value='<c:if test="${not empty user}">${user.lastName}</c:if>'/></td>
        </tr>
        <tr>
            <td align="right"><label>Логин*</label></td>
            <td width="80%"><input type="text" name="login"
                                   value='<c:if test="${not empty user}">${user.login}</c:if>'/></td>
        </tr>
        <tr>
            <td align="right"><label>Адрес*</label></td>
            <td width="80%"><input type="text" name="email"
                                   value='<c:if test="${not empty user}">${user.emailaddress}</c:if>'/></td>
        </tr>
        <tr>
            <td align="right"><label>Пароль*</label></td>
            <td width="80%"><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td align="right"><label>Пароль еще раз*</label></td>
            <td width="80%"><input type="password" name="password2"/></td>
        </tr>
        <tr><c:if test="${not empty user}">
                <td align="right">Роль</td>
                <td>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="checkbox" name="isAdmin" <c:if test="${user.isAdmin()}">checked</c:if> />Администратор
                </td>
            </c:if>
        </tr>
        <tr>
            <td align="right"><label></label></td>
            <td><input type="submit" name="newUser" onsubmit="return validate()" onclick="return validate()" value="Сохранить"></td>
        </tr>

    </form>
</table>
</body>
</html>
