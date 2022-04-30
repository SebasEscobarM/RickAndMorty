package main;

import java.util.Random;
import java.util.Scanner;

import model.Node;
import model.Board;
import model.Charactr;

public class Main {
	public static Scanner rd;
	public static Board brd;
	public static void main(String[] args) {
		rd=new Scanner(System.in);
		System.out.println("Configuracion previa del juego:");
		preConfig();
		game();
	}
	
	public static void game(){
	
		do{
			turn(brd.getRick());
			if(brd.getSeeds()<=0) {
				break;
			}
			turn(brd.getMorty());
		}while(brd.getSeeds()>0);
		
		setScores();
		
		end();
	}
	
	public static void setScores() {
		brd.getRick().getRick().setPlayerTotalPoints();
		brd.getMorty().getMorty().setPlayerTotalPoints();
	}
	
	public static void end() {
		
		System.out.println("Puntajes"
				+ "\nRick:" + brd.getRick().getRick().getSeeds()
				+ "\nMorty: "+ brd.getMorty().getMorty().getSeeds());
		
		if(brd.getMorty().getMorty().getSeeds()>brd.getRick().getRick().getSeeds()) {
			System.out.println("El ganador es: Morty"
					+ "\nFelictiaciones "+ brd.getMorty().getMorty().getPlyr().getUsername()
					+ "\nGanaste "+ brd.getMorty().getMorty().getPlyr().getTotalPoints()+" puntos!");
			
		}
		else if(brd.getMorty().getMorty().getSeeds()<brd.getRick().getRick().getSeeds()) {
			System.out.println("El ganador es: Rick"
					+ "\nFelictiaciones "+ brd.getRick().getRick().getPlyr().getUsername()
					+ "\nGanaste "+ brd.getRick().getRick().getPlyr().getTotalPoints()+" puntos!");
		}
		else {
			System.out.println("EMPATE NO HAY GANADOR");
		}
		
	}
	
	public static void turn(Node ply) {
		long start =System.currentTimeMillis();
		boolean repeat=true;
		do {
			System.out.print("Es el turno de ");
			if(ply.getRick()!=null) {
				System.out.print(ply.getRick().getName());
			}else if(ply.getMorty()!=null){
				System.out.print(ply.getMorty().getName());
			}
			System.out.println("! ¿Que deseas hacer?");
			System.out.println("\t1. Tirar dado");
			System.out.println("\t2. Ver tablero");
			System.out.println("\t3. Ver portales");
			System.out.println("\t4. Marcador");
			int menu=0;
			boolean rptSltc=true;
			do {
				menu=Integer.parseInt(rd.nextLine());
				switch(menu) {
				case 1:
					repeat=false;
					rptSltc=false;
					launchDice(ply);
					break;
				case 2:
					rptSltc=false;
					System.out.println(brd.print());
					break;
				case 3:
					rptSltc=false;

					System.out.println(brd.printPortals());
					break;
				case 4:
					rptSltc=false;
					scores();
					break;
				default:
					rptSltc=true;
					System.out.println("Elija una opcion valida.");
				}
			}while(rptSltc);
		}while(repeat);
		int turnDuration= (int) ((System.currentTimeMillis()-start)/1000);
		System.out.println(turnDuration);
		brd.addPlayerTurnTime(ply, turnDuration);
		
	}
	
