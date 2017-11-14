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

public class UserController {

    @FXML
    private Button logoutBtn;
    @FXML
    private Label lastNameLbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Button changeInfoBtn;
    @FXML
    private ListView<?> acctsList;
    @FXML
    private Label usernameLbl;
    @FXML
    private Button addTransactionBtn;
    @FXML
    private Label firstNameLbl;
    @FXML
    private Label greetingLbl;
    @FXML
    private TextField acctSearchTxt;
    @FXML
    private Label phoneLbl;
    @FXML
    private TableView<?> acctTbl;
    @FXML
    private Button acctHistoryBtn;

    // ************************** Model Variables *******************************

    ModelTXT model;

    User currentUser;
    ArrayList<Account> currentAccts;

    Stage thisStage;
    Stage loginStage;
    LoginController loginCtrl;

    // ************************** Initialization and Wireup *********************

    public void setDataModel(ModelTXT model){
      this.model = model;
    }
    public ModelTXT getDataModel(){
      return model;
    }
    public void setOtherStages(Stage loginStage, LoginController loginCtrl){
      this.loginStage = loginStage;
      this.loginCtrl = loginCtrl;
    }
    public void setStage(Stage thisStage){
      this.thisStage = thisStage;
    }

    public void login(User loginUser){
      currentUser = loginUser;
    }

    // ************************** Button Events ************************************

    @FXML
    void handleLogout(ActionEvent event) {
      currentUser = null;
      loginCtrl.clear();
      loginStage.show();
      thisStage.hide();
    }

}
