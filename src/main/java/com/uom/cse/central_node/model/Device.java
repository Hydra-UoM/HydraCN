package com.uom.cse.central_node.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Device {
	
	private final StringProperty deviceId;
	private final StringProperty IPAddress;
	private final StringProperty type;
	private int commandType;
	private long lastCommandTimeStamp;
	private String name;
	
	public final static String TYPE_ANDROID = "Android";
	public final static String TYPE_WINDOWS = "Windows";
	
	public Device(String deviceId, String IPaddress, String type) {
		this.deviceId = new SimpleStringProperty(deviceId);
		this.IPAddress = new SimpleStringProperty(IPaddress);
		this.type = new SimpleStringProperty(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLastCommandTimeStamp() {
		return lastCommandTimeStamp;
	}

	public void setLastCommandTimeStamp(long lastCommandTimeStamp) {
		this.lastCommandTimeStamp = lastCommandTimeStamp;
	}

	public boolean isTimeStampExpired(long timestamp){
		if((timestamp - this.lastCommandTimeStamp) > 120000){
			return true;
		}else{
			return false;
		}
	}
	
	public int getCommandType() {
		return commandType;
	}

	public void setCommandType(int commandType) {
		this.commandType = commandType;
	}

	public String getDeviceId() {
        return deviceId.get();
    }

    public void setDeviceId(String deviceId) {
        this.deviceId.set(deviceId);
    }
    
    public StringProperty deviceIdProperty() {
        return deviceId;
    }
    
	public String getIPAddress() {
        return IPAddress.get();
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress.set(IPAddress);
    }
    
    public StringProperty IPAddressProperty() {
        return IPAddress;
    }
    
	public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }
    
    public StringProperty typeProperty() {
        return type;
    }
}
