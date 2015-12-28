package com.uom.cse.central_node.view;

import com.uom.cse.central_node.model.CEPRuleData;
import com.uom.cse.central_node.model.WindowsLogData;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CEPRuleViewerController {
	@FXML
	public TableView<CEPRuleData> ruleTable;
	@FXML
	private TableColumn<CEPRuleData, String> name;
	@FXML
	private TableColumn<CEPRuleData, String> rule;
	@FXML
	private TableColumn<CEPRuleData, String> status;
	@FXML
	private TableColumn<CEPRuleData, String> alertMessage;
	@FXML
	private Button btnApplyRule;
	@FXML
	private Button btnDisableRule;
	@FXML
	private Button btnDisableAllRules;
	
	private CEPRuleData selectedRule;
	
	private Stage dialogStage;
	
	@FXML
	private void initialize() {
		name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		rule.setCellValueFactory(cellData -> cellData.getValue().ruleProperty());
		status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		alertMessage.setCellValueFactory(cellData -> cellData.getValue().alertMessageProperty());
		
		// add listener for change in table
		ruleTable.getSelectionModel().selectedItemProperty()
						.addListener((observable, oldValue, newValue) -> ruleChanged(newValue));
		
		ruleTable.setItems(DeviceOverviewController.hydraCN.getCEPRuleData());
	}
	
	private void ruleChanged(CEPRuleData newValue) {
		btnApplyRule.setDisable(false);
		selectedRule = newValue;
	}
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void actionClose(){
		dialogStage.close();
	}
}
