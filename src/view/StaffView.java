package view;

import java.util.Arrays;
import java.util.Date;

import controller.StaffListMovieController;
import controller.StaffController;
import static controller.InputOutputController.*;

import model.*;

/**
 * This View class displays the page for the staff, and takes in input from the
 * staff and passes them to the Controller class for processing.
 *
 */

public class StaffView {

	StaffController staff = new StaffController();
	StaffListMovieController stflist = new StaffListMovieController();

	/**
	 * This method displays the login page for the staff. This takes in 
	 * the username and password given by the staff.
	 *
	 */
	public void loginPage() {
		while (true) {
			printHeader("LOG IN");
			System.out.println("1 Login \n2 Quit");
			System.out.print("Enter Choice: ");
			int ch = readOption(1, 2);
			if (ch == 1) {
 				String username = readString("Enter username:");
 				//System.out.println(username);
				String password = readString("Enter password:");
				//char[] pass = System.console().readPassword("Enter password");
				//String password = new String(pass);
				if(!staff.loginCheck(username, password)) {
					System.out.println("Wrong username and password. Try again?");
					System.out.println("1 Yes 2 No");
					System.out.print("Enter Choice: ");
					int choice = readOption(1, 2);
					if (choice == 1)
						continue;
					else 
						return;
				}
				else
					generateDashes(30);
				System.out.println("Welcome, " + username + "!");
				generateDashes(30);
				this.mainPage();
			}
			else 
				return;
		}

	}

	/**
	 * This method displays the main page for the staff. The staff can perform the 
	 * following actions: Show the list of top 5 movies sorted by rating/sales, edit 
	 * the list of movies, edit the list of showtimes, and configure the settings.
	 *
	 */
	public void mainPage () {
		while(true) {
			printHeader("STAFF PAGE");
			System.out.println("1 Show ordered movie listing");
			System.out.println("2 Edit movie listing");
			System.out.println("3 Edit showtimes");
			System.out.println("4 Configure settings");
			System.out.println("5 Logout");
			System.out.print("Enter Choice: ");

			int choice = readOption(1, 5);

			switch (choice) {
			case 1:
				this.showMovies();
				break;
			case 2:
				this.editMovies();
				break;
			case 3:
				this.editShowtimes();
				break;
			case 4:
				this.confSettings();
				break;
			case 5:
				System.out.println("Logging out...");
				return;
			}
		}
	}

	/**
	 * This method displays the page to show the list of movies sorted by rating/sales
	 *  for the staff. The staff can perform the following actions: Show the list of 
	 *  top 5 movies sorted by rating or by sales
	 */
	public void showMovies () {
		int choice = 0;
		do {
			printHeader("SHOW MOVIES");
			System.out.println("1 Show movies sorted by rating");
			System.out.println("2 Show movies sorted by ticket sales");
			System.out.println("3 Back");
			System.out.print("Enter Choice: ");

			Movie[] list;
			choice = readOption(1, 3);
			stflist = new StaffListMovieController();

			switch (choice) {
			case 1:
				list = stflist.getTopMoviesByRating();
				System.out.printf( "%-2s %-25s %15s %n", "No", "Movie Title", "Rating");
				generateDashes(50);
				for (int i = 0; i < 5; i++) {
					System.out.printf( "%-2s %-25s %15s %n",(i+1), list[i].getTitle(), round(list[i].getRating(), 1));
				}
				break;
			case 2:
				list = stflist.getTopMoviesBySales();
				System.out.printf( "%-2s %-25s %15s %n", "No","Movie Title", "Sales");
				generateDashes(50);
				for (int i = 0; i < 5; i++) {
					System.out.printf( "%-2s %-25s %15s %n",(i+1), list[i].getTitle(), list[i].getSales());
				}
				break;
			case 3:
				return;

			}
		}while (choice != 3);
	}


