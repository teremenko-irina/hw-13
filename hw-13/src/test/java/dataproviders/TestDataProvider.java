package dataproviders;

import models.Employees;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.DataProvider;


import static utils.CsvReader.getListObjectsFromCsv;
import static utils.DBReaderAllQueries.getEmployeesFromDB;


public class TestDataProvider {

    private static final String PATH = "src\\test\\resources\\data.csv";
    public static Employees employees;


    @DataProvider(name = "employeesDB")
    public static Object[] @NotNull [] EmployeesFromDB() {



        return getListObjectsFromCsv(PATH, Employees.class).stream().map(employees -> new Object[]{employees.getName(),
                employees.getPosition(), employees.getSalary()}).toArray(Object[][]::new);

    }

    public static void main(String[] args) {
        getEmployeesFromDB();
    }
}







