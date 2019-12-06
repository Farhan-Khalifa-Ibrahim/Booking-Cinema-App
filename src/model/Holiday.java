package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This Model class contains all information of a holiday - including its name
 * and date.
 *
 * @version 1.0
 */

public class Holiday implements InterfaceToString {
	private String holidayName;
	private Date date;
	private String dateStr;
//	protected String date_only;
//	protected String peak_dates = "05,06,07,12,13,14,19,20,21,27,28,29"; 
	/**
	 * Constructor to be used when there is no Holiday available
	 * All attributes are set to null
	 */
	public Holiday(){
		this.holidayName = null;
		this.date = null;
	}
	/**
	 * Constructor used when creating a Holiday object
	 * @param holiday_name name of holiday
	 * @param date date in string
	 */
	public Holiday(String holiday_name,String date){
		this.holidayName = holiday_name;
		this.dateStr = date;
		this.date = this.convertDate(this.dateStr);

	}
	/**
	 * Constructor used when creating a Holiday object
	 * @param holiday_name name of holiday
	 * @param date Date object
	 */
	public Holiday(String holiday_name,Date date){
		this.holidayName = holiday_name;
		this.date = date;
		this.dateStr = this.convertStr(this.date);

	}
	/**
	 * Constructor strictly used for converting the attributes of the Holiday database into Holiday attributes
	 * @param db passing in a single row of Holiday database consisting attributes in the form of a string array
	 */
	public Holiday(String[] db) {
		this.holidayName = db[0];
		this.dateStr =db[1];
		this.date = convertDate(this.dateStr);
		

	}

	public String getHolidayname() {
		return this.holidayName;
	}
	
	public void setHolidayname(String holiday_name) {
		this.holidayName = holiday_name;
	}
	
	public String getDateStr() {
		return this.dateStr;
	}
	
	public void setDateStr(String date) {
		this.dateStr = date;
		this.date = this.convertDate(this.dateStr);
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
		this.dateStr = this.convertStr(this.date);
	}
	
//	public boolean isPeak(String date) {
//		return (peak_dates.contains(date_only));
//	}
	
	/**
     * This method is to convert a String with format yyyy-MM-dd and modifies it to be a Date object.
     * @param dateStr the string to be converted
     * @return the Date object
     */
	
	public Date convertDate(String dateStr) {
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        dateStr = new SimpleDateFormat("yyyy").format(new Date()) + "-" + dateStr; 
        try {
			date = simpleDateFormat.parse(dateStr);
			//return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return date;
	}
	
	/**
	 * This method is to convert a Date object to a String with format yyyy-MM-dd
     * @param dateD the object to be converted
     * @return the string
     */
	public String convertStr(Date dateD) {
		return new SimpleDateFormat("yyyy-MM-dd").format(dateD);
	}
	/**
	 * This method is to string all of the holiday attribute into a single string, to store back into the database
     */
	public String toString() {
        return new StringBuffer(this.holidayName)
        		.append("%").append(this.dateStr).toString();
    }
}
