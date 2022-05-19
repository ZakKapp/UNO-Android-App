//Beta Build: 5/3

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

	public void run() throws InterruptedException {
		//While there is no winner
		while(data.getState() == GameData.GameState.MIDDLE)
		{
			//Do a switch, cases are whose turn it is
			switch(data.getTurn())
			{
				case PLAYER0:
					upkeep(p0);
				case PLAYER1:
					upkeep(p1);
				case PLAYER2:
					upkeep(p2);
				case PLAYER3:
					upkeep(p3);
			}
		}
		//Check who won and display the correct message
		switch(data.getState())
		{

		}
	}

	public void initialize() throws InterruptedException {
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

		run();
	}

	public void upkeep(Player player) throws InterruptedException {
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

				if(valid = true)
				{
					//prompt the user to play a card if a valid play is available
					//call some graphic to let them know
					while(PlayScreen.getIsPlayed() == false)
					{
					}
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

				if(valid = true)
				{
					//prompt the user to play a card if a valid play is available
					//call some graphic to let them know
					TimeUnit.SECONDS.sleep(2);
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

	public void checkPlay(Player player)
	{
		Card temp = Card.getPlayField().get(Card.getPlayField().size() - 1);

		//Skip
		if(temp.getNumber() == 10)
		{
			data.getNextPlayer().setSkipped(true);
		}
		//Reverse
		if(temp.getNumber() == 11)
		{
			setReversed(!getReversed());
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
			//Call the method for the Wildcard selection
			String newColor = null;
			while(newColor == null)
			{
				//re call method here, they didn't pick
			}
			//Card.getPlayField().get(Card.getPlayField().size() - 1).setColor(newColor);

		}
		//Wild Draw 4
		if(temp.getNumber() == 14)
		{
			data.getNextPlayer().setSkipped(true);
			data.getNextPlayer().drawFour();
			//Call the method for the Wildcard selection
			String newColor = null;
			while(newColor == null)
			{

			}
		}

		if(player.getHandSize() == 0)
		{
			data.setWinner();
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
