import java.util.List;

public class Employee
{
    private int id;
    private float salary;
    private String department;
    private String position;
    private String firstName;
    private String sirName;
    private List<Employee> subordinates;
    private Employee supervisor; // For the first element, supervisor = null

    public Employee(String position, String firstName, String sirName, String department, float salary)
    {
        this.position = position;
        this.firstName = firstName;
        this.sirName = sirName;
        this.department = department;
        this.salary = salary;
        this.supervisor = null;
        this.subordinates = null;
    }

    public Employee(String position, String firstName, String sirName, String department, float salary, Employee supervisor)
    {
        this.position = position;
        this.firstName = firstName;
        this.sirName = sirName;
        this.department = department;
        this.salary = salary;
        this.supervisor = supervisor;
        this.subordinates = null;
    }

    public float getSalary()
    {
        return salary;
    }

    public String getDepartment()
    {
        return department;
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
        for(int i = 0; i < this.subordinates.size(); ++i)
        {
            this.supervisor.addSubordinate(this.subordinates.get(i));
            this.subordinates.get(i).setNewSupervisor(this.supervisor);
        }
        //Destroy the element?
    }

    public String getSirName()
    {
        return sirName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void addSubordinate(Employee subordinate)
    {
        subordinates.add(subordinate);
    }

    public void removeSubordinate(Employee subordinate)
    {
        if(subordinates==null)
        {
            System.out.println("ERROR, the employee doesn't not have any subordinate.");
            return;
        }
        for(int i = 0; i < subordinates.size(); ++i)
        {
            if(subordinate == subordinates.get(i))
            {
                subordinates.remove(i);
                return;
            }
        }
        System.out.println("ERROR, the employee searched doesn't exist.");
    }

    public void setNewSupervisor(Employee newSupervisor)
    {
        this.supervisor = newSupervisor;
    }
}
