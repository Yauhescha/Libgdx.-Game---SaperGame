package com.hescha.game.service;

import com.hescha.game.model.Saper;
import com.hescha.game.model.SaperCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SaperService {

    public static Saper createGame(int linesX, int linesY, int mines) {
        SaperCell[][] cells = new SaperCell[linesX][linesY];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new SaperCell();
            }
        }

        shuffleCells(cells, mines);

        Saper saper = new Saper();
        saper.setLinesX(linesX);
        saper.setLinesY(linesY);
        saper.setMines(mines);
        saper.setFlagsLeft(mines);
        saper.setCells(cells);
        return saper;
    }

    private static void shuffleCells(SaperCell[][] cells, int mines) {
        int linesX = cells.length;
        int linesY = cells[0].length;
        List<Integer> positions = new ArrayList<>(linesX * linesY);
        for (int i = 0; i < linesX * linesY; i++) {
            positions.add(i);
        }
        Collections.shuffle(positions); // Перемешиваем позиции

        for (int i = 0; i < mines; i++) {
            int pos = positions.get(i);
            int x = pos / linesY;
            int y = pos % linesY;
            cells[x][y].setValue(0); // Устанавливаем мины (0 - мина)
        }

        // Устанавливаем числа в ячейках
        for (int x = 0; x < linesX; x++) {
            for (int y = 0; y < linesY; y++) {
                if (cells[x][y].getValue() != 0) { // если не мина
                    int minesCount = 0;
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            int nx = x + dx;
                            int ny = y + dy;
                            if (nx >= 0 && nx < linesX && ny >= 0 && ny < linesY) {
                                if (cells[nx][ny].getValue() == 0) {
                                    minesCount++;
                                }
                            }
                        }
                    }
                    if (minesCount != 0) {
                        cells[x][y].setValue(minesCount); // Записываем количество соседних мин}
                    }
                }
            }
        }
    }
}