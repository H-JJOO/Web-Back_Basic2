package com.koreait.basic2.board;

import com.koreait.basic2.Utils;
import com.koreait.basic2.board.model.BoardEntity;
import com.koreait.basic2.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        if (Utils.getLoginUser(req) == null) {
            req.setAttribute("err", "올바른 방법으로 접근하세요!");
            req.getRequestDispatcher("/user/login").forward(req, res);
            return;
        }

        int iboard = Utils.getParameterInt(req, "iboard");
        int writer = Utils.getLoginUserPk(req);

        BoardEntity param = new BoardEntity();

        param.setIboard(iboard);
        param.setWriter(writer);

        int result = BoardDAO.delBoard(param);

        switch (result) {
            case 1:
                res.sendRedirect("/board/list");
                return;
            default:
                req.setAttribute("err", "삭제를 실패하였습니다.");
                req.getRequestDispatcher("/board/detail?iboard=" + iboard).forward(req, res);
                return;
        }
    }
}
