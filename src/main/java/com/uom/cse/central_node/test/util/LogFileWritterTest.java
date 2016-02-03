package com.uom.cse.central_node.test.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.services.ThriftAgentProcessInfo;
import com.uom.cse.central_node.services.myLogStructure;
import com.uom.cse.central_node.services.myUserAccountDetailsStruct;
import com.uom.cse.central_node.util.LogFileWritter;

public class LogFileWritterTest {

	@Test
	public void testWriteCommandFile() {
		try {
			LogFileWritter.writeCommandFile("192.168.1.2", "USER_DEFINED_COMMAND", "Android");
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
		
		try {
			LogFileWritter.writeCommandFile(null, null, null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testWriteFileThriftAgentProcessInfoString() {
		ThriftAgentProcessInfo process = new ThriftAgentProcessInfo();
		process.setCpuUsage(100);
		process.setMac("33:33:33:33:33:33");
		process.setRamUsage(100);
		process.setReceiveData(200);
		process.setSentData(200);
		process.name = "Test Process";
		process.packageName = "Test Package";
		process.ramUsage = 200;
		process.cpuUsage = 200;
		process.sentData = 200;
		process.receiveData = 200;
		process.pid = "P100";
		process.type = "Android";
		process.mac = "33:33:33:33:33:33";
		process.URLs = new ArrayList<String>();
		process.totalReceivedData = 400;
		
		try {
			LogFileWritter.writeFile(process, "Android Device");
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
		
		try {
			LogFileWritter.writeFile(process, null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testWriteFileListOfThriftAgentProcessInfoString() {
		ThriftAgentProcessInfo process1 = new ThriftAgentProcessInfo();
		process1.setCpuUsage(100);
		process1.setMac("33:33:33:33:33:33");
		process1.setRamUsage(100);
		process1.setReceiveData(200);
		process1.setSentData(200);
		process1.name = "Test Process";
		process1.packageName = "Test Package";
		process1.ramUsage = 200;
		process1.cpuUsage = 200;
		process1.sentData = 200;
		process1.receiveData = 200;
		process1.pid = "P100";
		process1.type = "Android";
		process1.mac = "33:33:33:33:33:33";
		process1.URLs = new ArrayList<String>();
		process1.totalReceivedData = 400;
		
		ThriftAgentProcessInfo process2 = new ThriftAgentProcessInfo();
		process2.setCpuUsage(100);
		process2.setMac("33:33:33:33:33:33");
		process2.setRamUsage(100);
		process2.setReceiveData(200);
		process2.setSentData(200);
		process2.name = "Test Process";
		process2.packageName = "Test Package";
		process2.ramUsage = 200;
		process2.cpuUsage = 200;
		process2.sentData = 200;
		process2.receiveData = 200;
		process2.pid = "P100";
		process2.type = "Android";
		process2.mac = "33:33:33:33:33:33";
		process2.URLs = new ArrayList<String>();
		process2.totalReceivedData = 400;
		
		List<ThriftAgentProcessInfo> processList = new ArrayList<>();
		processList.add(process1);
		processList.add(process2);
		
		try {
			LogFileWritter.writeFile(processList, "Android Device");
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
		
		try {
			LogFileWritter.writeFile(processList, null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testWriteFileWindowsLogListOfmyLogStructureString() {
		try {
			LogFileWritter.writeFileWindowsLog(new myLogStructure(), "Device-65");
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testWriteFileWindowsLogMyLogStructureString() {
		try {
			LogFileWritter.writeFileWindowsLog(new myLogStructure(), "Device-65");
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testWriteFileMyUserAccountDetailsStruct() {
		try {
			LogFileWritter.writeFile(new myUserAccountDetailsStruct());
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testWriteFileUsersInfo() {
		try {
			LogFileWritter.writeFile(new myUserAccountDetailsStruct());
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetPerformanceDataFilenames() {
		try {
			List<String> filelist = LogFileWritter.getPerformanceDataFilenames();
			assertTrue(null != filelist);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetUserInfoFilenames() {
		try {
			List<String> filelist = LogFileWritter.getUserInfoFilenames();
			assertTrue(null != filelist);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetPerformanceFilenameDevice() {
		Device device = new Device("33:33:33:33:33:44", "192.143:1:1", "Android");
		
		try {
			LogFileWritter.getPerformanceFilename(device);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetPerformanceFilenameListOfDevice() {
		Device device1 = new Device("33:33:33:33:33:44", "192.143:1:1", "Android");
		Device device2 = new Device("33:33:33:33:33:44", "192.143:1:1", "Android");
		
		List<Device> devicelist = new ArrayList<>();
		devicelist.add(device1);
		devicelist.add(device2);
		
		try {
			LogFileWritter.getPerformanceFilename(devicelist);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

}
