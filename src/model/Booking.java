package model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This Model class contains all information of a booking - including the name of
 * the moviegoer, the email address, the date and time, the cineplex name, 
 * the cinema ID, the movie name, the seat booked, the price of the ticket, 
 * the age of the person, and the ticket ID.
 *
 * @version 1.0
 */

public class Booking implements InterfaceToString{
	
	private String name;
	private String email;
	private String movieDatetimeStr;
	private Date movieDatetime;
	private String cineplexName;
	private int cinemaId;
	private String movieName;
	private int[] seat = new int[2];
	private double price;
	private int age;
	private String tId;
	
	/**
	 * Constructor to be used when showing no booking has been done
	 * All attributes are set to null for string or 0 for int
	 */
	public Booking () {
		
		name = null;
		email = null;
		movieDatetime = null;
		cineplexName = null;
		cinemaId = 0;
		movieName = null;
		seat = null;
		price = 0;
		age = 0;
		tId = null;
	}
	
	/**
	 * Constructor used for booking a ticket
	 * @param name name of person booking the ticket
	 * @param email of person booking the ticket
	 * @param movieDatetime showtime of movie in format of string
	 * @param cineplexName cineplex's name of booking
	 * @param cinemaId cineplex's id of booking
	 * @param movieName movie's name
	 * @param seat seat in the from of "[x,y]" where x is the row num, y is the column num
	 * @param price final price of the ticket
	 * @param age age of the person viewing the movie
	 * @param tId transaction ID of booking
	 */
	
	public Booking (String name, String email, String movieDatetime,
			String cineplexName, int cinemaId, String movieName, String seat, 
			double price,int age, String tId) {
		this.name = name;
		this.email = email;
		this.movieDatetimeStr = movieDatetime;
		this.cineplexName = cineplexName;
		this.cinemaId = cinemaId;
		this.movieName = movieName;
		String[] seat_string = seat.split(",");
		for(int a = 0; a < seat_string.length; a++) {
			this.seat[a] = (Integer.parseInt(seat_string[a]));
		}
		this.price = price;
		this.age = age;
		this.tId = tId;
		
		this.movieDatetime = this.convertDate(this.movieDatetimeStr);
	}
	
	/**
	 * Constructor used for booking a ticket
	 * @param name name of person booking the ticket
	 * @param email of person booking the ticket
	 * @param movieDatetime showtime of movie in format of Date object
	 * @param cineplexName cineplex's name of booking
	 * @param cinemaId cineplex's id of booking
	 * @param movieName movie's name
	 * @param seat seat in the from of "[x,y]" where x is the row num, y is the column num
	 * @param price final price of the ticket
	 * @param age age of the person viewing the movie
	 * @param tId transaction ID of booking
	 */
	
	public Booking (String name, String email, Date movieDatetime,
			String cineplexName, int cinemaId, String movieName, String seat, 
			double price,int age, String tId) {
		this.name = name;
		this.email = email;
		this.movieDatetime = movieDatetime;
		this.cineplexName = cineplexName;
		this.cinemaId = cinemaId;
		this.movieName = movieName;
		String[] seat_string = seat.split(",");
		for(int a = 0; a < seat_string.length; a++) {
			this.seat[a] = (Integer.parseInt(seat_string[a]));
		}
		this.price = price;
		this.age = age;
		this.tId = tId;
		
		this.movieDatetimeStr = this.convertStr(this.movieDatetime);
	}
	/**
	 * Constructor strictly used for converting the attributes of the booking database into model attributes
	 * @param booking_row passing in a single row of booking database consisting attributes in the form of a string array
	 */
	public Booking (String[] booking_row) {
		this.name = booking_row[0];
		this.email = booking_row[1];
		this.movieDatetimeStr = booking_row[2];
		this.cineplexName = booking_row[3];
		this.cinemaId = Integer.parseInt(booking_row[4]);

		String[] seat_string = booking_row[5].split(",");
		for(int a = 0; a < seat_string.length; a++) {
			this.seat[a] = (Integer.parseInt(seat_string[a]));
		}
		this.movieName = booking_row[6];
		this.price = Double.parseDouble(booking_row[7]);
		this.age = Integer.parseInt(booking_row[8]);
		this.tId = booking_row[9];
		
		this.movieDatetime = this.convertDate(this.movieDatetimeStr);
	}
	
