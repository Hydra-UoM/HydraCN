package com.uom.cse.central_node.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.uom.cse.central_node.model.AllDeviceDetails;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.model.ProcessInfo;
import com.uom.cse.central_node.model.Sensor;

public class LogFileReader {

	public static List<ProcessInfo> readProcessInfoData(Device device) {
		List<ProcessInfo> returnList = new ArrayList<ProcessInfo>();

		List<ProcessInfo> allProcessInfoList = new ArrayList<ProcessInfo>();

		String filename = LogFileWritter.getPerformanceFilename(device);

		if (filename != null) {
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

				String line = br.readLine();

				while (line != null) {
					ProcessInfo processInfo = convertToProcessInfo(line);

					allProcessInfoList.add(processInfo);

					line = br.readLine();
				}

				// get latest info
				for (ProcessInfo duplicatedInfo : allProcessInfoList) {
					boolean isAdded = false;
					for (int i = 0; i < returnList.size(); i++) {
						ProcessInfo info = returnList.get(i);

						if (duplicatedInfo.getProcessName().equals(info.getProcessName())) {
							returnList.set(i, duplicatedInfo);
							isAdded = true;
						}
					}

					if (!isAdded) {
						returnList.add(duplicatedInfo);
					}

				}

			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return returnList;
	}

	public static List<AllDeviceDetails> readProcessInfoData(List<Device> devices) {
		List<AllDeviceDetails> returnList = new ArrayList<AllDeviceDetails>();

		List<AllDeviceDetails> allProcessInfoList = new ArrayList<AllDeviceDetails>();

		List<LogFile> files = LogFileWritter.getPerformanceFilename(devices);

		for (LogFile file : files) {
			String filename = file.getFilename();
			if (filename != null) {
				try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

					String line = br.readLine();

					while (line != null) {
						ProcessInfo processInfo = convertToProcessInfo(line);

						AllDeviceDetails details = new AllDeviceDetails(file.getDeviceId(), file.getIpAddress(),
								file.getName(), processInfo.getProcessName(), processInfo.getCpu(),
								processInfo.getSharedMemory());

						allProcessInfoList.add(details);

						line = br.readLine();
					}

					// get latest info
					for (AllDeviceDetails duplicatedInfo : allProcessInfoList) {
						boolean isAdded = false;
						for (int i = 0; i < returnList.size(); i++) {
							AllDeviceDetails info = returnList.get(i);

							if (duplicatedInfo.getProcessName().equals(info.getProcessName())) {
								returnList.set(i, duplicatedInfo);
								isAdded = true;
							}
						}

						if (!isAdded) {
							returnList.add(duplicatedInfo);
						}

					}

				} catch (FileNotFoundException e) {

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return returnList;
	}

	public static List<Sensor> readUserInfoData(Device device) {
		List<Sensor> returnList = new ArrayList<Sensor>();

		String filename = LogFileWritter.getUserInfoFilename(device);

		if (filename != null) {
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

				String line = br.readLine();

				while (line != null) {
					List<Sensor> returnedList = convertToUserInfoIfItIsCurrentUser(line);

					if (returnedList != null) {
						returnList = returnedList;
					}
					
					line = br.readLine();
				}

			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return returnList;
	}

	private static ProcessInfo convertToProcessInfo(String line) {
		String[] dataArray = line.split(",");

		String processName = "";
		String cpuUsage = "0.0";
		String ramUsage = "0.0";
		String sendData = "0.0";
		String receivedData = "0.0";

		for (String data : dataArray) {
			String[] infoArray = data.split(":");

			if (infoArray.length > 2) {
				if (infoArray[2].contains("Process Name")) {
					processName = infoArray[3].trim();
				}
			}

			if ("CPU usage".equals(infoArray[0].trim())) {
				cpuUsage = infoArray[1].trim();

				try {
					double cpuInDouble = Double.parseDouble(cpuUsage);
					cpuUsage = truncateDecimal(cpuInDouble, 2) + "";
				} catch (NumberFormatException e) {

				}
			}

			if ("RAM usage".equals(infoArray[0].trim())) {
				ramUsage = infoArray[1].trim();

				try {
					double ramInDouble = Double.parseDouble(ramUsage);
					ramUsage = truncateDecimal(ramInDouble, 2) + "";
				} catch (NumberFormatException e) {

				}
			}

			if ("Sent Data".equals(infoArray[0].trim())) {
				sendData = infoArray[1].trim();

				try {
					double sendDataInDouble = Double.parseDouble(sendData);
					sendData = truncateDecimal(sendDataInDouble, 2) + "";
				} catch (NumberFormatException e) {

				}
			}

			if ("Received Data".equals(infoArray[0].trim())) {
				receivedData = infoArray[1].trim();

				try {
					double receivedDataInDouble = Double.parseDouble(receivedData);
					receivedData = truncateDecimal(receivedDataInDouble, 2) + "";
				} catch (NumberFormatException e) {

				}
			}

		}

		ProcessInfo processInfo = new ProcessInfo(processName, cpuUsage, ramUsage, sendData, receivedData);

		return processInfo;
	}

	private static List<Sensor> convertToUserInfoIfItIsCurrentUser(String line) {
		List<Sensor> currentUser = null;

		String[] dataArray = line.split(",");

		Sensor accountName = null;
		Sensor lastLogOn = null;
		Sensor privilege = null;
		Sensor noOfLogOns = null;

		for (String data : dataArray) {
			String[] infoArray = data.split(":");

			if ("Account Name".equals(infoArray[0].trim())) {
				accountName = new Sensor("Account Name", infoArray[1].trim());
			}

			if ("Last logOn".equals(infoArray[0].trim())) {
				if (infoArray.length > 3) {
					lastLogOn = new Sensor("Last logOn", infoArray[1].trim() + ":" + infoArray[2].trim() + 
							":" + infoArray[3].trim());
				}else{
					lastLogOn = new Sensor("Last logOn", "N/A");
				}
				
			}

			if ("Privilege".equals(infoArray[0].trim())) {
				privilege = new Sensor("Privilege", infoArray[1].trim());
			}

			if ("No Of logOns".equals(infoArray[0].trim())) {
				noOfLogOns = new Sensor("No Of logOns", infoArray[1].trim());
			}
			
			if ("Current User".equals(infoArray[0].trim())) {
				if ("Yes".equals(infoArray[1].trim())) {
					currentUser = new ArrayList<Sensor>();
					currentUser.add(accountName);
					currentUser.add(lastLogOn);
					currentUser.add(privilege);
					currentUser.add(noOfLogOns);
				}
			}
		}

		return currentUser;
	}

	private static BigDecimal truncateDecimal(double x, int numberofDecimals) {
		if (x > 0) {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
		} else {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
		}
	}
}
