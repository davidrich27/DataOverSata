package controller;
import model.basic.*;
import model.manager.*;
import model.master.*;

import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;

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

public class AdminController {

  @FXML
      private Button delAcctBtn;

      @FXML
      private Button delFeeBtn;

      @FXML
      private ListView<String> acctsList;

      @FXML
      private TableColumn<User, String> userColEmail;

      @FXML
      private ListView<String> feeList;

      @FXML
      private Button addTransactionBtn;

      @FXML
      private TextField userSearchTxt;

      @FXML
      private TableView<Account> acctAllTbl;

      @FXML
      private ListView<String> userList;

      @FXML
      private Label feeBalanceLbl;

      @FXML
      private Button examUserBtn;

      @FXML
      private Label masterBalanceLbl;

      @FXML
      private Button toAllowedBtn;

      @FXML
      private Button delUserBtn;

      @FXML
      private Label emailLbl;

      @FXML
      private ScrollPane blockedList;

      @FXML
      private Button examAcctBtn;

      @FXML
      private TextField feeSearchTxt;

      @FXML
      private TableColumn<User, String> userColAdmin;

      @FXML
      private Label firstNameLbl;

      @FXML
      private TableColumn<User, String> userColFirstName;

      @FXML
      private Button editFeeBtn;

      @FXML
      private TableColumn<User, String> userColLastName;

      @FXML
      private TextField transSearchTxt;

      @FXML
      private Button examTransBtn1;

      @FXML
      private Button acctHistoryBtn;

      @FXML
      private TableColumn<User, Integer> userColID;

      @FXML
      private TableColumn<User, String> userColPhone;

      @FXML
      private Button logoutBtn;

      @FXML
      private Label availBalanceLbl;

      @FXML
      private Button newFeeBtn;

      @FXML
      private Label lastNameLbl;

      @FXML
      private TableView<User> userTbl;

      @FXML
      private Button editAcctBtn;

      @FXML
      private Button newAcctBtn;

      @FXML
      private AnchorPane allowedList;

      @FXML
      private ChoiceBox<?> dropdownAcct;

      @FXML
      private Button editUserBtn;

      @FXML
      private ListView<String> transList;

      @FXML
      private TextField acctSearchTxt;

      @FXML
      private ListView<String> acctsAllList;

      @FXML
      private Label phoneLbl;

      @FXML
      private Button toBlockedBtn;

      @FXML
      private TableColumn<User, String> userColUsername;

      @FXML
      private Button changeInfoBtn;

      @FXML
      private Button delTransBtn1;

      @FXML
      private Label usernameLbl;

      @FXML
      private Button selectAcct;

      @FXML
      private Button examFeeBtn;

      @FXML
      private Label greetingLbl;

      @FXML
      private TableColumn<Account, Integer> acctsColID;

      @FXML
      private TableColumn<Account, String> acctsColName;

      @FXML
      private TableColumn<Account, String> acctsColDesc;

      @FXML
      private TableColumn<Account, Double> acctsColBal;

      @FXML
      private TableColumn<Account, Double> acctsColFees;

      @FXML
      private TableColumn<Account, Double> acctsColAvail;

      @FXML
      private TableColumn<FeeType, Integer> feeTypeColID;

      @FXML
      private TableColumn<FeeType, String> feeTypeColName;

      @FXML
      private TableColumn<FeeType, String> feeTypeColDescr;

      @FXML
      private TableColumn<FeeType, Double> feeTypeColAmt;

      @FXML
      private TableColumn<FeeType, Boolean> feeTypeColIsPer;

      @FXML
      private TableColumn<FeeType, Boolean> feeTypeColIsAdd;

      @FXML
      private TableColumn<FeeType, Boolean> feeTypeColIsCustom;

      @FXML
      private TableColumn<Transaction, Integer> transColID;

      @FXML
      private TableColumn<Transaction, Integer> transColAcctID;

      @FXML
      private TableColumn<Transaction, Integer> transColUserID;

      @FXML
      private TableColumn<Transaction, Integer> transColCode;

      @FXML
      private TableColumn<Transaction, LocalDateTime> transColDate;

      @FXML
      private TableColumn<Transaction, Double> transColSubTotal;

      @FXML
      private TableColumn<Transaction, Double> transColFeeTotal;

      @FXML
      private TableColumn<Transaction, Double> transColTotal;

      @FXML
      private TableColumn<Transaction, Double> transColAcctTotal;

      @FXML
      private TableColumn<Transaction, String> transColOtherParty;

      @FXML
      private TableColumn<Transaction, String> transColDescr;

      @FXML
      private TableColumn<Transaction, String> transColIsExpense;

      @FXML
      private TableColumn<Transaction, String> transColPaidFee;

      @FXML
      private TableColumn<Transaction, Integer> acctColID;

      @FXML
      private TableColumn<Transaction, Integer> acctColAcctID;

