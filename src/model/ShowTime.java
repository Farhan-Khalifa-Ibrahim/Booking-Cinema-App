package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This Model class contains all information of a showtime - including the name of 
 * the cineplex, the cinema ID, the movie name, the date and time, the row number, 
 * the column number, and the seating arrangement.
 *
 * @version 1.0
 */

public class ShowTime implements InterfaceToString {
	
	private String cineplexName;
	private int cinemaId;
	private String movieName;
	private String movieDatetimeStr;
	private Date movieDatetime;
	private int row;	
	private int column; 
	private int[][] seatingArrangement; //2-D int array
	private String seatingArrangementList;
	
	/**
	 * Constructor used when creating a showtime object
	 * @param cineplexName name of cineplex
	 * @param cinemaId id of cinema
	 * @param movieName name of movie
	 * @param movieDatetime showtime in string 
	 * @param seatingArrangementList seating arrangement list in string, filled with int of 0's (not occuppied) and 1's (occupied)
	 * @param row row number
	 * @param column column number
	 */
	public ShowTime(String cineplexName, int cinemaId, String movieName, String movieDatetime, String seatingArrangementList, int row, int column) {
		this.cineplexName = cineplexName;
		this.cinemaId =  cinemaId;
		this.movieName = movieName;
		this.movieDatetimeStr = movieDatetime;
		this.row = row;
		this.column = column;
		int counter = 0;
		this.seatingArrangementList = seatingArrangementList;
		seatingArrangement = new int[row][column];
		
		for (int i = 0; i < row ; i++){
			for (int j = 0; j < column; j++) {
				char number = seatingArrangementList.charAt(counter); 
			    //Process char
			    seatingArrangement[i][j] = Integer.parseInt(Character.toString(number));	
			    counter = counter + 1;
			}
		}
		this.movieDatetime = this.convertDate(this.movieDatetimeStr);
	}
	
	/**
	 * Constructor used when creating a showtime object
	 * @param cineplexName name of cineplex
	 * @param cinemaId id of cinema
	 * @param movieName name of movie
	 * @param movieDatetime showtime in Date object 
	 * @param seatingArrangementList seating arrangement list in string, filled with int of 0's (not occuppied) and 1's (occupied)
	 * @param row row number
	 * @param column column number
	 */
	public ShowTime(String cineplexName, int cinemaId, String movieName, Date movieDatetime, String seatingArrangementList, int row, int column) {
		this.cineplexName = cineplexName;
		this.cinemaId =  cinemaId;
		this.movieName = movieName;
		this.movieDatetime = movieDatetime;
		this.row = row;
		this.column = column;
		int counter = 0;
		this.seatingArrangementList = seatingArrangementList;
		seatingArrangement = new int[row][column];
		
		for (int i = 0; i < row ; i++){
			for (int j = 0; j < column; j++) {
				char number = seatingArrangementList.charAt(counter); 
			    //Process char
			    seatingArrangement[i][j] = Integer.parseInt(Character.toString(number));	
			    counter = counter + 1;
			}
		}
		this.movieDatetimeStr = this.convertStr(this.movieDatetime);
	}
	/**
	 * Constructor strictly used for converting the attributes of the showtime database into movie attributes
	 * @param db passing in a single row of movie database consisting attributes in the form of a string array
	 */
	public ShowTime(String[] db) {
		this.cineplexName = db[0];
		this.cinemaId = Integer.parseInt(db[1]);
		this.movieName = db[2];
		this.movieDatetimeStr= db[3];
		this.row = Integer.parseInt(db[4]);
		this.column = Integer.parseInt(db[5]);
		this.seatingArrangementList = db[6];
		
		int counter = 0;
		seatingArrangement = new int[column][row];
		for (int i = 0; i < row ; i++){
			for (int j = 0; j < column; j++) {
				char number = seatingArrangementList.charAt(counter); 
			    //Process char
			    seatingArrangement[i][j] = Integer.parseInt(Character.toString(number));
			    counter = counter + 1;
			}
		}
		this.movieDatetime = this.convertDate(this.movieDatetimeStr);
	}

	
	public String getCineplexname() {
		return cineplexName;
	}
	
	public void setCineplexname(String cineplexName) {
		this.cineplexName = cineplexName;
	}
	
	public int getCinemaId() {
		return cinemaId;
	}
	
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	
	public String getMoviename() {
		return movieName;
	}
	
	public void setMoviename(String movieName) {
		this.movieName = movieName;
	}
	
	public void setMoviedatetimeStr(String date_time) {
		this.movieDatetimeStr = date_time;
		this.movieDatetime = this.convertDate(this.movieDatetimeStr);
	}
	
