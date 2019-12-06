package controller;
import java.util.*;

import java.io.IOException;
import java.util.Arrays;

import model.Movie;
import model.ShowTime;
import model.Rating;
import controller.SuperController;
import static controller.InputOutputController.*;

/**
 * 
 * This Controller class manages searching made by the moviegoer.
 *
 *
 */

public class Moviegoersearchcontroller extends SuperController {
//	/**
//	 * Showtime store showtime database in double index list
//	 * Movie store Movie database in double index list
//	 * Rating_Review store Rating database in double index list
//	 * MovieList store Objects of Movie from Movie attribute in a list
//	 * ShowTimeList store Objects of ShowTime from Showtime attribute in a list
//	 * Rating_List store Objects of Rating from Rating_Review attribute in a list 
//	 */
	private String[][] Showtime;
	private String[][] Movie;
	private String[][] Rating_Review;
	private Movie[] MovieList;
	private ShowTime[] ShowTimeList;
	private Rating[] Rating_List;
	
	/**
	 * This is the constructor for the class
	 * We reload all the database in here
	 * We use controller from SuperController to read database
	 */
	public Moviegoersearchcontroller(){
		 SuperController control = new SuperController();
		 try {
		 this.Showtime=control.read_database("database/Showtime_data.txt");
		 this.Movie = control.read_database("database/Movie_data.txt");
		 this.Rating_Review = control.read_database("database/Rating_data.txt");
		 MovieList =  new Movie[Movie.length];
			for(int i=0;i<Movie.length;i++) {
				MovieList[i] = new Movie(Movie[i]);
		     }
		 ShowTimeList =  new ShowTime[Showtime.length];
			for(int i=0;i<Showtime.length;i++) {
				ShowTimeList[i] = new ShowTime(Showtime[i]);
			}
		 Rating_List = new Rating[Rating_Review.length];
			for(int i=0;i<Rating_Review.length;i++) {
				Rating_List[i] = new Rating(Rating_Review[i]);
			}
		 }
		 catch (IOException e) {
			 e.printStackTrace();
			 System .out.println("Database is not found");
		 }
	}
	
	/**
	 * This function is used to find the upcoming/now showing movie datail
	 * It will use functions to choose upcming/now showing movies we would like to see the details
	 */
	public void SearchMovie() {
		while(true) {
		Scanner in = new Scanner(System.in);
//		System.out.println("Type the movie title you want to search");
		int choice;
		String Search_movie="";
		System.out.println("-------What do you want to do?-------");
		System.out.println("1 Find the movies currently showing");
		System.out.println("2 Find the upcoming movies");
		System.out.println("3 Back");
		System.out.println("Enter Choice: ");
		choice = readOption(1,3);
		switch(choice){
			case 1: Search_movie=search_showing_movie_list();
					if(Search_movie.contentEquals("")) {
						break;
					}
					show_details(Search_movie);
					break;
			case 2: Search_movie=search_upcoming_movie_list();
					if(Search_movie.contentEquals("")) {
						break;
					}
					show_details(Search_movie);
					break;
			case 3: return;
			}
		}
	}
	