	/**
	 * This method displays the page to make any changes with regards to the list of movies. 
	 * The staff can perform the following actions: Show the list of all movies, see a 
	 * a particular movie's information, update an existing movie, add a new movie, 
	 * remove an existing movie, or delete all "not showing" movies from the list of 
	 * movies.
	 */
	public void editMovies () {
		int choice = 0;
		do {
			printHeader("EDIT MOVIES");
			System.out.println("1 Show all movies");
			System.out.println("2 See a movie's information");
			System.out.println("3 Update an existing movie");
			System.out.println("4 Add a new movie");
			System.out.println("5 Remove an existing movie (change status to 'not showing')");
			System.out.println("6 Back");
			System.out.print("Enter Choice: ");

			choice = readOption(1, 6);
			Movie[] mov;
			switch (choice) {
			case 1:
				System.out.println("LIST OF ALL MOVIES");
				mov = staff.getMovies();
				System.out.printf( "%-2s %-25s %25s %n", "No" ,"Movie Title", "Current Status");
				generateDashes(60);
				for (int i = 0; i < mov.length; i++) {
					System.out.printf( "%-2s %-25s %25s %n", (i+1), mov[i].getTitle(), mov[i].getStatus());
					//	        			System.out.println((i+1) + ". " + mov[i].getTitle());
				}
				generateDashes(60);
				break;
			case 2:
				System.out.println("LIST OF ALL MOVIES");
				mov = staff.getMovies();
				System.out.printf( "%-2s %-25s %25s %n", "No" ,"Movie Title", "Current Status");
				generateDashes(60);
				for (int i = 0; i < mov.length; i++) {
					System.out.printf( "%-2s %-25s %25s %n", (i+1), mov[i].getTitle(), mov[i].getStatus());
					//	        			System.out.println((i+1) + ". " + mov[i].getTitle());
				}
				generateDashes(60);

				System.out.println("Enter the NUMBER of the movie (0 to return): ");
				int movInfo = (readOption(0, mov.length)-1);
				if (movInfo == -1)
					break;
				Movie mo = staff.getMovieByIndex(movInfo);
				//int movUpdate = (readInteger("Enter the NUMBER of movie to update: ")-1);
				System.out.println("Movie Info: "
						+ "\n1. Title: " + mo.getTitle()
						+ "\n2. Status: "+ mo.getStatus() 
						+ "\n3. Synopsis: " + mo.getSynopsis()
						+ "\n4. Duration: " + mo.getDuration()
						+ "\n5. Director: " +mo.getDirector()
						+ "\n6. Cast Members: " + Arrays.toString(mo.getCastName())
						+ "\n7. Age Restriction: " + mo.getAgeRestriction()
						+ "\n8. Base Price: " + mo.getBasePrice()
						+ "\n8. Overall Rating: " + mo.getRating()
						+ "\n8. Total Sales: " + mo.getSales());
				break;
			case 3:
				System.out.println("LIST OF ALL MOVIES");
				mov = staff.getMovies();
				System.out.printf( "%-2s %-25s %25s %n", "No" ,"Movie Title", "Current Status");
				generateDashes(60);
				for (int i = 0; i < mov.length; i++) {
					System.out.printf( "%-2s %-25s %25s %n", (i+1), mov[i].getTitle(), mov[i].getStatus());
					//	        			System.out.println((i+1) + ". " + mov[i].getTitle());
				}
				generateDashes(60);

				String [] newCast = new String[5];
				String newVal = null;

				System.out.println("Enter the NUMBER of movie to update (0 to return): ");
				int movUpdate = (readOption(0, mov.length)-1);
				if (movUpdate == -1)
					break;
				Movie m = staff.getMovieByIndex(movUpdate);
				//	            	int movUpdate = (readInteger("Enter the NUMBER of movie to update: ")-1);
				System.out.println("Enter attribute of movie to change: "
						+ "\n1. Title: " + m.getTitle()
						+ "\n2. Status: "+ m.getStatus() 
						+ "\n3. Synopsis: " + m.getSynopsis()
						+ "\n4. Duration: " + m.getDuration()
						+ "\n5. Director: " +m.getDirector()
						+ "\n6. Cast Members: " + Arrays.toString(m.getCastName())
						+ "\n7. Age Restriction: " + m.getAgeRestriction()
						+ "\n8. Base Price: " + m.getBasePrice());
				// String[] array_of_info = new String[7];
				String[] array_of_info = new String[]{"Title","Status","Synopsis","Duration","Director","Cast Members","Age Restriction","Base Price"}; 
				int ch = readOption(1, 8);
				if (ch == 6) {
					for (int i = 0; i < 5; i++) {
						newVal = readString("Enter cast member number " + (i+1));
						newCast[i] = newVal;

					}
				}
				else if (ch == 8) {
					newVal = readString("Enter the new value for base price: ");
					if (Float.parseFloat(newVal) != staff.getPriceBlockbuster() ||
							Float.parseFloat(newVal) != staff.getPriceNormal()) {
						System.out.println("Price must be " + staff.getPriceNormal() + " to set it as a "
								+ "Normal movie or " + staff.getPriceBlockbuster() + " to set it as a "
										+ "Blockbuster movie. Try again." );
						return;
					}
						
				}
				else {
					newVal = readString("Enter the new value for " +array_of_info[ch-1] +" : ");
				}
				switch(staff.updateOneMovie(movUpdate, ch, newVal, newCast)) {
				case 0:System.out.println("Update Successful!");;break;
				case 1:System.out.println("Update Unsuccessful. Try again");break;
				case 2:System.out.println("This movie already exists. Try again. ");break;
				case 3:System.out.println("Movie status not valid. Only enter (upcoming, currently showing, not showing). Try again. ");break;
				case 4:System.out.println("Age restriction not valid. Only enter (0, 13, 16, 18, 21). Try again. ");break;

				}
				break;
			case 4:
				float newPrice;
				String newTitle = readString("Enter new movie name (0 to return):");
				if (newTitle.contentEquals("0"))
					break;
				String newSynopsis = readString("Enter the synopsis:");
				int newDuration = readInteger("Enter the duration:");
				String newStatus = readString("Enter the movie status(upcoming, currently showing, not showing):");
				String newDirector = readString("Enter the director:");
				int newAge = readInteger("Enter the age restriction (0, 13, 16, 18, 21):");
				System.out.println("Enter the movie type (1 for Normal (set "
						+ "base price to "+staff.getPriceNormal()+"), 2 for Blockbuster "
								+ "(set base price to " + staff.getPriceBlockbuster() +")) :");
				int newP = readOption(1,2);
				if (newP == 1)
					newPrice = staff.getPriceNormal();
				else
					newPrice = staff.getPriceBlockbuster();
				String newCast1 = readString("Enter cast 1:");
				String newCast2 = readString("Enter cast 2:");
				String newCast3= readString("Enter cast 3:");
				String newCast4 = readString("Enter cast 4:");
				String newCast5 = readString("Enter cast 5:");

				switch(staff.addOneMovie(newTitle, newStatus, newDuration, newDirector, 0, newAge, 0f, newSynopsis, newPrice,
						newCast1, newCast2, newCast3, newCast4, newCast5)) {
						case 0:System.out.println("Movie added successfully!");;break;
						case 1:System.out.println("Failed to add. Try again.");break;
						case 2:System.out.println("This movie already exists. Try again. ");break;
						case 3:System.out.println("Movie status not valid. Only enter (upcoming, currently showing, not showing). Try again. ");break;
						case 4:System.out.println("Age restriction not valid. Only enter (0, 13, 16, 18, 21). Try again. ");break;

				}
				//	                if (staff.addOneMovie(newTitle, newStatus, newDuration, newDirector, 0, newAge, 0f, newSynopsis, newPrice,
				//	                		 newCast1, newCast2, newCast3, newCast4, newCast5)==0)
				//	                	System.out.println("Movie added successfully!");
				//	                else if (staff.addOneMovie(newTitle, "upcoming", newDuration, newDirector, 0, newAge, 0f, newSynopsis, 9.0f
				//	                		, newCast1, newCast2, newCast3, newCast4, newCast5)==1)
				//	                	System.out.println("Failed to add. Try again.");
				//	                else 
				//	                	System.out.println("This movie already exists. Try again. ");
				//	                break;
				break;
			case 5:
				System.out.println("LIST OF ALL MOVIES");
				mov = staff.getMovies();
				System.out.printf( "%-2s %-25s %25s %n", "No" ,"Movie Title", "Current Status");
				generateDashes(60);
				for (int i = 0; i < mov.length; i++) {
					System.out.printf( "%-2s %-25s %25s %n", (i+1), mov[i].getTitle(), mov[i].getStatus());
					//	        			System.out.println((i+1) + ". " + mov[i].getTitle());
				}
				generateDashes(60);
				System.out.println("Enter the NUMBER of movie to remove (0 to return): ");
				int movDel = (readOption(0, mov.length)-1);
				//	        		int movDel = (readInteger("Enter the NUMBER of movie to remove: ")-1);
				if (movDel == -1)
					break;
				if (staff.deleteOneMovie(movDel) == 0)
					System.out.println("Movie removed successfully!");
				else if (staff.deleteOneMovie(movDel) == 1)
					System.out.println("Failed to remove. Try again.");
				else if (staff.deleteOneMovie(movDel) == 2)
					System.out.println("The movie still has a showtime. Try again.");
				break;
//			case 6:
//				if (staff.deleteNotShowingMovie())
//					System.out.println("Deleted successfully!");
//				else
//					System.out.println("Failed to delete. Try again.");
//				break;
			case 6:
				return;

			}
		}while (choice != 6);
	}

