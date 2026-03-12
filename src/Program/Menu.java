/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import BussinessObject.AttendanceManagement;
import java.util.Scanner;

public class Menu {
    public static void Menu(){
        Scanner sc = new Scanner(System.in);
        EmployeeManagement empMng = new EmployeeManagement();
        AttendanceManagement attMng = new AttendanceManagement();
        int choice;
        do{
            System.out.println("\n======================================");
            System.out.println("      HUMAN RESOURCE MANAGEMENT       ");
            System.out.println("======================================");
            System.out.println("1. Add New Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. Attendance Management");
            System.out.println("6. Calculate Salary");
            System.out.println("0. Exit");
            System.out.println("======================================");
            System.out.print("Choose an option: ");
            
            choice = sc.nextInt();
            switch (choice){
                case 1: 
                    empMng.menu();
                    break;
                case 2:
                    attMng.menu();
                    break;
                case 3:
                    
            }
        }
    }
}