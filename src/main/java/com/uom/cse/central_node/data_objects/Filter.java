package com.uom.cse.central_node.data_objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filter {
	private int id;
	private double cpuUsage;
	private double ramUsage;
	private double networkUsage;
	private String filterName;

	private int timeBound;
	private int eventId;
	private String message;
	
	private List<String> processes;
	private String returnValue = "";
	public static final String PROCESS_DELIMETER = "-";
	
	public Filter() {
		processes = new ArrayList<String>();
	}
	
	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public int getTimeBound() {
		return timeBound;
	}

	public void setTimeBound(int timeBound) {
		this.timeBound = timeBound;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public double getCpuUsage() {
		return cpuUsage;
	}
	
	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	
	public double getRamUsage() {
		return ramUsage;
	}
	
	public void setRamUsage(double ramUsage) {
		this.ramUsage = ramUsage;
	}
	
	public double getNetworkUsage() {
		return networkUsage;
	}
	
	public void setNetworkUsage(double networkUsage) {
		this.networkUsage = networkUsage;
	}
	

	public void addProcess(String value){
		processes.add(value);
	}
	
	public List<String> getProcesses() {
		return processes;
	}

	public void setProcesses(List<String> processes) {
		this.processes = processes;
	}

	public void setProcesses(String processes) {
		String[] parts = processes.split(PROCESS_DELIMETER);
		
		this.processes = Arrays.asList(parts);
	}
	
	public String getProcessesStr(){
		
		this.processes.forEach((process)->{
			returnValue += (PROCESS_DELIMETER + process);
		});
		
		return returnValue;
	}
}
