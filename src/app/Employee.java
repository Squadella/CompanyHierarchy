package app;

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
    private Employee supervisor; //For the first element, supervisor = null
    private int depth;

    public Employee(String position, String firstName, String sirName, String department, float salary, int id)
    {
        this.id = id;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.firstName = firstName;
        this.sirName = sirName;
        this.subordinates = new ArrayList<>();
        this.supervisor = null;
        this.depth = 0;
    }

    public Employee(String position, String firstName, String sirName, String department, float salary, Employee supervisor, int id)
    {
        this.position = position;
        this.firstName = firstName;
        this.sirName = sirName;
        this.department = department;
        this.salary = salary;
        this.supervisor = supervisor;
        this.subordinates = new ArrayList<>();
        this.id = id;
        this.depth = supervisor.getDepth() + 1;
    }

    @Override
    public String toString()
    {
        return firstName + " " + sirName + " - " + position;
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

        for(int i = 0; i < this.subordinates.size(); ++i)
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
        this.subordinates.add(subordinate);
        subordinate.supervisor = this;
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
        this.depth = this.supervisor.getDepth()+1;
    }

    public Employee getSupervisor()
    {
        return supervisor;
    }

    public Employee getEmployeeByID(int id, Employee startEmployee)
    {
        List<Employee> nextEmployees = new ArrayList<>();
        nextEmployees.addAll(startEmployee.getSubEmployee());
        //Browse all employee from the startEmployee.
        while(nextEmployees.size()<0)
        {
            if(nextEmployees.get(0).getId()==id)
                return nextEmployees.get(0);
            nextEmployees.addAll(nextEmployees.get(0).getSubEmployee());
            nextEmployees.remove(0);
        }
        return null;
    }

    public int getId()
    {
        return id;
    }

    public int getDepth()
    {
        return depth;
    }
}
