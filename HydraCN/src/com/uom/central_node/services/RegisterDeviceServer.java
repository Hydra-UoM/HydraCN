package com.uom.central_node.services;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.uom.central_node.HydraCN;
import com.uom.central_node.model.Device;

import org.apache.thrift.server.TServer.Args;

public class RegisterDeviceServer extends Thread {

	public static RegisterDeviceHandler handler;
	public static RegisterDeviceService.Processor processor;
	
	@Override
	public void run() {
		try {
			handler = new RegisterDeviceHandler();
			processor = new RegisterDeviceService.Processor(handler);
			simple(processor);

		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	public static void simple(RegisterDeviceService.Processor processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9091);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			
			server.serve();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
