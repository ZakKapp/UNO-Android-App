// Beta Build: 4/28

package com.cwugamejammers.uno;

import com.badlogic.gdx.Gdx;

import java.util.Collections;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;

public class Card implements Comparable<Card>
//ඞ
{
	// Colors
	private final static String[] color = { "Red", "Blue", "Yellow", "Green", "Black" };
	private static ArrayList<Card> playField = new ArrayList<Card>();

	// What the number of our card is (0,1,2...9)
	private int number;
	// The color of our card (Red, Blue, Yellow, Green, Wild)
	private String cardColor;
	// This boolean checks if the card is a special card or not (Draw 4, Draw 2,
	// Wildcard
	private boolean isSpecial;
	private int index;

	// Returns the number of the card
	public int getNumber() {
		return number;
	}

	// Returns the color of the card
	public String getColor() {
		return cardColor;
	}

	// Returns true if card is special, false if not
	public boolean getIsSpecial() {
		return isSpecial;
	}

	public static final int NUMBER_OF_DUPE_REG_CARDS = 2;
	public static final int NUMBER_OF_DUPE_ZERO_CARDS = 1;
	public static final int NUMBER_OF_DUPE_SPEC_CARDS = 2;
	public static final int NUMBER_OF_WILD_CARDS = 4;
	public static final int NUMBER_OF_WILD_D4_CARDS = 4;
	public static final int SHUFFLE_FACTOR = 1;

	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static Random rand;

	@Override public int compareTo(Card comparesto)
	{
		int compareNum = ((Card)comparesto).getNumber();

		return this.number - compareNum;
	}
	// Default constructor
	public Card(int num, String col, boolean isSpec) {
		number = num;
		cardColor = col;
		isSpecial = isSpec;
		index = -1;
	}

	public static void createDeck() {

		rand = new Random();
		for(int i = 1; i <= 9; i++)
		{
			for(int j = 0; j < NUMBER_OF_DUPE_REG_CARDS; j++)
			{
				deck.add(new Card(i, "Red", false));
				deck.add(new Card(i, "Blue", false));
				deck.add(new Card(i, "Yellow", false));
				deck.add(new Card(i, "Green", false));
			}
		}

		for(int j = 0; j< NUMBER_OF_DUPE_ZERO_CARDS; j++)
		{
			deck.add(new Card(0, "Red", false));
			deck.add(new Card(0, "Blue", false));
			deck.add(new Card(0, "Yellow", false));
			deck.add(new Card(0, "Green", false));
		}

		for(int j = 0; j < NUMBER_OF_DUPE_SPEC_CARDS; j++)
		{
			deck.add(new Card(10, "Red", true));
			deck.add(new Card(10, "Blue", true));
			deck.add(new Card(10, "Yellow", true));
			deck.add(new Card(10, "Green", true));
			deck.add(new Card(11, "Red", true));
			deck.add(new Card(11, "Blue", true));
			deck.add(new Card(11, "Yellow", true));
			deck.add(new Card(11, "Green", true));
			deck.add(new Card(12, "Red", true));
			deck.add(new Card(12, "Blue", true));
			deck.add(new Card(12, "Yellow", true));
			deck.add(new Card(12, "Green", true));
		}

		for(int i = 0; i < NUMBER_OF_WILD_CARDS; i++)
		{
			deck.add(new Card(13, "Wild", true));
		}

		for(int i = 0; i < NUMBER_OF_WILD_D4_CARDS; i++)
		{
			deck.add(new Card(14, "Wild", true));
		}

		shuffle();
	}

	public static void shuffle()
	{
		//lmao
		for(int i = 0; i < SHUFFLE_FACTOR * deck.size(); i++)
		{
			int x = rand.nextInt(deck.size());
			int y = rand.nextInt(deck.size());
			Card temp = deck.get(x);
			deck.set(y, temp);
		}
	}

	public boolean isEmpty()
	{
		return deck.size() == 0;
	}

	public static void draw(Player player)
	{
		if(deck.size() == 0)
		{
			emptyShuffle();
		}
		Card toCreate = deck.get(0);
		player.getList().add(deck.remove(0));

		//IF PLAYER IS HUMAN
		if(player.getId() == 0)
		{
			PlayScreen.createCard(toCreate);
		}

	}

	public static void drawTwo(Player player)
	{
		draw(player);
		draw(player);
	}

	public static void drawFour(Player player)
	{
		draw(player);
		draw(player);
		draw(player);
		draw(player);
	}

	public static void emptyShuffle()
	{
		Card temp = deck.get(deck.size() - 1);
		deck.addAll(playField);
		shuffle();
		playField.add(temp);
	}

	public static ArrayList<Card> getPlayField()
	{
		return playField;
	}

	public static ArrayList<Card> getDeck()
	{
		return deck;
	}
}
