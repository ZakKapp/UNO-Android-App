//Beta Build: 5/3

package com.cwugamejammers.uno;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

	private static Player p0;
	private static Player p1;
	private static Player p2;
	private static Player p3;

	private static boolean reversed;
	private int currentTurn;
	private ArrayList<Player> playerList = new ArrayList<Player>();

	private GameData data;

	public void run()
	{
		//While there is no winner
		while(data.getState() != GameData.GameState.WINNER)
		{
			//Do a switch, cases are whose turn it is
			switch(data.getTurn())
			{
				case PLAYER0:
					upkeep(p0);

			}
		}
	}

	public void initialize()
	{
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

	public void upkeep(Player player)
	{
		//Check skip flags to see if turn is valid.
		//If player is not skipped, run the turn
		if(player.getSkipped() == false)
		{
			//Does the player have a valid play?
			if(player.getIsAI() == false)
			{
				boolean valid = player.validPlay();


				if(valid = true)
				{
					//prompt the user to play a card if a valid play is available
					//call some graphic to let them know
					while(PlayScreen.getIsPlayed() == false)
					{
					}
					checkPlay();
					PlayScreen.setIsPlayed(false);

				}
				else
				{
					//if the play was not valid, skip the turn
					player.draw();
					return;
				}

				//If no valid play end the turn

			}
		}
		//If the player was skipped, flip the flag and move on
		else
		{
			player.setSkipped(!player.getSkipped());
			return;
		}
	}

	public void endTurn(Player player)
	{
		//Check to see if reverse flag is true.
	}

	public void checkPlay()
	{
		Card temp = Card.getPlayField().get(Card.getPlayField().size() - 1);

		//Skip
		if(temp.getNumber() == 10)
		{

		}
		//Reverse
		if(temp.getNumber() == 11)
		{

		}
		//Draw 2
		if(temp.getNumber() == 12)
		{

		}
		//Wild Card
		if(temp.getNumber() == 13)
		{

		}
		//Wild Draw 4
		if(temp.getNumber() == 14)
		{

		}
	}
	public boolean getReversed()
	{
		return reversed;
	}

	public void setReversed(boolean reverse)
	{
		reversed = reverse;
	}

	public int getCurrentTurn()
	{
		return currentTurn;
	}

	public void setCurrentTurn(int nextPlayer)
	{
		currentTurn = nextPlayer;
	}

	public GameData getData()
	{
		return data;
	}

	public static Player getP0() {
		return p0;
	}

	public Player getP1(){
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public Player getP3() {
		return p3;
	}
}
