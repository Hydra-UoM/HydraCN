package com.uom.cse.central_node.view;

import com.uom.cse.central_node.dataobjects.Filter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AppliedFilterViewController {
	@FXML
	private Label lblName;
	@FXML
	private Label lblCpu;
	@FXML
	private Label lblRam;
	@FXML
	private Label lblTimeBound;
	@FXML
	private Label lblSentData;
	@FXML
	private Label lblReceivedData;
	@FXML
	private Label lblMessage;
	
	private Stage dialogStage;
	
	public void setFilter(Filter filter){
		lblName.setText(filter.getFilterName());
		lblCpu.setText(filter.getCpuUsage() + "%");
		lblRam.setText(filter.getRamUsage() + "MB");
		lblTimeBound.setText(filter.getTimeBound() + "min");
		lblSentData.setText(filter.getSentData() + "MB");
		lblReceivedData.setText(filter.getReceivedData() + "MB");
		lblMessage.setText(filter.getMessage());
	}
	
	public void setStage(Stage stage){
		dialogStage = stage;
	}
	
	@FXML
	private void actionClose(){
		dialogStage.close();
	}
}
