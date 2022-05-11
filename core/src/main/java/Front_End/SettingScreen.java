package Front_End;

import Front_End.Button;
import Front_End.MainScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.cwugamejammers.uno.Uno;

public class SettingScreen implements Screen {
    Uno game;

    private Texture redBackground;
    private Texture backBanner;
    private Button backButton;


    public SettingScreen(Uno game){
        this.game = game;

        redBackground = new Texture("RedBackground.png");
        backBanner = new Texture("BackBanner.png");
        backButton = new Button(0, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/10);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    public void update(float dt){

        if (Gdx.input.justTouched()){
            if (backButton.collision(Gdx.input.getX(), Gdx.input.getY())){
                game.setScreen(new MainScreen(game));
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

        game.batch.draw(redBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(backBanner,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


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
        redBackground.dispose();
        backBanner.dispose();
    }
}