	/**
	 * This method displays the page to make any changes with regards to the list of showtimes. 
	 * The staff can perform the following actions: Show the list of all showtimes, update an 
	 * existing showtime, add a new showtime, or remove an existing showtime.
	 * 
	 */
	public void editShowtimes () {
		int choice = 0;
		do {
			printHeader("EDIT SHOWTIMES");
			System.out.println("1 Show all available showtimes");
			System.out.println("2 Update an existing showtime");
			System.out.println("3 Add a new showtime");
			System.out.println("4 Remove an existing showtime");
			System.out.println("5 Back");

			choice = readOption(1, 5);
			ShowTime[] st;

			switch (choice) {
			case 1:
				System.out.println("LIST OF ALL SHOWTIMES");
				st = staff.getShowtimes();
				System.out.printf( "%-2s %-20s %-9s %25s %20s %n", 
						"No", "Cineplex" ,"Cinema ID", "Movie Name", "Date and Time");
				generateDashes(80);
				for (int i = 0; i < st.length; i++) {
					System.out.printf( "%-2s %-20s %-9s %25s %20s %n", 
							(i+1),st[i].getCineplexname(),st[i].getCinemaId(), st[i].getMoviename(), st[i].getMoviedatetimeStr());
					//	        			System.out.println((i+1) + ". " + mov[i].getTitle());
				}
				generateDashes(80);
				break;
			case 2:
				System.out.println("LIST OF ALL SHOWTIMES");
				st = staff.getShowtimes();
				System.out.printf( "%-2s %-20s %-9s %25s %20s %n", 
						"No", "Cineplex" ,"Cinema ID", "Movie Name", "Date and Time");
				generateDashes(80);
				for (int i = 0; i < st.length; i++) {
					System.out.printf( "%-2s %-20s %-9s %25s %20s %n", 
							(i+1),st[i].getCineplexname(),st[i].getCinemaId(), st[i].getMoviename(), st[i].getMoviedatetimeStr());
					//	        			System.out.println((i+1) + ". " + mov[i].getTitle());
				}
				generateDashes(80);
				String newVal = null;
				Date newTime;
				//	            	String cineplexName = readString("Enter the cineplex name: ");
				//	            	int cinemaId = readInteger("Enter the cinema ID: ");
				//	            	Date datetime = readTimeMMddkkmm("Enter the date and time (format:mm-dd hh-mm)");

				System.out.println("Enter the NUMBER of the showtime to update (0 to return): ");
				int stUpdate = (readOption(0, st.length)-1);
				if (stUpdate == -1) {
					break;
				}
				//	        		int stUpdate = (readInteger("Enter the NUMBER of the showtime to update: ")-1);
				System.out.println("Enter attribute to change (\n1. Cineplex Name \n2. Cinema ID"
						+ " \n3. Movie Name \n4. Date and Time ): ");
				int ch = readOption(1, 4);
				if (ch == 4) {
					newTime = readTimeMMddkkmm("Enter the new value (format: yyyy-MM-dd hh:mm): ");
					Date cur = new Date();
					if (cur.before(newTime))
						newVal = formatTimeMMddkkmm(newTime);
					else
						System.out.println("Date time has already passed. Try again.");
				}
				else
					newVal = readString("Enter the new value: ");
				switch(staff.updateOneShowtime(stUpdate, ch, newVal)) {
				case 0:System.out.println("Update successful!");;break;
				case 1:System.out.println("Update unsuccessful. Try again.");break;
				case 2:System.out.println("Cannot edit showtime as booking has been made. Try again. ");break;
				case 3:
					System.out.println("Cineplex not valid. Try again. ");
					System.out.println("Valid cineplexes are: ");
					Cineplex[] cin = staff.getCineplex();
					for (int i = 0; i < cin.length; i++) {
						System.out.println(cin[i].getCineplexname());
					}
					break;
				case 4:System.out.println("Cinema ID not found in Cineplex. Try again. ");
				System.out.println("Valid cinemas for this Cineplex are: ");
				Cinema[] c = staff.getCinema(stUpdate);
				int i = 0;
				while (c[i]!=null) {
					System.out.println(c[i].getCinemaid());
					i++;
				}
				break;
				case 5:System.out.println("Movie not currently showing. Try again. ");
				System.out.println("Movies currently showing are: ");
				Movie[] movie = staff.getShowingMovies();
				int j = 0;
				while (movie[j]!=null) {
					System.out.println(movie[j].getTitle());
					j++;
				}
				break;
				case 7:System.out.println("A movie is ongoing during this showtime. Try again.");break;
				}
				break;
				//	                if (staff.updateOneShowtime(stUpdate, ch, newVal)==0)
				//	                	System.out.println("Update successful!");
				//	                else if (staff.updateOneShowtime(stUpdate, ch, newVal)==1)
				//	                	System.out.println("Update unsuccessful. Try again.");
				//	                else if (staff.updateOneShowtime(stUpdate, ch, newVal)==2)
				//	                	System.out.println("Invalid input. Try again.");
				//	                break;
			case 3:
				String newCineplexname = readString("Enter the cineplex name (0 to return):");
				if (newCineplexname.contentEquals("0"))
					break;
				int newCinemaid = readInteger("Enter the cinema ID:");
				String newMoviename = readString("Enter the movie name:");
				Date newMoviedatetime = readTimeMMddkkmm("Enter the date and time (format:yyyy-MM-dd hh:mm):");
				//	            	int newRow = readInteger("Enter the number of rows:");
				//	            	int newCol = readInteger("Enter the number of columns:");

				//	            	StringBuilder arr = new StringBuilder();
				//	            	for (int i = 0; i < newRow * newCol; i++) {
				//	            		arr.append('0');
				//	            	}
				//	            	String str = arr.toString();
				switch(staff.addOneShowtime(newCineplexname, newCinemaid, newMoviename, newMoviedatetime)) {
				case 0:System.out.println("Showtime added successfully!");break;
				case 1:System.out.println("Failed to add. Try again.");break;
				case 2:
					System.out.println("Cineplex not valid. Try again. ");
					System.out.println("Valid cineplexes are: ");
					Cineplex[] cin = staff.getCineplex();
					for (int i = 0; i < cin.length; i++) {
						System.out.println(cin[i].getCineplexname());
					}
					break;
				case 3:
					System.out.println("Cinema ID not found in Cineplex. Try again. ");
					System.out.println("Valid cinemas for this Cineplex are: ");
					Cinema[] c = staff.getCinema(newCineplexname);
					int i = 0;
					while (c[i]!=null) {
						System.out.println(c[i].getCinemaid());
						i++;
					}
					break;
				case 4:System.out.println("Movie not currently showing. Try again. ");
				System.out.println("Movies currently showing are: ");
				Movie[] movie = staff.getShowingMovies();
				int j = 0;
				while (movie[j]!=null) {
					System.out.println(movie[j].getTitle());
					j++;
				}
				break;
				case 5:System.out.println("Showtime already exists. Try again. "); break;
				case 6:System.out.println("Date time has already passed. Try again."); break;
				case 7:System.out.println("A movie is ongoing during this showtime. Try again.");break;
				}
				break;
			case 4:
				System.out.println("LIST OF ALL SHOWTIMES");
				st = staff.getShowtimes();
				System.out.printf( "%-2s %-20s %-9s %25s %20s %n", 
						"No", "Cineplex" ,"Cinema ID", "Movie Name", "Date and Time");
				generateDashes(80);
				for (int i = 0; i < st.length; i++) {
					System.out.printf( "%-2s %-20s %-9s %25s %20s %n", 
							(i+1),st[i].getCineplexname(),st[i].getCinemaId(), st[i].getMoviename(), st[i].getMoviedatetimeStr());
					//	        			System.out.println((i+1) + ". " + mov[i].getTitle());
				}
				generateDashes(80);
				//	            	String cineplexName = readString("Enter the cineplex name: ");
				//	            	int cinemaId = readInteger("Enter the cinema ID: ");
				//	            	Date datetime = readTimeMMddkkmm("Enter the date and time (format:mm-dd hh-mm)");
				System.out.println("Enter the NUMBER of the showtime to remove (0 to return): ");
				int stDel = (readOption(0, st.length)-1);
				//	        		int stDel = (readInteger("Enter the NUMBER of the showtime to remove: ")-1);
				if (stDel == -1)
					break;
				if (staff.deleteOneShowtime(stDel) == 0)
					System.out.println("Showtime removed successfully!");
				else if (staff.deleteOneShowtime(stDel) == 1)
					System.out.println("Failed to remove. Try again.");
				else if (staff.deleteOneShowtime(stDel) == 2)
					System.out.println("This showtime has already been booked and cannot be removed. Try again.");
				break;
			case 5:
				return;

			}
		}while (choice != 5);

	}

