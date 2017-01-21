package res;

import app.Employee;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.text.Text;

import java.util.List;

public class FXController {

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
}
