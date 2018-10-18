public class Robot implements Comparable<Robot> {
	private Node head = null;
	private Node tail;
	private Node home;
	private Node redFeeder;
	private Node yellowFeeder;
	private int size = 0;

	public void in(int e, int x, int y, boolean r) {
		if (head == null) {
			head = tail = new Node(e, x, y, r);
			head.setPos(0);
		} else if (searchElement(e))
			System.out.println("Element already exists");
		else {
			tail.setNext(tail = new Node(e, x, y, r));
			tail.setPos(tail.getPrevious().getPos() + 1);
		}

		size++;
	}

	public void in(Node n) {
		if (head == null) {
			head = tail = n;
			head.setPos(0);
		}else {
			tail.setNext(n);
			tail = n;
			tail.setPos(tail.getPrevious().getPos() + 1);
		}


		size++;
	}

	public boolean searchElement(int e) {
		Node temp = head;
		while (temp != null) {
			if (temp.getE() == e)
				return true;
			else temp = temp.getNext();
		}

		return false;
	}

	public double calculateDistance(Node n, Node t) {
		return Math.sqrt(Math.pow(n.getX() - t.getX(), 2) + Math.pow(n.getY() - t.getY(), 2));
	}

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

	public Robot getFirstHalf() {
		Robot r = new Robot();
		Node temp = head;
		Node mid = getMidpoint();
		while (temp != mid.getNext()) {
			r.in(temp.getE(), temp.getX(), temp.getY(), temp.isRed());
			temp = temp.getNext();
		}

		return r;
	}

	public void addSecondHalf(Robot r){
		Node n = r.getMidpoint().getNext();
		while (this.size != 10){
			if(!searchElement(n.getE()))
				this.in(n.getE(), n.getX(), n.getY(), n.isRed());

			if(r.getTail() != n)
				n = n.getNext();
			else
				n = r.getHead();
		}
	}

	public Node getMidpoint() {
		Node temp = head;
		while (temp.getPos() != 4) {
			temp = temp.getNext();
		}
		return temp;
	}



	public void printNodes() {
		Node temp = head;

		while (temp != null) {
			System.out.print(temp.getE() + " ");
			temp = temp.getNext();
		}
	}

	@Override
	public int compareTo(Robot robot) {
		return this.calcTotalDist() > robot.calcTotalDist() ? 1 :
				this.calcTotalDist() < robot.calcTotalDist() ? -1 : 0;
	}

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
