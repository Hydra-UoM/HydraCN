package com.uom.cse.central_node.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uom.cse.central_node.subscriber.StatementSubscriber;
import com.uom.cse.central_node.event.ApplicationEvent;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;


@Component
@Scope(value = "singleton")
public class SampleEventHandler implements InitializingBean{

    
    private static Logger LOG = LoggerFactory.getLogger(SampleEventHandler.class);

    
    private EPServiceProvider epService;
    private EPStatement criticalEventStatement;
    

    @Autowired
    @Qualifier("criticalEventSubscriber")
    private StatementSubscriber criticalEventSubscriber;


    
    public void initService() {

        LOG.debug("Initializing Servcie ..");
        Configuration config = new Configuration();
        config.addEventTypeAutoName("com.uom.cse.central_node.event");
        epService = EPServiceProviderManager.getDefaultProvider(config);

        createCriticalEventCheckExpression();
        
    }

    
    private void createCriticalEventCheckExpression() {
        
        LOG.debug("create Critical Event Check Expression");
        criticalEventStatement = epService.getEPAdministrator().createEPL(criticalEventSubscriber.getStatement());
        criticalEventStatement.setSubscriber(criticalEventSubscriber);
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
}
