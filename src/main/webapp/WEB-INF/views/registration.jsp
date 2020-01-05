<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
    <title>Registration</title>
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
        <h2 class="form-signin-heading">Register data</h2>
        <p>
            <label for="firstName" class="sr-only">firstName</label>
            <input type="text" id="firstName" name="registration.firstName" class="form-control"
                   placeholder="First name" required autofocus>
        </p>
        <p>
            <label for="lastName" class="sr-only">lastName</label>
            <input type="text" id="lastName" name="registration.lastName" class="form-control"
                   placeholder="Last name" required>
        </p>
        <p>
            <label for="patronymic" class="sr-only">patronymic</label>
            <input type="text" id="patronymic" name="registration.patronymic" class="form-control"
                   placeholder="Patronymic" required>
        </p>
        <p>
            <label for="login" class="sr-only">login</label>
            <input type="text" id="login" name="registration.login" class="form-control" placeholder="Login"
                   required>
        </p>
        <p>
            <label for="password" class="sr-only">password</label>
            <input type="password" id="password" name="registration.password" class="form-control"
                   placeholder="Password" required>
        </p>

        <input class="btn btn-lg btn-primary btn-block" type="button" value="Registration"
               onclick="RestPost(
                $('#firstName').val(),
                $('#lastName').val(),
                $('#patronymic').val(),
                $('#login').val(),
                $('#password').val())"/>

        <input class="btn btn-lg btn-primary btn-block" type="button" value="Cancel"
               onclick="location.pathname='login'"/>
    </form>
</div>

<div class="panel panel-default">
    <div class="page-heading">
        <strong>RESPONSE</strong>
    </div>
    <div class="panel-body" id="response">
    </div>
</div>

</body>
</html>