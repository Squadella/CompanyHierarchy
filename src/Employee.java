import java.util.ArrayList;
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

    public Employee(String position, String firstName, String sirName, String department, float salary, Employee supervisor, int id)
    {
        this.position=position;
        this.firstName=firstName;
        this.sirName=sirName;
        this.department=department;
        this.salary=salary;
        this.supervisor = supervisor;
        this.subordinates=null;
        this.id=id;
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
        //Moving subordinates to the top level
        this.supervisor.addSubordinate(this.subordinates);

        for(int i=0; i<this.subordinates.size(); ++i)
            this.subordinates.get(i).setNewSupervisor(this.supervisor);

        this.removeAllSubordinate();
        this.supervisor.removeSubordinate(this);
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

    public void addSubordinate(List<Employee> subordinates)
    {
        this.subordinates.addAll(subordinates);
    }

    public void removeAllSubordinate()
    {
        this.subordinates=new ArrayList<>();
    }

    public void removeSubordinate(Employee subordinate)
    {
        if(subordinates==null)
        {
            System.out.println("ERROR, the employee doesn't not have any subordinate.");
            return;
        }
        for(int i=0; i<subordinates.size(); ++i)
        {
            if(subordinate==subordinates.get(i))
            {
                subordinates.remove(i);
                return;
            }
        }
        System.out.println("ERROR, the employee searched doesn't exist.");
    }

    public void setNewSupervisor(Employee newSupervisor)
    {
        this.supervisor=newSupervisor;
    }

    public Employee getSupervisor()
    {
        return supervisor;
    }

    public Employee getEmployeeByID(int id, Employee current)
    {
        if(this.id == id)
            return this;

        if(current.getSubEmployee()==null)
            return null;

        for(int i=0; i<current.getSubEmployee().size(); ++i)
            getEmployeeByID(id, current.getSubEmployee().get(i));

        return null;
    }

    public int getId()
    {
        return id;
    }
}
