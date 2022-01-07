package com.balloonbuster.game;

import com.badlogic.gdx.Game;

public class BalloonBuster extends Game {
	@Override
	public void create () {
		setScreen(new BalloonMenu(this));
	}
}
