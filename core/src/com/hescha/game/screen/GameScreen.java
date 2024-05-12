package com.hescha.game.screen;

import static com.hescha.game.util.LoadedTextures.WORLD_HEIGHT;
import static com.hescha.game.util.LoadedTextures.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hescha.game.model.Saper;
import com.hescha.game.model.SaperCell;
import com.hescha.game.service.SaperService;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends ScreenAdapter {
    private Stage stage;

    SpriteBatch batch;
    Saper game;

    @Override
    public void show() {
        stage = new Stage(new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();


        game = SaperService.createGame(8, 10, 5);

        Group group = new Group();
        SaperCell[][] cells = game.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                SaperCell saperCell = cells[i][j];
                group.addActor(saperCell);
            }
        }

        stage.addActor(group);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
//        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }
}
