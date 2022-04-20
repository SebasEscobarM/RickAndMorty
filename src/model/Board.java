package model;

public class Board {
	//Parameters
	private int rws;
	private int clmns;
	private Node head;
	private Node tail;
	//Methods
	public Board(int rws, int clmns) {
		this.rws=rws;
		this.clmns=clmns;
		for(int i=1;i<=(rws*clmns);i++){
			add(new Node(i));
		}
	}
	
	public void add(Node nd) {
		if(head==null && tail==null) {
			nd.setNext(nd);
			nd.setPrev(nd);
			head=nd;
			tail=nd;
		}else {
			tail.setNext(nd);
			nd.setNext(head);
			nd.setPrev(tail);
			head.setPrev(nd);
			tail=nd;
		}
	}
	
	
//	public String print() {
//		String msg="";
//		boolean front=true;
//		for(int i=0;i<rws;i++) {
//			if(front) {
//				for(int j=1;j<7;j++) {
//					
//				}
//			}
//		}
//		return msg;
//	}
}
