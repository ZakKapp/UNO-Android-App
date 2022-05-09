package GameStateHandlers;

import java.util.ArrayList;
//Game state manager is the mediator that passes along functions

//initialize an actual gsm object in a controller or panel class.
// GameController? somewhere where we can control it and refer to it
public class GameStateManager {

    private ArrayList<GameState> gameStates;    //we could also use a stack
    //an index that keeps track of the different menus in the array list
    //ex. splashScreen = 0, mMenu = 1...
    private int currentState;   //where the index of the current state is saved

    public static final int MENUSTATE = 0;
    public static final int PLAYSTATE = 1;

    public GameStateManager(){  //constructor that creates all of the states
        gameStates = new ArrayList<GameState>();

        gameStates.add(new MenuState(this));
        gameStates.add(new PlayState(this));

        currentState = MENUSTATE;
    }

    //these will be all the methods that all the states are gonna need

    public void setCurrentState(int i){ //functiont that switches betwen states
        currentState = i;
    }

    public void update(){
        gameStates.get(currentState).update();
    }

    public void buttonPressed(int b){
        gameStates.get(currentState).buttonPressed(b);
    }

}
