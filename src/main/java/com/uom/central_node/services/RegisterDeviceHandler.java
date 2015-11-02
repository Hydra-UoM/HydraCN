package com.uom.central_node.services;

import org.apache.thrift.TException;

import com.uom.central_node.HydraCN;
import com.uom.central_node.model.Device;

public class RegisterDeviceHandler implements RegisterDeviceService.Iface {

	public static HydraCN hydraCN;

	@Override
	public void registerDevice(com.uom.central_node.services.Device deviceDetails) throws TException {
		
		com.uom.central_node.model.Device device = new com.uom.central_node.model.Device(
				deviceDetails.deviceId, deviceDetails.IPAddress, deviceDetails.type);
		
		hydraCN.getDeviceData().add(device);
	}
	
}
