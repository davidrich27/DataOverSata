package controller;
import model.basic.*;
import model.manager.*;
import model.master.*;

import java.io.IOException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.io.File;
import java.awt.Desktop;

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

public class AdminController {

  @FXML
      private Button delAcctBtn;

      @FXML
      private Button delFeeBtn;

      @FXML
      private ListView<Account> acctList;

      @FXML
      private TableColumn<User, String> userColEmail;

      @FXML
      private Button addTransactionBtn;

      @FXML
      private TextField userSearchTxt;

      @FXML
      private TableView<Account> acctAllTbl;

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
      private ListView<User> blockedList;

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
      private ListView<User> allowedList;

      @FXML
      private ChoiceBox<Account> acctDropDown;

      @FXML
      private Button editUserBtn;

      @FXML
      private TextField acctSearchTxt;

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
      private Button acctPermissionBtn;

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
      private TableColumn<Transaction, LocalDateTime> acctColDateEntry;

      @FXML
      private TableColumn<Transaction, LocalDate> acctColDateSale;

      @FXML
      private TableColumn<Transaction, Double> acctColSubTotal;

      @FXML
      private TableColumn<Transaction, Double> acctColFeeTotal;

      @FXML
      private TableColumn<Transaction, Double> acctColTotal;

      @FXML
      private TableColumn<Transaction, Double> acctColBal;

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
      private TableColumn<Code, Integer> codeColID;

      @FXML
      private TableColumn<Code, String> codeColName;

      @FXML
      private TableColumn<Code, String> codeColDescr;

      @FXML
      private TableView<Code> codeTbl;

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

      @FXML
      private MenuItem benCalcMenuItem;

      @FXML
      private Label permissionAcctLbl;

      @FXML
      private Tab overviewTab;

      @FXML
      private Tab acctTab;

      @FXML
      private Tab acctAllTab;

      @FXML
      private Tab usersTab;

      @FXML
      private Tab feesTab;

      @FXML
      private Tab codesTab;

      @FXML
      private Tab transTab;

      @FXML
      private Tab permissionTab;

      @FXML
      private TabPane tabPane;

      @FXML
      private DatePicker transHistStartPicker;

      @FXML
      private DatePicker transHistEndPicker;

  // ************************** Model Variables *******************************

  ModelTXT model;
  Account masterAcct;

  Stage thisStage;
  Stage loginStage;
  LoginController loginCtrl;

  User currentUser;
  ArrayList<Account> currentAccts;
  ArrayList<Transaction> currentTrans;

  // filter mechanisms
  LocalDate filterStartDate;
  LocalDate filterEndDate;
  Account filterAcct;
  Account allAcct;
  ArrayList<Transaction> filteredTrans;

  String stylePath;

