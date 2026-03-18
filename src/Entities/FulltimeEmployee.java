package Entities;

//@author Han

import java.time.LocalDate;

public class FulltimeEmployee extends Employee {
    public FulltimeEmployee(String id, String name, String department, String jobTitle, LocalDate dateOfBirth, LocalDate joinDate, double basicSalary, String email) throws Exception{
        super(id, name, department, jobTitle, dateOfBirth, joinDate, basicSalary, email, EmployeeType.FULLTIME);
    }
    @Override
    public double calculateSalary(int workingDays, int overtimeHours, int absentDays){
        double overtimePay = overtimeHours * 80000; //tien tang ca
        double absenceDeducetion = absentDays * 100000; //Tien phat
        return getBasicSalary() + overtimePay - absenceDeducetion; //Tong luong
    }
}
