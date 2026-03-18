
package BusinessObject;
        

/**
 *
 * @author Hao
 */
import DataObject.AttendanceDAO;
import Entities.Attendance;
import Entities.AttendanceStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceManagement {

    private Map<String, List<Attendance>> attendanceRecords;
    private AttendanceDAO attendanceDAO;

    public AttendanceManagement() {
        attendanceDAO = new AttendanceDAO();
        this.attendanceRecords = new HashMap<>(attendanceDAO.loadAttendance());
    }

    public boolean recordAttendance(String employeeID, LocalDate date, AttendanceStatus status, int overtimeHours) {
        attendanceRecords.putIfAbsent(employeeID, new ArrayList<>());
        List<Attendance> records = attendanceRecords.get(employeeID);
        
        for (Attendance record : records) {
            if (record.getDate().equals(date)) {
                return false; 
            }
        }
        
        records.add(new Attendance(employeeID, date, status, overtimeHours));
        attendanceDAO.saveAttendance(attendanceRecords);
        return true;
    }

    public boolean updateAttendance(String employeeID, LocalDate date, AttendanceStatus newStatus, int newOvertime) {
        if (!attendanceRecords.containsKey(employeeID)) {
            return false; 
        }
        
        List<Attendance> records = attendanceRecords.get(employeeID);
        for (Attendance record : records) {
            if (record.getDate().equals(date)) {
                record.setStatus(newStatus);
                record.setOvertimeHours(newOvertime);
                attendanceDAO.saveAttendance(attendanceRecords);
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
        for (Attendance record : getAttendanceHistory(employeeID)) {
            LocalDate d = record.getDate();
            if (d.getMonthValue() == month && d.getYear() == year && record.getStatus() == AttendanceStatus.PRESENT) {
                totalDays++;
            }
        }
        return totalDays;
    }

    public int getTotalAbsenceDays(String employeeID, int month, int year) {
        int totalAbsence = 0;
        for (Attendance record : getAttendanceHistory(employeeID)) {
            LocalDate d = record.getDate();
            if (d.getMonthValue() == month && d.getYear() == year && record.getStatus() == AttendanceStatus.ABSENT) {
                totalAbsence++;
            }
        }
        return totalAbsence;
    }

    public int getTotalOvertimeHours(String employeeID, int month, int year) {
        int totalOvertime = 0;
        for (Attendance record : getAttendanceHistory(employeeID)) {
            LocalDate d = record.getDate();
            if (d.getMonthValue() == month && d.getYear() == year) {
                totalOvertime += record.getOvertimeHours();
            }
        }
        return totalOvertime;
    }
}
