import app.Company;
import app.Employee;
import org.junit.jupiter.api.BeforeEach;
import utils.CsvFileReader;
import utils.CsvFileWriter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CsvFileWriterTest {
    Employee test;
    Employee subEmployee;
    List<Employee> employees = new ArrayList<Employee>();
    Company testC = new Company();

    @BeforeEach
    public void initialize()
    {
        test = new Employee("CEO", "FirstNameTest", "SirNameTest","Marketing",10,0);
        subEmployee = new Employee("Head", "2FirstNameTest", "2SirNameTest","Sales",20,1);
        test.addSubordinate(subEmployee);

        employees.add(test);
        employees.add(subEmployee);
    }

    @org.junit.jupiter.api.Test
    void writeCsvFile(){
        CsvFileWriter test = new CsvFileWriter();
        CsvFileReader testR = new CsvFileReader();
        String fileName = "D:\\_Perso\\Owncloud\\Cours\\4A\\CompanyHierarchy\\resTest\\testFile.csv";
        test.writeCsvFile(fileName, employees);
        testC.loadCompanyFromFile(fileName);
        Employee boss = testR.readCsvFile(fileName);
        assertEquals(employees.size(), testC.getAllEmployee().size());
    }


}