/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import BusinessObject.AttendanceManagement;
import Entities.Attendance;
import Entities.AttendanceStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nguye
 */
public class AttendanceMenu {

    private static Attendance inputAttendance(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter ID:");
        String ID = sc.nextLine();

        System.out.println("Enter date: (dd/MM/yyyy)");
        LocalDate date = LocalDate.parse(sc.nextLine(),formatter);

        AttendanceStatus status;

        while (true) {
            try {
                System.out.print("Status (PRESENT/ABSENT/LEAVE):");
                String input = sc.nextLine().toUpperCase();
                status = AttendanceStatus.valueOf(input);
                break;

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status! Please enter PRESENT, ABSENT or LEAVE.");
            }

        }

        System.out.println("Overtime hours:");
        int overtime = sc.nextInt();
        sc.nextLine();

        return new Attendance(ID, date, status, overtime);
    }

    public static void attendanceMenu(Scanner sc, AttendanceManagement attMng) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n===== ATTENDANCE MANAGEMENT =====");
            System.out.println("1. Record Attendance");
            System.out.println("2. Update Attendance");
            System.out.println("3. View Attendance History");
            System.out.println("0. Back");
            System.out.println("==================================");
            System.out.print("Choose an option:");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("The propram closed");
                    break;
                case 1:
                    Attendance a = inputAttendance(sc);
                    if (attMng.recordAttendance(a.getEmployeeID(), a.getDate(), a.getStatus(), a.getOvertimeHours())) {
                        System.out.println("Attendance recorded");
                    } else {
                        System.out.println("Attendance already exists");
                    }
                    break;
                case 2:
                    System.out.println("Enter new attendance infomation:");
                    Attendance upd = inputAttendance(sc);

                    if (attMng.updateAttendance(upd.getEmployeeID(), upd.getDate(), upd.getStatus(), upd.getOvertimeHours())) {
                        System.out.println("Attendance updated");
                    } else {
                        System.out.println("not found");
                    }
                    break;
                case 3:
                    System.out.print("Employee ID: ");
                    String ID = sc.nextLine();
                    List<Attendance> htr = attMng.getAttendanceHistory(ID);
                    if(htr.isEmpty()){
                        System.out.println("not found");
                    }else{
                        for(Attendance att : htr){
                            System.out.println(att);
                        }
                    }
                    break;
                default:
                    System.out.println("invalid choice. Please choose again");
                    break;
            }
        }
    }
}
