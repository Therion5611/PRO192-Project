/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessObject;

import Entities.Employee;

/**
 *
 * @author nguye
 */
public class SalaryManagement {
    private EmployeeManagement empMng;
    private AttendanceManagement attMng;
    public SalaryManagement(EmployeeManagement empMng, AttendanceManagement attMng){
        this.empMng = empMng;
        this.attMng = attMng;
    }
    
    public double calculateSalary(String ID, int month, int year){
        Employee emp = empMng.findEmployeeByID(ID);
        if(emp == null){
            System.out.println("Employee not found");
            return 0;
        }
        int days = attMng.getTotalWorkingDays(ID, month, year);
        int ovt = attMng.getTotalOvertimeHours(ID, month, year);
        int abs = attMng.getTotalAbsenceDays(ID, month, year);
        
        double salary = emp.calculateSalary(days, ovt, abs);
        return salary;
    }
    
    public void xuatSalary(String ID,int month,int year){
        double salary = calculateSalary(ID, month, year);
        System.out.println("===== Salary Result =====");
        System.out.println("ID Employee: " + ID);
        System.out.println("Month & Year: " + month + " , " + year);
        System.out.println("Salary: " + salary);
    }
}
