package com.uom.cse.central_node.view;

import java.util.List;

import com.uom.cse.central_node.HydraCN;
import com.uom.cse.central_node.androidagentservices.AndroidAgentServiceClient;
import com.uom.cse.central_node.androidagentservices.DeviceOverallInfo;
import com.uom.cse.central_node.androidagentservices.SensorDetails;
import com.uom.cse.central_node.commands.CommandStrings;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.model.InteractionData;
import com.uom.cse.central_node.model.Sensor;
import com.uom.cse.central_node.util.LogFileReader;
import com.uom.cse.central_node.util.LogFileWritter;
import com.uom.cse.central_node.windowsagentservices.ProcessStatsClient;
import com.uom.cse.central_node.windowsagentservices.WindowsDeviceOverallInfo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class DeviceOverviewController {
	@FXML
	public TableView<Device> deviceTable;
	@FXML
	private TableColumn<Device, String> deviceIdColumn;
	@FXML
	private TableColumn<Device, String> IPAddressColumn;
	@FXML
	private TableColumn<Device, String> typeColumn;
	@FXML
	private TableColumn<Device, String> nameColumn;

	@FXML
	private TitledPane basicInfoPane;
	@FXML
	private Label firstRowLabel;
	@FXML
	private Label secondRowLabel;
	@FXML
	private Label thirdRowLabel;
	@FXML
	private Label fourthRowLabel;
	@FXML
	private Label fifthRowLabel;
	@FXML
	private Label sixthRowLabel;
	@FXML
	private Label seventhRowLabel;

	@FXML
	private Label batteryLifeLabel;
	@FXML
	private Label ramUsedLabel;
	@FXML
	private Label ramFreeLabel;
	@FXML
	private Label cpuLabel;
	@FXML
	private Label deviceTypeLabel;
	@FXML
	private Label ipAddressLabel;
	@FXML
	private Label macAddressLabel;
	@FXML
	private Label infoLabel;

	@FXML
	private ChoiceBox<String> commandChoiceBox;
	@FXML
	private TitledPane sendCommandPane;
	@FXML
	private Button sendCommandBtn;

	@FXML
	public TitledPane filterPane;
	@FXML
	private TextField cpuTxt;
	@FXML
	private TextField ramTxt;
	@FXML
	private TextField processTxt;
	@FXML
	private ImageView centralImageView;

	@FXML
	private CheckBox cpuCheck;
	@FXML
	private CheckBox ramCheck;
	@FXML
	private CheckBox processesCheck;

	public static boolean isCpuChecked;
	public static boolean isRamChecked;
	public static boolean isProcessChecked;

	public static String condCpu;
	public static String condRam;
	public static String condProcess;

	private ObservableList<Sensor> sensorData = FXCollections.observableArrayList();

	@FXML
	private TableView<Sensor> sensorTable;
	@FXML
	private TableColumn<Sensor, String> sensorNameColumn;
	@FXML
	private TableColumn<Sensor, String> sensorAvailabiltyColumn;

	private ObservableList<Sensor> loggedInUserData = FXCollections.observableArrayList();

	@FXML
	private TitledPane loggedInUserPane;
	@FXML
	private TableView<Sensor> loggedInUserTable;
	@FXML
	private TableColumn<Sensor, String> informationColumn;
	@FXML
	private TableColumn<Sensor, String> valueColumn;

	// flags for commands
	public static boolean BASIC_INFO_FLAG = false;
	public static boolean PROCESSES_INFO_FLAG = false;

	// flags for indigate completion of commands
	public static boolean BASIC_INFO_COMPLETION_FLAG = true;

	public static HydraCN hydraCN;

	// store information got from android and windows
	private DeviceOverallInfo androidOverallInfo;
	private WindowsDeviceOverallInfo windowsOverallInfo;

	public static Device selectedDevice;
	public static String selectedCommand;

	public static DeviceOverviewController deviceOverviewController;

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		deviceIdColumn.setCellValueFactory(cellData -> cellData.getValue().deviceIdProperty());
		IPAddressColumn.setCellValueFactory(cellData -> cellData.getValue().IPAddressProperty());
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().deviceNameProperty());

		clearBasicInfoGrid();
		hideCommandBox();
		hideFilter();
		hideLoggedInUserTable();

		deviceTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> deviceChanged(newValue));

		commandChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> commandChanged(newValue));

		deviceOverviewController = this;

		final Tooltip tooltip1 = new Tooltip("Enable CPU Filter");
		cpuCheck.setTooltip(tooltip1);

		final Tooltip tooltip2 = new Tooltip("Enable RAM Filter");
		ramCheck.setTooltip(tooltip2);

		final Tooltip tooltip3 = new Tooltip("Enable Process Filter");
		processesCheck.setTooltip(tooltip3);
	}

	public void deviceChanged(Device device) {
		// stop all monitoring command
		stopAllMonitoringCommands();

		// clear basic info showing grid
		clearBasicInfoGrid();

		// hide sensortable
		hideSensorTable();

		// hide front image
		hideCentralImageView();

		// hide logged in user table
		hideLoggedInUserTable();

		// change selected device
		this.selectedDevice = device;

		// show command box
		showCommandBox();

		// show filterbox
		showFilter();
	}

	public void hideCentralImageView() {
		centralImageView.setVisible(false);
	}

	public void showCommandBox() {

		populateCommandChoiceBox();
		commandChoiceBox.setVisible(true);
		sendCommandPane.setVisible(true);
		sendCommandBtn.setVisible(true);
	}

	public void hideCommandBox() {
		commandChoiceBox.setVisible(false);
		sendCommandPane.setVisible(false);
		sendCommandBtn.setVisible(false);
	}

	private void stopAllMonitoringCommands() {
		BASIC_INFO_FLAG = false;
		PROCESSES_INFO_FLAG = false;
		while (!BASIC_INFO_COMPLETION_FLAG) {
		}
	}

	public void commandChanged(String command) {

		this.selectedCommand = command;
	}

	@FXML
	public void handleSendCommand() {
		// stop all monitoring command
		stopAllMonitoringCommands();

		// interprete the selected command
		interpreteCommand(selectedCommand);
	}

	public void interpreteCommand(String command) {
		// DataViewerController.updateInfoTableRunningFlag = false;

		if (command != null) {
			if (command.equals(CommandStrings.GET_BASIC_INFO)) {
				BASIC_INFO_FLAG = true;
				showDeviceBasicInfo();
			}

			if (command.equals(CommandStrings.GET_PROCESSES_WITH_INFORMATION)) {
				showDataViewer();
			}

			if (command.equals(CommandStrings.GET_SENSOR_DETAILS)) {
				updateSensorTable();
			}

			if (command.equals(CommandStrings.GET_CURRENT_LOGGEDIN_USER_INFORMATION)) {
				showLoggedInUserTable();
			}

			if (command.equals(CommandStrings.FILTER_PROCESS_ALL_DEVICES)) {
				condCpu = cpuTxt.getText();
				condProcess = processTxt.getText();
				condRam = ramTxt.getText();
				showFilterViewer();

			}
		}
	}

	private void showFilterViewer() {
		hydraCN.showAllDeviceFilter(this);
	}

	public void updateSensorTable() {
		showSensorTable();
	}

	public void showDeviceBasicInfo() {

		showAndroidOverallinfo(null);

		deviceTypeLabel.setText(selectedDevice.getType());
		ipAddressLabel.setText(selectedDevice.getIPAddress());
		macAddressLabel.setText(selectedDevice.getDeviceId());

		Thread thread = new Thread() {
			@Override
			public void run() {

				while (BASIC_INFO_FLAG) {
					String type = selectedDevice.getType();
					if (type.equals(selectedDevice.TYPE_ANDROID)) {

						androidOverallInfo = AndroidAgentServiceClient
								.getDeviceOverallInfo(selectedDevice.getIPAddress());
					} else {

						windowsOverallInfo = ProcessStatsClient.getDeviceOverallInfo(selectedDevice.getIPAddress());
					}

					Task task = new Task<Void>() {
						@Override
						public Void call() {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									if (BASIC_INFO_FLAG) {
										if (androidOverallInfo != null) {

											showAndroidOverallinfo(androidOverallInfo);
										} else if (windowsOverallInfo != null) {

											showWindowsBasicDetails(windowsOverallInfo);
										}
									}
								}
							});
							return null;
						}

					};

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Thread th = new Thread(task);
					th.setDaemon(true);
					th.start();
				}

				// indicate completion
				BASIC_INFO_COMPLETION_FLAG = true;
			}
		};

		thread.start();

	}

	private void showAndroidOverallinfo(DeviceOverallInfo overallInfo) {

		basicInfoPane.setVisible(true);

		firstRowLabel.setText("Device Type");
		secondRowLabel.setText("IP Address");
		thirdRowLabel.setText("MAC Address");
		fourthRowLabel.setText("Battery Life");
		fifthRowLabel.setText("RAM Free Memory");
		sixthRowLabel.setText("RAM Used Memory");
		seventhRowLabel.setText("CPU Usage");

		if (overallInfo != null) {

			double cpuUsage = Double.parseDouble(overallInfo.cpuUsage);
			// double ramFreeMemory =
			// Double.parseDouble(overallInfo.ramFreeMemory);
			// double ramUsedMemory =
			// Double.parseDouble(overallInfo.ramUsedMemory);

			double cpuUsageRoundOff = (double) Math.round(cpuUsage * 100);
			// double ramFreeMemoryRoundOff = Math.round(ramFreeMemory * 100) /
			// 100.f;
			// double ramUsedMemoryRoundOff = Math.round(ramUsedMemory * 100) /
			// 100.f;

			cpuLabel.setText(cpuUsageRoundOff + " %");
			ramFreeLabel.setText(overallInfo.ramFreeMemory);
			ramUsedLabel.setText(overallInfo.ramUsedMemory);
			batteryLifeLabel.setText(overallInfo.battery);
		} else {
			basicInfoPane.setVisible(true);
			cpuLabel.setText("loading..");
			ramFreeLabel.setText("loading..");
			ramUsedLabel.setText("loading..");
			batteryLifeLabel.setText("loading..");
		}
	}

	private void showWindowsBasicDetails(WindowsDeviceOverallInfo overallInfo) {

		basicInfoPane.setVisible(true);

		firstRowLabel.setText("Device Type");
		secondRowLabel.setText("IP Address");
		thirdRowLabel.setText("MAC Address");
		fourthRowLabel.setText("Network Upload");
		fifthRowLabel.setText("Network Download");
		sixthRowLabel.setText("RAM Used Memory");
		seventhRowLabel.setText("CPU Usage");

		if (overallInfo != null) {
			double cpuUsage = Double.parseDouble(overallInfo.getCpuUsage());
			double ramFreeMemory = Double.parseDouble(overallInfo.getRamUsedUsage());
			double upload = Double.parseDouble(overallInfo.getNetworkUpload());
			double download = Double.parseDouble(overallInfo.getNetworkDownload());

			double cpuUsageRoundOff = (double) Math.round(cpuUsage * 100) / 100;
			double ramFreeMemoryRoundOff = Math.round(ramFreeMemory * 100) / (1024 * 1024);
			double uploadRoundOff = Math.round(upload * 100) / 1024;
			double downloadRoundOff = Math.round(download * 100) / 1024;

			cpuLabel.setText(cpuUsageRoundOff + " %");
			ramFreeLabel.setText(downloadRoundOff + " MB");
			ramUsedLabel.setText(ramFreeMemoryRoundOff + " MB");
			batteryLifeLabel.setText(uploadRoundOff + " MB");
		} else {
			cpuLabel.setText("loading..");
			ramFreeLabel.setText("loading..");
			ramUsedLabel.setText("loading..");
			batteryLifeLabel.setText("loading..");
		}
	}

	private void clearBasicInfoGrid() {
		// basicPane.setVisible(false);
		basicInfoPane.setVisible(false);

		firstRowLabel.setText("");
		secondRowLabel.setText("");
		thirdRowLabel.setText("");
		fourthRowLabel.setText("");
		fifthRowLabel.setText("");
		sixthRowLabel.setText("");
		seventhRowLabel.setText("");

		cpuLabel.setText("");
		ramFreeLabel.setText("");
		ramUsedLabel.setText("");
		batteryLifeLabel.setText("");
		deviceTypeLabel.setText("");
		ipAddressLabel.setText("");
		macAddressLabel.setText("");
	}

	public void showFilter() {
		filterPane.setVisible(true);
	}

	public void hideFilter() {
		filterPane.setVisible(false);
		cpuTxt.setText("");
		ramTxt.setText("");
		processTxt.setText("");
	}

	public void hideSensorTable() {
		sensorTable.setVisible(false);
	}

	public void showSensorTable() {

		sensorNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		sensorAvailabiltyColumn.setCellValueFactory(cellData -> cellData.getValue().availabiltyProperty());
		sensorData = FXCollections.observableArrayList();
		sensorTable.setItems(sensorData);

		sensorData.add(new Sensor("loading..", false));

		Thread thread = new Thread() {
			@Override
			public void run() {

				List<SensorDetails> sensorDetails = AndroidAgentServiceClient
						.getSensorDetails(selectedDevice.getIPAddress());

				sensorTable.getItems().remove(0);

				for (SensorDetails detail : sensorDetails) {
					sensorData.add(new Sensor(detail.sensorName, detail.availability));
				}

			}
		};

		thread.start();

		sensorTable.setVisible(true);
	}

	public void hideLoggedInUserTable() {
		loggedInUserPane.setVisible(false);
	}

	public void showLoggedInUserTable() {

		informationColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().availabiltyProperty());
		loggedInUserData = FXCollections.observableArrayList();
		loggedInUserTable.setItems(loggedInUserData);

		Thread thread = new Thread() {
			@Override
			public void run() {

				List<Sensor> sensors = LogFileReader.readUserInfoData(selectedDevice);

				for (Sensor sensor : sensors) {
					loggedInUserData.add(sensor);
				}
			}
		};

		thread.start();

		loggedInUserPane.setVisible(true);
	}

	public void setMainApp(HydraCN hydraCN) {
		this.hydraCN = hydraCN;

		// Add observable list data to the table
		deviceTable.setItems(hydraCN.getDeviceData());
	}

	public void showInfoMessage(String ipAddress, String deviceName, String message, String shortInfo) {
		Platform.runLater(() -> 
			{
				if ("N/A".equals(ipAddress)){
					infoLabel.setText(message);
				}else {
					infoLabel.setText(shortInfo);
				}
				if (!"connected".equals(message)){
					InteractionData data = new InteractionData(LogFileWritter.getDateAndTime(), ipAddress,
							deviceName, message);
					
					DeviceOverviewController.hydraCN.getInterationData().add(data);
				}
			}
		);
	}

	private void showDataViewer() {
		hydraCN.showDataViewer();
	}

	private void populateCommandChoiceBox() {
		ObservableList<String> command = FXCollections.observableArrayList();

		command.add(CommandStrings.GET_BASIC_INFO);
		command.add(CommandStrings.GET_PROCESSES_WITH_INFORMATION);
		command.add(CommandStrings.FILTER_PROCESS_ALL_DEVICES);

		if (selectedDevice != null) {
			if (Device.TYPE_ANDROID.equals(selectedDevice.getType())) {
				command.add(CommandStrings.GET_SENSOR_DETAILS);
			}

			if (Device.TYPE_WINDOWS.equals(selectedDevice.getType())) {
				command.add(CommandStrings.GET_CURRENT_LOGGEDIN_USER_INFORMATION);
			}
		}

		commandChoiceBox.setItems(command);
	}

	@FXML
	private void checkHandleCPU() {

		if (!cpuCheck.isSelected()) {

			cpuTxt.setDisable(true);

			cpuTxt.setText("");

			final Tooltip tooltip = new Tooltip("Enable CPU Filter");
			cpuCheck.setTooltip(tooltip);

			isCpuChecked = false;

		} else {

			cpuTxt.setDisable(false);

			cpuTxt.setText("");

			final Tooltip tooltip = new Tooltip("Disable CPU Filter");
			cpuCheck.setTooltip(tooltip);

			isCpuChecked = true;
		}
	}

	@FXML
	private void checkHandleRAM() {

		if (!ramCheck.isSelected()) {

			ramTxt.setDisable(true);

			ramTxt.setText("");

			final Tooltip tooltip = new Tooltip("Enable RAM Filter");
			ramCheck.setTooltip(tooltip);

			isRamChecked = false;

		} else {

			ramTxt.setDisable(false);

			ramTxt.setText("");

			final Tooltip tooltip = new Tooltip("Disable RAM Filter");
			ramCheck.setTooltip(tooltip);

			isRamChecked = true;
		}
	}

	@FXML
	private void checkHandleProcess() {

		if (!processesCheck.isSelected()) {

			processTxt.setDisable(true);

			processTxt.setText("");

			final Tooltip tooltip = new Tooltip("Enable Process Filter");
			processesCheck.setTooltip(tooltip);

			isProcessChecked = false;

		} else {

			processTxt.setDisable(false);

			processTxt.setText("");

			final Tooltip tooltip = new Tooltip("Disable Process Filter");
			processesCheck.setTooltip(tooltip);

			isProcessChecked = true;
		}
	}

}
