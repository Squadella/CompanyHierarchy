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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("res/CompanyHierarchyUI.fxml"));
        loader.setLocation(MainWindow.class.getResource("res/CompanyHierarchyUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1100, 515);
        _controller = loader.getController();
        _company = new Company();

        //Stage manipulation
        Stage _stage = stage;
        _stage.setTitle("Company Hierarchy Manager");
        _stage.setScene(scene);
        _stage.setResizable(false);
        _stage.show();

        loadUI();
    }

    private void loadUI()
    {
        //Charger société en attributs locaux
        //Charger les employees
        loadListView(/* Pass employee list here */);
        refreshUI();
    }

    //Refresh everything in UI except Employee frame
    private void refreshUI()
    {
        _controller.setTextTotalHRExpenses("Total HR expenses : " + _company.getTotalCost() + " $");
        _controller.setTextTotalEmployee("Number of employees : " + _company.getEmployeeNumber());
        _controller.setTextMostExpDpt("Most expensive departement : " + _company.getMostExpensiveDepartment());
        _controller.setTextLessExpDpt("Less expensive departement : " + _company.getLessExpensiveDepartment());
        _controller.setTextAverageDptExp("Average departement expenses : " + _company.getAverageDepartmentCost() + " $");

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

    //Load `listViewEmployee` w/ magic
    private void loadListView(/* Pass employee list */)
    {
        List<String> tmp = new ArrayList<>();

        tmp.add("1");
        tmp.add("2");
        tmp.add("3");

        _controller.fillListView(tmp);
    }
}
