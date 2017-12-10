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

  private ToggleGroup expenseGroup;

    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;
    Stage homeStage;
    AdminController homeCtrl;

    User loginUser;
    Account selectedAcct;
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
    void setupEdit(){
      setMode("edit");

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
    }

    // ************************** Other Events ************************************

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
      //int acctId = acctChoiceBx.getSelectionModel().getSelectedItem().getID();
      int acctId = selectedAcct.getID();
      int userId = loginUser.getID();
      int codeId = codeChoiceBx.getSelectionModel().getSelectedItem().getID();
      double acctTotal = postAcctBal;
      String otherParty = otherPartyTxt.getText();
      String descr = descrTxt.getText();
      LocalDateTime dateEntry = LocalDateTime.now();
      LocalDate dateSale = datePicker.getValue();
      Boolean isExpense = expenseRadio.isSelected();
      Boolean paidFee = false;
      model.addNewTrans(acctId, userId, codeId, subTotal, feeTotal, acctTotal, otherParty, descr, dateEntry, dateSale, isExpense, paidFee);
    }

    void confirmEdit(){

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

    boolean checkFields(){
      return true;
    }

    void calcFees(){
      subTotal = Double.parseDouble(amountTxt.getText());
      feeTotal = 0.0;
      for (FeeType fee : feeTypeIn){
        if (fee.getIsPercent()) {
          feeTotal += fee.getAmt() * subTotal * .01 * -1;
        } else {
          feeTotal += fee.getAmt();
        }
      }
      if (expenseRadio.isSelected()){
        if (subTotal > 0){
          subTotal = subTotal*-1;
        }
      }
      transTotal = subTotal + feeTotal;
      postAcctBal = prevAcctBal + transTotal;
    }
}
