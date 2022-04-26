package model;

import java.util.ArrayList;

public class PlayerData {
	private ArrayList<Player> plyrs=new ArrayList<>();
	
	public Player search(String nickname) {
		for(int i=0;i<plyrs.size();i++) {
			if(plyrs.get(i).getUsername().equals(nickname)) {
				return plyrs.get(i);
			}
		}
		return null;
	}
	public ArrayList<Player> getPlyrs(){
		return plyrs;
	}
	
}
