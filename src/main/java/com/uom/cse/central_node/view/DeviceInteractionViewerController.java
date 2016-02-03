package com.uom.cse.central_node.view;

import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.model.InteractionData;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class DeviceInteractionViewerController {
	@FXML
	public TableView<InteractionData> dataTable;
	@FXML
	private TableColumn<InteractionData, String> deviceNameColumn;
	@FXML
	private TableColumn<InteractionData, String> IPAddressColumn;
	@FXML
	private TableColumn<InteractionData, String> timeColumn;
	@FXML
	private TableColumn<InteractionData, String> actionColumn;

	private Stage dialogStage;
	
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		deviceNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		IPAddressColumn.setCellValueFactory(cellData -> cellData.getValue().ipAddressProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		actionColumn.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
		
		dataTable.setItems(DeviceOverviewController.hydraCN.getInterationData());
	}
	
	public void setDialogStage(Stage newFilterStage) {
		dialogStage = newFilterStage;
	}
	
	@FXML
	public void actionClose(){
		dialogStage.close();
	}
}
