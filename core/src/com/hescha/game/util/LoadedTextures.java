package com.hescha.game.util;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class LoadedTextures {
    public static final int TILE_SIZE = 128;
    public static final int WORLD_WIDTH = 1024;
    public static final int WORLD_HEIGHT = 1920;

    public static Map<Integer, Texture> textureMap = new HashMap<>();

    public static Texture bomb_exploded;
    public static Texture flat_1;
    public static Texture question_1;
    public static Texture unknown_1;
    public static Texture selected;

    public static void loadTextureMap() {
        textureMap.put(-1, new Texture("game/128x128/empty.png"));
        textureMap.put(0, new Texture("game/128x128/bomb.png"));
        textureMap.put(1, new Texture("game/128x128/1.png"));
        textureMap.put(2, new Texture("game/128x128/2.png"));
        textureMap.put(3, new Texture("game/128x128/3.png"));
        textureMap.put(4, new Texture("game/128x128/4.png"));
        textureMap.put(5, new Texture("game/128x128/5.png"));
        textureMap.put(6, new Texture("game/128x128/6.png"));
        textureMap.put(7, new Texture("game/128x128/7.png"));
        textureMap.put(8, new Texture("game/128x128/8.png"));

        bomb_exploded = new Texture("game/128x128/bomb_exploded.png");
        flat_1 = new Texture("game/128x128/flat_1.png");
        question_1 = new Texture("game/128x128/question_1.png");
        unknown_1 = new Texture("game/128x128/unknown_1.png");
        selected = new Texture("game/128x128/selected.png");
    }
}
