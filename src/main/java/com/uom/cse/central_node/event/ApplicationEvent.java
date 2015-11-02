package com.uom.cse.central_node.event;

import java.util.Date;

public class ApplicationEvent {

    private int eventId;
    
    private int cpuUsage;
    
    private int deviceId;
    
    
    public ApplicationEvent(int eventId,int cpuUsage, int deviceId) {
    	
        this.eventId = eventId;
        this.deviceId = deviceId;
        this.cpuUsage = cpuUsage;
        
    }

    public int getEventId() {
        return eventId;
    }
    
    
    public int getCpuUsage(){
    	return cpuUsage;
    }
    
    public int getDeviceId(){
    	return deviceId;
    }

    @Override
    public String toString() {
        return "ApplicationEvent : Event ID - " + eventId + " Device Id - " + deviceId +" CPU Usage - " + cpuUsage;
    }

}
