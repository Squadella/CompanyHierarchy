import java.util.ArrayList;
import java.util.List;

public class Company
{
    private Employee CEO;
    private float totalCost;
    private float averageDepartmentCost;
    private int numberOfEmployee;
    private String mostExpensiveDepartment;
    private String lessExpensiveDepartment;

    public Company()
    {
        //Default initialisations
        totalCost=0;
        averageDepartmentCost=0;
        numberOfEmployee=0;
        mostExpensiveDepartment=null;
        lessExpensiveDepartment=null;
    }

    public void moveEmployee()
    {

    }

    public void addEmployee(Employee supervisor)
    {
        //TODO: find a way to be place at the superior level

    }

    public void getAllEmployee(Employee currentEmployee, List<Employee> allEmployees)
    {
        if(currentEmployee.getSubEmployee()!=null)
        {
            for (int i = 0; i < currentEmployee.getSubEmployee().size(); ++i)
            {
                allEmployees.add(currentEmployee.getSubEmployee().get(i));
                getAllEmployee(currentEmployee.getSubEmployee().get(i), allEmployees);
            }
        }
    }

    public Employee getEmployeeByID(int id)
    {
        return null;
    }

    public float getTotalCost()
    {
        return totalCost;
    }

    public int getEmployeeNumber()
    {
        return numberOfEmployee;
    }

    public String getMostExpensiveDepartment()
    {
        return mostExpensiveDepartment;
    }

    public String getLessExpensiveDepartment()
    {
        return lessExpensiveDepartment;
    }

    public float getAverageDepartmentCost()
    {
        return averageDepartmentCost;
    }
}
