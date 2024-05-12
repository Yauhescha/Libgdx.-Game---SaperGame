package com.hescha.game;

import com.badlogic.gdx.Game;
import com.hescha.game.screen.GameScreen;

public class SaperGame extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
