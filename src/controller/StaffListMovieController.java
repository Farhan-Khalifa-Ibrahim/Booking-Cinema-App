package controller;
import java.io.IOException;

import model.*;

/**
 * 
 * This Controller class manages the display format of the list of movies for the staff.
 * It sorts the movies based on the overall ratings or by the total sales.
 *
 *
 */
public class StaffListMovieController extends SuperController {
//	/**
//	 *MovieList, array of Movie object
//	 * HolidayList, array of Holiday object
//	 * @ShowTimeList, array of showTime object
//	 */
	protected Movie[] MovieList;
	protected Holiday[] HolidayList;
	protected ShowTime[] ShowTimeList;
	
	/**
	 * Constructor method to instantiate array object from their respective
	 * data base.
	 */
	public StaffListMovieController(){
//		SuperController rwDatabase = new SuperController();
		String[][] movie_from_db = null;
		String[][] hol_from_db = null;
		String[][] showtime_from_db = null;
		try {
			movie_from_db = super.read_database("database/Movie_data.txt");
			hol_from_db= super.read_database("database/Holiday_data.txt");
			showtime_from_db = super.read_database("database/Showtime_data.txt");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		this.ShowTimeList = new ShowTime[showtime_from_db.length];
		for(int i = 0; i < showtime_from_db.length ; i++) {
			//System.out.println(showtime_from_db[i][0]);
			this.ShowTimeList[i] = new ShowTime(showtime_from_db[i]);
		}
		
		MovieList = new Movie[movie_from_db.length];
		for(int i = 0; i < movie_from_db.length ; i++) {
			MovieList[i] = new Movie(movie_from_db[i]);
		}
		HolidayList = new Holiday[hol_from_db.length];
		for(int i = 0; i < hol_from_db.length ; i++) {
			HolidayList[i] = new Holiday(hol_from_db[i]);
		}

				
	}
	
	/**
	 * This method is to get the list of movies and return the array of
	 * Movie objects in which the elements are already sorted based on the overall
	 * ratings.
	 * @return array of sorted Movie object
	 */
	public Movie[] getTopMoviesByRating() {
		
		for(int k = 0; k < MovieList.length; k++) {
			for(int i = 1; i < (MovieList.length - k) ; i++) {
				Movie temp_movie;
				if(MovieList[i - 1].getRating() < MovieList[i].getRating()) {
					temp_movie = MovieList[i - 1];
					MovieList[i - 1] = MovieList[i];
					MovieList[i] = temp_movie;
				}
			}
		}
		Movie[] result = combine2D3DRating(MovieList);
		return result;
	}
	
	/**
	 * This method is to calculate the average rating if a movie has 
	 * both 2D and 3D movie type. If the movie only has 2D movie type then 
	 * the ratings remain the same. 
	 * @param movielist sorted array of Movie object
	 * @return sorted array of Movie object with the ratings combined
	 */
	public Movie[] combine2D3DRating(Movie[] movielist) {
		Movie[] result = new Movie[5];
		int counter =0;
		int counter2=0;
		int counter3=0;
		for (int i =0;i<movielist.length;i++) {
			if(movielist[i].getTitle().contains("3D")) {
				counter ++;
			}
		}
		Movie[] Movie2D = new Movie[movielist.length-counter];
		Movie[] Movie3D = new Movie[counter];
		for (int i =0;i<movielist.length;i++) {
			if(movielist[i].getTitle().contains("3D")) {
				Movie3D[counter3] = movielist[i];
				counter3++;
			}
			else {
				Movie2D[counter2] = movielist[i];
				counter2++;
			}
		}
		for (int i =0;i<Movie2D.length;i++) {
			for (int j =0;j<Movie3D.length;j++) {
				if ( Movie3D[j].getTitle().contains(Movie2D[i].getTitle())&& Movie3D[j].getRating()!=0.0 ) {
					Movie2D[i].setRating( (Movie2D[i].getRating()+Movie3D[j].getRating())/2 ); 
				}
			}
		}
		for(int k = 0; k < Movie2D.length; k++) {
			for(int i = 1; i < (Movie2D.length - k) ; i++) {
				Movie temp_movie;
				if(Movie2D[i - 1].getRating() < Movie2D[i].getRating()) {
					temp_movie = Movie2D[i - 1];
					Movie2D[i - 1] = Movie2D[i];
					Movie2D[i] = temp_movie;
				}
			}
		}
		return Movie2D;
	}
	
	/**
	 * This method is to get the list of movies and return the array of
	 * Movie objects in which the elements are already sorted based on the total sales.
	 * @return array of sorted Movie object
	 */
	public Movie[] getTopMoviesBySales() {
		for(int k = 0; k < MovieList.length; k++) {
			for(int i = 1; i < (MovieList.length - k) ; i++) {
				Movie temp_movie;
				if(MovieList[i - 1].getSales() < MovieList[i].getSales()) {
					temp_movie = MovieList[i - 1];
					MovieList[i - 1] = MovieList[i];
					MovieList[i] = temp_movie;
				}
			}
		}
		Movie[] result = combine2D3DSales(MovieList);
		return result;
	}
	/**
	 * This method is to calculate the total sales if a movie has 
	 * both 2D and 3D movie type. If the movie only has 2D movie type then 
	 * the sales remain the same. 
	 * @param movielist sorted array of Movie object
	 * @return sorted array of Movie object with the sales combined
	 */
	public Movie[] combine2D3DSales(Movie[] movielist) {
		Movie[] result = new Movie[5];
		int counter =0;
		int counter2=0;
		int counter3=0;
		for (int i =0;i<movielist.length;i++) {
			if(movielist[i].getTitle().contains("3D")) {
				counter ++;
			}
		}
		Movie[] Movie2D = new Movie[movielist.length-counter];
		Movie[] Movie3D = new Movie[counter];
		for (int i =0;i<movielist.length;i++) {
			if(movielist[i].getTitle().contains("3D")) {
				Movie3D[counter3] = movielist[i];
				counter3++;
			}
			else {
				Movie2D[counter2] = movielist[i];
				counter2++;
			}
		}
		for (int i =0;i<Movie2D.length;i++) {
			for (int j =0;j<Movie3D.length;j++) {
				if ( Movie3D[j].getTitle().contains(Movie2D[i].getTitle()) ) {
					Movie2D[i].setRating( (Movie2D[i].getSales()+Movie3D[j].getSales()) ); 
				}
			}
		}
		for(int k = 0; k < Movie2D.length; k++) {
			for(int i = 1; i < (Movie2D.length - k) ; i++) {
				Movie temp_movie;
				if(Movie2D[i - 1].getRating() < Movie2D[i].getRating()) {
					temp_movie = Movie2D[i - 1];
					Movie2D[i - 1] = Movie2D[i];
					Movie2D[i] = temp_movie;
				}
			}
		}
		return Movie2D;
	}
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
	
}
