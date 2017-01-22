package res;

import app.Company;
import app.Employee;
import app.MainWindow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import utils.CsvFileWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FXController {

    private Company company;
    private Employee lastSelectedEmployee;

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
        dialog();
        refreshUI();
        loadListView(company.getAllEmployee());
    }

    public void dialog()
    {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Hire an employee");
        ButtonType buttonTypeHire = new ButtonType("Hire", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeHire, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField department = new TextField();
        TextField position = new TextField();
        TextField salary = new TextField();
        ListView<Employee> superior = new ListView<>();
        ListView<Employee> subordonates = new ListView<>();

        superior.setMaxHeight(200);
        subordonates.setMaxHeight(200);
        subordonates.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        grid.add(new Label("First name :"), 0, 0);
        grid.add(firstName, 1, 0);
        grid.add(new Label("Last name :"), 0, 1);
        grid.add(lastName, 1, 1);
        grid.add(new Label("Department :"), 0, 2);
        grid.add(department, 1, 2);
        grid.add(new Label("Position :"), 0, 3);
        grid.add(position, 1, 3);
        grid.add(new Label("Salary :"), 0, 4);
        grid.add(salary, 1, 4);

        if(company.getAllEmployee().size()>0)
        {
            grid.add(new Label("Superior :"), 0, 5);
            grid.add(superior, 1, 5);
            grid.add(new Label("Subordinates :"), 0, 6);
            grid.add(subordonates, 1, 6);
            superior.setItems(FXCollections.observableArrayList(company.getAllEmployee()));
            subordonates.setItems(FXCollections.observableArrayList(company.getAllEmployee()));
        }


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeHire) {
                dialog.close();
                if(company.getAllEmployee().size()>0)
                {
                    company.addEmployee(position.getText(), firstName.getText(), lastName.getText(), department.getText(), Float.parseFloat(salary.getText()), company.getEmployeeByID(company.getAllEmployee().get(superior.getSelectionModel().getSelectedIndex()).getId()), subordonates.getSelectionModel().getSelectedItems());
                }
                else {
                    company.addEmployee(position.getText(), firstName.getText(), lastName.getText(), department.getText(), Float.parseFloat(salary.getText()), null);
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    public void removeEmployee()
    {
        //Get employee
        if(lastSelectedEmployee==null)
        {
            return;
        }
        if(!Objects.equals(company.removeEmployee(lastSelectedEmployee), "OK"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setContentText("Can't destroy CEO if he has more than one subordinate");

            alert.showAndWait();
        }
        refreshEmployee(company.getCEO());
        refreshUI();
    }

    public void moveEmployee()
    {
        List<Employee> tmp =new ArrayList<>();
        tmp.add(company.getEmployeeByID(17));
        //company.moveEmployee(company.getCEO(), lastSelectedEmployee, tmp);
        refreshUI();
        loadListView(company.getAllEmployee());
    }

    public void loadUI()
    {
        //Charger les employees
        company = new Company();
        company.loadCompanyFromFile();
        company.searchLastID();
        refreshUI();
    }

    //Refresh everything in UI except app.Employee frame
    private void refreshUI()
    {
        company.generateStats();
        loadListView(company.getAllEmployee());
        //new CsvFileWriter().writeCsvFile(company.getAllEmployee());
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
        lastSelectedEmployee = employee;
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
