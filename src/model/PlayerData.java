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
	
	public void setPlyrs(ArrayList<Player> impPlyrs) {
		plyrs=impPlyrs;
	}
	
	public void addPlyr(Player player) {
		plyrs.add(player);
	}
	
	public void bubbleSort()
	{
	    for (int i = 0; i < plyrs.size(); i++) {
	    	for (int j = 1; j <plyrs.size()-i; j++) {
	    		if (plyrs.get(j).getTotalPoints() > plyrs.get(j-1).getTotalPoints()){
	    			Player temp = plyrs.get(j);
	    			plyrs.set(j, plyrs.get(j-1));
	    			plyrs.set(j-1, temp);
	    	   
	    		}
	    	}
	    }
	}
	
	public ArrayList<Player> getHighestPlayers(){
		ArrayList<Player> topPlayers=new ArrayList<>();
		int counter=0;
		for (int i=0;i<plyrs.size();i++) {
			if (counter>=5) {
				break;
			}
			topPlayers.add(plyrs.get(i));
			counter++;
			
		}
		return topPlayers;
	}
	
	public String toString(ArrayList<Player> plyrs) {
		String msg="";
		
		for (int i=0;i<plyrs.size();i++) {
			msg+=plyrs.get(i).getUsername()+"--"+ plyrs.get(i).getTotalPoints()+"\n";
		}
		return msg;
		
	}
	
}
