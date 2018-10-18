import java.time.Instant;
import java.util.Date;

/**
 * Created by thoma on 9/12/2018.
 */
public class Book {
	private String authorName;
	private int bookID;
	private String title;
	private boolean available;
	private Instant availabilityDate;

	public Book(int bookID, String title, String authorName){
		this.authorName = authorName;
		this.bookID = bookID;
		this.title = title;
		this.available = true;
	}

	public Book(int bookID, String title, String authorName, boolean available, Instant availabilityDate){
		this.authorName = authorName;
		this.bookID = bookID;
		this.title = title;
		this.available = available;
		this.availabilityDate = availabilityDate;
	}

	public String getAuthorName() { return authorName; }
	public int getBookID() { return bookID; }
	public String getTitle() { return title; }
	public boolean getAvailable() { return available; }
	public Instant getAvailabilityDate() { return availabilityDate; }

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setAvailabilityDate(Instant availabilityDate) {
		this.availabilityDate = availabilityDate;
	}
}
