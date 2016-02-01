package com.uom.cse.central_node.commands;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.bandwidthDetector.BandwidthDetector;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.services.ThriftAgentProcessInfo;
import com.uom.cse.central_node.util.LogFileWritter;
import com.uom.cse.central_node.view.DeviceOverviewController;
import com.uom.cse.central_node.windowsagentservices.ProcessStatsClient;

import javafx.concurrent.Task;

public class CommandManager {

	private static Executor executor;

	static {
		executor = Executors.newSingleThreadExecutor(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
	}

	public static void checkAndDeployCommandForWindows(ThriftAgentProcessInfo process) {
		
		if (process != null) {
			DeviceOverviewController.deviceOverviewController.deviceTable.getItems().forEach((device) -> {

				if (device.getDeviceId().equals(process.mac)) {

					Date d1 = new Date();
					long currentTimestamp = d1.getTime();

					if (device.isTimeStampExpired(currentTimestamp)) {

						device.setLastCommandTimeStamp(currentTimestamp);

						Task<Double> bandwidthTask = new Task<Double>() {
							@Override
							public Double call() throws Exception {
								double bandwidth = BandwidthDetector.calculate(device.getIPAddress(), 4, 100);
								return bandwidth;
							}
						};

						bandwidthTask.setOnSucceeded((e) -> {
							double bandwidth = bandwidthTask.getValue();

							double cpu = ProcessStatsClient.getTotalCpu(device.getIPAddress());
							double ram = ProcessStatsClient.getTotalRam(device.getIPAddress());

							int command = determineCommandForWindows(bandwidth, cpu, ram);

							if (device.getCommandType() != command) {
								device.setCommandType(command);
								deployCommandForWindows(command, device.getIPAddress());
							}

						});

						// run the task using a thread from the thread pool:
						executor.execute(bandwidthTask);
					}
				}

			});
		}
	}

	private static void deployCommandForWindows(int command, String ipAddress) {
		String commandStr = "";
		if (command == 0) {
			commandStr = "USER_DEFINE_COMMAND";
		} else if (command == 1) {
			commandStr = "FULL_DATA_WITHOUT_PREPROCESSING_COMMAND";
		} else if (command == 2) {
			commandStr = "PRE_DEFINED_DATA_WITHOUT_PREPROCESSING_COMMAND";
		} else if (command == 3) {
			commandStr = "CRITICAL_DATA_WITH_PREPROCESSING_COMMAND";
		}
		DeviceOverviewController.hydraCN.showInfoMessage(commandStr + " is sent to " + ipAddress + "  (Windows)");
		LogFileWritter.writeCommandFile(ipAddress, commandStr, Device.TYPE_WINDOWS);
	}

	private static int determineCommandForWindows(double bandwidth, double cpu, double ram) {
		int command = -1;

		boolean isBandwidthLow = isBandwidthLow(bandwidth, 100);
		boolean isResourceUtilizationHigh = isResourceUtilizationHigh(cpu, ram, 3, 100);

		if (isBandwidthLow && isResourceUtilizationHigh) {
			command = 2;
		} else if (isBandwidthLow && !isResourceUtilizationHigh) {
			command = 3;
		} else if (!isBandwidthLow && isResourceUtilizationHigh) {
			command = 1;
		} else if (!isBandwidthLow && !isResourceUtilizationHigh) {
			command = 0;
		}

		return command;
	}

	public static void checkAndDeployCommandForAndorid(ThriftAgentProcessInfo process) {
		
		if (process != null) {
			DeviceOverviewController.deviceOverviewController.deviceTable.getItems().forEach((device) -> {

				if (device.getDeviceId().equals(process.mac)) {

					Date d1 = new Date();
					long currentTimestamp = d1.getTime();

					if (device.isTimeStampExpired(currentTimestamp)) {

						device.setLastCommandTimeStamp(currentTimestamp);

						Task<Double> bandwidthTask = new Task<Double>() {
							@Override
							public Double call() throws Exception {
								double bandwidth = BandwidthDetector.calculate(device.getIPAddress(), 4, 100);
								return bandwidth;
							}
						};

						bandwidthTask.setOnSucceeded((e) -> {
							double bandwidth = bandwidthTask.getValue();
							
							//DeviceOverallInfo info = AndroidAgentServiceClient.getDeviceOverallInfo(device.getIPAddress());
							//double ram = info.ramFreeMemory/(info.ramFreeMemory + info.ramUsedMemory);
							double ram = 0.0;
							double cpu = 0.0;
							
							int command = determineCommandForAndroid(bandwidth, cpu, ram);

							if (device.getCommandType() != command) {
								device.setCommandType(command);
								deployCommandForAndroid(command, device.getIPAddress());
							}

						});

						// run the task using a thread from the thread pool:
						executor.execute(bandwidthTask);
					}
				}

			});
		}
	}

	private static void deployCommandForAndroid(int command, String ipAddress) {
		String commandStr = "";
		if (command == 0) {
			commandStr = "USER_DEFINE_COMMAND";
		} else if (command == 1) {
			commandStr = "FULL_DATA_WITHOUT_PREPROCESSING_COMMAND";
		} else if (command == 2) {
			commandStr = "PRE_DEFINED_DATA_WITHOUT_PREPROCESSING_COMMAND";
		} else if (command == 3) {
			commandStr = "CRITICAL_DATA_WITH_PREPROCESSING_COMMAND";
		}
		DeviceOverviewController.hydraCN.showInfoMessage(commandStr + " is sent to " + ipAddress + "  (Android)");
		LogFileWritter.writeCommandFile(ipAddress, commandStr, Device.TYPE_ANDROID);
	}

	private static int determineCommandForAndroid(double bandwidth, double cpu, double ram) {
		int command = -1;

		boolean isBandwidthLow = isBandwidthLow(bandwidth, 100);
		boolean isResourceUtilizationHigh = isResourceUtilizationHigh(cpu, ram, 3, 100);

		if (isBandwidthLow && isResourceUtilizationHigh) {
			command = 2;
		} else if (isBandwidthLow && !isResourceUtilizationHigh) {
			command = 3;
		} else if (!isBandwidthLow && isResourceUtilizationHigh) {
			command = 1;
		} else if (!isBandwidthLow && !isResourceUtilizationHigh) {
			command = 0;
		}

		return command;
	}

	private static boolean isResourceUtilizationHigh(double cpu, double ram, double cpuThreshold, double ramThreshold) {
		// define variable
		boolean isHigh = false;

		// check resource utilization
		if (ram > ramThreshold && cpu > cpuThreshold) {
			isHigh = true;
		}

		// return value
		return isHigh;
	}

	private static boolean isBandwidthLow(double bandwidth, double bandwitdhThreshold) {
		// define variable
		boolean isHigh = false;

		// check bandwidth
		if (bandwidth > bandwitdhThreshold) {
			isHigh = true;
		}

		// return value
		return isHigh;
	}
}
