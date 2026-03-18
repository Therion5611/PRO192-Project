/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import Entities.Employee;
import Entities.EmployeeType;
import Entities.FulltimeEmployee;
import Entities.ParttimeEmployee;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author nguye
 */
public class EmployeeMenu {

    public static Employee inputEmployee(Scanner sc, String ID) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Employee emp = null;
        try {
            System.out.print("Enter name:");
            String name = sc.nextLine();

            System.out.print("Enter department:");
            String department = sc.nextLine();

            System.out.print("Enter job title: ");
            String jobTitle = sc.nextLine();

            System.out.print("Enter date of birth (dd/mm/yyyy):");
            LocalDate dob = LocalDate.parse(sc.nextLine(), formatter);

            System.out.print("Enter join date (dd/mm/yyyy):");
            LocalDate joinDate = LocalDate.parse(sc.nextLine(), formatter);

            System.out.print("Enter basic salary:");
            double salary = sc.nextDouble();
            sc.nextLine();

            System.out.print("Enter email:");
            String email = sc.nextLine();

            System.out.print("Enter type (FullTime/PartTime):");
            String type = sc.nextLine().toUpperCase();
            EmployeeType typeOfEmployyee;
            try {
                typeOfEmployyee = EmployeeType.valueOf(type);
            } catch (Exception e) {
                System.out.println("Error: Type must be FULLTIME or PARTTIME");
                return null;
            }
            if(typeOfEmployyee == EmployeeType.FULLTIME){
                emp = new FulltimeEmployee(ID, name, department, jobTitle, dob, joinDate, salary, email);
            }else{
                emp = new ParttimeEmployee(ID, name, department, jobTitle, dob, joinDate, salary, email);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
        return emp;
    }
}
