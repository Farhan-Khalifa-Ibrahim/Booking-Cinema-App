package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This Controller class is the parent class for all the controllers in the package.
 * This class manages all the reading and writing into the database (.txt files).
 *
 */

public class SuperController {
	// this class is for reading in the database.
	// you will have to pass in the file_path e.g database/Movie_data.txt
	// it will return u a 2D array of string
	
	/**
	 * @throws IOException
	 * This method is to read from the database. It returns the nested string array from database,
	 * whereby the columns represent the attributes and the rows represent the number of data.
	 * @param file_path the file name that will be read
	 * @return 2d String array
	 * 
	 */
	public String[][] read_database(String file_path) throws IOException {
		String[][] movie_info ;
		int counter =0,counter2=0;
		FileReader     frStream = new FileReader( file_path );
		BufferedReader brStream = new BufferedReader( frStream  );
		String counterLine ;
		char someChar ='%';
		counterLine = brStream.readLine(); // read in a line

		for (int i =0; i <counterLine.length();i++) {
			if (counterLine.charAt(i) == someChar) {
				counter2++;
			}
		}
		counter2++;

		while (counterLine !=null) { 
			counter++;
			counterLine = brStream.readLine();
		}
		movie_info = new String[counter][counter2];
		brStream.close();
		int i = 0;
		try {
			frStream = new FileReader( file_path);
			brStream = new BufferedReader( frStream  );
			String inputLine ;
			inputLine = brStream.readLine(); // read in a line
			for (int j =0;j<counter;j++) {
				String[] parts = inputLine.split("%");
				for (int k = 0;k<counter2;k++) {
					movie_info[j][k] = parts[k];
				}
				inputLine = brStream.readLine();
			}
			brStream.close();

		}
		catch ( FileNotFoundException e ) {
			System.out.println( "Error opening the input file!"
					+ e.getMessage() );
			System.exit( 0 );
		}
		return movie_info;

	}
	
	/**
	 * @throws IOException
	 * This method is to write into the database. It takes in the nested string array given
	 * and formats it such that each attribute is separated by "%", and writes it into the .txt file.
	 * @param filePath the file name that will be written into
	 * @param new_movie_info the updated movie information after any update
	 * 
	 */

	public void writeData(String filePath, String[][] new_movie_info) throws IOException {
		try {
			FileWriter     fwStream = new FileWriter( filePath );
			BufferedWriter bwStream = new BufferedWriter( fwStream  );

			for (int l =0;l<new_movie_info.length;l++) {
				String newEntry = new_movie_info[l][0];
				for (int k = 1;k<new_movie_info[0].length;k++) {
					if(k<new_movie_info[0].length) {
						newEntry = newEntry+"%";
					}
					newEntry = newEntry + new_movie_info[l][k];

				}
				bwStream.write(newEntry);
				if(l<new_movie_info.length-1) {
					bwStream.newLine();
				}
				bwStream.flush();
			}
			bwStream.close();
		}


		catch ( IOException e ) {
		}	

	}
	
	/**
	 * @throws IOException
	 * This method is to write into the database. It takes in the array of Object given
	 * and writes it into the .txt file.
	 * @param filePath the file name that will be written into
	 * @param o the array of Object
	 * 
	 */
	public void writeData(String filePath, Object[] o) throws IOException {
		try {
			FileWriter     fwStream = new FileWriter( filePath );
			BufferedWriter bwStream = new BufferedWriter( fwStream  );
			int temp=0;
			temp = 0;
			int temp2 = o.length;
			for (Object currentObject : o) {
				temp ++;
				String entry = currentObject.toString();
				bwStream.write(entry);
				if (temp<temp2) {
				bwStream.newLine();
				}
				bwStream.flush();
			}
			bwStream.close();
			
		}


		catch ( IOException e ) {
		}	

	}
	
	/**
	 * @throws IOException
	 * This method is to print the contents of a single Object. 
	 * @param o the Object whose content is to be printed
	 * 
	 */
	public void printObject(Object o) throws IOException {

				String entry = o.toString();
				String my_new_str = entry.replace("%", " ");
				System.out.println(my_new_str);

	}
	
	/**
	 * @throws IOException
	 * This method is to insert a single object into the database.
	 * @param filePath the file name that will be written into
	 * @param o Object to be written to file
	 * 
	 */
	public void insertObject(String filePath, Object o) throws IOException {

		String entry = o.toString();
		
		File file = new File(filePath);
		FileWriter fr = new FileWriter(file, true);
		BufferedWriter br = new BufferedWriter(fr);
		br.newLine();
		br.write(entry);
		
		br.close();
		fr.close();

}

}
