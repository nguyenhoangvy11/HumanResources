package model;

public class Manager extends Staff implements ICalculator {
    String titles;

    public Manager(String id, String name, int age, int salary, int dayCome, int dayOff, String department,
            String titles) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dayCome = dayCome;
        this.dayOff = dayOff;
        this.department = department;
        this.titles = titles;
        this.salary = calculateSalary();
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-20d%-20d%-20d%-20d%-20s", id, name, age, salary, dayCome, dayOff, department);
    }

    @Override
    public int calculateSalary() {
        int responsible = 0;
        if (titles.equalsIgnoreCase("Business Leader")) {
            responsible = 8000000;
        } else if (titles.equalsIgnoreCase("Project Leader")) {
            responsible = 5000000;
        } else if (titles.equalsIgnoreCase("Technical Leader")) {
            responsible = 6000000;
        }
        return salary * 5000000 + responsible;
    }
}
