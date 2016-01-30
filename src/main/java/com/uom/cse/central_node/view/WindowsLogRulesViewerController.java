package com.uom.cse.central_node.view;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.data_objects.LogRule;
import com.uom.cse.central_node.data_objects.LogRuleTable;
import com.uom.cse.central_node.model.WindowsLogData;
import com.uom.cse.central_node.windows_agent_services.ProcessStatsClient;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
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
	@FXML
	private Button btnShowAppliedRule;
	@FXML
	private Button btnRemoveAppliedRule;

	private Stage dialogStage;
	
	private WindowsLogData selectedRule;

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
		
		Task<LogRule> filterTask = new Task<LogRule>() {
            @Override
            public LogRule call() throws Exception {
                return LogRuleTable.getAppliedRule();
            }
        };
        
        filterTask.setOnSucceeded((e) ->{
        	LogRule rule = filterTask.getValue();
        	if(rule != null){
    			btnShowAppliedRule.setDisable(false);
    		}
        });
        
        // run the task using a thread from the thread pool:
        exec.execute(filterTask);

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
		btnApply.setDisable(false);
		selectedRule = newValue;
	}

	@FXML
	private void actionClose() {
		dialogStage.close();
	}

	@FXML
	private void actionShowNewForm() {
		DeviceOverviewController.hydraCN.showWindowsLogRuleCreateForm();
	}
	
	@FXML
	private void actionApplyRule(){
		Task<List<LogRule>> logDataTask = new Task<List<LogRule>>() {
			@Override
			public List<LogRule> call() throws Exception {
				LogRuleTable.applyRule(selectedRule.getId());
				return null;
			}
		};

		logDataTask.setOnSucceeded((e) -> {
			DeviceOverviewController.hydraCN.getDeviceData().forEach((device) -> {
				ProcessStatsClient.sendWindowsLogInfo(device.getIPAddress(), new LogRule(selectedRule));
			});
		});

		// run the task using a thread from the thread pool:
		exec.execute(logDataTask);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("HYDRA SUCCESS MESSAGE");
		alert.setHeaderText(null);
		alert.setContentText("Your Windows log rule applied successfully!!");

		// Add a custom icon.
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("icon.png"));
		
		alert.showAndWait();
		
		btnShowAppliedRule.setDisable(false);
		
	}
	
	@FXML
	private void actionRemoveAppliedRule(){
		LogRuleTable.disableAllRule();
		btnShowAppliedRule.setDisable(true);
	}
	
	@FXML
	private void actionShowAppiledRule(){
		
		DeviceOverviewController.hydraCN.showAppliedRuleView(new WindowsLogData(LogRuleTable.getAppliedRule()));
	}

	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
}
