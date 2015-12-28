package com.uom.cse.central_node.model;

import javafx.beans.property.StringProperty;

public class CEPRuleData {
	private StringProperty name;
	private StringProperty rule;
	private StringProperty status;
	private StringProperty alertMessage;
	
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty() {
		return name;
	}
	
	public String getRule() {
		return rule.get();
	}
	public void setRule(String rule) {
		this.rule.set(rule);
	}
	public StringProperty ruleProperty() {
		return name;
	}
	
	public String getStatus() {
		return status.get();
	}
	public void setStatus(String status) {
		this.status.set(status);
	}
	public StringProperty statusProperty() {
		return name;
	}
	
	public String getAlertMessage() {
		return alertMessage.get();
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage.set(alertMessage);
	}
	public StringProperty alertMessageProperty() {
		return name;
	}
	
}
