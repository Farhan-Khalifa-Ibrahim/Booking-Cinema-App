package model;

/**
 * This Model class contains all information of a cinema - including its cineplex,
 * the cinema ID, the type of the cinema (Platinum or Normal), the number of 
 * seats, the row number, and the column number.
 *
 * @version 1.0
 */

public class Cinema implements InterfaceToString{
	private String cineplexName;
	private int cinemaId;
	private String classType;
	private int numSeats;
	private int row;
	private int column;
	
	/**
	 * Constructor to be used when there is no cinema available
	 * All attributes are set to null for string or 0 for int
	 */
	public Cinema(){
		cineplexName = null;
		cinemaId = 0;
		classType = null;
		numSeats = 0;
		row = 0;
		column = 0;
	}
	
	/**
	 * Constructor strictly used for converting the attributes of the cinema database into cinema attributes
	 * @param title passing in a single row of cinema database consisting attributes in the form of a string array
	 */
	
	public Cinema(String[] title){
		cineplexName = title[0];
		cinemaId = Integer.parseInt(title[1]);
		classType = title[2];
		numSeats = Integer.parseInt(title[3]);
		row = Integer.parseInt(title[4]);
		column = Integer.parseInt(title[5]);
	}
	
	/**
	 * Constructor used when creating a cinema object
	 * @param cineplex_name name of cineplex
	 * @param cinema_id id of cinema
	 * @param type_of_class type of class, normal / platinum 
	 * @param no_seats num of seats
	 * @param row num of row
	 * @param column num of column
	 */
	
	public Cinema(String cineplex_name,int cinema_id, String type_of_class, int no_seats, int row, int column) {
		this.cineplexName = cineplex_name;
		this.cinemaId = cinema_id;
		this.classType = type_of_class;
		this.numSeats = no_seats;
		this.row = row;
		this.column = column;
	}
	
	/**
	 * Constructor used when creating a cinema object
	 * @param cineplex_name name of cineplex
	 * @param cinema_id id of cinema
	 * @param type_of_class type of class, normal / platinum 
	 * @param row num of row
	 * @param column num of column
	 */
	public Cinema(String cineplex_name,int cinema_id, String type_of_class, int row, int column) {
		this.cineplexName = cineplex_name;
		this.cinemaId = cinema_id;
		this.classType = type_of_class;
		this.numSeats = row * column;
		this.row = row;
		this.column = column;
	}
	
	public String getCineplexname() {
		return cineplexName;
	}
	
	public void setCineplexname(String cineplex_name){
		this.cineplexName = cineplex_name;
	}
	
	public int getCinemaid() {
		return cinemaId;
	}
	
	public void setCinemaid(int cinema_id){
		this.cinemaId = cinema_id;
	}
	
	public String getClasstype() {
		return classType;
	}
	
	public void setClasstype (String type_of_class) {
		this.classType = type_of_class;
	}
	
	public int getNum_Seats() {
		return numSeats;
	}
	
	public void setNumseats(int row, int column) {
		this.row = row;
		this.column = column;
		this.numSeats = row*column;
	}

	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	/**
	 * This method is to string all of the cinema attribute into a single string, to store back into the database
     */
	
	public String toString() {
        return new StringBuffer(this.cineplexName).append("%")
        		.append(this.cinemaId).append("%")
                .append(this.classType).append("%")
                .append(this.numSeats).append("%")
                .append(this.row).append("%")
                .append(this.column).append("%")
                .toString();
    }
	
}