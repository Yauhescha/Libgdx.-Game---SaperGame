package com.hescha.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hescha.game.model.Saper;
import com.hescha.game.model.SaperCell;
import com.hescha.game.service.SaperService;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends ScreenAdapter {
    static final int WORLD_WIDTH = 1024;
    static final int WORLD_HEIGHT = 1920;
    private Stage stage;

    private OrthographicCamera camera;
    private Viewport viewport;
    SpriteBatch batch;
    Saper game;
    int textureWidth;

    @Override
    public void show() {
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply(true);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        Texture texture = new Texture("game/128x128/empty_128x128.png");


        textureWidth = texture.getWidth();

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


//
//        ScreenUtils.clear(Color.WHITE);
//
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//        SaperCell[][] cells = game.getCells();
//        for (int i = 0; i < cells.length; i++) {
//            for (int j = 0; j < cells[i].length; j++) {
//                SaperCell saperCell = cells[i][j];
//                int value = saperCell.getValue();
//                batch.draw(textureMap.get(value), i * textureWidth, j * textureWidth);
//            }
//        }
//
//        batch.end();
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
