package model;

public class Department {
    public String idDepartment;
    public String nameDepartment;
    public int amountEmployee;

    public Department(String idDepartment, String nameDepartment, int amountEmployee){
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.amountEmployee = amountEmployee;
    }

    @Override
    public String toString(){
        return String.format("%-10s%-20s%-20d", idDepartment, nameDepartment, amountEmployee);
    }

}
