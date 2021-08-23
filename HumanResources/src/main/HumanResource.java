package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Department;
import model.Employee;
import model.Manager;
import model.Staff;

public class HumanResource {
    public static List<Staff> listStaff = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    private static boolean condition = true;


    public static void main(String[] args){
        while(condition){
            System.out.println("-------------------------");
            System.out.println("1. Display employee.");
            System.out.println("2. Display department.");
            System.out.println("3. Display employee by department.");
            System.out.println("4. Enter a new employee.");
            System.out.println("5. Search a employee by name.");
            System.out.println("6. Display the payroll of employees throughout the company.");
            System.out.println("7. Display employee payroll in ascending order.");
            System.out.println("8. Exit. \n");
            int choice = inputInt("choice");
            humanResource(choice);
        }
    }


    // Hàm xử lý khi user lựa chọn chức năng
    public static void humanResource(int choice){
        switch (choice) {
            case 1:
                displayEmployee();
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
                search();
                break;
            case 6:
                displaySalary();
                break;
            case 7:
                displaySalaryInOrder();
                break;
            case 8:
                condition = false;
                break;
            default:
                System.out.println("You need to choice from 1 to 8 !!!");
        }
    }


    //Hiển thị danh sách tất cả nhân viên.
    private static void displayEmployee(){
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%-20s%n", "ID", "Name", "Age", "Salary", "Day Come", "Department", "Day Off");
        for(Staff staff: listStaff){
            System.out.println(staff.toString());
        }
    }


    //Hiển thị tất cả bộ phân.
    public static void displayDepartment(){
        List<Department> listDepartment = new ArrayList<>();
        List<String> listNameDepartment = listNameDepartment();

        int id = 0;
        for(String s: listNameDepartment){
            int amountEmployee = 0;
            for(Staff staff: listStaff){
                if(s.equalsIgnoreCase(staff.department)){
                    amountEmployee++;
                }
            }
            id++;
            Department addDepartment = new Department("DEPT-" + id, s, amountEmployee);
            listDepartment.add(addDepartment);
        }


        System.out.printf("%-10s%-20s%-20s%n", "ID", "Department", "Amount Staff");
        for(Department d: listDepartment){
            System.out.println(d.toString());
        }
    }


    //Hiển thị các nhân viên theo bộ phận.
    public static void displayEmployeeByDepartment(){
        System.out.printf("%-10s%-20s%-20s%-20s%-20s%-20s%-20s%n", "ID", "Name", "Age", "Salary", "Day Come", "Department", "Day Off");
        for(String s: listNameDepartment()){
            for(Staff staff: listStaff){
                if(s.equalsIgnoreCase(staff.department)){
                    System.out.println(staff.toString());
                }
            }
            System.out.println();
        }
    }


    //Thêm một nhân viên mới vào công ty.
    public static void add(){
        int answer = kindOfStaff();
        String id = inputString("ID",1);
        String name = inputString("name",0);
        int age = inputInt("age");
        int salary = inputInt("salary");
        int dayCome = inputInt("day come");
        String department = inputString("department",1);
        int dayOff = inputInt("day off");

        switch(answer){
            case 0:
                addEmployee(id.toUpperCase(), name, age, salary, dayCome, department, dayOff);
                break;
            case 1:
                addManager(id.toUpperCase(), name, age, salary, dayCome, department, dayOff);
                break;
        }
    }

    //Tìm kiếm nhân viên theo Name.
    public static void search(){
        System.out.print("Enter employee name to search: ");
        String searchKey = scanner.next();
        List<Staff> foundStaff = search(searchKey);
        if(foundStaff.isEmpty()){
            System.out.println("No staff found !");
            return;
        }
        for (Staff staff: foundStaff){
            System.out.println(staff.toString());
        }
    }

    public static List<Staff> search(String name){
        List<Staff> foundStaff = new ArrayList<>();
        for(Staff staff: listStaff){
            if(staff.name.contains(name)){
                foundStaff.add(staff);
            }
        }
        return foundStaff;
    }


    //Hiển thị bảng lương toàn bộ nhân viên.
    public static void displaySalary(){
        List<Staff> salaryOrder = new ArrayList<>(listStaff);
        for(int i = 0; i < salaryOrder.size(); i++){
            for(int j = 0; j < salaryOrder.size() - 1 - i; j++){
                if(salaryOrder.get(j).salary < salaryOrder.get(j+1).salary){
                    Staff temp = salaryOrder.get(j);
                    salaryOrder.set(j, salaryOrder.get(j+1));
                    salaryOrder.set(j+1, temp);
                }
            }
        }
        System.out.printf("%-20s%-20s%n", "Name", "Salary");
        for(Staff i: salaryOrder){
            System.out.printf("%-20s%-20s%n", i.name, i.salary);
        }
    }


    //Hiển thị bảng lương theo thứ tự tăng dần.
    public static void displaySalaryInOrder(){
        List<Staff> salaryOrder = new ArrayList<>(listStaff);
        for(int i = 0; i < salaryOrder.size(); i++){
            for(int j = 0; j < salaryOrder.size() - 1 - i; j++){
                if(salaryOrder.get(j).salary > salaryOrder.get(j+1).salary){
                    Staff temp = salaryOrder.get(j);
                    salaryOrder.set(j, salaryOrder.get(j+1));
                    salaryOrder.set(j+1, temp);
                }
            }
        }
        System.out.printf("%-20s%-20s%n", "Name", "Salary");
        for(Staff i: salaryOrder){
            System.out.printf("%-20s%-20s%n", i.name, i.salary);
        }
    }


    //Thêm thông tin khi là Employee
    public static void addEmployee(String id, String name, int age, int salary, int dayCome, String department, int dayOff){
        int overTime = inputInt("over time");
        Staff employee = new Employee(id, name, age, salary, dayCome, department, dayOff, overTime);
        listStaff.add(employee);
    }


    //Thêm thông tin khi là Manager
    public static void addManager(String id, String name, int age, int salary, int dayCome, String department, int dayOff){
        System.out.print("What is your details(1 = Business Leader, 2 = Project Leader, 3 = Technical Leader): ");
        int choice = scanner.nextInt();
        String titles = "";
        switch(choice){
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

        Staff manager = new Manager(id, name, age, salary, dayCome, department, dayOff, titles);
        listStaff.add(manager);
    }


    //
    public static int kindOfStaff(){
        while(true){
            System.out.print("What is your titles (0 = Employee, 1 = Manager): ");
            int result = scanner.nextInt();
            if(result == 0 || result == 1)
                return result;
        }
    }

    //Nhập thông tin là Integer
    public static int inputInt(String information){
        while(true){
            System.out.print("Enter " + information + ": ");
            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            scanner.nextLine();
            System.out.println("You need enter a number !!!");
        }
    }

    //Nhập thông tin là String
    public static String inputString(String information, int base){

        if(base == 1){
            System.out.print("Enter " + information + ": ");
            return scanner.nextLine() + scanner.nextLine();
        }else {
            System.out.print("Enter " + information + ": ");
            return scanner.nextLine();

        }
    }

    // Lọc ra danh sách tên Bộ phận
    public static List<String> listNameDepartment(){
        List<String> listNameDepartment = new ArrayList<>();

        for (Staff staff : listStaff) {
            if (!listNameDepartment.contains(staff.department)) {
                listNameDepartment.add(staff.department);
            }
        }
        return listNameDepartment;
    }

}


