//Beta Build: 4/28

package com.cwugamejammers.uno;

import java.util.ArrayList;
import java.util.Collections;
//ayy lmao
public class Hand {

	private ArrayList<Card> hand = new ArrayList<Card>();

	public void play(Card card) {
		Card.cardPlay(card);
		hand.remove(card);
	}

	public ArrayList<Card> getList()
	{
		return hand;
	}

	// Red Blue Green Yellow
	public void sortHand()
	{
		ArrayList<Card> red = new ArrayList<Card>();
		ArrayList<Card> blue = new ArrayList<Card>();
		ArrayList<Card> yellow = new ArrayList<Card>();
		ArrayList<Card> green = new ArrayList<Card>();

		for(int i = 0; i < hand.size(); i++)
		{
			if(hand.get(i).getColor() == "Red")
			{
				red.add(hand.get(i));
			}
			if(hand.get(i).getColor() == "Blue")
			{
				blue.add(hand.get(i));
			}
			if(hand.get(i).getColor() == "Green")
			{
				green.add(hand.get(i));;
			}
			if(hand.get(i).getColor() == "Yellow")
			{
				yellow.add(hand.get(i));
			}
		}
		int size = hand.size() - 1;
		for(int i = size; i >= 0; i--)
		{
			hand.remove(i);
		}

		Collections.sort(red);
		Collections.sort(blue);
		Collections.sort(green);
		Collections.sort(yellow);

		hand.addAll(red);
		hand.addAll(blue);
		hand.addAll(green);
		hand.addAll(yellow);

		for(int i = 0; i < hand.size(); i++)
		{
			hand.get(i).setIndex(i);
		}
	}

}