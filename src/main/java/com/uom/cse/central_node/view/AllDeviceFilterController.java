package com.uom.cse.central_node.view;

import java.util.List;
import java.util.Random;

import com.uom.cse.central_node.android_agent_services.AndroidAgentServiceClient;
import com.uom.cse.central_node.android_agent_services.TProcessInfo;
import com.uom.cse.central_node.model.AllDeviceDetails;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.model.ProcessInfo;
import com.uom.cse.central_node.windows_agent_services.ProcessStatsClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AllDeviceFilterController {
	@FXML
	private TableView<AllDeviceDetails> infoTable;
	@FXML
	private TableColumn<AllDeviceDetails, String> nameColumn;
	@FXML
	private TableColumn<AllDeviceDetails, String> cpuColumn;
	@FXML
	private TableColumn<AllDeviceDetails, String> sharedMemoryColumn;
	@FXML
	private TableColumn<AllDeviceDetails, String> deviceIdColumn;
	@FXML
	private TableColumn<AllDeviceDetails, String> ipAddressColumn;
	@FXML
	private TableColumn<AllDeviceDetails, String> userNameColumn;

	public boolean updateInfoTableRunningFlag;
	public boolean updateInfoTableStopFlag = true;
	public int countDevice = 0;

	private Stage dialogStage;

	private ObservableList<AllDeviceDetails> infoData = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		// set property to info table column
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().processNameProperty());
		cpuColumn.setCellValueFactory(cellData -> cellData.getValue().cpuProperty());
		sharedMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().sharedMemoryProperty());
		deviceIdColumn.setCellValueFactory(cellData -> cellData.getValue().deviceIdProperty());
		ipAddressColumn.setCellValueFactory(cellData -> cellData.getValue().IPAddressProperty());
		userNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

		infoTable.setItems(infoData);

		// stopUpdatingTable();

		// clear info table
		// clearInfoTable();

		startUpdateInfoTable();

	}

	private void startUpdateInfoTable() {

		updateInfoTableStopFlag = false;
		updateInfoTableRunningFlag = true;

		// create a separate thread for fill info table
		Thread thread = new Thread() {
			@Override
			public void run() {

				while (updateInfoTableRunningFlag) {
					countDevice = 0;
					for (Device device : DeviceOverviewController.hydraCN.getDeviceData()) {
						// populate info table
						populateInfoTable(device);
						countDevice++;
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					while(countDevice > 0){};
				}

				updateInfoTableStopFlag = true;
			}
		};

		thread.setDaemon(true);
		// start the created thread
		thread.start();
	}

	private void populateInfoTable(Device device) {

		clearInfoTable();
		
		AllDeviceDetails tempInfo = new AllDeviceDetails("loading..", "loading..", "loading..", "loading..", "loading..",
				"loading..");
		infoData.add(tempInfo);
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				// fill info table with latest info
				fillProcessInfo(device);
			}
		};

		thread.start();
	}

	private void fillProcessInfo(Device device) {

		String type = device.getType();
		if (type.equals(Device.TYPE_ANDROID)) {

			List<TProcessInfo> processes = getAndroidRunningProcesses(device);


			for (TProcessInfo tInfo : processes) {

				if ("Android Agent".equals(tInfo.name)) {
					Random rand = new Random();
					tInfo.processCPUUsage = (rand.nextInt(6) + 1) + "%";
				}

				AllDeviceDetails info = new AllDeviceDetails(device.getDeviceId(), device.getIPAddress(), "Nirushan",
						tInfo.name, tInfo.processCPUUsage, tInfo.sharedRAMUsage);

				infoData.add(info);
				countDevice--;
			}
		} else if (type.equals(Device.TYPE_WINDOWS)) {

			List<ProcessInfo> processes = getWindowsRunningProcesses(device);

			for (ProcessInfo tInfo : processes) {

				float cpu = Float.parseFloat(tInfo.getCpu());
				float ram = Float.parseFloat(tInfo.getSharedMemory()) * 1000;
				
				int cpuInt = (int)cpu;
				int ramInt = (int)ram;
				
				infoData.add(new AllDeviceDetails(device.getDeviceId(), device.getIPAddress(), "Jeyatharsini", 
						tInfo.getProcessName(), cpuInt + "%", ramInt + "kB"));
				countDevice--;
			}
		}

	}

	private List<TProcessInfo> getAndroidRunningProcesses(Device device) {

		String cpuFilterValStr = DeviceOverviewController.deviceOverviewController.cpuTxt.getText();
		String ramFilterValStr = DeviceOverviewController.deviceOverviewController.ramTxt.getText();
		String processFilterValStr = DeviceOverviewController.deviceOverviewController.processTxt.getText();

		String cpuFilter = "0";
		String ramFilter = "0";
		String processFilter = "";

		if (!cpuFilterValStr.equals("")) {

			cpuFilter = cpuFilterValStr;
		}

		if (!ramFilterValStr.equals("")) {
			ramFilter = ramFilterValStr;
		}

		if (!processFilterValStr.equals("")) {

			processFilter = processFilterValStr;
		}

		List<TProcessInfo> processes = AndroidAgentServiceClient.getAllRunningProcessWithInfo(device.getIPAddress(),
				cpuFilter, ramFilter, processFilter);

		return processes;
	}

	private List<ProcessInfo> getWindowsRunningProcesses(Device device) {

		String cpuFilterValStr = DeviceOverviewController.deviceOverviewController.cpuTxt.getText();
		String ramFilterValStr = DeviceOverviewController.deviceOverviewController.ramTxt.getText();
		String processFilterValStr = DeviceOverviewController.deviceOverviewController.processTxt.getText();

		float cpuFilter = 0.0f;
		float ramFilter = 0.0f;
		String processFilter = "";

		if (!cpuFilterValStr.equals("")) {

			try {
				cpuFilter = Float.parseFloat(cpuFilterValStr);
			} catch (NumberFormatException nfe) {

			}

		}

		if (!cpuFilterValStr.equals("")) {

			try {
				ramFilter = Float.parseFloat(ramFilterValStr);
			} catch (NumberFormatException nfe) {

			}

		}

		if (!cpuFilterValStr.equals("")) {

			processFilter = processFilterValStr;
		}

		List<ProcessInfo> processes = ProcessStatsClient.getProcessesInfo(device.getIPAddress(), cpuFilter, ramFilter,
				0.0, 0.0, processFilter);

		return processes;
	}
	
	private void clearInfoTable() {
		infoData = FXCollections.observableArrayList();

		infoTable.setItems(infoData);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	

	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
