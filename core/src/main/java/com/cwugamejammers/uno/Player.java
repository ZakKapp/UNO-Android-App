//Beta Build: 5/3

package com.cwugamejammers.uno;

public class Player {

	private int id;
	private String name;
	private Hand hand;

	private boolean isSkipped;

	public Player(int player_id, String player_name) {
		id = player_id;
		name = player_name;
		hand = new Hand();
		isSkipped = false;
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

	public boolean getIsSkipped()
	{
		return isSkipped;
	}

	public void setIsSkipped(boolean flag)
	{
		isSkipped = flag;
	}

}