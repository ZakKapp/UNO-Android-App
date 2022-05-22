package com.cwugamejammers.uno;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.swing.DefaultListSelectionModel;

public class PlayScreen implements Screen, GestureDetector.GestureListener, InputProcessor {
    Uno game;

    static CardAssetManager assMan;

    private Texture redBackground;
    private Texture blueBackground;
    private Texture deckTex;
    private Texture t;

    private static float cardWidth = Gdx.graphics.getWidth()/4;
    private static float cardHeight = Gdx.graphics.getHeight()/4;
    private Button deckButton;
    static Button pileButton;


    private Texture rBlank;
    private Texture bBlank;
    private Texture gBlank;
    private Texture yBlank;
    private Texture uButt;

    private static Button wildRed;
    private  static Button wildBlue;
    private static Button wildGreen;
    private static Button wildYellow;
    private static Button UnoButton;

    PlayerInfo p0Info;
    PlayerInfo p1Info;
    PlayerInfo p2Info;
    PlayerInfo p3Info;

    Player currentPlayer;

    //Button addCard;
    //Button removeCard;

    //Button card1;
    static ArrayList<Button> cardList;

    Music song1;
    Music song2;
    Music song3;
    Music song4;
    Music song5;
    ArrayList<Music> musicList;

    static Button cardButton;

    private static ArrayList<ArrayList<Texture>> textureList;

    GameController controller;

    GameData.GameState GS;
    GameData.Turn turn;

    private static boolean isPlayed = false;
    private static boolean cardSelected = false;
    private static boolean cardJustSelected = false;
    private static boolean newTurn = true;














    public PlayScreen(Uno game){
        this.game = game;
        assMan = new CardAssetManager();
        assMan.loadImages();
        assMan.finishLoading();

        InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(this);
        im.addProcessor(gd);
        im.addProcessor(this);
        Gdx.input.setInputProcessor(im);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);

        redBackground = new Texture("RedBackground.png");
        blueBackground = new Texture("BlueBackground.png");
        deckTex = assMan.manager.get("cards/CardBack.jpeg");

        //PILEBUTTON TEXTURE WILL BE REPLACED WITH LAST PLAYED CARD
        deckButton = new Button(deckTex, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2, cardWidth, cardHeight);
        pileButton = new Button(deckTex, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, cardWidth, cardHeight);

        bBlank = new Texture("cards/BBlank.jpeg");
        rBlank = new Texture("cards/RBlank.jpeg");
        gBlank = new Texture("cards/GBlank.jpeg");
        yBlank = new Texture("cards/YBlank.jpeg");
        uButt = new Texture("UNOButton.png");

        wildBlue = new Button(bBlank, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2 + 300, cardWidth, cardHeight);
        wildRed = new Button(rBlank, Gdx.graphics.getWidth()/4 + cardWidth, Gdx.graphics.getHeight()/2 + 300, cardWidth, cardHeight);
        wildGreen = new Button(gBlank, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/2 + 300 - cardHeight, cardWidth, cardHeight);
        wildYellow = new Button(yBlank, Gdx.graphics.getWidth()/4 + cardWidth, Gdx.graphics.getHeight()/2 + 300 - cardHeight, cardWidth, cardHeight);
        UnoButton = new Button(uButt, Gdx.graphics.getWidth()*4/5 - 100, Gdx.graphics.getHeight()/4 + 30, Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/5);

        //ALTERNATE DECK PLACEMENTS
        //deckButton = new Button(deckTex, Gdx.graphics.getWidth()/4 - cardWidth/2, Gdx.graphics.getHeight()/2, cardWidth, cardHeight);
        //pileButton = new Button(deckTex, Gdx.graphics.getWidth()*3/4 - cardWidth/2 , Gdx.graphics.getHeight()/2, cardWidth, cardHeight);



        song1 = Gdx.audio.newMusic(Gdx.files.internal("music/AndSoItBegins.mp3"));
        song2 = Gdx.audio.newMusic(Gdx.files.internal("music/Branch.mp3"));
        song3 = Gdx.audio.newMusic(Gdx.files.internal("music/Octilary.mp3"));
        song4 = Gdx.audio.newMusic(Gdx.files.internal("music/Onion.mp3"));
        song5 = Gdx.audio.newMusic(Gdx.files.internal("music/Portrait.mp3"));

        musicList = new ArrayList<Music>();
        musicList.add(song1);
        musicList.add(song2);
        musicList.add(song3);
        musicList.add(song4);
        musicList.add(song5);
        song1.setOnCompletionListener(a -> {
            song2.play();
        });
        song2.setOnCompletionListener(a -> {
            song3.play();
        });
        song3.setOnCompletionListener(a -> {
            song4.play();
        });
        song4.setOnCompletionListener(a -> {
            song5.play();
        });
        song5.setOnCompletionListener(a -> {
            song1.play();
        });

