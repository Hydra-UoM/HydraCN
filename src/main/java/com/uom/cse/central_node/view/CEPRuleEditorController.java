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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
		txtRule.setWrapText(true);

	}

	@FXML
	private void actionClose() {
		dialogStage.close();
	}

	@FXML
	private void actionApplyRule(){
		try{
			EventFeeder.deployCEPRule(txtRule.getText(), txtAlertMsg.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("HYDRA SUCCESS MESSAGE");
			alert.setHeaderText(null);
			alert.setContentText("Your CEP rule deployed successfully!!");
			
			// Add a custom icon.
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("icon.png"));
			
			alert.showAndWait();
			
			dialogStage.close();
		}catch(EPStatementException e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("HYDRA ERROR MESSAGE");
			alert.setHeaderText("CEP Rule Error!!");
			alert.setContentText(e.getMessage());

			// Add a custom icon.
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("icon.png"));
			
			alert.showAndWait();
		}
		
	}

	public void setDialogStage(Stage stage) {
		dialogStage = stage;
	}
}