	/**
	 * This Function will show all the movies currently showing
	 * It will return the movie currently showing by choosing the movie we want to find the details
	 * @return String of movie name
	 */
	public String search_showing_movie_list() {
		int index=0;
		String movie="";
		printHeader("Currently Showing Movies");
		//System.out.println("=============Currently Showing Movies===============");
		System.out.printf( "%-1s %-35s %-35s %n", "","Movie Name", "Minimum age to watch");
		for(int i=0;i<MovieList.length;i++) {
				if(MovieList[i].getStatus().contentEquals("currently showing")) {
					System.out.printf( "%-1s %-35s %-35s %n", ++index , MovieList[i].getTitle(), MovieList[i].getAgeRestriction());
//					System.out.println(++index+" "+MovieList[i].getTitle()+" "+ "Age_restriction:" +MovieList[i].getAgeRestriction());
				}
		}
		System.out.println("\n"+ ++index+" "+"Back");
		System.out.println("Choose the movie you want to find the info:");
		int option = readOption(1,index),k=1;
		for(int i=0;i<MovieList.length;i++) {
			if(MovieList[i].getStatus().contentEquals("currently showing")&&k!=option) {
				k++;
			}
			else if(MovieList[i].getStatus().contentEquals("currently showing")&&k==option) {
				movie=MovieList[i].getTitle();
				break;
			}
		}
		if(option==index) {
			return"";
		}
		return movie;
	}
	/**
	 * This Function will show all the movies upcoming
	 * It will return the movie currently showing by choosing the movie we want to find the details
	 * @return String of movie name
	 */
	public String search_upcoming_movie_list() {
		int index=0;
		String movie="";
		printHeader("Upcoming Movies");
		//System.out.println("=============Upcoming Movies===============");
		System.out.printf( "%-2s %-35s %-35s %n", "","Movie Name", "Minimum age to watch");
		for(int i=0;i<MovieList.length;i++) {
				if(MovieList[i].getStatus().contentEquals("upcoming")) {
					System.out.printf( "%-2s %-35s %-35s %n", ++index , MovieList[i].getTitle(), MovieList[i].getAgeRestriction());
					//System.out.println(++index+" "+MovieList[i].getTitle()+" "+ "Age_restriction:" +MovieList[i].getAgeRestriction());
				}
		}
		System.out.println("\n"+ ++index+" "+"Back");
		System.out.println("Choose the movie you want to find the info:");
		int option = readOption(1,index),k=1;
		for(int i=0;i<MovieList.length;i++) {
			if(MovieList[i].getStatus().contentEquals("upcoming")&&k!=option) {
				k++;
			}
			else if(MovieList[i].getStatus().contentEquals("upcoming")&&k==option) {
				 movie = MovieList[i].getTitle();
				 break;
			}
		}
		if(option==index) {
			return"";
		}
		return  movie;
	}
	/**
	 * Find movie rating and revies
	 * @param movieName Movie we would like to search
	 * @throws IOException Prevent if there is an unsuccessful reading database
	 */
		public void ShowReviewRating(String movieName) throws IOException{
			int i;
			int counter = 1;
			boolean review = false;
			String[][] ratingList = super.read_database("database/Rating_data.txt");
			Rating [] rating = new Rating[ratingList.length];
			this.Movie = super.read_database("database/Movie_data.txt");
			MovieList =  new Movie[Movie.length];
			for(i=0;i<Movie.length;i++) {
				MovieList[i] = new Movie(Movie[i]);
		     }
			for(i=0;i<MovieList.length;i++) {
				if(i==MovieList.length-1&&MovieList[i].getTitle().toLowerCase().equals(movieName.toLowerCase())==false) {
					System.out.println("==================Movie Review and Rating=================");
					System.out.println("Movie is not in our database");
					System.out.println();
					return;
				}
				else if(MovieList[i].getTitle().toLowerCase().equals(movieName.toLowerCase())) {
					break;
				}
			}
			for(int k=0;k<ratingList.length;k++) {
				rating[k] = new Rating(ratingList[k]);
			}
			System.out.println("==================Movie Review and Rating=================");
			for(i=0;i<rating.length;i++) {
				if(rating[i].getMovieTitle().equals(movieName)) {
					review=true;
				}
			}
			if(review==true) {
				for(int j=0;j<MovieList.length;j++) {
				if(MovieList[j].getTitle().equals(movieName)) {
				System.out.println("Title: "+MovieList[j].getTitle());
				System.out.println("Rating: "+MovieList[j].getRating());
				System.out.println("Review below:");
					}
				}
				int index = 0;
				for(i=0;i<rating.length;i++) {
					if(rating[i].getMovieTitle().equals(movieName)) {
						System.out.println(++index+") "+rating[i].getReview());
					}
				}
				System.out.println();
			}
			else {
				System.out.println("Movie is not reviewed/rated yet");
				System.out.println("");
			}
//			for(i=0;i<MovieList.length;i++) {
//				int j;
//				review = false;
//				for(j=0;j<Rating_List.length;j++) {
//					if(Rating_List[j].getMovieTitle().toLowerCase().equals(movieName.toLowerCase())) {
//						review = true;
//						break;
//					}
//				}
//				if(review){
//					System.out.println("Title: "+MovieList[i].getTitle());
//					System.out.println("Rating: "+MovieList[i].getRating());
//					System.out.println("Review below:");
//				}
//				
//				for(j=0;j<ratingList.length;j++) {
//					if(MovieList[i].getTitle().toLowerCase().contentEquals(rating[j].getMovieTitle().toLowerCase())&&MovieList[i].getTitle().contentEquals(movieName)) {
//						System.out.println(counter+" "+rating[j].getReview());
//						counter++;
//					}
//				}
//			}
//			if (counter == 1) {
//				System.out.println("Title: "+movieName);
//				System.out.println("Rating: 0");
//				System.out.println("Review below:");
//				System.out.println("NA");
//			}
	   }
		/**
		 * method provides functionality to search for movie in the database
		 * @return Movie object of the movie that was found based on user search
		 */
		public Movie movieSearchBooking() {
//			System.out.println("Type the movie title you want to search");
			InputOutputController io = new InputOutputController();
			String movie_search = io.readString("Type the movie title you want to search");
			Movie movie_search_obj = new Movie();
			for(int i=0;i<MovieList.length;i++) {
				if(MovieList[i].getTitle().toLowerCase().equals(movie_search.toLowerCase())) {
					movie_search_obj = MovieList[i];
				}
			}
			return movie_search_obj;
		}
		/**
		 * Find the ShowTime for a particular movie
		 * @param movie_name the movie we want to find
		 * @return ShowTime object list
		 */
		public ShowTime[] getShowTimesMovie(String movie_name) {
			ShowTime[] available_showtimes;
			Date tdyDate = new Date();
			int count = 0;
			for(int i = 0; i < ShowTimeList.length; i++) {
				if(ShowTimeList[i].getMoviename().contains(movie_name) &&
						ShowTimeList[i].getMoviedatetime().compareTo(tdyDate)>0) {
					count++;
				}
			}

			available_showtimes = new ShowTime[count];
			if(count == 0) {
				return available_showtimes;
			}
			int k = 0;
			for(int i = 0; i < ShowTimeList.length; i++) {
				if(ShowTimeList[i].getMoviename().contains(movie_name) &&
						ShowTimeList[i].getMoviedatetime().compareTo(tdyDate)>0) {
					available_showtimes[k] = ShowTimeList[i];
					k++;
				}
			}
			
			return available_showtimes;
		}
		/**
		 * This Function is used to find the details of movie we would like to find
		 * @param Search_movie Movie we would like to search
		 */
		public void show_details(String Search_movie){
		int ShowTimeLength=ShowTimeList.length;
		for(int i=0;i<MovieList.length;i++) {
			if(MovieList[i].getTitle().toLowerCase().equals(Search_movie.toLowerCase())) {
				Search_movie = MovieList[i].getTitle();
				System.out.println("==================="+MovieList[i].getTitle()+"===================");
				System.out.println("Status: "+MovieList[i].getStatus());
				System.out.println("Synopsis: "+MovieList[i].getSynopsis());
				System.out.println("Director: "+MovieList[i].getDirector());
				System.out.println("Rating: "+MovieList[i].getRating());
				System.out.println("Cast: "+MovieList[i].getCastName()[0]+","+MovieList[i].getCastName()[1]+","+MovieList[i].getCastName()[2]+","+MovieList[i].getCastName()[3]+","+MovieList[i].getCastName()[4]);
				System.out.print("Available: ");
				
				String[] Available_Cineplex = new String[3];
				for(int k=0;k<3;k++) {
					Available_Cineplex[k]=" ";
				}
				int index=0;
				for(int j=0;j<ShowTimeLength;j++) {
					if(ShowTimeList[j].getMoviename().toLowerCase().equals(Search_movie.toLowerCase())&&!Arrays.stream(Available_Cineplex).anyMatch(ShowTimeList[j].getCineplexname()::equals)) {
						Available_Cineplex[index]=ShowTimeList[j].getCineplexname();
						++index;
					}
				}
				if(index==0) {
					System.out.println("NULL");
				}
				for(int k=0;k<index;k++) {
					 if(k!=index-1) {
						System.out.print(Available_Cineplex[k]+"/");
					}
					else{
						System.out.println(Available_Cineplex[k]);
					}
				}
				int counter=0;
				for(int z=0;z<Rating_List.length;z++) {
					if(Rating_List[z].getMovieTitle().toLowerCase().equals(Search_movie.toLowerCase())) {
						counter++;
					}
				}
				if(counter==0) {
					System.out.print("Review: ");
					System.out.println("NULL");
				}
				else if(counter!=0) {
					System.out.println("Review:");
				}
				int low=1;
				for(int z=0;z<Rating_List.length;z++) {
					if(Rating_List[z].getMovieTitle().toLowerCase().equals(Search_movie.toLowerCase())) {
						if(low!=counter) {
							System.out.println(low+") "+Rating_List[z].getReview());
							low++;
						}
						else {
							System.out.println(low+") "+Rating_List[z].getReview());
							low++;
						}
					}
				}
				System.out.println();
			}
		}
	}

/**
 *This function is used to find the ShowTime available for a specific cineplex 
 * @return Array of ShowTime object
 */
		public ShowTime[] cineplexSearchBooking() {
//			System.out.println("Type the movie title you want to search");
			ShowTime[] available_showtimes;
			InputOutputController io = new InputOutputController();
			String cineplex_search = io.readString("Type the cineplex you want to search");
			int count = 0;
			Date tdyDate = new Date();
			for(int i = 0; i < ShowTimeList.length; i++) {
				if(ShowTimeList[i].getCineplexname().equals(cineplex_search) &&
						ShowTimeList[i].getMoviedatetime().compareTo(tdyDate)>0) {
					count++;
				}
			}

			available_showtimes = new ShowTime[count];
			if(count == 0) {
				return available_showtimes;
			}
			int k = 0;
			for(int i = 0; i < ShowTimeList.length; i++) {
				if(ShowTimeList[i].getCineplexname().equals(cineplex_search)&&
						ShowTimeList[i].getMoviedatetime().compareTo(tdyDate)>0) {
					available_showtimes[k] = ShowTimeList[i];
					k++;
				}
			}
			
			return available_showtimes;
		}
	
}
