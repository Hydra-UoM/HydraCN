package com.uom.cse.central_node.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.model.AllDeviceDetails;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.util.LogFileReader;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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

	private Executor executor;

	private Stage dialogStage;
	
	private List<Device> currentlyConnectedDevices;

	private ObservableList<AllDeviceDetails> infoData = FXCollections.observableArrayList();
	private DeviceOverviewController deviceOverviewController;

	@FXML
	private void initialize() {
		
		currentlyConnectedDevices = DeviceOverviewController.deviceOverviewController.deviceTable
				.getItems();
		
		executor = Executors.newSingleThreadExecutor(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
		
		// set property to info table column
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().processNameProperty());
		cpuColumn.setCellValueFactory(cellData -> cellData.getValue().cpuProperty());
		sharedMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().sharedMemoryProperty());
		deviceIdColumn.setCellValueFactory(cellData -> cellData.getValue().deviceIdProperty());
		ipAddressColumn.setCellValueFactory(cellData -> cellData.getValue().IPAddressProperty());
		userNameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

		infoTable.setItems(infoData);
		
		populateInfoTable();

	}
	
	private void populateInfoTable() {
		
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				while(true){
					
					
					List<AllDeviceDetails> processInfoList = LogFileReader.readProcessInfoData(currentlyConnectedDevices);
					
					if (isDataChanged(processInfoList)){
						infoData = FXCollections.observableArrayList();
						
						Platform.runLater(
								() -> infoTable.setItems(infoData));
						
						fillProcessInfo(filterDeviceDetails(processInfoList));
					}
					Thread.sleep(1000);
				}
				
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
		
		List<AllDeviceDetails> processInfoList = LogFileReader.readProcessInfoData(currentlyConnectedDevices);
		// fill info table with latest info
		fillProcessInfo(filterDeviceDetails(processInfoList));
	}
	
	private void fillProcessInfo(List<AllDeviceDetails> processInfoList) {

		for (AllDeviceDetails info : processInfoList) {
			infoData.add(info);
		}
	}
	
	private boolean isDataChanged (List<AllDeviceDetails> processInfoList) {
		boolean returnFlag = false;
		if (infoData.size() == processInfoList.size()) {
			for (int i = 0; i < processInfoList.size(); i++) {
				AllDeviceDetails currentData = processInfoList.get(i);
				AllDeviceDetails latestData = infoData.get(i);
				
				if (!currentData.getProcessName().equals(latestData.getProcessName()) || 
						!currentData.getCpu().equals(latestData.getCpu()) ||
						!currentData.getSharedMemory().equals(latestData.getSharedMemory())) {
					returnFlag = true;
				}
			}
		}else{
			returnFlag = true;
		}
		return returnFlag;
	}
	
	private List<AllDeviceDetails> filterDeviceDetails (List<AllDeviceDetails> detailList) {
		List<AllDeviceDetails> cpuFilterList = new ArrayList<AllDeviceDetails>();
//		
//		for (AllDeviceDetails detail : detailList) {
//			returnList.add(detail);
//		}
//		
		if (DeviceOverviewController.isCpuChecked && !DeviceOverviewController.condCpu.equals("")) {
			for (AllDeviceDetails detail : detailList) {
				String cpu = detail.getCpu();
				try{
					
					double cpuInDouble = Double.parseDouble(cpu);
					double condCpuInDouble = Double.parseDouble(DeviceOverviewController.condCpu);
					if (cpuInDouble >= condCpuInDouble) {
						cpuFilterList.add(detail);
					}
				}catch(NumberFormatException e){
					
				}
			}
		}else{
			for (AllDeviceDetails detail : detailList) {
				cpuFilterList.add(detail);
			}
		}
		
		List<AllDeviceDetails> ramFilterList = new ArrayList<AllDeviceDetails>();
		if (deviceOverviewController.isRamChecked && !deviceOverviewController.condRam.equals("")) {
			for (AllDeviceDetails detail : cpuFilterList) {
				String ram = detail.getSharedMemory();
				try{
					
					double ramInDouble = Double.parseDouble(ram);
					double condRamInDouble = Double.parseDouble(deviceOverviewController.condRam);
					if (ramInDouble >= condRamInDouble) {
						ramFilterList.add(detail);
					}
				}catch(NumberFormatException e){
					
				}
			}
		}else{
			for (AllDeviceDetails detail : cpuFilterList) {
				ramFilterList.add(detail);
			}
		}
		
		List<AllDeviceDetails> processFilterList = new ArrayList<AllDeviceDetails>();
		if (deviceOverviewController.isProcessChecked && !deviceOverviewController.condProcess.equals("")) {
			for (AllDeviceDetails detail : ramFilterList) {
				String processName = detail.getProcessName().trim();
				String condProcessName = deviceOverviewController.condProcess.trim();
				if (!detail.getProcessName().toLowerCase().equals(condProcessName.toLowerCase())) {
					processFilterList.add(detail);
				}
			}
		}else{
			for (AllDeviceDetails detail : ramFilterList) {
				processFilterList.add(detail);
			}
		}
		
		return processFilterList;
	}

	public void setDialogStage(Stage dialogStage, DeviceOverviewController deviceOverviewController) {
		this.deviceOverviewController = deviceOverviewController;
		this.dialogStage = dialogStage;
	}
	

	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
