package controller;

//import Model.Constant.*;
//import Model.Holiday;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Controller class handles input and also formats to desired output. All methods of this class is static
 * so that they can be called without creating an object of this class.
 *
 */

public class InputOutputController {

    /**
     * This method is to read an integer from standard input whose value should be within a
     * certain range.
     * @param i the lower bound of the input
     * @param j the upper bound of the input
     * @return input within the desired range
     */
    public static int readOption(int i, int j) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readOption(i, j);
        }

        if (choice < i || choice > j) {
            System.out.println("Invalid input, try again.");
            return readOption(i, j);
        }
        return choice;
    }

    /**
     * The method is to read a {@code String} from standard input.
     * @param message the message to be shown to the user
     * @return input from standard input
     */
    public static String readString(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
    *  This method is to read a {@code double} from standard input.
    * @param message the message to be shown to the user 
    * @return input from standard input
    */
    public static double readDouble(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        double output;

        try {
            output = sc.nextDouble();
            return output;
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readDouble(message);
        }
    }
    
    /**
    *  This method is to read a {@code integer} from standard input.
    * @param message the message to be shown to the user 
    * @return input from standard input
    */
    public static int readInteger(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        int output;

        try {
            output = sc.nextInt();
            return output;
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readInteger(message);
        }
    }
    
    /**
     * This method is to read an email address from standard input.
     * @param message the message to be shown to the user
     * @return the input from standard input
     */
    public static String readEmail(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Pattern EMAIL_PATTERN = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = EMAIL_PATTERN.matcher(input);
        if (matcher.matches()) {
            return input;
        }
        else {
            System.out.println("Invalid Email address, try again.");
            return readEmail(message);
        }
    }


//    public static String generateSpaces(int size) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < size; i++) stringBuilder.append(" ");
//        return stringBuilder.toString();
//    }
    
   

    
//    public static boolean readAgeRestriction(int input) {
//    	if (input == 0 || input == 13 || input == 16 || input == 18 || input == 21)
//    		return true;
//    	return false;
//    }
//
//
//    public static boolean readMovieStatus(String input) {
//        switch (input.toLowerCase()) {
//            case "upcoming":
//                return true;
//            case "currently showing":
//                return true;
//            case "not showing":
//                return true;
//            default:
//                return false;
//        }
//    }

    /**
     * This method is to read a {@code String} with format yyyy-MM-dd kk:mm 
     * from standard input and modifies it to be a {@code Date}.
     * @param message the message to be shown to the user
     * @return the {@code Date}
     */
    public static Date readTimeMMddkkmm(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        simpleDateFormat.setLenient(false);
        try {
            String input = readString(message);
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Try again.");
            return readTimeMMddkkmm(message);
        }
    }

    /**
     * This method is to read a {@code String} with format yyyy-MM-dd
     * from standard input and modifies it to be a {@code Date}.
     * @param message the message to be shown to the user
     * @return the {@code Date}
     */
    public static Date readTimeMMdd(String... message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        try {
            String input = readString(message);
//            input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;  // set year as current year
            Date date = simpleDateFormat.parse(input);
            return date;
        } catch (ParseException ex) {
            System.out.println("Wrong format. Try again.");
            return readTimeMMdd(message);
        }
    }
    

    /**
     * This method is to show multiple dashes of given length.
     * @param size the number of dashes to be shown
     */
    public static void generateDashes(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) stringBuilder.append("-");
        System.out.println(stringBuilder.toString()); 
    }


    /**
     * This method is to print specified {@code String} to standard output.
     * @param header the message to be written to standard output
     */
    public static void printHeader(String header) {
        int length = 30;
        for (int i = 0; i < length; i++) System.out.print("=");
        System.out.println();

        int indent = (length - header.length()) / 2;
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.print(header);
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println();

        for (int i = 0; i < length; i++) System.out.print("=");
        System.out.println();
    }

    /**
     * This method is to format a {@code Date} to a {@code String} with format yyyy-MM-dd kk:mm.
     * @param time the {@code Date} to be formatted
     * @return the {@code String} formatted
     */
    public static String formatTimeMMddkkmm(Date time) {
    	return new SimpleDateFormat("yyyy-MM-dd kk:mm").format(time);
    }

    /**
     * This method is to format a {@code Date} to a {@code String} with format yyyy-MM-dd.
     * @param time the {@code Date} to be formatted
     * @return the {@code String} formatted
     */
    public static String formatTimeMMdd(Date time) {
    	return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * This method is to check whether a {@code Date} is a weekend
     * @param time the {@code Date} to check
     * @return true if the {@code Date} is a weekend, false otherwise
     */
    public static boolean isWeekend(Date time) {
        String whatDay = new SimpleDateFormat("EEEE").format(time);
        if (whatDay.equals("Saturday") || whatDay.equals("Sunday")) return true;
        else return false;
    }


//    public static boolean dateEquals(Date d1, Date d2) {
//        return formatTimeMMdd(d1).equals(formatTimeMMdd(d2));
//    }


    /**
     * This method is used to round a {@code double} to a specified decimal place.
     * @param value the value to be rounded
     * @param places the number of decimal places of the result
     * @return the result of the rounding
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}




