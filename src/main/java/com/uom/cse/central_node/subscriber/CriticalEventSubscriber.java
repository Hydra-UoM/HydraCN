package com.uom.cse.central_node.subscriber;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.uom.cse.central_node.event.ApplicationEvent;

@Component
public class CriticalEventSubscriber implements StatementSubscriber {

    
    private static Logger LOG = LoggerFactory.getLogger(CriticalEventSubscriber.class);

   
    public String getStatement() {
        
      
		
        String crtiticalEventExpression = "select avg(cpuUsage) as avgCpu from ApplicationEvent.win:time_batch(30) where deviceId = 0 having avg(cpuUsage) > 20";
                
        return crtiticalEventExpression;
    }
    
    
    public void update(Map<String, ApplicationEvent> eventMap) {


        StringBuilder sb = new StringBuilder();
        sb.append("************************************************");
        sb.append("\n* [ALERT] : CRITICAL EVENT DETECTED BY ESPER! ");
        sb.append("\n* The CPU Usage of device 1 is above 20%; Average CPU Usage is - " + eventMap.get("avgCpu"));
        sb.append("\n**********************************************");
        System.out.println(sb.toString());
        LOG.debug(sb.toString());
    }

    
}