	/**
	 * This method displays the page to make any changes with holidays or base price of
	 *  Blockbuster/Normal movie. The staff can perform the following actions: List all holidays,
	 *  add a new holiday, remove a holiday, or change the base price.
	 * 
	 */

	public void confSettings () {
		int choice = 0;
		do {
			printHeader("CONFIGURE SETTINGS");
			System.out.println("1 List all holidays");
			System.out.println("2 Add a new holiday");
			System.out.println("3 Remove a holiday");
			System.out.println("4 Show the current prices");
			System.out.println("5 Change the base price");
			System.out.println("6 Back");

			choice = readOption(1, 6);

			switch (choice) {
			case 1:
				Holiday[] hol = staff.getHolidays();
				System.out.printf( "%-25s %25s %n", "Holiday Name", "Date");
				generateDashes(50);
				for (int i = 0; i < hol.length; i++) {
					System.out.printf( "%-25s %25s %n", hol[i].getHolidayname(), hol[i].getDateStr());
				}
				break;
			case 2:
				String holName = readString("Enter new holiday (0 to return): ");
				if (holName.contentEquals("0"))
					break;
				//String date = readString("Enter the date: ");
				Date date_ = readTimeMMdd("Enter the date (format: yyyy-MM-dd): ");
				if (staff.addOneHoliday(holName, date_) ==0)
					System.out.println("Holiday added successfully!");
				else if(staff.addOneHoliday(holName, date_) ==1)
					System.out.println("Failed to add. Try again.");
				else
					System.out.println("This holiday is already listed. Try again. ");
				break;
			case 3:	         
				String holDel = readString("Enter holiday to delete (0 to return): ");
				if (holDel.contentEquals("0"))
					break;
				if (staff.deleteOneHoliday(holDel)==0)
					System.out.println("Holiday deleted successfully!");
				else if (staff.deleteOneHoliday(holDel)==1)
					System.out.println("Failed to delete. Try again.");
				else
					System.out.println("Holiday does not exist in the database. Try again.");
				break;
			case 4:
				System.out.println("Price for Normal movies: " + staff.getPriceNormal());
				System.out.println("Price for Blockbuster movies: " + staff.getPriceBlockbuster());
				break;
			case 5:
				System.out.println("1 Change base price for Normal movies \n"
						+ "2 Change base price for Blockbuster movies \n"
						+ "3 Back");
				int opt = readOption(1, 3);
				switch(opt) {
				case 1: 
					System.out.println("Current base price for Normal movies: " + staff.getPriceNormal());
					float newP = readInteger("Enter the new base price for Normal movies");
					int result = staff.changePrice(newP, 1);
					switch (result) {
					case 0: System.out.println("Price updated successfully!"); break;
					case 1:System.out.println("Price not updated successfully. Try again.");break;
					case 2: System.out.println("Price of Normal movies cannot be more than price of "
							+ "Blockbuster movies. Try again."); break;

				
					} break;
				case 2:
					System.out.println("Current base price for Blockbuster movies: " + staff.getPriceBlockbuster());
					float newPB = readInteger("Enter the new base price for Blockbuster movies");
					int result1 = staff.changePrice(newPB, 2);
					switch (result1) {
					case 0: System.out.println("Price updated successfully!"); break;
					case 1:System.out.println("Price not updated successfully. Try again.");break;
					case 2: System.out.println("Price of Normal movies cannot be more than price of "
							+ "Blockbuster movies. Try again."); break;
				} break;
				case 3:
					return;
			} break;
			case 6:
				return;
			}
		}while (choice != 6);


	}

}
