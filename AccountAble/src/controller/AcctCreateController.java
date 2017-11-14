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


    // ************************** Model Variables *******************************

    ModelTXT model;
    Stage thisStage;
    Stage homeStage;

    User loginUser;

    // ************************** Initialization and Wireup *********************

    public void setModel(ModelTXT model){
      this.model = model;
    }
    public void setStage(Stage thisStage){
      this.thisStage = thisStage;
    }
    public void setHomeStage(Stage homeStage){
      this.homeStage = homeStage;
    }
    public void populate(User loginUser){
    }

    // ************************** Other Events ************************************

    @FXML
    void confirmClick(ActionEvent event) {

    }

    @FXML
    void cancelClick(ActionEvent event) {

    }

}
