package com.uom.cse.central_node.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.services.ThriftAgentProcessInfo;
import com.uom.cse.central_node.services.myLogStructure;
import com.uom.cse.central_node.services.myUserAccountDetailsStruct;

import javafx.concurrent.Task;

public class LogFileWritter {
	private static Executor executor;
	private static final String PERFORMANCE_LOG_FILE_NAME = "PerformanceData.txt";
	private static final String USER_INFO_LOG_FILE_NAME = "userInfo.txt";
	private static final String WINDOWS_LOG_FILE_NAME = "WindowsLog.txt";
	
	private static StringBuilder builder;
	
	static {
		executor = Executors.newSingleThreadExecutor(runnable -> {
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			return t;
		});
	}

	public static void writeFile(List<ThriftAgentProcessInfo> processesInfo) {
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {
				
				processesInfo.forEach((processInfo) -> {
					builder = new StringBuilder();
					builder.append(getDateAndTime());
					builder.append(" Process Name : " + processInfo.name.trim());
					builder.append(", MAC ID : " + processInfo.mac);
					builder.append(", CPU usage : " + processInfo.cpuUsage);
					builder.append(", RAM usage : " + processInfo.ramUsage);
					builder.append(", Sent Data : " + processInfo.sentData);
					builder.append(", Received Data : " + processInfo.receiveData);
					builder.append(", Process ID : " + processInfo.pid);
					builder.append(", Type : " + processInfo.type);
					writeFile(PERFORMANCE_LOG_FILE_NAME, builder);
				});

				return null;
			}
		};

		// run the task using a thread from the thread pool:
		executor.execute(writeTask);
	}
	
	public static void writeFileWindowsLog(List<myLogStructure> logs){
		Task<Object> writeTask = new Task<Object>() {
			@Override
			public Object call() throws Exception {
				
				logs.forEach((log) -> {
					builder = new StringBuilder();
					
					builder.append("" + log.myTimeStamp1.day + "/" + log.myTimeStamp1.month + "/"
							+ log.myTimeStamp1.year + " " + log.myTimeStamp1.hour + ":" + log.myTimeStamp1.minute + 
							":" + log.myTimeStamp1.second);
					builder.append(", Message : " + log.message);
					builder.append(", Level : " + log.levelMessageString);
					builder.append(", Process Id : " + log.executionProcessID);
					builder.append(", Thread Id : " + log.executionThreadID);
					builder.append(", Computer : " + log.computer);
					builder.append(", Event Id : " + log.EventID);
					builder.append(", S_Account Name : " + log.mySubject1.Account_Name);
					builder.append(", Object Name : " + log.myObject1.Object_Name);
					builder.append(", Object Type : " + log.myObject1.Object_Type);
					builder.append(", Source Address : " + log.myNetworkInformation1.Source_Address);
					builder.append(", Source Port : " + log.myNetworkInformation1.Source_Port);
					builder.append(", Destination Address : " + log.myNetworkInformation1.Destination_Address);
					builder.append(", Destination Port : " + log.myNetworkInformation1.Destination_Port);
					builder.append(", Protocol : " + log.myNetworkInformation1.Protocol);
					builder.append(", Change Type : " + log.myChangeInformation1.Change_Type);
					builder.append(", Accesses : " + log.myAccessRequestInformation1.Accesses);
					builder.append(", Access Reason : " + log.myAccessRequestInformation1.Access_Reasons);
					builder.append(", Privilege for Access Check : " + log.myAccessRequestInformation1.Privileges_Used_For_Access_Check);
					builder.append(", Error Information Reason : " + log.myErrorInformation1.Reason);
					builder.append(", Process Name : " + log.myProcessInformation1.Process_Name);
					builder.append(", Logon Type : " + log.myLogonType1.Logon_Type);
					builder.append(", Impersonation Level : " + log.myImpersonationLevel1.Impersonation_Level);
					builder.append(", Account Name : " + log.myAccountForWhichLogonFailed1.Account_Name);
					builder.append(", Failure Reason : " + log.myFailureInformation1.Failure_Reason);
					builder.append(", Failure Status : " + log.myFailureInformation1.Status);
					builder.append(", Logon Account Name : " + log.myNewLogon1.Account_Name);
					
					writeFile(WINDOWS_LOG_FILE_NAME, builder);	
				});

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
				
				writeFile(USER_INFO_LOG_FILE_NAME, builder);

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
					
					writeFile(USER_INFO_LOG_FILE_NAME, builder);
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
		try{
			timestamp = Long.parseLong(timestampStr);
		}catch (Exception e) {
			
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Timestamp stamp = new Timestamp(timestamp * 1000);
		
		long millis = stamp.getTime();
		
		Date date = new Date(millis);
		
		// convert date into String
		String currentDateAndTime = dateFormat.format(date);

		return currentDateAndTime;
	}

	private static void writeFile(String filename, StringBuilder content) {
		File file = new File(filename);
		try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, true))) {
			printWriter.println(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