      @FXML
      private TableColumn<Transaction, Integer> acctColUserID;

      @FXML
      private TableColumn<Transaction, Integer> acctColCode;

      @FXML
      private TableColumn<Transaction, LocalDateTime> acctColDate;

      @FXML
      private TableColumn<Transaction, Double> acctColSubTotal;

      @FXML
      private TableColumn<Transaction, Double> acctColFeeTotal;

      @FXML
      private TableColumn<Transaction, Double> acctColTotal;

      @FXML
      private TableColumn<Transaction, Double> acctColAcctTotal;

      @FXML
      private TableColumn<Transaction, String> acctColOtherParty;

      @FXML
      private TableColumn<Transaction, String> acctColDescr;

      @FXML
      private TableColumn<Transaction, String> acctColIsExpense;

      @FXML
      private TableColumn<Transaction, String> acctColPaidFee;

      @FXML
      private Button editTransBtn1;

      @FXML
      private Button newUserBtn;

      @FXML
      private TableView<Transaction> acctTbl;

      @FXML
      private TextField acctAllSearchTxt;

      @FXML
      private TableView<FeeType> feeTypeTbl;

      @FXML
      private TableView<Transaction> transTbl;

  // ************************** Model Variables *******************************

  ModelTXT model;
  Account master;

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

