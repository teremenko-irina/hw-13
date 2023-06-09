package activities;

import dataproviders.TestDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import models.Employees;
import utils.DBReaderAllQueries;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;
import static utils.DBReaderAllQueries.insert;


public class EmployeesTests {
    private static final Logger log = LogManager.getLogger(EmployeesTests.class);

    @Description("The test which checks executing actions with employees data")
    @Issue("ISSUE-1")
    @Story("Story-1")
    @Test(dataProvider = "employeesDB", dataProviderClass = TestDataProvider.class)

    public int testGetSalary(String name, String position, int salary) {

        log.info("The testGetSalary method was started");
        log.info("The test has parameters name = {}, position = {}, salary = {}", name, position, salary);

        return salary;
    }


    @Test
    public void testGetNameAfterInsert() {
        int id = 10;
        String name = "Anton";
        String position = "Manual tester";
        int salary = 1500;

        insert(id, name, position, salary);

        List<Employees> employees = DBReaderAllQueries.getEmployeesFromDB();
        Employees insertedEmployee = null;
        for (Employees employee : employees) {
            if (employee.getName().equals(name)) {
                insertedEmployee = employee;
                break;
            }
        }

        assert insertedEmployee != null;
        Assert.assertEquals(name, insertedEmployee.getName());
    }

    @Test
    public void testGetSalaryAfterUpdate() {
        int id = 3; // Assuming this is the ID of the employee to update
        int newSalary = 6000;

        DBReaderAllQueries.update(id, newSalary);

        List<Employees> employees = DBReaderAllQueries.getEmployeesFromDB();
        Employees updatedEmployee = null;
        for (Employees employee : employees) {
            if (employee.getPosition().equals("Lead developer")) {
                updatedEmployee = employee;
                break;
            }
        }

        assert updatedEmployee != null;
        Assert.assertEquals(newSalary, updatedEmployee.getSalary());
    }

    @Test
    public void testGetPositionAfterDelete() {
        int id = 5; // Assuming this is the ID of the employee to delete

        DBReaderAllQueries.delete(id);

        List<Employees> employees = DBReaderAllQueries.getEmployeesFromDB();
        Employees deletedEmployee = null;
        for (Employees employee : employees) {
            if (employee.getName().equals("Christina")) {
                deletedEmployee = employee;
                break;
            }
        }

        assertNull(deletedEmployee);
    }
}










