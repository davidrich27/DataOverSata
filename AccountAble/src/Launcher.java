import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create an instance of the DB / Model
        ModelTXT model = new ModelTXT();
        model.printInfo();

        // Set Stage for Initial View
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewInitial.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.hide();

        // Set stage for Login Screen
        Stage loginStage = new Stage();
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("ViewLogin.fxml"));
        Parent loginRoot = loginLoader.load();
        Scene loginScene = new Scene(loginRoot);
        loginStage.setScene(loginScene);
        loginStage.show();

        // Link the the model to both controllers
        ControllerInitial ctrlInitial = loader.<ControllerInitial>getController();
        ctrlInitial.setModel(model);
        ControllerLogin ctrlLogin = loginLoader.<ControllerLogin>getController();
        ctrlLogin.setModel(model);
        // Link references to eachother's controller and view
        ctrlInitial.setLogin(loginStage, ctrlLogin);
        ctrlInitial.setStage(primaryStage);
        ctrlLogin.setInitial(primaryStage, ctrlInitial);
        ctrlLogin.setStage(loginStage);
    }

    // main method to support non-JavaFX-aware environments:

    public static void main(String[] args) {
        // starts the FX toolkit, instantiates this class,
        // and calls start(...) on the FX Application thread:
        launch(args);
    }
}
