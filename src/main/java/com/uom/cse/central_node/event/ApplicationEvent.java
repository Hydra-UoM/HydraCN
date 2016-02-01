package com.uom.cse.central_node.event;

import java.util.Date;
import java.util.List;

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
	
	private List<String> urls;
	
	private double totalReceivedData;
    
    
    public ApplicationEvent(ThriftAgentProcessInfo agentProcessInfo) {
    	//String name,String packageName,double ramUsage,double cpuUsage,double sentData,double receiveData,String pid,String type,String mac
        this.name = agentProcessInfo.name.trim();
        this.packageName = agentProcessInfo.packageName;
        this.ramUsage = agentProcessInfo.ramUsage;
        this.cpuUsage = agentProcessInfo.cpuUsage;
        this.sentData = agentProcessInfo.sentData;
        this.receiveData = agentProcessInfo.receiveData;
        this.pid = agentProcessInfo.pid;
        this.type = agentProcessInfo.type;
        this.mac = agentProcessInfo.mac;
        this.urls = agentProcessInfo.URLs;
        this.totalReceivedData = agentProcessInfo.totalReceivedData;
        
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


	public List<String> getUrls() {
		return urls;
	}


	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
	public static boolean exceptThis(List<String> list, String token) {

			if(list.isEmpty()){
				return false;
			}else{
				for (String s1 : list) {
					String word = s1.split("//")[1].split("/")[0];
				    if(!word.equals(token)){
				    	return true;
				    }  
			    }
			}
			return false;  
	}
	
	public static boolean isAny(String processName, String token) {

		String []processlist = token.split(","); 
		for(String process : processlist){
			if(processName.equals(process)){
				return true;
			}
		}
		return false;
}


	public double getTotalReceivedData() {
		return totalReceivedData;
	}


	public void setTotalReceivedData(double totalReceivedData) {
		this.totalReceivedData = totalReceivedData;
	}

}
