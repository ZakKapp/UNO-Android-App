
//Beta Build: 5/19

package com.cwugamejammers.uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameController {

	private static Player p0;
	private static Player p1;
	private static Player p2;
	private static Player p3;

	private static boolean reversed;
	private ArrayList<Player> playerList = new ArrayList<Player>();

	private GameData data;

	private PlayScreen screen;
	//not static ref to playScreen
	public GameController(PlayScreen playscreen)
	{
		screen = playscreen;
	}

	public void run() {
		//While there is no winner

			//Do a switch, cases are whose turn it is
			switch(data.getTurn()) {
				case PLAYER0:
					upkeep(p0);
				case PLAYER1:
					upkeep(p1);
				case PLAYER2:
					upkeep(p2);
				case PLAYER3:
					upkeep(p3);
			}


		//Check who won and display the correct message
		switch(data.getState())
		{
			case PLAYER0:
				//winner is you
			case PLAYER1:
				// winner is AI1
			case PLAYER2:
				// winner is AI2
			case PLAYER3:
				// winner is AI3
		}
	}

	public void initialize(){
		p0 = new Player(0, "You", false);
		p1 = new Player(1, "AI1", true);
		p2 = new Player(2, "AI2", true);
		p3 = new Player(3, "AI3", true);

		data = new GameData();

		for (int i = 0; i < 7; i++)
		{
				p0.draw();
				p1.draw();
				p2.draw();
				p3.draw();
		}

		//run();
	}

	public void playerUpkeep(int index){
		p0.play(index);
		checkPlay(p0);
		if (p0.getHandSize() == 0){
			data.setWinner();
		}
	}



	public void upkeep(Player player) {
		//Check skip flags to see if turn is valid.
		//If player is not skipped, run the turn
		if(player.getSkipped() == false)
		{
			//Does the player have a valid play?
			if(player.getIsAI() == false)
			{
				int[] validPlays = player.validPlay();
				boolean valid = false;
				for(int i = 0; i < player.getHandSize(); i++)
				{
					if(validPlays[i] == 1) valid = true;
				}

				if(valid == true)
				{
					//prompt the user to play a card if a valid play is available
					//call some graphic to let them know
					/*while(PlayScreen.getIsPlayed() == false)
					{

					}*/



					checkPlay(player);
					PlayScreen.setIsPlayed(false);
				}
				else
				{
					//if the play was not valid, skip the turn
					player.draw();
					return;
				}
			}

			else
			{
				int[] validPlays = player.validPlayAI();
				boolean valid = false;
				for(int i = 0; i < player.getHandSize(); i++)
				{
					if(validPlays[i] == 1) valid = true;
				}

				if(valid == true)
				{
					//prompt the user to play a card if a valid play is available
					//call some graphic to let them know
					boolean foundPlay = false;
					while(foundPlay == false)
					{
						Random r = new Random();
						int high = validPlays.length;
						int result = r.nextInt(high);
						if(validPlays[result] == 1)
						{
							player.play(result);
							foundPlay = true;
						}
					}
					checkPlay(player);
				}
				else
				{
					//if the play was not valid, skip the turn
					player.draw();
					return;
				}
			}
		}
		//If the player was skipped, flip the flag and move on
		else
		{
			player.setSkipped(!player.getSkipped());
		}

		data.setTracker();
	}


	// method that check if the card is valid
	public boolean isValidCard(int index){
		// variable to check if valid
		boolean esValid = false;
		//top card of the play pile
		Card temp = Card.getPlayField().get(Card.getPlayField().size() - 1);
		// card that being compared
		Card comp = p0.getHand().get(index);
		// if statement that compare the number or color
		if(temp.getNumber() == comp.getNumber() || temp.getColor() == comp.getColor()){
			esValid = true;
		}
		// return the variable
		return esValid;
	}

	//yoink
	public boolean playableCards()
	{
		int[] validPlays = p0.validPlay();
		boolean valid = false;
		for(int i = 0; i < p0.getHandSize(); i++)
		{
			if(validPlays[i] == 1) valid = true;
		}
		return valid;
	}


	public void checkPlay(Player player)
	{
		Card temp = Card.getPlayField().get(Card.getPlayField().size() - 1);

		//Skip
		if(temp.getNumber() == 10)
		{
			data.getNextPlayer().setSkipped(true);
			return;
		}
		//Reverse
		if(temp.getNumber() == 11)
		{
			setReversed(!getReversed());
			return;
		}
		//Draw 2
		if(temp.getNumber() == 12)
		{
			data.getNextPlayer().setSkipped(true);
			data.getNextPlayer().drawTwo();
		}
		//Wild Card
		if(temp.getNumber() == 13)
		{
			if(data.getTurn() == GameData.Turn.PLAYER0)
			{
				//Call the method for the Wildcard selection
				String newColor = PlayScreen.wildCardPick();
				while(newColor == null)
				{
					newColor = PlayScreen.wildCardPick();
					while(PlayScreen.getCardSelected() == false)
					{
					}
				}
				Card.getPlayField().get(Card.getPlayField().size() - 1).setColor(newColor);
				if(newColor == "Red")
				{
					screen.setWildCard("W13R.jpeg");
				}
				if(newColor == "Blue")
				{
					screen.setWildCard("W13B.jpeg");
				}
				if(newColor == "Green")
				{
					screen.setWildCard("W13G.jpeg");
				}
				if(newColor == "Yellow")
				{
					screen.setWildCard("W13Y.jpeg");
				}
			}
			else
			{
				Card.getPlayField().get(Card.getPlayField().size() - 1).setColor("Red");
			}
		}
		//Wild Draw 4
		if(temp.getNumber() == 14)
		{
			data.getNextPlayer().setSkipped(true);
			data.getNextPlayer().drawFour();
			if(data.getTurn() == GameData.Turn.PLAYER0)
			{
				//Call the method for the Wildcard selection
				String newColor = PlayScreen.wildCardPick();
				while(newColor == null)
				{
					newColor = PlayScreen.wildCardPick();
					while(PlayScreen.getCardSelected() == false)
					{
					}
				}
				Card.getPlayField().get(Card.getPlayField().size() - 1).setColor(newColor);
				if(newColor == "Red")
				{
					screen.setWildCard("W14R.jpeg");
				}
				if(newColor == "Blue")
				{
					screen.setWildCard("W14B.jpeg");
				}
				if(newColor == "Green")
				{
					screen.setWildCard("W14G.jpeg");
				}
				if(newColor == "Yellow")
				{
					screen.setWildCard("W14Y.jpeg");
				}
			}
			else
			{
				Card.getPlayField().get(Card.getPlayField().size() - 1).setColor("Red");
			}
		}
		// if statement that check the hand size to see if they win
		if(player.getHandSize() == 0)
		{
			data.setWinner();
		}
	}

	// player object that check who the current player
	public Player getCurrentPlayer()
	{
		if(data.getTurn() == GameData.Turn.PLAYER0)
		{
			return p0;
		}
		if(data.getTurn() == GameData.Turn.PLAYER1)
		{
			return p1;
		}
		if(data.getTurn() == GameData.Turn.PLAYER2)
		{
			return p2;
		}
		else
		{
			return p3;
		}
	}
	public static boolean getReversed()
	{
		return reversed;
	}

	public static void setReversed(boolean reverse)
	{
		reversed = reverse;
	}
	public GameData getData()
	{
		return data;
	}

	public static Player getP0()
	{
		return p0;
	}

	public static Player getP1(){
		return p1;
	}

	public static Player getP2()
	{
		return p2;
	}

	public static Player getP3()
	{
		return p3;
	}
}
