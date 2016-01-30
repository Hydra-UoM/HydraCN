package com.uom.cse.central_node.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uom.cse.central_node.subscriber.StatementSubscriber;
import com.uom.cse.central_node.data_objects.Filter;
import com.uom.cse.central_node.event.ApplicationEvent;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;


@Component
@Scope(value = "singleton")
public class ProcessInfoEventHandler implements InitializingBean{

    
    private static Logger LOG = LoggerFactory.getLogger(ProcessInfoEventHandler.class);

    
    private EPServiceProvider epService;
    private EPStatement criticalEventStatement;
    private Filter filter;
    
    private StatementSubscriber criticalEventSubscriber;
    
    public ProcessInfoEventHandler(StatementSubscriber statementSubscriber) {
		this.criticalEventSubscriber = statementSubscriber;
		initService();
	}


    
    public void initService() {

        LOG.debug("Initializing Servcie ..");
        Configuration config = new Configuration();
       // config.addEventType("ApplicationEvent", ApplicationEvent.class.getName());
        config.addPlugInSingleRowFunction("exceptThis", "com.uom.cse.central_node.event.ApplicationEvent", "exceptThis");
        config.addPlugInSingleRowFunction("isAny", "com.uom.cse.central_node.event.ApplicationEvent", "isAny");
        config.addEventTypeAutoName("com.uom.cse.central_node.event");
        epService = EPServiceProviderManager.getDefaultProvider(config);
        
       // String criticalEventExpression = "select mac,cpuUsage,ramUsage,sentData,receiveData from ApplicationEvent where cpuUsage > "+ filter.getCpuUsage() +" and ramUsage > " + filter.getRamUsage() + " and sentData > " + filter.getSentData()+" and receiveData > " + filter.getReceivedData();
       
       // createCriticalEventCheckExpression(criticalEventExpression,criticalEventSubscriber);
        
    }
    
    
    public void createCriticalEventCheckExpression(String expression,StatementSubscriber subscriber) {
        
        LOG.debug("create Critical Event Check Expression");
        criticalEventStatement = epService.getEPAdministrator().createEPL(expression);
        criticalEventStatement.setSubscriber(subscriber);
        
    }

    
    public void handle(ApplicationEvent event) {
    	
        System.out.println(event.toString());
        LOG.debug(event.toString());
        epService.getEPRuntime().sendEvent(event);

    }
    
    public void afterPropertiesSet() {
        
        LOG.debug("Configuring..");
        initService();
    }
    
    public Filter getFilter() {
		return filter;
	}


    public void setFilter(Filter filter) {
		this.filter = filter;
	}


	
}
