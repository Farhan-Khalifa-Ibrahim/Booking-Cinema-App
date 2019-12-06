
package controller;
import java.io.IOException;
import model.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import controller.InputOutputController;


/**
 * This Controller class manages anything related to booking of showtimes 
 * made by the moviegoer.
 *
 */
public class MovieBookingController extends SuperController {
	protected String movie;
	protected String date_time;
	protected String cinema_id;
	protected String cineplex_name;
	protected int age;
	protected int seat_book_row;
	protected int seat_book_column;
//	protected String seat;
	protected String[][] from_db_movie;
	protected String[][] from_db_holiday;
	protected String[][] from_db_cinema;
	protected String[][] from_db_booking;
	protected String[][] from_db_showtime;
	protected Movie[] MovieList;
	protected Holiday[] HolidayList;
	protected Cinema[] CinemaList;
	protected Booking[] BookingList;
	protected ShowTime[] ShowtimeList;
	protected String[][] to_db_booking;
	
	/**
	 *  Constructor for movie booking controller, to open database from txt files
	 * Setting all string attribute to null, int atttributes to either 0 / -1
	 */
	public MovieBookingController(){
		this.movie = null;
		this.date_time = null;
		this.cinema_id = null;
		this.cineplex_name = null;
//		this.seat = null;
		this.seat_book_row = -1;
		this.seat_book_column = -1;
		this.age = 0;
		
	

		
		SuperController test_super = new SuperController();
		try {
			from_db_movie = test_super.read_database("database/Movie_data.txt");	
			from_db_holiday = test_super.read_database("database/Holiday_data.txt");
			from_db_cinema = test_super.read_database("database/Cinema_data.txt");
			from_db_booking = test_super.read_database("database/Booking_data.txt");
			from_db_showtime = test_super.read_database("database/Showtime_data.txt");
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		MovieList = new Movie[from_db_movie.length];
		for (int j=0;j<from_db_movie.length;j++) {
			MovieList[j] = new Movie(from_db_movie[j]);
		}
		HolidayList = new Holiday[from_db_holiday.length];
		for (int j=0;j<from_db_holiday.length;j++) {
			HolidayList[j] = new Holiday(from_db_holiday[j]);
		}
		CinemaList = new Cinema[from_db_cinema.length];
		for (int j=0;j<from_db_cinema.length;j++) {
			CinemaList[j] = new Cinema(from_db_cinema[j]);
		}
		BookingList = new Booking[from_db_booking.length];
		for (int j=0;j<from_db_booking.length;j++) {
			BookingList[j] = new Booking(from_db_booking[j]);
		}
		ShowtimeList = new ShowTime[from_db_showtime.length];
		for (int j=0;j<from_db_showtime.length;j++) {
			ShowtimeList[j] = new ShowTime(from_db_showtime[j]);
		}
		
	
	}
	
	/**
	 *  Method to check base price of the movie
	 * @param movie string of movie name
	 * @return returns base price
	 */
	public double checkBasePrice(String movie) {
		double basePrice = 0;
		for(int k = 0; k<MovieList.length; k++) {
			if(MovieList[k].getTitle().equals(movie) ){
				basePrice = MovieList[k].getBasePrice();
				return basePrice;
			}
		}
		return basePrice;
	}
	
	/////////////////////////
	////////CHECK AGAIN//////
	/////////////////////////
	
	
	/**
	 *  Method to check whether the date is a holiday, from the holiday database
	 * @param date_time Date object to check with the holiday database
	 * @return true if falls on holiday, false if not
	 */
	public boolean checkIsHoliday(Date date_time) {
//		String date_only_check = null;
//		date_only_check = date_time.substring(0, date_time.length()-6);
//		System.out.println(date_only_check);
//		// remember to cut the format from date_time to date format (YYYY-MM-DD)
//		for(int k = 0; k<HolidayList.length; k++) {
//			if(HolidayList[k].getDate().equals(date_time)){
//				return true;
//			}
//		}
//		return false;
//		String date = new SimpleDateFormat("yyyy-MM-dd").format(date_time);
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String date_input = date.format(date_time);
		
//		System.out.println("HOLIDAY DATE"+ date);
		for(int k = 0; k<HolidayList.length; k++) {
//			System.out.println("THIS IS FROM HOLIDAY " + HolidayList[k].getDate());
//			System.out.println("THIS IS FROM INPUT " + date_time);
			if(date.format(HolidayList[k].getDate()).equals(date_input))
				return true;
		}
		return false;
	}
	
	/**
	 *  Method to whether the showtime of the movie is during the weekends
	 * @param date_time Date object, showing the showtime of the movie
	 * @return true if is during weekend, false if not
	 */
	public boolean checkIsPeak(Date date_time) {
//		String date_only_check = null;
//		date_only_check = date_time.substring(8, date_time.length()-6);
//		// remember to cut the format from date_time to date_only format (DD)
//		for(int k = 0; k<HolidayList.length; k++) {
//			if(HolidayList[k].isPeak(date_only_check))
//				return true;
//		}
//		return false;
        String whatDay = new SimpleDateFormat("EEEE").format(date_time);
        if (whatDay.equals("Saturday") || whatDay.equals("Sunday")) return true;
        else return false;
	}
	
	/**
	 *  Method to check whether the cinema id in that particular cineplex is platinum or not, from the cinema database
	 * @param cineplex_name cineplex name
	 * @param cinema_id id of the cinema of that particular cineplex
	 * @return true if it is platinum, false if not
	 */
	public boolean checkIsPlat(String cineplex_name, String cinema_id) {
		String Plat = null;
		for(int k = 0; k<CinemaList.length; k++) {
			// needs fixing at cinema model class
			if(String.valueOf(CinemaList[k].getCinemaid()).equals(cinema_id) && CinemaList[k].getCineplexname().equals(cineplex_name))
			{	
				Plat = CinemaList[k].getClasstype();
			}
		}
		if (Plat.equals("Platinum"))
			return true;
		return false;
	}
	/**
	 *  Method to check the final price of ticket by factors like holiday,peak period, platinum class, blockbuster , 3D and age.
	 * @param movie name of the movie
	 * @param date_time showtime of the movie as a Date object
	 * @param cineplex_name name of cineplex
	 * @param cinema_id cinema id
	 * @param age age of the viewer of that ticket
	 * @return final price of the ticket
	 */	
	public double checkPrice(String movie, Date date_time, String cineplex_name, String cinema_id, int age) {
		double base_price = 0;
		boolean isHoliday = true;
		boolean isPeak = true;
		double multiplier = 1;
		boolean isPlat = true;
		int is3D = 0;
		InputOutputController InOutCont = new InputOutputController();
		
		isPlat = checkIsPlat(cineplex_name, cinema_id);
		base_price = checkBasePrice(movie);
		is3D = checkIs3D(movie);
		isHoliday = checkIsHoliday(date_time); 
		isPeak = checkIsPeak(date_time);
		// isPlat = checkIsPlat(cineplex_name,cinema_id);
		if (isHoliday)
			multiplier = multiplier*1.5;
		if (isPeak)
			multiplier = multiplier*1.2;
		if (isPlat)
			multiplier = multiplier*2.0;
		if (is3D == 1)
			multiplier = multiplier*1.2;
		if (age < 17)
			return (InOutCont.round(multiplier*base_price*0.8,1));
		else if (age > 64)
			return (InOutCont.round(multiplier*base_price*0.7,1));
		else
			return (InOutCont.round(multiplier*base_price,1));
	}
	
	// below bookticket uses final price, so rmb check price and pass it into this parameter

		/**
	 *  Method to check whether a booking can be inserted, makes sure that seats are available, and age requirement is met
	 * @param name name of moviegoer 
	 * @param  email email of moviegoer 
	 * @param  movieDatetime showtime of the movie as a Date object
	 * @param  cineplexName name of cineplex
	 * @param  cinemaId cinema ID
	 * @param  movieName name of the movie
	 * @param  seat_book_row row of the booking
	 * @param  seat_book_column column of booking
	 * @param  final_price price based on checkPrice()
	 * @param  age age of the person 
	 * @param  tId transaction ID 
	 * @return Booking object if successful, if not successful, price for booking object will be set to a specific value and subsequently checked at the view
	 */	

	public Booking bookTicket(String name, String email, Date movieDatetime,String cineplexName, int cinemaId, String movieName, int seat_book_row, int seat_book_column, double final_price, int age, String tId) {

		
		Booking current_booking = new Booking();
		// check seat
		for(int k = 0; k<BookingList.length; k++) {
			if(BookingList[k].getMoviedatetime().equals(movieDatetime) && BookingList[k].getCineplexname().equals(cineplexName) && BookingList[k].getCinemaId() == cinemaId && BookingList[k].getSeat()[0] == seat_book_row && BookingList[k].getSeat()[1] == seat_book_column) {
				// System.out.println("Seat is occupied!");
				current_booking.setPrice(1.0);
				return current_booking;
			}
		}
		
		// check age
		for(int k=0; k<MovieList.length; k++) {
			if (MovieList[k].getTitle().equals(movieName)) {
				if (MovieList[k].getAgeRestriction() > age) {
					// System.out.println("Person's age is under the age requirement to watch the movie!");
					current_booking.setPrice(2.0);
					return current_booking;
				}
			}
		}
		
		// update the seating plan
		for(int k = 0; k<ShowtimeList.length; k++) {
			if(ShowtimeList[k].getMoviedatetime().equals(movieDatetime) && ShowtimeList[k].getCineplexname().equals(cineplexName) && ShowtimeList[k].getCinemaId() == cinemaId) {
				ShowtimeList[k].updateSeatingPlan(seat_book_row, seat_book_column);
			}	
		}
		
//		System.out.println("The seat booking row is " + seat_book_row);
//		System.out.println("The seat booking column is " +seat_book_column);
		String convert_back_seat = seat_book_row + "," + seat_book_column;
		BookingList = new Booking[from_db_booking.length + 1];
		for (int j=0;j<from_db_booking.length;j++) {
			BookingList[j] = new Booking(from_db_booking[j]);
//			BookingList[j].convertObjtoString();
		}
		BookingList[from_db_booking.length] = new Booking(name, email, movieDatetime, cineplexName, cinemaId, movieName, convert_back_seat, final_price, age, tId);
		current_booking = new Booking(name, email, movieDatetime, cineplexName, cinemaId, movieName, convert_back_seat, final_price, age, tId);
		return current_booking;
		// update sales by 1
		
		// for(int i = 0; i < MovieList.length; i++) {
		// 	if(MovieList[i].getTitle().equals(movieName)) {
		// 		int current_sales = MovieList[i].getSales();
		// 		current_sales++;
		// 		MovieList[i].setSales(current_sales);
		// 	}
		// }
		
		// try {
		// 	super.writeData("database/Booking_data.txt", BookingList);
		// 	super.writeData("database/Showtime_data.txt", ShowtimeList);
		// 	super.writeData("database/Movie_data.txt", MovieList);
		// } catch (IOException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }
//		System.out.println("Booking successful!");
		// return 0;
	}
	
	/**
	 * method to show the available seats by showing the 2-D array in 0's (empty) and 1's (occupied) of the particular showtime in that particular cineplex and cinema, from the showtime database
	 * @param movieDatetime showtime of the movie
	 * @param cineplexName cineplex name
	 * @param cinemaId cinema id
	 * @return 2-D array
	 */
	public int[][] showAvailableSeats(Date movieDatetime,String cineplexName, int cinemaId){
		int[][] dummy_2d = new int[1][1];
		for(int k = 0; k<ShowtimeList.length; k++) {
			
			
			if(ShowtimeList[k].getMoviedatetime().equals(movieDatetime) && ShowtimeList[k].getCineplexname().equals(cineplexName) && ShowtimeList[k].getCinemaId() == cinemaId) {
				return (ShowtimeList[k].getSeatingPlan());
			}	
		}

		return dummy_2d;
	}
	/**
	 * method to check a particular seat in that particular showtime, cineplex and cinema id, from the showtime database
	 * @param movieDatetime showtime of movie
	 * @param cineplexName cineplex name
	 * @param cinemaId cinema id
	 * @param row row of seat to check
	 * @param column column of seat to check
	 * @return returns 1 if is occupied, 0 if not, -1 if invalid (cannot find)
	 */
	public int checkSeatOccupied(String movieDatetime,String cineplexName, int cinemaId, int row, int column) {
		for(int k = 0; k<ShowtimeList.length; k++) {
			if(ShowtimeList[k].getMoviedatetime().equals(movieDatetime) && ShowtimeList[k].getCineplexname().equals(cineplexName) && ShowtimeList[k].getCinemaId() == cinemaId) {
				if(ShowtimeList[k].checkSeat(row, column))
					return 1;
				else
					return 0;
			}	
		}
		return -1;
	}
	/**
	 * method to return back the type of class of the cinema based on the cineplex and cinema id, from the cinema database
	 * @param cineplexName cineplex name
	 * @param cinemaId cinema id
	 * @return string containing either "platinum" or "normal"
	 */
	public String returnTypeCinema(String cineplexName, String cinemaId) {
		for(int k = 0; k<CinemaList.length; k++) {
			if(String.valueOf(CinemaList[k].getCinemaid()).equals(cinemaId) && CinemaList[k].getCineplexname().equals(cineplexName))
			{	
				return CinemaList[k].getClasstype();
			}
		}
		return null;
	}
	/**
	 * method to return the age needed to watch the particular movie, from the movie database
	 * @param movieName movie name
	 * @return age restriction of movie
	 */
	public int returnAgeNeeded(String movieName) {
		for(int i = 0; i<MovieList.length; i++) {
			if (MovieList[i].getTitle().equals(movieName)){
				return MovieList[i].getAgeRestriction();
			}
		}
		return -1;
	}
	/**
	 * method to check if the movie is 2D / 3D base on the movie name
	 * @param movieName movie name
	 * @return 0 if is 2D, 1 if is 3D, -1 if neither/invalid
	 */
	public int checkIs3D(String movieName) {
		String check_2D_3D = null;
		check_2D_3D = movieName.substring((movieName.length() - 2));
		//System.out.println(check_2D_3D);
		if (check_2D_3D.equals("2D"))
			return 0;
		else if (check_2D_3D.equals("3D"))
			return 1;
		else 
			return -1;
	}
	/**
	 * method to insert an array of bookings into database, the method will traverse through the array 
	 * and insert each booking into the booking database as well as update seating plan for that showtime
	 * @param bookingList an array of Booking objects
	 */

	public void booking_insert_actual(Booking[] bookingList) {

//		BookingList = new Booking[from_db_booking.length + bookingList.length];
//		for (int j=0;j<from_db_booking.length;j++) {
//			BookingList[j] = new Booking(from_db_booking[j]);
//		}
		for (int i = 0 ; i < bookingList.length; i++) {
//			BookingList[from_db_booking.length + i] = bookingList[i];
			try{
				super.insertObject("database/Booking_data.txt", bookingList[i]);
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int k = 0; k<ShowtimeList.length; k++) {
				if(ShowtimeList[k].getMoviedatetime().equals(bookingList[i].getMoviedatetime()) && ShowtimeList[k].getCineplexname().equals(bookingList[i].getCineplexname()) && ShowtimeList[k].getCinemaId() == bookingList[i].getCinemaId()) {
					ShowtimeList[k].updateSeatingPlan(bookingList[i].getSeat()[0], bookingList[i].getSeat()[1]);
				}
			}
			for(int k = 0; k < MovieList.length; k++) {
				if(MovieList[k].getTitle().equals(bookingList[i].getMoviename())) {
					int current_sales = MovieList[k].getSales();
					current_sales++;
					MovieList[k].setSales(current_sales);
				}
			}
		}
		try {
//			super.writeData("database/Booking_data.txt", BookingList);
			super.writeData("database/Showtime_data.txt", ShowtimeList);
			super.writeData("database/Movie_data.txt", MovieList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
