/**
 * This class implements comparable to allow for a sorting method in the main class
 * This includes all the logic for inserting nodes and the logic for splitting them as well
 */

public class Robot implements Comparable<Robot> {
	private Node head = null;
	private Node tail;
	private Node home;
	private Node redFeeder;
	private Node yellowFeeder;
	private int size = 0;

	//Insert using passed in parameters, only used in this class because I was too lazy to change
	public void in(int e, int x, int y, boolean r) {
		if (head == null) {
			head = tail = new Node(e, x, y, r);
			head.setPos(0);
		} else if (searchElement(e))
			return;
		else {
			tail.setNext(tail = new Node(e, x, y, r));
			tail.setPos(tail.getPrevious().getPos() + 1);
		}

		size++;
	}

	//Insert based on a new node, mostly used for the other class
	public void in(Node n) {
		if (head == null) {
			head = tail = n;
			head.setPos(0);
		} else if (searchElement(n.getE()))
			return;
		else {
			tail.setNext(n);
			tail = n;
			tail.setPos(tail.getPrevious().getPos() + 1);
		}

		size++;
	}

	//Search for element in the array and return if it exists or not
	public boolean searchElement(int e) {
		Node temp = head;
		while (temp != null) {
			if (temp.getE() == e)
				return true;
			else temp = temp.getNext();
		}

		return false;
	}

	//Calculate the distance between two points
	public double calculateDistance(Node n, Node t) {
		return Math.sqrt(Math.pow(n.getX() - t.getX(), 2) + Math.pow(n.getY() - t.getY(), 2));
	}

	//Calculate the total distance between nodes calling calculateDistance for each node
	public double calcTotalDist() {
		double totalDistance = 0;
		if (head.isRed())
			totalDistance += calculateDistance(home, getRedFeeder());
		else
			totalDistance += calculateDistance(home, getYellowFeeder());

		Node temp = head;
		while (temp != tail) {

			if (temp.isRed())
				totalDistance += calculateDistance(temp, getRedFeeder());
			else
				totalDistance += calculateDistance(temp, getYellowFeeder());

			if (temp.getNext().isRed())
				totalDistance += calculateDistance(temp, getRedFeeder());
			else
				totalDistance += calculateDistance(temp, getYellowFeeder());

			temp = temp.getNext();
		}

		if (temp.isRed())
			totalDistance += calculateDistance(temp, getRedFeeder());
		else
			totalDistance += calculateDistance(temp, getYellowFeeder());

		totalDistance += calculateDistance(temp, home);

		return totalDistance;
	}

	//Not technically half but just get the first part of the array until point X
	public Robot getFirstHalf(int x) {
		Robot r = new Robot();
		Node temp = head;
		Node mid = getPoint(x);
		while (temp != mid.getNext()) {
			r.in(temp.getE(), temp.getX(), temp.getY(), temp.isRed());
			temp = temp.getNext();
		}

		return r;
	}

	//Not technically half but just get the last part of the array from point X
	public void addSecondHalf(Robot r, int x){
		Node n = r.getPoint(x).getNext();
		while (this.size != 10){
			if(!searchElement(n.getE()))
				this.in(n.getE(), n.getX(), n.getY(), n.isRed());

			if(r.getTail() != n)
				n = n.getNext();
			else
				n = r.getHead();
		}
	}

	//Get a point rather than the midpoint, this allows for random slicing to aide in random numbers
	public Node getPoint(int x) {
		Node temp = head;
		while (temp.getPos() != x) {
			temp = temp.getNext();
		}
		return temp;
	}

	//Print the nodes out in order
	public void printNodes() {
		Node temp = head;

		while (temp != null) {
			System.out.print(temp.getE() + " ");
			temp = temp.getNext();
		}
	}

	public int[] getNodes(){
		int arr[] = new int[10];
		Node temp = head;
		while(temp.getNext() != null){
			arr[temp.getPos()] = temp.getE();
			temp = temp.getNext();
		}

		return arr;
	}

	//Implemented class, honestly not 100% on how it works but it does so I used it.
	@Override
	public int compareTo(Robot robot) {
		return this.calcTotalDist() > robot.calcTotalDist() ? 1 :
				this.calcTotalDist() < robot.calcTotalDist() ? -1 : 0;
	}

	//Getters and setters, pretty straightforward
	public void setHome(int x, int y) {
		home = new Node(0, x, y, false);
	}

	public Node getHome() {
		return home;
	}

	public void setRedFeeder(int x, int y) {
		redFeeder = new Node(0, x, y, true);
	}

	public void setYellowFeeder(int x, int y) {
		yellowFeeder = new Node(0, x, y, false);
	}

	public Node getRedFeeder() {
		return redFeeder;
	}

	public Node getYellowFeeder() {
		return yellowFeeder;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
