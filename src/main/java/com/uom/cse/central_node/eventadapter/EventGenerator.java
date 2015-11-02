package com.uom.cse.central_node.eventadapter;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.google.gson.*;
import com.uom.cse.central_node.event.ApplicationEvent; 

public class EventGenerator {
	
	public ApplicationEvent jsonToJava() throws UnsupportedEncodingException{
		
		Reader reader = new InputStreamReader(EventGenerator.class.getResourceAsStream("/event.json"), "UTF-8");
		Gson gson = new GsonBuilder().create();
		ApplicationEvent event = gson.fromJson(reader, ApplicationEvent.class);
		
		return event;
		
	}
	
	
}
