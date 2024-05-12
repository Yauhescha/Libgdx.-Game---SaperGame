package com.hescha.game;

import com.badlogic.gdx.Game;
import com.hescha.game.screen.GameScreen;
import com.hescha.game.util.LoadedTextures;

public class SaperGame extends Game {

	@Override
	public void create() {
		LoadedTextures.loadTextureMap();
		setScreen(new GameScreen());
	}
}
