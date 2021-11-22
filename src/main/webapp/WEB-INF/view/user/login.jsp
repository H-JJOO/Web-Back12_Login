<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        html {height: 100%; width: 100%;}
        .position {height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: center;}
        .err {color: red;}

    </style>
</head>
<body class="position">
    <h1>Login</h1>
    <div class="err">${err}</div>
    <form action="/user/login" method="post">
        <div><input type="text" name="uid" placeholder="id"></div>
        <div><input type="password" name="upw" placeholder="password"></div>
        <span><input type="submit" value="login"></span>
        <span><a href="/user/join"><input type="button" value="join"></a></span>
    </form>
</body>
</html>
