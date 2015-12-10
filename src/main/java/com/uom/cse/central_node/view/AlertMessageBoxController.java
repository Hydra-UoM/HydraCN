package com.uom.cse.central_node.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertMessageBoxController {
	@FXML
	private Label lblMessage;
	
	private Stage dialogStage;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setMessage(String msg){
		lblMessage.setText(msg);
	}
	
	@FXML
	private void actionClose(){
		dialogStage.close();
	}
}
