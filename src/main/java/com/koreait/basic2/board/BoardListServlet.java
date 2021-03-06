package com.koreait.basic2.board;

import com.koreait.basic2.Utils;
import com.koreait.basic2.board.model.BoardDTO;
import com.koreait.basic2.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int page = Utils.getParameterInt(req, "page", 1);

        BoardDTO param = new BoardDTO();
        param.setRowCnt(5);
        param.setPage(page);
        int startIdx = (param.getPage() - 1) * param.getRowCnt();
        param.setStartIdx(startIdx);

        int maxPageNum = BoardDAO.getMaxPageNum(param);

        req.setAttribute("maxPageNum", maxPageNum);
        req.setAttribute("list", BoardDAO.selBoardList(param));

        Utils.displayView("게시판", "/board/list", req, res);
    }
}
