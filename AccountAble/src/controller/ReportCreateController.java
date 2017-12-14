package controller;

import model.basic.*;
import model.manager.*;
import model.master.*;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.PrintWriter;

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

public class ReportCreateController {

  @FXML
  private DatePicker endDatePicker;

  @FXML
  private Button cancelBtn;

  @FXML
  private TextField nameTxt;

  @FXML
  private TextField filenameTxt;

  @FXML
  private Label titleLbl;

  @FXML
  private DatePicker startDatePicker;

  @FXML
  private Button confirmBtn;

  @FXML
  private Label warningLbl;

  @FXML
  private ChoiceBox<Account> acctChoiceBx;

  @FXML
  private TextArea descrTxt;

    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;

    Stage homeStage;
    AdminController homeCtrl;

    User currentUser;
    ArrayList<Account> currentAccts;
    ArrayList<Transaction> currentTrans;

    Account filterAcct;
    LocalDate filterStartDate;
    LocalDate filterEndDate;

    ArrayList<Transaction> filteredTrans;

    String reportFileName, reportName, reportDescr;
    Double totalNet, totalDeposits, totalExpenses;

    // ************************** Initialization and Wireup *********************

    public void initialize() {
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

    void setup(User currentUser, ArrayList<Account> currentAccts, ArrayList<Transaction> currentTrans){
      this.currentUser = currentUser;
      this.currentAccts = currentAccts;
      this.currentTrans = currentTrans;

      LocalDate today = LocalDate.now();
      startDatePicker.setValue(today.minusMonths(1));
      endDatePicker.setValue(today);

      popLists();

      warningLbl.setVisible(false);
    }

    void popLists(){
      Account masterAcct = new Account(0, "ALL ACCOUNTS");
      currentAccts.add(0, masterAcct);
      ObservableList<Account> acctsObservable = FXCollections.observableArrayList(currentAccts);
      acctChoiceBx.setItems(acctsObservable);
      acctChoiceBx.getSelectionModel().selectFirst();
      currentAccts.remove(0);
    }

    // ************************** Button Events ************************************

    @FXML
    void confirmClick(ActionEvent event) {
      reportFileName = filenameTxt.getText();
      reportName = nameTxt.getText();
      reportDescr = descrTxt.getText();

      filterAcct = acctChoiceBx.getSelectionModel().getSelectedItem();
      if (filterAcct.getID() == 0){
        filterAcct = null;
      }
      filterStartDate = startDatePicker.getValue();
      filterEndDate = endDatePicker.getValue();
      filterTransactions();
      buildStatement();
      openStatement();

      thisStage.hide();
      homeCtrl.refresh();
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

    // ********************* Helper Functions ***********************************

    private boolean checkFields(){
      return true;
    }

    private void filterTransactions() {
      filteredTrans = new ArrayList<Transaction>();
      for (Transaction tran : currentTrans){
        if (filterAcct == null || filterAcct.getID().equals(tran.getAcctID())){
          if (filterEndDate == null || (tran.getDateSale().isBefore(filterEndDate) || tran.getDateSale().equals(filterEndDate))){
            if (filterStartDate == null || (tran.getDateSale().isAfter(filterStartDate) || tran.getDateSale().equals(filterStartDate))){
              filteredTrans.add(tran);
            }
          }
        }
      }
    }

    private void buildStatement(){
      Collections.sort(filteredTrans, Transaction.BY_DATESALE_EARLIEST());
      totalNet = 0.0;
      totalExpenses = 0.0;
      totalDeposits = 0.0;

      try {
        PrintWriter writer = new PrintWriter("../exports/" + reportFileName + ".txt", "UTF-8");
        // Create Header of Document
        writer.println("");
        writer.println("Financial Report: " + reportName);
        if (reportDescr != "" && reportDescr != null){
          writer.println("");
          writer.println("Description: " + reportDescr);
        }
        writer.println("");
        if (filterAcct != null){
          writer.println("Account: " + filterAcct.getName());
        } else {
          writer.println("Account: ALL ACCOUNTS");
        }
        writer.println("");
        writer.println("Date Range: From " + filterStartDate.toString() + " to " + filterEndDate.toString());
        writer.println("");
        writer.println("=======================================");
        writer.println("");
        // Iterate through transactions and output them
        for (Transaction tran : filteredTrans){
          // Print transaction to Line
          writer.println(tran.getDateSale() + "\t $" + tran.getSubTotal() + "\t" + tran.getOtherParty() + " || " + tran.getDescr());
          writer.println("");
          // Add trans to totals
          totalNet += tran.getSubTotal();
          if (tran.getIsExpense()){
            totalExpenses += tran.getSubTotal();
          } else {
            totalDeposits += tran.getSubTotal();
          }
        }
        writer.println("=========================================");
        writer.println("");
        writer.println("Total Expenses: $" + totalExpenses + "\t Total Deposits: $" + totalDeposits);
        writer.println("");
        writer.println("Net Total: $" + totalNet);
        writer.close();

      } catch(Exception e){
        e.printStackTrace();
      }
    }

    private void openStatement() {
      if (Desktop.isDesktopSupported()) {
        try {
            File myFile = new File("../exports/" + reportFileName + ".txt");
            Desktop.getDesktop().open(myFile);
        } catch (Exception ex) {
            // no application registered for PDFs
            System.out.println("ERROR: Desktop not supported.");
        }
      }
    }
}
