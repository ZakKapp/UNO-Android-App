//Beta Build: 5/3

package com.cwugamejammers.uno;

import java.util.ArrayList;
import java.util.Collections;
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
		getHand().add(Card.getDeck().remove(0));

		int card = getHand().size() - 1;
		//IF PLAYER IS HUMAN
		if(getId() == 0)
		{
			//getHand().get(card).createTexture(getHand().get(card));
			//textureButton = PlayScreen.createCard(getHand().get(getHand().size() - 1));
			PlayScreen.createCardButton(getHand().get(card).getTextureButton());
			sortHand();
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

	public void sortHand()
	{
		ArrayList<Card> red = new ArrayList<Card>();
		ArrayList<Card> blue = new ArrayList<Card>();
		ArrayList<Card> green = new ArrayList<Card>();
		ArrayList<Card> yellow = new ArrayList<Card>();
		ArrayList<Card> black = new ArrayList<Card>();


		for(int i = 0; i < getHand().size(); i++)
		{
			if(getHand().get(i).getColor() == "Red") red.add(getHand().get(i));
			if(getHand().get(i).getColor() == "Blue") blue.add(getHand().get(i));
			if(getHand().get(i).getColor() == "Green") green.add(getHand().get(i));
			if(getHand().get(i).getColor() == "Yellow") yellow.add(getHand().get(i));
			if(getHand().get(i).getColor() == "Black") black.add(getHand().get(i));
		}

		for(int i = 0; i < getHand().size(); i++)
		{
			getHand().remove(i);
		}

		Collections.sort(red);
		Collections.sort(blue);
		Collections.sort(green);
		Collections.sort(yellow);
		Collections.sort(black);

		getHand().addAll(red);
		getHand().addAll(blue);
		getHand().addAll(green);
		getHand().addAll(yellow);
		getHand().addAll(black);

		PlayScreen.clearList();
		for(int i = 0; i < getHand().size(); i++)
		{
			PlayScreen.createCardButton(getHand().get(i).getTextureButton());
		}

	}

}