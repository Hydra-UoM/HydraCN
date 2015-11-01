package com.uom.central_node.view;

import com.uom.central_node.HydraCN;
import com.uom.central_node.model.Device;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DeviceOverviewController {
	@FXML
    private TableView<Device> deviceTable;
	@FXML
    private TableColumn<Device, String> deviceIdColumn;
    @FXML
    private TableColumn<Device, String> IPAddressColumn;
    @FXML
    private TableColumn<Device, String> typeColumn;
    
    private HydraCN hydraCN;
    
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	deviceIdColumn.setCellValueFactory(cellData -> cellData.getValue().deviceIdProperty());
    	IPAddressColumn.setCellValueFactory(cellData -> cellData.getValue().IPAddressProperty());
    	typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
    }
    
    public void setMainApp(HydraCN hydraCN) {
        this.hydraCN = hydraCN;

        //Add observable list data to the table
        deviceTable.setItems(hydraCN.getDeviceData());
    }
}
