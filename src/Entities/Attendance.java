package Entities;

import Utilities.DataValidation;
import java.time.LocalDate;

/**
 *
 * @author Hao
 */
public class Attendance {

    private final String employeeID;
    private final LocalDate date;
    private AttendanceStatus status;
    private int overtimeHours;

    public Attendance(String employeeID, LocalDate date, AttendanceStatus status, int overtimeHours) {
        this.employeeID = employeeID.toUpperCase();
        this.date = date;
        setStatus(status);
        setOvertimeHours(overtimeHours);
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public LocalDate getDate() {
        return date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Attendance status cannot be null");
        }
        this.status = status;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
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
