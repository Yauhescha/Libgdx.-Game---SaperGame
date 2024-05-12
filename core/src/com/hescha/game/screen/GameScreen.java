package com.hescha.game.screen;

import static com.hescha.game.util.LoadedTextures.TILE_SIZE;
import static com.hescha.game.util.LoadedTextures.WORLD_HEIGHT;
import static com.hescha.game.util.LoadedTextures.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.hescha.game.model.Saper;
import com.hescha.game.model.SaperCell;
import com.hescha.game.service.SaperService;

public class GameScreen extends ScreenAdapter {
    private Stage stage;
    Table table;
    SpriteBatch batch;
    Saper game;
    Skin skin;

    @Override
    public void show() {
        stage = new Stage(new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("style/style.json"));

        batch = new SpriteBatch();


        int linesX = 7;
        int linesY = 10;
        int mines = 5;
        game = SaperService.createGame(linesX, linesY, mines);

        Group sellsGroup = new Group();
        sellsGroup.setBounds(0, TILE_SIZE * 2, linesY - TILE_SIZE, linesX * TILE_SIZE);
        SaperCell[][] cells = game.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                SaperCell saperCell = cells[i][j];
                sellsGroup.addActor(saperCell);
            }
        }
        if(sellsGroup.getWidth()<WORLD_WIDTH){
            sellsGroup.setX((WORLD_WIDTH-TILE_SIZE*linesX)/2);
        }
        stage.addActor(sellsGroup);


        Group buttonGroup = new Group();
        buttonGroup.setWidth(WORLD_WIDTH);
        buttonGroup.setHeight(TILE_SIZE * 2);

        TextButton flagButton = new TextButton("Flag", skin);
        flagButton.setBounds(0, 0, WORLD_WIDTH / 3, TILE_SIZE * 2);

        TextButton questButton = new TextButton("Quest", skin);
        questButton.setBounds(WORLD_WIDTH / 3, 0, WORLD_WIDTH / 3, TILE_SIZE * 2);

        TextButton openButton = new TextButton("Open", skin);
        openButton.setBounds((WORLD_WIDTH / 3) * 2, 0, WORLD_WIDTH / 3, TILE_SIZE * 2);

        buttonGroup.addActor(flagButton);
        buttonGroup.addActor(questButton);
        buttonGroup.addActor(openButton);
        stage.addActor(buttonGroup);
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
        stage.getViewport().update(width, height, true);
    }
}
