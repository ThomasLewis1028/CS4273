import java.util.ArrayList;
import java.util.Collections;

public class RobotMain {
	public static void main(String[] args) {
		ArrayList<Robot> robots = new ArrayList<>();
		ArrayList<Node> nodes = new ArrayList<>();

		nodes.add(new Node(1, 2, 2, true));
		nodes.add(new Node(2, 1, 5, false));
		nodes.add(new Node(3, 3, 7, true));
		nodes.add(new Node(4, 5, 9, false));
		nodes.add(new Node(5, 7, 3, true));
		nodes.add(new Node(6, 8, 1, true));
		nodes.add(new Node(7, 8, 5, true));
		nodes.add(new Node(8, 4, 6, false));
		nodes.add(new Node(9, 6, 8, false));
		nodes.add(new Node(10, 9, 7, false));

		int counter = 0;

		//Create initial 10
		for (int i = 0; i < 10; i++) {
			robots.add(new Robot());

			robots.get(i).setHome(0, 0);
			robots.get(i).setRedFeeder(10, 4);
			robots.get(i).setYellowFeeder(5, 10);

			ArrayList<Node> tempNodes = nodes;
			Collections.shuffle(tempNodes);

			for (int j = 0; j < 10; j++) {
				robots.get(i).in(new Node(tempNodes.get(j).getE(), tempNodes.get(j).getX(),
						tempNodes.get(j).getY(), tempNodes.get(j).isRed()));
			}
		}

		while (counter < 30000) {
			if (counter % 10000 == 0) {
				System.out.println("Loop " + (counter + 1));
				for (Robot r : robots) {
					r.printNodes();
					System.out.println(" - " + r.calcTotalDist());
				}

				System.out.println();
			}

			for (int i = 0; i < 10; i += 2) {
				Robot temp = robots.get(i).getFirstHalf();
				temp.addSecondHalf(robots.get(i + 1));
				temp.setHome(0, 0);
				temp.setRedFeeder(10, 4);
				temp.setYellowFeeder(5, 10);
				robots.add(temp);
			}

			Collections.sort(robots);

			for (int i = robots.size() - 1; i > 9; i--) {
				robots.remove(i);
			}

			Collections.shuffle(robots);
			counter++;
		}
	}
}