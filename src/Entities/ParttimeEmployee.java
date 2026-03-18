package Entities;



import java.time.LocalDate;
//@author Han

public class ParttimeEmployee extends Employee {
    public ParttimeEmployee(String id, String name, String department, String jobTitle, LocalDate dateOfBirth, LocalDate joinDate, double basicSalary, String email) throws Exception{
        super(id, name, department, jobTitle, dateOfBirth, joinDate, basicSalary, email, EmployeeType.PARTTIME);
    }
    @Override
    public double calculateSalary(int workingDays, int overtimeHours, int absentDays){
        double overtimePay = overtimeHours * 50000;
        double absenceDeducetion = absentDays * 100000;
        return getBasicSalary() + overtimePay - absenceDeducetion;
    }
}
