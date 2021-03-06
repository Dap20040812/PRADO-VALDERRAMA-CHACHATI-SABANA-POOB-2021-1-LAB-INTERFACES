package edu.sabana.poob.sabanapayroll;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class ColsubsidioFundTest {

    private static Faker faker;

    private static List<Employee> employees;
    private static Department department;

    private static EmployeeBySalary employeeBySalary;
    private static EmployeeBySalary employeeBySalary1;

    private static EmployeeByHours employeeByHours;
    private static EmployeeByCommission employeeByCommission;

    private static IFamilyCompensationFund colsubsidioFund;

    @BeforeAll
    public static void setUp() {
        faker = new Faker(new Locale("en-US"));

        department = new Department("Engineering");

        employeeBySalary = new EmployeeBySalary(faker.name().firstName(), faker.name().lastName(), department, 1000000,"Saving");
        employeeBySalary1 = new EmployeeBySalary(faker.name().firstName(), faker.name().lastName(), department, 1000000,"Saving");

        employeeByHours = new EmployeeByHours(faker.name().firstName(), faker.name().lastName(), department, 40,"Checking");
        employeeByCommission = new EmployeeByCommission(faker.name().firstName(), faker.name().lastName(), department, 100,"checking");

        employees = new ArrayList<>();
        employees.add(employeeBySalary);
        employees.add(employeeBySalary1);
        employees.add(employeeByHours);
        employees.add(employeeByCommission);

        colsubsidioFund = new ColsubsidioFund();

    }

    @Test
    @DisplayName("GIVEN a employee by salary WHEN try to register THEN success")
    public void shouldRegisterEmployee() {

        assertTrue(colsubsidioFund.registerEmployee(employeeBySalary1));
    }

    @Test
    @DisplayName("GIVEN a employee by commission WHEN try to register THEN fails")
    public void shouldNotRegisterEmployeeWhenByCommission() {

        assertFalse(colsubsidioFund.registerEmployee(employeeByCommission));
    }

    @Test
    @DisplayName("GIVEN a employee by salary registered WHEN try to register again THEN fails")
    public void shouldNotRegisterEmployeeWhenDuplicated() {

        assertTrue(colsubsidioFund.registerEmployee(employeeByHours));
        assertFalse(colsubsidioFund.registerEmployee(employeeByHours));
    }

    @Test
    @DisplayName("GIVEN a employee by salary registered WHEN try to delete THEN success")
    public void shouldDeleteEmployee() {

        assertTrue(colsubsidioFund.registerEmployee(employeeBySalary));
        assertTrue(colsubsidioFund.deleteEmployee(employeeBySalary.getId()));
    }

    @Test
    @DisplayName("GIVEN a employee by salary not registered WHEN try to delete THEN fails")
    public void shouldNotDeleteEmployee() {

        assertFalse(colsubsidioFund.deleteEmployee(employeeBySalary.getId()));
    }

    @Test
    @DisplayName("GIVEN a employee by salary registered WHEN try to validate is registered THEN success")
    public void shouldValidateEmployeeIsRegistered() {

        assertTrue(colsubsidioFund.registerEmployee(employeeBySalary));
        assertTrue(colsubsidioFund.isEmployeeRegistered(employeeBySalary.getId()));
    }

    @Test
    @DisplayName("GIVEN a employee by salary not registered WHEN try to validate is registered THEN fails")
    public void shouldNotValidateEmployeeIsRegistered() {

        assertFalse(colsubsidioFund.isEmployeeRegistered(employeeBySalary.getId()));
    }

    @Test
    public void shouldPrintBenefits() {

        String benefits = colsubsidioFund.printBenefits();
        assertNotNull(benefits);
    }

}
