package com.uom.cse.central_node.windowsagentservices;

import java.util.LinkedList;
import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.dataobjects.LogRule;
import com.uom.cse.central_node.model.ProcessInfo;

public class ProcessStatsClient {
	public static WindowsDeviceOverallInfo getDeviceOverallInfo(String IPAddress) {

		WindowsDeviceOverallInfo overallInfo = null;
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

	public static List<ProcessInfo> getProcessesInfo(String IPAddress) {

		List<ProcessInfo> overallInfo = new LinkedList<ProcessInfo>();
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			List<String> overallInfoStr = getProcessInfoFromService(client);

			for (int i = 0; i < overallInfoStr.size(); i++) {
				if ((i + 4) < overallInfoStr.size()) {
					String processName = overallInfoStr.get(i++);
					String cpu = overallInfoStr.get(i++);
					String usedMemory = overallInfoStr.get(i++);
					String download = overallInfoStr.get(i++);
					String upload = overallInfoStr.get(i++);
					ProcessInfo info = new ProcessInfo(processName, cpu, usedMemory, "0.0", download, upload);
					overallInfo.add(info);
				}

			}

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return overallInfo;
	}

	public static List<ProcessInfo> getProcessesInfo(String IPAddress, double cpu, double mem, double download,
			double upload, String process) {

		List<ProcessInfo> overallInfo = new LinkedList<ProcessInfo>();
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			List<String> overallInfoStr = getProcessInfoFromService(client, cpu, mem, download, upload, process);

			for (int i = 0; i < overallInfoStr.size(); i++) {
				if ((i + 4) < overallInfoStr.size()) {
					String processName = overallInfoStr.get(i++);
					String cpuStr = overallInfoStr.get(i++);
					String usedMemoryStr = overallInfoStr.get(i++);
					String downloadStr = overallInfoStr.get(i++);
					String uploadStr = overallInfoStr.get(i++);
					ProcessInfo info = new ProcessInfo(processName, cpuStr, usedMemoryStr, "0.0", downloadStr,
							uploadStr);
					overallInfo.add(info);
				}

			}

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return overallInfo;
	}

	public static List<String> getCurrentLoggedInUser(String IPAddress) {
		List<String> overallInfo = new LinkedList<String>();
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			//overallInfo = getCurrentLoggedInUserFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return overallInfo;
	}

	public static boolean getAllAvgProcessInfo(String IPAddress, Filter filter) {
		
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			getProcessAvgInfoFromService(client, filter.getCpuUsage(), filter.getRamUsage(), filter.getReceivedData()
					, filter.getSentData(), filter.getTimeBound(), filter.getProcesses());

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return true;
	}
	
	public static double getTotalCpu(String IPAddress) {
		
		double cpu = 0.0;
		
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			cpu = getTotalCpuFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return cpu;
	}
	
	public static double getTotalRam(String IPAddress){
		
		double ram = 0.0;
		
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			ram = getTotalRamFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
		
		return ram;
	}
	
	public static boolean sendWindowsLogInfo(String IPAddress, LogRule rule) {
		
		try {
			TTransport transport;

			transport = new TSocket(IPAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			getWindowsLogInfoFromService(client, Integer.parseInt(rule.getTimeBound()), rule.getSummarizationLevel(), 
					rule.getLogTypeAsList(), rule.getType(), rule.getProcessName(), rule.getSecurityLevel());

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}

		return true;
	}
	
	public static boolean stopWindowsLogInfo(String ipAddress){
		
		try {
			TTransport transport;

			transport = new TSocket(ipAddress, 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ProcessStats.Client client = new ProcessStats.Client(protocol);

			stopWindowsLogInfoFromService(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
		return true;
	}

	private static void stopWindowsLogInfoFromService(ProcessStats.Client client) {
		
		try {
			client.stopLogInfo();
			//client.getFullLogInformation((short)1);
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static WindowsDeviceOverallInfo getDeviceOverallInfoFromService(ProcessStats.Client client)
			throws TException {

		double cpuUsage = client.getTotalCPU();
		double ramUsedUsage = client.getTotalMemory();
		double networkUpload = client.getTotalNetUpload();
		double networkDownload = client.getTotalNetDownload();

		WindowsDeviceOverallInfo overallInfo = new WindowsDeviceOverallInfo();
		overallInfo.setCpuUsage(cpuUsage);
		overallInfo.setNetworkDownload(networkDownload);
		overallInfo.setNetworkUpload(networkUpload);
		overallInfo.setRamUsedUsage(ramUsedUsage);

		return overallInfo;
	}

	private static double getTotalCpuFromService(ProcessStats.Client client){
		
		double cpuUsage = 0.0;
		
		try {
			cpuUsage = client.getTotalCPU();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cpuUsage;
	}
	
	private static double getTotalRamFromService(ProcessStats.Client client){
		
		double ramUsage = 0.0;
		
		try {
			ramUsage = client.getTotalMemory();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ramUsage;
	}
	
	private static List<String> getProcessInfoFromService(ProcessStats.Client client) throws TException {

		List<String> processes = client.filterAllProcesses(0, 0, 0, 0, "");

		return processes;
	}

	private static List<String> getProcessInfoFromService(ProcessStats.Client client, double cpu, double mem,
			double download, double upload, String process) throws TException {

		List<String> processes = client.filterAllProcesses(cpu, mem, download, upload, process);

		return processes;
	}

//	private static List<String> getCurrentLoggedInUserFromService(ProcessStats.Client client) throws TException {
//
//		List<String> processes = client.getCurrentLoggedInUser();
//
//		return processes;
//	}
	
	private static boolean getProcessAvgInfoFromService(ProcessStats.Client client, double cpu, double mem,
			double download, double upload, long time, List<String> processList) throws TException {

		client.send_filterAllAvgProcesses(time, cpu, mem, download, upload, processList);
		//client.send_filterAllAvgProcesses(time, cpu, mem, download, upload, null);

		return true;
	}
	
	private static boolean getWindowsLogInfoFromService(ProcessStats.Client client, int time, int summarizationLevel,
			List<String> eventIndices, String logType, String processName, String securityLevel) throws TException {

		short timeInShort = (short)time;
		short summarizationLevelInShort = (short)summarizationLevel;
		client.getLogRelatedInformation(timeInShort, summarizationLevelInShort, eventIndices, logType, processName, securityLevel);

		return true;
	}
	
}
