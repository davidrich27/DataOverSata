package controller;
import model.basic.*;
import model.manager.*;
import model.master.*;

import java.util.*;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;

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

public class LoginController{

  // ************************* FX Objects *************************************

  @FXML
  private Label warningLbl;
  @FXML
  private TextField usernameTxt;
  @FXML
  private TextField pwdTxt;
  @FXML
  private Button loginBtn;

  // ************************** Model Variables *******************************

  ModelTXT model;
  Stage thisStage;
  Stage userStage;
  Stage adminStage;
  UserController userCtrl;
  AdminController adminCtrl;

  //*********************** Initialization and Wireup **************************

  public void initialize() {
    warningLbl.setText("");
  }

  public void setDataModel(ModelTXT model){
    this.model = model;
  }
  public ModelTXT getDataModel(){
    return model;
  }
  public void setOtherStages(Stage adminStage, AdminController adminCtrl, Stage userStage, UserController userCtrl){
    this.adminStage = adminStage;
    this.adminCtrl = adminCtrl;
    this.userStage = userStage;
    this.userCtrl = userCtrl;
  }
  public void setStage(Stage thisStage){
    this.thisStage = thisStage;
  }

  //************************* Button Events *********************************************

  @FXML
  private void handleLogin(ActionEvent event) throws InvalidKeySpecException, NoSuchAlgorithmException{
    String username = usernameTxt.getText();
    String pwd = pwdTxt.getText();
    boolean success = model.testLogin(username, pwd);
    if (success == true){
      System.out.println("Logging user in...");
      User loginUser = model.getUserByUsername(username);
      loginUser.printInfo();
      adminCtrl.login(loginUser);
      adminStage.show();
      thisStage.hide();
    } else {
      System.out.println("Incorrect creds...");
      warningLbl.setText("Sorry, try again.");
    }
  }

  public void clear(){
    usernameTxt.setText("");
    pwdTxt.setText("");
  }
}
