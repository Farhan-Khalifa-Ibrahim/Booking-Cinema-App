package scse.cz2002.zzk.lab3;
import java.io.*;
public class test {
	
	  public static void main( String[] args ) throws IOException {
	    String apple = null;
	    String[] movie_info ;
	    movie_info = new String[20];
	    int i = 0;
	    try {
	        FileReader     frStream = new FileReader( "data.txt" );
	        BufferedReader brStream = new BufferedReader( frStream  );
	        String inputLine ;
	        LineNumberReader rdr = new LineNumberReader(frStream);
	        
	          inputLine = brStream.readLine(); // read in a line
	          while (inputLine !=null) {
	        	  String[] parts = inputLine.split("%");
	        	  String title1 = parts[0];
	        	  String title2 = parts[1];
	        	  String title3 = parts[2];
	        	  String title4 = parts[3];
	        	  String title5 = parts[4];
	        	  String title6 = parts[5];
	        	  String title7 = parts[6];
	        	  String title8 = parts[7];
	        	  movie_info[i] = title1+' '+ title2+' '+title3+' '+title4+' '+title5+' '+title6+' '+title7+' '+title8;
	        	  i++;
	        	  inputLine = brStream.readLine();
	        
	          }
	        brStream.close();
	        int j =0;
	        while (movie_info[j]!=null) {
	        	System.out.println(movie_info[j]);
	        	j++;
	        }
	      
	    }
	      catch ( FileNotFoundException e ) {
	        System.out.println( "Error opening the input file!"
	                            + e.getMessage() );
	        System.exit( 0 );
	      }
	    
//	    try {
//	        FileWriter     fwStream = new FileWriter(    "data.txt" );
//	        BufferedWriter bwStream = new BufferedWriter( fwStream  );
//	        PrintWriter    pwStream = new PrintWriter(    bwStream  );
//	        int num ;
//	        
//	          pwStream.println(apple);
//	        pwStream.close();
//	      }
//	      catch ( IOException e ) {
//	         System.out.println( "IO Error!" + e.getMessage() );
//	         e.printStackTrace();
//	         System.exit( 0 );
//	      }


	  }
	}