	public String getMoviedatetimeStr () {
		return movieDatetimeStr;
	}
	
	public void setMoviedatetime(Date date_time) {
		this.movieDatetime = date_time;
		this.movieDatetimeStr = this.convertStr(this.movieDatetime);
	}
	
	public Date getMoviedatetime () {
		return movieDatetime;
	}

	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int[][] getSeatingPlan() {
		return seatingArrangement;
	}
	/**
	 * used when updating the seating plan when a person books a ticket (from int 0 to 1 for the 2-D array)
	 * @param row_selected booking seat row
	 * @param column_selected booking seat column
	 */
	public void updateSeatingPlan(int row_selected, int column_selected) {
		seatingArrangement[row_selected-1][column_selected-1] = 1;
		StringBuilder seatingArrangementList2 = new StringBuilder();
    	for (int i = 0; i < seatingArrangement.length; i++) {
    		for (int j = 0; j < seatingArrangement[0].length;j++) {
    			seatingArrangementList2.append(Integer.toString(seatingArrangement[i][j]));
    		}
    	}
    	this.seatingArrangementList = seatingArrangementList2.toString();
	}
	
	/**
     * This method is to convert a String with format yyyy-MM-dd kk:mm and modifies it to be a Date object.
     * @param datetimeStr the string to be converted
     * @return the Date object
     */
	
	public Date convertDate(String datetimeStr) {
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
//        datetimeStr = new SimpleDateFormat("yyyy").format(new Date()) + "-" + datetimeStr;  // set year as current year
        try {
			date = simpleDateFormat.parse(datetimeStr);
			//return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return date;
	}
	/**
	 * This method is to convert a Date object to a String with format yyyy-MM-dd kk:mm
     * @param dateD the object to be converted
     * @return the string
     */
	public String convertStr(Date dateD) {
		return new SimpleDateFormat("yyyy-MM-dd kk:mm").format(dateD);
	}
	
	/**
	 * This method is the print the seating plan of the showtime in 2-D array format
	 */
	public void printSeatingArrangement() {
		for(int i =0; i < row; i++) {
			for(int k = 0; k < column; k++) {
				System.out.print(seatingArrangement[i][k] + " ");
			}
			System.out.println("");
		}
	}
	
	public boolean checkSeat(int row, int column) {
		if (seatingArrangement[row][column] == 1)
			return true;
		else
			return false;
	}
	/**
	 * This method is for admin to reset the showtime's seating plan to all empty
	 */
	public void resetSeatingPlan() {
		for(int i =0; i < row; i++) {
			for(int k = 0; k < column; k++) {
				seatingArrangement[i][k] = 0;
			}
		}
		StringBuilder seatingArrangementList2 = new StringBuilder();
    	for (int i = 0; i < seatingArrangement.length; i++) {
    		for (int j = 0; j < seatingArrangement[0].length;j++) {
    			seatingArrangementList2.append(Integer.toString(seatingArrangement[i][j]));
    		}
    	}
    	this.seatingArrangementList = seatingArrangementList2.toString();
	}
	/**
	 * this method is for admin to check the showtime's seating plan, whether the seats are all empty
	 * @return true if is empty, false if not
	 */
	public boolean isEmpty() {
		for (int[] a : this.seatingArrangement) {
			for (int b: a) {
				if (b == 1)
					return false;
			}

		}
		return true;
	}
	/**
	 * This method is to string all of the showtime attribute into a single string, to store back into the database
     */
	public String toString() {
        return new StringBuffer(this.cineplexName).append("%")
        		.append(this.cinemaId).append("%")
                .append(this.movieName).append("%")
                .append(this.movieDatetimeStr).append("%")
                .append(this.row).append("%")
                .append(this.column).append("%")
                .append(this.seatingArrangementList)
                .toString();
    }

}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//	HOW TO CREATE SHOWTIMELIST AND READ
//	SuperController test_super = new SuperController();
//	result_from_db = test_super.read_database("database/Showtime_data.txt");
//	ShowTime[] ShowtimeList = new ShowTime[result_from_db.length];
//	for (int j=0;j<result_from_db.length;j++) {
//		ShowtimeList[j] = new ShowTime(result_from_db[j]);
//	}
//	
//	for(int k = 0; k<ShowtimeList.length; k++) {
//		
//		if(ShowtimeList[k].getMoviename().equals("zombieland") ){
//			System.out.println(ShowtimeList[k].getCineplexname()+" " + ShowtimeList[k].getMoviedatetime());
//			ShowtimeList[k].printSeatingArrangement();
//			System.out.println();
//		}
//	}

