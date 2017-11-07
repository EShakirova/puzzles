<html>
<head>
    <title>Редактирование ребуса/загадки</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript">
        function add(i) {
            i++;
            $('#add-button').parent().remove();
            var html = '<p><input type="text" name="answer'+i+'"/>';
            html += '<input type="radio" name="iscorrect" value="item' + i + '"/>Правильный варинт ответа</p>'
            html += '<p><a id="add-button" href="#" onclick="add(';
            html += i + ')">Добавить вариант ответа</a> </p>';
            $('#questions').append(html);

        }
        $(document).ready(function() {
            add(0);
        });
    </script>
</head>
<body>

<table  width="40%" style="margin: 0 auto;">
    <form id="myForm" method="post" action="/puzzles/editPuzzle">
        <tr>
            <td  width="80%"><label>ID </label><label><c:out value="${puzzle.id}"></c:out></label></td>
        </tr>
        <tr>
            <td width="80%"><fieldset>
                <legend>Уровень сложности</legend>
                <select name="difficultyLevel">
                    <c:forEach items="${diflevels}" var="item" >
                        <option value="${item.id}">${item.name}</option>
                        <%--<option selected value="${puzzle.difficultyLevel.name}">${puzzle.difficultyLevel.name}</option>
                   --%> </c:forEach>
                </select>
            </fieldset>
            </td>
        </tr>
        <tr>
            <td width="80%">
                <fieldset >
                    <legend>Тип вопроса</legend>
                    <input name="behavior" type="radio" value="true"  <c:if test="${puzzle.behavior = true}">checked</c:if>>Загадка
                    <input name="behavior" type="radio" value="false" <c:if test="${puzzle.behavior = false}">checked</c:if>>Ребус
                </fieldset>
            </td>
        </tr>
        <tr>
            <td width="80%"><fieldset>
                <legend>Текст вопроса</legend>
                <textarea name="question" rows="5" cols="80" maxlength="200">${puzzle.question}</textarea>
            </fieldset>
            </td>
        </tr>
        <tr>
            <td width="80%"><fieldset id="questions">
                <legend>Варианты ответов</legend>

            </fieldset>
            </td>
        </tr>
        <tr>
            <td align="center"><input type="submit" name="save"  value="Сохранить"></td>
        </tr>
    </form>
</table>

</body>
</html>
