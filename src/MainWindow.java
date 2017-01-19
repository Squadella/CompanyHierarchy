import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/res/CompanyHierarchyUI.fxml"));
        Scene scene = new Scene(root, 1100, 515);

        stage.setTitle("Company Hierarchy Manager");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
