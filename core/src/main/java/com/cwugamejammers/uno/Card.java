// Beta Build: 4/28

package com.cwugamejammers.uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
public class Card implements Comparable<Card>
//ඞ
{
	// Colors
	private final static String[] color = { "Red", "Blue", "Yellow", "Green", "Black" };
	private static ArrayList<Card> playField = new ArrayList<Card>();

	// What the number of our card is (0,1,2...9)
	private static int number;
	// The color of our card (Red, Blue, Yellow, Green, Wild)
	private static String cardColor;
	// This boolean checks if the card is a special card or not (Draw 4, Draw 2,
	// Wildcard
	private boolean isSpecial;

	// Returns the number of the card
	public int getNumber() {
		return number;
	}

	// Returns the color of the card
	public String getColor() {
		return cardColor;
	}

	public static final int NUMBER_OF_DUPE_REG_CARDS = 2;
	public static final int NUMBER_OF_DUPE_ZERO_CARDS = 1;
	public static final int NUMBER_OF_DUPE_SPEC_CARDS = 2;
	public static final int NUMBER_OF_WILD_CARDS = 4;
	public static final int NUMBER_OF_WILD_D4_CARDS = 4;
	public static final int SHUFFLE_FACTOR = 1;

	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static Random rand;

	private Texture textureButton;

	@Override public int compareTo(Card comparesto)
	{
		int compareNum = ((Card)comparesto).getNumber();

		return this.number - compareNum;
	}
	// Default constructor
	public Card(int num, String col) {
		number = num;
		cardColor = col;
		textureButton = PlayScreen.createTexture(cardColor, number);
	}

	public static void createDeck() {

		rand = new Random();
		for(int i = 1; i <= 9; i++)
		{
			for(int j = 0; j < NUMBER_OF_DUPE_REG_CARDS; j++)
			{
				deck.add(new Card(i, "Red"));
				deck.add(new Card(i, "Blue"));
				deck.add(new Card(i, "Yellow"));
				deck.add(new Card(i, "Green"));
			}
		}

		for(int j = 0; j< NUMBER_OF_DUPE_ZERO_CARDS; j++)
		{
			deck.add(new Card(0, "Red"));
			deck.add(new Card(0, "Blue"));
			deck.add(new Card(0, "Yellow"));
			deck.add(new Card(0, "Green"));
		}

		for(int j = 0; j < NUMBER_OF_DUPE_SPEC_CARDS; j++)
		{
			//10 is Skip, 11 is Reverse, 12 is Draw 2
			deck.add(new Card(10, "Red"));
			deck.add(new Card(10, "Blue"));
			deck.add(new Card(10, "Yellow"));
			deck.add(new Card(10, "Green"));
			deck.add(new Card(11, "Red"));
			deck.add(new Card(11, "Blue"));
			deck.add(new Card(11, "Yellow"));
			deck.add(new Card(11, "Green"));
			deck.add(new Card(12, "Red"));
			deck.add(new Card(12, "Blue"));
			deck.add(new Card(12, "Yellow"));
			deck.add(new Card(12, "Green"));
		}

		for(int i = 0; i < NUMBER_OF_WILD_CARDS; i++)
		{
			deck.add(new Card(13, "Wild"));
		}

		for(int i = 0; i < NUMBER_OF_WILD_D4_CARDS; i++)
		{
			deck.add(new Card(14, "Wild"));
		}

		/////POSSIBLE CARD DUPLICATIONS Fix:  emptyshuffle and shuffle method is also the culprit
		//Collections.shuffle(deck);
		shuffle();
	}

	public static void shuffle()
	{
		//ඞ
		Collections.shuffle(deck);
	}

	public static boolean isEmpty()
	{
		return deck.size() == 0;
	}

	public static void emptyShuffle()
	{
		Card temp = getPlayField().get(getPlayField().size() - 1);
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

	public static void setColor(String newColor)
	{
		cardColor = newColor;
	}

	/*
	public void createTexture(Card card)
	{
		textureButton = PlayScreen.createTexture(card);
	}

	*/

	public Texture getTextureButton()
	{
		return textureButton;
	}
}