  // Set Values of Model to Populate Tables
  public void setFactories(){
    // Set User Factory
    userColID.setCellValueFactory(new PropertyValueFactory<User, Integer>("ID"));
    userColUsername.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
    userColFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
    userColLastName.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
    userColEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
    userColPhone.setCellValueFactory(new PropertyValueFactory<User, String>("Phone"));
    userColAdmin.setCellValueFactory(new PropertyValueFactory<User, String>("Admin"));

    // Set Account Transaction Factory
    acctColID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("ID"));
    acctColAcctID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("AcctID"));
    acctColUserID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("UserID"));
    acctColCode.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("CodeID"));
    acctColDate.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDateTime>("Date"));
    acctColSubTotal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("SubTotal"));
    acctColFeeTotal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("FeeTotal"));
    acctColTotal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Total"));
    acctColDescr.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Descr"));
    acctColOtherParty.setCellValueFactory(new PropertyValueFactory<Transaction, String>("OtherParty"));
    acctColIsExpense.setCellValueFactory(new PropertyValueFactory<Transaction, String>("IsExpense"));
    acctColPaidFee.setCellValueFactory(new PropertyValueFactory<Transaction, String>("PaidFee"));

    // Set Accounts Factory
    acctsColID.setCellValueFactory(new PropertyValueFactory<Account, Integer>("ID"));
    acctsColName.setCellValueFactory(new PropertyValueFactory<Account, String>("Name"));
    acctsColDesc.setCellValueFactory(new PropertyValueFactory<Account, String>("Descr"));
    acctsColBal.setCellValueFactory(new PropertyValueFactory<Account, Double>("Balance"));
    acctsColFees.setCellValueFactory(new PropertyValueFactory<Account, Double>("FeesBalance"));
    acctsColAvail.setCellValueFactory(new PropertyValueFactory<Account, Double>("AvailBalance"));

    // Set Fees Factory
    feeTypeColID.setCellValueFactory(new PropertyValueFactory<FeeType, Integer>("ID"));
    feeTypeColName.setCellValueFactory(new PropertyValueFactory<FeeType, String>("Name"));
    feeTypeColDescr.setCellValueFactory(new PropertyValueFactory<FeeType, String>("Descr"));
    feeTypeColAmt.setCellValueFactory(new PropertyValueFactory<FeeType, Double>("Amt"));
    feeTypeColIsPer.setCellValueFactory(new PropertyValueFactory<FeeType, Boolean>("isPercent"));
    feeTypeColIsAdd.setCellValueFactory(new PropertyValueFactory<FeeType, Boolean>("isAdditional"));
    feeTypeColIsCustom.setCellValueFactory(new PropertyValueFactory<FeeType, Boolean>("isCustom"));

    // Set All Transaction Factory
    transColID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("ID"));
    transColAcctID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("AcctID"));
    transColUserID.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("UserID"));
    transColCode.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("CodeID"));
    transColDate.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDateTime>("Date"));
    transColSubTotal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("SubTotal"));
    transColFeeTotal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("FeeTotal"));
    transColTotal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Total"));
    transColDescr.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Descr"));
    transColOtherParty.setCellValueFactory(new PropertyValueFactory<Transaction, String>("OtherParty"));
    transColIsExpense.setCellValueFactory(new PropertyValueFactory<Transaction, String>("IsExpense"));
    transColPaidFee.setCellValueFactory(new PropertyValueFactory<Transaction, String>("PaidFee"));
  }

  // Populate Tables
  public void repop(){
    ArrayList<User> users = model.uaManager.getAllUsers();
    ObservableList<User> usersObservable = FXCollections.observableArrayList(users);
    userTbl.setItems(usersObservable);

    ArrayList<Transaction> acctTrans = model.uaManager.getAllTransactions();
    ObservableList<Transaction> acctTransObservable = FXCollections.observableArrayList(acctTrans);
    acctTbl.setItems(acctTransObservable);

    ArrayList<Account> accts = model.uaManager.getAllAccts();
    ObservableList<Account> acctsObservable = FXCollections.observableArrayList(accts);
    acctAllTbl.setItems(acctsObservable);

    ArrayList<FeeType> feeTypes = model.uaManager.getAllFeeTypes();
    ObservableList<FeeType> feeTypesObservable = FXCollections.observableArrayList(feeTypes);
    feeTypeTbl.setItems(feeTypesObservable);

    ArrayList<Transaction> trans = model.uaManager.getAllTransactions();
    ObservableList<Transaction> transObservable = FXCollections.observableArrayList(trans);
    transTbl.setItems(transObservable);
  }

  // ************************** Other Events ************************************

  public void login(User loginUser){
    currentUser = loginUser;
    currentAccts = model.uaManager.getAllAccts();

    // OVERVIEW TAB
    // Populate Text on Overview Tab
    greetingLbl.setText("Welcome Back, " + currentUser.getName()[0] + "!");
    usernameLbl.setText(currentUser.getUsername());
    firstNameLbl.setText(currentUser.getName()[0]);
    lastNameLbl.setText(currentUser.getName()[1]);
    emailLbl.setText(currentUser.getEmail());
    phoneLbl.setText(currentUser.getPhone());
    // Master Account Balances
    master = model.uaManager.getMasterAccount();
    masterBalanceLbl.setText(master.getBalance().toString());
    feeBalanceLbl.setText(master.getFeesBalance().toString());
    availBalanceLbl.setText(master.getAvailBalance().toString());

    // ACCOUNTS TAB
    // Populate All Accounts Lists
    ObservableList<String> acctNames = FXCollections.observableArrayList();
    for (Account acct : currentAccts){
      String acctName = acct.getName();
      acctNames.add(acctName);
    }
    acctsList.setItems(acctNames);
    acctsAllList.setItems(acctNames);

    // USERS TAB
    // Populate User List
    ObservableList<String> userNames = FXCollections.observableArrayList();
    ArrayList<User> allUsers = model.uaManager.getAllUsers();
    for (User user : allUsers){
      String userName = user.getUsername();
      userNames.add(userName);
    }
    userList.setItems(userNames);

    // FEETYPE TAB
    // Populate FeeType List
    ObservableList<String> feeTypeNames = FXCollections.observableArrayList();
    ArrayList<FeeType> allFeeTypes = model.uaManager.getAllFeeTypes();
    for (FeeType feeType : allFeeTypes){
      String feeTypeName = feeType.getName();
      feeTypeNames.add(feeTypeName);
    }
    feeList.setItems(feeTypeNames);

    setFactories();
    repop();
  }

  public void refresh(){
    repop();
  }

  // ************************** FX Events ***************************************

  @FXML
  void logoutClick(ActionEvent event) {
    currentUser = null;
    loginCtrl.clear();
    loginStage.show();
    thisStage.hide();
  }

    // ************************ Overview Tab ***************

  @FXML
  void changeInfoClick(ActionEvent event) throws IOException {
    // Open new instance of viewEditUser
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewEditUser.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    UserEditController newCtrl = newLoader.<UserEditController>getController();
    newCtrl.setStage(newStage);
    newCtrl.populate(currentUser);
    newStage.show();
  }

    // *********************** Users Overview Tab **********

  @FXML
  void newUserClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateUser.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    UserCreateController newCtrl = newLoader.<UserCreateController>getController();
    newCtrl.setStage(newStage);
    newCtrl.setHome(thisStage, this);
    newCtrl.setModel(model);
    newStage.show();
  }

    // ****************** Transaction Overview Tab **********

  @FXML
  void addTransactionClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateTrans.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    TransCreateController newCtrl = newLoader.<TransCreateController>getController();
    newCtrl.setStage(newStage);
    newCtrl.setHome(thisStage, this);
    newCtrl.setModel(model);
    newStage.show();
  }

    // ******************* All Accounts Overview Tab ********

  @FXML
  void newAcctClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateAcct.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    AcctCreateController newCtrl = newLoader.<AcctCreateController>getController();
    newCtrl.setStage(newStage);
    newCtrl.setHome(thisStage, this);
    newCtrl.setModel(model);
    newStage.show();
  }

    // ******************* Fees Overview Tab ****************

  @FXML
  void newFeeClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateFeeType.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    FeeTypeCreateController newCtrl = newLoader.<FeeTypeCreateController>getController();
    newCtrl.setStage(newStage);
    newStage.show();
  }
}
