package view;
import java.io.IOException;
import java.text.SimpleDateFormat;

import model.*;
import java.util.*;
import controller.*;

import static controller.InputOutputController.*;

/**
 * This View class displays the page for the moviegoer, and takes in input from the
 * moviegoer and passes them to the Controller class for processing.
 *
 */

public class MoviegoerView {
	/**
	 * @throws IOException
	 * This method displays the main page for the moviegoer.
	 * Movie Goer User Interface (main page) to perform following actions:
	 * Search/list movies,
	 * view booking history,
	 * book ticket,
	 * show available seats,
	 * give review/rating,
	 * view rating/review for movies,
	 * logging out
	 * some options will go into other mainPages under this main page
	 * Creating objects from MoviegoerListMovieController, Moviegoersearchcontroller and MovieBookingController to be used within the mainPage
	 */
	public void mainPage () throws IOException {
		Scanner sc = new Scanner(System.in);
		
		MoviegoerListMovieController mgv = new MoviegoerListMovieController();
		Moviegoersearchcontroller msc = new Moviegoersearchcontroller();
		MovieBookingController mbc2 = new MovieBookingController();
		while(true) {
			printHeader("MOVIEGOER PAGE");
			System.out.println("1 Search movies/list movies");
			System.out.println("2 View booking history");
			System.out.println("3 Book ticket");
			System.out.println("4 Show available seats");
			System.out.println("5 Give review/rating");
			System.out.println("6 View rating/review movies");
			System.out.println("7 Logout");
			System.out.print("Enter Choice: ");
			int choice = readOption(1, 7);

	        switch (choice) {
	            case 1:
	            	this.mainPage2();
	                break;
	            case 2:
	            	printHeader("VIEW BOOKING HISTORY");
	            	int ret = 0;
	            	String emailAddress;
	            	System.out.println("Enter email address here, or press");
	            	System.out.println("1 to return");
	            	System.out.print("Enter Choice: ");
//	            	String emailAddress = sc.next();
	            	
//	            	
	            	emailAddress = sc.next();
//	            	
	            	try {
	            		   ret = Integer.parseInt(emailAddress);
	            		}
	            		catch (NumberFormatException e)
	            		{
	            		   ret =0;
	            		}
	            	if (ret ==1) {
	            		this.mainPage();
	            	}
	            	mgv.viewBookingHistory(emailAddress);
	                break;
	            case 3:
	            	while(true) {
	            		this.mainPage3();
	            		break;
	        	    }
	        	    break;
	            case 4:
	            	while(true) {
	            		this.showAvailableSeats();
	            		break;
	                }
	                break;
	            case 5:
	            	String email = readEmail("please insert your email");
	            	String movie_name = readString("please insert the movie you want to give review/rating");
	            	mgv.insertReviewRating(email, movie_name);
	            	
	                break;
	            case 6:
	            	String movieTitle = readString("Please input the movie name");
	            	msc.ShowReviewRating(movieTitle);
	            	break;
	            case 7:
	            	System.out.println("Logging out...");
	                return;
	        }
		}
	}

