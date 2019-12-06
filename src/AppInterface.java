import java.io.IOException;
import java.util.*;
import view.*;
import controller.*;

public class AppInterface {
	public static void main(String[] args) throws IOException {
		StaffView st = new StaffView();
		MoviegoerView mv = new MoviegoerView();
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			InputOutputController.printHeader("MOBLIMA");
			System.out.println("WELCOME!\n");
			System.out.println("1 Enter as a Moviegoer \n2 Login as Staff");
			System.out.println("3 Quit");
			System.out.print("Enter Choice: ");
			String choice = sc.next();
			int ret =0;
			try {
				ret = Integer.parseInt(choice);
     		  if (ret == 1)
  				mv.mainPage();
     		  else if (ret ==2)
  				st.loginPage();
     		  else if (ret ==3) {
     			  System.out.println("Exiting system ... ...");
     			  return;
     		  }
     		  else
     			 System.out.println("Invalid input!!!, please enter option 1-3");
     			  
     		}
     		catch (NumberFormatException e)
     		{
     		   System.out.println("Invalid input!!!, please enter option 1-3");
     		}
		}
	}

}
