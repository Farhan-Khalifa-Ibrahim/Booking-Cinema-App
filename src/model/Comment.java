package model;
public class Comment implements InterfaceToString {
	protected String Movie;
	protected String Comment;
	
	/**
	 * Constructor used when creating a Comment object
	 * @param movie name of movie
	 * @param Comment comment
	 */
	public Comment(String movie, String Comment) {
		this.Movie = movie;
		this.Comment = Comment;
	}
	
	/**
	 * Constructor strictly used for converting the attributes of the comment database into comment attributes
	 * @param Commentator passing in a single row of comment database consisting attributes in the form of a string array
	 */
	public Comment(String[] Commentator) {
		this.Movie=Commentator[0];
		this.Comment=Commentator[1];
	}
	
	public void setMovie(String movie) {
		this.Movie=movie;
	}
	
	public String getMovie() {
		return this.Movie;
	}
	
	public void setComment(String Comment) {
		this.Comment=Comment;
	}
	
	public String getComment() {
		return this.Comment;
	}
	/**
	 * This method is to string all of the comment attribute into a single string, to store back into the database
     */
	public String toString() {
        return new StringBuffer(this.Movie).append("%").append(this.Comment).append("%").toString();
    }
}
