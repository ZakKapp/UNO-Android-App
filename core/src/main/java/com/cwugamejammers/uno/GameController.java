//Beta Build: 5/3

package com.cwugamejammers.uno;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;

	private boolean reversed;
	private Node currentTurn;
	private ArrayList<Player> playerList = new ArrayList<Player>();

	public void run()
	{
		Node player1 = new Node(0);
		Node player2 = new Node(1);
		Node player3 = new Node(2);
		Node player4 = new Node(3);

		//Set connections
		player1.setPrev(player4);
		player1.setNext(player2);
		player2.setPrev(player1);
		player2.setNext(player3);
		player3.setPrev(player2);
		player3.setNext(player4);
		player4.setPrev(player3);
		player4.setNext(player1);

		reversed = false;
		//Default first player is player1
		Node currentTurn = player1;
		/**
		 * Basic idea of this block is, check if the curr node has getSkipped flag set as true, if it does, run a
		 * yet to be created function named nextTurn, which would change what currentTurn points to and move on.
		if(currentTurn.getNum().getIsSkipped() == True)
		{
			currentTurn.nextTurn();
		}
		 **/

		/**
		 * Basic idea is that at the end of the players turn, we check if the current card on the pile is a skip or
		 * reverse, and set flags accordingly

		//IF REVERSE
		 if(Card.playFieldView().getNumber() == 11)
		 {
			setIsReverse(true);
		 }

		 //IF SKIP
		 if(Card.playFieldView().getNumber() == 10)
		 {
			 currentTurn.getNext().getNum().setIsSkipped(true);
		 }
		 //IF +2
		 if(Card.playFieldView().getNumber() == 12)
		 {

		 	currentTurn.getNext().getNum().setIsSkipped(true);
		 }

		 //IF +4
		 if(Card.playFieldView().getNumber() == 14)
		 {
			currentTurn.getNext().getNum().setIsSkipped(true);
		 }
		 */



	}

	public void initialize()
	{
		Scanner scan = new Scanner(System.in);

		String name;

		// Consumes the \n
		scan.nextLine();
		while(true)
		{
			System.out.println("What is your name? 10 characters max");
			name = scan.nextLine();
			if (name.length() > 10 || name.length() <= 0)
				System.out.println("Name is invalid, try again");
			else
			{
				p1 = new Player(0, name);
				break;
			}

		}

		while(true)
		{
			System.out.println("What is your name? 10 characters max");
			name = scan.nextLine();
			if (name.length() > 10 || name.length() <= 0)
				System.out.println("Name is invalid, try again");
			else
			{
				p2 = new Player(1, name);
				break;
			}

		}

		while(true)
		{
			System.out.println("What is your name? 10 characters max");
			name = scan.nextLine();
			if (name.length() > 10 || name.length() <= 0)
				System.out.println("Name is invalid, try again");
			else
			{
				p3 = new Player(2, name);
				break;
			}

		}

		while(true)
		{
			System.out.println("What is your name? 10 characters max");
			name = scan.nextLine();
			if (name.length() > 10 || name.length() <= 0)
				System.out.println("Name is invalid, try again");
			else
			{
				p4 = new Player(3, name);
				break;
			}

		}

		scan.close();

		GameData data = new GameData();

		for (int i = 0; i < 7; i++)
		{
				Card.cardDraw(Card.getDeck(), p1);
				Card.cardDraw(Card.getDeck(), p2);
				Card.cardDraw(Card.getDeck(), p3);
				Card.cardDraw(Card.getDeck(), p4);
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

	public Node getCurrentTurn()
	{
		return currentTurn;
	}

	public void setCurrentTurn(Node nextPlayer)
	{
		currentTurn = nextPlayer;
	}
}
