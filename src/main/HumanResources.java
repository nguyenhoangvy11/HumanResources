package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import model.Employee;
import model.Manager;
import model.Staff;

public class HumanResources {

    public static List<Staff> listStaff = new ArrayList<>();
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        while (true) {
            System.out.println("-------------------------");
            System.out.println("1. Display employee.");
            System.out.println("2. Display department.");
            System.out.println("3. Display employee by department.");
            System.out.println("4. Enter a new employee.");
            System.out.println("5. Search employee by name.");
            System.out.println("6. Display the payroll of employees throughout the company.");
            System.out.println("7. Display employee payroll by ascending order");
            System.out.println("8.Exit. \n");
            int choice = inputInt("choice");
            if (choice == 8) {
                break;
            }
            choiceProcess(choice);
        }
    }

    // processing option from user
    public static void choiceProcess(int choice) {
        switch (choice) {
            case 1:
                displayStaff();
                break;
            case 2:
                displayDepartment();
                break;
            case 3:
                displayEmployeeByDepartment();
                break;
            case 4:
                add();
                break;
            case 5:
                searchEmployeeByName();
                break;
            case 6:
                displaySalary(listStaff);
                break;
            case 7:
                List<Staff> listOrder = listStaffOrderAscending(listStaff);
                displaySalary(listOrder);
                break;
            default:
                System.out.println("You need to enter number 1 - 8 !!!");
                break;
        }
    }

    // integet input from user by title
    public static int inputInt(String title) {
        int result;
        while (true) {
            System.out.print("Enter " + title + ": ");
            try {
                result = Integer.parseInt(input.readLine());
                break;
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                System.out.println("You need enter a number !!!");
            }
        }
        return result;
    }

    // string input from user by title
    public static String inputString(String title) {
        String result = "";
        System.out.print("Enter " + title + ": ");
        try {
            result = input.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Please enter character");
        }
        return result;
    }

    // user add a new staff include Employee and Manager
    public static void add() {
        int kindStaff = kindOfStaff();
        String id = String.format("%03d", listStaff.size()+1);
        String name = inputString("Name");
        int age = inputInt("age");
        int salary = inputInt("salary");
        int dayCome = inputInt("day come");
        int dayOff = inputInt("day off");
        String department = inputString("department");

        switch (kindStaff) {
            case 0:
                addEmployee(id.toUpperCase(), name, age, salary, dayCome, dayOff, department);
                break;
            case 1:
                addManager(id.toUpperCase(), name, age, salary, dayCome, dayOff, department);
                break;
        }

    }

    // choose Employee or Manager when add new user
    public static int kindOfStaff() {
        while (true) {
            System.out.print("What is your titles (0 = Employee, 1 = Manager): ");
            try {
                int result = Integer.parseInt(input.readLine());
                if (result == 0 || result == 1) {
                    return result;
                }
                System.out.println("You need to choose 0 or 1");
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Please enter a number");
            }
        }
    }

    // add new Employee 
    public static void addEmployee(String id, String name, int age, int salary, int dayCome, int dayOff,
            String department) {
        int overTime = inputInt("over time");
        Staff employee = new Employee(id, name, age, salary, dayCome, dayOff, department);
        listStaff.add(employee);
    }

    // add new Manager
    public static void addManager(String id, String name, int age, int salary, int dayCome, int dayOff,
            String department) {
        int choice = 0;
        String titles = "";

        while (true) {
            System.out.println("What is your details (1 = Business Leader, 2 = Project Leader, 3 = Technical Leader) ");
            choice = inputInt("choice");
            if (choice > 0 && choice < 4) {
                break;
            }
            System.out.println("Pleease enter number 1 - 3 !!!");
        }

        switch (choice) {
            case 1:
                titles = "Business Leader";
                break;
            case 2:
                titles = "Project Leader";
                break;
            case 3:
                titles = "Technical Leader";
                break;
        }

        Staff manager = new Manager(id, name, age, salary, dayCome, dayOff, department, titles);
        listStaff.add(manager);
    }

    // display all staff
    public static void displayStaff(){
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "Name", "Age", "Salary", "Day come", "Day off", "Department");
        for(Staff staff: listStaff){
            System.out.println(staff.toString());
        }
    }

    // display list all department 
    public static void displayDepartment(){
        List<Department> listDepartment = new ArrayList<>();
        List<String> listNameDepartment = listNameDepartment();

        int id = 0;
        for(String nameDepartment: listNameDepartment){
            id++;
            Department department = new Department("DEPT-" + id, nameDepartment, 0);
            listDepartment.add(department);
        }

        for(Staff staff: listStaff){
            for(Department department: listDepartment){
                if(staff.department.equalsIgnoreCase(department.nameDepartment)){
                    department.increaseAmountEmployee();
                }
            }
        }

        System.out.printf("%-10s%-20s%-20s\n", "ID", "Department", "Amount employee");
        for(Department department: listDepartment){
            System.out.println(department.toString());
        }

    }

    // get list department 
    public static List<String> listNameDepartment(){
        List<String> listNameDepartment = new ArrayList<>();
        for(Staff staff: listStaff){
            if(!listNameDepartment.contains(staff.department)){
                listNameDepartment.add(staff.department);
            }
        }
        return listNameDepartment;
    }

    // display employee by department
    public static void displayEmployeeByDepartment(){
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "Name", "Age", "Salary", "Day come", "Day off", "Department");
        for(String nameDepartment: listNameDepartment()){
            for(Staff staff: listStaff){
                if(nameDepartment.equalsIgnoreCase(staff.department)){
                    System.out.println(staff.toString());
                }
            }
        }
    }

    // function search employee by name
    public static void searchEmployeeByName(){
        String name = inputString("Name");
        Staff staff = search(name);
        if(staff == null){
            System.out.println("Staff not found !!!!");
            return;
        }
        
        System.out.printf("%-10s%-10s%-10");
        System.out.println(staff.toString());
    }

    
    public static Staff search(String name){
        for(Staff staff: listStaff){
            if(staff.name.equalsIgnoreCase(name)){
                return staff;
            }
        }
        return null;
    }

    // display slalary by List<Staff>
    public static void displaySalary(List<Staff> list){
        System.out.printf("%-10s%-20s%-20s%-20s\n", "ID", "Name", "Salary", "Department");
        for(Staff staff: list){
            System.out.printf("%-10s%-20s%-20d%-20s\n", staff.id, staff.name, staff.salary, staff.department);
        }
    }

    // rearrange in ascending order by List<Staff> 
    public static List<Staff> listStaffOrderAscending(List<Staff> list){
        List<Staff> order  = new ArrayList<>(list);
        
        for(int i = 0; i < order.size() - 1; i++){
            for(int j = i+1; j < order.size(); j++){
                Staff left = order.get(i);
                Staff right = order.get(j);
                if(left.salary > right.salary){
                    order.set(i, right);
                    order.set(j, left);
                }
            }
        }

        return order;
    }
}