  // NOTE: May be able to declare ObservableLists at the beginning (no repop?)

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
  public void resetFactories(){
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
    acctColDateEntry.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDateTime>("DateEntry"));
    acctColDateSale.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("DateSale"));
    acctColSubTotal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("SubTotal"));
    acctColFeeTotal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("FeeTotal"));
    acctColBal.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("AcctBal"));
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

    // Set Codes Factory
    codeColID.setCellValueFactory(new PropertyValueFactory<Code, Integer>("ID"));
    codeColName.setCellValueFactory(new PropertyValueFactory<Code, String>("Name"));
    codeColDescr.setCellValueFactory(new PropertyValueFactory<Code, String>("Descr"));
  }

  // *************************** Login ******************************************

  public void login(User loginUser){
	stylePath = "styles/current_style.css";
    model.reconcileAll();

    currentUser = loginUser;
    currentAccts = model.uaManager.getAllAccts();
    masterAcct = model.uaManager.getMasterAccount();

    toggleAdminView();
    resetText();
    resetFactories();
    getAcctAndTransLists();
    repop();
  }

  // NOTE: Not efficient algorithm
  public void getAcctAndTransLists(){
    if (currentUser.getAdmin()){
      currentAccts = model.uaManager.getAllAccts();
      currentTrans = model.uaManager.getAllTransactions();
    } else {
      ArrayList<Integer> acctIDs = model.uaManager.getAllAcctByUserID(currentUser.getID());
      ArrayList<Integer> transIDs = new ArrayList<Integer>();
      currentAccts = new ArrayList<Account>();
      currentTrans = new ArrayList<Transaction>();
      for (int i=0; i<acctIDs.size(); i++){
        System.out.println("Adding account #" + i);
        currentAccts.add(model.uaManager.getAcctByID(acctIDs.get(i)));
        ArrayList<Integer> tempTransIDs = model.uaManager.getAllTransByAcctID(acctIDs.get(i));
        for (int j=0; j<tempTransIDs.size(); j++){
          System.out.println("Adding trans #" + j);
          transIDs.add(tempTransIDs.get(j));
        }
      }
      for (int i=0; i<transIDs.size(); i++){
        currentTrans.add(model.uaManager.getTransByID(transIDs.get(i)));
      }
    }

  }

  // Populate Tables
  public void repop(){
    ObservableList<Transaction> acctTransObservable = FXCollections.observableArrayList(currentTrans);
    acctTbl.setItems(acctTransObservable);

    ObservableList<Account> acctsObservable = FXCollections.observableArrayList(currentAccts);
    acctsObservable.add(0, masterAcct);
    acctAllTbl.setItems(acctsObservable);

    if (currentUser.getAdmin()){
      ArrayList<User> users = model.uaManager.getAllUsers();
      ObservableList<User> usersObservable = FXCollections.observableArrayList(users);
      userTbl.setItems(usersObservable);

      ArrayList<FeeType> feeTypes = model.uaManager.getAllFeeTypes();
      ObservableList<FeeType> feeTypesObservable = FXCollections.observableArrayList(feeTypes);
      feeTypeTbl.setItems(feeTypesObservable);

      ArrayList<Code> codes = model.uaManager.getAllCodes();
      ObservableList<Code> codesObservable = FXCollections.observableArrayList(codes);
      codeTbl.setItems(codesObservable);
    }
  }

  // Reset Texts
  public void resetText(){
    // Populate Text on Overview Tab
    greetingLbl.setText("Welcome Back, " + currentUser.getName()[0] + "!");
    usernameLbl.setText(currentUser.getUsername());
    firstNameLbl.setText(currentUser.getName()[0]);
    lastNameLbl.setText(currentUser.getName()[1]);
    emailLbl.setText(currentUser.getEmail());
    phoneLbl.setText(currentUser.getPhone());
    // Master Account Balances
    masterAcct = model.uaManager.getMasterAccount();
    masterBalanceLbl.setText(masterAcct.getBalance().toString());
    feeBalanceLbl.setText(masterAcct.getFeesBalance().toString());
    availBalanceLbl.setText(masterAcct.getAvailBalance().toString());
    // Populate All Lists
    ObservableList<Account> acctsObservable = FXCollections.observableArrayList(currentAccts);
    allAcct = new Account(-1, "ALL ACCOUNT TRANSACTIONS");
    acctsObservable.add(0, allAcct);
    acctList.setItems(acctsObservable);
  }

  // NOTE: can't log out of account and log in to admin in same session
  void toggleAdminView(){
    if (!currentUser.getAdmin()){
      tabPane.getTabs().remove(usersTab);
      tabPane.getTabs().remove(acctAllTab);
      tabPane.getTabs().remove(feesTab);
      tabPane.getTabs().remove(codesTab);
      tabPane.getTabs().remove(transTab);
      tabPane.getTabs().remove(permissionTab);
    } else {
      // tabPane.getTabs().add(usersTab);
      // tabPane.getTabs().add(acctAllTab);
      // tabPane.getTabs().add(feesTab);
      // tabPane.getTabs().add(codesTab);
      // tabPane.getTabs().add(transTab);
      // tabPane.getTabs().add(permissionTab);
    }
  }

  // *********************** Refresh *********************************************

  // Refresh all dataViews
  public void refresh(){
    resetText();
    repop();
    // refresh all tables
    acctTbl.refresh();
    userTbl.refresh();
    feeTypeTbl.refresh();
    codeTbl.refresh();
    acctAllTbl.refresh();
    // refresh all lists
    acctList.refresh();
  }
  // Reconcile Accounts to get Proper Balances
  public void reconcile(){
    model.reconcileAll();
  }
  // Filter Transactions by
  public void filterTransactions(){
    ArrayList<Transaction> trans = currentTrans;
    filteredTrans = new ArrayList<Transaction>();
    for (Transaction tran : trans){
      if (filterAcct == null || filterAcct.getID().equals(tran.getAcctID())){
        if (filterEndDate == null || (tran.getDateSale().isBefore(filterEndDate) || tran.getDateSale().equals(filterEndDate))){
          if (filterStartDate == null || (tran.getDateSale().isAfter(filterStartDate) || tran.getDateSale().equals(filterStartDate))){
            filteredTrans.add(tran);
          }
        }
      }
    }
    ObservableList<Transaction> transObservable = FXCollections.observableArrayList(filteredTrans);
    acctTbl.setItems(transObservable);
  }

  // ************************** Menu Events ***************************************

  @FXML
  void logoutClick(ActionEvent event) {
    currentUser = null;
    loginCtrl.clear();
    loginStage.show();
    thisStage.hide();
  }
  @FXML
  void saveClick(ActionEvent event) {
    model.saveAll();
  }
  @FXML
  void reconcileClick(ActionEvent event) {
    model.reconcileAll();
    resetText();
    repop();
  }
  @FXML
  void closeClick(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  void statementClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateReport.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    ReportCreateController newCtrl = newLoader.<ReportCreateController>getController();
    newCtrl.setStage(newStage);
    newCtrl.setHome(thisStage, this);
    newCtrl.setup(currentUser, currentAccts, currentTrans);
    newScene.getStylesheets().add(stylePath);
    newStage.show();
  }

  /* Benefits Calculator */
  @FXML
  void benCalcClick(ActionEvent event) throws Exception {
	//  System.out.println("benCalc clicked!");
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/BCview.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    BCcontroller newCtrl = newLoader.<BCcontroller>getController();
    newCtrl.setStage(newStage);
    newScene.getStylesheets().add(stylePath);
    newStage.show();
  }

  @FXML
  void colorClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewSchemes.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    SchemeController newCtrl = newLoader.<SchemeController>getController();
    newCtrl.setStage(newStage);
    newCtrl.setHome(thisStage, this);
    newScene.getStylesheets().add(stylePath);
    newStage.show();
  }

  @FXML
  void helpClick(ActionEvent event) {
    if (Desktop.isDesktopSupported()) {
      try {
          // Name of PDF File here (save in docs)
          File myFile = new File("../docs/User_Guide.pdf");
          Desktop.getDesktop().open(myFile);
      } catch (IOException ex) {
          // no application registered for PDFs
          System.out.println("ERROR: Desktop not supported.");
      }
    }
  }
  @FXML
  void docClick(ActionEvent event) {
    if (Desktop.isDesktopSupported()) {
      try {
          File myFile = new File("../docs/System_Documentation.pdf");
          Desktop.getDesktop().open(myFile);
      } catch (IOException ex) {
          // no application registered for PDFs
          System.out.println("ERROR: Desktop not supported.");
      }
    }
  }
  @FXML
  void aboutClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewAbout.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    newScene.getStylesheets().add(stylePath);
    newStage.show();
  }

    // ************************ Overview Tab *************************************

  @FXML
  void changeInfoClick(ActionEvent event) throws IOException {
    // Open new instance of viewEditUser
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewChangeInfo.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    ChangeInfoController newCtrl = newLoader.<ChangeInfoController>getController();
    newCtrl.setStage(newStage);
    newCtrl.setHome(thisStage, this);
    newCtrl.setModel(model);
    newCtrl.setupEdit(currentUser);
    newScene.getStylesheets().add(stylePath);
    newStage.show();

  }

    // ****************** Account Tab ********************************************

  @FXML
  void setAcctClick(ActionEvent event) throws Exception {
    filterAcct = acctList.getSelectionModel().getSelectedItem();
    if (filterAcct == null || filterAcct.equals(allAcct)){
      filterAcct = null;
    }
    filterTransactions();
  }

  @FXML
  void acctHistoryClick(ActionEvent event) throws Exception {
    filterStartDate = transHistStartPicker.getValue();
    filterEndDate = transHistEndPicker.getValue();
    filterTransactions();
  }

  @FXML
  void addTransactionClick(ActionEvent event) throws Exception {
    Account selectedAcct = acctList.getSelectionModel().getSelectedItem();
    if (selectedAcct != null && selectedAcct.getID() != -1) {
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateTrans.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      TransCreateController newCtrl = newLoader.<TransCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newCtrl.setupCreate(currentUser, selectedAcct, currentAccts);
      newScene.getStylesheets().add(stylePath);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select Account for Transaction to be entered.");
    }
  }

  @FXML
  void editTransactionClick(ActionEvent event) throws Exception {
    Transaction selectedTrans = acctTbl.getSelectionModel().getSelectedItem();
    if (selectedTrans != null) {
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateTrans.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      TransCreateController newCtrl = newLoader.<TransCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newCtrl.setupEdit(selectedTrans);
      newScene.getStylesheets().add(stylePath);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select Account for Transaction to be entered.");
    }
  }

  @FXML
  void examTransactionClick(ActionEvent event) throws Exception {
    Transaction selectedTrans = acctTbl.getSelectionModel().getSelectedItem();
    if (selectedTrans != null) {
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateTrans.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      TransCreateController newCtrl = newLoader.<TransCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newCtrl.setupExam(selectedTrans);
      newScene.getStylesheets().add(stylePath);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select Account for Transaction to be entered.");
    }
  }

  @FXML
  void delTransactionClick(ActionEvent event) throws Exception {
    Transaction selected = acctTbl.getSelectionModel().getSelectedItem();
    if (selected == null){
      return;
    }
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("You are about to Delete a Transaction");
    alert.setHeaderText("Warning: You are about to Delete a Transaction!");
    alert.setContentText("This cannot be undone. Continue?");

    ButtonType delBtn = new ButtonType("Delete");
    ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    alert.getButtonTypes().setAll(delBtn, cancelBtn);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == delBtn){
        // ... user chose "One"
        if (model.deleteTrans(selected.getID())){
          System.out.println("Transaction #" + selected.getID() + " was deleted!");
          reconcile();
          refresh();
        }
    } else {
        // ... user chose CANCEL or closed the dialog
    }
  }

    // ******************* All Accounts Manager Tab *****************************

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
    newScene.getStylesheets().add(stylePath);
    newCtrl.setupCreate();
    newStage.show();
  }

  @FXML
  void editAcctClick(ActionEvent event) throws Exception {
    // Pass selected account to controller
    Account selected = acctAllTbl.getSelectionModel().getSelectedItem();
    if (selected != null && selected.getID() != 0){
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateAcct.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      AcctCreateController newCtrl = newLoader.<AcctCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newScene.getStylesheets().add(stylePath);
      newCtrl.setupEdit(selected);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select an Account to edit.");
    }
  }

  @FXML
  void permissionClick(ActionEvent event) throws Exception {
    // Pass selected account to controller
    Account selected = acctAllTbl.getSelectionModel().getSelectedItem();
    if (selected != null && selected.getID() != 0){
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewAcctPermissions.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      AcctPermissionsController newCtrl = newLoader.<AcctPermissionsController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newScene.getStylesheets().add(stylePath);
      newCtrl.setup(selected);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select an Account to see permissions.");
    }
  }

  @FXML
  void examAcctClick(ActionEvent event) throws Exception {
    // Pass selected account to controller
    Account selected = acctAllTbl.getSelectionModel().getSelectedItem();
    if (selected != null && selected.getID() != 0){
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateAcct.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      AcctCreateController newCtrl = newLoader.<AcctCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newScene.getStylesheets().add(stylePath);
      newCtrl.setupExam(selected);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select an Account to edit.");
    }
  }

  @FXML
  void delAcctClick(ActionEvent event) throws Exception {
    Account selected = acctAllTbl.getSelectionModel().getSelectedItem();
    if (selected != null && selected.getID() != 0){
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Deleting the '" + selected.getName() + "' Account");
      alert.setHeaderText("Warning: You are about to Delete a Transaction!");
      alert.setContentText("Choose carefully - Deleting an account will also delete all Transactions for that account.  Retiring an account will make it no longer editable, but will stay in the system.");

      ButtonType delBtn = new ButtonType("Delete");
      ButtonType retireBtn = new ButtonType("Retire");
      ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

      alert.getButtonTypes().setAll(delBtn, cancelBtn);

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == delBtn){
          // ... user chose "One"
          if (model.deleteAccount(selected.getID())){
            System.out.println("Account #" + selected.getID() + " was deleted!");
            refresh();
          }
      } else if (result.get() == retireBtn) {
          // ... user chose "Two"
      } else {
          // ... user chose CANCEL or closed the dialog
      }
    }
  }

    // *********************** Users Manager Tab *********************************

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
    newScene.getStylesheets().add(stylePath);
    newCtrl.setupCreate();
    newStage.show();
  }

  @FXML
  void editUserClick(ActionEvent event) throws Exception {
    // Pass selected user to controller
    User selected = userTbl.getSelectionModel().getSelectedItem();
    if (selected != null){
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateUser.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      UserCreateController newCtrl = newLoader.<UserCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newScene.getStylesheets().add(stylePath);
      newCtrl.setupEdit(selected);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select an User to edit.");
    }
  }

  @FXML
  void examUserClick(ActionEvent event) throws Exception {
    // Pass selected user to controller
    User selected = userTbl.getSelectionModel().getSelectedItem();
    if (selected != null){
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateUser.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      UserCreateController newCtrl = newLoader.<UserCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newScene.getStylesheets().add(stylePath);
      newCtrl.setupExam(selected);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select an User to edit.");
    }
  }

  @FXML
  void delUserClick(ActionEvent event) throws Exception {
    User selected = userTbl.getSelectionModel().getSelectedItem();
    if (selected == null){
      return;
    }
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("You are about to Delete or Retire a User Account");
    alert.setHeaderText("Warning: You are about to Delete or Retire a User Account!");
    alert.setContentText("Choose carefully - Retiring a User will allow it to be recovered if you ever need to do so, but deleting cannot be undone. Continue?");

    ButtonType delBtn = new ButtonType("Delete");
    ButtonType retireBtn = new ButtonType("Retire");
    ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    alert.getButtonTypes().setAll(delBtn, retireBtn, cancelBtn);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == delBtn){
        // ... user chose "One"
        if (model.deleteUser(selected.getID())){
          System.out.println("User Account #" + selected.getID() + " was deleted!");
          refresh();
        }
    } else if (result.get() == retireBtn) {
        // ... user chose "Two"
    } else {
        // ... user chose CANCEL or closed the dialog
    }
  }

    // ******************* Fees Manager Tab *************************************

  @FXML
  void newFeeClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateFeeType.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    FeeTypeCreateController newCtrl = newLoader.<FeeTypeCreateController>getController();
    newCtrl.setStage(newStage);
    newCtrl.setHome(thisStage, this);
    newCtrl.setModel(model);
    newScene.getStylesheets().add(stylePath);
    newCtrl.setupCreate();
    newStage.show();
  }

  @FXML
  void editFeeClick(ActionEvent event) throws Exception {
    // Pass selected account to controller
    FeeType selected = feeTypeTbl.getSelectionModel().getSelectedItem();
    if (selected != null){
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateFeeType.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      FeeTypeCreateController newCtrl = newLoader.<FeeTypeCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newScene.getStylesheets().add(stylePath);
      newCtrl.setupEdit(selected);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select a Fee Type to edit.");
    }
  }

  @FXML
  void examFeeClick(ActionEvent event) throws Exception {
  }

  @FXML
  void delFeeClick(ActionEvent event) throws Exception {
    FeeType selected = feeTypeTbl.getSelectionModel().getSelectedItem();
    System.out.println(selected != null);
    if (selected != null){
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("You are about to Delete a Fee Type");
      alert.setHeaderText("Warning: You are about to Fee Type!");
      alert.setContentText("This cannot be undone. Continue?");

      ButtonType delBtn = new ButtonType("Delete");
      ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

      alert.getButtonTypes().setAll(delBtn, cancelBtn);

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == delBtn){
          // ... user chose "One"
          if (model.deleteFeeType(selected.getID())){
            System.out.println("Fee Type #" + selected.getID() + " was deleted!");
            refresh();
          }
      } else {
          // ... user chose CANCEL or closed the dialog
      }
    }
  }

  // ******************* Code Manager Tab ****************************************

  @FXML
  void newCodeClick(ActionEvent event) throws Exception {
    Stage newStage = new Stage();
    FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateCode.fxml"));
    Parent newRoot = newLoader.load();
    Scene newScene = new Scene(newRoot);
    newStage.setScene(newScene);
    CodeCreateController newCtrl = newLoader.<CodeCreateController>getController();
    newCtrl.setStage(newStage);
    newCtrl.setHome(thisStage, this);
    newCtrl.setModel(model);
    newScene.getStylesheets().add(stylePath);
    newCtrl.setupCreate();
    newStage.show();
  }

  @FXML
  void editCodeClick(ActionEvent event) throws Exception {
    // Pass selected account to controller
    Code selected = codeTbl.getSelectionModel().getSelectedItem();
    if (selected != null){
      Stage newStage = new Stage();
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource("../view/ViewCreateCode.fxml"));
      Parent newRoot = newLoader.load();
      Scene newScene = new Scene(newRoot);
      newStage.setScene(newScene);
      CodeCreateController newCtrl = newLoader.<CodeCreateController>getController();
      newCtrl.setStage(newStage);
      newCtrl.setHome(thisStage, this);
      newCtrl.setModel(model);
      newScene.getStylesheets().add(stylePath);
      newCtrl.setupEdit(selected);
      newStage.show();
    } else {
      System.out.println("ERROR: Must select an Code to edit.");
    }
  }

  @FXML
  void examCodeClick(ActionEvent event) throws Exception {
  }

  @FXML
  void delCodeClick(ActionEvent event) throws Exception {
    Code selected = codeTbl.getSelectionModel().getSelectedItem();
    if (selected != null){
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("You are about to Delete a Code");
      alert.setHeaderText("Warning: You are about to Delete a Code!");
      alert.setContentText("This cannot be undone. Continue?");

      ButtonType delBtn = new ButtonType("Delete");
      ButtonType cancelBtn = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

      alert.getButtonTypes().setAll(delBtn, cancelBtn);

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == delBtn){
          // ... user chose "One"
          if (model.deleteCode(selected.getID())){
            System.out.println("Code #" + selected.getID() + " was deleted!");
            refresh();
          }
      } else {
          // ... user chose CANCEL or closed the dialog
      }
    }
  }

  // ************************ Notifications *************************************

  // Warning if user needs to select something but hasn't
  void noSelectNotif(String thing){
    System.out.println("Error: Sorry, you must select a " + thing + " to open this dialog.");
  }
}
