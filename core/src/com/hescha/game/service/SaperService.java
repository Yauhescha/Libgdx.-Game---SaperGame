package com.hescha.game.service;

import com.hescha.game.model.Saper;
import com.hescha.game.model.SaperCell;
import com.hescha.game.util.LoadedTextures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SaperService {

    public static Saper createGame(int linesX, int linesY, int mines) {
        Saper saper = new Saper();
        saper.setLinesX(linesX);
        saper.setLinesY(linesY);
        saper.setMines(mines);
        saper.setFlagsLeft(mines);

        SaperCell[][] cells = new SaperCell[linesX][linesY];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new SaperCell();
                cells[i][j].setX(i * LoadedTextures.TILE_SIZE);
                cells[i][j].setY(j * LoadedTextures.TILE_SIZE);
                cells[i][j].setGame(saper);
            }
        }

        shuffleCells(cells, mines);

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

    public static void flagSelectedCell(Saper game) {
        SaperCell saperCell = game.findSelectedCell();
        if (saperCell != null && !saperCell.isOpened()) {
            if (saperCell.isFlagged()) {
                saperCell.setFlagged(false);
                game.plusFlag();
            } else if (game.getFlagsLeft() > 0) {
                saperCell.setFlagged(true);
                game.minusFlag();
            }
            saperCell.setQuestion(false);
        }
    }

    public static void questSelectedCell(Saper game) {
        SaperCell saperCell = game.findSelectedCell();
        if (saperCell != null && !saperCell.isOpened()) {
            if (saperCell.isFlagged()) {
                saperCell.setFlagged(false);
                game.plusFlag();
            }
            if (saperCell.isQuestion()) {
                saperCell.setQuestion(false);
            } else {
                saperCell.setQuestion(true);
            }

        }
    }

    public static void openSelectedCell(Saper game) {
        SaperCell saperCell = game.findSelectedCell();
        if (saperCell != null && !saperCell.isOpened()) {
            if (saperCell.isFlagged()) {
                saperCell.setFlagged(false);
                game.plusFlag();
            }
            saperCell.setQuestion(false);
            saperCell.setOpened(true);
        }
    }
}