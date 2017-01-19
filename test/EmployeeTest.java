import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Sauce Ultra on 19/01/2017.
 */
class EmployeeTest {
    Employee test;
    @BeforeEach
    public void initialize()
    {
        this.test = new Employee("CEO", "FirstNameTest", "SirNameTest","Marketing",0);
    }
    @Test
    void getSalary() {
        assertEquals(0, test.getSalary());
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
        
    }

    @Test
    void removeEmployee() {

    }

    @Test
    void getSirName() {
        assertEquals("SirNameTest", test.getSirName());

    }

    @Test
    void getFirstName() {
        assertEquals("FirstNameTest", test.getFirstName());
    }

    @Test
    void addSubordinate() {

    }

    @Test
    void removeSubordinate() {

    }

    @Test
    void setNewSupervisor() {

    }

}