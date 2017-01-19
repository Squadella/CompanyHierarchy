import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Sauce Ultra on 19/01/2017.
 */
class EmployeeTest {
    Employee test;
    Employee subEmployee;

    @BeforeEach
    public void initialize()
    {
        test = new Employee("CEO", "FirstNameTest", "SirNameTest","Marketing",10,null,0);
        subEmployee = new Employee("Head", "2FirstNameTest", "2SirNameTest","Sales",20,1);
        test.addSubordinate(subEmployee);
    }
    @Test
    void getSalary() {
        assertEquals(10, test.getSalary());
    }

    @Test
    void getDepartment() {
        assertEquals("Marketing", test.getDepartment());
    }

    @Test
    void getPosition() {
        assertEquals("CEO", test.getPosition());

    }

    @Test
    void getSubEmployee() {
        List<Employee> list=new ArrayList<>();
        list.add(subEmployee);
        assertEquals(list, test.getSubEmployee());
    }

    @Test
    void removeEmployee() {
        List<Employee> list=new ArrayList<>();
        test.getSubEmployee().get(0).removeEmployee();
        assertEquals(list, test.getSubEmployee());
    }

    @Test
    void getSirName()
    {
        assertEquals("SirNameTest", test.getSirName());
    }

    @Test
    void getFirstName() {
        assertEquals("FirstNameTest", test.getFirstName());
    }

    @Test
    void addSubordinate() {
        assertEquals(subEmployee, test.getSubEmployee().get(0));
    }
}