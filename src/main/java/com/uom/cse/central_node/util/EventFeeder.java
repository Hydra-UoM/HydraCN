package com.uom.cse.central_node.util;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.data_objects.Filter;
import com.uom.cse.central_node.event.ApplicationEvent;
import com.uom.cse.central_node.handler.ProcessInfoEventHandler;
import com.uom.cse.central_node.services.ThriftAgentProcessInfo;
import com.uom.cse.central_node.subscriber.CriticalEventSubscriber;

public class EventFeeder {
	
	public static Filter defaultFilter;
	
	static{
		defaultFilter = new Filter();
		defaultFilter.setFilterName("Default Filter");
		defaultFilter.setCpuUsage(30);
		defaultFilter.setRamUsage(41);
		defaultFilter.setSentData(410);
		defaultFilter.setReceivedData(420);
		defaultFilter.setTimeBound(1);
	}
	
	
	
	public static void applyFilter(Filter filter){
		
		defaultFilter.setFilterName(filter.getFilterName());
		defaultFilter.setCpuUsage(filter.getCpuUsage());
		defaultFilter.setRamUsage(filter.getRamUsage());
		defaultFilter.setSentData(filter.getSentData());
		defaultFilter.setReceivedData(filter.getReceivedData());
		defaultFilter.setTimeBound(filter.getTimeBound());
	    defaultFilter.setMessage(filter.getMessage());
	    
	}
	
	public static void pushToCEP(List<ThriftAgentProcessInfo> processes){
		
		CriticalEventSubscriber customSubscriber = new CriticalEventSubscriber();
		
		ProcessInfoEventHandler processInfoEventHandler = new ProcessInfoEventHandler(customSubscriber,defaultFilter);
		String customExpression = "select mac,cpuUsage,ramUsage,sentData,receiveData from ApplicationEvent where cpuUsage >" + 48.00 + " and ramUsage > "+ 46.00 +"and sentData > " + 1500.00 + " and receiveData > " + 1500.00;
		processInfoEventHandler.createCriticalEventCheckExpression(customExpression, customSubscriber);
		
		ExecutorService eventFeeder = Executors.newSingleThreadExecutor();
		eventFeeder.submit(new Runnable() {
	            public void run() {
	            	
	            	processes.forEach((process)->{
	        			processInfoEventHandler.handle(new ApplicationEvent(process));
	        		});
	            	
//	            	int count = 0;
//	            	
//	            	while(count < 100){
//	        			
//	        			ApplicationEvent event = new ApplicationEvent(new ThriftAgentProcessInfo("name", "packageName", new Random().nextInt(50), new Random().nextInt(50), new Random().nextInt(2000), new Random().nextInt(2000), "15", "Android", "123:33:45:333"));
//	            		//ApplicationEvent event = new ApplicationEvent("name","packagename",new Random().nextInt(50),new Random().nextInt(50),new Random().nextInt(500),new Random().nextInt(500),"100","Android","1002:4444:456:78");
//	                    //sampleEventHandler.handle(ve);
//	            		processInfoEventHandler.handle(event);
//	        			//System.out.println("testing");
//	        		}

	            }
	    });
		
	}
}
