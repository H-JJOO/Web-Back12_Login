<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Write</title>
    <style>
        .err {color: red;}
    </style>
</head>
<body>
    <h1>Write</h1>
    <div class="err">${err}</div>
    <form action="/board/write" method="post">
        <div><input type="text" name="title" placeholder="title" value="${requestScope.data.title}"></div>
        <div><textarea name="ctnt" placeholder="content" rows="10">${requestScope.data.ctnt}</textarea></div>
        <div>
            <input type="submit" value="글등록">
            <input type="reset" value="초기화">
            <a href="/board/list"><input type="button" value="리스트"></a>
        </div>
    </form>
</body>
</html>
