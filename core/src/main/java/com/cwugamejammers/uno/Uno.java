package com.cwugamejammers.uno;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

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