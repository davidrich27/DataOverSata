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

public class CodeCreateController {

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
    Code selectedCode;
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
      titleLbl.setText("Create New Code");
      warningLbl.setVisible(false);
    }
    public void setupEdit(Code selectedCode){
      setMode("edit");
      titleLbl.setText("Edit Code");
      warningLbl.setVisible(false);
      if (selectedCode != null){
        this.selectedCode = selectedCode;
        nameTxt.setText(selectedCode.getName());
        descrTxt.setText(selectedCode.getDescr());
      } else {
        System.out.println("Error: No Code was selected.");
      }
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
      model.addNewCode(name, descr);
      System.out.println("New Account, " + name + " has been created!");
    }
    void confirmEdit(){
      int id = selectedCode.getID();
      // Change name and descr
      String name = nameTxt.getText();
      String descr = descrTxt.getText();
      model.editCode(id, name, descr);
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

}
