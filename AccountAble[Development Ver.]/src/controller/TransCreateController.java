package controller;

import model.basic.*;
import model.manager.*;
import model.master.*;

import java.util.*;
import java.time.LocalDate;
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

public class TransCreateController {

  @FXML
  private Label prevAcctBalLbl;

  @FXML
  private Label newAcctBalLbl;

  @FXML
  private Button cancelBtn;

  @FXML
  private Label transSubTotalLbl;

  @FXML
  private ChoiceBox<Code> codeChoiceBx;

  @FXML
  private Button confirmBtn;

  @FXML
  private DatePicker datePicker;

  @FXML
  private ChoiceBox<Account> acctChoiceBx;

  @FXML
  private RadioButton depositRadio;

  @FXML
  private Button recalcBtn;

  @FXML
  private Button feeInBtn;

  @FXML
  private Button feeOutBtn;

  @FXML
  private TextField otherPartyTxt;

  @FXML
  private TextField amountTxt;

  @FXML
  private Label feesTotalLbl;

  @FXML
  private ListView<FeeType> feeOutList;

  @FXML
  private RadioButton expenseRadio;

  @FXML
  private Label transTotalLbl;

  @FXML
  private ListView<FeeType> feeInList;

  @FXML
  private TextArea descrTxt;

  @FXML
  private Label titleLbl;

  @FXML
  private Label warningLbl;

  @FXML
  private RadioButton feeYesRadio;

  @FXML
  private RadioButton feeNoRadio;

  private ToggleGroup expenseGroup;

  private ToggleGroup feeGroup;

    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;
    Stage homeStage;
    AdminController homeCtrl;

    User loginUser;
    Account selectedAcct;
    Transaction selectedTrans;
    ArrayList<Account> currentAccts;
    String mode;

    ArrayList<FeeType> feeTypeIn;

    Double prevAcctBal;
    Double subTotal;
    Double feeTotal;
    Double transTotal;
    Double postAcctBal;

    // ************************** Initialization and Wireup *********************

