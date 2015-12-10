package com.uom.cse.central_node.event;

import java.util.Date;

import com.uom.cse.central_node.services.ThriftAgentProcessInfo;

public class ApplicationEvent {

	private String name;
	
	private String packageName;
	
	private double ramUsage;
	
	private double cpuUsage;
	
	private double sentData;
	
	private double receiveData;
	
	private String pid;
	
	private String type;
	
	private String mac;
    
    
    public ApplicationEvent(ThriftAgentProcessInfo agentProcessInfo) {
    	//String name,String packageName,double ramUsage,double cpuUsage,double sentData,double receiveData,String pid,String type,String mac
        this.name = agentProcessInfo.name;
        this.packageName = agentProcessInfo.packageName;
        this.ramUsage = agentProcessInfo.ramUsage;
        this.cpuUsage = agentProcessInfo.cpuUsage;
        this.sentData = agentProcessInfo.sentData;
        this.receiveData = agentProcessInfo.receiveData;
        this.pid = agentProcessInfo.pid;
        this.type = agentProcessInfo.type;
        this.mac = agentProcessInfo.mac;
        
    }

   
    @Override
    public String toString() {
       // return "ApplicationEvent : Event ID - " + eventId + " Device Id - " + deviceId +" CPU Usage - " + getCpuUsage();
    	return "Event from Device ID - " +  mac +" and Application name - " + name;
    }


    public String getName() {
		return name;
	}


    public void setName(String name) {
		this.name = name;
	}


    public String getPackageName() {
		return packageName;
	}


    public void setPackageName(String packageName) {
		this.packageName = packageName;
	}


    public double getRamUsage() {
		return ramUsage;
	}


    public void setRamUsage(double ramUsage) {
		this.ramUsage = ramUsage;
	}


    public double getCpuUsage() {
		return cpuUsage;
	}


    public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}


    public double getSentData() {
		return sentData;
	}


    public void setSentData(double sentData) {
		this.sentData = sentData;
	}


    public double getReceiveData() {
		return receiveData;
	}


    public void setReceiveData(double receiveData) {
		this.receiveData = receiveData;
	}


    public String getPid() {
		return pid;
	}


    public void setPid(String pid) {
		this.pid = pid;
	}


    public String getType() {
		return type;
	}


    public void setType(String type) {
		this.type = type;
	}


    public String getMac() {
		return mac;
	}


    public void setMac(String mac) {
		this.mac = mac;
	}

}
