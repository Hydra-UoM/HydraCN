package com.uom.cse.central_node.view;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.dataobjects.FilterTable;
import com.uom.cse.central_node.model.FilterData;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FilterCreateFormController {
	@FXML
	private TextField txtFilterName;
	@FXML
	private Slider sldCpuUsage;
	@FXML
	private Slider sldRamUsage;
	@FXML
	private Slider sldSentData;
	@FXML
	private Slider sldReceivedData;
	@FXML
	private Slider sldTimeBound;
	@FXML
	private TextField txtProcessesName;
	@FXML
	private TextArea txtMessage;
	@FXML
	private Label lblCpuUsage;
	@FXML
	private Label lblRamUsage;
	@FXML
	private Label lblSentData;
	@FXML
	private Label lblReceivedData;
	@FXML
	private Label lblTimeBound;

	private Stage dialogStage;

	// executer for creating a thread pool
	private Executor exec;

	@FXML
	private void initialize() {
		sldCpuUsage.valueChangingProperty().addListener((observable, oldValue, newValue) -> lblCpuUsage
				.setText((int) (sldCpuUsage.valueProperty().get()) + "%"));

		sldRamUsage.valueChangingProperty().addListener((observable, oldValue, newValue) -> lblRamUsage
				.setText((int) (sldRamUsage.valueProperty().get() * 40.96) + "MB"));

		sldSentData.valueChangingProperty().addListener((observable, oldValue, newValue) -> lblSentData
				.setText((int) (sldSentData.valueProperty().get() * 40.96) + "MB"));

		sldReceivedData.valueChangingProperty().addListener((observable, oldValue, newValue) -> lblReceivedData
				.setText((int) (sldReceivedData.valueProperty().get() * 40.96) + "MB"));

		sldTimeBound.valueChangingProperty().addListener((observable, oldValue, newValue) -> lblTimeBound
				.setText((int) (sldTimeBound.valueProperty().get()) + " min"));

		// create executor that uses daemon threads:
		exec = Executors.newCachedThreadPool(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void actionSaveFilter() {

		Filter newFilter = new Filter();

		newFilter.setFilterName(txtFilterName.getText());
		newFilter.setCpuUsage((int) (sldCpuUsage.valueProperty().get()));
		newFilter.setRamUsage((int) (sldRamUsage.valueProperty().get() * 40.96));
		newFilter.setSentData((int) (sldSentData.valueProperty().get() * 40.96));
		newFilter.setReceivedData((int) (sldReceivedData.valueProperty().get() * 40.96));
		newFilter.setTimeBound((int) (sldTimeBound.valueProperty().get()));
		newFilter.setMessage(txtMessage.getText());
		newFilter.setProcesses(txtProcessesName.getText());

		Task<List<Filter>> insertFilterTask = new Task<List<Filter>>() {
			@Override
			public List<Filter> call() throws Exception {
				newFilter.setId(FilterTable.insertFilter(newFilter));
				return null;
			}
		};

		insertFilterTask.setOnSucceeded((e) -> {
			DeviceOverviewController.hydraCN.getFilterData().add(new FilterData(newFilter));
		});

		// run the task using a thread from the thread pool:
		exec.execute(insertFilterTask);

		this.dialogStage.close();
	}

	@FXML
	private void actionClose() {
		this.dialogStage.close();
	}
}
