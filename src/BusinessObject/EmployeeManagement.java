/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessObject;

import Entities.Employee;
import DataObject.EmployeeDAO;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public class EmployeeManagement {
    private ArrayList<Employee>nv;
    private EmployeeDAO employeeDAO;
    
    public EmployeeManagement(){
        employeeDAO = new EmployeeDAO();
        nv = new ArrayList<>(employeeDAO.loadEmployee());
    }
    
    public Employee findEmployeeByID(String ID){
        for (Employee e : nv){
            if(e.getId().equalsIgnoreCase(ID)){
                return e;
            }
        }
        return null;
    }
    
    public ArrayList<Employee> findEmployeeByName(String name){
        ArrayList<Employee> result = new ArrayList<>();
        for(Employee e : nv){
            Boolean chua = e.getName().toLowerCase().contains(name.toLowerCase());
            if(chua){
                result.add(e);
            }
        }
        return result;
    }
    
    public boolean removeEmployee(String ID){
        Employee e = findEmployeeByID(ID);
        if(e != null){
            nv.remove(e);
            employeeDAO.saveEmployee(nv);
            return true;
        }
        return false;
    }
    
    public boolean updateEmployee(String ID, Employee upd){
        for(int i = 0; i<nv.size(); i++){
            boolean chua = nv.get(i).getId().equalsIgnoreCase(ID);
            if(chua){
                nv.set(i, upd);
                employeeDAO.saveEmployee(nv);
                return true;
            }
        }
        return false;
    }
    
    public boolean addEmployee(Employee e){
        if(findEmployeeByID(e.getId())!=null){
            return false;
        }
        nv.add(e);
        employeeDAO.saveEmployee(nv);
        return true;
    }
    
    public void printAllEmployees(){
        if(nv.isEmpty()){
            System.out.println("There is no employee in this list");
            return;
        }
        for(Employee e : nv){
            System.out.println("");
            System.out.println(e.toDetailString());
        }
    }
    
    public ArrayList<Employee> getAllEmployees(){
        return new ArrayList<>(nv);
    }
}
