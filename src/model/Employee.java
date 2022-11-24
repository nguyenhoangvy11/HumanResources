package model;

public class Employee extends Staff implements ICalculator {
    int overTime;

    public Employee(String id, String name, int age, int salary, int dayCome, int dayOff, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dayCome = dayCome;
        this.dayOff = dayOff;
        this.department = department;
        this.salary = calculateSalary();
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-20d%-20d%-20d%-20d%-20s", id, name, age, salary, dayCome, dayOff, department);
    }

    @Override
    public int calculateSalary() {
        return salary * 3000000 + overTime * 2000000;
    }

    
}
