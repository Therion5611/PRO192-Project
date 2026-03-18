/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataObject;

import BusinessObject.AttendanceManagement;
import Entities.Attendance;
import Entities.AttendanceStatus;
import Utilities.FileManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nguye
 */
public class AttendanceDAO {

    private String File_Name = "attendance.txt";

    public void saveAttendance(Map<String, List<Attendance>> attMap) {
        ArrayList<String> lines = new ArrayList<String>();
        
        for (Map.Entry<String, List<Attendance>> entry : attMap.entrySet()) {
            for(Attendance a : entry.getValue()){
                String line = String.join(",", 
                a.getEmployeeID(),
                a.getDate().toString(),
                a.getStatus().name(),
                String.valueOf(a.getOvertimeHours()));
                lines.add(line);
            }
        }
        FileManager.writeFile(File_Name, lines);
        System.out.println("Attendance saved successfully");
    }

    public Map<String,List<Attendance>> loadAttendance() {
        Map<String,List<Attendance>> attMap = new HashMap<>();
        List<String> lines = FileManager.readFile(File_Name);
        
        for(String line : lines){
            try {
                String[] parts = line.split(",");
                String id = parts[0];
                LocalDate date = LocalDate.parse(parts[1]);
                AttendanceStatus status = AttendanceStatus.valueOf(parts[2]);
                int ovt = Integer.parseInt(parts[3]);
                Attendance att = new Attendance(id, date, status, ovt);
                attMap.putIfAbsent(id, new ArrayList<>());
                attMap.get(id).add(att);
            } catch (Exception e) {
                System.out.println("Invalid attendance data: " + line);
            }
        }
        return attMap;
    }
}
