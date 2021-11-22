package com.koreait.board4;

import com.koreait.board4.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyUtils {
    //jsp 파일 이동
    public static void disForward(HttpServletRequest req, HttpServletResponse res, String jsp) throws ServletException, IOException {
        String path = "/WEB-INF/view" + jsp + ".jsp";
        req.getRequestDispatcher(path).forward(req, res);
    }

        public static int parseStringToInt(String str) {
            return parseStringToInt(str, 0);
        }

    public static int parseStringToInt(String str, int defVal) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defVal;
        }
    }

    public static int parameterInt(HttpServletRequest req, String key, int defVal) {
        return parseStringToInt(req.getParameter(key), defVal);
    }


    public static int parameterInt(HttpServletRequest req, String key) {
        String strVal = req.getParameter(key);
        int intVal = parseStringToInt(strVal);
        return intVal;
    }

    public static int getLoginUserIboard(HttpServletRequest req) {
        UserVO loginUser = getUserLogin(req);
        return loginUser == null ? 0 : loginUser.getIuser();
    }

    public static boolean isLogin(HttpServletRequest req) {
        return getUserLogin(req) != null;
    }

    public static boolean isLogout(HttpServletRequest req) {
        return getUserLogin(req) == null;
    }

    public static UserVO getUserLogin(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (UserVO)session.getAttribute("loginUser");
    }
}
