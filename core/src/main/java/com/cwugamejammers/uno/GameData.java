//Beta Build 4/28

package com.cwugamejammers.uno;

import java.util.Stack;

public class GameData {

	private int winnerId;
	private int currentTurnId;
	public GameData()
	{
		//Create the deck, and then place the top card of the deck into the playField.
		Card.createDeck();
		Card.getPlayField().add(Card.getDeck().remove(0));
	}
}