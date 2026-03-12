
package BussinessObject;
        

/**
 *
 * @author Hao
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Entities.Attendance;
import Entities.AttendanceStatus;

public class AttendanceManagement {

    private final Map<String, List<Attendance>> attendanceRecords;

    public AttendanceManagement() {
        this.attendanceRecords = new HashMap<>();
    }

    public boolean recordAttendance(String employeeID, LocalDate date, String status, int overtimeHours) {
        attendanceRecords.putIfAbsent(employeeID, new ArrayList<>());
        
        List<Attendance> records = attendanceRecords.get(employeeID);
        
        for (Attendance record : records) {
            if (record.getDate().equals(date)) {
                return false; 
            }
        }
        
        records.add(new Attendance(employeeID, date, status, overtimeHours));
        return true;
    }

    public boolean updateAttendance(String employeeID, LocalDate date, String newStatus, int newOvertime) {
        if (!attendanceRecords.containsKey(employeeID)) {
            return false; 
        }
        
        List<Attendance> records = attendanceRecords.get(employeeID);
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getDate().equals(date)) {
                records.set(i, new Attendance(employeeID, date, newStatus, newOvertime));
                return true;
            }
        }
        
        return false; 
    }

    public List<Attendance> getAttendanceHistory(String employeeID) {
        return attendanceRecords.getOrDefault(employeeID, new ArrayList<>());
    }

    public int getTotalWorkingDays(String employeeID, int month, int year) {
        int totalDays = 0;
        List<Attendance> records = getAttendanceHistory(employeeID);
        
        for (Attendance record : records) {
            LocalDate d = record.getDate();
            if (d.getMonthValue() == month && d.getYear() == year && "Present".equalsIgnoreCase(record.getStatus())) {
                totalDays++;
            }
        }
        return totalDays;
    }

    public int getTotalAbsenceDays(String employeeID, int month, int year) {
        int totalAbsence = 0;
        List<Attendance> records = getAttendanceHistory(employeeID);
        
        for (Attendance record : records) {
            LocalDate d = record.getDate();
            if (d.getMonthValue() == month && d.getYear() == year && "Absent".equalsIgnoreCase(record.getStatus())) {
                totalAbsence++;
            }
        }
        return totalAbsence;
    }

    public int getTotalOvertimeHours(String employeeID, int month, int year) {
        int totalOvertime = 0;
        List<Attendance> records = getAttendanceHistory(employeeID);
        
        for (Attendance record : records) {
            LocalDate d = record.getDate();
            if (d.getMonthValue() == month && d.getYear() == year) {
                totalOvertime += record.getOvertimeHours();
            }
        }
        return totalOvertime;
    }
}