//Beta Build: 5/3

package com.cwugamejammers.uno;

import java.util.ArrayList;
public class Player {
	private int id;
	private String name;
	private ArrayList<Card> hand;

	private boolean isAI;
	private boolean isSkipped;

	private boolean noPlay;

	public Player(int player_id, String player_name, boolean ai) {
		id = player_id;
		name = player_name;
		isSkipped = false;
		hand = new ArrayList<Card>();
		isAI = ai;
		noPlay = false;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Card> getHand() { return hand; }

	public boolean getIsSkipped()
	{
		return isSkipped;
	}

	public void setIsSkipped(boolean flag)
	{
		isSkipped = flag;
	}

	public boolean getNoPlay()
	{
		return noPlay;
	}

	public void setNoPlay(boolean flag)
	{
		noPlay = flag;
	}

	public boolean getHumanPlay()
	{
		int playFieldSize = Card.getPlayField().size();
		//Checks to see what is top card of play pile, finds a valid card in hand and plays it.
		for(int i = 0; i < getHand().size(); i++)
		{
			//If the color is the same, play the card
			if(Card.getPlayField().get(playFieldSize - 1).getColor() == getHand().get(i).getColor())
			{
				return true;
			}
			//If there is a same number, play the card.
			else if(Card.getPlayField().get(playFieldSize - 1).getNumber() == getHand().get(i).getNumber())
			{
				return true;
			}
		}

		//If no card in the hand was found, set noPlay flag and return -1;
		setNoPlay(true);
		return false;
	}
	public int getAIPlay()
	{
		int playFieldSize = Card.getPlayField().size();
		//Checks to see what is top card of play pile, finds a valid card in hand and plays it.
		for(int i = 0; i < getHand().size(); i++)
		{
			//If the color is the same, play the card
			if(Card.getPlayField().get(playFieldSize - 1).getColor() == getHand().get(i).getColor())
			{
				return i;
			}
			//If there is a same number, play the card.
			else if(Card.getPlayField().get(playFieldSize - 1).getNumber() == getHand().get(i).getNumber())
			{
				return i;
			}
		}

		//If no card in the hand was found, set noPlay flag and return -1;
		setNoPlay(true);
		return -1;
	}

	public void play(int index)
	{
		Card.getPlayField().add(hand.remove(index));
	}

	public void draw()
	{
		if(Card.getDeck().size() == 0)
		{
			Card.emptyShuffle();
		}
		Card toCreate = Card.getDeck().get(0);
		getHand().add(Card.getDeck().remove(0));

		//IF PLAYER IS HUMAN
		if(getId() == 0)
		{
			PlayScreen.createCard(toCreate);
		}

	}

	public void drawTwo(Player player)
	{
		draw();
		draw();
	}

	public void drawFour(Player player)
	{
		draw();
		draw();
		draw();
		draw();
	}

}