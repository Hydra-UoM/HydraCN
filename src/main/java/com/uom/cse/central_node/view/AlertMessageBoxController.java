package com.uom.cse.central_node.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertMessageBoxController {
	@FXML
	private Label lblMessage;
	@FXML
	private Label lblTitle;
	
	private Stage dialogStage;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setMessage(String msg){
		lblMessage.setText(msg);
	}
	
	public void setTitle(String title){
		lblTitle.setText(title);
	}
	
	@FXML
	private void actionClose(){
		dialogStage.close();
	}
}
