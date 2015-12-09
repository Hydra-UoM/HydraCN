package com.uom.central_node.view;

import javafx.fxml.FXML;

public class RootLayoutController {
	@FXML
	private void showFilterList(){
		DeviceOverviewController.hydraCN.showFilterDetails();
	}
}
