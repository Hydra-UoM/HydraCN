package com.uom.cse.central_node.view;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.data_objects.LogRule;
import com.uom.cse.central_node.data_objects.LogRuleTable;
import com.uom.cse.central_node.model.WindowsLogData;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class WindowsLogRulesViewerController {
	@FXML
	public TableView<WindowsLogData> logRuleTable;
	@FXML
	private TableColumn<WindowsLogData, String> ruleName;
	@FXML
	private TableColumn<WindowsLogData, String> summarizationLevel;
	@FXML
	private TableColumn<WindowsLogData, String> logType;
	@FXML
	private TableColumn<WindowsLogData, String> timeInterval;
	@FXML
	private Button btnApply;

	private Stage dialogStage;

	// executer for creating a thread pool
	private Executor exec;

	@FXML
	private void initialize() {

		// create executor that uses daemon threads:
		exec = Executors.newCachedThreadPool(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});

		ruleName.setCellValueFactory(cellData -> cellData.getValue().filterNameProperty());
		timeInterval.setCellValueFactory(cellData -> cellData.getValue().timeBoundProperty());
		logType.setCellValueFactory(cellData -> cellData.getValue().logTypeProperty());
		summarizationLevel.setCellValueFactory(cellData -> cellData.getValue().summarizationLevelProperty());

		// add listener for change in table
		logRuleTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> ruleChanged(newValue));

		// Add observable list data to the table
		if (DeviceOverviewController.hydraCN.getWindowsLogData().isEmpty()) {
			populateWindowsLogDataObservableArrayList();
		}
		
		logRuleTable.setItems(DeviceOverviewController.hydraCN.getWindowsLogData());

	}

	private void populateWindowsLogDataObservableArrayList() {

		Task<List<LogRule>> logDataTask = new Task<List<LogRule>>() {
			@Override
			public List<LogRule> call() throws Exception {
				return LogRuleTable.getAllLogRules();
			}
		};

		logDataTask.setOnSucceeded((e) -> {
			List<LogRule> windowsLogList = logDataTask.getValue();
			windowsLogList
					.forEach((rule) -> DeviceOverviewController.hydraCN.getWindowsLogData().add(new WindowsLogData(rule)));
		});

		// run the task using a thread from the thread pool:
		exec.execute(logDataTask);
	}

	private void ruleChanged(WindowsLogData newValue) {

	}

	@FXML
	private void actionClose() {
		dialogStage.close();
	}

	@FXML
	private void actionShowNewForm() {
		DeviceOverviewController.hydraCN.showWindowsLogRuleCreateForm();
	}

	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
}
