/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.time.LocalDate;

/**
 *
 * @author Light
 */
public class DataValidation {
    private DataValidation(){}
    
    // 1. Check int num
    public static boolean isNumberInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    // 2. Check double num
    public static boolean isDoubleInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    // 3. Check String null and empty
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    // 4. Check String length
    public static boolean isStringLengthInRange(String value, int min, int max) {
        if (!isNotEmpty(value)) {
            return false;
        }
        int length = value.trim().length();
        return length >= min && length <= max;
    }

    // 5. Check pattern
    public static boolean matchesPattern(String value, String pattern) {
        if (value == null) {
            return false;
        }
        return value.trim().matches(pattern);
    }

    // 6. Check valid date
    public static boolean isValidDate(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now());
    }

    // 7. Check DayOfBirth
    public static boolean isValidDateOfBirth(LocalDate dob) {
        if (dob == null) return false;
        return !dob.isAfter(LocalDate.now().minusYears(18));
    }

    // 8. Check e type
    public static boolean isValidEmployeeType(String type) {
        if (!isNotEmpty(type)) return false;
        String t = type.trim().toLowerCase();
        return t.equals("full-time") || t.equals("part-time");
    }

    // 9. Check attend status
    public static boolean isValidAttendanceStatus(String status) {
        if (!isNotEmpty(status)) return false;
        String s = status.trim().toUpperCase();
        return s.equalsIgnoreCase("PRESENT") || s.equalsIgnoreCase("ABSENT") || s.equalsIgnoreCase("LEAVE");
    }

    // 10. Check overtime
    public static boolean isValidOvertimeHours(int hours) {
        return hours >= 0;
    }
    
    
}
