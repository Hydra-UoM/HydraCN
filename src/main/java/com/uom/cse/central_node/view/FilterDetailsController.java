package com.uom.cse.central_node.view;

import java.util.List;

import com.uom.cse.central_node.data_objects.Filter;
import com.uom.cse.central_node.data_objects.FilterTable;
import com.uom.cse.central_node.model.FilterData;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class FilterDetailsController {
	@FXML
	public TableView<FilterData> filterTable;
	@FXML
	private TableColumn<FilterData, String> filterName;
	@FXML
	private TableColumn<FilterData, String> cpu;
	@FXML
	private TableColumn<FilterData, String> ram;
	@FXML
	private TableColumn<FilterData, String> network;
	@FXML
	private TableColumn<FilterData, String> timeBound;
	@FXML
	private TableColumn<FilterData, String> process;
	@FXML
	private TableColumn<FilterData, String> eventId;
	@FXML
	private TableColumn<FilterData, String> message;
	
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		filterName.setCellValueFactory(cellData -> cellData.getValue().filterNameProperty());
		cpu.setCellValueFactory(cellData -> cellData.getValue().cpuProperty());
		ram.setCellValueFactory(cellData -> cellData.getValue().timeBoundProperty());
		network.setCellValueFactory(cellData -> cellData.getValue().ramProperty());
		timeBound.setCellValueFactory(cellData -> cellData.getValue().timeBoundProperty());
		process.setCellValueFactory(cellData -> cellData.getValue().processesProperty());
		eventId.setCellValueFactory(cellData -> cellData.getValue().eventIdProperty());
		message.setCellValueFactory(cellData -> cellData.getValue().messageProperty());
		
		// Add observable list data to the table
		populateFilterDataObservableArrayList();
		
		filterTable.setItems(DeviceOverviewController.hydraCN.getFilterData());
	}
	
	private void populateFilterDataObservableArrayList(){
		List<Filter> filterList = FilterTable.getAllFilters();
		filterList.forEach((filter)->DeviceOverviewController.hydraCN.getFilterData().add(new FilterData(filter)));
	}
}
