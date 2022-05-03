//Beta Build 4/28

//package com.cwugamejammers.uno;

import java.util.Stack;

public class GameData {

	private Stack<Card> deck;

	public GameData() {
		deck = Card.createDeck();
	}

	public Stack<Card> getDeck() {
		return deck;
	}

	public Card getTop() {
		return deck.peek();
	}
}