package com.hescha.game.model;

import lombok.Data;

@Data
public class Saper {
    private int linesX;
    private int linesY;
    private int mines;
    private int flagsLeft;
    private SaperCell[][] cells;
}
