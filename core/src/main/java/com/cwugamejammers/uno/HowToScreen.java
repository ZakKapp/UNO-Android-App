package com.cwugamejammers.uno;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class HowToScreen implements Screen, GestureDetector.GestureListener{
    Uno game;
    private Texture redBackground;
    private Texture blueBackground;
    private Texture backBanner;


    private Texture ruleTexture1;
    private Texture ruleTexture2;
    private Texture ruleTexture3;
    private Texture ruleTexture4;
    private Texture ruleTexture5;
    private Texture ruleTexture6;



    private Button backButton;
    private Button rule1;
    private Button rule2;
    private Button rule3;
    private Button rule4;
    private Button rule5;
    private Button rule6;

    private ArrayList<Button> rulesList;



    public HowToScreen (Uno game){
        this.game = game;

        //Initializes textures
        ///////////////////////////////////////////////////////////////
        redBackground = new Texture("RedBackground.png");
        blueBackground = new Texture("BlueBackground.png");
        backBanner = new Texture("BackBanner.png");
        ruleTexture1 = new Texture("GameRules.png");
        ruleTexture2 = new Texture("ActionCards.png");
        ruleTexture3 = new Texture("3.png");
        ruleTexture4 = new Texture("1.png");
        ruleTexture5 = new Texture("2.png");
        ruleTexture6 = new Texture("3.png");


        backButton = new Button(0, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/10);

        rule1 = new Button(ruleTexture1, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        rule2 = new Button(ruleTexture2, 0, -Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        rule3 = new Button(ruleTexture3, 0, -Gdx.graphics.getHeight()*2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        rule4 = new Button(ruleTexture4, 0, -Gdx.graphics.getHeight()*3, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        rule5 = new Button(ruleTexture5, 0, -Gdx.graphics.getHeight()*4, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        rule6 = new Button(ruleTexture6, 0, -Gdx.graphics.getHeight()*5, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //creates arraylist for rules and adds them to list
        rulesList = new ArrayList<Button>();
        rulesList.add(rule1);
        rulesList.add(rule2);

        //Sets the bounds of rules so they dont go offscreen
        rule1.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()*(rulesList.size() - 1));
    }


    @Override
    public void show() {
        // Prepare your screen here.
    }

    public void update(float dt){

        //Checks if the backButton is touched, if so go back to the main screen
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

        //Calls the pan function to move the rules around
        pan(Gdx.input.getX(), Gdx.input.getY(), Gdx.input.getDeltaX(), Gdx.input.getDeltaY());

        //Begins the spritebatch
        game.batch.begin();

        //sets the background color based on the theme chosen
        if (game.colortheme == Uno.COLORTHEME.RED){
            game.batch.draw(redBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        if (game.colortheme == Uno.COLORTHEME.BLUE){
            game.batch.draw(blueBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        //Draws all the rules images to the screen
        for (Button b : rulesList){
            b.draw(game.batch);
        }

        //draws the back banner to the screen
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
        blueBackground.dispose();
        backBanner.dispose();
        ruleTexture1.dispose();
        ruleTexture2.dispose();
        ruleTexture3.dispose();
        ruleTexture4.dispose();
        ruleTexture5.dispose();
        ruleTexture6.dispose();
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button){
        return true;
    }


    @Override
    public boolean tap(float x, float y, int count, int button){
        return true;
    }

    @Override
    public boolean longPress(float x, float y){
        return true;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button){
        return true;
    }


    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY){

        for (int i = 0; i < rulesList.size(); i++){

            //Checks the valid positions for the images based on the first image, if going out of bounds
            //breaks out of the for loop so positions do not update
            if (i == 0){
                if(rulesList.get(i).rect.y - deltaY > rulesList.get(i).bounds.maxY){
                    break;
                }

                if(rulesList.get(i).rect.y - deltaY < rulesList.get(i).bounds.minY){
                    break;
                }
            }

            //Moves the rule images all together
            rulesList.get(i).reposition(rulesList.get(i).rect.x, rulesList.get(i).rect.y - deltaY);
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int a, int b){

        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance){
        return true;
    }


    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2){
        return true;
    }

    @Override
    public void pinchStop(){

    }

}
