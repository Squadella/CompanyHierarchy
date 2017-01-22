import app.Employee;
import org.junit.jupiter.api.BeforeEach;
import utils.CsvFileReader;

import java.io.File;
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


        CsvFileReader testRdr;
        testRdr = new CsvFileReader();
        String file = "resTest/testFile.csv";
        assertEquals(test.getId(), testRdr.readCsvFile().getId());

    }

}