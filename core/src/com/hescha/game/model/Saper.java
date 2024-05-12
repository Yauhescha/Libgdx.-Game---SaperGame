package com.hescha.game.model;

import lombok.Data;

@Data
public class Saper {
    private int linesX;
    private int linesY;
    private int mines;
    private int flagsLeft;
    private SaperCell[][] cells;

    public SaperCell findSelectedCell() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                SaperCell saperCell = cells[i][j];
                if (saperCell.isSelected()) {
                    return saperCell;
                }
            }
        }
        return null;
    }

    public void plusFlag() {
        flagsLeft++;
    }

    public void minusFlag() {
        flagsLeft--;
    }
}
