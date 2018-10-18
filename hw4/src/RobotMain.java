/**
 * Thomas Lewis
 * Dr. J Cecil
 * 10/17/2018
 * Homework 4
 * <p>
 * Run this code and it will automatically run for 300k iterations.
 * When I tested this using a ton of different variations it seemed to get stuck in one place.
 * I think that I could probably fix it by checking the actual lists against each other but that
 * created more overhead and I decided it wasn't worth it for now.
 * <p>
 * Tends to hit local minima around 8k loops
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class RobotMain {
	public static void main(String[] args) {
		//Create array lists for Robot class and nodes
		ArrayList<Robot> robots = new ArrayList<>();
		ArrayList<Node> nodes = new ArrayList<>();
		//This allows for different home and feeder locations
		Node redFeeder = new Node(0, 10, 4, true);
		Node yelFeeder = new Node(0, 5, 10, false);
		Node home = new Node(0, 0, 0, true);

		//Insert the nodes listed from the homework
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

		//Set counter for while loop later
		int counter = 1;

		//Create initial 10 parents
		for (int i = 0; i < 10; i++) {
			robots.add(new Robot());

			//Set the home and feeders
			robots.get(i).setHome(home.getX(), home.getY());
			robots.get(i).setRedFeeder(redFeeder.getX(), redFeeder.getY());
			robots.get(i).setYellowFeeder(yelFeeder.getX(), yelFeeder.getY());

			//Create a temp node list to shuffle
			ArrayList<Node> tempNodes = nodes;
			Collections.shuffle(tempNodes);

			//Iterate through and add the nodes based on the value not the reference (caused lots of issues before
			//I figured that out
			for (int j = 0; j < 10; j++)
				robots.get(i).in(new Node(tempNodes.get(j).getE(), tempNodes.get(j).getX(),
						tempNodes.get(j).getY(), tempNodes.get(j).isRed()));
		}

		//Go for 300k iterations, can be changed on a whim
		while (counter <= 300000) {

			//Output every 10k iterations, can be changed
			if (counter % 10000 == 0) {
				System.out.println("Loop " + (counter));
				for (Robot r : robots) {
					r.printNodes();
					System.out.println(" - " + r.calcTotalDist());
				}

				System.out.println();
			}

			int size = robots.size();
			//Create children based on two parents and add them to the robots list
			for (int i = 0; i < size; i += 2) {
				int x = ThreadLocalRandom.current().nextInt(1, 8);
				Robot temp = robots.get(i).getFirstHalf(x);
				temp.addSecondHalf(robots.get(i + 1), x);

				temp.setHome(home.getX(), home.getY());
				temp.setRedFeeder(redFeeder.getX(), redFeeder.getY());
				temp.setYellowFeeder(yelFeeder.getX(), yelFeeder.getY());

				robots.add(temp);
			}

			//Sort the list of robots based on the Comparator that uses the totalDistance
			Collections.sort(robots);

			//Remove the last 5 (largest) items from the list
			for (int i = robots.size() - 1; i > 9; i--) {
				robots.remove(i);
			}

			//Shuffle the list again to hope for totally random stuff
			Collections.shuffle(robots);
			counter++;
		}

		//Irrelevant but useful in testing
		return;
	}
}