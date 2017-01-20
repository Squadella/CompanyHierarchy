package utils;

import app.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFileWriter
{
    private static final String DELIMITER = ";";
    private static final String DELIMITER2 = ",";
    private static final String SEPARATOR = "\n";

    public static void writeCsvFile(String fileName, List<Employee> Employees)
    {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName);
            for(int i = 0; i < Employees.size(); ++i) {



                fileWriter.append(String.valueOf(Employees.get(i).getId()));
                fileWriter.append(DELIMITER);
                fileWriter.append(Employees.get(i).getSirName());
                fileWriter.append(DELIMITER);
                fileWriter.append(Employees.get(i).getFirstName());
                fileWriter.append(DELIMITER);
                fileWriter.append(Employees.get(i).getDepartment());
                fileWriter.append(DELIMITER);
                fileWriter.append(Employees.get(i).getPosition());
                fileWriter.append(DELIMITER);
                fileWriter.append(String.valueOf(Employees.get(i).getSalary()));
                fileWriter.append(DELIMITER);
                if(Employees.get(i).getSupervisor() != null) {
                    fileWriter.append(String.valueOf(Employees.get(i).getSupervisor().getId()));
                }
                else
                    fileWriter.append("null");
                fileWriter.append(DELIMITER);
                if(Employees.get(i).getSubEmployee().size() != 0) {
                    for (int j = 0; j < Employees.get(i).getSubEmployee().size(); ++j) {
                        fileWriter.append(String.valueOf(Employees.get(i).getSubEmployee().get(i).getId()));
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
