import app.Employee;

import java.io.FileWriter;
import java.io.IOException;

public class CsvFileWriter
{
    private static final String DELIMITER = ";";
    private static final String DELIMITER2 = ",";
    private static final String SEPARATOR = "\n";

    public static void writeCsvFile(String fileName, Employee current)
    {
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(fileName,true);

            fileWriter.append(String.valueOf(current.getId()));
            fileWriter.append(DELIMITER);
            fileWriter.append(current.getSirName());
            fileWriter.append(DELIMITER);
            fileWriter.append(current.getFirstName());
            fileWriter.append(DELIMITER);
            fileWriter.append(current.getDepartment());
            fileWriter.append(DELIMITER);
            fileWriter.append(current.getPosition());
            fileWriter.append(DELIMITER);
            fileWriter.append(String.valueOf(current.getSalary()));
            fileWriter.append(DELIMITER);
            fileWriter.append(String.valueOf(current.getSupervisor().getId()));
            for(int i = 0; i < current.getSubEmployee().size(); ++i)
            {
                fileWriter.append(String.valueOf(current.getSubEmployee().get(i).getId()));
                fileWriter.append(DELIMITER2);
            }
            fileWriter.append(SEPARATOR);
        }
        catch (Exception e)
        {
            System.out.println("Error in CsvFileWriter !!!");
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
