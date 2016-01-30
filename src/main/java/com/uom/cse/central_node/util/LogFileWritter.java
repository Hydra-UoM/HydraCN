package com.uom.cse.central_node.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.services.ThriftAgentProcessInfo;
import com.uom.cse.central_node.services.myLogStructure;
import com.uom.cse.central_node.services.myUserAccountDetailsStruct;

import javafx.concurrent.Task;

public class LogFileWritter {
	private static Executor executor;
	private static final String PERFORMANCE_LOG_FILE_NAME_PREFIX = "PERFORMANCE_DATA";
	private static final String USER_INFO_LOG_FILE_NAME_PREFIX = "USER_INFO";
	private static final String WINDOWS_LOG_FILE_NAME_PREFIX = "WINDOWS_LOG";
	private static final String COMMANDS_LOG_FILE_NAME = "COMMANDS.txt";
	private static final String LOG_FILES_NAMES_FILE = "logfilenames.txt";

	private static StringBuilder builder;

	static {
		executor = Executors.newSingleThreadExecutor(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
	}

	public static void writeCommandFile(String ipAddress, String command, String type) {
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				builder = new StringBuilder();
				builder.append(getDateAndTime());
				builder.append(" IP Address : " + command);
				builder.append(", Command : " + ipAddress);
				builder.append(", Type : " + type);

				writeFile(COMMANDS_LOG_FILE_NAME, builder, "centralNodeCommands");

				return null;
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
	}

	public static void writeFile(ThriftAgentProcessInfo processInfo, String deviceName) {
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				builder = new StringBuilder();
				builder.append(getDateAndTime(processInfo.timestamp));
				builder.append(" Process Name : " + processInfo.name.trim());
				builder.append(", MAC ID : " + processInfo.mac);
				builder.append(", CPU usage : " + processInfo.cpuUsage);
				builder.append(", RAM usage : " + processInfo.ramUsage);
				builder.append(", Sent Data : " + processInfo.sentData);
				builder.append(", Received Data : " + processInfo.receiveData);
				builder.append(", Process ID : " + processInfo.pid);
				
				if(!Device.TYPE_ANDROID.equals(processInfo.type)){
					builder.append(", URLs : " + processInfo.URLs.toString());
				}
				
				builder.append(", Type : " + processInfo.type);

				String filename = getFileName(PERFORMANCE_LOG_FILE_NAME_PREFIX, processInfo.mac,
						deviceName + "_" + processInfo.type);

				String foldername = getFolderName(processInfo.mac, deviceName);
				
				insertLogFilename(foldername + "/" + filename);

				writeFile(filename, builder, foldername);

				return null;
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
	}

	public static void writeFile(List<ThriftAgentProcessInfo> processesInfo, String deviceName) {
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				processesInfo.forEach((processInfo) -> {
					builder = new StringBuilder();
					builder.append(getDateAndTime(processInfo.timestamp));
					builder.append(" Process Name : " + processInfo.name.trim());
					builder.append(", MAC ID : " + processInfo.mac);
					builder.append(", CPU usage : " + processInfo.cpuUsage);
					builder.append(", RAM usage : " + processInfo.ramUsage);
					builder.append(", Sent Data : " + processInfo.sentData);
					builder.append(", Received Data : " + processInfo.receiveData);
					builder.append(", Process ID : " + processInfo.pid);
					builder.append(", Type : " + processInfo.type);

					String filename = getFileName(PERFORMANCE_LOG_FILE_NAME_PREFIX, processInfo.mac,
							deviceName + "_" + processInfo.type);

					String foldername = getFolderName(processInfo.mac, deviceName);
					
					insertLogFilename(foldername + "/" + filename);
					
					writeFile(filename, builder, foldername);
				});

				return null;
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
	}

	public static void writeFileWindowsLog(List<myLogStructure> logs, String deviceName) {
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				logs.forEach((log) -> {
					builder = new StringBuilder();

					builder.append(formatTime(log));
					builder.append(", Message : " + log.message.trim());
					builder.append(", Level : " + log.levelMessageString.trim());
					builder.append(", MAC : " + log.mac.trim());
					builder.append(", Device : " + deviceName.trim());
					builder.append(", Process Id : " + log.executionProcessID.trim());
					builder.append(", Thread Id : " + log.executionThreadID.trim());
					builder.append(", Computer : " + log.computer.trim());
					builder.append(", Event Id : " + log.EventID.trim());
					builder.append(", S_Account Name : " + log.mySubject1.Account_Name.trim());
					builder.append(", Object Name : " + log.myObject1.Object_Name.trim());
					builder.append(", Object Type : " + log.myObject1.Object_Type.trim());
					builder.append(", Source Address : " + log.myNetworkInformation1.Source_Address.trim());
					builder.append(", Source Port : " + log.myNetworkInformation1.Source_Port.trim());
					builder.append(", Destination Address : " + log.myNetworkInformation1.Destination_Address.trim());
					builder.append(", Destination Port : " + log.myNetworkInformation1.Destination_Port.trim());
					builder.append(", Protocol : " + log.myNetworkInformation1.Protocol.trim());
					builder.append(", Change Type : " + log.myChangeInformation1.Change_Type.trim());
					builder.append(", Accesses : " + log.myAccessRequestInformation1.Accesses.trim());
					builder.append(", Access Reason : " + log.myAccessRequestInformation1.Access_Reasons);
					builder.append(", Privilege for Access Check : "
							+ log.myAccessRequestInformation1.Privileges_Used_For_Access_Check.trim());
					builder.append(", Error Information Reason : " + log.myErrorInformation1.Reason.trim());
					builder.append(", Process Name : " + log.myProcessInformation1.Process_Name.trim());
					builder.append(", Logon Type : " + log.myLogonType1.Logon_Type.trim());
					builder.append(", Impersonation Level : " + log.myImpersonationLevel1.Impersonation_Level.trim());
					builder.append(", Account Name : " + log.myAccountForWhichLogonFailed1.Account_Name.trim());
					builder.append(", Failure Reason : " + log.myFailureInformation1.Failure_Reason.trim());
					builder.append(", Failure Status : " + log.myFailureInformation1.Status.trim());
					builder.append(", Logon Account Name : " + log.myNewLogon1.Account_Name.trim());
					builder.append(", Event : " + log.eventCategory.trim());

					String filename = getFileName(WINDOWS_LOG_FILE_NAME_PREFIX, log.mac, deviceName);
					
					String foldername = getFolderName(log.mac, deviceName);
					
					insertLogFilename(foldername + "/" + filename);
					
					writeFile(filename, builder, foldername);
				});

				return null;
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
	}

	public static void writeFileWindowsLog(myLogStructure log, String deviceName) {
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				builder = new StringBuilder();

				builder.append(formatTime(log));
				builder.append(", Message : " + log.message.trim());
				builder.append(", Level : " + log.levelMessageString.trim());
				builder.append(", MAC : " + log.mac.trim());
				builder.append(", Device : " + deviceName.trim());
				builder.append(", Process Id : " + log.executionProcessID.trim());
				builder.append(", Thread Id : " + log.executionThreadID.trim());
				builder.append(", Computer : " + log.computer.trim());
				builder.append(", Event Id : " + log.EventID.trim());
				builder.append(", S_Account Name : " + log.mySubject1.Account_Name.trim());
				builder.append(", Object Name : " + log.myObject1.Object_Name.trim());
				builder.append(", Object Type : " + log.myObject1.Object_Type.trim());
				builder.append(", Source Address : " + log.myNetworkInformation1.Source_Address.trim());
				builder.append(", Source Port : " + log.myNetworkInformation1.Source_Port.trim());
				builder.append(", Destination Address : " + log.myNetworkInformation1.Destination_Address.trim());
				builder.append(", Destination Port : " + log.myNetworkInformation1.Destination_Port.trim());
				builder.append(", Protocol : " + log.myNetworkInformation1.Protocol.trim());
				builder.append(", Change Type : " + log.myChangeInformation1.Change_Type.trim());
				builder.append(", Accesses : " + log.myAccessRequestInformation1.Accesses.trim());
				builder.append(", Access Reason : " + log.myAccessRequestInformation1.Access_Reasons);
				builder.append(", Privilege for Access Check : "
						+ log.myAccessRequestInformation1.Privileges_Used_For_Access_Check.trim());
				builder.append(", Error Information Reason : " + log.myErrorInformation1.Reason.trim());
				builder.append(", Process Name : " + log.myProcessInformation1.Process_Name.trim());
				builder.append(", Logon Type : " + log.myLogonType1.Logon_Type.trim());
				builder.append(", Impersonation Level : " + log.myImpersonationLevel1.Impersonation_Level.trim());
				builder.append(", Account Name : " + log.myAccountForWhichLogonFailed1.Account_Name.trim());
				builder.append(", Failure Reason : " + log.myFailureInformation1.Failure_Reason.trim());
				builder.append(", Failure Status : " + log.myFailureInformation1.Status.trim());
				builder.append(", Logon Account Name : " + log.myNewLogon1.Account_Name.trim());
				builder.append(", Event : " + log.eventCategory.trim());

				String filename = getFileName(WINDOWS_LOG_FILE_NAME_PREFIX, log.mac, deviceName);
				
				String foldername = getFolderName(log.mac, deviceName);
				
				insertLogFilename(foldername + "/" + filename);
				
				writeFile(filename, builder, foldername);

				return null;
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
	}

	public static void writeFile(myUserAccountDetailsStruct userInfo) {
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				builder = new StringBuilder();
				builder.append(getDateAndTime());
				builder.append(" Computer Name : " + userInfo.computerName);
				builder.append(", Account Name : " + userInfo.usri4_name);
				builder.append(", Last logOn : " + getDateAndTime(userInfo.usri4_last_logon));
				builder.append(", Last logOff : " + getDateAndTime(userInfo.usri4_last_logoff));
				builder.append(", Last logOn hours : " + getDateAndTime(userInfo.usri4_logon_hours));

				String priv = userInfo.usri4_priv;
				String privStr = "";
				if (priv.equals("0")) {
					privStr = "GUEST";
				}

				if (priv.equals("1")) {
					privStr = "USER";
				}

				if (priv.equals("2")) {
					privStr = "ADMIN";
				}

				builder.append(", Privilege : " + privStr);
				builder.append(", No Of logOns : " + userInfo.usri4_num_logons);
				builder.append(", Current User : Yes");

				String filename = getFileName(USER_INFO_LOG_FILE_NAME_PREFIX, userInfo.mac, userInfo.computerName);
				
				String foldername = getFolderName(userInfo.mac, userInfo.computerName);
				
				insertLogFilename(foldername + "/" + filename);
				
				writeFile(filename, builder, foldername);

				return null;
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
	}

	public static void writeFileUsersInfo(List<myUserAccountDetailsStruct> usersInfo) {
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {

				usersInfo.forEach((userInfo) -> {
					builder = new StringBuilder();
					builder.append(getDateAndTime());
					builder.append(" Computer Name : " + userInfo.computerName);
					builder.append(", Account Name : " + userInfo.usri4_name);
					builder.append(", Last logOn : " + getDateAndTime(userInfo.usri4_last_logon));
					builder.append(", Last logOff : " + getDateAndTime(userInfo.usri4_last_logoff));
					builder.append(", Last logOn hours : " + getDateAndTime(userInfo.usri4_logon_hours));

					String priv = userInfo.usri4_priv;
					String privStr = "";
					if (priv.equals("0")) {
						privStr = "GUEST";
					}

					if (priv.equals("1")) {
						privStr = "USER";
					}

					if (priv.equals("2")) {
						privStr = "ADMIN";
					}

					builder.append(", Privilege : " + privStr);
					builder.append(", No Of logOns : " + userInfo.usri4_num_logons);
					builder.append(", Current User : No");

					String filename = getFileName(USER_INFO_LOG_FILE_NAME_PREFIX, userInfo.mac, userInfo.computerName);
					
					String foldername = getFolderName(userInfo.mac, userInfo.computerName);
					
					insertLogFilename(foldername + "/" + filename);
					
					writeFile(filename, builder, foldername);
				});
				return null;
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
	}

	public static String getDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		// get current date time with Date()
		Date date = new Date();

		// convert date into String
		String currentDateAndTime = dateFormat.format(date);

		return currentDateAndTime;

	}

	public static String getDateAndTime(String timestampStr) {
		long timestamp = 0;

		if ("".equals(timestampStr) || "0".equals(timestampStr)) {
			return "0";
		}

		try {
			timestamp = Long.parseLong(timestampStr);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			Timestamp stamp = new Timestamp(timestamp * 1000);

			long millis = stamp.getTime();

			Date date = new Date(millis);

			// convert date into String
			String currentDateAndTime = dateFormat.format(date);

			return currentDateAndTime;
		} catch (NumberFormatException e) {
			return "";
		}

	}
	
	public static String formatTime(myLogStructure log){
		int day = log.myTimeStamp1.day;
		int month = log.myTimeStamp1.month;
		int hour = log.myTimeStamp1.hour;
		int minute = log.myTimeStamp1.minute;
		int second = log.myTimeStamp1.second;
		
		StringBuilder sb = new StringBuilder();
		
		if(day <= 9){
			sb.append("0" + day + "/");
		}else{
			sb.append(day + "/");
		}
		
		if(month <= 9){
			sb.append("0" + month + "/");
		}else{
			sb.append(month + "/");
		}
		
		sb.append(log.myTimeStamp1.year + " ");
		
		if(hour <= 9){
			sb.append("0" + hour + ":");
		}else{
			sb.append(hour + ":");
		}

		if(minute <= 9){
			sb.append("0" + minute + ":");
		}else{
			sb.append(minute + ":");
		}

		if(second <= 9){
			sb.append("0" + second + " ");
		}else{
			sb.append(second + " ");
		}
		
		return sb.toString();
	}

	public static void getPerformanceStatistics() {
		List<String> filenames = getWindowsLogFilenames();

		filenames.forEach((filename) -> {
			try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

				String line = br.readLine();

				while (line != null) {
					List<String> elements = Arrays.asList(line.split(","));
					
					elements.forEach((element) -> {
						String[] values = element.split(":");
						
						if(values.length >= 2){
							String title = values[0].trim();
							if("Event".equals(title)){
								
							}
						}
						
					});
					
					line = br.readLine();
				}

			} catch (FileNotFoundException e) {

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
	}

	private static String getFileName(String prefix, String mac, String name) {

		String macWithoutColon = mac.replace(":", "");

		String filename = prefix.toLowerCase() + "_" + macWithoutColon.toLowerCase() + "_" + name.toLowerCase()
				+ ".txt";

		return filename;
	}
	
	private static String getFolderName(String mac, String name) {

		String macWithoutColon = mac.replace(":", "");

		String filename = macWithoutColon.toLowerCase() + "_" + name.toLowerCase();

		return filename;
	}

	private static void writeFile(String filename, StringBuilder content, String foldername) {
		File dir = new File(foldername);
		dir.mkdir();
		
		File file = new File(foldername + "/" + filename);
		try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
			printWriter.println(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void insertLogFilename(String filename) {
		boolean flag = true;

		try (BufferedReader br = new BufferedReader(new FileReader("LogFileConfig/" + LOG_FILES_NAMES_FILE))) {

			String line = br.readLine();

			while (line != null) {
				if (filename.equals(line)) {
					flag = false;
				}
				line = br.readLine();
			}

			if (flag) {
				StringBuilder sb = new StringBuilder();
				sb.append(filename);
				writeFile(LOG_FILES_NAMES_FILE, sb, "LogFileConfig");
			}

		} catch (FileNotFoundException e) {

			StringBuilder sb = new StringBuilder();
			sb.append(filename);
			writeFile(LOG_FILES_NAMES_FILE, sb, "logFileConfig");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static List<String> getPerformanceDataFilenames() {

		List<String> returnList = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader("LogFileConfig/" + LOG_FILES_NAMES_FILE))) {

			String line = br.readLine();

			while (line != null) {

				if (line.toLowerCase().contains(PERFORMANCE_LOG_FILE_NAME_PREFIX.toLowerCase())) {
					returnList.add(line);
				}

				line = br.readLine();
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnList;
	}

	private static List<String> getUserInfoFilenames() {

		List<String> returnList = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader("LogFileConfig/" + LOG_FILES_NAMES_FILE))) {

			String line = br.readLine();

			while (line != null) {

				if (line.toLowerCase().contains(USER_INFO_LOG_FILE_NAME_PREFIX.toLowerCase())) {
					returnList.add(line);
				}

				line = br.readLine();
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnList;
	}

	private static List<String> getWindowsLogFilenames() {

		List<String> returnList = new ArrayList<String>();

		try (BufferedReader br = new BufferedReader(new FileReader("LogFileConfig/" + LOG_FILES_NAMES_FILE))) {

			String line = br.readLine();

			while (line != null) {

				if (line.toLowerCase().contains(WINDOWS_LOG_FILE_NAME_PREFIX.toLowerCase())) {
					returnList.add(line);
				}

				line = br.readLine();
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnList;
	}
	
	public static String getPerformanceFilename(Device device) {


		try (BufferedReader br = new BufferedReader(new FileReader("LogFileConfig/" + LOG_FILES_NAMES_FILE))) {

			String line = br.readLine();

			while (line != null) {

				if (line.toLowerCase().contains(PERFORMANCE_LOG_FILE_NAME_PREFIX.toLowerCase())) {
					String macWithoutColon = device.getDeviceId().replace(":", "");
					if(line.toLowerCase().contains(macWithoutColon.toLowerCase())){
						return line;
					}
				}

				line = br.readLine();
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static List<LogFile> getPerformanceFilename(List<Device> devices) {

		List<LogFile> returnList = new ArrayList<LogFile>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("LogFileConfig/" + LOG_FILES_NAMES_FILE))) {

			String line = br.readLine();

			while (line != null) {

				if (line.toLowerCase().contains(PERFORMANCE_LOG_FILE_NAME_PREFIX.toLowerCase())) {
					
					for (Device device : devices){
						String macWithoutColon = device.getDeviceId().replace(":", "");
						if(line.toLowerCase().contains(macWithoutColon.toLowerCase())){
							LogFile file = new LogFile();
							file.setName(device.getName());
							file.setIpAddress(device.getIPAddress());
							file.setDeviceId(device.getDeviceId());
							file.setFilename(line);
							
							returnList.add(file);
							break;
						}
					}
					
				}

				line = br.readLine();
			}

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}

		return returnList;
	}
}
