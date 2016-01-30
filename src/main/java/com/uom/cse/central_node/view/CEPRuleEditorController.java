package com.uom.cse.central_node.view;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.espertech.esper.client.EPStatementException;
import com.uom.cse.central_node.data_objects.LogRule;
import com.uom.cse.central_node.data_objects.LogRuleTable;
import com.uom.cse.central_node.model.WindowsLogData;
import com.uom.cse.central_node.util.EventFeeder;
import com.uom.cse.central_node.windows_agent_services.ProcessStatsClient;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CEPRuleEditorController {
	@FXML
	private Button btnApply;
	@FXML
	private TextField txtRuleName;
	@FXML
	private TextArea txtRule;
	@FXML
	private TextField txtAlertMsg;
	@FXML
	private Label lblErrorMsg;

	private Stage dialogStage;

	@FXML
	private void initialize() {

		lblErrorMsg.setText("");
	}

	@FXML
	private void actionClose() {
		dialogStage.close();
	}

	@FXML
	private void actionApplyRule(){
		try{
			EventFeeder.deployCEPRule(txtRule.getText(), txtAlertMsg.getText());
			dialogStage.close();
		}catch(EPStatementException e){
			System.out.println(e.getMessage());
			//need to put an alert
		}
		
	}

	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
}
