package com.uom.cse.central_node.data_objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.uom.cse.central_node.model.FilterData;

public class Filter {
	private int id;
	private double cpuUsage;
	private double ramUsage;
	private double sentData;
	private double receivedData;
	private String filterName;

	private int timeBound;
	private String eventId;
	private String message;
	
	private String processes;
	private String returnValue = "";
	public static final String PROCESS_DELIMETER = ",";
	public static final String EVENT_DELIMETER = ",";
	
	public Filter() {
		
	}
	
	public Filter(FilterData data) {
		cpuUsage = data.getCpuUsage();
		ramUsage = data.getRamUsage();
		sentData = data.getSendData();
		receivedData = data.getReceivedData();
		timeBound = data.getTimeBound();
		message = data.getMessage();
		processes = data.getProcesses();
		filterName = data.getFilterName();
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

	public String getEventIdStr() {
		return eventId;
	}

	public List<Integer> getEventId() {
		final List<Integer> events = new ArrayList<Integer>();
		String[] parts = this.eventId.split(EVENT_DELIMETER);
		Arrays.asList(parts).forEach((eventId) -> events.add(Integer.parseInt(eventId)));
		return events;
	}

	public void setEventIdStr(String eventId) {
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
	
	public double getReceivedData() {
		return receivedData;
	}
	
	public void setReceivedData(double receivedData) {
		this.receivedData = receivedData;
	}

	public double getSentData() {
		return sentData;
	}
	
	public void setSentData(double sentData) {
		this.sentData = sentData;
	}
	
	public List<String> getProcesses() {
		if(processes != null){
			String[] parts = processes.split(PROCESS_DELIMETER);
			return Arrays.asList(parts);
		}
		return new ArrayList<>();
	}

	public String[] getProcessesAsArray() {
		if(processes != null && "".equals(processes)){
			return processes.split(PROCESS_DELIMETER);
		}
		return null;
	}

	public void setProcesses(String processes) {
		this.processes = processes;
	}
	
	public String getProcessesStr(){
		return processes;
	}
	

	public String getProcessesToQuery(){
		if(processes != null && !"".equals(processes)){
			String []tempProcesses = processes.split(PROCESS_DELIMETER);
			String processString = "";
			for (String string : tempProcesses) {
				string = "'" + string + "'";
				processString += string + ",";
			}
			return processString.substring(0,processString.length()-1);
		}
		return "";
	}

}
