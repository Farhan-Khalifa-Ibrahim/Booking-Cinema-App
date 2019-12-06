package model;

/**
 * This Model class contains all information of a movie - including the title
 * of the movie, the status, the duration, the director, the total sales,
 * the age restriction, the rating, the synopsis, the cast members,
 * and the base price, according to the movie type (Normal or Blockbuster).
 *
 * @version 1.0
 */

public class Movie implements InterfaceToString{
	
	private String title;
	private String status;
	private int duration;
	private String director;
	private int sales;
	private int ageRestriction;
	private float rating;
	private String synopsis;
	private float basePrice;
	private String[] cast = new String[5];
	
	/**
	 * Constructor to be used when there is no movie available
	 * All attributes are set to null for string or 0 for int
	 */
	public Movie() {
		title = null;
		status = null;
		duration = 0;
		director = null;
		sales = 0;
		ageRestriction = 0;
		rating = 0;
		synopsis = null;
		basePrice = 0;
		cast[0]=null;
		cast[1]=null;
		cast[2]=null;
		cast[3]=null;
		cast[4]=null;
	}

	/**
	 * Constructor used when creating a movie object
	 * @param title name of movie
	 * @param status status of movie, currently showing, now showing, upcoming
	 * @param duration duration of movie
	 * @param director director director of movie
	 * @param sales sales of movie
	 * @param ageRestriction age restriction of movie
	 * @param rating rating of movie
	 * @param synopsis description of movie
	 * @param basePrice baseprice of movie, determined by admin, to show blockbuster or not
	 * @param cast_name1 cast num 1's name
	 * @param cast_name2 cast num 2's name
	 * @param cast_name3 cast num 3's name
	 * @param cast_name4 cast num 4's name
	 * @param cast_name5 cast num 5's name
	 */
	public Movie(String title,String status, int duration, String director, 
			int sales, int ageRestriction, float rating, String synopsis,
			float basePrice,String cast_name1,String cast_name2,
			String cast_name3,String cast_name4,String cast_name5) {
		this.title = title;
		this.status = status;
		this.duration = duration;
		this.sales = sales;
		this.director = director;
		this.ageRestriction = ageRestriction;
		this.rating = rating;
		this.synopsis = synopsis;	
		this.basePrice = basePrice;
		this.cast[0] = cast_name1;
		this.cast[1] = cast_name2;
		this.cast[2] = cast_name3;
		this.cast[3] = cast_name4;
		this.cast[4] = cast_name5;
	}
	/**
	 * Constructor used when creating a movie object
	 * @param title name of movie
	 * @param status status of movie, currently showing, now showing, upcoming
	 * @param duration duration of movie
	 * @param director director director of movie
	 * @param sales sales of movie
	 * @param ageRestriction age restriction of movie
	 * @param rating rating of movie
	 * @param synopsis description of movie
	 * @param basePrice baseprice of movie, determined by admin, to show blockbuster or not
	 * @param cast string of cast names (5 max)
	 */
	
	public Movie(String title,String status, int duration, String director, 
			int sales, int ageRestriction, float rating, String synopsis,
			float basePrice, String[] cast) {
		this.title = title;
		this.status = status;
		this.duration = duration;
		this.sales = sales;
		this.director = director;
		this.ageRestriction = ageRestriction;
		this.rating = rating;
		this.synopsis = synopsis;	
		this.basePrice = basePrice;
		this.cast = cast;
	}
	/**
	 * Constructor strictly used for converting the attributes of the movie database into movie attributes
	 * @param db passing in a single row of movie database consisting attributes in the form of a string array
	 */
	public Movie(String[] db) {
		this.title = db[0];
		this.status = db[1];
		this.duration = Integer.parseInt(db[2]);
		this.director = db[3];
		this.sales = Integer.parseInt(db[4]);
		this.ageRestriction = Integer.parseInt(db[5]);
		this.rating = Float.parseFloat(db[6]);
		this.synopsis = db[7];
		this.basePrice = Float.parseFloat(db[8]);
		this.cast[0] = db[9];
		this.cast[1] = db[10];
		this.cast[2] = db[11];
		this.cast[3] = db[12];
		this.cast[4] = db[13];
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setSales(int sales) {
		this.sales = sales;
	}
	
	public int getSales() {
		return sales;
	}
	
	public void setAgeRestriction(int ageRestriction) {
		this.ageRestriction = ageRestriction;
	}
	
	public int getAgeRestriction() {
		return ageRestriction;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public float getRating() {
		return rating;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public String getDirector() {
		return this.director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public float getBasePrice() {
		return basePrice;
	}
	
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	
	public String[] getCastName() {
		return cast;
	}
	
	public void setCastName(String[] cast_name) {
		cast = cast_name;
	}
//	public static void incrementSales() {
//	}
	/**
	 * This method is to string all of the movie attribute into a single string, to store back into the database
     */
	
	public String toString() {
        return new StringBuffer(this.title).append("%")
        		.append(this.status).append("%")
                .append(this.duration).append("%")
                .append(this.director).append("%")
                .append(this.sales).append("%")
                .append(this.ageRestriction).append("%")
                .append(this.rating).append("%")
                .append(this.synopsis).append("%")
                .append(this.basePrice).append("%")
                .append(this.cast[0]).append("%")
                .append(this.cast[1]).append("%")
                .append(this.cast[2]).append("%")
                .append(this.cast[3]).append("%")
                .append(this.cast[4]).toString();
    }

}
