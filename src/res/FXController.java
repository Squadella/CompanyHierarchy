package res;

import app.Company;
import app.Employee;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class FXController {

    private Company company;

    @FXML private ListView<Employee> listViewEmployee;

    @FXML private Text textTotalHRExpenses;
    @FXML private Text textTotalEmployee;
    @FXML private Text textMostExpDpt;
    @FXML private Text textLessExpDpt;
    @FXML private Text textAverageDptExp;

    @FXML private Text textAccExpenses;
    @FXML private Text textAccEmployee;

    @FXML private Text textSalesExpenses;
    @FXML private Text textSalesEmployee;

    @FXML private Text textMarkExpenses;
    @FXML private Text textMarkEmployee;

    @FXML private Text textManuExpenses;
    @FXML private Text textManuEmployee;

    @FXML private Text textEmployeeFirstName;
    @FXML private Text textEmployeeLastName;
    @FXML private Text textEmployeeDpt;
    @FXML private Text textEmployeePosition;
    @FXML private Text textEmployeeSalary;
    @FXML private Text textEmployeeSuperior;
    @FXML private Text textEmployeeSubordonate;

    @FXML private Menu menuQuit;

    public void fillListView(List<Employee> employeeList)
    {
        listViewEmployee.setItems(FXCollections.observableArrayList(employeeList));
    }

    public void setTextTotalHRExpenses(String text) { textTotalHRExpenses.setText(text); }
    public void setTextTotalEmployee(String text) { textTotalEmployee.setText(text); }
    public void setTextMostExpDpt(String text) { textMostExpDpt.setText(text); }
    public void setTextLessExpDpt(String text) { textLessExpDpt.setText(text); }
    public void setTextAverageDptExp(String text) { textAverageDptExp.setText(text); }

    public void setTextAccExpenses(String text) { textAccExpenses.setText(text); }
    public void setTextAccEmployee(String text) { textAccEmployee.setText(text); }

    public void setTextSalesExpenses(String text) { textSalesExpenses.setText(text); }
    public void setTextSalesEmployee(String text) { textSalesEmployee.setText(text); }

    public void setTextMarkExpenses(String text) { textMarkExpenses.setText(text); }
    public void setTextMarkEmployee(String text) { textMarkEmployee.setText(text); }

    public void setTextManuExpenses(String text) { textManuExpenses.setText(text); }
    public void setTextManuEmployee(String text) { textManuEmployee.setText(text); }

    public void setTextEmployeeFirstName(String text) { textEmployeeFirstName.setText(text); }
    public void setTextEmployeeLastName(String text) { textEmployeeLastName.setText(text); }
    public void setTextEmployeeDpt(String text) { textEmployeeDpt.setText(text); }
    public void setTextEmployeePosition(String text) { textEmployeePosition.setText(text); }
    public void setTextEmployeeSalary(String text) { textEmployeeSalary.setText(text); }
    public void setTextEmployeeSuperior(String text) { textEmployeeSuperior.setText(text); }
    public void setTextEmployeeSubordonate(String text) { textEmployeeSubordonate.setText(text); }

    public void stop(ActionEvent actionEvent)
    {
        Platform.exit();
    }

    public ListView<Employee> getListViewEmployee()
    {
        return listViewEmployee;
    }

    public void addEmployee()
    {
        System.out.print("Add");
    }

    public void removeEmployee()
    {
        System.out.print("Remove");
    }

    public void moveEmployee()
    {
        System.out.print("Move");
    }

    public void loadUI()
    {
        //Charger les employees
        company = new Company();
        String fileName="D:\\Documents\\GitHub\\CompanyHierarchy\\src\\res\\db.csv";
        //String fileName="/home/messmaker/Documents/Programming/Java/CompanyHierarchy/src/res/db.csv";
        company.loadCompanyFromFile(fileName);
        company.generateStats();
        loadListView(company.getAllEmployee());
        refreshUI();
    }

    //Refresh everything in UI except app.Employee frame
    private void refreshUI()
    {
        setTextTotalHRExpenses("Total HR expenses : " + company.getTotalCost() + " $");
        setTextTotalEmployee("Number of employees : " + company.getEmployeeNumber());
        setTextMostExpDpt("Most expensive department : " + company.getMostExpensiveDepartment());
        setTextLessExpDpt("Less expensive department : " + company.getLessExpensiveDepartment());
        setTextAverageDptExp("Average department expenses : " + company.getAverageDepartmentCost() + " $");

        setTextAccExpenses("Total expenses : " + Float.toString(company.getDptExpenses("Accounting")) + " $");
        setTextAccEmployee("Number of employees : " + Float.toString(company.getDptEmployee("Accounting")));

        setTextSalesExpenses("Total expenses : " + Float.toString(company.getDptExpenses("Sales")) + " $");
        setTextSalesEmployee("Number of employees : " + Float.toString(company.getDptEmployee("Sales")));

        setTextMarkExpenses("Total expenses : " + Float.toString(company.getDptExpenses("Marketing")) + " $");
        setTextMarkEmployee("Number of employees : " + Float.toString(company.getDptEmployee("Marketing")));

        setTextManuExpenses("Total expenses : " + Float.toString(company.getDptExpenses("Manufacturing")) + " $");
        setTextManuEmployee("Number of employees : " + Float.toString(company.getDptEmployee("Manufacturing")));

        //Actualiser l'affichage pour employee
    }

    public void refreshEmployee(Employee employee)
    {
        setTextEmployeeFirstName(employee.getFirstName());
        setTextEmployeeLastName(employee.getSirName());
        setTextEmployeeDpt(employee.getDepartment());
        setTextEmployeePosition(employee.getPosition());
        setTextEmployeeSalary(Float.toString(employee.getSalary()));

        if(employee.getSupervisor() != null)
            setTextEmployeeSuperior(employee.getSupervisor().toString());
        else
            setTextEmployeeSuperior("No superior");

        if(employee.getSubEmployee().size() != 0)
        {
            String tmp = employee.getSubEmployee().get(0).toString();

            for(int i = 1; i < employee.getSubEmployee().size() ; ++i)
                tmp += ", " + employee.getSubEmployee().get(i);

            setTextEmployeeSubordonate(tmp);
        }
        else
            setTextEmployeeSubordonate("No subordinates");
    }

    //Load `listViewEmployee` w/ magic
    private void loadListView(List<Employee> allEmployees)
    {
        if(allEmployees.size()<=0)
        {
            List<Employee> tmp = new ArrayList<>();
            tmp.add(new Employee("position", "no Employee", "", "", 0, -1));
            fillListView(tmp);
        }
        fillListView(allEmployees);
    }
}
