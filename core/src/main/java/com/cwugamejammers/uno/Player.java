//Beta Build: 4/28

//package com.cwugamejammers.uno;

public class Player {

	private int id;
	private String name;
	private Hand hand;

	public Player(int player_id, String player_name) {
		id = player_id;
		name = player_name;
		hand = new Hand();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

}