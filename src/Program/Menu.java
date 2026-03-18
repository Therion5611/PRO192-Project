/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import BusinessObject.AttendanceManagement;
import BusinessObject.EmployeeManagement;
import BusinessObject.SalaryManagement;
import Entities.Employee;
import java.util.Scanner;

public class Menu {

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        EmployeeManagement empMng = new EmployeeManagement();
        AttendanceManagement attMng = new AttendanceManagement();
        SalaryManagement salMng = new SalaryManagement(empMng, attMng);

        int choice = -1;
        while (choice != 0) {
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
            sc.nextLine();
            switch (choice) {
                case 1:
                    String id;

                    while (true) {

                        System.out.print("Enter ID:");
                        id = sc.nextLine();

                        if (empMng.findEmployeeByID(id) != null) {
                            System.out.println("ID already exists");
                        } else {
                            break;
                        }
                    }

                    Employee emp = EmployeeMenu.inputEmployee(sc, id);
                    if (empMng.addEmployee(emp)) {
                        System.out.println("Employee added successfully");
                    } else {
                        System.out.println("Add employee failed");
                    }
                    break;

                case 2:
                    String updateID;

                    while (true) {

                        System.out.print("Enter ID:");
                        updateID = sc.nextLine();

                        if (empMng.findEmployeeByID(updateID) == null) {
                            System.out.println("ID not found!");
                        } else {
                            break;
                        }
                    }

                    Employee newEmp = EmployeeMenu.inputEmployee(sc, updateID);
                    if (empMng.updateEmployee(updateID, newEmp)) {
                        System.out.println("Updated successful");
                    } else {
                        System.out.println("Update failed");
                    }
                    break;

                case 3:
                    String removeID;

                    while (true) {

                        System.out.print("Enter ID:");
                        removeID = sc.nextLine();

                        if (empMng.findEmployeeByID(removeID) == null) {
                            System.out.println("ID not found!");
                        } else {
                            break;
                        }
                    }
                    empMng.removeEmployee(removeID);
                    System.out.println("Removed successfully");
                    break;
                case 4:
                    empMng.printAllEmployees();
                    break;
                case 5:
                    AttendanceMenu.attendanceMenu(sc, attMng);
                    break;
                case 6:
                    System.out.print("Enter Employee ID: ");
                    String salaryID = sc.nextLine();
                    System.out.print("Enter month: ");
                    int month = sc.nextInt();
                    System.out.print("Enter year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    salMng.xuatSalary(salaryID, month, year);
                    break;
                case 0:
                    System.out.println("Program closed");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again");
                    break;
            }
        }
    }
}
