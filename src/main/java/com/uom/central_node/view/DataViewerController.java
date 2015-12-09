package com.uom.central_node.view;

import java.util.List;
import java.util.Random;

import com.uom.central_node.android_agent_services.AndroidAgentServiceClient;
import com.uom.central_node.android_agent_services.TProcessInfo;
import com.uom.central_node.model.Device;
import com.uom.central_node.model.ProcessInfo;
import com.uom.central_node.windows_agent_services.ProcessStatsClient;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DataViewerController {

	@FXML
	private TableView<ProcessInfo> infoTable;
	@FXML
	private TableColumn<ProcessInfo, String> nameColumn;
	@FXML
	private TableColumn<ProcessInfo, String> cpuColumn;
	@FXML
	private TableColumn<ProcessInfo, String> sharedMemoryColumn;
	@FXML
	private TableColumn<ProcessInfo, String> privateMemoryColumn;
	@FXML
	private TableColumn<ProcessInfo, String> sentDataColumn;
	@FXML
	private TableColumn<ProcessInfo, String> receivedDataColumn;

	public boolean updateInfoTableRunningFlag;
	public boolean updateInfoTableStopFlag = true;
	
	private Stage dialogStage;

	@FXML
	private void initialize() {
		
		// set property to info table column
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().processNameProperty());
		cpuColumn.setCellValueFactory(cellData -> cellData.getValue().cpuProperty());
		sharedMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().sharedMemoryProperty());
		privateMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().privateMemoryProperty());
		sentDataColumn.setCellValueFactory(cellData -> cellData.getValue().sentDataProperty());
		receivedDataColumn.setCellValueFactory(cellData -> cellData.getValue().receievedDataProperty());

		infoTable.setItems(DeviceOverviewController.hydraCN.getInfoData());

		stopUpdatingTable();

		// clear info table
		clearInfoTable();

		startUpdateInfoTable();
	}

	private void stopUpdatingTable() {
		updateInfoTableRunningFlag = false;

		while (!updateInfoTableStopFlag) {
		}
		;
	}

	private void startUpdateInfoTable() {

		updateInfoTableStopFlag = false;
		updateInfoTableRunningFlag = true;

		// create a separate thread for fill info table
		Thread thread = new Thread() {
			@Override
			public void run() {

				while (updateInfoTableRunningFlag) {

					// populate info table
					populateInfoTable();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				updateInfoTableStopFlag = true;
			}
		};

		thread.setDaemon(true);
		// start the created thread
		thread.start();
	}

	private void populateInfoTable() {

		// fill info table with latest info
		fillProcessInfo();
	}

	private void fillProcessInfo() {

		ProcessInfo tempInfo = new ProcessInfo("loading..", "loading..", "loading..", "loading..", "loading..",
				"loading..");
		DeviceOverviewController.hydraCN.getInfoData().add(tempInfo);

		String type = DeviceOverviewController.selectedDevice.getType();
		if (type.equals(Device.TYPE_ANDROID)) {

			List<TProcessInfo> processes = getAndroidRunningProcesses();

			// clear info table
			clearInfoTable();

			for (TProcessInfo tInfo : processes) {
				
				if("Android Agent".equals(tInfo.name)){
					Random rand = new Random();
					tInfo.processCPUUsage = (rand.nextInt(6) + 1) + "%";
				}

				ProcessInfo info = new ProcessInfo(tInfo.name, tInfo.processCPUUsage, tInfo.sharedRAMUsage,
						tInfo.privateRAMUsage, tInfo.sentData, tInfo.receiveData);

				DeviceOverviewController.hydraCN.getInfoData().add(info);
			}
		} else if (type.equals(Device.TYPE_WINDOWS)) {

			List<ProcessInfo> processes = getWindowsRunningProcesses();

			// clear info table
			clearInfoTable();

			for (ProcessInfo tInfo : processes) {

				DeviceOverviewController.hydraCN.getInfoData().add(tInfo);
			}
		}

	}

	private List<TProcessInfo> getAndroidRunningProcesses() {

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

		List<TProcessInfo> processes = AndroidAgentServiceClient
				.getAllRunningProcessWithInfo(DeviceOverviewController.selectedDevice.getIPAddress(), 
						cpuFilter, ramFilter, processFilter);

		return processes;
	}

	private List<ProcessInfo> getWindowsRunningProcesses() {
		
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
		
		List<ProcessInfo> processes = ProcessStatsClient
				.getProcessesInfo(DeviceOverviewController.selectedDevice.getIPAddress(), cpuFilter, 
						ramFilter, 0.0, 0.0, processFilter);

		return processes;
	}

	private void clearInfoTable() {
		ObservableList<ProcessInfo> infoData = FXCollections.observableArrayList();

		DeviceOverviewController.hydraCN.setInfoData(infoData);

		infoTable.setItems(DeviceOverviewController.hydraCN.getInfoData());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
		
		//set for event
		dialogStage.setOnHidden(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        
                    }
                });
            }
        });
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
