
package Entities;

import Utilities.DataValidation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Employee {
    private final String id;                
    private String name;
    private String department;            
    private String jobTitle;
    private LocalDate dateOfBirth;
    private LocalDate joinDate;             
    private double basicSalary;
    private String email;
    private String type;                    // "Full-time" hoặc "Part-time"
    private boolean active = true;

   
    //Constructor
    public Employee(String id, String name, String department, String jobTitle, LocalDate dateOfBirth, LocalDate joinDate, double basicSalary, String email, String type) throws Exception {
        if (id == null || !DataValidation.matchesPattern(id.toUpperCase(), "E\\d{3}")) {
            throw new Exception("Invalid Employee ID format! Must be E followed by 3 digits");
        }
        this.id = id.toUpperCase();
        setName(name);
        setDepartment(department);
        setJobTitle(jobTitle);
        setDateOfBirth(dateOfBirth);
        setJoinDate(joinDate);
        setBasicSalary(basicSalary);
        setEmail(email);
        setType(type);
        
    }

    //Validate email
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    
    
    
    //Getter setter
    public String getId(){
        return id;
    }
    
    public String getName() {
        return toTitleCase(name);
    }

    public void setName(String name) throws Exception {
        if (name == null || !DataValidation.matchesPattern(name.trim(), "[A-Za-z\\s]{3,60}")){
            throw new Exception("Name must be 3-60 alphabetic character (BR2)");
        }
        this.name = name.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) throws Exception{
        if (department == null || department.trim().isEmpty()) {
            throw new Exception ("Department cannot be empty!");
        }
        this.department = department.trim();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) throws Exception {
        if (jobTitle == null || jobTitle.trim().isEmpty()) {
            throw new Exception("Job title cannot be empty.");
        }
        this.jobTitle = jobTitle;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dob) throws Exception {
        if (dob == null || dob.isAfter(LocalDate.now().minusYears(18))) {
            throw new Exception("Date of birth invalid (at least 18 years old)!");
        }
        this.dateOfBirth = dob;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) throws Exception {
        if (joinDate == null || joinDate.isAfter(LocalDate.now())) {
            throw new Exception("Join date cannot be in the future!");
        }
        this.joinDate = joinDate;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double salary) throws Exception {
        if (salary <= 0) {
            throw new Exception("Basic salary must be greater than 0.");
        }
        this.basicSalary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email == null || !DataValidation.matchesPattern(email,EMAIL_PATTERN)) {
            throw new Exception("Invalid email!");
        }
        this.email = email.trim().toLowerCase();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws Exception {
        if (type == null || (!type.equalsIgnoreCase("Full-time") && !type.equalsIgnoreCase("Part-time"))) {
            throw new Exception("Employee type must be 'Full-time' or 'Part-time'.");
        }
        this.type = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    //Abstract class
    public abstract double calculateSalary(int workingDays, int overtimeHours, int absentDays);
    
    
    //Phuong Thuc Ho Tro getName()
    private String toTitleCase(String str) {
        if (str == null || str.trim().isEmpty()) return "";
        String trimmed = str.trim().replaceAll("\\s+", " ").toLowerCase();
        String[] words = trimmed.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
    }
    
    //ToString();
    public String toDetailString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "ID          : " + id + "\n" +
               "Name        : " + toTitleCase(name) + "\n" +
               "Department  : " + department + "\n" +
               "Job Title   : " + jobTitle + "\n" +
               "Type        : " + type + "\n" +
               "DOB         : " + dateOfBirth.format(fmt) + "\n" +
               "Join Date   : " + joinDate.format(fmt) + "\n" +
               "Basic Salary: " + String.format("%,.0f VND", basicSalary) + "\n" +
               "Email       : " + email + "\n" +
               "Status      : " + (active ? "Active" : "Inactive");
    }
}