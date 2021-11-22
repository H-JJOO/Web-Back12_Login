<%@ page import="com.koreait.board4.model.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.koreait.board4.model.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserVO loginUser = (UserVO)session.getAttribute("loginUser");
    List<BoardVO> list = (List<BoardVO>)request.getAttribute("data");
    int maxPage = (int)request.getAttribute("maxPage");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List</title>
    <style>
        .position {height: 100%; display: flex; flex-direction: column; justify-content: center;}
    </style>
</head>
<body class="position">
    <%if (loginUser == null) { %>
    <div><a href="/user/login"><input type="button" value="login"></a></div>
    <% } %>

    <%if (loginUser != null) { %>
    <div>
        <%=loginUser.getUid()%>(<%=loginUser.getNm()%>)님 환영합니다.
        <a href="/user/logout"><input type="button" value="logout"></a>
    </div>
    <% } %>
    <h1>List</h1>

    <% if (loginUser != null) { %>
    <div><a href="/board/write"><input type="button" value="글쓰기"></a></div>
    <% } %>

    <table border="1">
        <tr>
            <th>글 번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일자</th>
        </tr>

        <% for (BoardVO vo: list) { %>
        <tr>
            <td><%=vo.getIboard()%></td>
            <td>
                <a href="/board/detail?iboard=<%=vo.getIboard()%>">
                    <%=vo.getTitle()%>
                </a>
            </td>
            <td><%=vo.getWriterNm()%></td>
            <td><%=vo.getRdt()%></td>
        </tr>
        <% } %>
        </div>
    </table>

    <div style="display: flex; justify-content: center;">
            <%for (int i = 1; i <= maxPage; i++ ) { %>
        <span><a href="/board/list?page<%=i%>"><%=i%></a></span>
            <% } %>
</body>
</html>
