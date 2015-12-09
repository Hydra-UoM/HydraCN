package com.uom.cse.central_node.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AllDeviceDetails {
	private final StringProperty deviceId;
	private final StringProperty IPAddress;
	private final StringProperty processName;
	private final StringProperty cpu;
	private final StringProperty sharedMemory;
	private final StringProperty username;
	
	public AllDeviceDetails(String deviceId, String IPaddress, String username, String processName, 
			String cpu, String sharedMemory) {
		this.deviceId = new SimpleStringProperty(deviceId);
		this.IPAddress = new SimpleStringProperty(IPaddress);
		this.processName = new SimpleStringProperty(processName);
		this.cpu = new SimpleStringProperty(cpu);
		this.sharedMemory = new SimpleStringProperty(sharedMemory);
		this.username = new SimpleStringProperty(username);
	}
	
	public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
    
    public StringProperty usernameProperty() {
        return username;
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
    
    public String getProcessName() {
        return processName.get();
    }

    public void setProcessName(String processName) {
        this.processName.set(processName);
    }
    
    public StringProperty processNameProperty() {
        return processName;
    }
    
    public String getCpu() {
        return cpu.get();
    }

    public void setCpu(String cpu) {
        this.cpu.set(cpu);
    }
    
    public StringProperty cpuProperty() {
        return cpu;
    }
    
    public String getSharedMemory() {
        return sharedMemory.get();
    }

    public void setSharedMemory(String sharedMemory) {
        this.sharedMemory.set(sharedMemory);
    }
	
    public StringProperty sharedMemoryProperty() {
        return sharedMemory;
    }
}
