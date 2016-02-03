package com.uom.cse.central_node.test.esper;

import org.junit.Test;

import com.uom.cse.central_node.event.ApplicationEvent;
import com.uom.cse.central_node.handler.ProcessInfoEventHandler;
import com.uom.cse.central_node.services.ThriftAgentProcessInfo;
import com.uom.cse.central_node.subscriber.CriticalEventSubscriber;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

public class EsperTest {
	
	@Test
	public void testInsertAppliedRule() {
		

		ThriftAgentProcessInfo process = new ThriftAgentProcessInfo();
		process.setCpuUsage(100);
		process.setMac("33:33:33:33:33:33");
		process.setRamUsage(100);
		process.setReceiveData(200);
		process.setSentData(200);
		process.name = "Test Process";
		process.packageName = "Test Package";
		process.ramUsage = 200;
		process.cpuUsage = 200;
		process.sentData = 200;
		process.receiveData = 200;
		process.pid = "P100";
		process.type = "Android";
		process.mac = "33:33:33:33:33:33";
		process.URLs = new ArrayList<String>();
		process.totalReceivedData = 400;
		
		CriticalEventSubscriber customSubscriber = new CriticalEventSubscriber("Test Subscriber"){
			@Override
			public void update(Map<String, ApplicationEvent> eventMap) {
				
				assertEquals(eventMap.get("cpuUsage"), process.cpuUsage);
				assertEquals(eventMap.get("ramUsage"), process.ramUsage);
				assertEquals(eventMap.get("receiveData"), process.receiveData);
				assertEquals(eventMap.get("sentData"), process.sentData);
				assertEquals(eventMap.get("mac"), process.mac);
			}
		};
		
		ProcessInfoEventHandler processInfoEventHandler = new ProcessInfoEventHandler(customSubscriber);
		
		String cepRule = "select mac,cpuUsage,ramUsage,sentData,receiveData from ApplicationEvent "
				+ "where cpuUsage > 40.00 and ramUsage > 40.00 and sentData > 150.00 and receiveData > 150.00";
		
		processInfoEventHandler.createCriticalEventCheckExpression(cepRule, customSubscriber);
		
		processInfoEventHandler.handle(new ApplicationEvent(process));
		
		
	}
}
