package com.koreait.board4.dao;

import com.koreait.board4.DbUtils;
import com.koreait.board4.model.BoardParamVO;
import com.koreait.board4.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board (title, ctnt, writer) " +
                    " VALUES (?, ?, ?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }

        return 0;
    }

    public static List<BoardVO> selBoardList(BoardParamVO param) {
        List<BoardVO> list = new ArrayList();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT A.iboard, A.title, A.rdt, B.nm AS writerNm " +
                    " FROM t_board A " +
                    " INNER JOIN t_user B " +
                    " ON A.writer = B.iuser " +
                    " ORDER BY A.iboard DESC " +
                    " LIMIT ?, ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getsIdx());
            ps.setInt(2, param.getRecordCnt());
            rs = ps.executeQuery();

            while (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setRdt(rs.getString("rdt"));
                vo.setWriterNm(rs.getString("writerNm"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static BoardVO selBoardDetail(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT A.title, A.ctnt, A.rdt, A.writer, B.nm AS writerNm " +
                    " FROM t_board A " +
                    " INNER JOIN t_user B " +
                    " ON A.writer = B.iuser " +
                    " WHERE A.iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if (rs.next()) {
                BoardVO vo = new BoardVO();
                vo.setIboard(param.getIboard());
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setRdt(rs.getString("rdt"));
                vo.setWriter(rs.getInt("writer"));
                vo.setWriterNm(rs.getString("writerNm"));
                return vo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }

    public static int selPrevIboard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iboard FROM t_board " +
                    " WHERE iboard > ? " +
                    " ORDER BY iboard " +
                    " LIMIT 1 ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("iboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static int selNextIboard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iboard FROM t_board " +
                    " WHERE iboard < ? " +
                    " ORDER BY iboard DESC " +
                    " LIMIT 1 ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("iboard");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }

    public static int selMaxPage(BoardParamVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT CEIL(COUNT(*) / ?) " +
                    " FROM t_board";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getRecordCnt());
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);//한개나올때 첫번째 컬럼
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return 0;
    }


    public static int updBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_board " +
                    " SET title = ?, " +
                    " ctnt = ?, " +
                    " mdt = now() " +
                    " WHERE iboard = ? " +
                    " AND writer = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getIboard());
            ps.setInt(4, param.getWriter());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delBoard(BoardVO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board " +
                    " WHERE iboard = ? " +
                    " AND writer = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            ps.setInt(2, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }


}
