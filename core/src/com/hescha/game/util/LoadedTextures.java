package com.hescha.game.util;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class LoadedTextures {
    public static final int TILE_SIZE = 128;
    public static Map<Integer, Texture> textureMap = new HashMap<>();

    public static void loadTextureMap() {
        textureMap.put(-1, new Texture("game/128x128/empty_128x128.png"));
        textureMap.put(0, new Texture("game/128x128/bomb_128x128.png"));
        textureMap.put(1, new Texture("game/128x128/1_128x128.png"));
        textureMap.put(2, new Texture("game/128x128/2_128x128.png"));
        textureMap.put(3, new Texture("game/128x128/3_128x128.png"));
        textureMap.put(4, new Texture("game/128x128/4_128x128.png"));
        textureMap.put(5, new Texture("game/128x128/5_128x128.png"));
        textureMap.put(6, new Texture("game/128x128/6_128x128.png"));
        textureMap.put(7, new Texture("game/128x128/7_128x128.png"));
        textureMap.put(8, new Texture("game/128x128/8_128x128.png"));
    }
}
