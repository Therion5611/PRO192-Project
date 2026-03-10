package Entities;

import java.time.LocalDate;

public class FulltimeEmployee extends Employee {
    public FulltimeEmployee(String id, String name, String department, String jobTitle, LocalDate dateOfBirth, LocalDate joinDate, double basicSalary, String email, String type) throws Exception{
        super(id, name, department, jobTitle, dateOfBirth, joinDate, basicSalary, email, "Full-time");
    }
    @Override
    public double calculateSalary(int workingDays, int overtimeHours, int absentDays){
        double overtimePay = overtimeHours * 80.000;
        double absenceDeducetion = absentDays * 100.000;
        return getBasicSalary() + overtimePay - absenceDeducetion;
    }
}