	/**
	 * @throws IOException
	 * This method displays the second main page for the moviegoer.
	 * Movie Goer User Interface (main page 2) to perform following actions:
	 * Search movies,
	 * view movie by rating,
	 * view movie by sales,
	 * return back to main page,
	 * Creating objects from MoviegoerListMovieController and Moviegoersearchcontroller to be used within the mainPage2
	 */
	public void mainPage2 () throws IOException {
		
		MoviegoerListMovieController mgv = new MoviegoerListMovieController();
		Moviegoersearchcontroller a = new Moviegoersearchcontroller();
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			printHeader("SEARCH MOVIES/LIST MOVIES");
			System.out.println("1 Search movies");
			System.out.println("2 View movies by rating");
			System.out.println("3 View movies by sales");
			System.out.println("4 Return");
			System.out.print("Enter Choice: ");

			int choice = readOption(1, 4);

			switch (choice) {
			case 1:
				a.SearchMovie();
				break;
			case 2:
				Movie[] mv = mgv.getTopMoviesByRating();
				System.out.printf( "%-2s %-35s %15s %n", "No", "Movie Title", "Rating");
				System.out.println();
				
				
				for (int i = 0;i<5;i++) {
					int rank = i+1;
					System.out.printf( "%-2s %-35s %15s %n",rank, mv[i].getTitle(), mgv.round(mv[i].getRating(), 1));
				}
				
				break;
			case 3:
				Movie[] mv2 = mgv.getTopMoviesBySales();
				System.out.printf( "%-2s %-35s %15s %n", "No", "Movie Title", "Sales");
				for (int i = 0;i<5;i++) {
					int rank = i+1;
					System.out.printf( "%-1s %-35s %15s %n", rank, mv2[i].getTitle(), mv2[i].getSales());
				}
				break;
			case 4:
				System.out.println("Returning...");
				return;
			}
		}
	}
	/**
	 * This method displays the third main page for the moviegoer.
	 * Movie Goer User Interface (main page 3) to perform following actions:
	 * search by movies,
	 * search by cineplex,
	 * going back to mainpage.
	 * Creating objects from MovieBookingController and Moviegoersearchcontroller to be used within the mainPage3
	 */
	public void mainPage3() {
		Scanner sc = new Scanner(System.in);
		
		MovieBookingController booking_cont = new MovieBookingController();
		Moviegoersearchcontroller searching = new Moviegoersearchcontroller();
		
		printHeader("SEARCH MOVIES/LIST MOVIES");
		System.out.println("1 Search by movies");
		System.out.println("2 Search by cineplex");
		System.out.println("3 Go Back");
		System.out.print("Enter Choice: ");
		int choice = readOption(1, 3);
		
        switch (choice) {
        	case 3:
        		System.out.println("Returning...");
        		return;
            case 1:
            	Movie result_movie = searching.movieSearchBooking();
            	if(result_movie.getTitle() == null) {
            		System.out.println("Movie Not Found!");
            		return;
            	}
            	else {
            		String movie_title = result_movie.getTitle();
            		
            		ShowTime[] show_times = searching.getShowTimesMovie(movie_title);
            		if(show_times.length == 0) {
            			System.out.println("No show times found");
            			return;
            		}
            		else {
            			printHeader(movie_title);
            			System.out.printf( "%-1s %-35s %-25s %-35s %-25s %-45s %n", "","Movie Name","Cineplex Name" ,"Showtime", "Type of cinema class", "Miniumum age to watch");
            			for(int i = 1; i <= show_times.length ; i++) {
            				System.out.printf( "%-1s %-35s %-25s %-35s %-25s %-40s %n", i, show_times[i-1].getMoviename(), show_times[i-1].getCineplexname(), show_times[i-1].getMoviedatetime(),booking_cont.returnTypeCinema(show_times[i-1].getCineplexname(),String.valueOf(show_times[i-1].getCinemaId())),booking_cont.returnAgeNeeded(show_times[i-1].getMoviename()));
            				
            			}
            			
            		}
            		System.out.println("Select the showtime you would like to book!");
            		// get the index
            		int show_time_index = readOption(1, show_times.length);
            		movie_title = show_times[show_time_index-1].getMoviename();
            		System.out.println("Booking");
            		System.out.println("Movie: "+ movie_title+ "\nCineplex name: "+show_times[show_time_index-1].getCineplexname()+ " Showtime: "+show_times[show_time_index-1].getMoviedatetime());
            		System.out.println("1 Confirm");
            		System.out.println("2 Back");
            		System.out.print("Enter Choice: ");
            		choice = readOption(1, 2);
            		if(choice == 2) {
            			return;
            		} else if(choice == 1) {
            			boolean result_insert = insertBooking(show_times[show_time_index-1]);
            			if(result_insert == true){
            				System.out.println("Booking Successful");
            			}
            			else{
            				System.out.println("Booking Unsuccessful");
            			}
	            		return;
            		}
            		
            	}
                // break;
            case 2:
            	ShowTime[] show_times = searching.cineplexSearchBooking();
            	if(show_times.length == 0) {
        			System.out.println("No show times found");
        		}
            	else {
            		// very hard to print header for this one because the cineplex name is lost.
            		System.out.printf( "%-2s %-35s %-35s %-25s %-45s %n", "","Movie" ,"Showtime", "Type of cinema class", "Miniumum age to watch");
            		
            		for(int i = 1; i <= show_times.length ; i++) {
            			System.out.printf( "%-2s %-35s %-35s %-25s %-40s %n", i, show_times[i-1].getMoviename(), show_times[i-1].getMoviedatetime(),booking_cont.returnTypeCinema(show_times[i-1].getCineplexname(),String.valueOf(show_times[i-1].getCinemaId())),booking_cont.returnAgeNeeded(show_times[i-1].getMoviename()));
            		}
        			System.out.println("Select the showtime you would like to book!");
            		// get the index
            		int show_time_index = readOption(1, show_times.length);
            		System.out.println("Booking");
            		System.out.println("Movie: "+ show_times[show_time_index-1].getMoviename()+ "\nCineplex name: "+show_times[show_time_index-1].getCineplexname()+ " Showtime: "+show_times[show_time_index-1].getMoviedatetime());
            		System.out.println("1 Confirm");
            		System.out.println("2 Back");
            		System.out.print("Enter Choice: ");
            		choice = readOption(1, 2);
            		if(choice == 1){
            			boolean result_insert = insertBooking(show_times[show_time_index-1]);
            			if(result_insert == true){
            				System.out.println("Booking Successful");
            			}
            			else{
            				System.out.println("Booking Unsuccessful");
            			}
            		}
            		
        		}
                return;
        }
	
		
		sc.close();
	}
	/**
	 * This method inserts booking based on the showtime selected by the moviegoer.
	 * Movie goer booking the ticket will need to input his/her 
	 * name, email, mobile number, number of ticket to purchase, age of person viewing the movie, seat of the cinema in that particular showtime
	 * final price for the person going to the movie will be calculated and displayed before user chooses his/her seat (after inputing the age)
	 * A 2-D array with "O"s (not occupied) and "X"s (occupied) will printed for the showtime user selected
	 * row and column number and screen will be displayed when selecting the seats too
	 * movie goer can choose to book tickets halfway and decide not to book, if everything is decided before making the payment
	 * @param show_times showtime given
	 * @return true if book successfully, false if not
	 */
	public boolean insertBooking(ShowTime show_times){
		Scanner sc = new Scanner(System.in);
		
		String cineplex_name_sel = show_times.getCineplexname();
		Date show_time_sel = show_times.getMoviedatetime();
		int cinema_id_sel = (show_times.getCinemaId());
		String movie_title = show_times.getMoviename();
		System.out.println(movie_title);
		System.out.println("Cineplex name: "+cineplex_name_sel+ " Showtime: "+ show_time_sel);
		System.out.println("Enter Name:");
		String name = sc.nextLine();
		String email = readEmail("Enter Email:");
		System.out.println("Enter Mobile Number");
		String phone_number = sc.nextLine();
//		String email = sc.nextLine();
		System.out.println("Enter Number of Tickets");

		int choice_number_ticks = readOption(1, 10);

		MovieBookingController movBookContr = new MovieBookingController();
		int[][] seatingArrg = movBookContr.showAvailableSeats(show_time_sel,cineplex_name_sel, cinema_id_sel);
		Booking[] booking_to_insert = new Booking[choice_number_ticks];
		boolean insert_into = true;
		String tid_print;
		Date now = new Date();
		SimpleDateFormat ticketFormat = new SimpleDateFormat("yyyyMMddkkmm");
		for(int i = 0; i < choice_number_ticks; i++) {
			movBookContr = new MovieBookingController();
			// int[][] seatingArrg = movBookContr.showAvailableSeats(show_time_sel,cineplex_name_sel, cinema_id_sel);
			
			int number = i + 1;
			while(true) {
				int some = 1;
				System.out.println("Please enter the age for person "+ number );
				int age = readOption(1, 99);
				double price = movBookContr.checkPrice(movie_title, show_time_sel,cineplex_name_sel, Integer.toString(cinema_id_sel), age);
				price = Math.round(price*100.0)/100.0;
				System.out.println("Price for this ticket: $"+price+"0");
				
				System.out.print("    ");
				int aile = seatingArrg.length/2;
				for(int k = 0; k < seatingArrg.length; k++) {
					if (aile == k)
						System.out.print("  ");
					System.out.print(some + " ");
					some++;								
				}
				System.out.println("");
				System.out.print(" ");
				for(int k = 0; k < seatingArrg.length; k++) {
					if (aile != k)
						System.out.print("  ");
					else
						System.out.print("Screen");
				}
				System.out.println("");
				for(int k = 0; k<seatingArrg.length; k++) {
					int a = k +1;
					System.out.print( a + "|" +  "  ");
					for(int l = 0; l < seatingArrg[1].length ; l++ ) {
						if (aile == l)
							System.out.print("  ");
						if(seatingArrg[k][l] == 0) {
							System.out.print("O ");
						}
						else {
							System.out.print("X ");
						}
					}
					System.out.print(" " +  "|");
					System.out.println("");
				}
//				System.out.print("  ");
//				for(int k = 0; k < seatingArrg.length; k++) {
//					System.out.print(some + " ");
//					some++;
//				}
//				System.out.println("");
//				for(int k = 0; k<seatingArrg.length; k++) {
//					int a = k +1;
//					System.out.print( a + " ");
//					for(int l = 0; l < seatingArrg[1].length ; l++ ) {
//						if(seatingArrg[k][l] == 0) {
//							System.out.print("O ");
//						}
//						else {
//							System.out.print("X ");
//						}
//					}
//					System.out.println("");
//				}
				
				
				System.out.println("Please enter the row (horizontal) number");
				int row = readOption(1, seatingArrg.length);
				System.out.println("Please enter the column (vertical) number");
				int column = readOption(1, seatingArrg.length);
				if (seatingArrg[row-1][column-1] == 1) {
					System.out.println("Seat is already occupied!");
					System.out.println("Choose another seat");
					continue;
				}
					
				
			
				//KENNY LEW
//				String tId = "201911121841JGV";
				String tid = this.getTid(cineplex_name_sel,now);
				// tries to insert into db, bookTicket() returns Booking object if its successful
				Booking result_insert = movBookContr.bookTicket(name, email, show_time_sel, cineplex_name_sel, cinema_id_sel, movie_title, row, column, price, age,tid);
				if(result_insert.getPrice() == 1.0) {
					System.out.println("Seat is already occupied!");
					System.out.println("Choose another seat");
					continue;
				}
				else if (result_insert.getPrice() == 2.0){
					System.out.println("Person is underage");
					System.out.println("Do you still want to continue booking?");
					System.out.println("1 Yes");
					System.out.println("2 No");
					System.out.print("Enter Choice: ");
					int option_to_cont = readOption(1, 2);
					if (option_to_cont == 1) {
						continue;
					}
					else {
						insert_into = false;
						System.out.println("Cancelling booking");
						return false;
					}	
				}
				else{
					seatingArrg[row-1][column-1] = 1;
					booking_to_insert[i] = result_insert;
					break;

				}

			}
		}
		if(insert_into == true){
			System.out.println("Make Payment! \n1 Credit Card\n2 Exit\nEnter Choice: ");
			int payment_choice = readOption(1, 2);
			if (payment_choice == 1) {
				System.out.println("Payment Successful");
				System.out.println("TID: "+ booking_to_insert[0].getTID());
				movBookContr.booking_insert_actual(booking_to_insert);
				// for(int i=0; i < booking_to_insert.length ; i++ ){
				// 	System.out.println(booking_to_insert[i].toString());
				// }
				return true;
			}
			else if (payment_choice == 2)
				System.out.println("Payment Unsuccessful, exiting...");
			return false;
		}

		return false;
	}
	/**
	 * This method shows the current seating arrangement of the showtime for the moviegoer.
	 * Movie Goer User Interface for showing available seats,user can choose to perform following actions:(viewing showtime seats by)
	 * search by movie,
	 * search by cineplex,
	 * going back to mainpage.
	 * A 2-D array with "O"s (not occupied) and "X"s (occupied) will printed for the showtime user selected
	 * row and column number and screen will be displayed too
	 * Creating objects from MovieBookingController and Moviegoersearchcontroller to be used within the mainPage2
	 */
	public void showAvailableSeats(){
		Scanner sc = new Scanner(System.in);
		
		Moviegoersearchcontroller searching = new Moviegoersearchcontroller();
		MovieBookingController mbc2 = new MovieBookingController();
		printHeader("SEARCH MOVIES/LIST MOVIES");
		System.out.println("1 Search by movies");
		System.out.println("2 Search by cineplex");
		System.out.println("3 Go Back");
		System.out.print("Enter Choice: ");
		int choice = readOption(1, 5);
		
        switch (choice) {
        	case 3:
        		System.out.println("Returning...");
        		return;
            case 1:
            	while (true){
	            	Movie result_movie = searching.movieSearchBooking();
	            	if(result_movie.getTitle()== null) {
	            		System.out.println("Movie Not Found!");
	            		System.out.println("1 Try again");
            			System.out.println("2 Back");
            			choice = readOption(1,2);
            			if(choice == 1){
            				continue;
            			}
            			else if(choice == 2){
            				return;
            			}
	            	}
	            	else {
	            		String movie_title = result_movie.getTitle();
	            		ShowTime[] show_times = searching.getShowTimesMovie(movie_title);
	            		if(show_times.length == 0) {
	            			System.out.println("No show times found");
	            			System.out.println("1 Try again");
	            			System.out.println("2 Back");
	            			choice = readOption(1,2);
	            			if(choice == 1){
	            				continue;
	            			}
	            			else if(choice == 2){
	            				return;
	            			}
	            			
	            		}
	            		else {
	            			printHeader(movie_title);
	            			System.out.printf( "%-1s %-35s %-25s %-35s %-25s %-45s %n", "","Movie Name","Cineplex Name" ,"Showtime", "Type of cinema class", "Miniumum age to watch");
	            			for(int i = 1; i <= show_times.length ; i++) {
	            				System.out.printf( "%-1s %-35s %-25s %-35s %-25s %-40s %n", i, show_times[i-1].getMoviename() , show_times[i-1].getCineplexname(), show_times[i-1].getMoviedatetime(),mbc2.returnTypeCinema(show_times[i-1].getCineplexname(),String.valueOf(show_times[i-1].getCinemaId())),mbc2.returnAgeNeeded(show_times[i-1].getMoviename()));
	            			}   			
	            		}
	            		System.out.println("Select the showtime you would like to view!");
	            		// get the index
	            		int show_time_index = readOption(1, show_times.length);
	            		System.out.println("Seat Availability");
	            		movie_title = show_times[show_time_index-1].getMoviename();
	            		System.out.println("Movie: "+ movie_title+ "\nCineplex name: "+show_times[show_time_index-1].getCineplexname()+ " Showtime: "+show_times[show_time_index-1].getMoviedatetime());
	            		String cineplex_name_sel = show_times[show_time_index-1].getCineplexname();
						Date show_time_sel = show_times[show_time_index-1].getMoviedatetime();
						int cinema_id_sel = (show_times[show_time_index-1].getCinemaId());
						movie_title = show_times[show_time_index-1].getMoviename();
	            		MovieBookingController movBookContr = new MovieBookingController();
						int[][] seatingArrg = movBookContr.showAvailableSeats(show_time_sel,cineplex_name_sel, cinema_id_sel);
						int some = 1;
						System.out.print("    ");
						int aile = seatingArrg.length/2;
						for(int k = 0; k < seatingArrg.length; k++) {
							if (aile == k)
								System.out.print("  ");
							System.out.print(some + " ");
							some++;								
						}
						System.out.println("");
						System.out.print(" ");
						for(int k = 0; k < seatingArrg.length; k++) {
							if (aile != k)
								System.out.print("  ");
							else
								System.out.print("Screen");
						}
						System.out.println("");
						for(int k = 0; k<seatingArrg.length; k++) {
							int a = k +1;
							System.out.print( a + "|" +  "  ");
							for(int l = 0; l < seatingArrg[1].length ; l++ ) {
								if (aile == l)
									System.out.print("  ");
								if(seatingArrg[k][l] == 0) {
									System.out.print("O ");
								}
								else {
									System.out.print("X ");
								}
							}
							System.out.print(" " +  "|");
							System.out.println("");
						}
		            	return;
	            		// }

	            		
	            	}
            	}
                // break;
            case 2:
            	ShowTime[] show_times = searching.cineplexSearchBooking();
            	while (true){
	            	if(show_times.length == 0) {
	        			System.out.println("No show times found");
	        			System.out.println("1 Try again");
	        			System.out.println("2 Go Back");
	        			int decision = readOption(1,2);
	        			if(decision == 1){
	        				continue;
	        			}
	        			else if(decision == 2){
	        				return;
	        			}
	        		}
	            	else {
	            		// very hard to print header for this one because the cineplex name is lost.
	            		System.out.printf( "%-1s %-35s %-35s %-25s %-45s %n", "","Movie" ,"Showtime", "Type of cinema class", "Miniumum age to watch");
	            		
	            		for(int i = 1; i <= show_times.length ; i++) {
	            			System.out.printf( "%-1s %-35s %-35s %-25s %-40s %n", i, show_times[i-1].getMoviename(), show_times[i-1].getMoviedatetime(),mbc2.returnTypeCinema(show_times[i-1].getCineplexname(),String.valueOf(show_times[i-1].getCinemaId())),mbc2.returnAgeNeeded(show_times[i-1].getMoviename()));
	            		}
	        			System.out.println("Select the showtime you would like to book!");
	            		// get the index
	            		int show_time_index = readOption(1, show_times.length);
	            		System.out.println("View Available Seats");
	            		System.out.println("Movie: "+ show_times[show_time_index-1].getMoviename()+ "\nCineplex name: "+show_times[show_time_index-1].getCineplexname()+ " Showtime: "+show_times[show_time_index-1].getMoviedatetime());
	            		String cineplex_name_sel = show_times[show_time_index-1].getCineplexname();
						Date show_time_sel = show_times[show_time_index-1].getMoviedatetime();
						int cinema_id_sel = (show_times[show_time_index-1].getCinemaId());
						String movie_title = show_times[show_time_index-1].getMoviename();
	            		MovieBookingController movBookContr = new MovieBookingController();
						int[][] seatingArrg = movBookContr.showAvailableSeats(show_time_sel,cineplex_name_sel, cinema_id_sel);
						int some = 1;
						System.out.print("    ");
						int aile = seatingArrg.length/2;
						for(int k = 0; k < seatingArrg.length; k++) {
							if (aile == k)
								System.out.print("  ");
							System.out.print(some + " ");
							some++;								
						}
						System.out.println("");
						System.out.print(" ");
						for(int k = 0; k < seatingArrg.length; k++) {
							if (aile != k)
								System.out.print("  ");
							else
								System.out.print("Screen");
						}
						System.out.println("");
						for(int k = 0; k<seatingArrg.length; k++) {
							int a = k +1;
							System.out.print( a + "|" +  "  ");
							for(int l = 0; l < seatingArrg[1].length ; l++ ) {
								if (aile == l)
									System.out.print("  ");
								if(seatingArrg[k][l] == 0) {
									System.out.print("O ");
								}
								else {
									System.out.print("X ");
								}
							}
							System.out.print(" " +  "|");
							System.out.println("");
						}
		            	return;
	            		// System.out.println("1 Confirm");
	            		// System.out.println("2 Back");
	            		// choice = readOption(1, 2);
	            		
	            		
	        		}
	        	}
        }
	}
	/**
	 * This method generates the Transaction ID (TID) when booking made by the moviegoer is successful.
	 * Method to get the Tid for booking, by concatenating of the first 2 letter, first letter after whitespace of the cineplex name,
	 * then uppercasing them, and concatenating the date-time ("yyyyMMddkkmm") to it 
	 * @param cineplex cineplex name
	 * @param now Date object 
	 * @return String of the TID
	 */
	public String getTid (String cineplex, Date now) {
		String tid = "";
		String cin_upp = cineplex.toUpperCase();
		
		for (int i = 0; i < cineplex.length(); ++i) {
		    char ch = cin_upp.charAt(i);
		    if (i == 0)
		    	tid += ch;
		    if (i == 1)
		    	tid += ch;
		    if (Character.isWhitespace(ch)) {
		    	tid += cin_upp.charAt(i+1);
		    	break;
		    }
		}
		
		
		SimpleDateFormat ticketFormat = new SimpleDateFormat("yyyyMMddkkmm");

		String dt = ticketFormat.format(now);
		tid += dt;

		
		return tid;
	}
}

