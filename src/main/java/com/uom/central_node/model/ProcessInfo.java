package com.uom.central_node.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProcessInfo {
	
	private final StringProperty processName;
	private final StringProperty cpu;
	private final StringProperty sharedMemory;
	private final StringProperty privateMemory;
	private final StringProperty sentData;
	private final StringProperty recievedData;
	
	public ProcessInfo(String processName, String cpu, String sharedMemory, String privateMemory,
			String sentData, String recievedData) {
		
		this.processName = new SimpleStringProperty(processName);
		this.cpu = new SimpleStringProperty(cpu);
		this.sharedMemory = new SimpleStringProperty(sharedMemory);
		this.privateMemory = new SimpleStringProperty(privateMemory);
		this.sentData = new SimpleStringProperty(sentData);
		this.recievedData = new SimpleStringProperty(recievedData);
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
    
    public String getPrivateMemory() {
        return privateMemory.get();
    }

    public void setPrivateMemory(String privateMemory) {
        this.privateMemory.set(privateMemory);
    }
    
    public StringProperty privateMemoryProperty() {
        return privateMemory;
    }
    
    public String getSentData() {
        return sentData.get();
    }

    public void setSentData(String sentData) {
        this.sentData.set(sentData);
    }
    
    public StringProperty sentDataProperty() {
        return sentData;
    }
    
    public String getRecievedData() {
        return recievedData.get();
    }

    public void setRecievedData(String recievedData) {
        this.recievedData.set(recievedData);
    }
    
    public StringProperty receievedDataProperty() {
        return recievedData;
    }
}
