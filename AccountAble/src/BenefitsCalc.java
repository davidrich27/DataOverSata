

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class BenefitsCalc extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    //launch program.
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/BCview.fxml"));
        stage.setTitle("Benefits Calculator");
        stage.setScene(new Scene(root, 300, 400));
        stage.setMaximized(false);
        stage.show();
    }
}
