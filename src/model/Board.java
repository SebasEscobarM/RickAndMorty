package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;


public class Board {
	//Constant
	public final int PLYRS=2;
	//Parameters
	private PlayerData playrs;
	private int rws;
	private int clmns;
	private Node head;
	private Node rick;
	private Node morty;
	private boolean rckTurn;
	private int seeds;
	
	//Methods
	@SuppressWarnings("unchecked")
	public Board(int rws, int clmns, int seeds) {
		this.playrs=new PlayerData();
		this.rws=rws;
		this.clmns=clmns;
		this.setSeeds(seeds);
		this.rckTurn=true;
		ArrayList<Integer> sds=new ArrayList<>();
		Random rnd=new Random();
		for(int i=0;i<seeds;i++) {
			int ps=0;
			do {
				ps=rnd.nextInt(((clmns*rws)-1)+1)+1;
			}while(sds.contains(ps));
			sds.add(ps);
		}
		for(int i=1;i<=(rws*clmns);i++){
			add(new Node(i, sds.contains(i)));
		}
		
		ArrayList<Player> players= new ArrayList<>();
		try {
			FileInputStream fileIn = new FileInputStream(".\\data\\PlayerData.csv");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<Player> p;
			
			while(true) {
				
				p = (ArrayList<Player>)in.readObject();
				players.addAll(p);
				
			}
		}catch(EOFException e) {
		}catch(IOException e2) {
		}catch(ClassNotFoundException e3) {
		}
		
		playrs.setPlyrs(players);
		playrs.bubbleSort();
	}
	
	public void add(Node nd) {
		if(head==null) {
			nd.setNext(nd);
			nd.setPrev(nd);
			head=nd;
		}else {
			nd.setNext(head);
			nd.setPrev(head.getPrev());
			head.getPrev().setNext(nd);
			head.setPrev(nd);
		}
	}
	
	public void addPrtls(int n) {
		ArrayList<Integer> prtls=new ArrayList<>();
		Random rnd=new Random();
		for(int i=0;i<n*2;i++) {
			int m=0;
			do {
				m=rnd.nextInt(((clmns*rws)-1)+1)+1;
			}while(prtls.contains(m));
			prtls.add(m);
		}
		int j=0;
		for(int i=0;i<n*2;i+=2) {
			char ltr=(char) (65+j);
			Node a=search(prtls.get(i));
			Node b=search(prtls.get(i+1));
			a.setPrtlLtr(ltr);
			b.setPrtlLtr(ltr);
			a.setPortal(b);
			b.setPortal(a);
			j++;
		}
	}
	
	public void placeChrctrs(Charactr character) {
		int m=0;
		Random rnd=new Random();
		do {
			m=rnd.nextInt(((clmns*rws)-1)+1)+1;
		}while(search(m).isSeed());
		if(character.getName().equalsIgnoreCase("Rick")) {
			search(m).setRick(character);
			rick=search(m);
		}else if(character.getName().equalsIgnoreCase("Morty")){
			search(m).setMorty(character);
			morty=search(m);
		}
	}
	
	public Node search(int goal) {
		if(head==null) {
			return null;
		}else {
			return search(head, goal);
		}
	}
	
	public Node search(Node actNd, int goal) {
		if(actNd.getNum()==goal) {
			return actNd;
		}
		return search(actNd.getNext(), goal);
	}
	
	
	public String print() {
		String msg="";
		String[] nds=new String[clmns];
		boolean front=true;
		Node nd=head;
		for(int i=0;i<rws;i++) {
			for(int j=0;j<clmns;j++) {
				if(nd.isSeed()) {
					nds[j]="[ ** ]\t";
				}else if(nd.getMorty()!=null && nd.getRick()!=null) {
					nds[j]="[ RM ]\t";
				}else if(nd.getMorty()!=null){
					nds[j]="[  M ]\t";
				}else if(nd.getRick()!=null){
					nds[j]="[ R  ]\t";
				}else {
					if(nd.getNum()<10) {
						nds[j]="[ 0"+nd.getNum()+" ]\t";
					}else {
						nds[j]="[ "+nd.getNum()+" ]\t";
					}
				}
				nd=nd.getNext();
			}
			if(front) {
				for(String m:nds) {
					msg+=m;
				}
				front=false;
			}else {
				for(int j=clmns-1;j>=0;j--) {
					msg+=nds[j];
				}
				front=true;
			}
			msg+="\n";
		}
		return msg;
	}

	
	public String printPortals() {
		String msg="";
		String[] nds=new String[clmns];
		boolean front=true;
		Node nd=head;
		for(int i=0;i<rws;i++) {
			for(int j=0;j<clmns;j++) {
				if(nd.getPortal()!=null) {
					nds[j]="[  "+nd.getPrtlLtr()+" ]\t";
				}else {
					if(nd.getNum()<10) {
						nds[j]="[ 0"+nd.getNum()+" ]\t";
					}else {
						nds[j]="[ "+nd.getNum()+" ]\t";
					}
				}
				nd=nd.getNext();
			}
			if(front) {
				for(String m:nds) {
					msg+=m;
				}
				front=false;
			}else {
				for(int j=clmns-1;j>=0;j--) {
					msg+=nds[j];
				}
				front=true;
			}
			msg+="\n";
		}
		return msg;
	}

	public void setPlyr(String nm, String nickname) {
		Player player=new Player(nickname);
		if(nm.equals("Rick")) {
			rick.getRick().setPlyr(player);;
		}else if(nm.equals("Morty")) {
			morty.getMorty().setPlyr(player);;
		}
	}
	
	public void savePlayer(String winner) {
		
		if (winner.equalsIgnoreCase("R")) {
			if(playrs.search(rick.getRick().getPlyr().getUsername())!=null) {
				int idx=playrs.getPlyrs().indexOf(playrs.search(rick.getRick().getPlyr().getUsername()));
				rick.getRick().getPlyr().setTotalPoints(playrs.getPlyrs().get(idx).getTotalPoints());
				playrs.getPlyrs().set(idx, rick.getRick().getPlyr());
			}else {
				playrs.addPlyr(rick.getRick().getPlyr());
			}
		}
		else {
			if(playrs.search(morty.getMorty().getPlyr().getUsername())!=null) {
				int idx=playrs.getPlyrs().indexOf(playrs.search(morty.getMorty().getPlyr().getUsername()));
				morty.getMorty().getPlyr().setTotalPoints(playrs.getPlyrs().get(idx).getTotalPoints());
				playrs.getPlyrs().set(idx, morty.getMorty().getPlyr());
			}else {
				playrs.addPlyr(morty.getRick().getPlyr());
			}
		}
		ArrayList<Player> playerDataAL=playrs.getPlyrs();
		try {
			FileOutputStream fileOut = new FileOutputStream(".\\data\\PlayerData.csv");
			ObjectOutputStream out;
			out = new ObjectOutputStream(fileOut);
			out.reset();
			
			out.writeObject(playerDataAL);
			
			
			out.close();
			fileOut.close();	
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
	}
	
	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
	}

	public Node getRick() {
		return rick;
	}

	public void setRick(Node rick) {
		this.rick = rick;
	}

	public Node getMorty() {
		return morty;
	}

	public void setMorty(Node morty) {
		this.morty = morty;
	}
	
	public void minSeed() {
		this.seeds--;
	}

	public boolean isRckTurn() {
		return rckTurn;
	}

	public void setRckTurn(boolean rckTurn) {
		this.rckTurn = rckTurn;
	}

	public PlayerData getPlayrs() {
		return playrs;
	}

	public void setPlayrs(PlayerData playrs) {
		this.playrs = playrs;
	}
}
