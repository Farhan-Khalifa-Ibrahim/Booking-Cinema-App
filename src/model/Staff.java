package model;

/**
 * This Model class contains all information of a staff - including the staff's
 * ID and the staff password for logging in purposes.
 *
 * @version 1.0
 */

public class Staff implements InterfaceToString{
	private String staffId;
	private String staffPass;
	
	/**
	 * Constructor to be used when there is no staff
	 * All attributes are set to null for string or 0 for int
	 */
	public Staff() {
		this.staffId = null;
		this.staffPass = null;
	}
	/**
	 * Constructor used when creating a staff object
	 * @param staffId staff ID(name)
	 * @param staffPass staff password
	 */
	public Staff(String staffId, String staffPass) {
		this.staffId = staffId;
		this.staffPass = staffPass;
	}
	/**
	 * Constructor strictly used for converting the attributes of the Staff database into movie attributes
	 * @param db passing in a single row of movie database consisting attributes in the form of a string array
	 */
	public Staff(String[] db) {
		this.staffId = db[0];
		this.staffPass = db[1];
	}
	
	public String getStaffID() {
		return this.staffId;
	}
	
	public void setStaffID(String staffId) {
		this.staffId = staffId;
	}
	
	public String getStaffPass() {
		return this.staffPass;
	}
	
	public void setStaffPass(String staffPass) {
		this.staffPass = staffPass;
	}
	/**
	 * This method is to string all of the staff attribute into a single string, to store back into the database
     */
	public String toString() {
        return new StringBuffer(this.staffId).append("%")
                .append(this.staffPass)
                .toString();
	}
	
	
}