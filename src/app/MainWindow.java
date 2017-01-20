package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import res.FXController;

import java.util.ArrayList;
import java.util.List;

public class MainWindow extends Application
{
    private FXController _controller;
    private Company _company;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    //Startup function, builds the interface from resources
    public void start(Stage stage) throws Exception
    {
        //Loading .fxml from /src/res and setting FXML loader (w/ controller)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res/CompanyHierarchyUI.fxml"));
        loader.setLocation(MainWindow.class.getResource("/res/CompanyHierarchyUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1100, 515);
        _controller = loader.getController();
        _company = new Company();

        //Stage manipulation
        stage.setTitle("app.Company Hierarchy Manager");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        loadUI();

        _controller.getListViewEmployee().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, id) -> {
            refreshEmployee(observable.getValue());
        });
    }

    private void loadUI()
    {
        //Charger les employees
        String fileName="FILE NAME OF CSV";
        _company.loadCompanyfromFile(fileName);
        loadListView(_company.getAllEmployee());
        refreshUI();
    }

    //Refresh everything in UI except app.Employee frame
    private void refreshUI()
    {
        _controller.setTextTotalHRExpenses("Total HR expenses : " + _company.getTotalCost() + " $");
        _controller.setTextTotalEmployee("Number of employees : " + _company.getEmployeeNumber());
        _controller.setTextMostExpDpt("Most expensive department : " + _company.getMostExpensiveDepartment());
        _controller.setTextLessExpDpt("Less expensive department : " + _company.getLessExpensiveDepartment());
        _controller.setTextAverageDptExp("Average department expenses : " + _company.getAverageDepartmentCost() + " $");

        _controller.setTextAccExpenses("Total expenses : " + Float.toString(_company.getDptExpenses("Accounting")) + " $");
        _controller.setTextAccEmployee("Number of employees : " + Float.toString(_company.getDptEmployee("Accounting")));

        _controller.setTextSalesExpenses("Total expenses : " + Float.toString(_company.getDptExpenses("Sales")) + " $");
        _controller.setTextSalesEmployee("Number of employees : " + Float.toString(_company.getDptEmployee("Sales")));

        _controller.setTextMarkExpenses("Total expenses : " + Float.toString(_company.getDptExpenses("Marketing")) + " $");
        _controller.setTextMarkEmployee("Number of employees : " + Float.toString(_company.getDptEmployee("Marketing")));

        _controller.setTextManuExpenses("Total expenses : " + Float.toString(_company.getDptExpenses("Manufacturing")) + " $");
        _controller.setTextManuEmployee("Number of employees : " + Float.toString(_company.getDptEmployee("Manufacturing")));

        //Actualiser l'affichage pour employee
    }

    public void refreshEmployee(Employee employee)
    {
        _controller.setTextEmployeeFirstName(employee.getFirstName());
        _controller.setTextEmployeeLastName(employee.getSirName());
        _controller.setTextEmployeeDpt(employee.getDepartment());
        _controller.setTextEmployeePosition(employee.getPosition());
        _controller.setTextEmployeeSalary(Float.toString(employee.getSalary()));

        if(employee.getSupervisor() != null)
            _controller.setTextEmployeeSuperior(employee.getSupervisor().toString());
        else
            _controller.setTextEmployeeSuperior("No superior");

        if(employee.getSubEmployee().size() != 0)
        {
            String tmp = employee.getSubEmployee().get(0).toString();

            for(int i = 1; i < employee.getSubEmployee().size() ; ++i)
                tmp += ", " + employee.getSubEmployee().get(i);

            _controller.setTextEmployeeSubordonate(tmp);
        }
        else
            _controller.setTextEmployeeSubordonate("No subordinates");
    }

    //Load `listViewEmployee` w/ magic
    private void loadListView(List<Employee> allEmployees)
    {
        if(allEmployees.size()<=0)
        {
            List<Employee> tmp = new ArrayList<>();
            tmp.add(new Employee("position", "no Employee", "", "", 0, -1));
            _controller.fillListView(tmp);
        }
        _controller.fillListView(allEmployees);
    }
}
