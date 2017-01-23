package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import res.FXController;

public class MainWindow extends Application
{
    private FXController controller;


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
        controller = loader.getController();

        //Stage manipulation
        stage.setTitle("app.Company Hierarchy Manager");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        controller.loadUI();

        controller.getListViewEmployee().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, id) -> {
            if(observable.getValue()!=null)
                controller.refreshEmployee(observable.getValue());
            else
            {
                controller.refreshUI();
            }
        });
    }
}
