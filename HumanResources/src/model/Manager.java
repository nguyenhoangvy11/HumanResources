package model;

public class Manager extends Staff implements ICalculator{
    String titles;

    public Manager(String id, String name, int age, int salary, int dayCome, String department, int dayOff, String titles){
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dayCome = dayCome;
        this.department = department;
        this.dayOff = dayOff;
        this.titles = titles;
        this.salary = calculateSalary();
    }



    @Override
    public String toString(){
        return String.format("%-10s%-20s%-20d%-20d%-20d%-20s%-20s%-20s", id, name, age, salary, dayCome, department, dayOff, titles);
    }


    @Override
    public int calculateSalary(){
        int responsible = 0;
        if(this.titles.equalsIgnoreCase("Business Leader")){
            responsible = 8000000;
        } else if(this.titles.equalsIgnoreCase("Project Leader")){
            responsible = 5000000;
        } else if (this.titles.equalsIgnoreCase("Technical Leader")){
            responsible = 6000000;
        }
        return this.salary*5000000 + responsible;
    }
}