	public static void launchDice(Node nd) {
		Random rnd=new Random();
		int num=rnd.nextInt(5+1)+1;
		System.out.println(brd.print());
		System.out.println("Sacaste "+num+".");
		System.out.println("Desesas avanzar o retroceder:");
		System.out.println("\t1. Avanzar");
		System.out.println("\t2. Retroceder");
		boolean rpt=true;
		int opt=0;
		Charactr plyr=null;
		boolean rck=false;
		if(nd==brd.getRick()) {
			rck=true;
		}
		if(rck) {
			plyr=nd.getRick();
			nd.setRick(null);
		}else {
			plyr=nd.getMorty();
			nd.setMorty(null);
		}
		do {
			opt=Integer.parseInt(rd.nextLine());
			switch(opt) {
			case 1:
				rpt=false;
				if(rck) {
					for(int i=0;i<num;i++) {
						brd.setRick(brd.getRick().getNext());
					}
					brd.getRick().setRick(plyr);
				}else {
					for(int i=0;i<num;i++) {
					brd.setMorty(brd.getMorty().getNext());
					}
					brd.getMorty().setMorty(plyr);
				}
				break;
			case 2:
				rpt=false;
				if(rck) {
					for(int i=0;i<num;i++) {
						brd.setRick(brd.getRick().getPrev());
					}	
					brd.getRick().setRick(plyr);
				}else {
					for(int i=0;i<num;i++) {
					brd.setMorty(brd.getMorty().getPrev());
					}
					brd.getMorty().setMorty(plyr);
				}
				break;
			default:
				System.out.println("Elija una opcion valida.");
				rpt=true;
			}
		}while(rpt);
		
		if(rck) {
			if(brd.getRick().isSeed()) {
				brd.getRick().setSeed(false);
				brd.getRick().getRick().addSeed();
				brd.minSeed();
			}
			if(brd.getRick().getPortal()!=null) {
				brd.getRick().getPortal().setRick(plyr);
				brd.getRick().setRick(null);
				brd.setRick(brd.getRick().getPortal());
			}
			if(brd.getRick().isSeed()) {
				brd.getRick().setSeed(false);
				brd.getRick().getRick().addSeed();
				brd.minSeed();
			}
		}else {
			if(brd.getMorty().isSeed()) {
				brd.getMorty().setSeed(false);
				brd.getMorty().getMorty().addSeed();
				brd.minSeed();
			}
			if(brd.getMorty().getPortal()!=null) {
				brd.getMorty().getPortal().setMorty(plyr);
				brd.getMorty().setMorty(null);
				brd.setMorty(brd.getMorty().getPortal());
			}
			if(brd.getMorty().isSeed()) {
				brd.getMorty().setSeed(false);
				brd.getMorty().getMorty().addSeed();
				brd.minSeed();
			}
		}
	}
	
	public static void scores() {
		System.out.println("Rick: "+brd.getRick().getRick().getSeeds()+" semillas.");
		System.out.println("Morty: "+brd.getMorty().getMorty().getSeeds()+" semillas.");
	}
	
	public static void preConfig() {
		boolean rept=false;
		int clmns=0, rws=0, seeds=0, prtl=0;
		do{
			rept=false;
			System.out.println("Ingrese el numero de columnas del tablero:");
			clmns=Integer.parseInt(rd.nextLine());
			System.out.println("Ingrese el numero de filas del tablero:");
			rws=Integer.parseInt(rd.nextLine());
			System.out.println("Ingrese el numero de semillas a posicionar en el tablero:");
			seeds=Integer.parseInt(rd.nextLine());
			if(seeds>((clmns*rws)-1)) {
				//Limpiar pantalla.
				System.out.println("El numero de semillas no puede ser mayor al numero de casillas en el tablero, configure nuevamente.");
				rept=true;
			}
		}while(rept);
		brd=new Board(rws, clmns, seeds);
		System.out.println(brd.print());
		
		rept=false;
		do {
			rept=false;
			System.out.println("Ingrese la cantidad de portales a generar:");
			prtl=Integer.parseInt(rd.nextLine());
			if(prtl>((clmns*rws)/2)) {
				//Limpiar pantalla.
				System.out.println("El numero de portales no puede ser mayor a la mitad de casillas del tablero.");
				rept=true;
			}
		}while(rept);
		brd.addPrtls(prtl);
		System.out.println(brd.printPortals());
		
		brd.placeChrctrs(new Charactr("Rick"));
		brd.placeChrctrs(new Charactr("Morty"));
		
		System.out.println("Ingrese el nickname de quien jugara como Rick:");
		System.out.println("Si el nickname no existe se creara como nuevo jugador.");
		String rck=rd.nextLine();
		brd.setPlyr("Rick", rck);
		System.out.println("Ingrese el nickname de quien jugara como Morty:");
		System.out.println("Si el nickname no existe se creara como nuevo jugador.");
		String mrt=rd.nextLine();
		brd.setPlyr("Morty", mrt);
		System.out.println(brd.print());
	}
}
