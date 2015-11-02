package com.uom.central_node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.uom.cse.central_node.event.ApplicationEvent;
import com.uom.cse.central_node.eventadapter.EventGenerator;
import com.uom.cse.central_node.util.RandomSampleEventGenerator;


public class StartEsper {

    private static Logger LOG = LoggerFactory.getLogger(StartEsper.class);

    
    public static void main(String[] args) throws Exception {
    	
    	EventGenerator eventgenerator = new EventGenerator();
    	ApplicationEvent event = eventgenerator.jsonToJava();
    	System.out.println(event.getCpuUsage());

        LOG.debug("Starting...");
        System.out.println("Starting...");
        long noOfEvents = 1000;

        if (args.length != 1) {
            LOG.debug("No override of number of events detected - defaulting to " + noOfEvents + " events.");
        } else {
            noOfEvents = Long.valueOf(args[0]);
        }

        // Load spring config
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] { "application-context.xml" });
        BeanFactory factory = (BeanFactory) appContext;

        // Start Demo
        RandomSampleEventGenerator generator = (RandomSampleEventGenerator) factory.getBean("eventGenerator");
        generator.startSendingEvents(noOfEvents);
        
    }

}
