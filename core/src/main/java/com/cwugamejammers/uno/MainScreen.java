package com.cwugamejammers.uno;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainScreen implements Screen {
    private Uno game;

    //Textures for the title screen
    //////////////////////////////////////////////////////////////
    //private Texture unoTitle; //--
    private Texture menuStart;
    private Texture menuHowTo;
    private Texture menuQuit;
    private Texture menuSettings;
    private Texture menuCredits;
    private Texture rules;
    private Texture backBanner;
    private Texture redBackground;
    private Texture blueBackground;


    private Button startButton;
    private Button howToButton;
    private Button quitButton;
    private Button settingsButton;
    private Button creditsButton;
    private Button backButton;

    private Music song;

    public MainScreen (Uno game){
        this.game = game;

        //unoTitle = new Texture("UnoTitle.png"); //--
        menuStart = new Texture("StartGameButton.png");
        menuHowTo = new Texture("HowToPlayButton.png");
        menuQuit = new Texture("QuitButton.png");
        menuSettings = new Texture("SettingsButton.png");
        menuCredits = new Texture("CreditsButton.png");
        rules = new Texture("RulesPlaceHolder.png");
        backBanner = new Texture("BackBanner.png");
        blueBackground = new Texture("BlueBackground.png");
        redBackground = new Texture("TitleScreenTiltGlow.png");


        startButton = new Button(menuStart, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()*6/10, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/9 );
        howToButton = new Button(menuHowTo,Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()*4.5f/10, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/9);
        quitButton = new Button(menuQuit, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()*3/10, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/9);
        settingsButton = new Button(menuSettings,0, 0, 300, 300);
        creditsButton = new Button(menuCredits, Gdx.graphics.getWidth() - 200, 0, 200, 200);
        backButton = new Button(0, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/10);

        song = Gdx.audio.newMusic(Gdx.files.internal("music/Chill.mp3"));
        song.play();
        song.setLooping(true);

    }


    @Override
    public void show() {


    }

    public void update(float dt) {
        if (Gdx.input.justTouched()){
            if(startButton.collision(Gdx.input.getX(),Gdx.input.getY())){
                song.stop();
                game.setScreen(new PlayScreen(game));
            }

            if(howToButton.collision(Gdx.input.getX(),Gdx.input.getY())){
                song.stop();
                game.setScreen(new HowToScreen(game));
            }

            if(quitButton.collision(Gdx.input.getX(),Gdx.input.getY())){
                song.stop();
                Gdx.app.exit();
                System.exit(0);
            }

            if(settingsButton.collision(Gdx.input.getX(),Gdx.input.getY())){
                song.stop();
                game.setScreen(new SettingScreen(game));
            }

            if(creditsButton.collision(Gdx.input.getX(),Gdx.input.getY())){
                song.stop();
                game.setScreen(new CreditScreen(game));
            }

        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        //Sets a color for the screen to be cleared with
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        //Replaces everything from the previous frame with a solid color specified in the previous line
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Begins the spritebatch
        game.batch.begin();


        if (game.colortheme == Uno.COLORTHEME.RED){
            game.batch.draw(redBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if (game.colortheme == Uno.COLORTHEME.BLUE){
            game.batch.draw(blueBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }


        //game.batch.draw(unoTitle, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //--
        startButton.draw(game.batch);
        howToButton.draw(game.batch);
        quitButton.draw(game.batch);
        //settingsButton.draw(game.batch);
        creditsButton.draw(game.batch);

        game.batch.end();
    }


    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        //unoTitle.dispose(); //--
        menuStart.dispose();
        menuHowTo.dispose();
        menuQuit.dispose();
        menuSettings.dispose();
        menuCredits.dispose();
        backBanner.dispose();
        redBackground.dispose();
        blueBackground.dispose();
        startButton.dispose();
        howToButton.dispose();
        quitButton.dispose();
        settingsButton.dispose();
        creditsButton.dispose();
        song.dispose();
    }
}
