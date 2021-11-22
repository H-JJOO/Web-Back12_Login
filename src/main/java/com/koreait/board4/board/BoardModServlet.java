package com.koreait.board4.board;

import com.koreait.board4.MyUtils;
import com.koreait.board4.dao.BoardDAO;
import com.koreait.board4.model.BoardVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (MyUtils.isLogout(req)) {
            res.sendRedirect("/board/login");
            return;
        }

        if (req.getAttribute("data") == null) {
            int iboard = MyUtils.parameterInt(req, "iboard");
            BoardVO param = new BoardVO();
            param.setIboard(iboard);
            BoardVO data = BoardDAO.selBoardDetail(param);
            req.setAttribute("data", data);
        }
        MyUtils.disForward(req, res, "/board/mod");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = MyUtils.parameterInt(req, "iboard");
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        BoardVO param = new BoardVO();

        param.setIboard(iboard);
        param.setTitle(title);
        param.setCtnt(ctnt);
        param.setWriter(MyUtils.getLoginUserIboard(req));

        int result = BoardDAO.updBoard(param);

        switch (result) {
            case 1:
                res.sendRedirect("/board/detail?iboard=" + iboard);
                break;
            case 0:
                req.setAttribute("err", "수정을 실패하였습니다.");
                req.setAttribute("data",  param);
                doGet(req, res);
                break;
        }

    }
}
