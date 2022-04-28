package model;

public class Charactr {
	
	private String name;
	private Player plyr;
	private int seeds;
	private int totalTime;
	
	public Charactr(String name) {
		this.name=name;
		this.plyr=null;
		this.seeds=0;
		this.totalTime=0;
	}

	public void addSeed() {
		this.seeds++;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player getPlyr() {
		return plyr;
	}

	public void setPlyr(Player plyr) {
		this.plyr = plyr;
	}

	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	
}