	public String getName () {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getMoviedatetime () {
		return movieDatetime;
	}
	
	public String getMoviedatetimeStr () {
		return movieDatetimeStr;
	}
	
	public void setMoviedatetime(Date date_time) {
		this.movieDatetime = date_time;
		this.movieDatetimeStr = this.convertStr(this.movieDatetime);
	}
	
	public void setMoviedatetimeStr(String date_time) {
		this.movieDatetimeStr = date_time;
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
	
	public void setCineplexId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	
	public String getMoviename() {
		return movieName;
	}
	
	public void setMoviename(String movieName) {
		this.movieName = movieName;
	}
	
	public int[] getSeat() {
		return seat;
	}
	
	public void setSeat(String seat_no_string) {
		String[] seat_string = seat_no_string.split(",");
		for(int a = 0; a < seat_string.length; a++) {
			this.seat[a] = (Integer.parseInt(seat_string[a]));
		}
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setTID(String tId) {
		this.tId = tId;
	}
	
	public String getTID() {
		return tId;
	}
	
	
//	public String[] convertObjtoString() {
//		String[] booking_info ;
//		booking_info = new String[9];
//		booking_info[0] = this.name;
//		booking_info[1] = this.email;
//		booking_info[2] = this.movieDatetimeStr;
//		booking_info[3] = this.cineplexName;
//		booking_info[4] = Integer.toString(this.cinemaId);
//		booking_info[5] = seat[0] + "," + seat[1];
//		booking_info[6] = this.movieName;
//		booking_info[7] = Double.toString(this.price);
//		booking_info[8] = Integer.toString(this.age);
//		booking_info[9] = this.tId;
//				
//		return booking_info;
//	}
	
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
	 * This method is to string all of the booking attribute into a single string, to store back into the database
     */
	public String toString() {
        return new StringBuffer(this.name).append("%")
        		.append(this.email).append("%")
                .append(this.movieDatetimeStr).append("%")
                .append(this.cineplexName).append("%")
                .append(this.cinemaId).append("%")
                .append(this.seat[0]).append(",").append(this.seat[1]).append("%")
                .append(this.movieName).append("%")
                .append(this.price).append("%")
                .append(this.age).append("%")
                .append(this.tId)
                .toString();
    }
	
//////////////////////////////////////////////////////////////////////////////////////////////////////
// HOW TO CREATE BOOKINGLIST AND READ
// 
//	String[][] result_from_db = null;
//	SuperController test_super = new SuperController();
//	result_from_db = test_super.read_database("database/Booking_data.txt");
//	Booking[] BookingList = new Booking[result_from_db.length];
//	for (int j=0;j<result_from_db.length;j++) {
//		BookingList[j] = new Booking(result_from_db[j]);
//	}
//	
////	Booking test_single_booking = new Booking(result_from_db[0][2]);
//	for(int k = 0; k<BookingList.length; k++) {
//		
//		if(BookingList[k].getName().equals("karen") ){
//			System.out.println(BookingList[k].getEmail());
//		}
//	}
	
// HOW TO WRITE INTO DB
//		String[][] result_from_db = null;
//	SuperController test_super = new SuperController();
//	result_from_db = test_super.read_database("database/Booking_data.txt");
//	Booking[] BookingList = new Booking[result_from_db.length];
//	for (int j=0;j<result_from_db.length;j++) {
//		BookingList[j] = new Booking(result_from_db[j]);
//	}
//	
//	Booking test_single_booking = new Booking(result_from_db[0][2]);
//	for(int k = 0; k<BookingList.length; k++) {
//		
//		if(BookingList[k].getName().equals("zhen yi") ){
//			System.out.println(BookingList[k].getEmail());
//		}
//	}
//	BookingList[2].setEmail("adams1@gmail.com");
//	
//	String[][] update_array = new String[BookingList.length][8];
//	for(int i = 0; i<BookingList.length; i++) {
//		update_array[i] = BookingList[i].convertObjtoString();
//	}
//	test_super.writeData("database/Booking_data.txt", update_array);
}