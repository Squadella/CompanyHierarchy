import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import res.FXController;

public class MainWindow extends Application
{
    //Attributes
    private Stage _stage;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    //Startup function, builds the interface from resources
    public void start(Stage stage) throws Exception
    {
        //Loading .fxml from /src/res and setting FXML loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("res/CompanyHierarchyUI.fxml"));
        loader.setLocation(MainWindow.class.getResource("res/CompanyHierarchyUI.fxml"));
        Parent root = loader.load();
        //Creating scene from `loader`
        //Parent root = loader.load();
        Scene scene = new Scene(root, 1100, 515);
        FXController controller = loader.getController();
        controller.changeText("HELLO WORLD!");
        //Stage manipulation
        _stage = stage;
        _stage.setTitle("Company Hierarchy Manager");
        _stage.setScene(scene);
        _stage.setResizable(false);
        _stage.show();

    }

    //Close application
    public void stop() throws Exception
    {
        _stage.close();
    }
}
