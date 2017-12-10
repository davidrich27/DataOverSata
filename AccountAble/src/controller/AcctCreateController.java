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

public class AcctCreateController {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextArea descrTxt;

    @FXML
    private Label warningLbl;

    @FXML
    private Label titleLbl;


    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;

    Stage homeStage;
    AdminController homeCtrl;

    User loginUser;
    Account selectedAcct;
    // Determines whether in create, edit, view
    String mode;

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
    public void setMode(String mode){
      this.mode = mode;
    }

    public void setupCreate(){
      setMode("create");
      titleLbl.setText("Create New Account");
      warningLbl.setVisible(false);
    }
    public void setupEdit(Account selectedAcct){
      this.selectedAcct = selectedAcct;
      setMode("edit");
      titleLbl.setText("Edit Account");
      warningLbl.setVisible(false);
      populateFields();
    }
    public void setupExam(Account selectedAcct){
      this.selectedAcct = selectedAcct;
      setMode("exam");
      titleLbl.setText("Examine Account");
      populateFields();
      setUneditable();
    }

    void populateFields(){
      if (selectedAcct != null){
        this.selectedAcct = selectedAcct;
        nameTxt.setText(selectedAcct.getName());
        descrTxt.setText(selectedAcct.getDescr());
      } else {
        System.out.println("Error: No Account was selected.");
      }
    }
    void setUneditable(){
      nameTxt.setDisable(true);
      descrTxt.setDisable(true);
      confirmBtn.setVisible(false);
      cancelBtn.setText("Close");
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
      String name = nameTxt.getText();
      String descr = descrTxt.getText();
      model.addNewAcct(name, descr);
    }
    void confirmEdit(){
      int id = selectedAcct.getID();
      // Change name and descr
      String name = nameTxt.getText();
      String descr = descrTxt.getText();
      model.editAccount(id, name, descr);
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

}
