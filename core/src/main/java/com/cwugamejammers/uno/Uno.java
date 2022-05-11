package com.cwugamejammers.uno;

import Front_End.MainScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Uno extends Game {

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;


	enum COLORTHEME{
		RED,
		BLUE,
		GREEN,
		YELLOW
	}
	COLORTHEME colortheme;


	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		colortheme = COLORTHEME.RED;

		setScreen(new MainScreen(this));
	}


	@Override
	public void dispose(){
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}

}