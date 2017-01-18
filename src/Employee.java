import java.util.List;

public class Employee
{
    private int id;
    private float salary;
    private String departement;
    private String position;
    private String firstName;
    private String sirName;
    private List<Employee> subordinates;

    public Employee(String position, String firstName, String sirName, String departement, float salary)
    {
        subordinates = null;
    }

    public float getSalary()
    {
        return salary;
    }

    public String getDepartement()
    {
        return departement;
    }

    public String getPosition()
    {
        return position;
    }

    public List<Employee> getSubEmployee()
    {
        return subordinates;
    }

    public void removeEmployee()
    {
        //TODO : implement remove action.
    }

    public String getSirName()
    {
        return sirName;
    }

    public String getFirstName()
    {
        return firstName;
    }
}
