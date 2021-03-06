package app;

import utils.CsvFileReader;

import java.util.ArrayList;
import java.util.List;

public class Company
{
    private int lastID;
    private Employee CEO;
    private float totalCost;
    private float averageDepartmentCost;
    private String mostExpensiveDepartment;
    private String lessExpensiveDepartment;
    private int numberOfEmployee;

    public Company()
    {
        //Default initialisations
        totalCost = 0;
        averageDepartmentCost = 0;
        numberOfEmployee = 0;
        mostExpensiveDepartment = null;
        lessExpensiveDepartment = null;
        lastID = 0;
    }

    public void moveEmployee(Employee supervisor, Employee employee, List<Employee> subordinates, Employee newEmployee)
    {
        //Anti-troll system
        if(supervisor!=null && employee.getId() == supervisor.getId())
        {
            return;
        }
        for(Employee checkDat : newEmployee.getSubEmployee())
        {
            if(checkDat.getId() == employee.getId())
            {
                return;
            }
        }
        //Anti-loop system.
        for(Employee subTest : subordinates)
        {
            if(subTest.getDepth()<employee.getDepth())
            {
                System.out.println("ERROR");
                return;
            }
        }

        employee.resetInformation(newEmployee);

        if(supervisor != null)
        {
            employee.getSupervisor().removeSubordinate(employee);
            employee.setNewSupervisor(supervisor);

            //changing neighbors
            employee.getSupervisor().addSubordinate(employee);
        }
        for (Employee neighboor : employee.getSubEmployee())
        {
            neighboor.setNewSupervisor(employee);
        }

        //Managing subordinates
        if(subordinates.size()>0)
        {
            //Adding links in new position
            employee.removeAllSubordinate();
            employee.addSubordinate(subordinates);
            //Removing the node in previous poistion
            for (int i = 0; i < employee.getSubEmployee().size(); ++i) {
                //Setting supervisor
                employee.getSubEmployee().get(i).setNewSupervisor(employee);
                employee.getSupervisor().removeSubordinate(employee.getSubEmployee().get(i));
            }

        }

    }

    public void addEmployee(String position, String firstName, String sirName, String department, float salary, Employee supervisor)
    {
        if(supervisor==null)
        {
            //Top tree
            this.CEO = new Employee(position, firstName, sirName, department, salary, lastID);
        }
        else
        {
            lastID++;
            supervisor.addSubordinate(new Employee(position, firstName, sirName, department, salary, supervisor, lastID));
        }
    }

    public void addEmployee(String position, String firstName, String sirName, String department, float salary, Employee supervisor, List<Employee> subordinates)
    {
        lastID++;
        Employee tmp = new Employee(position, firstName, sirName, department, salary, supervisor, lastID);
        supervisor.addSubordinate(tmp);
        tmp.addSubordinate(subordinates);
        for(Employee subordinate: subordinates)
        {
            subordinate.setNewSupervisor(tmp);
            //search if superior has the same subordinate and remove it if needed.
            for(Employee superiorSubordinate: supervisor.getSubEmployee())
            {
                if(superiorSubordinate.getId() == subordinate.getId()) {
                    supervisor.removeSubordinate(subordinate);
                }
            }
        }
    }

    public float getDptExpenses(String dpt)
    {
        float totalSalary = 0;
        List<Employee> nextEmployee = new ArrayList<>();
        if(CEO==null)
        {
            return 0;
        }
        nextEmployee.add(CEO);
        while(nextEmployee.size()>0) {
            if (nextEmployee.get(0).getDepartment().equals(dpt)) {
                totalSalary+= nextEmployee.get(0).getSalary();
            }
            nextEmployee.addAll(nextEmployee.get(0).getSubEmployee());
            nextEmployee.remove(0);
        }
        return totalSalary;
    }

    public int getDptEmployee(String dpt)
    {
        int totalEmployees = 0;
        List<Employee> nextEmployee = new ArrayList<>();
        if(CEO==null)
        {
            return 0;
        }
        nextEmployee.add(CEO);
        while(nextEmployee.size()>0) {
            if (nextEmployee.get(0).getDepartment().equals(dpt)) {
                totalEmployees++;
            }
            nextEmployee.addAll(nextEmployee.get(0).getSubEmployee());
            nextEmployee.remove(0);
        }
        return totalEmployees;
    }

    public List<Employee> getAllEmployee()
    {
        if(CEO==null)
            return new ArrayList<>();
        List<Employee> allEmployees = new ArrayList<>();
        List<Employee> nextEmployee = new ArrayList<>();
        nextEmployee.add(CEO);
        while(nextEmployee.size()>0)
        {
            allEmployees.add(nextEmployee.get(0));
            nextEmployee.addAll(nextEmployee.get(0).getSubEmployee());
            nextEmployee.remove(0);
        }
        return allEmployees;
    }

    public Employee getEmployeeByID(int id)
    {
        return CEO.getEmployeeByID(id, CEO);
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

    public Employee getCEO()
    {
        return CEO;
    }

    public void loadCompanyFromFile()
    {
        this.CEO = new CsvFileReader().readCsvFile();
    }

    public void generateStats()
    {
        List<Employee> allEmployee = getAllEmployee();

        //Setting the number of employee.
        numberOfEmployee=allEmployee.size();

        //Calculating department cost.
        float[] departmentCost = new float[4]; //same order as the department name.
        String[] departmentName = {"Accounting", "Sales", "Marketing", "Manufacturing"};
        for(Employee employee: allEmployee)
        {
            for (int i=0; i<4; ++i)
            {
                if(employee.getDepartment().equals(departmentName[i]))
                {
                    departmentCost[i] += employee.getSalary();
                    break;
                }
            }
        }

        //Getting the total cost of the company, the most expensive department and the less expensive one.
        int indexMax=0, indexMin=0;
        float max=0, min=departmentCost[0];
        for(int i=0; i<4; ++i)
        {
            if(max<departmentCost[i])
            {
                indexMax=i;
                max=departmentCost[i];
            }
            if(min>departmentCost[i])
            {
                indexMin=i;
                min=departmentCost[i];
            }
            this.totalCost+=departmentCost[i];
        }
        mostExpensiveDepartment=departmentName[indexMax];
        lessExpensiveDepartment=departmentName[indexMin];
        averageDepartmentCost=totalCost/4;
    }

    public String removeEmployee(Employee employee)
    {
        if(employee.getPosition().equals("CEO"))
        {
            if( CEO.getSubEmployee().size()==1)
            {
                Employee tmp = CEO.getSubEmployee().get(0);
                tmp.setNoSupervisor();
                CEO.removeAllSubordinate();
                CEO = tmp;
                return "OK";
            }
            else
            {
                return "Can't destroy CEO if he has more than one subordinate";
            }
        }
        else
        {
            employee.removeEmployee();
            return "OK";
        }
    }

    public void searchLastID()
    {
        List<Employee> employees = getAllEmployee();
        for(Employee employee:employees)
        {
            if(employee.getId()>lastID)
            {
                lastID=employee.getId();
            }
        }
    }

    public int getLastID()
    {
        lastID++;
        return lastID;
    }
}
