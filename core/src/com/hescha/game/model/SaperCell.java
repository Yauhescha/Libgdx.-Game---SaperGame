package com.hescha.game.model;

import static com.hescha.game.util.LoadedTextures.TILE_SIZE;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.hescha.game.screen.GameScreen;
import com.hescha.game.util.LoadedTextures;

import lombok.Data;

@Data
public class SaperCell extends Actor {
    // '-1' is empty, '0' is bomb
    private int value = -1;
    private boolean isSelected;
    private boolean isOpened;
    private boolean isFlagged;
    private boolean isQuestion;

    private Saper game;

    public SaperCell() {
        setBounds(getX(), getY(), TILE_SIZE, TILE_SIZE);

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isSelected) {
                    isSelected = false;
                } else {
                    //disable all other selections
                    SaperCell[][] cells = game.getCells();
                    for (int i = 0; i < cells.length; i++) {
                        for (int j = 0; j < cells[i].length; j++) {
                            cells[i][j].setSelected(false);
                        }
                    }
                    isSelected = true;
                }
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (isQuestion) {
            batch.draw(LoadedTextures.question_1, getX(), getY());
        } else if (isFlagged) {
            batch.draw(LoadedTextures.flat_1, getX(), getY());
        } else if (isOpened) {
            batch.draw(LoadedTextures.textureMap.get(value), getX(), getY());
        } else {
            batch.draw(LoadedTextures.unknown_1, getX(), getY());
        }

        if (isSelected) {
            batch.draw(LoadedTextures.selected, getX(), getY());
        }
    }
}
