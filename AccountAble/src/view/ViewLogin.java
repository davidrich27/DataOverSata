package view;
import model.*;
import controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewLogin extends Application {

  public static void main(String[] args) {
    Application.launch(ViewLogin.class, args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    try{
      Parent root = FXMLLoader.load(getClass().getResource("ViewLogin.fxml"));

      stage.setTitle("AccountAble");
      stage.setScene(new Scene(root, 300, 275));
      stage.show();
    } catch (IOException e) {
			e.printStackTrace();
		}
  }
}
