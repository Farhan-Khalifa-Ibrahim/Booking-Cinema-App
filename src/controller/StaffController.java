package controller;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import model.*;


/**
 * This Controller class manages any changes made by the staff to any of the database.
 *
 */

public class StaffController extends SuperController{

	private Staff[] StaffList;
	private Movie[] MovieList;
	private ShowTime[] ShowTimeList;
	private Holiday[] HolidayList;
	private Cineplex[] CineplexList;
	private Cinema[] CinemaList;
	
	private float basePriceNormal;
	private float basePriceBlockbuster;

	/**
	 * Constructor method to instantiate array object from their respective
	 * data base
	 */
	public StaffController(){
		String[][] staff_from_db = null;
		String[][] movie_from_db = null;
		String[][] showtime_from_db = null;
		String[][] hol_from_db = null;
		String[][] cineplex_from_db = null;
		String[][] cinema_from_db = null;

		try {
			staff_from_db = super.read_database("database/Staff_data.txt");
			movie_from_db = super.read_database("database/Movie_data.txt");
			showtime_from_db = super.read_database("database/Showtime_data.txt");
			hol_from_db= super.read_database("database/Holiday_data.txt");
			cineplex_from_db= super.read_database("database/Cineplex_data.txt");
			cinema_from_db= super.read_database("database/Cinema_data.txt");
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		this.MovieList = new Movie[movie_from_db.length];
		for(int i = 0; i < movie_from_db.length ; i++) {

			this.MovieList[i] = new Movie(movie_from_db[i]);
			//System.out.println(MovieList[i].getTitle());
		}

		this.ShowTimeList = new ShowTime[showtime_from_db.length];
		for(int i = 0; i < showtime_from_db.length ; i++) {
			//System.out.println(showtime_from_db[i][0]);
			this.ShowTimeList[i] = new ShowTime(showtime_from_db[i]);
		}
		HolidayList = new Holiday[hol_from_db.length];
		for(int i = 0; i < hol_from_db.length ; i++) {
			HolidayList[i] = new Holiday(hol_from_db[i]);
		}
		this.StaffList = new Staff[staff_from_db.length];
		for(int i = 0; i < staff_from_db.length ; i++) {
			this.StaffList[i] = new Staff(staff_from_db[i]);
		}
		this.CineplexList = new Cineplex[cineplex_from_db.length];
		for(int i = 0; i < cineplex_from_db.length ; i++) {
			this.CineplexList[i] = new Cineplex(cineplex_from_db[i]);
		}
		this.CinemaList = new Cinema[cinema_from_db.length];
		for(int i = 0; i < cinema_from_db.length ; i++) {
			this.CinemaList[i] = new Cinema(cinema_from_db[i]);
		}
		
		float temp1  = MovieList[0].getBasePrice();
		float temp2 = 0;
		for (int i = 1; i < MovieList.length; i++) {

			if (temp1 != MovieList[i].getBasePrice()) {
				temp2 = MovieList[i].getBasePrice();
				break;
				}
		}
		if (temp1 < temp2) {
			this.basePriceNormal = temp1;
			this.basePriceBlockbuster = temp2;
		}
		else {
			this.basePriceNormal = temp2;
			this.basePriceBlockbuster = temp1;
		}

	}

//	public StaffController(Movie[] MovieList, ShowTime[] ShowTimeList, Holiday[] HolidayList) {
//		this.HolidayList = HolidayList;
//		this.MovieList = MovieList;
//		this.ShowTimeList = ShowTimeList;
//	}
	
	/**
	 * This method is to get the list of holidays
	 * @return the array of Holiday
	 *
	 */
	public Holiday[] getHolidays() {
		return this.HolidayList;
	}
	
	/**
	 * This method is to get the list of movies
	 * @return the array of Movie
	 *
	 */
	public Movie[] getMovies() {
		return this.MovieList;
	}
	/**
	 * This method is to get the list of showtimes
	 * @return the array of ShowTime
	 *
	 */
	public ShowTime[] getShowtimes() {
		return this.ShowTimeList;
	}
	
	/**
	 * This method is to get the list of cineplexes
	 * @return the array of Cineplex
	 *
	 */
	public Cineplex[] getCineplex() {
		return this.CineplexList;
	}
	
	/**
	 * This method is to get the price for Normal movies
	 * @return the base price for Normal movies
	 */
	public float getPriceNormal() {
		return this.basePriceNormal;
	}
	
	/**
	 * This method is to get the price for Blockbuster movies
	 * @return the base price for Blockbuster movies
	 */
	public float getPriceBlockbuster() {
		return this.basePriceBlockbuster;
	}
	
	
	
	/**
	 * This method is to get the list of cinemas of the particular cineplex
	 * @param cineplexName is the name of the cineplex
	 * @return the array of Cinema
	 *
	 */
	public Cinema[] getCinema(String cineplexName) {
		int count = 0;
		Cinema [] cinema= new Cinema[this.CinemaList.length];
		for (int i = 0; i < this.CinemaList.length; i++) {
			if (this.CinemaList[i].getCineplexname().equals(cineplexName)) {
				cinema[count] = this.CinemaList[i];
				count++;
			}
		}
		return cinema;
	}
	
	/**
	 * This method is to get the list of cinemas of the particular cineplex
	 * @param index the index of the showtime in the array of ShowTime
	 * @return the array of Cinema
	 *
	 */
	public Cinema[] getCinema(int index) {
		int count= 0;
		Cinema [] cinema= new Cinema[this.CinemaList.length];
		for (int i = 0; i < this.CinemaList.length; i++) {
			if (this.CinemaList[i].getCineplexname().equals(this.ShowTimeList[index].getCineplexname())) {
				cinema[count] = this.CinemaList[i];
				count++;
			}
		}
		return cinema;
	}
	/**
	 * This method is to get the list of movies whose status are "currently showing"
	 * @return the array of Movie
	 *
	 */
	public Movie[] getShowingMovies() {
		int count = 0;
		Movie [] mov= new Movie[this.MovieList.length];
		for (int i = 0; i < this.MovieList.length; i++) {
			if (this.MovieList[i].getStatus().equals("currently showing")) {
				mov[count] = this.MovieList[i];
				count++;
			}
		}
		return mov;
	}
	/**
	 * This method is to get the movie by index
	 * @param i the index of the movie
	 * @return the Movie Object
	 *
	 */
	public Movie getMovieByIndex(int i) {
		return MovieList[i];
	}

	/**
	 * This method is to check for validity of the username and password of the staff.
	 * @param username username given
	 * @param pass password given
	 * @return true if the username and password matches, return false otherwise
	 *
	 */

	public boolean loginCheck(String username, String pass) {
		for (int i = 0; i<this.StaffList.length; i++) {
			if(this.StaffList[i].getStaffID().equals(username) &&
					this.StaffList[i].getStaffPass().equals(pass))
				return true;
		}
		return false;
	}

	/**
	 * This method is to update the movie chosen by changing any one of the attributes of the movie.
	 * @param i the movie chosen from the list
	 * @param option the attribute of the movie to change
	 * @param newAtt the new value of the attribute to change
	 * @param newCast the new array of cast members
	 * @return 0 if movie successfully updated, 1 if movie is not written to database,
	 * 2 if movie already exists, 3 if movie status input is not correct,
	 * 4 if age restriction input is not correct
	 *
	 */
	public int updateOneMovie(int i, int option, String newAtt, String[] newCast) {
		//		boolean found = false;
		//		for(int i = 0; i<this.MovieList.length; i++) {
		//			if(this.MovieList[i].getTitle().equalsIgnoreCase(movieName) ){
		//				found = true;
		switch (option) {
		case 1: 
			for(int j = 0; j<this.MovieList.length; j++) {
				if(i != j &&
						this.MovieList[j].getTitle().equalsIgnoreCase(newAtt) ) {
					//							System.out.println("This movie already exists. Try again. ");
					return 2;
				}
			}
			MovieList[i].setTitle(newAtt); break;
		case 2: 
			if (!this.checkMovieStatus(newAtt))
				return 3;
			else
				MovieList[i].setStatus(newAtt); break;
		case 3: MovieList[i].setSynopsis(newAtt); break;
		case 4: MovieList[i].setDuration(Integer.parseInt(newAtt)); break;
		case 5: MovieList[i].setDirector(newAtt); break;
		case 6: MovieList[i].setCastName(newCast);break;
		case 7: 
			if (!this.checkAgeRestriction(Integer.parseInt(newAtt)))
				return 4;
			else
				MovieList[i].setAgeRestriction(Integer.parseInt(newAtt)); break;
		case 8: MovieList[i].setBasePrice(Float.parseFloat(newAtt)); break;
		default: break;
		}
		//				break;
		//			}
		//		}
		//		if (! found) {
		//			System.out.println("Movie is not inside the database. Try again.");
		//			return;
		//		}

		//update to database
		try {
			super.writeData("database/Movie_data.txt", MovieList);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * This method is to remove one movie by changing its status to "not showing".
	 * @param i the movie to be removed
	 * @return 0 if the movie is successfully removed, 1 if the movie is not removed
	 * from the database, and 2 if the movie still has a showtime
	 *
	 */
	public int deleteOneMovie(int i) {

		for (int j = 0; j < this.ShowTimeList.length; j++) {
			if (this.ShowTimeList[j].getMoviename().equals(this.MovieList[i].getTitle())) {
				return 2;
			}
		}
		this.MovieList[i].setStatus("not showing");


		try {
			super.writeData("database/Movie_data.txt", this.MovieList);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

//	/**
//	 * This method is to remove all "not showing" movies from the list of movies.
//	 * @return true if the deletion is successful, return false otherwise
//	 *
//	 */
//	public boolean deleteNotShowingMovie() {
//		int i =0 ;
//		Movie[] MovieListNew;
//		int length = this.MovieList.length;
//		for(; i<length; i++) {
//			if(this.MovieList[i].getStatus().equals("not showing") ) {
//
//				MovieListNew = new Movie[MovieList.length - 1]; 
//				System.arraycopy(MovieList, 0, MovieListNew, 0, i); 
//				System.arraycopy(MovieList, i + 1, MovieListNew, i, MovieList.length - i - 1); 
//				this.MovieList = MovieListNew;
//				length--;
//				i--;
//			}
//		}
//
//		try {
//			super.writeData("database/Movie_data.txt", this.MovieList);
//			return true;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}

	/**
	 * This method is to add a new movie into the list of movies.
	 * @param title new title
	 * @param status new status
	 * @param duration new duration
	 * @param director new director
	 * @param sales new sales
	 * @param ageRestriction new age restriction
	 * @param rating new rating
	 * @param synopsis new synopsis
	 * @param basePrice new base price
	 * @param cast1 new cast no. 1
	 * @param cast2 new cast no. 2
	 * @param cast3 new cast no. 3
	 * @param cast4 new cast no. 4
	 * @param cast5 new cast no. 5
	 * @return 0 if movie successfully added, 1 if new movie is not written to database,
	 * 2 if movie already exists, 3 if movie status input is not correct,
	 * 4 if age restriction input is not correct
	 *
	 */
	public int addOneMovie(String title, String status, int duration, String director, int sales, 
			int ageRestriction, float rating, String synopsis,float basePrice, 
			String cast1,String cast2,String cast3,String cast4,String cast5) {

		//check if the movie already exists within database
		for(int i = 0; i<this.MovieList.length; i++) {
			if(this.MovieList[i].getTitle().equalsIgnoreCase(title) ) {
				//				System.out.println("This movie already exists. Try again. ");
				return 2;
			}
		}

		if (!this.checkMovieStatus(status))
			return 3;

		else if (!this.checkAgeRestriction(ageRestriction))
			return 4;

		//insert movie into the movie list
		Movie[] MovieListNew = new Movie[MovieList.length + 1]; 

		System.arraycopy(MovieList, 0, MovieListNew, 0, MovieList.length);

		Movie movieNew = new Movie(title, status, duration, director, sales, 
				ageRestriction, rating, synopsis, basePrice, cast1, cast2, cast3,
				cast4, cast5);

		MovieListNew[MovieListNew.length-1] = movieNew;

		this.MovieList = MovieListNew;

		//        for (Movie m : MovieList)
		//        	System.out.println(m.getTitle());

		//insert into database
		try {
			super.writeData("database/Movie_data.txt", MovieListNew);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;

	}

	/**
	 * This method is to update one showtime by changing any one of the attributes of the showtime.
	 * @param i the showtime chosen from the list
	 * @param option the attribute of the showtime to change
	 * @param newAtt the new value of the attribute to change
	 * @return 0 if showtime successfully updated, 1 if showtime is not written to database,
	 * 2 if booking has been made at that particular showtime,
	 * 3 if cineplex entered is not in the database, 4 if cinema ID is not in the cineplex,
	 * 5 if movie is not listed as "currently showing", 
	 * 6 if the showtime is before the current date, or 
	 * 7 if the showtime coincides with another showtime
	 */
	public int updateOneShowtime(int i, int option, String newAtt) {
		boolean valid = false;

		if (!this.ShowTimeList[i].isEmpty())
			return 2;

		if (option == 1) {
			for(int j = 0; j<this.CineplexList.length; j++) {
				if(this.CineplexList[j].getCineplexname().equalsIgnoreCase(newAtt) ) {
					valid = true;
					break;
				}
			}
			if (valid)
				ShowTimeList[i].setCineplexname(newAtt);
			else
				return 3;
		}
		else if (option == 2) {
			for(int j = 0; j<this.CinemaList.length; j++) {
				if(this.CinemaList[j].getCineplexname().equalsIgnoreCase(this.ShowTimeList[i].getCineplexname())  &&
						this.CinemaList[j].getCinemaid() == Integer.parseInt(newAtt)) {
					valid = true;
					break;
				}
			}
			if (valid)
				ShowTimeList[i].setCinemaId(Integer.parseInt(newAtt));
			else
				return 4;
		}
		else if (option == 3) {
			for(int j = 0; j<this.MovieList.length; j++) {
				if(this.MovieList[j].getTitle().equalsIgnoreCase(newAtt)  &&
						this.MovieList[j].getStatus().equalsIgnoreCase("currently showing")) {
					valid = true;
					break;
				}
			}
			if (valid)
				ShowTimeList[i].setMoviename(newAtt);
			else
				return 5;
		}
		else if (option == 4) {
			if (newAtt == null)
				return 6;
			Calendar cal = Calendar.getInstance();
			for(int k = 0; k<this.ShowTimeList.length; k++) {
				if(this.ShowTimeList[k].getCineplexname().equalsIgnoreCase(this.ShowTimeList[i].getCineplexname())  &&
						this.ShowTimeList[k].getCinemaId()== this.ShowTimeList[i].getCinemaId()) {
					for(int j = 0; j<this.MovieList.length; j++) {
						if(this.MovieList[j].getTitle().equals(this.ShowTimeList[k].getMoviename())) {
							cal.setTime(this.ShowTimeList[k].getMoviedatetime());
							cal.add(Calendar.MINUTE,this.MovieList[j].getDuration());
							Date d= cal.getTime();
							Date date = null;
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
							simpleDateFormat.setLenient(false);
					        try {
								date = simpleDateFormat.parse(newAtt);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if (d.after(date))
								return 7;
						}
					}
				}
			}

			ShowTimeList[i].setMoviedatetimeStr(newAtt);
		}
		
		




		//update to database
		try {
			super.writeData("database/Showtime_data.txt", ShowTimeList);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * This method is to delete one showtime from the list of showtimes.
	 * @param i the showtime chosen from the list
	 * @return 0 if showtime is deleted successfully, 1 if the showtime is not
	 * deleted from the database, and 2 if the showtime already has booking
	 *
	 */
	public int deleteOneShowtime(int i) {

		if (!this.ShowTimeList[i].isEmpty()) {
			return 2;
		}
		// Create another array of size one less 
		ShowTime[] ShowTimeListNew = new ShowTime[ShowTimeList.length - 1]; 

		// Copy the elements from starting till index 
		// from original array to the other array 
		System.arraycopy(ShowTimeList, 0, ShowTimeListNew, 0, i); 

		// Copy the elements from index + 1 till end 
		// from original array to the other array 
		System.arraycopy(ShowTimeList, i + 1, ShowTimeListNew, i, ShowTimeList.length - i - 1); 

		this.ShowTimeList = ShowTimeListNew;


		try {
			super.writeData("database/Showtime_data.txt", ShowTimeListNew);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * This method is to add one showtime into the list of showtimes.
	 * @param cineplexName the cineplex name
	 * @param cinemaId the cinema ID
	 * @param movieName the name of the movie
	 * @param movieDateTime the new showtime
	 * @return 0 if showtime successfully updated, 1 if showtime is not written to database,
	 * 2 if cineplex entered is not in the database, 3 if cinema ID is not in the cineplex,
	 * 4 if movie is not listed as "currently showing", 5 if showtime already exists,
	 * 6 if the showtime is before the current date and time, or 7 if the showtime
	 * coincides with another showtime
	 *
	 */
	public int addOneShowtime(String cineplexName, int cinemaId, String movieName, 
			Date movieDateTime) {

		/////////////////////////////////////////////////////////////
		////////////////CHECKING FOR CORRECT INPUT//////////////////
		////////////////////////////////////////////////////////////
		boolean valid = false;
		for(int i = 0; i<this.CineplexList.length; i++) {
			if(this.CineplexList[i].getCineplexname().equalsIgnoreCase(cineplexName) ) {
				valid = true;
				break;
			}
		}
		if (!valid)
			return 2;

		valid = false;
		for(int i = 0; i<this.CinemaList.length; i++) {
			if(this.CinemaList[i].getCineplexname().equalsIgnoreCase(cineplexName)  &&
					this.CinemaList[i].getCinemaid() == cinemaId) {
				valid = true;
				break;
			}
		}
		if (!valid)
			return 3;

		valid = false;
		for(int i = 0; i<this.MovieList.length; i++) {
			if(this.MovieList[i].getTitle().equalsIgnoreCase(movieName)  &&
					this.MovieList[i].getStatus().equalsIgnoreCase("currently showing")) {
				valid = true;
				break;
			}
		}
		if (!valid)
			return 4;

		for(int i = 0; i<this.ShowTimeList.length; i++) {
			if(this.ShowTimeList[i].getCineplexname().equalsIgnoreCase(cineplexName)  &&
					this.ShowTimeList[i].getCinemaId()== cinemaId &&
					this.ShowTimeList[i].getMoviedatetime().equals(movieDateTime)) {
				return 5;
			}
		}
		
		Date cur = new Date();
		if (cur.after(movieDateTime))
			return 6;
		
		Calendar cal=Calendar.getInstance();
		
		
		for(int i = 0; i<this.ShowTimeList.length; i++) {
			if(this.ShowTimeList[i].getCineplexname().equalsIgnoreCase(cineplexName)  &&
					this.ShowTimeList[i].getCinemaId()== cinemaId) {
				for(int j = 0; j<this.MovieList.length; j++) {
					if(this.MovieList[j].getTitle().equals(this.ShowTimeList[i].getMoviename())) {
						cal.setTime(this.ShowTimeList[i].getMoviedatetime());
						cal.add(Calendar.MINUTE,this.MovieList[j].getDuration());
						Date d= cal.getTime();
						if (d.after(movieDateTime))
							return 7;
					}
				}
			}
		}
		
//		for(int i = 0; i<this.MovieList.length; i++) {
//			if(this.MovieList[i].getTitle().equals(movieName)) {
//				cal.add(Calendar.MINUTE,-(this.MovieList[i].getDuration()));
//			}
//		}
//		for(int i = 0; i<this.ShowTimeList.length; i++) {
//			if(this.ShowTimeList[i].getCineplexname().equalsIgnoreCase(cineplexName)  &&
//					this.ShowTimeList[i].getCinemaId()== cinemaId) {
//				Date d= cal.getTime();
//				if (d.before(this.ShowTimeList[i].getMoviedatetime()))
//					return 7;
//				
//			}
//		}


		int col = 0;
		int row = 0;

		for(int i = 0; i<this.CinemaList.length; i++) {
			if(this.CinemaList[i].getCineplexname().equalsIgnoreCase(cineplexName)  &&
					this.CinemaList[i].getCinemaid() == cinemaId) {
				row = this.CinemaList[i].getRow();
				col = this.CinemaList[i].getColumn();
			}
		}
		StringBuilder arr = new StringBuilder();
		for (int i = 0; i < row * col; i++) {
			arr.append('0');
		}
		String seatingArrangement = arr.toString();

		//insert showtime into showtime list
		ShowTime[] ShowTimeListNew = new ShowTime[ShowTimeList.length + 1]; 

		System.arraycopy(ShowTimeList, 0, ShowTimeListNew, 0, ShowTimeList.length);

		ShowTime showTimeNew = new ShowTime(cineplexName, cinemaId,  movieName, 
				movieDateTime, seatingArrangement, row, col );

		ShowTimeListNew[ShowTimeListNew.length-1] = showTimeNew;

		this.ShowTimeList = ShowTimeListNew;

		//insert into database
		try {
			super.writeData("database/Showtime_data.txt", ShowTimeListNew);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}


	/**
	 * This method is to delete one holiday from the list of holidays.
	 * @param holName the name of the holiday to remove
	 * @return 0 if holiday successfully deleted, 1 if deletion is unsuccessful,
	 * 2 if holiday name is not in the list of holidays
	 *
	 */
	public int deleteOneHoliday(String holName) {

		boolean found = false;
		int i;
		for(i = 0; i<this.HolidayList.length; i++) {
			if(this.HolidayList[i].getHolidayname().equalsIgnoreCase(holName)){
				found = true;
				break;
			}
		}

		//if the input does not match any holiday name in the list
		if (!found) {
			//			System.out.println("Holiday does not exist in the database. Try again.");
			return 2;
		}

		//delete the holiday from the holiday list
		Holiday[] HolidayListNew = new Holiday[HolidayList.length - 1]; 

		System.arraycopy(HolidayList, 0, HolidayListNew, 0, i); 

		System.arraycopy(HolidayList, i + 1, HolidayListNew, i, HolidayList.length - i - 1); 

		this.HolidayList = HolidayListNew;

		//update the database
		try {
			super.writeData("database/Holiday_data.txt", HolidayListNew);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * This method is to add one holiday to the list of holidays.
	 * @param holName the name of the new holiday
	 * @param date the date of the holiday
	 * @return 0 if holiday successfully added, 1 if holiday not added successfully,
	 * 2 if holiday name is already in the database
	 *
	 */
	public int addOneHoliday(String holName, Date date ) {

		//check if the holiday already exists within database
		for(int i = 0; i<this.HolidayList.length; i++) {
			if(this.HolidayList[i].getHolidayname().equalsIgnoreCase(holName) &&
					this.HolidayList[i].getDate().equals(date)) {
				//				System.out.println("This holiday is already listed. Try again. ");
				return 2;
			}
		}
		
		for(int i = 0; i<this.HolidayList.length; i++) {
			if(this.HolidayList[i].getDate().equals(date)) {
				//				System.out.println("This holiday is already listed. Try again. ");
				return 2;
			}
		}
		

		//insert holiday into holiday list
		Holiday[] HolidayListNew = new Holiday[HolidayList.length + 1]; 

		System.arraycopy(HolidayList, 0, HolidayListNew, 0, HolidayList.length);

		Holiday HolidayNew = new Holiday(holName, date );

		HolidayListNew[HolidayListNew.length-1] = HolidayNew;

		this.HolidayList = HolidayListNew;

		//insert into database
		try {
			super.writeData("database/Holiday_data.txt", HolidayListNew);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;

		//		return this.update(this.HolidayList, "database/Holiday_data.txt");
	}

	/**
	 * This method is to check for correct input of age restriction
	 * @param input the age restriction to check
	 * @return true if the input is 0, 13, 16, 18, or 21, returns false otherwise
	 *
	 */
	public boolean checkAgeRestriction(int input) {
		if (input == 0 || input == 13 || input == 16 || input == 18 || input == 21)
			return true;
		return false;
	}

	/**
	 * This method is to check for correct input of movie status
	 * @param input the movie status to check
	 * @return true if the input is "upcoming", "currently showing", or "not showing",
	 * returns false otherwise
	 *
	 */
	public boolean checkMovieStatus(String input) {
		switch (input.toLowerCase()) {
		case "upcoming":
			return true;
		case "currently showing":
			return true;
		case "not showing":
			return true;
		default:
			return false;
		}
	}

	/**
	 * This method is to change the price of either Normal or Blockbuster movies
	 * @param price the new price
	 * @param type the type of movie
	 * @return 0 if the update is successful, 1 if the update is unsuccessful, 
	 * 2 if the Normal price is more than the Blockbuster price.
	 *
	 */
	public int changePrice (float price, int type) {
		if (type == 1) {
			if (this.basePriceBlockbuster < price)
				return 2;
			
			for (int i = 0; i < this.MovieList.length; i++) {
				if (this.MovieList[i].getBasePrice() == this.basePriceNormal) {
					this.MovieList[i].setBasePrice(price);
				}
			}
			this.basePriceNormal = price;
		}
		else {
			if (this.basePriceNormal > price)
				return 2;
			
			for (int i = 0; i < this.MovieList.length; i++) {
				if (this.MovieList[i].getBasePrice() == this.basePriceBlockbuster) {
					this.MovieList[i].setBasePrice(price);
				}
			}
			this.basePriceBlockbuster = price;
		}
		
		
		try {
			super.writeData("database/Movie_data.txt", this.MovieList);
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	
	//	public int update(Object[] o, String filepath) {
	//		try {
	//			super.writeData(filepath, o);
	//			return 0;
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		return 1;
	//	}


}
