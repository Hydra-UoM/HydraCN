package com.uom.cse.central_node.data_objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogRule {
	private int id;
	private String filterName;
	private String timeBound;
	private String logType;
	private int summarizationLevel;
	private boolean isProcessLogEnable;
	private String processName;
	private String type;
	private String securityLevel;
	
	private String returnValue = "";
	
	public String getSecurityLevel() {
		return securityLevel;
	}
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public String getTimeBound() {
		return timeBound;
	}
	public void setTimeBound(String timeBound) {
		this.timeBound = timeBound;
	}
	
	public String getLogTypeNames() {
		if(logType != null){
			Arrays.asList(logType.split(",")).forEach((type) -> {
				
				if(type.equals("17")){
					returnValue += "Logons Failures,";
				}
				
				if(type.equals("25")){
					returnValue += "Success Login Information,";
				}
				
				if(type.equals("27")){
					returnValue += "Firewall Events,";
				}
				
				if(type.equals("28")){
					returnValue += "Account Usage Events,";
				}
				
				if(type.equals("29")){
					returnValue += "Group Policy Editors,";
				}
				
				if(type.equals("30")){
					returnValue += "Windows Defenders,";
				}
				
				if(type.equals("31")){
					returnValue += "Mobile Device Activities,";
				}
				
				if(type.equals("32")){
					returnValue += "Printing Service Events,";
				}
				
				if(type.equals("33")){
					returnValue += "System Failure Events,";
				}
				
				if(type.equals("34")){
					returnValue += "Clearing Event Logs,";
				}
				
				if(type.equals("35")){
					returnValue += "Window Update Errors,";
				}
				
				if(type.equals("36")){
					returnValue += "Application Crash Events,";
				}
				
				if(type.equals("37")){
					returnValue += "Software Instalation Events,";
				}
				
				if(type.equals("38")){
					returnValue += "Remote Logons,";
				}
				
				if(type.equals("22")){
					returnValue += "Currently Logged In User Information,";
				}
				
				if(type.equals("23")){
					returnValue += "All User Information,";
				}
			});
		}
		
		return returnValue;
	}
	
	public String getLogType() {
		return logType;
	}
	public List<String> getLogTypeAsList(){
		List<String> returnValue = new ArrayList<String>();
		
		if(logType != null){
			returnValue = Arrays.asList(logType.split(","));
		}
		
		return returnValue;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public int getSummarizationLevel() {
		return summarizationLevel;
	}
	public void setSummarizationLevel(int summarizationLevel) {
		this.summarizationLevel = summarizationLevel;
	}
	public boolean isProcessLogEnable() {
		return isProcessLogEnable;
	}
	public void setProcessLogEnable(boolean isProcessLogEnable) {
		this.isProcessLogEnable = isProcessLogEnable;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
