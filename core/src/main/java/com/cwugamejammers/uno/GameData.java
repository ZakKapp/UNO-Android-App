//Beta Build 4/28

package com.cwugamejammers.uno;

import java.util.Stack;

public class GameData {

	private Turn turn;
	private GameState state;

	//Turn Tracker
	enum Turn
	{
		PLAYER0(0),
		PLAYER1(1),
		PLAYER2(2),
		PLAYER3(3);
		private int turnTrack;

		Turn(int i)
		{
			turnTrack = i;
		}
	}

	//States for WINNER, LOSER, or in progress
	enum GameState {
		WINNER,
		MIDDLE
	}

	public GameData() {
		//Create the deck, and then place the top card of the deck into the playField.
		Card.createDeck();
		Card.getPlayField().add(Card.getDeck().remove(0));

		//Set the GameState to indicate that a game is in progress
		state = GameState.MIDDLE;
		//Player1 gets the first move
		turn = Turn.PLAYER0;
	}

	public void setTracker(int input)
	{

	}
	public Turn getTurn()
	{
		return turn;
	}

	public GameState getState()
	{
		return state;
	}

	public Player getNextPlayer()
	{
		if(turn == Turn.PLAYER0 && GameController.getReversed() == false)
		{

		}
	}
}