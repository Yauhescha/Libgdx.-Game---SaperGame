package com.hescha.game.screen;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hescha.game.model.Saper;
import com.hescha.game.model.SaperCell;
import com.hescha.game.service.SaperService;

import java.util.HashMap;
import java.util.Map;

public class GameScreen extends ScreenAdapter {
    static final int WORLD_WIDTH = 1024;
    static final int WORLD_HEIGHT = 1920;

    private OrthographicCamera camera;
    private Viewport viewport;
    SpriteBatch batch;
    Saper game;
    Map<Integer, Texture> textureMap = new HashMap<>();
    int textureWidth;

    @Override
    public void show() {
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        viewport.apply(true);

        batch = new SpriteBatch();
        Texture texture = new Texture("game/128x128/empty_128x128.png");
        textureMap.put(-1, texture);
        textureMap.put(0, new Texture("game/128x128/bomb_128x128.png"));
        textureMap.put(1, new Texture("game/128x128/1_128x128.png"));
        textureMap.put(2, new Texture("game/128x128/2_128x128.png"));
        textureMap.put(3, new Texture("game/128x128/3_128x128.png"));
        textureMap.put(4, new Texture("game/128x128/4_128x128.png"));
        textureMap.put(5, new Texture("game/128x128/5_128x128.png"));
        textureMap.put(6, new Texture("game/128x128/6_128x128.png"));
        textureMap.put(7, new Texture("game/128x128/7_128x128.png"));
        textureMap.put(8, new Texture("game/128x128/8_128x128.png"));

        textureWidth = texture.getWidth();

        game = SaperService.createGame(8, 10, 5);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        SaperCell[][] cells = game.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                SaperCell saperCell = cells[i][j];
                int value = saperCell.getValue();
                batch.draw(textureMap.get(value), i * textureWidth, j * textureWidth);
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        textureMap.values().forEach(Texture::dispose);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
