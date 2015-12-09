package com.uom.cse.central_node.view;

import javafx.fxml.FXML;

public class RootLayoutController {
	@FXML
	private void showFilterList(){
		DeviceOverviewController.hydraCN.showFilterDetails();
	}
}
