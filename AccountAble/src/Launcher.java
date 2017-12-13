// local packages
import model.basic.*;
import model.manager.*;
import model.master.*;
import controller.*;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Launcher extends Application {

    @Override
    public void start(Stage loginStage) throws Exception {
    	
      String styleDefaultPath = "view/Styles.css";      

      // Create an instance of the DB / Model
      ModelTXT model = new ModelTXT();
      model.printInfo();
      System.out.println("New Model initialized...");

      // Set Stage for Login View
      FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LoginView.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      loginStage.setScene(scene);
      loginStage.show();
      // Include Stylesheet
      scene.getStylesheets().add(styleDefaultPath);

      // Set stage for Admin Screen
      Stage adminStage = new Stage();
      FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("view/AdminView.fxml"));
      Parent adminRoot = adminLoader.load();
      Scene adminScene = new Scene(adminRoot);
      adminStage.setScene(adminScene);
      adminStage.setMaximized(true);
      adminScene.getStylesheets().add(styleDefaultPath);
      adminStage.hide();

      // Set stage for Normal User Screen
      Stage userStage = new Stage();
      FXMLLoader userLoader = new FXMLLoader(getClass().getResource("view/UserView.fxml"));
      Parent userRoot = userLoader.load();
      Scene userScene = new Scene(userRoot);
      userStage.setScene(userScene);
      userStage.setMaximized(true);
      userScene.getStylesheets().add(styleDefaultPath);
      userStage.hide();

      // Link the the model to both controllers
      LoginController loginCtrl = loader.<LoginController>getController();
      loginCtrl.setDataModel(model);
      AdminController adminCtrl = adminLoader.<AdminController>getController();
      adminCtrl.setDataModel(model);
      UserController userCtrl = userLoader.<UserController>getController();
      userCtrl.setDataModel(model);
      // Link references to eachother's controller and view
      adminCtrl.setOtherStages(loginStage, loginCtrl);
      userCtrl.setOtherStages(loginStage, loginCtrl);
      adminCtrl.setStage(adminStage);
      userCtrl.setStage(userStage);
      loginCtrl.setOtherStages(adminStage, adminCtrl, userStage, userCtrl);
      loginCtrl.setStage(loginStage);
    }

    // main method to support non-JavaFX-aware environments:
    public static void main(String[] args) {
        // starts the FX toolkit, instantiates this class,
        // and calls start(...) on the FX Application thread:
        launch(args);
    }

}
