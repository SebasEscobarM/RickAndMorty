package model;

public class Node {
	
	//Relations
	private Node next;
	private Node prev;
	private Node portal;
	//Values
	private int num;
	private boolean seed;
	private Player rick;
	private Player morty;
	
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

	public Player getRick() {
		return rick;
	}

	public void setRick(Player rick) {
		this.rick = rick;
	}

	public Player getMorty() {
		return morty;
	}

	public void setMorty(Player morty) {
		this.morty = morty;
	}
	
	
}