    public void initialize() {
      expenseGroup = new ToggleGroup();
      expenseRadio.setToggleGroup(expenseGroup);
      depositRadio.setToggleGroup(expenseGroup);
      expenseRadio.setSelected(true);

      feeGroup = new ToggleGroup();
      feeYesRadio.setToggleGroup(feeGroup);
      feeNoRadio.setToggleGroup(feeGroup);
      feeNoRadio.setSelected(true);
    }
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
    public void setMode(String mode){
      this.mode = mode;
    }
    // public void setLists(ObservableList<Account> currentAccts){
    //   this.currentAccts = currentAccts;
    // }
    void setupCreate(User loginUser, Account selectedAcct, ArrayList<Account> currentAccts){
      this.loginUser = loginUser;
      this.currentAccts = currentAccts;
      this.selectedAcct = selectedAcct;
      setMode("create");

      titleLbl.setText("Create Transaction");
      warningLbl.setVisible(false);

      prevAcctBal = selectedAcct.getBalance();
      prevAcctBalLbl.setText(prevAcctBal.toString());
      transSubTotalLbl.setText("0.0");
      feesTotalLbl.setText("0.0");
      transTotalLbl.setText("0.0");
      newAcctBalLbl.setText(prevAcctBal.toString());

      datePicker.setValue(LocalDate.now());

      popLists();
    }
    void setupEdit(Transaction selectedTrans){
      this.selectedTrans = selectedTrans;
      setMode("edit");
      titleLbl.setText("Edit Transaction");

      // For editing, transaction cannot change accounts
      selectedAcct = model.uaManager.getAcctByID(selectedTrans.getAcctID());
      currentAccts = new ArrayList<Account>();
      currentAccts.add(selectedAcct);
      popLists();
      // Find code in list and preselect
      ArrayList<Code> codes = model.uaManager.getAllCodes();
      for (int i=0; i<codes.size(); i++){
        if (codes.get(i).getID().equals(selectedTrans.getCodeID())){
          codeChoiceBx.getSelectionModel().select(i);
        }
      }
      amountTxt.setText(selectedTrans.getSubTotal().toString());
      descrTxt.setText(selectedTrans.getDescr());
      otherPartyTxt.setText(selectedTrans.getOtherParty());
      if (selectedTrans.getIsExpense()){
        expenseRadio.setSelected(true);
      } else {
        depositRadio.setSelected(true);
      }
      if(selectedTrans.getPaidFee()){
        feeYesRadio.setSelected(true);
      } else {
        feeNoRadio.setSelected(true);
      }
      datePicker.setValue(selectedTrans.getDateSale());

      prevAcctBal = selectedTrans.getPrevAcctBal();
      subTotal = selectedTrans.getSubTotal();
      feeTotal = selectedTrans.getFeeTotal();
      transTotal = selectedTrans.getTotal();
      postAcctBal = selectedTrans.getAcctBal();
      prevAcctBalLbl.setText(prevAcctBal.toString());
      transSubTotalLbl.setText(subTotal.toString());
      feesTotalLbl.setText(feeTotal.toString());
      transTotalLbl.setText(transTotal.toString());
      newAcctBalLbl.setText(postAcctBal.toString());

      warningLbl.setText("NOTE: To make changes, you must re-input proper fees.");
    }
    void setupExam(Transaction selectedTrans){
      this.selectedTrans = selectedTrans;
      setMode("edit");
      titleLbl.setText("Edit Transaction");

      // For editing, transaction cannot change accounts
      selectedAcct = model.uaManager.getAcctByID(selectedTrans.getAcctID());
      currentAccts = new ArrayList<Account>();
      currentAccts.add(selectedAcct);
      popLists();
      // Find code in list and preselect
      ArrayList<Code> codes = model.uaManager.getAllCodes();
      for (int i=0; i<codes.size(); i++){
        if (codes.get(i).getID().equals(selectedTrans.getCodeID())){
          codeChoiceBx.getSelectionModel().select(i);
        }
      }
      amountTxt.setText(selectedTrans.getSubTotal().toString());
      descrTxt.setText(selectedTrans.getDescr());
      otherPartyTxt.setText(selectedTrans.getOtherParty());
      if (selectedTrans.getIsExpense()){
        expenseRadio.setSelected(true);
      } else {
        depositRadio.setSelected(true);
      }
      if(selectedTrans.getPaidFee()){
        feeYesRadio.setSelected(true);
      } else {
        feeNoRadio.setSelected(true);
      }
      datePicker.setValue(selectedTrans.getDateSale());

      prevAcctBal = selectedTrans.getPrevAcctBal();
      subTotal = selectedTrans.getSubTotal();
      feeTotal = selectedTrans.getFeeTotal();
      transTotal = selectedTrans.getTotal();
      postAcctBal = selectedTrans.getAcctBal();
      prevAcctBalLbl.setText(prevAcctBal.toString());
      transSubTotalLbl.setText(subTotal.toString());
      feesTotalLbl.setText(feeTotal.toString());
      transTotalLbl.setText(transTotal.toString());
      newAcctBalLbl.setText(postAcctBal.toString());

      warningLbl.setText("NOTE: To make changes, you must re-input proper fees.");

      confirmBtn.setVisible(false);
    }

    void popLists(){
      ArrayList<Code> codes = model.uaManager.getAllCodes();
      ObservableList<Code> codesObservable = FXCollections.observableArrayList(codes);
      codeChoiceBx.setItems(codesObservable);

      feeTypeIn = new ArrayList<FeeType>();
      ArrayList<FeeType> feeTypes = model.uaManager.getAllFeeTypes();
      ObservableList<FeeType> feeTypesObservable = FXCollections.observableArrayList(feeTypes);
      feeOutList.setItems(feeTypesObservable);

      ObservableList<Account> acctsObservable = FXCollections.observableArrayList(currentAccts);
      acctChoiceBx.setItems(acctsObservable);
      for (int i=0; i<currentAccts.size(); i++){
        if (currentAccts.get(i).equals(selectedAcct)){
          acctChoiceBx.getSelectionModel().select(i);
          break;
        }
      }
    }

    // ************************** Button Events ************************************

