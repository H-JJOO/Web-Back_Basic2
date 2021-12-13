package com.koreait.basic2.board;

import com.koreait.basic2.Utils;
import com.koreait.basic2.board.model.BoardDTO;
import com.koreait.basic2.board.model.BoardEntity;
import com.koreait.basic2.board.model.BoardVO;
import com.koreait.basic2.dao.BoardDAO;

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
        int nohits = Utils.getParameterInt(req, "nohits");
        int iboard = Utils.getParameterInt(req, "iboard");

        BoardDTO param = new BoardDTO();
        param.setIboard(iboard);

        int prevBoard = BoardDAO.selPrevBoard(param);
        int nextBoard = BoardDAO.selNextBoard(param);

        BoardVO data = BoardDAO.selBoardDetail(param);

        int loginUserPk = Utils.getLoginUserPk(req);
        if (data.getWriter() != loginUserPk && nohits != 1) {//
            BoardDAO.updBoardHitUp(param);
        }

        req.setAttribute("data", data);
        req.setAttribute("prevBoard", prevBoard);
        req.setAttribute("nextBoard", nextBoard);

        Utils.displayView(data.getTitle(), "/board/detail", req, res);

    }

}
