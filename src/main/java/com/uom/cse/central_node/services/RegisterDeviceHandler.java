package com.uom.cse.central_node.services;

import java.util.List;

import org.apache.thrift.TException;

import com.uom.cse.central_node.HydraCN;
import com.uom.cse.central_node.view.DeviceOverviewController;

import javafx.application.Platform;

public class RegisterDeviceHandler implements RegisterDeviceService.Iface {

	public static HydraCN hydraCN;

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
		return true;
	}

	@Override
	public boolean pushProcessesInfo(List<ThriftAgentProcessInfo> processes) throws TException {
		
		return true;
	}

	@Override
	public boolean pushLogInfo(List<myLogStructure> logInfo) throws TException {
		
		return true;
	}

	@Override
	public boolean pushUsersInfo(List<myUserAccountDetailsStruct> usersInfo) throws TException {
		
		return true;
	}

	@Override
	public boolean pushCurrentUserInfo(myUserAccountDetailsStruct userInfo) throws TException {
		
		return true;
	}
	
}
