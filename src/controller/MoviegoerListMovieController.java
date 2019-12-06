package controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import model.*;

/**
 * 
 * This Controller class manages the display format of the list of movies for the moviegoer.
 * It sorts the movies based on the overall ratings or by the total sales.
 *
 *
 */
public class MoviegoerListMovieController extends StaffListMovieController{
	/**
	 * method to return the information of the specific movie name
	 * @param name, specific movie name to check the booking history we wan
	 * @throws IOException
	 */
	public void viewBookingHistory(String name) throws IOException {
		String[][] booking_history = super.read_database("database/Booking_data.txt");
		Booking[] bookingList = new Booking[booking_history.length];
		int counter =1;
		boolean check = false;
		for (int i = 0;i<booking_history.length;i++) {
			bookingList[i] = new Booking(booking_history[i]);
		}
		for (int i =0;i<bookingList.length ;i++) {
			if (i==0) {
				System.out.println("------Booking History------: \n");
			}
			double total_cost = 0;
			if(name.toLowerCase().equals(bookingList[i].getEmail().toLowerCase())){
				// System.out.println(counter+") "+"Name:"+bookingList[i].getName()+"\n"+
				// 		"Movie:"+bookingList[i].getMoviename()+"\n"+
				// 		"Cineplex:"+bookingList[i].getCineplexname()+"\n"+ 
				// 		"Cinema:"+bookingList[i].getCinemaId()+"\n"+
				// 		"Seat ID:"+(bookingList[i].getSeat())[0]+","+(bookingList[i].getSeat())[1]+"\n"+
				// 		"Date and show time:"+ bookingList[i].getMoviedatetime()+"\n"+
				// 		"TID: " + bookingList[i].getTID()
				// 		);
//				System.out.println("hihihihihi");
				System.out.println(counter+") "+"TID: " + bookingList[i].getTID());
				System.out.println("Name: "+bookingList[i].getName());
				System.out.println("Movie:"+bookingList[i].getMoviename());
				System.out.println("Cineplex:"+bookingList[i].getCineplexname());
				System.out.println("Cinema:"+bookingList[i].getCinemaId());
				//seat
				System.out.print("Seat(s): " + "(" + bookingList[i].getSeat()[0] + " , " +bookingList[i].getSeat()[1] +") ");
				//traverse the next few bookings to see if there is another booking with some TID
				total_cost += bookingList[i].getPrice();
				for(int k = i + 1; k < bookingList.length; k++){
					if(bookingList[k].getTID().equals(bookingList[i].getTID()) ){
						System.out.print("(" + bookingList[k].getSeat()[0] +" , "+ bookingList[k].getSeat()[1] + ") ");
						total_cost += bookingList[k].getPrice();
						if(k<bookingList.length - 1){
							continue;
						}
					}
					i = k-1;
					break;
				}
				System.out.println("");

				System.out.println("Date and show time: "+ bookingList[i].getMoviedatetime());
				System.out.println("Total: $" + total_cost);
				System.out.println("");

				counter++;
				check = true;
			}

		}
		if (check == false) {
			System.out.println("The booking history for this personnel cannot be found!!!");
		}
	}


	/**
	 * method to insert the review and rating if and only if our booking history data base
	 * has the record of the moviegoer watching that particular movie. The moviegoear is uniquely identify by his/her
	 * email address
	 * @param email, email address of moviegoer
	 * @param title, movie title
	 * @throws IOException
	 */
	public void insertReviewRating(String email,String title) throws IOException {
		String[][] booking_history = super.read_database("database/Booking_data.txt");
		Booking[] bookingList = new Booking[booking_history.length];
		for (int i = 0;i<booking_history.length;i++) {
			bookingList[i] = new Booking(booking_history[i]);
		}
		Scanner sc = new Scanner(System.in);
		String[][] rate = super.read_database("database/Rating_data.txt");
		InputOutputController io = new InputOutputController();
		int rates = 0;
		int book_id = rate.length +1;
		boolean check = false;
		String review;
		Date tdyDate = new Date();
		for (int i =0;i<bookingList.length;i++) {
			if ((bookingList[i].getEmail().toLowerCase().equals(email.toLowerCase())&& bookingList[i].getMoviename().toLowerCase().equals(title.toLowerCase()) && bookingList[i].getMoviedatetime().compareTo(tdyDate)<0) ){
				check = true;
			}
		}
		if (check == false) {
			System.out.println("This individual did not watch the movie, hence he/she cannot give a rating/review");
		}
		else {
			System.out.println("Please give your rating(1/2/3/4/5)");
			rates = io.readOption(1, 5);
			System.out.println("Please input your review");
			review = sc.nextLine();
			Rating new_rating = new Rating(book_id,title,rates,review);
			super.insertObject("database/Rating_data.txt", new_rating);
			this.updateRatingInMovieDB();
			System.out.println("Review has been inserted successfully!!");
		}
	}
	
	/**
	 * method use by insertReviewRating to update the rating in the movie database after user have input a new rating.
	 * @throws IOException
	 */
	public void updateRatingInMovieDB() throws IOException{
		String[][] movies = super.read_database("database/Movie_data.txt");
		Movie[] movieList = new Movie[movies.length];
		for (int i = 0;i<movies.length;i++) {
			movieList[i] = new Movie(movies[i]);
		}
		String[][] ratings = super.read_database("database/Rating_data.txt");
		Rating[] ratingList = new Rating[ratings.length];
		
		for (int i = 0;i<ratings.length;i++) {
			ratingList[i] = new Rating(ratings[i]);
		}
		
		int counter =0;
		float total =0;
		float test=0;
		for (int i =0;i<movieList.length;i++) {
			counter = 0;
			total =0;
			for (int j =0;j<ratingList.length;j++) {
				if (movieList[i].getTitle().toLowerCase().equals(ratingList[j].getMovieTitle().toLowerCase())){
					test = ratingList[j].getRating();
					total = total + test;
					counter ++;
				}
			}
			if (counter ==0) {
				movieList[i].setRating((0));
			}
			else {
				double total2 = 0;
				if(counter ==0) {
					total2 = 0;
				}
				else {
					total = total/counter;
					total2 = total;
					total2 = this.round(total2, 1);
				}
			movieList[i].setRating(((float)total2));
			}
		}
		
		super.writeData("database/Movie_data.txt", movieList);
	}
	/**
	 * method use to pass in a value and return a specific decimal place depending on the user's need
	 * @param value, double value
	 * @param precision, the number of decimal 
	 * @return, the double value that has rounded to specific decimal place
	 */
	public double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}
}
