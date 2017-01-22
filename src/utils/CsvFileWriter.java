package utils;

import app.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CsvFileWriter
{
    private static final String DELIMITER = ";";
    private static final String DELIMITER2 = ",";
    private static final String SEPARATOR = "\n";

    public void writeCsvFile(List<Employee> employees)
    {
        //Getting the path of the jar file.
        URL csv = getClass().getResource("/db.csv");
        File csvFile = new File(csv.getPath());
        String fileName = csvFile.getAbsolutePath();
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName);
            for(int i = 0; i < employees.size(); ++i)
            {
                fileWriter.append(String.valueOf(employees.get(i).getId()));
                fileWriter.append(DELIMITER);
                fileWriter.append(employees.get(i).getSirName());
                fileWriter.append(DELIMITER);
                fileWriter.append(employees.get(i).getFirstName());
                fileWriter.append(DELIMITER);
                fileWriter.append(employees.get(i).getDepartment());
                fileWriter.append(DELIMITER);
                fileWriter.append(employees.get(i).getPosition());
                fileWriter.append(DELIMITER);
                fileWriter.append(String.valueOf(employees.get(i).getSalary()));
                fileWriter.append(DELIMITER);
                if(employees.get(i).getSupervisor() != null)
                {
                    fileWriter.append(String.valueOf(employees.get(i).getSupervisor().getId()));
                }
                else
                    fileWriter.append("null");

                fileWriter.append(DELIMITER);
                if(employees.get(i).getSubEmployee().size() != 0)
                {
                    for (int j = 0; j < employees.get(i).getSubEmployee().size(); ++j)
                    {
                        fileWriter.append(String.valueOf(employees.get(i).getSubEmployee().get(j).getId()));
                        fileWriter.append(DELIMITER2);
                    }
                }
                else
                    fileWriter.append("null");

                fileWriter.append(SEPARATOR);
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in utils.CsvFileWriter !!!");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e)
            {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

    }

}
