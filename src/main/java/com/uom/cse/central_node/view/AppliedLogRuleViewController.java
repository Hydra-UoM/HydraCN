package com.uom.cse.central_node.view;

import com.uom.cse.central_node.model.WindowsLogData;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AppliedLogRuleViewController {
	@FXML
	private Label lblRuleName;
	@FXML
	private Label lblSummarizationLevel;
	@FXML
	private Label lblEvents;
	@FXML
	private Label lblTimeInteval;
	
	private Stage dialogStage;
	
	public void setStage(Stage stage){
		dialogStage = stage;
	}
	
	public void setLogData(WindowsLogData logData){
		lblRuleName.setText(logData.getFilterName());
		lblSummarizationLevel.setText(logData.getSummarizationLevel() + "");
		lblEvents.setText(logData.getLogType());
		lblTimeInteval.setText(logData.getTimeBound() + "min");
	}
	
	@FXML
	private void actionClose(){
		dialogStage.close();
	}
	
}
