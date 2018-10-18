/**
 * Basic node class to hold element and x/y.
 * Also includes a position to help determine slice points
 */

public class Node {
	private int e;
	private int x;
	private int y;
	private int pos;
	private boolean isRed;
	private Node next;
	private Node previous;

	public Node (int e, int x, int y, boolean r){
		this. e = e;
		this.x = x;
		this.y = y;
		this.isRed = r;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public boolean isRed() {
		return isRed;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
		next.setPrevious(this);
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}
}
