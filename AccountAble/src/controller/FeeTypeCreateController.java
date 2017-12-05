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

public class FeeTypeCreateController {

  @FXML
  private ToggleGroup isPercent;

  @FXML
  private RadioButton isFlatFeeRadio;

  @FXML
  private TextField amtTxt;

  @FXML
  private RadioButton isPercentRadio;

  @FXML
  private Button cancelBtn;

  @FXML
  private TextField nameTxt;

  @FXML
  private Label titleLbl;

  @FXML
  private Button confirmBtn;

  @FXML
  private Label warningLbl;

  @FXML
  private TextArea descrTxt;

    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;

    Stage homeStage;
    AdminController homeCtrl;

    User loginUser;
    FeeType selectedFee;
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
      titleLbl.setText("Create New Fee Type");
      warningLbl.setVisible(false);
    }
    public void setupEdit(FeeType selectedFee){
      setMode("edit");
      titleLbl.setText("Edit Fee Type");
      warningLbl.setVisible(false);
      if (selectedFee != null){
        this.selectedFee = selectedFee;
        nameTxt.setText(selectedFee.getName());
        descrTxt.setText(selectedFee.getDescr());
        amtTxt.setText(selectedFee.getAmt().toString());
        if (selectedFee.getIsPercent()){
          isPercentRadio.setSelected(true);
        } else {
          isFlatFeeRadio.setSelected(true);
        }
      } else {
        System.out.println("Error: No Fee Type was selected.");
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
    }
    void confirmCreate(){
      String name = nameTxt.getText();
      String descr = descrTxt.getText();
      Double amt = Double.parseDouble(amtTxt.getText());
      Boolean isPercent = isPercentRadio.isSelected();
      Boolean isAdditional = false;
      Boolean isCustom = false;
      model.addNewFeeType(name, descr, amt, isPercent, isAdditional, isCustom);
      homeCtrl.refresh();
      thisStage.hide();
    }
    void confirmEdit(){
      int id = selectedFee.getID();
      String name = nameTxt.getText();
      String descr = descrTxt.getText();
      Double amt = Double.parseDouble(amtTxt.getText());
      Boolean isPercent = isPercentRadio.isSelected();
      Boolean isAdditional = false;
      Boolean isCustom = false;
      model.editFeeType(id, name, descr, amt, isPercent, isAdditional, isCustom);
      model.uaManager.getFeeTypeByID(id).printInfo();
      homeCtrl.refresh();
      thisStage.hide();
    }

    @FXML
    void cancelClick(ActionEvent event) {
      thisStage.hide();
    }

}
