package models;

import com.opencsv.bean.CsvBindByName;

import static utils.DBReaderAllQueries.getEmployeesFromDB;



public class Employees {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "position")
    private String position;
    @CsvBindByName(column = "salary")
    private int salary;

    public Employees(String name, String position, int salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public static void main(String[] args) {
        getEmployeesFromDB();
    }


    }


