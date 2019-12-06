package model;

/**
 * This Model class contains all information of a cineplex - including its name
 * and the number of cinemas.
 *
 * @version 1.0
 */

public class Cineplex implements InterfaceToString{


	private String cineplexName;
	private int noOfCinema;

	/**
	 * Constructor to be used when there is no cineplex available
	 * All attributes are set to null for string or 0 for int
	 */
	public Cineplex() {
		this.cineplexName = null;
		this.noOfCinema = 0;
	}
	
	/**
	 * Constructor used when creating a Cineplex object
	 * @param name name of cineplex
	 * @param nb id of cinema
	 */
	
	public Cineplex(String name, int nb) {
		this.cineplexName = name;
		this.noOfCinema = nb;
	}
	
	/**
	 * Constructor strictly used for converting the attributes of the cineplex database into cineplex attributes
	 * @param db passing in a single row of cineplex database consisting attributes in the form of a string array
	 */
	
	public Cineplex(String []db) {
		this.cineplexName = db[0];
		this.noOfCinema = Integer.parseInt(db[1]);
	}

	public String getCineplexname() {
		return this.cineplexName;	
	}
	
	public void setCineplexName(String name) {
		this.cineplexName = name;
	}
	
	public int getNoofcinemas() {
		return this.noOfCinema;
	}
	
	public void setNoofcinemas(int nb) {
		this.noOfCinema = nb;
	}
	
	/**
	 * This method is to string all of the cineplex attribute into a single string, to store back into the database
     */
	public String toString() {
        return new StringBuffer(this.cineplexName)
        		.append("%").append(this.noOfCinema).toString();
    }

}
