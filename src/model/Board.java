package model;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	//Parameters
	private int rws;
	private int clmns;
	private Node head;
	private int seeds;
	//Methods
	public Board(int rws, int clmns, int seeds) {
		this.rws=rws;
		this.clmns=clmns;
		this.seeds=seeds;
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
	
	
	public String print() {
		String msg="";
		String[] nds=new String[clmns];
		boolean front=true;
		Node nd=head;
		for(int i=0;i<rws;i++) {
			for(int j=0;j<clmns;j++) {
				if(nd.isSeed()) {
					nds[j]="[ ** ]\t";
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

	
//	public String print() {
//		String msg="";
//		String[] nds=new String[clmns];
//		boolean front=true;
//		Node nd=head;
//		do{
//			if(nd.isSeed()) {
//				msg+="[ * ]";
//			}else {
//				msg+="[ "+nd.getNum()+" ]";
//			}
//			nd=nd.getNext();
//		}while(nd.getNext()!=head.getNext());
//		return msg;
//	}
}
