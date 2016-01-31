package com.uom.cse.central_node.view;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.androidagentservices.AndroidAgentServiceClient;
import com.uom.cse.central_node.androidagentservices.TProcessInfo;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.model.ProcessInfo;
import com.uom.cse.central_node.util.LogFileReader;
import com.uom.cse.central_node.windowsagentservices.ProcessStatsClient;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
	
	private ObservableList<ProcessInfo> infoData = FXCollections.observableArrayList();
	
	private Stage dialogStage;
	
	private Executor executor;

	@FXML
	private void initialize() {
		
		executor = Executors.newSingleThreadExecutor(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
		
		// set property to info table column
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().processNameProperty());
		cpuColumn.setCellValueFactory(cellData -> cellData.getValue().cpuProperty());
		sharedMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().sharedMemoryProperty());
		//privateMemoryColumn.setCellValueFactory(cellData -> cellData.getValue().privateMemoryProperty());
		sentDataColumn.setCellValueFactory(cellData -> cellData.getValue().sentDataProperty());
		receivedDataColumn.setCellValueFactory(cellData -> cellData.getValue().receievedDataProperty());

		infoTable.setItems(infoData);
		
		// populate info table
		populateInfoTable();
	}

	private void populateInfoTable() {
		Device device = DeviceOverviewController.selectedDevice;
		
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				while(true){
					
					
					List<ProcessInfo> processInfoList = LogFileReader.readProcessInfoData(device);
					
					if (isDataChanged(processInfoList)){
						infoData = FXCollections.observableArrayList();
						
						Platform.runLater(
								() -> infoTable.setItems(infoData));
						
						fillProcessInfo(processInfoList);
					}
					Thread.sleep(1000);
				}
				
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
		
		List<ProcessInfo> processInfoList = LogFileReader.readProcessInfoData(device);
		// fill info table with latest info
		fillProcessInfo(processInfoList);
	}

	private void fillProcessInfo(List<ProcessInfo> processInfoList) {

		for (ProcessInfo info : processInfoList) {
			infoData.add(info);
		}
	}
	
	private boolean isDataChanged (List<ProcessInfo> processInfoList) {
		boolean returnFlag = false;
		if (infoData.size() == processInfoList.size()) {
			for (int i = 0; i < processInfoList.size(); i++) {
				ProcessInfo currentData = processInfoList.get(i);
				ProcessInfo latestData = infoData.get(i);
				
				if (!currentData.getProcessName().equals(latestData.getProcessName()) || 
						!currentData.getCpu().equals(latestData.getCpu()) ||
						!currentData.getSharedMemory().equals(latestData.getSharedMemory()) ||
						!currentData.getSentData().equals(latestData.getSentData()) ||
						!currentData.getRecievedData().equals(latestData.getRecievedData())) {
					returnFlag = true;
				}
			}
		}else{
			returnFlag = true;
		}
		return returnFlag;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
}