        song1.play();

        cardList = new ArrayList<Button>();
        controller = new GameController(this);
        controller.initialize();

        GS = controller.getData().getState();
        turn = controller.getData().getTurn();
        currentPlayer = controller.getCurrentPlayer();


        //Initializes the AI players text info at the top of the screen
        p0Info = new PlayerInfo(controller.getP1().getName(), controller.getP1().getHandSize(), 10, Gdx.graphics.getHeight()- 400, Gdx.graphics.getWidth()/3, 300);
        p1Info = new PlayerInfo(controller.getP1().getName(), controller.getP1().getHandSize(), 10, Gdx.graphics.getHeight()- 10, Gdx.graphics.getWidth()/3, 300);
        p2Info = new PlayerInfo(controller.getP2().getName(), controller.getP2().getHandSize(), Gdx.graphics.getWidth()/3 + 10, Gdx.graphics.getHeight() - 10, Gdx.graphics.getWidth()/3, 300);
        p3Info = new PlayerInfo(controller.getP3().getName(), controller.getP3().getHandSize(), Gdx.graphics.getWidth()*2/3 + 10, Gdx.graphics.getHeight() - 10, Gdx.graphics.getWidth()/3, 300);
    }





    @Override
    public void show() {
        // Prepare your screen here.
    }

    public void update(float dt){   //called every frame
        //Sets hand bounds based on the size of the hand
        repositionHand();
        //Updates the text at the top of the screen with current card counts
        updatePlayerInfo();
        // variable for current player
        currentPlayer = controller.getCurrentPlayer();
        GS = controller.getData().getState();

        if (GS == GameData.GameState.MIDDLE) {

            if (turn != controller.getData().getTurn()) {
                newTurn = true;
                turn = controller.getData().getTurn();
            }

            if (newTurn && currentPlayer.getIsAI()) {    //if new turn and if AI
                controller.run();   //run AI move
                newTurn = false;
            }

            if (currentPlayer.getId() == 0 && newTurn) {    //if current player is human and is new turn
                if (!controller.playableCards())            //if the player does not have playable cards
                {
                    currentPlayer.draw();                   //draws card if no playable cards
                    controller.getData().setTracker();      //set next turn
                    newTurn = true;
                }
            }
        }


        
        //touchDown(Gdx.input.getX(),Gdx.input.getY(), 0, 0);


        //Calls the pan function to drag the cards around
        //pan(Gdx.input.getX(), Gdx.input.getY(), Gdx.input.getDeltaX(), Gdx.input.getDeltaY());

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

        if (GS == GameData.GameState.MIDDLE) {
            //depending on the theme draw a specific color background
            if (game.colortheme == Uno.COLORTHEME.RED) {
                game.batch.draw(redBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }

            if (game.colortheme == Uno.COLORTHEME.BLUE) {
                game.batch.draw(blueBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            }

            //Draws the cards
            for (Button b : cardList) {
                b.draw(game.batch);
            }
            //Draws the back of the deck and the pile to the screen
            deckButton.draw(game.batch);
            pileButton.draw(game.batch);

            if (cardSelected) {
                wildBlue.draw(game.batch);
                wildGreen.draw(game.batch);
                wildRed.draw(game.batch);
                wildYellow.draw(game.batch);
            }

            //Draws the Uno Button
            UnoButton.draw(game.batch);

            //Draws the text of the AIs player info at the top of the screen
            //p0Info.draw(game.batch, game.font);
            p1Info.draw(game.batch, game.font);
            p2Info.draw(game.batch, game.font);
            p3Info.draw(game.batch, game.font);


            if (currentPlayer.getId() == 0) {
                String info = "Your Turn!";
                game.font.draw(game.batch, info, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 4 + 60);
            } else {
                String info = currentPlayer.getName() + "'s Turn";
                game.font.draw(game.batch, info, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 4 + 60);
            }
        }

        if (GS == GameData.GameState.PLAYER0){
            game.batch.draw(redBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.font.draw(game.batch, "YOU WON!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        }
        if (GS == GameData.GameState.PLAYER1){
            game.batch.draw(redBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.font.draw(game.batch, "YOU LOST!" + "\n" + "AI1 Won", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        }
        if (GS == GameData.GameState.PLAYER2){
            game.batch.draw(redBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.font.draw(game.batch, "YOU LOST!" + "\n" + "AI2 Won", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        }
        if (GS == GameData.GameState.PLAYER3){
            game.batch.draw(redBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            game.font.draw(game.batch, "YOU LOST!" + "\n" + "AI3 Won", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        }

        //winner texts



        //Flushes the batch and draws everything to the screen
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


    //Frees up memory from assets no longer used
    @Override
    public void dispose() {
        redBackground.dispose();
        blueBackground.dispose();
        cardButton.dispose();
        song1.dispose();
        song2.dispose();
        song3.dispose();
        song4.dispose();
        song5.dispose();
        deckTex.dispose();
        t.dispose();
        assMan.dispose();
        bBlank.dispose();
        rBlank.dispose();
        gBlank.dispose();
        yBlank.dispose();

    }

    public void repositionHand(){
        if (cardList.size() == 0){

        }
        else if (cardList.size() < 2 && cardList.size() != 0){
            cardList.get(0).setBounds(0, 0, Gdx.graphics.getWidth() - cardList.get(0).rect.width, Gdx.graphics.getHeight());
        }
        else{
            cardList.get(0).setBounds(-cardList.get(0).rect.width*(cardList.size() - 1), 0, Gdx.graphics.getWidth() - cardList.get(0).rect.width, Gdx.graphics.getHeight());
            for (int i = 1; i < cardList.size(); i++){
                cardList.get(i).reposition(cardList.get(i-1).getx() + cardList.get(i).rect.width, cardList.get(i).rect.y);
            }
        }
    }


    public static void setPlayPileTex(String s){
        pileButton.setTexture(assMan.manager.get(s));
    }

    public static void setPlayPileTex(Texture t){
        pileButton.setTexture(t);
    }

    public void updatePlayerInfo(){
        p0Info.updateCard(controller.getP0().getHandSize());
        p1Info.updateCard(controller.getP1().getHandSize());
        p2Info.updateCard(controller.getP2().getHandSize());
        p3Info.updateCard(controller.getP3().getHandSize());

    }

    public void setAiWildColor(String s){
        if (s == "Red"){
            pileButton.setTexture(rBlank);
        }

        if (s == "Blue"){
            pileButton.setTexture(bBlank);
        }

        if (s == "Green"){
            pileButton.setTexture(gBlank);
        }
        if (s == "Yellow"){
            pileButton.setTexture(yBlank);
        }

    }


    public void PlayCard(){
        Button playedCard = null;
            for (Button b : cardList) {
                if (b.collision(Gdx.input.getX(), Gdx.input.getY()) && controller.getData().getTurn() == GameData.Turn.PLAYER0) {
                    if (controller.isValidCard(cardList.indexOf(b))) {
                        if (controller.getP0().getHand().get(cardList.indexOf(b)).getNumber() >= 13)
                        {
                            cardSelected = true;
                            if(controller.getP0().getHand().get(cardList.indexOf(b)).getNumber() == 14)
                            {
                                if(controller.getReversed())
                                {
                                    controller.getP3().drawFour();
                                    controller.getP3().setSkipped(true);
                                }
                                else
                                {
                                    controller.getP1().drawFour();
                                    controller.getP1().setSkipped(true);
                                }
                            }
                        }


                        pileButton.setTexture(b.getTexture());

                        playedCard = b;

                        //When a card is selected to be played, we move the card in the hand as well
                        int index = cardList.indexOf(b);
                        controller.getP0().play(index);
                        if(controller.getP0().getHandSize() == 0) controller.getData().setWinner();
                        controller.checkPlay(currentPlayer);
                        if (!cardSelected) {
                            newTurn = true;
                            controller.getData().setTracker();
                            controller.run();
                        }
                        //setIsPlayed(true);
                    }
                }
            }
            if (playedCard != null) {
                cardList.remove(playedCard);
                if (cardList.size() > 0) {
                    cardList.get(0).reposition(0, 0);
                }
            }

    }

    public void wildCardPick(){
        if (wildBlue.collision(Gdx.input.getX(), Gdx.input.getY())){
            cardSelected = false;
            cardJustSelected = true;
            Card.getPlayField().get(Card.getPlayField().size() - 1).setColor("Blue");
            setPlayPileTex(wildBlue.getTexture());
            newTurn = true;
            controller.getData().setTracker();
            controller.run();
        }

        else if (wildRed.collision(Gdx.input.getX(), Gdx.input.getY())){
            cardSelected = false;
            cardJustSelected = true;
            Card.getPlayField().get(Card.getPlayField().size() - 1).setColor("Red");
            setPlayPileTex(wildRed.getTexture());
            newTurn = true;
            controller.getData().setTracker();
            controller.run();
        }

        else if (wildGreen.collision(Gdx.input.getX(), Gdx.input.getY())){
            cardSelected = false;
            cardJustSelected = true;
            Card.getPlayField().get(Card.getPlayField().size() - 1).setColor("Green");
            setPlayPileTex(wildGreen.getTexture());
            newTurn = true;
            controller.getData().setTracker();
            controller.run();
        }

        else if  (wildYellow.collision(Gdx.input.getX(), Gdx.input.getY())){
            cardSelected = false;
            cardJustSelected = true;
            Card.getPlayField().get(Card.getPlayField().size() - 1).setColor("Yellow");
            setPlayPileTex(wildYellow.getTexture());
            newTurn = true;
            controller.getData().setTracker();
            controller.run();
        }

    }


    public void setWildCard(String s)
    {
        pileButton.setTexture(assMan.manager.get(s));
    }
    //CREATE A FUNCTION THAT TAKES A PLAYER AS INPUT, AND THEN DISPLAYS A WIN/LOSS MESSAGE DEPENDING ON THE PLAYER,
    //NO RETURN NEEDED


    public ArrayList<ArrayList<Texture>> getTextureList()
    {
        return textureList;
    }

    public static Texture createTexture(String color, int number)
    {
        String fileName = "cards/";
        if(color == "Red")
        {
            fileName += "R";
        }

        if(color == "Blue")
        {
            fileName += "B";
        }

        if(color == "Yellow")
        {
            fileName += "Y";
        }

        if(color == "Green")
        {
            fileName += "G";
        }

        if(color == "Wild")
        {
            fileName += "W";
        }



        fileName += Integer.toString(number) + ".jpeg";
        //Texture t = new Texture(fileName);
        Texture t = assMan.manager.get(fileName);
        //cardButton = new Button(t, 0, 0, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        //cardList.add(cardButton);

        return t;
    }

    public static void createCardButton(Texture tex)
    {
        cardButton = new Button(tex, 0, 0, cardWidth, cardHeight);
        cardList.add(cardButton);
    }

    public static void winnerFound(int i){
        if(i == 0)
        {

        }
        if(i == 1)
        {

        }
        if(i == 2)
        {

        }
        if(i == 3)
        {

        }
    }


    public static ArrayList<Button> getCardList()
    {
        return cardList;
    }


    public static void redoHand(String color, int number)
    {
        String fileName = "cards/";
        if(color == "Red")
        {
            fileName += "R";
        }

        if(color == "Blue")
        {
            fileName += "B";
        }

        if(color == "Yellow")
        {
            fileName += "Y";
        }

        if(color == "Green")
        {
            fileName += "G";
        }

        if(color == "Wild")
        {
            fileName += "W";
        }



        fileName += Integer.toString(number) + ".jpeg";
        //Texture t = new Texture(fileName);
        Texture t = assMan.manager.get(fileName);


        cardButton = new Button(t, 0, 0, cardWidth, cardHeight);
        cardList.add(cardButton);
        //cardButton = new Button(t, 0, 0, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        //cardList.add(cardButton);
    }

    public static void clearList()
    {
        cardList.clear();
    }

    public static boolean getIsPlayed()
    {
        return isPlayed;
    }

    public static void setIsPlayed(boolean flag)
    {
        isPlayed = flag;
    }

    public static boolean getCardSelected()
    {
        return cardSelected;
    }

    public static void setCardSelected(boolean flag)
    {
        cardSelected = flag;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button){
        return true;
    }


    @Override
    public boolean tap(float x, float y, int count, int button){
        if (turn == GameData.Turn.PLAYER0) {
            PlayCard();
            if (cardSelected) {
                wildCardPick();
            }
        }

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

        //For loop to move all the cards
        for (int i = 0; i < cardList.size(); i++){
            if (i == 0){
                //if the card will be in a position less than the minimum allowed x value break out and stop moving
                if (cardList.get(i).rect.x + deltaX < cardList.get(i).bounds.minX){
                    break;
                }

                //if the card will be in a position greater than the maximum allowed x value break out and stop moving
                if (cardList.get(i).rect.x + deltaX > cardList.get(i).bounds.maxX){
                    break;
                }
            }

            //Moves the cards
            cardList.get(i).reposition(cardList.get(i).rect.x + deltaX, cardList.get(i).rect.y);
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

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            for (Music m : musicList) {
                m.stop();
            }
            game.setScreen(new MainScreen(game));
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        return true;
    }

    @Override
    public boolean keyTyped(char character) {

        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(float x, float y) {
        return false;
    }
}
