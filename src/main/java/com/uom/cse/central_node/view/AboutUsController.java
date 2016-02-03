package com.uom.cse.central_node.view;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AboutUsController {
	private Stage dialogStage;
	public void setDialogStage(Stage newFilterStage) {
		dialogStage = newFilterStage;
	}
	
	@FXML
	public void actionClose(){
		dialogStage.close();
	}
	
}
