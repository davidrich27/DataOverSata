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

public class AcctEditController {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextArea descrTxt;


    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;
    Stage homeStage;
    AdminController homeCtrl;

    User loginUser;
    Account selectedAcct;

    // ************************** Initialization and Wireup *********************

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
    public void populate(Account selectedAcct){
      if (selectedAcct != null){
        this.selectedAcct = selectedAcct;
        nameTxt.setText(selectedAcct.getName());
        descrTxt.setText(selectedAcct.getDescr());
      } else {
        System.out.println("Error: No Account was selected.");
      }
    }

    // ************************** Other Events ************************************

    @FXML
    void confirmClick(ActionEvent event) {
      System.out.println("Account Edit clicked!");
      // Change selected Account's name and descr
      String name = nameTxt.getText();
      String descr = descrTxt.getText();
      selectedAcct.setName(name);
      selectedAcct.setDescr(descr);
      homeCtrl.refresh();
      thisStage.hide();
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

}
