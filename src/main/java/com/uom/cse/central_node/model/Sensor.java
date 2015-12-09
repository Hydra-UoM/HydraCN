package com.uom.cse.central_node.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sensor {
	private final StringProperty name;
	private final StringProperty availability;
	
	public Sensor(String name, boolean availability) {
		this.name = new SimpleStringProperty(name);
		this.availability = new SimpleStringProperty(availability + "");
	}
	
	public Sensor(String name, String availability) {
		this.name = new SimpleStringProperty(name);
		this.availability = new SimpleStringProperty(availability);
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
    
    public String getAvailability() {
        return availability.get();
    }

    public void setAvailability(boolean availability) {
        this.availability.set(availability + "");
    }
    
    public StringProperty availabiltyProperty() {
        return availability;
    }
}
