package com.uom.central_node.android_agent_services;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class AndroidAgentServiceClient {
	public static List<TProcessInfo> getRunningProcess(String IPAddress) {
		List<TProcessInfo> processes = null;
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			AndroidAgentService.Client client = new AndroidAgentService.Client(protocol);

			processes = getAllRunningProcessFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return processes;
	}

	public static List<TProcessInfo> getRAMUsageInfo(String IPAddress) {
		List<TProcessInfo> processes = null;
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			AndroidAgentService.Client client = new AndroidAgentService.Client(protocol);

			processes = getRAMUsageFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return processes;
	}

	public static List<TProcessInfo> getCPUUsageInfo(String IPAddress) {
		List<TProcessInfo> processes = null;
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			AndroidAgentService.Client client = new AndroidAgentService.Client(protocol);

			processes = getCPUUsageFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return processes;
	}

	public static DeviceOverallInfo getDeviceOverallInfo(String IPAddress) {
		DeviceOverallInfo overallInfo = null;
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			AndroidAgentService.Client client = new AndroidAgentService.Client(protocol);

			overallInfo = getDeviceOverallInfoFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return overallInfo;
	}
	
	public static List<TProcessInfo> getAllRunningProcessWithInfo(String IPAddress) {
		List<TProcessInfo> info = null;
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			AndroidAgentService.Client client = new AndroidAgentService.Client(protocol);

			info = getAllRunningProcessesWithInfoFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return info;
	}

	private static List<TProcessInfo> getAllRunningProcessFromService(AndroidAgentService.Client client)
			throws TException {

		List<TProcessInfo> processes = client.getAllRunningProcesses();

		return processes;
	}

	private static List<TProcessInfo> getRAMUsageFromService(AndroidAgentService.Client client) throws TException {

		List<TProcessInfo> processes = client.getRAMUsageInfo();

		return processes;
	}

	private static List<TProcessInfo> getCPUUsageFromService(AndroidAgentService.Client client) throws TException {

		List<TProcessInfo> processes = client.getCPUUsageInfo();

		return processes;
	}

	private static DeviceOverallInfo getDeviceOverallInfoFromService(AndroidAgentService.Client client)
			throws TException {

		DeviceOverallInfo overallInfo = client.getOverallBasicInfo();

		return overallInfo;
	}
	
	private static List<TProcessInfo> getAllRunningProcessesWithInfoFromService(AndroidAgentService.Client client)
			throws TException {
	
		List<TProcessInfo> processes = client.getAllRunningProcessesWithInfo();
		
		return processes;
	}
	
}
