package com.uom.cse.central_node.util;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.event.ApplicationEvent;
import com.uom.cse.central_node.handler.ProcessInfoEventHandler;
import com.uom.cse.central_node.services.ThriftAgentProcessInfo;
import com.uom.cse.central_node.subscriber.CriticalEventSubscriber;

public class EventFeeder {
	
	public static Filter defaultFilter;
	public static ProcessInfoEventHandler eventHandler;
	public static boolean isHandlerset;
	
	static{
		defaultFilter = new Filter();
		defaultFilter.setFilterName("Default Filter");
		defaultFilter.setCpuUsage(10);
		defaultFilter.setRamUsage(30);
		defaultFilter.setSentData(10);
		defaultFilter.setReceivedData(10);
		defaultFilter.setTimeBound(1);
		defaultFilter.setProcesses("name,nameone,nametwo");
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
	
	public static String statementInterpreter(Filter filter){
		
		String statement = "select name,mac,cpuUsage,ramUsage,sentData,receiveData from ApplicationEvent";
		
		if(filter.getTimeBound() != 0){
			statement += ".win:time_batch(" + filter.getTimeBound() * 60 +"sec)";
			//statement += ".win:time_batch(10sec)";
		}
		if(!filter.getProcesses().isEmpty()){
			statement += " where name in (" + filter.getProcessesToQuery() +") and";
			//statement += " where name in ('nameone','name')";
		}
		
		statement += " having avg(cpuUsage) > " + filter.getCpuUsage() + " and avg(ramUsage) > " + filter.getRamUsage() + " and avg(sentData) > " + filter.getSentData() + " and avg(receiveData) >" + filter.getReceivedData();
		//statement += " cpuUsage > " + filter.getCpuUsage() + " and ramUsage > " + filter.getRamUsage() + " and sentData > " + filter.getSentData() + " and receiveData >" + filter.getReceivedData();
		return statement;
	}
	
	public static void deployCEPRule(String cepRule, String alertMessage){
		
		CriticalEventSubscriber customSubscriber = new CriticalEventSubscriber(alertMessage);
		
		ProcessInfoEventHandler processInfoEventHandler = new ProcessInfoEventHandler(customSubscriber);
		
		//String customExpression = "select mac,cpuUsage,ramUsage,sentData,receiveData from ApplicationEvent where cpuUsage > 40.00 and ramUsage > 40.00 and sentData > 150.00 and receiveData > 150.00";
		
		processInfoEventHandler.createCriticalEventCheckExpression(cepRule, customSubscriber);
		
		//customExpression = "select cpuUsage from ApplicationEvent where cpuUsage > 30";
		
		/*processInfoEventHandler.createCriticalEventCheckExpression(customExpression, new StatementSubscriber() {
			
			@Override
			public String getStatement() {
				// TODO Auto-generated method stub
				return null;
			}
			
			 public void update(Map<String, ApplicationEvent> eventMap) {


			        StringBuilder sb = new StringBuilder();
			        sb.append("************************************************");
			        sb.append("\n* [ALERT] : CRITICAL EVENT DETECTED BY ESPER UPdated! ");
			        sb.append("\n* The CPU Usage of device 1 is above 20%; CPU Usage - " + eventMap.get("cpuUsage") );
			        sb.append("\n**********************************************");
			        System.out.println(sb.toString());
			        
			    }
		}); */
		eventHandler = processInfoEventHandler;
		isHandlerset = true;
		
	}
	
	public static void pushToCEP (List<ThriftAgentProcessInfo> processes){

		ExecutorService eventFeeder = Executors.newSingleThreadExecutor();
		eventFeeder.submit(new Runnable() {
	            public void run() {
	            	
	            	processes.forEach((process)->{
	            		eventHandler.handle(new ApplicationEvent(process));
//	        			try {
//							Thread.sleep(5000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}	   
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
