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

public class Controller{
  ModelTXT model;
  View view;

  User loggedIn;
  ArrayList<Account> accts;

  public Controller(ModelTXT model, View view){
    this.model = model;
    this.view = view;
  }

  //******************** Controls for Login View ******************************

  @FXML
  private Label warningLbl;
  private TextField usernameTxt;
  private TextField pwdTxt;
  private Button loginBtn;

  @FXML
  private void handleLogin(ActionEvent event) {
    // Button was clicked, do something...
    System.out.println("Login button pressed!");
  }

  //********************* Controls for Initial View ***************************

  @FXML
  private Label greetingLbl;
  private TextField usernameTxt2;

  @
}
