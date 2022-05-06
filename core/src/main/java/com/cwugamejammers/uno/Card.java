// Beta Build: 4/28

package com.cwugamejammers.uno;

import com.badlogic.gdx.Gdx;

import java.util.Collections;
import java.util.Stack;

public class Card implements Comparable<Card>
//ඞ
{
	// Colors
	private final static String[] color = { "Red", "Blue", "Yellow", "Green", "Black" };

	// Deck
	private static Stack<Card> deck;

	private static Stack<Card> playField;

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
		Card card = null;

		for (int i = 0; i < 4; ++i) {
			card = new Card(0, color[i], false);
			deck.push(card);

			for (int cardValue = 1; cardValue < 10; ++cardValue) {
				card = new Card(cardValue, color[i], false);
				deck.push(card);
				deck.push(card);
			}
			// 10 = skip
			// 11 = reversal
			// 12 = +2
			// 13 wild
			// 14 wild +4
			card = new Card(10, color[i], true);
			deck.push(card);
			deck.push(card);
			card = new Card(11, color[i], true);
			deck.push(card);
			deck.push(card);
			card = new Card(12, color[i], true);
			deck.push(card);
			deck.push(card);

		}

		// Creates 4 Wild Cards
		for (int i = 0; i < 4; ++i) {
			card = new Card(13, "Wild", true);
			deck.push(card);
		}

		// Creates 4 Wild+4 Cards
		for (int i = 0; i < 4; ++i) {
			card = new Card(14, "Wild", true);
			deck.push(card);
		}

		// Shuffles
		Collections.shuffle(deck);
	}

	public static Stack<Card> getDeck() {
		return deck;
	}

	public static int getDeckSize()
	{
		return deck.size();
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public int getIndex()
	{
		return index;
	}
	public static void cardDraw(Stack<Card> deck, Player player)
	{
		//If there are no cards remaining when a draw is attempted, reshuffle the deck first.
		if(getDeckSize() == 0)
		{
			reshuffle();
		}

		//Draw the card
		Card draw = deck.pop();
		player.getList().add(draw);
		//Sort the hand after draw
		//player.getHand().sortHand();

		if(player.getId() == 0)
		{
			//CREATE THE CARD BUTTON
			PlayScreen.createCard(draw);
		}
	}

	public static void cardDrawTwo(Stack<Card> deck, Player player)
	{
		/**
		for(int i = 0; i < 2; i++)
		{
			//If there are no cards remaining when a draw is attempted, reshuffle the deck first.
			if(getDeckSize() == 0)
			{
				reshuffle();
			}

			//Draw the card
			player.getList().add(deck.pop());

		}
		 */
		cardDraw(deck, player);
		cardDraw(deck, player);
		//player.getHand().sortHand();
	}

	public static void cardDrawFour(Stack<Card> deck, Player player)
	{
		/**
		for(int i = 0; i < 4; i++)
		{
			//If there are no cards remaining when a draw is attempted, reshuffle the deck first.
			if(getDeckSize() == 0)
			{
				reshuffle();
			}

			//Draw the card
			player.getList().add(deck.pop());
		}
		 */
		cardDraw(deck, player);
		cardDraw(deck, player);
		cardDraw(deck, player);
		cardDraw(deck, player);
		//player.getHand().sortHand();
	}
	public static void cardPlay(Card card) {
		playField.push(card);
	}

	public static Card playFieldView()
	{
		return playField.peek();
	}

	public static void reshuffle() {
		// Pushes the top playField card so it is safe for later
		Card temp = playField.pop();

		// Push the remaining deck into the playField
		for (int i = 0; i < deck.size(); i++) {
			playField.push(deck.pop());
		}

		// Move all of playField into deck
		for (int i = 0; i < playField.size(); i++) {
			deck.push(playField.pop());
		}
		// shuffle it
		Collections.shuffle(deck);
		// move our temp back to playField
		playField.push(temp);
	}
}
