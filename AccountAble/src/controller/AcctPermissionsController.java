package controller;
import model.basic.*;
import model.manager.*;
import model.master.*;

import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

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
import javafx.scene.control.ButtonBar.ButtonData;

public class AcctPermissionsController {

    @FXML
    private ChoiceBox<Account> acctDropDown;

    @FXML
    private Button toAllowedBtn;

    @FXML
    private Button closeBtn;

    @FXML
    private Label permissionAcctLbl;

    @FXML
    private ListView<User> blockedList;

    @FXML
    private Button toBlockedBtn;

    @FXML
    private ListView<User> allowedList;

    @FXML
    private Button acctPermissionBtn;

    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;

    Stage homeStage;
    AdminController homeCtrl;

    ArrayList<Integer> allowedUserIDs;
    ArrayList<User> allowedUsers;
    ArrayList<Account> accts;
    ArrayList<User> users;
    Account permissionAcct;

    // ****************** Initialization & Wireup ************************************

    public void setModel(ModelTXT model){
      this.model = model;
    }
    public void setStage(Stage thisStage){
      this.thisStage = thisStage;
    }
    public void setHome(Stage homeStage, AdminController homeCtrl){
      this.homeStage = homeStage;
      this.homeCtrl = homeCtrl;
    }

    public void initialize() {
    }

    public void setup(Account selectedAcct){
      accts = model.uaManager.getAllAccts();
      ObservableList<Account> acctsObservable = FXCollections.observableArrayList(accts);
      acctDropDown.setItems(acctsObservable);

      users = model.uaManager.getAllUsers();
      ObservableList<User> usersObservable = FXCollections.observableArrayList(users);
      blockedList.setItems(usersObservable);

      permissionAcct = selectedAcct;
      permissionAcctLbl.setText(permissionAcct.getName());
      allowedUserIDs = model.uaManager.getAllUserByAcct(permissionAcct.getID());
      allowedUsers = new ArrayList<User>();
      for (int i : allowedUserIDs){
        User allowedUser = model.uaManager.getUserByID(i);
        allowedUsers.add(allowedUser);
      }
      ObservableList<User> allowedObservable = FXCollections.observableArrayList(allowedUsers);
      allowedList.setItems(allowedObservable);
    }

    // ****************** Button Events *********************************************

      @FXML
      void acctPermissionClick(ActionEvent event) throws Exception {
        permissionAcct = acctDropDown.getSelectionModel().getSelectedItem();
        permissionAcctLbl.setText(permissionAcct.getName());
        allowedUserIDs = model.uaManager.getAllUserByAcct(permissionAcct.getID());
        allowedUsers = new ArrayList<User>();
        for (int i : allowedUserIDs){
          User allowedUser = model.uaManager.getUserByID(i);
          allowedUsers.add(allowedUser);
        }
        ObservableList<User> allowedObservable = FXCollections.observableArrayList(allowedUsers);
        allowedList.setItems(allowedObservable);
      }

      @FXML
      void allowedBtnClick(ActionEvent event) throws Exception {
        User selectedUser = blockedList.getSelectionModel().getSelectedItem();
        if (selectedUser != null && permissionAcct != null){
          if (allowedUsers.contains(selectedUser) == false){
            model.addNewUser_Acct(selectedUser.getID(), permissionAcct.getID());
            allowedUsers.add(selectedUser);
            ObservableList<User> allowedObservable = FXCollections.observableArrayList(allowedUsers);
            allowedList.setItems(allowedObservable);
          }
        }
      }

      @FXML
      void blockedBtnClick(ActionEvent event) throws Exception {
        User selectedUser = allowedList.getSelectionModel().getSelectedItem();
        if (selectedUser != null && permissionAcct != null){
          model.deleteUser_Acct(selectedUser.getID(), permissionAcct.getID());
          allowedUsers.remove(selectedUser);
          ObservableList<User> allowedObservable = FXCollections.observableArrayList(allowedUsers);
          allowedList.setItems(allowedObservable);
        }
      }

      @FXML
      void closeClick(ActionEvent event) {
        thisStage.hide();
      }
}
