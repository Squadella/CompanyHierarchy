import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest
{
    Employee test;

    @BeforeEach
    void setUp()
    {
        test = new Employee("TEST", "TestFirstName", "TestSirName", "DPT", 0);
    }

    @org.junit.jupiter.api.Test
    void getSirName()
    {
        assertEquals("TestSirName", test.getSirName());
    }

}