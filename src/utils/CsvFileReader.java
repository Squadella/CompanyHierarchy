package utils;

import app.Employee;

import java.io.*;
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


    private void setSubordinates(String subFile, List<Employee> employees, Employee currentEmployee)
    {
        if(subFile.equals("null"))
            return ;

        String[] tokens = subFile.split(DELIMITER2);
        for (String token: tokens)
        {
            for (Employee employee : employees)
            {
                if (Integer.parseInt(token) == employee.getId())
                {
                    currentEmployee.addSubordinate(employee);
                }
            }
        }
    }

    private void setSupervisor(String id, List<Employee> employees, Employee currentEmployee)
    {
        for(Employee employee: employees)
        {
            if(employee.getId()==Integer.parseInt(id))
            {
                currentEmployee.setNewSupervisor(employee);
            }
        }
    }

    public Employee readCsvFile(String file)
    {
        File csv = new File(file);
        String fileName = csv.getAbsolutePath();

        BufferedReader fileReader = null;
        List<Employee> employees = new ArrayList<>();
        try
        {
            String line = "";
            //fileReader = new BufferedReader(new FileReader(fileName));
            InputStreamReader fileReader2 = new InputStreamReader(getClass().getResourceAsStream("db.csv"));
            fileReader = new BufferedReader(fileReader2);
            //First pass in the file, loading all the employee into memory.
            while ((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.split(DELIMITER);
                if (tokens.length > 0)
                {
                    Employee employee = new Employee(tokens[POSITION], tokens[FIRST_NAME], tokens[SIR_NAME], tokens[DEPARTMENT], Float.parseFloat(tokens[SALARY]),Integer.parseInt(tokens[ID]));
                    employees.add(employee);
                }
            }

            //Second pass, make the hierarchical links.
            fileReader2 = new InputStreamReader(getClass().getResourceAsStream("db.csv"));
            fileReader = new BufferedReader(fileReader2);
            int count = 0;
            while ((line = fileReader.readLine()) != null)
            {
                String[] tokens = line.split(DELIMITER);
                if (tokens.length > 0)
                {
                    if(!tokens[SUBORDINATES].equals("null"))
                        setSubordinates(tokens[SUBORDINATES], employees, employees.get(count));
                    if(!tokens[SUPERVISOR].equals("null"))
                        setSupervisor(tokens[SUPERVISOR], employees, employees.get(count));
                }
                count++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in utils.CsvFileReader !!!");
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
        return employees.get(0);
    }
}
