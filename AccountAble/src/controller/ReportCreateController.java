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

public class ReportCreateController {

  @FXML
  private DatePicker endDatePicker;

  @FXML
  private Button cancelBtn;

  @FXML
  private TextField nameTxt;

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

    User loginUser;
    Account selectedAcct;
    ArrayList<Account> currentAccts;

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

    void setup(User loginUser, ArrayList<Account> currentAccts){
      this.loginUser = loginUser;
      this.currentAccts = currentAccts;
      this.selectedAcct = selectedAcct;

      titleLbl.setText("Create Transaction");

      popLists();
    }

    void popLists(){
      ObservableList<Account> acctsObservable = FXCollections.observableArrayList(currentAccts);
      acctChoiceBx.setItems(acctsObservable);
    }

    // ************************** Button Events ************************************

    @FXML
    void confirmClick(ActionEvent event) {

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


}