    @FXML
    void confirmClick(ActionEvent event) {
      if (mode.equals("create")){
        confirmCreate();
      } else if (mode.equals("edit")){
        confirmEdit();
      }
      thisStage.hide();
      homeCtrl.refresh();
    }

    void confirmCreate(){
      calcFees();
      int acctId = acctChoiceBx.getSelectionModel().getSelectedItem().getID();
      int userId = loginUser.getID();
      int codeId = codeChoiceBx.getSelectionModel().getSelectedItem().getID();
      double acctBal = postAcctBal;
      String otherParty = otherPartyTxt.getText();
      String descr = descrTxt.getText();
      LocalDateTime dateEntry = LocalDateTime.now();
      LocalDate dateSale = datePicker.getValue();
      Boolean isExpense = expenseRadio.isSelected();
      Boolean paidFee = feeYesRadio.isSelected();
      model.addNewTrans(acctId, userId, codeId, subTotal, feeTotal, acctBal, otherParty, descr, dateEntry, dateSale, isExpense, paidFee);

      homeCtrl.reconcile();
      homeCtrl.refresh();
    }

    void confirmEdit(){
      calcFees();
      int transID = selectedTrans.getID();
      int acctId = acctChoiceBx.getSelectionModel().getSelectedItem().getID();
      int userId = selectedTrans.getUserID();
      int codeId = codeChoiceBx.getSelectionModel().getSelectedItem().getID();
      double acctBal = postAcctBal;
      String otherParty = otherPartyTxt.getText();
      String descr = descrTxt.getText();
      LocalDateTime dateEntry = LocalDateTime.now();
      LocalDate dateSale = datePicker.getValue();
      Boolean isExpense = expenseRadio.isSelected();
      Boolean paidFee = feeYesRadio.isSelected();
      model.editTrans(transID, acctId, userId, codeId, subTotal, feeTotal, acctBal, otherParty, descr, dateEntry, dateSale, isExpense, paidFee);

      homeCtrl.reconcile();
      homeCtrl.refresh();
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

    @FXML
    void feeOutClick(ActionEvent event) {
      FeeType selectedFee = feeInList.getSelectionModel().getSelectedItem();
      if (selectedFee != null){
        for (int i=0; i<feeTypeIn.size(); i++){
          if (feeTypeIn.get(i).equals(selectedFee)){
            feeTypeIn.remove(i);
            ObservableList<FeeType> feeInObservable = FXCollections.observableArrayList(feeTypeIn);
            feeInList.setItems(feeInObservable);
            break;
          }
        }
      }
    }

    @FXML
    void feeInClick(ActionEvent event) {
      FeeType selectedFee = feeOutList.getSelectionModel().getSelectedItem();
      if (selectedFee != null){
        if (feeTypeIn.contains(selectedFee) == false){
          feeTypeIn.add(selectedFee);
          ObservableList<FeeType> feeInObservable = FXCollections.observableArrayList(feeTypeIn);
          feeInList.setItems(feeInObservable);
        }
      }
    }

    @FXML
    void recalcClick(ActionEvent event) {
      calcFees();
      prevAcctBalLbl.setText(prevAcctBal.toString());
      transSubTotalLbl.setText(subTotal.toString());
      feesTotalLbl.setText(feeTotal.toString());
      transTotalLbl.setText(transTotal.toString());
      newAcctBalLbl.setText(postAcctBal.toString());
    }

    // ********************* Helper Functions ***********************************

    boolean checkFields(){
      return true;
    }

    void calcFees(){
      subTotal = Double.parseDouble(amountTxt.getText());
      feeTotal = 0.0;
      for (FeeType fee : feeTypeIn){
        if (fee.getIsPercent()) {
          feeTotal += fee.getAmt() * subTotal * .01;
        } else {
          feeTotal += fee.getAmt();
        }
        if (feeTotal > 0){
          feeTotal = feeTotal * -1;
        }
      }
      if (expenseRadio.isSelected()){
        if (subTotal > 0){
          subTotal = subTotal * -1;
        }
      } else {
        if (subTotal < 0){
          subTotal = subTotal * -1;
        }
      }
      transTotal = subTotal + feeTotal;
      postAcctBal = prevAcctBal + transTotal;
    }
}
