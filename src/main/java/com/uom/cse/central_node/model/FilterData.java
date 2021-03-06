package com.uom.cse.central_node.model;

import com.uom.cse.central_node.dataobjects.Filter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FilterData {
	private StringProperty id;
	private StringProperty cpuUsage;
	private StringProperty ramUsage;
	private StringProperty sentData;
	private StringProperty reveivedData;
	private StringProperty filterName;

	private StringProperty timeBound;
	private StringProperty eventId;
	private StringProperty message;
	
	private StringProperty processes;
	
	public FilterData(Filter filter) {
		id = new SimpleStringProperty(filter.getId() + "");
		cpuUsage = new SimpleStringProperty(filter.getCpuUsage() + "");
		ramUsage = new SimpleStringProperty(filter.getRamUsage() + "");
		sentData = new SimpleStringProperty(filter.getSentData() + "");
		reveivedData = new SimpleStringProperty(filter.getReceivedData() + "");
		filterName = new SimpleStringProperty(filter.getFilterName());
		timeBound = new SimpleStringProperty(filter.getTimeBound() + "");
		eventId = new SimpleStringProperty(filter.getEventIdStr() + "");
		message = new SimpleStringProperty(filter.getMessage() + "");
		processes = new SimpleStringProperty(filter.getProcessesStr() + "");
	}
	
	public int getId() {
        return Integer.parseInt(id.get());
    }

    public void setId(int id) {
        this.id.set(id + "");
    }
    
    public StringProperty idProperty() {
        return id;
    }
	
	public double getCpuUsage() {
        return Double.parseDouble(cpuUsage.get());
    }

    public void setCpuUsage(double cpu) {
        this.cpuUsage.set(cpu + "");
    }
    
    public StringProperty cpuProperty() {
        return cpuUsage;
    }

	public double getRamUsage() {
        return Double.parseDouble(ramUsage.get());
    }

    public void setRamUsage(double ram) {
        this.ramUsage.set(ram + "");
    }
    
    public StringProperty ramProperty() {
        return ramUsage;
    }

	public double getSendData() {
        return Double.parseDouble(sentData.get());
    }

    public void setSendData(double network) {
        this.sentData.set(network + "");
    }
    
    public StringProperty sentDataProperty() {
        return sentData;
    }
    
    public double getReceivedData() {
        return Double.parseDouble(reveivedData.get());
    }

    public void setReceivedData(double network) {
        this.reveivedData.set(network + "");
    }
    
    public StringProperty receivedProperty() {
        return reveivedData;
    }
    
    public int getTimeBound() {
        return Integer.parseInt(timeBound.get());
    }

    public void setTimeBound(int timeBound) {
        this.timeBound.set(timeBound + "");
    }
    
    public StringProperty timeBoundProperty() {
        return timeBound;
    }
	
    public int getEventId() {
        return Integer.parseInt(eventId.get());
    }

    public void setEventId(int eventId) {
        this.eventId.set(eventId + "");
    }
    
    public StringProperty eventIdProperty() {
        return eventId;
    }
	
	public String getFilterName() {
        return filterName.get();
    }

    public void setName(String filterName) {
        this.filterName.set(filterName);
    }
    
    public StringProperty filterNameProperty() {
        return filterName;
    }

	public String getMessage() {
        return message.get();
    }

    public void setMessage(String message) {
        this.message.set(message);
    }
    
    public StringProperty messageProperty() {
        return message;
    }

	public String getProcesses() {
        return processes.get();
    }

    public void setProcesses(String processes) {
        this.processes.set(processes);
    }
    
    public StringProperty processesProperty() {
        return processes;
    }
}
