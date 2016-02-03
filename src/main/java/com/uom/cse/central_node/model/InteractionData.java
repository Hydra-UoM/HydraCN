package com.uom.cse.central_node.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InteractionData {
	private StringProperty name;
	private StringProperty ipAddress;
	private StringProperty time;
	private StringProperty action;
	
	public InteractionData(String time, String ipAddress, String name, String action) {
		this.name = new SimpleStringProperty(name);
		this.ipAddress = new SimpleStringProperty(ipAddress);
		this.time = new SimpleStringProperty(time);
		this.action = new SimpleStringProperty(action);
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty() {
		return name;
	}
	
	public String getIpAddress() {
		return ipAddress.get();
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress.set(ipAddress);
	}
	public StringProperty ipAddressProperty() {
		return ipAddress;
	}
	
	public String gettime() {
		return time.get();
	}
	public void settime(String time) {
		this.time.set(time);
	}
	public StringProperty timeProperty() {
		return time;
	}
	
	public String getaction() {
		return action.get();
	}
	public void setaction(String action) {
		this.action.set(action);
	}
	public StringProperty actionProperty() {
		return action;
	}
}
