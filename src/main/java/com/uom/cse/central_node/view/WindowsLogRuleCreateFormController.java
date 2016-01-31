package com.uom.cse.central_node.view;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.dataobjects.LogRule;
import com.uom.cse.central_node.dataobjects.LogRuleTable;
import com.uom.cse.central_node.model.WindowsLogData;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WindowsLogRuleCreateFormController {
	@FXML
	private TextField txtRuleName;
	@FXML
	private ComboBox<String> comboSummarizationLevel;
	@FXML
	private CheckBox chkLogonFailures;
	@FXML
	private CheckBox chkSuccessLogins;
	@FXML
	private CheckBox chkFirewallEvents;
	@FXML
	private CheckBox chkAccountUsage;
	@FXML
	private CheckBox chkGroupPolicyEditors;
	@FXML
	private CheckBox chkWindowsDefender;
	@FXML
	private CheckBox chkMobileDevice;
	@FXML
	private CheckBox chkPrintingServices;
	@FXML
	private CheckBox chkSystemFailures;
	@FXML
	private CheckBox chkClearingEventLogs;
	@FXML
	private CheckBox chkUpdateErrors;
	@FXML
	private CheckBox chkApplicationCrashes;
	@FXML
	private CheckBox chkSoftwareInstallations;
	@FXML
	private CheckBox chkRemoteLogons;
	@FXML
	private CheckBox chkUserInformation;
	@FXML
	private CheckBox chkProcessLogs;
	@FXML
	private TextField txtProcessName;
	@FXML
	private ComboBox<String> comboSecurityLevel;
	@FXML
	private ComboBox<String> comboType;
	@FXML
	private Slider sldTimeInterval;
	@FXML
	private Label lblTimeInterval;
	
	private Stage dialogStage;

	// executer for creating a thread pool
	private Executor exec;
	
	@FXML
	private void initialize() {
		sldTimeInterval.valueChangingProperty().addListener((observable, oldValue, newValue) -> lblTimeInterval
				.setText((int) (sldTimeInterval.valueProperty().get()) + " min"));
		
		comboSummarizationLevel.getItems().addAll("Low", "Medium", "High");
		
		comboSecurityLevel.getItems().addAll("Information", "Warning", "Error", "Critical");
		
		comboType.getItems().addAll("Application", "System", "Security", "Operational", "Setup");
		
		// create executor that uses daemon threads:
		exec = Executors.newCachedThreadPool(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
	}
	
	@FXML
	private void showRuleCreateForm() {
		DeviceOverviewController.hydraCN.showFilterCreateForm();
	}
	
	@FXML
	private void actionChangeProcessLogs(){
		if(chkProcessLogs.isSelected()){
			txtProcessName.setDisable(false);
			comboSecurityLevel.setDisable(false);
			comboType.setDisable(false);
		}else{
			txtProcessName.setDisable(true);
			comboSecurityLevel.setDisable(true);
			comboType.setDisable(true);
		}
	}
	
	@FXML
	private void actionSaveRule(){
		LogRule rule = new LogRule();
		
		rule.setFilterName(txtRuleName.getText());
		
		String summarizationLevel = comboSummarizationLevel.getValue();
		if("Low".equals(summarizationLevel)){
			rule.setSummarizationLevel(0);
		}else if("Medium".equals(summarizationLevel)){
			rule.setSummarizationLevel(1);
		}else if("High".equals(summarizationLevel)){
			rule.setSummarizationLevel(2);
		}else{
			rule.setSummarizationLevel(-1);
		}
		
		String logType = "";
		if(chkAccountUsage.isSelected()){
			logType += "28,";
		}
		if(chkApplicationCrashes.isSelected()){
			logType += "36,";
		}
		if(chkClearingEventLogs.isSelected()){
			logType += "34,";
		}
		if(chkFirewallEvents.isSelected()){
			logType += "27,";
		}
		if(chkGroupPolicyEditors.isSelected()){
			logType += "29,";
		}
		if(chkLogonFailures.isSelected()){
			logType += "17,";
		}
		if(chkMobileDevice.isSelected()){
			logType += "31,";
		}
		if(chkPrintingServices.isSelected()){
			logType += "32,";
		}
		if(chkRemoteLogons.isSelected()){
			logType += "38,";
		}
		if(chkSoftwareInstallations.isSelected()){
			logType += "37,";
		}
		if(chkSuccessLogins.isSelected()){
			logType += "25,";
		}
		if(chkSystemFailures.isSelected()){
			logType += "33,";
		}
		if(chkUpdateErrors.isSelected()){
			logType += "35,";
		}
		if(chkUserInformation.isSelected()){
			logType += "22, 23,";
		}
		if(chkWindowsDefender.isSelected()){
			logType += "30,";
		}
		
		if(chkProcessLogs.isSelected()){
			rule.setProcessLogEnable(true);
			rule.setProcessName(txtProcessName.getText());
			
			if(comboType.getValue() != null){
				rule.setType(comboType.getValue());
			}else{
				rule.setType("");
			}
			
			if(comboSecurityLevel.getValue() != null){
				rule.setSecurityLevel(comboSecurityLevel.getValue());
			}else{
				rule.setSecurityLevel("");
			}
			
			logType += "18,";
		}
		rule.setLogType(logType);
		
		int timeBound = (int)(sldTimeInterval.valueProperty().get());
		rule.setTimeBound(timeBound + "");
		
		Task<List<LogRule>> insertLogRuleTask = new Task<List<LogRule>>() {
			@Override
			public List<LogRule> call() throws Exception {
				rule.setId(LogRuleTable.insertRule(rule));
				return null;
			}
		};

		insertLogRuleTask.setOnSucceeded((e) -> {
			DeviceOverviewController.hydraCN.getWindowsLogData().add(new WindowsLogData(rule));
		});

		// run the task using a thread from the thread pool:
		exec.execute(insertLogRuleTask);
		
		dialogStage.close();
	}

	public void setDialogStage(Stage newFilterStage) {
		this.dialogStage = newFilterStage;
	}
	
	@FXML
	private void actionClose(){
		dialogStage.close();
	}
}
