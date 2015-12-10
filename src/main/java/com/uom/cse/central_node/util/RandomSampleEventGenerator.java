package com.uom.cse.central_node.util;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uom.cse.central_node.handler.ProcessInfoEventHandler;
import com.uom.cse.central_node.event.ApplicationEvent;


@Component
public class RandomSampleEventGenerator {

   
    private static Logger LOG = LoggerFactory.getLogger(RandomSampleEventGenerator.class);

    
    @Autowired
    private ProcessInfoEventHandler sampleEventHandler;

   
    public void startSendingEvents(final long noOfEvents) {
        //System.out.println(noOfEvents);
        ExecutorService xrayExecutor = Executors.newSingleThreadExecutor();

        xrayExecutor.submit(new Runnable() {
            public void run() {

                LOG.debug(getStartingMessage());
                
                int count = 0;
                while (count < noOfEvents) {
                	
                  //  ApplicationEvent ve = new ApplicationEvent((new Random().nextInt(15)+4000), (new Random().nextInt(50)),(new Random().nextInt(3)));
                 //   sampleEventHandler.handle(ve);
                   
                }

            }
        });
    }

    
    private String getStartingMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n\n************************************************************");
        sb.append("\n* STARTING - ");
        sb.append("\n* PLEASE WAIT - EVENTS ARE RANDOM SO MAY TAKE");
        sb.append("\n* A WHILE TO SEE WARNING AND CRITICAL EVENTS!");
        sb.append("\n************************************************************\n");
        return sb.toString();
    }
}
