import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvFileReader
{
    private static final String DELIMITER = ";";
    private static final String DELIMITER2 = ",";

    private static final int ID = 0;
    private static final int SIR_NAME = 1;
    private static final int FIRST_NAME = 2;
    private static final int DEPARTMENT = 3;
    private static final int POSITION = 4;
    private static final int SALARY = 5;
    private static final int SUPERVISOR = 6;
    private static final int SUBORDINATES = 7;


    private List<Employee> getSubordinates(String subFile,List<Employee> employees)
    {
        String[] tokens = subFile.split(DELIMITER2);
        List<Employee> subordinates = new ArrayList<>();

        for(int i = 0; i < tokens.length; ++i) {
            subordinates.add(employees.get(0).getEmployeeByID(Integer.parseInt(tokens[i]), employees.get(0)));
        }
        return subordinates;
    }

    public List<Employee> readCsvFile(String fileName)
    {
        BufferedReader fileReader = null;
        List<Employee> employees = new ArrayList<>();
        try
        {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileName));

            while ((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.split(DELIMITER);
                if (tokens.length > 0)
                {
                    Employee employee = new Employee(tokens[POSITION], tokens[FIRST_NAME], tokens[SIR_NAME], tokens[DEPARTMENT], Float.parseFloat(tokens[SALARY]),Integer.parseInt(tokens[ID]));
                    employees.add(employee);
                }

            }
            fileReader = new BufferedReader(new FileReader(fileName));

            while ((line = fileReader.readLine()) != null)
            {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                List<Employee> subordinates = getSubordinates(tokens[SUBORDINATES],employees);
                if (tokens.length > 0)
                {
                    for(int i = 0; i > subordinates.size(); ++i)
                    {
                        employees.get(Integer.parseInt(tokens[ID])).addSubordinate(subordinates.get(i));
                    }
                    employees.get(Integer.parseInt(tokens[ID])).setNewSupervisor(employees.get(Integer.parseInt(tokens[ID])).getEmployeeByID(Integer.parseInt(tokens[SUPERVISOR]),employees.get(Integer.parseInt(tokens[ID]))));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileReader.close();
            }
            catch (IOException e)
            {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        return employees;
    }
}
