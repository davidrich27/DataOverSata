package controller;
import model.basic.*;
import model.manager.*;
import model.master.*;
import model.security.*;

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

public class SessionEndController{

  // ************************* FX Objects *************************************

  @FXML
  private TextField pwdTxt;
  @FXML
  private Label warningLbl;

  // ************************** Model Variables *******************************

  ModelTXT model;
  Stage thisStage;

  Stage homeStage;
  AdminController homeCtrl;

  User currentUser;

  //*********************** Initialization and Wireup **************************

  public void initialize() {
    warningLbl.setText("");
  }

  public void setModel(ModelTXT model){
    this.model = model;
  }
  public void setHome(Stage homeStage, AdminController homeCtrl){
    this.homeStage = homeStage;
    this.homeCtrl = homeCtrl;
  }
  public void setStage(Stage thisStage){
    this.thisStage = thisStage;
  }
  public void setup(User currentUser){
    this.currentUser = currentUser;
  }

  //************************* Button Events *********************************************

  @FXML
  private void loginClick(ActionEvent event)
  throws NoSuchAlgorithmException, InvalidKeySpecException, PasswordHasher.InvalidHashException, PasswordHasher.CannotPerformOperationException {
    String username = currentUser.getUsername();
    String pwd = pwdTxt.getText();
    boolean success = model.testLogin(username, pwd);
    if (success == true){
      thisStage.hide();
      homeCtrl.resetSession();
    } else {
      System.out.println("Incorrect creds...");
      warningLbl.setText("Sorry, try again.");
    }
  }

  public void logoutClick(){
    thisStage.hide();
    homeCtrl.logout();
  }

  @FXML
  public void exitApplication(ActionEvent event) {
     homeCtrl.logout();
  }
}
