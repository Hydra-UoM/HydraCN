package com.uom.cse.central_node.services;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import org.apache.thrift.server.TServer.Args;

public class RegisterDeviceServer extends Thread {

	public static RegisterDeviceHandler handler;
	public static RegisterDeviceService.Processor processor;
	private static TServer server;
	
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
			server = new TSimpleServer(new Args(serverTransport).processor(processor));
			
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void stopServer() {
		try {
			server.stop();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
