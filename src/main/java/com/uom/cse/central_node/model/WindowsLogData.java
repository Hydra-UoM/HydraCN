package com.uom.cse.central_node.model;

import com.uom.cse.central_node.data_objects.LogRule;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WindowsLogData {
	private StringProperty id;
	private StringProperty filterName;
	private StringProperty timeBound;
	private StringProperty logType;
	private StringProperty summarizationLevel;
	
	public WindowsLogData(LogRule rule) {
		id = new SimpleStringProperty(rule.getId() + "");
		filterName = new SimpleStringProperty(rule.getFilterName());
		timeBound = new SimpleStringProperty(rule.getTimeBound() + "");
		logType = new SimpleStringProperty(rule.getLogTypeNames());
		summarizationLevel = new SimpleStringProperty(rule.getSummarizationLevel() + "");
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

	public String getFilterName() {
        return filterName.get();
    }

    public void setName(String filterName) {
        this.filterName.set(filterName);
    }
    
    public StringProperty filterNameProperty() {
        return filterName;
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

    public String getLogType() {
        return logType.get();
    }

    public void setLogType(String logType) {
        this.logType.set(logType);
    }
    
    public StringProperty logTypeProperty() {
        return logType;
    }

	public int getSummarizationLevel() {
        return Integer.parseInt(summarizationLevel.get());
    }

    public void setSummarizationLevel(int level) {
        this.summarizationLevel.set(level + "");
    }
    
    public StringProperty summarizationLevelProperty() {
        return summarizationLevel;
    }

}
