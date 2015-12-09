package com.uom.cse.central_node.android_agent_services;

import java.util.Date;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.uom.cse.central_node.android_agent_services.AndroidAgentService.Processor.testNetwork;

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

	public static List<TProcessInfo> getAllRunningProcessWithInfo(String IPAddress, String cpu, String ram,
			String process) {
		List<TProcessInfo> info = null;
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			AndroidAgentService.Client client = new AndroidAgentService.Client(protocol);

			info = getAllRunningProcessesWithInfoFromService(client, cpu, ram, process);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return info;
	}

	public static List<SensorDetails> getSensorDetails(String IPAddress) {
		List<SensorDetails> processes = null;
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			AndroidAgentService.Client client = new AndroidAgentService.Client(protocol);

			processes = getSensorDetailsFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return processes;
	}

	public static float getNetworkBandwidth(String IPAddress) {
		float processes = -1.0f;
		
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			AndroidAgentService.Client client = new AndroidAgentService.Client(protocol);

			processes = testNetworkFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return processes;
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

	private static List<TProcessInfo> getAllRunningProcessesWithInfoFromService(AndroidAgentService.Client client,
			String cpu, String ram, String process) throws TException {

		List<TProcessInfo> processes = client.getFilteredProcessInfo(cpu + "", ram + "", process + "");
		// List<TProcessInfo> processes =
		// client.getAllRunningProcessesWithInfo();

		return processes;
	}

	private static List<SensorDetails> getSensorDetailsFromService(AndroidAgentService.Client client)
			throws TException {

		List<SensorDetails> processes = client.getSensorDetails();

		return processes;
	}

	private static float testNetworkFromService(AndroidAgentService.Client client) throws TException {

		java.nio.ByteBuffer buff = java.nio.ByteBuffer.allocate(1024);
		int sizeOfBytes = 100;

		long totalDiff = 0;
		for (int i = 0; i < sizeOfBytes; i++) {
			Date d1 = new Date();
			boolean response = client.testNetwork(buff);
			Date d2 = new Date();
			long diff = (d2.getTime() - d1.getTime());
			totalDiff += diff;
		}
		
		float bandwidth = sizeOfBytes * 1.0f / totalDiff; // kB/milliSec
		return bandwidth * 1000;
	}

}
