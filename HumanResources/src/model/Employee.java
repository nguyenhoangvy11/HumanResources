package model;

public class Employee extends Staff implements ICalculator{
    public int overTime;

    public Employee(String id, String name, int age, int salary, int dayCome, String department, int dayOff, int overTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dayCome = dayCome;
        this.department = department;
        this.dayOff = dayOff;
        this.overTime = overTime;
        this.salary = calculateSalary();
    }

    @Override
    public String toString(){
        return String.format("%-10s%-20s%-20d%-20d%-20d%-20s%-20d", id, name, age, salary, dayCome, department, dayOff);
    }

    @Override
    public int calculateSalary(){
        return this.salary*3000000 + overTime*200000;
    }

}
