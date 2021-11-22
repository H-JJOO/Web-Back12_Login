package com.koreait.board4.user;

import com.koreait.board4.MyUtils;
import com.koreait.board4.dao.UserDAO;
import com.koreait.board4.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.disForward(req, res, "/user/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserVO param = new UserVO();

        param.setUid(uid);
        param.setUpw(upw);

        //0:로그인실패, 1: 로그인성공, 2: 아이디없음, 3: 비밀번호틀림
        int result = UserDAO.login(param);

        if (result == 1) {
            param.setUpw(null);

            HttpSession session = req.getSession();
            session.setAttribute("loginUser", param);

            res.sendRedirect("/board/list");
            return;
        }
        String err = null;
        switch (result) {
            case 0 :
                err = "로그인을 실패하였습니다.";
                break;
            case 2 :
                err = "아이디를 확인하세요.";
                break;
            case 3 :
                err = "비밀번호를 확인하세요.";
                break;
        }
        req.setAttribute("err", err);
        doGet(req, res);








    }
}
