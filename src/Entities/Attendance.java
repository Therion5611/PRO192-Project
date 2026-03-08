/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class Attendance {
    private final String employeeID;
    private final LocalDate date;
    private String status;
    private int overtimeHours;

    public Attendance(String employeeID, LocalDate date, String status, int overtimeHours) {
        this.employeeID = employeeID;
        this.date = date;
        this.status = status;
       if (status != null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
        this.overtimeHours = overtimeHours;
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

    public int getOvertimeHours() {
        return overtimeHours;
    }

    @Override
    public String toString() {
        return "Attendence{" + "employeeID=" + employeeID + ", date=" + date + ", status=" + status + ", overtimeHours=" + overtimeHours + '}';
    }
   
    
}
