import app.Employee;
import org.junit.jupiter.api.BeforeEach;
import utils.CsvFileReader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CsvFileReaderTest {
    Employee test;
    Employee subEmployee;

    @BeforeEach
    public void initialize()
    {
        test = new Employee("CEO", "FirstNameTest", "SirNameTest","Marketing",10,0);
        subEmployee = new Employee("Head", "2FirstNameTest", "2SirNameTest","Sales",20,1);
        test.addSubordinate(subEmployee);
    }

    @org.junit.jupiter.api.Test
    void readCsvFile()
    {

        List<Employee> list=new ArrayList<>();
        list.add(test);
        CsvFileReader testRdr;
        testRdr = new CsvFileReader();
        String fileNameTest = "/home/zk/Cours/CompanyHierarchy/resTest/testFile.csv";
        List<Employee> testFile = testRdr.readCsvFile(fileNameTest);
        assertEquals(list.get(0).getId(), testFile.get(0).getId());

    }

}