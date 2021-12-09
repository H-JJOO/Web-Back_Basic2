package com.koreait.basic2.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int iboard;
    private int page;
    private int startIdx;
    private int rowCnt;
}
