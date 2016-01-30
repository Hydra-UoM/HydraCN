package com.uom.cse.central_node.view;

import javafx.fxml.FXML;

public class RootLayoutController {
	@FXML
	private void showFilterList(){
		DeviceOverviewController.hydraCN.showFilterDetails();
	}
	
	@FXML
	private void showWindowsLogViewer(){
		DeviceOverviewController.hydraCN.showWindowsLogRuleViewer();
	}
	
	@FXML
	private void showCepRuleEditor(){
		DeviceOverviewController.hydraCN.showCEPRuleEditor();
	}
	
	@FXML
	private void showAbout(){
		DeviceOverviewController.hydraCN.showAbout();
	}
}
