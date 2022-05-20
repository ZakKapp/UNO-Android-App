//Beta Build: 5/3

package com.cwugamejammers.uno;

import java.util.ArrayList;
import java.util.Collections;
public class Player {
	private int id;
	private String name;
	private ArrayList<Card> hand;

	private boolean isAI;
	private boolean skipped;

	private boolean noPlay;


	public Player(int player_id, String player_name, boolean ai) {
		id = player_id;
		name = player_name;
		skipped = false;
		hand = new ArrayList<Card>();
		isAI = ai;
		noPlay = false;
	}

	public boolean getIsAI()
	{
		return isAI;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Card> getHand() { return hand; }

	public int getHandSize(){return hand.size();}

	public boolean getSkipped()
	{
		return skipped;
	}

	public void setSkipped(boolean flag)
	{
		skipped = flag;
	}

	public boolean getNoPlay()
	{
		return noPlay;
	}

	public void setNoPlay(boolean flag)
	{
		noPlay = flag;
	}

	public int[] validPlay()
	{
		int[] valid = new int[hand.size()];

		int playFieldSize = Card.getPlayField().size() - 1;

		for(int i = 0; i < hand.size(); i++)
		{
			//If the card in hand is a draw 4 or wildcard, it is valid
			if(hand.get(i).getNumber() == 13 || hand.get(i).getNumber() == 14)
			{
				valid[i]++;
				//somehow elevate the texture
			}
			//If the card in hand matches the color of the top playField, it is valid
			else if(hand.get(i).getColor() == Card.getPlayField().get(playFieldSize).getColor())
			{
				valid[i]++;
				//somehow elevate the texture
			}
			//If the card matches the number of the top playField, it is valid
			else if(hand.get(i).getNumber() == Card.getPlayField().get(playFieldSize).getNumber())
			{
				valid[i]++;
				//somehow elevate the texture
			}
		}
		return valid;
	}

	public int[] validPlayAI()
	{
		int[] valid = new int[hand.size()];
		int playFieldSize = Card.getPlayField().size() - 1;

		for(int i = 0; i < hand.size(); i++)
		{
			//If the card in hand is a draw 4 or wildcard, it is valid
			if(hand.get(i).getNumber() == 13 || hand.get(i).getNumber() == 14)
			{
				valid[i]++;
			}
			//If the card in hand matches the color of the top playField, it is valid
			else if(hand.get(i).getColor() == Card.getPlayField().get(playFieldSize).getColor())
			{
				valid[i]++;
			}
			//If the card matches the number of the top playField, it is valid
			else if(hand.get(i).getNumber() == Card.getPlayField().get(playFieldSize).getNumber())
			{
				valid[i]++;
			}
		}
		return valid;
	}

	public void play(int index)
	{
		Card.getPlayField().add(hand.remove(index));
	}

	public void draw()
	{
		if(Card.isEmpty() == true)
		{
			Card.emptyShuffle();
		}
		getHand().add(Card.getDeck().remove(0));

		int card = getHand().size() - 1;	//-----------------------------------------------------------------------------------------------------
		//IF PLAYER IS HUMAN
		if(getId() == 0)
		{
			//getHand().get(card).createTexture(getHand().get(card));
			//textureButton = PlayScreen.createCard(getHand().get(getHand().size() - 1));
			PlayScreen.createCardButton(getHand().get(card).getTextureButton());
			sortHand();
		}

	}

	public void drawTwo()
	{
		draw();
		draw();
	}

	public void drawFour()
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
		ArrayList<Card> wild = new ArrayList<Card>();


		for(int i = 0; i < getHand().size(); i++)
		{
			if(getHand().get(i).getColor() == "Red") red.add(getHand().get(i));
			if(getHand().get(i).getColor() == "Blue") blue.add(getHand().get(i));
			if(getHand().get(i).getColor() == "Green") green.add(getHand().get(i));
			if(getHand().get(i).getColor() == "Yellow") yellow.add(getHand().get(i));
			if(getHand().get(i).getColor() == "Wild") wild.add(getHand().get(i));
		}


			getHand().clear();


		Collections.sort(red);
		Collections.sort(blue);
		Collections.sort(green);
		Collections.sort(yellow);
		Collections.sort(wild);

		getHand().addAll(red);
		getHand().addAll(blue);
		getHand().addAll(green);
		getHand().addAll(yellow);
		getHand().addAll(wild);


		//NEW IDEA: toString the texture's and then sort the cardList
		/*
		PlayScreen.clearList();
		for(int i = 0; i < getHand().size(); i++)
		{
			PlayScreen.createCardButton(getHand().get(i).getTextureButton());
		}
		*/

		PlayScreen.clearList();
		for(int i = 0; i < hand.size(); i++)
		{
			PlayScreen.redoHand(hand.get(i).getColor(), hand.get(i).getNumber());
		}

	}

}