package controller;
import model.basic.*;
import model.manager.*;
import model.master.*;

import java.io.IOException;
import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.event.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class ControllerInitial {
/*
    ModelTXT model;
    ControllerLogin loginCtrl;
    Stage thisStage;
    Stage loginStage;
    // User info
    User loginUser;
    ArrayList<Account> loginAccts;

    @FXML
    private Label greetingLbl;
    @FXML
    private Button logoutBtn;
    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField pwdTxt;
    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TableView acctsTbl;
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn descrCol;
    @FXML
    private TableColumn balanceCol;
    @FXML
    private Button deleteBtn;
    @FXML
    private ListView acctsList;

    // called by the FXML loader after the labels declared above are injected:
    public void initialize() {
      System.out.println("Running initialization...");
      greetingLbl.setText("Please Login...");
      logoutBtn.setText("Login");
    }

    public void setModel(ModelTXT model){
      this.model = model;
    }
    public ModelTXT getModel(){
      return model;
    }
    public void setLogin(Stage loginStage, ControllerLogin loginCtrl){
      this.loginStage = loginStage;
      this.loginCtrl = loginCtrl;
    }
    public void setStage(Stage thisStage){
      this.thisStage = thisStage;
    }

    public void login(User loginUser){
      this.loginUser = loginUser;
      if (loginUser.getAdmin() == true){
        loginAccts = model.uaManager.getAllAccts();
      } else {
        loginAccts = model.uaManager.getAllAcctByUser(loginUser);
      }
      loginStage.hide();
      logoutBtn.setText("Log Out");

      greetingLbl.setText("Welcome, " + loginUser.getName()[0] + "!");
      // Show user account info on first panel
      usernameTxt.setText(loginUser.getUsername());
      firstNameTxt.setText(loginUser.getName()[0]);
      lastNameTxt.setText(loginUser.getName()[1]);
      emailTxt.setText(loginUser.getEmail());
      phoneTxt.setText(loginUser.getPhone());
      // Populate table with financial accts
      // acctsTbl.setEditable(false);
      // ObservableList<Account> obsAccts = FXCollections.observableArrayList(loginAccts);
      // idCol.setCellValueFactory(
      //   new PropertyValueFactory<Account,String>("id")
      // );
      // nameCol.setCellValueFactory(
      //   new PropertyValueFactory<Account,String>("name")
      // );
      // descrCol.setCellValueFactory(
      //   new PropertyValueFactory<Account,String>("descr")
      // );
      // balanceCol.setCellValueFactory(
      //   new PropertyValueFactory<Account,String>("balance")
      // );
      // Populate ListView on Account View Tab
      ObservableList<String> acctNames = FXCollections.observableArrayList();
      for (Account acct : loginAccts){
        String acctName = acct.getName();
        acctNames.add(acctName);
      }
      acctsList.setItems(acctNames);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
      // Button was clicked, do something...
      loginStage.show();
      thisStage.hide();
    }

    @FXML
    private void handleDelete(ActionEvent event){
      System.out.println("Delete button clicked!");
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Confirm Deletion");
      // String selectedAcct = acctsList.getSelectionModel().getSelectedItem();
      alert.setHeaderText("You are about to Delete this Account!");
      alert.setContentText("Are you ok with this?");

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK){
          // ... user chose OK
      } else {
          // ... user chose CANCEL or closed the dialog
      }
    }
    */
}
