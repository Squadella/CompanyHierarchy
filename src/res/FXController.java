package res;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.text.Text;

public class FXController {

    @FXML
    private Menu menuQuit;

    @FXML
    private Text test;

    public void changeText(String text)
    {
        test.setText(text);
    }
}
