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

@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = MyUtils.getParameterInt(req, "iboard");

        BoardVO param = new BoardVO();

        param.setIboard(iboard);

        BoardVO data = BoardDAO.selBoardDetail(param);

        int prevIboard = BoardDAO.selPrevIboard(param);
        int nextIboard = BoardDAO.selNextIboard(param);

        req.setAttribute("data", data);
        req.setAttribute("prevIboard", prevIboard);
        req.setAttribute("nextIboard", nextIboard);

        MyUtils.disForward(req, res, "/board/detail");

    }
}
