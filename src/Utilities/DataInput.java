
package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Quang
 */
public class DataInput {

    private static Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String inputString(String s) {
        while (true) {
            System.out.println(s + ": ");
            String input = sc.nextLine().trim();
            if (DataValidation.isNotEmpty(input)) {
                return input;
            }
            System.err.println("Input cannot be empty!!!");
        }
    }

    public static String inputEmployeeID(String s) {
        while (true) {
            String id = inputString(s).toUpperCase();
            if (DataValidation.matchesPattern(id, "E\\d{3}")) {
                return id;
            }
            System.err.println("Invalid format!");

        }
    }

    public static LocalDate inputDate(String s) {
        while (true) {
            String dateStr = inputString(s + "dd/MM/yyyy");
            try {
                LocalDate date = LocalDate.parse(dateStr, dateFormatter);
                if (DataValidation.isValidDate(date)) {
                    return date;
                }
                System.err.println("Date cannot be in the future!!!");
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format!!");
            }
        }
    }

    public static double inputDouble(String s, double min, double max) {
        while (true) {
            try {
                System.out.print(s + ": ");
                double value = Double.parseDouble(sc.nextLine());
                if (DataValidation.isDoubleInRange(value, min, max)) {
                    return value;
                }
                System.err.println("Value must be >= " + min +  " and <= " + max + "!!!");
                
            } catch (NumberFormatException e) {
                System.err.println("Invalid number!!!");
            }
        }
    }
    
    public static int inputInt(String s, int min, int max) {
        while (true) {
            try {
                System.out.print(s + ": ");
                int value = Integer.parseInt(sc.nextLine());
                if (DataValidation.isNumberInRange(value, min, max)) {
                    return value;
                }
                System.err.println("Value must be < " + min + " and > " + max);
            } catch (NumberFormatException e) {
                System.err.println("Error: Please enter a valid integer!");
            }
        }
    }
}
