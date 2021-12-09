package com.koreait.basic2.dao;

import com.koreait.basic2.DbUtils;
import com.koreait.basic2.board.model.BoardDTO;
import com.koreait.basic2.board.model.BoardEntity;
import com.koreait.basic2.board.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoardWithPk(BoardEntity entity) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " INSERT INTO t_board (title, ctnt, writer) " +
                    " VALUES (?, ?, ?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getWriter());
            result = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int iboard = rs.getInt(1);
                entity.setIboard(iboard);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return result;
    }

    public static int insBoard(BoardEntity entity) {
        //title, ctnt, writer
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board (title, ctnt, writer )" +
                " VALUES (?, ?, ?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int getMaxPageNum(BoardDTO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT CEIL(COUNT(iboard) / ?) " +
                    " FROM t_board ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getRowCnt());
            rs = ps.executeQuery();

            if (rs.next()) {
                int maxPageNum = rs.getInt(1);
                return maxPageNum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }

        return 0;

    }


    public static List<BoardVO> selBoardList(BoardDTO param) {
        List<BoardVO> list = new ArrayList();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.title, A.writer, A.hits, A.rdt , B.nm AS writerNm " +
                    " FROM t_board A " +
                    " INNER JOIN t_user B " +
                    " ON A.writer = B.iuser" +
                    " ORDER BY A.iboard DESC " +
                    " LIMIT ?, ?";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getStartIdx());
            ps.setInt(2, param.getRowCnt());
            rs = ps.executeQuery();

            while (rs.next()) {
                int iboard = rs.getInt("iboard");
                String title = rs.getString("title");
                int writer = rs.getInt("writer");
                int hits = rs.getInt("hits");
                String rdt = rs.getString("rdt");
                String writerNm = rs.getString("writerNm");
                BoardVO vo = BoardVO.builder()
                        .iboard(iboard)
                        .title(title)
                        .writer(writer)
                        .hits(hits)
                        .rdt(rdt)
                        .writerNm(writerNm)
                        .build();
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static BoardVO selBoardDetail (BoardDTO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT A.title, A.ctnt, A.hits, A.writer, A.rdt, B.nm AS writerNm " +
                    " FROM t_board A " +
                    " INNER JOIN t_user B " +
                    " ON A.writer = B.iuser " +
                    " WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            rs = ps.executeQuery();

            if (rs.next()) {
                BoardVO vo = BoardVO.builder()
                        .iboard(param.getIboard())
                        .title(rs.getString("title"))
                        .ctnt(rs.getString("ctnt"))
                        .hits(rs.getInt("hits"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                return vo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }

    public static int selPrevBoard(BoardDTO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iboard FROM t_board " +
                    " WHERE iboard > ? " +
                    " ORDER BY iboard ";

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

    public static int selNextBoard(BoardDTO param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iboard FROM t_board " +
                " WHERE iboard < ? " +
                " ORDER BY iboard DESC ";

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

    public static void updBoardHitUp (BoardDTO param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_board SET hits = hits + 1 WHERE iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
    }

    public static int updBoard(BoardEntity entity) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_board" +
                    " SET title = ? " +
                    " , ctnt = ? " +
                    " , mdt = NOW() " +
                    " WHERE iboard = ? " +
                    " AND writer = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setString(2, entity.getCtnt());
            ps.setInt(3, entity.getIboard());
            ps.setInt(4, entity.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delBoard(BoardEntity entity) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board " +
                    " WHERE iboard = ? " +
                    " AND writer = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIboard());
            ps.setInt(2, entity.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
