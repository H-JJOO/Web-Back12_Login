<%@ page import="com.koreait.board4.model.UserVO" %>
<%@ page import="com.koreait.board4.model.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserVO loginUser = (UserVO)session.getAttribute("loginUser");
    BoardVO vo = (BoardVO)request.getAttribute("data");
    int prevIboard = (int)request.getAttribute("prevIboard");
    int nextIboard = (int)request.getAttribute("nextIboard");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${requestScope.data.title}</title>
    <style>
        .err {color: red;}
    </style>

</head>
<body>
    <h1>Detail</h1>
    <div class="err">${err}</div>
    <%if (loginUser != null && vo.getWriter() == loginUser.getIuser()) { %>
    <div>
        <a href="/board/del?iboard=<%=vo.getIboard()%>"><input type="button" value="삭제"></a>
        <a href="/board/mod?iboard=<%=vo.getIboard()%>"><input type="button" value="수정"></a>
    </div>
    <% } %>
    <div>번호 : ${requestScope.data.iboard}</div>
    <div>제목 : ${requestScope.data.title}</div>
    <div>작성자 : ${requestScope.data.writerNm}</div>
    <div>작성일자 : ${requestScope.data.rdt}</div>
    <div>내용 : ${requestScope.data.ctnt}</div>
    <div>
        <% if (prevIboard != 0) { %>
        <a href="/board/detail?iboard=<%=prevIboard%>"><input type="button" value="이전글"></a>
        <% } %>
        <% if (nextIboard != 0) { %>
        <a href="/board/detail?iboard=<%=nextIboard%>"><input type="button" value="다음글"></a>
        <% } %>
    </div>
    <div><a href="/board/list"><input type="button" value="리스트로 이동"></a></div>
</body>
</html>
