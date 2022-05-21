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
		PLAYER0,
		PLAYER1,
		PLAYER2,
		PLAYER3,
		MIDDLE
	}

	public GameData() {
		//Create the deck, and then place the top card of the deck into the playField.
		Card.createDeck();
		Card.getPlayField().add(Card.getDeck().remove(0));
		Card temp = Card.getPlayField().get(0);
		String name = "cards/";
		if(temp.getColor().equals("Red")) name += "R";
		if(temp.getColor().equals("Blue")) name += "B";
		if(temp.getColor().equals("Yellow")) name += "Y";
		if(temp.getColor().equals("Green")) name += "G";
		if(temp.getColor().equals("Wild")) name += "W";

		name += Integer.toString(temp.getNumber());

		name += ".jpeg";

		PlayScreen.setPlayPileTex(name);

		//Set the GameState to indicate that a game is in progress
		state = GameState.MIDDLE;
		//Player1 gets the first move
		turn = Turn.PLAYER0;
	}

	public void setWinner()
	{
		if(turn == Turn.PLAYER0)
		{
			state = GameState.PLAYER0;
		}
		if(turn == Turn.PLAYER1)
		{
			state = GameState.PLAYER1;
		}
		if(turn == Turn.PLAYER2)
		{
			state = GameState.PLAYER2;
		}
		if(turn == Turn.PLAYER3)
		{
			state = GameState.PLAYER3;
		}
	}

	public void setTracker()
	{
		if(turn == Turn.PLAYER0 && GameController.getReversed() == false)
		{
			turn = Turn.PLAYER1;
		}
		if(turn == Turn.PLAYER0 && GameController.getReversed() == true)
		{
			turn = Turn.PLAYER3;
		}
		if(turn == Turn.PLAYER1 && GameController.getReversed() == false)
		{
			turn = Turn.PLAYER2;
		}
		if(turn == Turn.PLAYER1 && GameController.getReversed() == true)
		{
			turn = Turn.PLAYER0;
		}
		if(turn == Turn.PLAYER2 && GameController.getReversed() == false)
		{
			turn = Turn.PLAYER3;
		}
		if(turn == Turn.PLAYER2 && GameController.getReversed() == true)
		{
			turn = Turn.PLAYER2;
		}
		if(turn == Turn.PLAYER3 && GameController.getReversed() == false)
		{
			turn = Turn.PLAYER0;
		}
		if(turn == Turn.PLAYER3 && GameController.getReversed() == true)
		{
			turn = Turn.PLAYER2;
		}
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
			return GameController.getP1();
		}
		if(turn == Turn.PLAYER0 && GameController.getReversed() == true)
		{
			return GameController.getP3();
		}
		if(turn == Turn.PLAYER1 && GameController.getReversed() == false)
		{
			return GameController.getP2();
		}
		if(turn == Turn.PLAYER1 && GameController.getReversed() == true)
		{
			return GameController.getP0();
		}
		if(turn == Turn.PLAYER2 && GameController.getReversed() == false)
		{
			return GameController.getP3();
		}
		if(turn == Turn.PLAYER2 && GameController.getReversed() == true)
		{
			return GameController.getP1();
		}
		if(turn == Turn.PLAYER3 && GameController.getReversed() == false)
		{
			return GameController.getP0();
		}
		if(turn == Turn.PLAYER3 && GameController.getReversed() == true)
		{
			return GameController.getP2();
		}
		return GameController.getP0();
	}
}