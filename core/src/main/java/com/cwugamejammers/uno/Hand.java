//Beta Build: 4/28

package com.cwugamejammers.uno;

import java.util.ArrayList;
//ayy lmao
public class Hand {

	private ArrayList<Card> hand = new ArrayList<Card>();
	private int size;

	//Possibly obsolete?
	/**
	public void draw(GameData data) {
		hand.add(Card.cardDraw(data.getDeck()));
	}
	*/
	public void play(Card card) {
		Card.cardPlay(card);
		hand.remove(card);
	}

	public ArrayList<Card> getList()
	{
		return hand;
	}

}