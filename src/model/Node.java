package model;

public class Node {
	
	//Relations
	private Node next;
	private Node prev;
	private Node portal;
	private char prtlLtr;
	//Values
	private int num;
	private boolean seed;
	private Charactr rick;
	private Charactr morty;
	
	public Node(int num, boolean seed) {
		this.num=num;
		this.seed=seed;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getPortal() {
		return portal;
	}

	public void setPortal(Node portal) {
		this.portal = portal;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isSeed() {
		return seed;
	}

	public void setSeed(boolean seed) {
		this.seed = seed;
	}

	public Charactr getRick() {
		return rick;
	}

	public void setRick(Charactr rick) {
		this.rick = rick;
	}

	public Charactr getMorty() {
		return morty;
	}

	public void setMorty(Charactr morty) {
		this.morty = morty;
	}

	public char getPrtlLtr() {
		return prtlLtr;
	}

	public void setPrtlLtr(char prtlLtr) {
		this.prtlLtr = prtlLtr;
	}
	
	
}
