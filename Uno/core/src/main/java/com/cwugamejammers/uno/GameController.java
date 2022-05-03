//Beta Build: 4/28

//package com.cwugamejammers.uno;

import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

	private int playerCount;
	private ArrayList<Player> playerList = new ArrayList<Player>();

	void run() {

	}

	void initialize() {
		Scanner scan = new Scanner(System.in);

		// User input for Valid number of players
		boolean isValid = false;
		while (!isValid) {

			try {
				System.out.print("How many players?");
				playerCount = scan.nextInt();
			} catch (Exception e) {
				System.out.println("Error");
				scan.nextLine();
			}
			if (playerCount > 1 && playerCount < 5) {
				isValid = true;
			}

		}

		System.out.println("The number of players is: " + playerCount);

		/**
		 * System.out.println("How many players are human?"); while(true) { int
		 * humanCount = scan.nextInt(); if(humanCount > playerCount)
		 * System.out.println("You have input too many human players, please try
		 * again"); else break; }
		 **/

		String name;

		// Consumes the \n
		scan.nextLine();

		for (int i = 0; i < playerCount; i++) {

			System.out.println("What is your name? 10 characters max");
			name = scan.nextLine();

			if (name.length() > 10 || name.length() <= 0)
				System.out.println("Name is invalid, try again");

			Player player = new Player(i, name);
			playerList.add(player);
		}

		scan.close();

		GameData data = new GameData();

		for (int i = 0; i < playerCount; i++) {
			for (int j = 0; j < 7; j++) {
				playerList.get(i).getHand().draw(data);
			}
		}

		System.out.println(data.getDeck().size());
	}

	public int getPlayerCount() {
		return playerCount;
	}
}
