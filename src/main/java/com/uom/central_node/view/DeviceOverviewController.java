package com.uom.central_node.view;

import com.uom.central_node.HydraCN;
import com.uom.central_node.android_agent_services.AndroidAgentServiceClient;
import com.uom.central_node.android_agent_services.DeviceOverallInfo;
import com.uom.central_node.commands.CommandStrings;
import com.uom.central_node.model.Device;
import com.uom.central_node.windows_agent_services.ProcessStatsClient;
import com.uom.central_node.windows_agent_services.WindowsDeviceOverallInfo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DeviceOverviewController {
	@FXML
	private TableView<Device> deviceTable;
	@FXML
	private TableColumn<Device, String> deviceIdColumn;
	@FXML
	private TableColumn<Device, String> IPAddressColumn;
	@FXML
	private TableColumn<Device, String> typeColumn;

	@FXML
	private Label headerLabel;
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
	private ChoiceBox<String> commandChoiceBox;
	@FXML
	private Label sendCommandLabel;
	@FXML
	private Button sendCommandBtn;

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

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		deviceIdColumn.setCellValueFactory(cellData -> cellData.getValue().deviceIdProperty());
		IPAddressColumn.setCellValueFactory(cellData -> cellData.getValue().IPAddressProperty());
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

		clearBasicInfoGrid();
		hideCommandBox();

		deviceTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> deviceChanged(newValue));

		commandChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> commandChanged(newValue));
	}

	public void deviceChanged(Device device) {
		// stop all monitoring command
		stopAllMonitoringCommands();

		// clear basic info showing grid
		clearBasicInfoGrid();

		// change selected device
		this.selectedDevice = device;

		// show command box
		showCommandBox();
	}

	public void showCommandBox() {

		populateCommandChoiceBox();
		commandChoiceBox.setVisible(true);
		sendCommandLabel.setVisible(true);
		sendCommandBtn.setVisible(true);
	}

	public void hideCommandBox() {
		commandChoiceBox.setVisible(false);
		sendCommandLabel.setVisible(false);
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
		
		//interprete the selected command
		interpreteCommand(selectedCommand);
	}

	public void interpreteCommand(String command) {

		if (command != null) {
			if (command.equals(CommandStrings.GET_BASIC_INFO)) {
				BASIC_INFO_FLAG = true;
				showDeviceBasicInfo();
			}

			if (command.equals(CommandStrings.GET_PROCESSES_WITH_INFORMATION)) {
				PROCESSES_INFO_FLAG = true;
				showDataViewer();
			}
		}
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
						// TODO Auto-generated catch block
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

		headerLabel.setText("Basic Information");

		firstRowLabel.setText("Device Type");
		secondRowLabel.setText("IP Address");
		thirdRowLabel.setText("MAC Address");
		fourthRowLabel.setText("Battery Life");
		fifthRowLabel.setText("RAM Free Memory");
		sixthRowLabel.setText("RAM Used Memory");
		seventhRowLabel.setText("CPU Usage");

		if (overallInfo != null) {
			cpuLabel.setText(overallInfo.cpuUsage);
			ramFreeLabel.setText(overallInfo.ramFreeMemory);
			ramUsedLabel.setText(overallInfo.ramUsedMemory);
			batteryLifeLabel.setText(overallInfo.battery);
		} else {
			cpuLabel.setText("loading..");
			ramFreeLabel.setText("loading..");
			ramUsedLabel.setText("loading..");
			batteryLifeLabel.setText("loading..");
		}
	}

	private void showWindowsBasicDetails(WindowsDeviceOverallInfo overallInfo) {

		headerLabel.setText("Basic Information");

		firstRowLabel.setText("Device Type");
		secondRowLabel.setText("IP Address");
		thirdRowLabel.setText("MAC Address");
		fourthRowLabel.setText("Network Upload");
		fifthRowLabel.setText("Network Download");
		sixthRowLabel.setText("RAM Used Memory");
		seventhRowLabel.setText("CPU Usage");

		if (overallInfo != null) {
			cpuLabel.setText(overallInfo.getCpuUsage());
			ramFreeLabel.setText(overallInfo.getNetworkDownload());
			ramUsedLabel.setText(overallInfo.getRamUsedUsage());
			batteryLifeLabel.setText(overallInfo.getNetworkUpload());
		} else {
			cpuLabel.setText("loading..");
			ramFreeLabel.setText("loading..");
			ramUsedLabel.setText("loading..");
			batteryLifeLabel.setText("loading..");
		}
	}

	private void clearBasicInfoGrid() {
		headerLabel.setText("");

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

	public void setMainApp(HydraCN hydraCN) {
		this.hydraCN = hydraCN;

		// Add observable list data to the table
		deviceTable.setItems(hydraCN.getDeviceData());
	}

	private void showDataViewer() {
		hydraCN.showDataViewer();
	}

	private void populateCommandChoiceBox() {
		ObservableList<String> command = FXCollections.observableArrayList();

		command.add(CommandStrings.GET_BASIC_INFO);
		command.add(CommandStrings.GET_PROCESSES_WITH_INFORMATION);

		commandChoiceBox.setItems(command);
	}
}
