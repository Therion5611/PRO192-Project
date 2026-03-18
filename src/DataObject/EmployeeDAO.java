/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataObject;

import Entities.Employee;
import Entities.EmployeeType;
import Entities.FulltimeEmployee;
import Entities.ParttimeEmployee;
import Utilities.FileManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class EmployeeDAO {

    private static final String File_Name = "employee.txt";

    public void saveEmployee(ArrayList<Employee> nv) {
        ArrayList<String> lines = new ArrayList<String>();
        
        for(Employee e : nv){
            String line = String.join(",",
            e.getType().name(),
            e.getId(),
            e.getName(),
            e.getDepartment(),
            e.getJobTitle(),
            e.getDateOfBirth().toString(),
            e.getJoinDate().toString(),
            String.valueOf(e.getBasicSalary()),
            e.getEmail(),
            String.valueOf(e.isActive()));
            lines.add(line);
        }
        FileManager.writeFile(File_Name, lines);
        System.out.println("Employees saved successfully"); 
    }

    public List<Employee> loadEmployee() {
       List<Employee> employees = new ArrayList<>();
       List<String> lines = FileManager.readFile(File_Name);
       
       for(String line : lines){
           try {
               String[] parts = line.split(",");
               String type = parts[0];
               String id = parts[1];
               String name = parts[2];
               String department = parts[3];
               String jobTitle = parts[4];
               LocalDate dob = LocalDate.parse(parts[5]);
               LocalDate joinDate = LocalDate.parse(parts[6]);
               double salary = Double.parseDouble(parts[7]);
               String email = parts[8];
               boolean active = Boolean.parseBoolean(parts[9]);
               
               Employee e;
               if(EmployeeType.valueOf(type) == EmployeeType.FULLTIME){
                   e = new FulltimeEmployee(id, name, department, jobTitle, dob, joinDate, salary, email);
               }else{
                   e = new ParttimeEmployee(id, name, department, jobTitle, dob, joinDate, salary, email);
               }
               
               e.setActive(active);
               employees.add(e);
           } catch (Exception e) {
               System.out.println("Invalid employee data line: " + line);
           }
       }
       return employees;
    }
}
