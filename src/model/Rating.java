package model;

/**
 * This Model class contains all information of a rating - including its
 * rating iD, the movie title, the overall rating, and the reviews.
 * 
 * @version 1.0
 */

public class Rating implements InterfaceToString {
	
	private int ratingId;
	private String movieTitle;
	private float rating;
	private String review;

	/**
	 * Constructor to be used when there is no rating available
	 * All attributes are set to null for string or 0 for int
	 */
	public Rating(){
		this.ratingId = 0;
		this.movieTitle = null;
		this.rating = 0;
		this.review = null;
	}
	/**
	 * Constructor used when creating a rating object
	 * @param ratingId rating ID
	 * @param movieTitle movie name
	 * @param rating rating  
	 * @param review review
	 */
	public Rating(int ratingId, String movieTitle,float rating, String review){
		this.ratingId = ratingId;
		this.movieTitle = movieTitle;
		this.rating = rating;
		this.review = review;

	}
	/**
	 * Constructor strictly used for converting the attributes of the Rating database into Rating attributes
	 * @param db passing in a single row of Rating database consisting attributes in the form of a string array
	 */
	public Rating (String[] db) {

		this.ratingId = Integer.parseInt(db[0]);
		this.movieTitle = db[1];
		this.rating = Float.parseFloat(db[2]);
		this.review = db[3];
	}


		public void setMovieTitle(String Title) {
			movieTitle = Title;
		}

		public void setRating(int rate) {
			rating = rate;
		}

		public void setReview(String reviewContent) {
			review = reviewContent;
		}

		public int getRatingId(){
			return ratingId;
		}

		public String getMovieTitle() {
			return movieTitle;
		}

		public float getRating() {
			return rating;
		}

		public String getReview() {
			return review;
		}

		/**
		 * This method is to string all of the rating attribute into a single string, to store back into the database
	     */
		public String toString() {
	        return new StringBuffer(String.valueOf(this.ratingId)).append("%")
	        		.append(this.movieTitle).append("%")
	                .append(this.rating).append("%")
	                .append(this.review)
	                .toString();
	        }


}
