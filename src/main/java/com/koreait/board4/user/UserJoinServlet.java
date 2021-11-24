package com.koreait.board4.user;

import com.koreait.board4.MyUtils;
import com.koreait.board4.dao.UserDAO;
import com.koreait.board4.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/join")
public class UserJoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        MyUtils.disForward(req, res, "/user/join");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");
        String nm = req.getParameter("nm");
        String phNum = req.getParameter("phNum");
        int gender = MyUtils.getParameterInt(req, "gender");

        UserVO param = new UserVO();

        param.setUid(uid);
        param.setUpw(upw);
        param.setNm(nm);
        param.setPhNum(phNum);
        param.setGender(gender);

        int result = UserDAO.join(param);

        switch (result) {
            case 1:
                res.sendRedirect("/user/login");
                break;
            case 0:
                req.setAttribute("err", "회원가입에 실패했습니다.");
                doGet(req, res);
                break;
        }

    }
}
