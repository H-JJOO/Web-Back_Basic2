package com.koreait.basic2.board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardEntity {
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hits;
    private String rdt;
    private String mdt;

}
