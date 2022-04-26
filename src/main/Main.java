package main;

import java.util.Scanner;

import model.Board;
import model.Charactr;

public class Main {
	public static Scanner rd;
	public static Board brd;
	public static void main(String[] args) {
		rd=new Scanner(System.in);
		System.out.println("Configuracion previa del juego:");
		preConfig();
		
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
