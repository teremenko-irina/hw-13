package utils;

import models.Employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dataproviders.TestDataProvider.employees;


public class DBReaderAllQueries {

    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";

    private final static String USER_NAME = "postgres";

    private final static String USER_PASSWORD = "postgres";

    private final static String QUERY_SELECT_ALL = "select * from employees";
    private final static String QUERY_INSERT = "insert into employees values(?, ?, ?, ?)";
    private final static String QUERY_UPDATE = "update employees set salary=? where id=?";
    private final static String QUERY_DELETE = "delete from employees where id=?";


    @org.jetbrains.annotations.NotNull
    public static List<Employees> getEmployeesFromDB() {
        List<Employees> employees = new ArrayList<>();


        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            Statement sqlStatement = connection.createStatement();
            try (ResultSet resultSet = sqlStatement.executeQuery(QUERY_SELECT_ALL)) {


                while (resultSet.next()) {
                    Employees employee = new Employees(resultSet.getString("name"),
                            resultSet.getString("position"),
                            resultSet.getInt("salary"));

                    employees.add(employee);

                }
            }


        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string. URL [%s], name [%s], pass [%s]",
                    URL, USER_NAME, USER_PASSWORD));
        }
        return employees;
    }

    public static void insert(int id, String name, String position, int salary) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, position);
            preparedStatement.setInt(4, salary);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string. URL [%s], name [%s], pass [%s]",
                    URL, USER_NAME, USER_PASSWORD));
        }
    }


    public static Employees update(int id, int salary) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, salary);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string. URL [%s], name [%s], pass [%s]",
                    URL, USER_NAME, USER_PASSWORD));
        }
        return employees;
    }

    public static Employees delete(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string. URL [%s], name [%s], pass [%s]",
                    URL, USER_NAME, USER_PASSWORD));
        }
        return employees;
    }

    public static void main(String[] args) {
        getEmployeesFromDB();


    }

}


