
package Entities;

import java.time.LocalDate;

/**
 *
 * @author Hao
 */
public class Attendance {
    private final String employeeID;
    private final LocalDate date;
    private String status;
    private int overtimeHours;

    public Attendance(String employeeID, LocalDate date, String status, int overtimeHours) {
        this.employeeID = employeeID.toUpperCase();
        this.date = date;
        this.status = status;
        setStatus(status);
        setOvertimeHours(overtimeHours);
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status){
        if (!DataValidation.isValidAttendanceStatus(status)) {
            throw new IllegalArgumentException("Invalid attendance status");
        }
        this.status = status;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }
    public void setOvertimeHours(int overtimeHours){
        if (!DataValidation.isValidOvertimeHours(overtimeHours)) {
            throw new IllegalArgumentException("Overtime hours cannot be negative or invalid");
        }
        this.overtimeHours = overtimeHours;
    }

    @Override
    public String toString() {
        return String.format("Attendance [ID: %s | Date: %s | Status: %s | OvertimeHours: %dh]", employeeID, date, status, overtimeHours);
    }
   
    
}

