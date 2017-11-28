package controller;

import model.basic.*;
import model.manager.*;
import model.master.*;

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
    private TextField codeTxt;

    @FXML
    private Label prevAcctBalLbl;

    @FXML
    private Label newAcctBalLbl;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label transSubTotalLbl;

    @FXML
    private Button confirmBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private RadioButton depositChkBx;

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
    private ListView<?> feeOutList;

    @FXML
    private RadioButton expenseChkbx;

    @FXML
    private ChoiceBox<?> acctSelect;

    @FXML
    private Label transTotalLbl;

    @FXML
    private ListView<?> feeInList;

    @FXML
    private TextArea descrTxt;

    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;
    Stage homeStage;
    AdminController homeCtrl;

    User loginUser;

    // ************************** Initialization and Wireup *********************

    public void initialize() {
      System.out.println("Running initialization of TransCreateController...");
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
    public void populate(User loginUser){
      this.loginUser = loginUser;
    }

    // ************************** Other Events ************************************

    @FXML
    void confirmClick(ActionEvent event) {
      System.out.println("Clicked Confirm!");
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

    @FXML
    void feeOutClick(ActionEvent event) {

    }

    @FXML
    void feeInClick(ActionEvent event) {

    }

    @FXML
    void recalcClick(ActionEvent event) {

    }
}
