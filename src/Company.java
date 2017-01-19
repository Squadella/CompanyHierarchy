import java.util.List;

public class Company
{
    private int lastID; //txt the lastID will be written at the end
    private Employee CEO;
    private float totalCost;
    private float averageDepartmentCost;
    private String mostExpensiveDepartment;
    private String lessExpensiveDepartment;
    private int numberOfEmployee;

    public Company()
    {
        //Default initialisations
        totalCost=0;
        averageDepartmentCost=0;
        numberOfEmployee=0;
        mostExpensiveDepartment=null;
        lessExpensiveDepartment=null;
        lastID=0;
    }

    public void moveEmployee(Employee supervisor, Employee employee, List<Employee> subordinates)
    {
        //Anti-loop system.
        for(int i=0; i<employee.getSubEmployee().size(); ++i)
        {
            if(employee.getSubEmployee().get(i).getDepth()<=employee.getDepth())
            {
                System.out.println("ERROR");
                System.exit(-1);
            }
        }
        for(int i=0; i<employee.getSubEmployee().size(); ++i)
        {
            //Setting supervisor
            employee.getSubEmployee().get(i).setNewSupervisor(employee.getSupervisor());
            //Setting subordinate
            employee.getSupervisor().addSubordinate(employee.getSubEmployee().get(i));
        }
        employee.removeAllSubordinate();
        employee.addSubordinate(subordinates);
        employee.setNewSupervisor(supervisor);
    }

    public void addEmployee(String position, String firstName, String sirName, String department, float salary, Employee supervisor)
    {
        //TODO: find a way to be place at the superior level
        if(supervisor==null)
        {
            //Top tree
            this.CEO = new Employee(position, firstName, sirName, department, salary, null, lastID);
        }
        else
        {
            ++lastID;
            supervisor.addSubordinate(new Employee(position, firstName, sirName, department, salary, supervisor, lastID));
        }
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
