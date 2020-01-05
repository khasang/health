<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
    <title>Hello</title>
</head>
<script>
    var service = window.location.href + '/add';

    var RestPost = function (firstName, lastName, patronymic, login, password) {
        var JSONObject = {
            'firstName': firstName,
            'lastName': lastName,
            'patronymic': patronymic,
            'login': login,
            'password': password
        };

        $.ajax({
            type: 'POST',
            url: service,
            dataType: 'json',
            data: JSON.stringify(JSONObject),
            accept: 'json',
            contentType: 'application/json;utf-8',
            async: false,
            success: function (result) {
                $('#response').html(JSON.stringify(result))
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#response').html(JSON.stringify(jqXHR))
            }
        });
    };
</script>
<body>
<div class="container">
    <form class="form-signin">
        <h2 class="form-signin-heading">Login ${login}</h2>

        <input class="btn btn-lg btn-primary btn-block" type="button" value="Exit"
               onclick="location.pathname='login'"/>
    </form>
</div>
</body>
</html>
