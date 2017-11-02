import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class View{
  public ViewLogin login;
  public ViewInitial initial;

  public Stage primaryStage;

  public View(Stage primaryStage){
    this.primaryStage = primaryStage;

    login = new ViewLogin();
    initial= new ViewInitial();
  }
}
