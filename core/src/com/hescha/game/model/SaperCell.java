package com.hescha.game.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hescha.game.util.LoadedTextures;

import lombok.Data;

@Data
public class SaperCell extends Actor {
    // '-1' is nothing, '0' is bomb
    private int value = -1;
    private boolean isSelected;
    private boolean isOpened;
    private boolean isFlagged;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(LoadedTextures.textureMap.get(value),
                getX(),
                getY());
    }
}
