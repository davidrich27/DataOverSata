package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.basic.BCmodel;
import javafx.scene.control.Button;

public class BCcontroller {
	
	@FXML
	private Label result, leftOver, decValue;
	private String operator = "";
	private boolean start = true;
	
	//When a number is clicked start is set to false.
	@FXML
	public void processNumbers(ActionEvent event) {
		if (start) {
			result.setText("");
			start = false;
			leftOver.setText("");
		}
		//Get value of number
		String value = ((Button)event.getSource()).getText();
		//set result to equal value. 
		result.setText(result.getText()+ value);

		
	}
	
	//When a percentage is pressed use it to calculate
	@FXML
	public void processOperators(ActionEvent event) {
		String value = ((Button)event.getSource()).getText();
		
		//make the operator the percentage value.
		operator = value;
		
		//make num2 equal to result and send the operator to BCmodel
		double num2 = Long.parseLong(result.getText());
		double output = BCmodel.calculate(num2, operator);
		
		//Make num3 equal to result and send the operator to BCmodel
		double num3 = Long.parseLong(result.getText());
		double leOver = BCmodel.payCalc(num3, operator);
		
		
		//Return leOver to view
		leftOver.setText("$ " + String.valueOf(leOver));	
		//The Returned value after calculations
		result.setText("$ " + String.valueOf(output));

		//Deletes operator so a new calculation can happen.
		operator = "";
		start = true;
			
			
		}

	public void setStage(Stage newStage) {
		// TODO Auto-generated method stub
		
	}
		
	}

