package model;

import java.io.Serializable;

public class Player implements Serializable{
	private String username;
	private int totalPoints;
	
	public Player(String username) {
		this.username=username;
		this.totalPoints=0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints += totalPoints;
	}
}
