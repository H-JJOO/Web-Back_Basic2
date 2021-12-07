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

@WebServlet("/board/regmod")
public class BoardRegModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = "글등록";
        Utils.displayView(title, "/board/regmod", req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = Utils.getLoginUserPk(req);
        if (loginUserPk == 0) {
            res.sendRedirect("/user/login");
            return;
        }

        int iboard = Utils.getParameterInt(req, "iboard");
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        int result = 0;
        BoardEntity entity = new BoardEntity();
        entity.setTitle(title);
        entity.setCtnt(ctnt);
        entity.setWriter(loginUserPk);

        if (iboard == 0) {
            result = BoardDAO.insBoardWithPk(entity);
        } else {
            entity.setIboard(iboard);
            result = BoardDAO.updBoard(entity);
        }
        switch (result) {
            case 1:
                if (entity.getIboard() != 0) {
                    res.sendRedirect("/board/detail?ibaord=" + entity.getIboard());
                    return;
                }
                break;
            default:
                req.setAttribute("err", "등록/수정에 실패하였습니다.");
                req.setAttribute("data", entity);
                doGet(req, res);
                break;
        }
        res.sendRedirect("/board/list");
    }
}
