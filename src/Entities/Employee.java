
package Entities;


import Utilities.DataValidation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {  
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private double basicSalary;
    private String email;
    private String type;
    private boolean active = true;
    //Constructors
    public Employee(String id, String name,
            LocalDate dateOfBirth,String email,double basicSalary) throws Exception {
       
        setId(id);
        setEmail(email);
        setName(name);
        setDateOfBirth(dateOfBirth);
        setSalary(basicSalary);
    }
   
    public void setId(String value) throws Exception{
        if(!DataValidation.checkStringWithFormat(value.toUpperCase(),"E\\d{3}")){
            throw new Exception("Id is invalid. The correct format:Exxx, with x is digits");
        }
        this.id = value;
    }
    
     public String getId() {
        return id.toUpperCase();
    }
  
    public String getName() {
        return toTiteCase(name);
    }
    public void setName(String name) throws Exception{
        if(!DataValidation.checkStringWithFormat(name,"[A-Za-z|\\s]{3,50}")){
            throw new Exception("Name must be from 3 to 50 characters.");
        }
        this.name = toTiteCase(name);
    }
    public String toTiteCase(String value) {
        String s = "";
        value = value.trim().replaceAll("\\s+", " ").toLowerCase();
        String[] words = value.split(" ");
        for (String word : words) {
            char[] arr = word.toCharArray();
            arr[0] = Character.toUpperCase(arr[0]);
            s = s + new String(arr) + " ";
        }
        return s.trim();
    }
    
    public String getEmail() {        
        return email;
    }

    public void setEmail(String email)  throws Exception {
        if(!DataValidation.checkStringWithFormat(email,"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
            throw new Exception("Email is invalid.");
        }
        this.email = email;
    }  

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws Exception {
        if(dateOfBirth == null){
             throw new Exception("DateOfBirth is invalid.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public double getSalary() {
        return basicSalary;
    }

    public void setSalary(double salary) {
        this.basicSalary = salary;
    }
          
    public String toString(){
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        return String.format("%s, %s, %s, %s, %.2f",getId(),
                getName(), dateOfBirth.format(formatters),email,basicSalary);
    }
}

