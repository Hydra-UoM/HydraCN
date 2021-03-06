package com.uom.cse.central_node.services;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.thrift.TException;

import com.uom.cse.central_node.HydraCN;
import com.uom.cse.central_node.androidagentservices.AndroidAgentServiceClient;
import com.uom.cse.central_node.commands.CommandManager;
import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.dataobjects.FilterTable;
import com.uom.cse.central_node.dataobjects.LogRule;
import com.uom.cse.central_node.dataobjects.LogRuleTable;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.util.EventFeeder;
import com.uom.cse.central_node.util.LogFileWritter;
import com.uom.cse.central_node.view.DeviceOverviewController;
import com.uom.cse.central_node.windowsagentservices.ProcessStatsClient;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class RegisterDeviceHandler implements RegisterDeviceService.Iface {

	public static HydraCN hydraCN;

	private static Executor executor;
	
	static {
		executor = Executors.newSingleThreadExecutor(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
	}

	int count = 0;

	@Override
	public boolean registerDevice(com.uom.cse.central_node.services.Device deviceDetails) throws TException {
		
		com.uom.cse.central_node.model.Device device = new com.uom.cse.central_node.model.Device(deviceDetails.deviceId,
				deviceDetails.IPAddress, deviceDetails.type);
		device.setName(deviceDetails.name);

		List<com.uom.cse.central_node.model.Device> existingDeviceList = DeviceOverviewController.deviceOverviewController.deviceTable
				.getItems();

		synchronized(RegisterDeviceHandler.class){
			count = 0;
			for (com.uom.cse.central_node.model.Device tempDevice : existingDeviceList) {

				if (tempDevice.getDeviceId().equals(device.getDeviceId())) {

					Platform.runLater(
							() -> DeviceOverviewController.deviceOverviewController.deviceTable.getItems().remove(count));
					
					device.setAlreadyRegistered(true);
				}
				count++;
			}

			// add device to observableList of device table
			hydraCN.getDeviceData().add(device);
		}
		
		hydraCN.showInfoMessage(device.getIPAddress(), device.getName(), "connected",
				deviceDetails.IPAddress + " " + deviceDetails.name + " connected");
		return true;
	}
	
	boolean returnFlag = false;
	@Override
	public boolean getCommands(com.uom.cse.central_node.services.Device deviceDetails) throws TException {
		
		com.uom.cse.central_node.model.Device device = new com.uom.cse.central_node.model.Device(deviceDetails.deviceId,
				deviceDetails.IPAddress, deviceDetails.type);
		device.setName(deviceDetails.name);

		Task<Filter> commandTask = new Task<Filter>() {
			@Override
			public Filter call() throws Exception {
				Filter filter = FilterTable.getAppliedFilter();
				return filter;
			}
		};

		commandTask.setOnSucceeded((e) -> {
			Filter retFilter = commandTask.getValue();
			if (retFilter != null) {
				if (device.getType().equals(Device.TYPE_ANDROID)) {
					AndroidAgentServiceClient.deployCommand(deviceDetails.IPAddress, retFilter);
					returnFlag = true;
				} else {
					ProcessStatsClient.getAllAvgProcessInfo(deviceDetails.IPAddress, retFilter);
					returnFlag = true;
				}
			}
		});

		// run the task using a thread from the thread pool:
		executor.execute(commandTask);

		Task<LogRule> logCommandTask = new Task<LogRule>() {
			@Override
			public LogRule call() throws Exception {
				LogRule rule = LogRuleTable.getAppliedRule();
				return rule;
			}
		};

		logCommandTask.setOnSucceeded((e) -> {
			LogRule logRule = logCommandTask.getValue();
			if (logRule != null && !device.getType().equals(Device.TYPE_ANDROID)) {
				ProcessStatsClient.sendWindowsLogInfo(deviceDetails.IPAddress, logRule);
				returnFlag = true;
			}
		});

		// run the task using a thread from the thread pool:
		executor.execute(logCommandTask);
		
		hydraCN.showInfoMessage(device.getIPAddress(), device.getName(), "connected and got command",
				deviceDetails.IPAddress + " " + deviceDetails.name + " got command");
		
		return true;
	}

	@Override
	public boolean pushProcessesInfo(List<ThriftAgentProcessInfo> processes) throws TException {
		final String[] details = new String[2];
		
		if(EventFeeder.isHandlerset){
			EventFeeder.pushToCEP(processes);
		}
		
		processes.forEach((process) -> {
			
			List<com.uom.cse.central_node.model.Device> existingDeviceList = DeviceOverviewController.deviceOverviewController.deviceTable
					.getItems();

			for (com.uom.cse.central_node.model.Device tempDevice : existingDeviceList) {
				if (tempDevice.getDeviceId().equals(process.mac)) {
					LogFileWritter.writeFile(process, tempDevice.getName());
					details[0] = tempDevice.getIPAddress();
					details[1] = tempDevice.getName();
					break;
				}
			}
			
			if (process.type.equals(Device.TYPE_WINDOWS)) {
				// deploy command for windows
				CommandManager.checkAndDeployCommandForWindows(process);
			} else if (process.type.equals(Device.TYPE_ANDROID)) {
				// deploy command for android
				CommandManager.checkAndDeployCommandForAndorid(process);
			}
		});
		
		hydraCN.showInfoMessage(details[0], details[1], "pushed processes information",
				details[0] + " " + details[1] + " pushed processes information");
		
		return true;

	}

	@Override
	public boolean pushLogInfo(List<myLogStructure> logInfo) throws TException {
		try {
			myLogStructure log = logInfo.get(0);
			List<com.uom.cse.central_node.model.Device> existingDeviceList = DeviceOverviewController.deviceOverviewController.deviceTable
					.getItems();

			for (com.uom.cse.central_node.model.Device tempDevice : existingDeviceList) {
				if (tempDevice.getDeviceId().equals(log.mac)) {
					LogFileWritter.writeFileWindowsLog(logInfo, tempDevice.getName());
					break;
				}
			}

		} catch (Exception e) {
			
		}

		return true;
	}

	@Override
	public boolean pushUsersInfo(List<myUserAccountDetailsStruct> usersInfo) throws TException {
		LogFileWritter.writeFileUsersInfo(usersInfo);
		return true;
	}

	@Override
	public boolean pushCurrentUserInfo(myUserAccountDetailsStruct userInfo) throws TException {
		LogFileWritter.writeFile(userInfo);
		return true;
	}

	@Override
	public boolean pushLogInfoTest1(List<myLogStructure> logInfo) throws TException {
		final String[] details = new String[2];
		
		logInfo.forEach((log) -> {
			List<com.uom.cse.central_node.model.Device> existingDeviceList = DeviceOverviewController.deviceOverviewController.deviceTable
					.getItems();
			
			for (com.uom.cse.central_node.model.Device tempDevice : existingDeviceList) {
				if (tempDevice.getDeviceId().equals(log.mac)) {
					LogFileWritter.writeFileWindowsLog(log, tempDevice.getName());
					details[0] = tempDevice.getIPAddress();
					details[1] = tempDevice.getName();
					break;
				}
			}
			
		});
		
		hydraCN.showInfoMessage(details[0], details[1], "pushed log information",
				details[0] + " " + details[1] + " pushed log information");
		
		return true;
	}

}
