/**
 * Created by Thomas on 9/12/2018.
 * Software Engineering Homework 3
 */

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class BookMain {
	public static void main(String[] args){
		//Create scanners arrays and booleans
		Scanner scanner = new Scanner(System.in);
		boolean notExit = true;
		ArrayList<Book> books = new ArrayList<>();
		String input;

		//Set up my books and IDs with availability dates and such
		Book progBook1 = new Book(1234, "Programming for Dummies", "Dr. Progman");
		books.add(progBook1);

		Book anotherBook = new Book(432551, "That Book You Don't Want", "Boringman Dullname", false, Instant.now().plus(56, ChronoUnit.DAYS));
		books.add(anotherBook);

		Book narnia = new Book(777777, "The Chronicles of Narnia", "C.S. Lewis", false, Instant.now().plus(9, ChronoUnit.DAYS));
		books.add(narnia);

		Book testBook4 = new Book(123456890, "Test Book Please Ignore", "Testy McTesterface");
		books.add(testBook4);

		Book bible = new Book(1, "Bible", "God");
		books.add(bible);

		books.add(new Book(123, "Title", "Author"));
		//Keep the program running till I want to exit
		while(notExit) {
			System.out.print("Are you searching, renting, or returning (\"Exit\" to exit)? ");
			input = scanner.nextLine();

			//Exit if the user wants
			if(input.toLowerCase().contains("exit"))
				notExit = false;

			//Searching criteria
			while(input.toLowerCase().contains("search")){
				System.out.print("Please enter the name of an author (use \"Rent\" to rent): ");
				input = scanner.nextLine();

				//Exit if needbe
				if(input.toLowerCase().contains("exit")){
					notExit = false;
					continue;
				}

				//Switch to rent if needbe
				if(input.toLowerCase().contains("rent")){
					continue;
				}

				//Search the books that contain a string and output the results
				for(Book book : books){
					if(book.getAuthorName().contains(input)){
						System.out.println("Title: " + book.getTitle()+ "\n" +
								"Book ID: " + book.getBookID() + "\n" +
								"Author: " + book.getAuthorName() + "\n" +
								"Available: " + (book.getAvailable() ? " Yes":" No") + "\n" +
								(book.getAvailable() ? "\n" : "Availability Date: " + book.getAvailabilityDate() + "\n"));
					}
				}

				//Keep user in loop
				input = "search";
			}

			//Exactly the same as above but change the availability to false with a date existing 5 days from now
			while(input.toLowerCase().contains("rent")) {
				System.out.print("Enter the book ID to rent for 5 days: ");
				input = scanner.nextLine();

				if(input.toLowerCase().contains("exit")){
					notExit = false;
					continue;
				}

				for(Book book : books){
					if(book.getBookID() == Integer.parseInt(input)){
						if(book.getAvailable()) {
							book.setAvailable(false);
							book.setAvailabilityDate(Instant.now().plus(5, ChronoUnit.DAYS));
							System.out.println("You rented " + book.getTitle() + " for 5 days, due " + book.getAvailabilityDate());
						}else
							System.out.println(book.getTitle() + " is not available until " + book.getAvailabilityDate());
					}
				}
			}

			//Exactly the same as above but set availability to true and the date to null
			while(input.toLowerCase().contains("return")){
				System.out.print("Enter the book ID to return: ");
				input = scanner.nextLine();

				if(input.toLowerCase().contains("exit")){
					notExit = false;
					continue;
				}

				for(Book book : books){
					if(book.getBookID() == Integer.parseInt(input)){
						book.setAvailable(true);
						book.setAvailabilityDate(null);
						System.out.println("You returned " + book.getTitle());
					}
				}
			}
		}
	}
}
