package model;

public class Charactr {
	
	private String name;
	private Player plyr;
	private int seeds;
	private Long totalTime;
	
	public Charactr(String name) {
		this.name=name;
		this.plyr=null;
		this.seeds=0;
		this.totalTime=(long) 0;
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

	public Long getTotalTime() {
		return totalTime;
	}

	public void addTotalTime(Long turnDuration) {
		this.totalTime += turnDuration;
	}
	
	public void setPlayerTotalPoints() {
		int points= seeds*120;
		points-=totalTime;
		plyr.setTotalPoints(points);
	}
	
	
}
