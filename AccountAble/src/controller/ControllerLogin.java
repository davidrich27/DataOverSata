package controller;
import model.*;
import view.*;

import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.event.*;

public class ControllerLogin{

  ModelTXT model;
  Stage thisStage;
  Stage stageInitial;
  ControllerInitial ctrlInitial;

  @FXML
  private Label warningLbl;
  @FXML
  private TextField usernameTxt;
  @FXML
  private TextField pwdTxt;
  @FXML
  private Button loginBtn;

  public void initialize() {
    warningLbl.setText("");
  }

  public void setModel(ModelTXT model){
    this.model = model;
  }
  public ModelTXT getModel(){
    return model;
  }
  public void setInitial(Stage stageInitial, ControllerInitial ctrlInitial){
    this.stageInitial = stageInitial;
    this.ctrlInitial = ctrlInitial;
  }
  public void setStage(Stage thisStage){
    this.thisStage = thisStage;
  }

  @FXML
  private void handleLogin(ActionEvent event) {
    String username = usernameTxt.getText();
    String pwd = pwdTxt.getText();

    boolean success = model.testLogin(username, pwd);

    if (success == true){
      System.out.println("Logging user in...");
      User loginUser = model.getUserByUsername(username);
      loginUser.printInfo();
      ctrlInitial.login(loginUser);
      stageInitial.show();
    } else {
      System.out.println("Incorrect creds...");
      warningLbl.setText("Sorry, try again.");
    }

  }
}
