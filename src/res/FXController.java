package res;

import app.Company;
import app.Employee;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;
import utils.CsvFileWriter;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

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

    private void fillListView(List<Employee> employeeList)
    {
        listViewEmployee.setItems(FXCollections.observableArrayList(employeeList));
    }

    private void setTextTotalHRExpenses(String text) { textTotalHRExpenses.setText(text); }
    private void setTextTotalEmployee(String text) { textTotalEmployee.setText(text); }
    private void setTextMostExpDpt(String text) { textMostExpDpt.setText(text); }
    private void setTextLessExpDpt(String text) { textLessExpDpt.setText(text); }
    private void setTextAverageDptExp(String text) { textAverageDptExp.setText(text); }

    private void setTextAccExpenses(String text) { textAccExpenses.setText(text); }
    private void setTextAccEmployee(String text) { textAccEmployee.setText(text); }

    private void setTextSalesExpenses(String text) { textSalesExpenses.setText(text); }
    private void setTextSalesEmployee(String text) { textSalesEmployee.setText(text); }

    private void setTextMarkExpenses(String text) { textMarkExpenses.setText(text); }
    private void setTextMarkEmployee(String text) { textMarkEmployee.setText(text); }

    private void setTextManuExpenses(String text) { textManuExpenses.setText(text); }
    private void setTextManuEmployee(String text) { textManuEmployee.setText(text); }

    private void setTextEmployeeFirstName(String text) { textEmployeeFirstName.setText(text); }
    private void setTextEmployeeLastName(String text) { textEmployeeLastName.setText(text); }
    private void setTextEmployeeDpt(String text) { textEmployeeDpt.setText(text); }
    private void setTextEmployeePosition(String text) { textEmployeePosition.setText(text); }
    private void setTextEmployeeSalary(String text) { textEmployeeSalary.setText(text); }
    private void setTextEmployeeSuperior(String text) { textEmployeeSuperior.setText(text); }
    private void setTextEmployeeSubordonate(String text) { textEmployeeSubordonate.setText(text); }

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

    private void dialog()
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
                if(company.getAllEmployee().size()>0)
                    company.addEmployee(position.getText(), firstName.getText(), lastName.getText(), department.getText(), Float.parseFloat(salary.getText()), company.getEmployeeByID(company.getAllEmployee().get(superior.getSelectionModel().getSelectedIndex()).getId()), subordonates.getSelectionModel().getSelectedItems());
                else 
                    company.addEmployee(position.getText(), firstName.getText(), lastName.getText(), department.getText(), Float.parseFloat(salary.getText()), null);
                dialog.close();
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void editDialog()
    {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Edit an employee");
        ButtonType buttonTypeHire = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
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
        grid.add(new Label("Change superior :"), 0, 5);
        grid.add(superior, 1, 5);
        grid.add(new Label("Change subordinates :"), 0, 6);
        grid.add(subordonates, 1, 6);
        superior.setItems(FXCollections.observableArrayList(company.getAllEmployee()));
        subordonates.setItems(FXCollections.observableArrayList(company.getAllEmployee()));

        firstName.setText(lastSelectedEmployee.getFirstName());
        lastName.setText(lastSelectedEmployee.getSirName());
        department.setText(lastSelectedEmployee.getDepartment());
        position.setText(lastSelectedEmployee.getPosition());
        salary.setText(Float.toString(lastSelectedEmployee.getSalary()));

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeHire)
            {
                //System.out.println(superior.getSelectionModel().getSelectedIndex());
                if(superior.getSelectionModel().getSelectedIndex()!=-1)
                    company.moveEmployee(company.getEmployeeByID(company.getAllEmployee().get(superior.getSelectionModel().getSelectedIndex()).getId()), lastSelectedEmployee, subordonates.getSelectionModel().getSelectedItems(), new Employee(position.getText(), firstName.getText(), lastName.getText(), department.getText(), Float.parseFloat(salary.getText()), company.getLastID()));
                else
                    company.moveEmployee(null, lastSelectedEmployee, subordonates.getSelectionModel().getSelectedItems(), new Employee(position.getText(), firstName.getText(), lastName.getText(), department.getText(), Float.parseFloat(salary.getText()), company.getLastID()));
                dialog.close();
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
        editDialog();
        refreshUI();
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
    public void refreshUI()
    {
        if(listViewEmployee.getItems().size()>0)
            listViewEmployee.getItems().clear();
        company.generateStats();
        loadListView(company.getAllEmployee());
        new CsvFileWriter().writeCsvFile(company.getAllEmployee());
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
            List<Employee> tmp = new CopyOnWriteArrayList<>();
            tmp.add(new Employee("position", "no Employee", "", "", 0, -1));
            fillListView(tmp);
        }
        fillListView(allEmployees);
    }
}
