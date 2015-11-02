package com.uom.central_node.windows_agent_services;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.uom.central_node.android_agent_services.AndroidAgentService;
import com.uom.central_node.android_agent_services.DeviceOverallInfo;

public class ProcessStatsClient {
	public static DeviceOverallInfo getDeviceOverallInfo(String IPAddress) {
		DeviceOverallInfo overallInfo = null;
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			overallInfo = getDeviceOverallInfoFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return overallInfo;
	}
	
	private static DeviceOverallInfo getDeviceOverallInfoFromService(ProcessStats.Client client)
			throws TException {

		//List<String> overallInfo = client.recv_getAllProcesses();
		double cpuUsage = client.getTotalCPU();
		double ramUsedUsage = client.getTotalMemory();
		double networkUpload = client.getTotalNetUpload();
		double networkDownload = client.getTotalNetDownload();

		return null;
	}
}
