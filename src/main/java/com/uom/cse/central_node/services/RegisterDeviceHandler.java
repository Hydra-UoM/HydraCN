package com.uom.cse.central_node.services;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.thrift.TException;

import com.uom.cse.central_node.HydraCN;
import com.uom.cse.central_node.android_agent_services.AndroidAgentServiceClient;
import com.uom.cse.central_node.commands.CommandManager;
import com.uom.cse.central_node.data_objects.Filter;
import com.uom.cse.central_node.data_objects.FilterTable;
import com.uom.cse.central_node.data_objects.LogRule;
import com.uom.cse.central_node.data_objects.LogRuleTable;
import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.util.LogFileWritter;
import com.uom.cse.central_node.view.DeviceOverviewController;
import com.uom.cse.central_node.windows_agent_services.ProcessStatsClient;

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
		
		com.uom.cse.central_node.model.Device device = new com.uom.cse.central_node.model.Device(
				deviceDetails.deviceId, deviceDetails.IPAddress, deviceDetails.type);
		
		List<com.uom.cse.central_node.model.Device> existingDeviceList = 
				DeviceOverviewController.deviceOverviewController.deviceTable.getItems();
		
		count = 0;
		for(com.uom.cse.central_node.model.Device tempDevice : existingDeviceList){
			
			if(tempDevice.getDeviceId().equals(device.getDeviceId())){
				
				Platform.runLater 
				( () -> DeviceOverviewController.deviceOverviewController.deviceTable.getItems().remove(count));
				
			}
			count++;
		}
		
		//add device to observableList of device table
		hydraCN.getDeviceData().add(device);
//		float bandwidth = AndroidAgentServiceClient.getNetworkBandwidth(device.getIPAddress());
		
		
		Task<Filter> commandTask = new Task<Filter>() {
			@Override
			public Filter call() throws Exception {
				Filter filter = FilterTable.getAppliedFilter();
				return filter;
			}
		};

		commandTask.setOnSucceeded((e) ->{
        	Filter retFilter = commandTask.getValue();
        	if(retFilter != null){
        		if(device.getType().equals(Device.TYPE_ANDROID)){
        			AndroidAgentServiceClient.deployCommand(deviceDetails.IPAddress, retFilter);
        		}else{
        			ProcessStatsClient.getAllAvgProcessInfo(deviceDetails.IPAddress, retFilter);
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

		logCommandTask.setOnSucceeded((e) ->{
			LogRule logRule = logCommandTask.getValue();
        	if(logRule != null && !device.getType().equals(Device.TYPE_ANDROID)){
        		ProcessStatsClient.sendWindowsLogInfo(deviceDetails.IPAddress, logRule);
    		}
        });
		
		// run the task using a thread from the thread pool:
		executor.execute(logCommandTask);
		return true;
	}

	@Override
	public boolean pushProcessesInfo(List<ThriftAgentProcessInfo> processes) throws TException {
		
		LogFileWritter.writeFile(processes);
		
		try{
			ThriftAgentProcessInfo process = processes.get(0);
			if(process.type.equals(Device.TYPE_WINDOWS)){
				//deploy command for windows
				CommandManager.checkAndDeployCommandForWindows(process);
			}else if(process.type.equals(Device.TYPE_ANDROID)){
				//deploy command for android
				CommandManager.checkAndDeployCommandForAndorid(process);
			}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		
		return true;
		
	}

	@Override
	public boolean pushLogInfo(List<myLogStructure> logInfo) throws TException {
		LogFileWritter.writeFileWindowsLog(logInfo);
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
	
}
