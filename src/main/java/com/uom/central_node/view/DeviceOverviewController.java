package com.uom.central_node.view;

import com.uom.central_node.HydraCN;
import com.uom.central_node.android_agent_services.AndroidAgentServiceClient;
import com.uom.central_node.android_agent_services.DeviceOverallInfo;
import com.uom.central_node.model.Device;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
	@FXML
	private Label batteryLifeLabel;
	@FXML
	private Label ramUsedLabel;
	@FXML
	private Label ramFreeLabel;
	@FXML
	private Label cpuLabel;

	private HydraCN hydraCN;

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		deviceIdColumn.setCellValueFactory(cellData -> cellData.getValue().deviceIdProperty());
		IPAddressColumn.setCellValueFactory(cellData -> cellData.getValue().IPAddressProperty());
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

		showDeviceBasicInfo(null);

		deviceTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDeviceBasicInfo(newValue));
	}

	public void showDeviceBasicInfo(Device device) {

		if (device != null) {
			cpuLabel.setText("loading..");
			ramFreeLabel.setText("loading..");
			ramUsedLabel.setText("loading..");
			batteryLifeLabel.setText("loading..");
			
			Thread thread = new Thread() {
				@Override
				public void run() {

					while (true) {
						DeviceOverallInfo overallInfo = AndroidAgentServiceClient
								.getDeviceOverallInfo(device.getIPAddress());

						Task task = new Task<Void>() {
							@Override
							public Void call(){
								Platform.runLater(new Runnable() {
									@Override
									public void run() {

										cpuLabel.setText(overallInfo.cpuUsage);
										ramFreeLabel.setText(overallInfo.ramFreeMemory);
										ramUsedLabel.setText(overallInfo.ramUsedMemory);
										batteryLifeLabel.setText(overallInfo.battery);
									}
								});
								return null;
							}

						};

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Thread th = new Thread(task);
						th.setDaemon(true);
						th.start();
					}
				}
			};
			thread.start();
		} else {
			cpuLabel.setText("");
			ramFreeLabel.setText("");
			ramUsedLabel.setText("");
			batteryLifeLabel.setText("");
		}
	}

	public void setMainApp(HydraCN hydraCN) {
		this.hydraCN = hydraCN;

		// Add observable list data to the table
		deviceTable.setItems(hydraCN.getDeviceData());
	}
}
