package com.uom.cse.central_node.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uom.cse.central_node.util.LogFile;

public class LogFileTest {

	@Test
	public void testGetFilename() {
		LogFile logFile = new LogFile();
		logFile.setFilename("File Name");
		assertEquals("File Name", logFile.getFilename());
	}

	@Test
	public void testSetFilename() {
		LogFile logFile = new LogFile();
		logFile.setFilename("File Name");
		assertEquals("File Name", logFile.getFilename());
	}

	@Test
	public void testGetDeviceId() {
		LogFile logFile = new LogFile();
		logFile.setDeviceId("33:44:33:88:88:66:99");
		assertEquals("33:44:33:88:88:66:99", logFile.getDeviceId());
	}

	@Test
	public void testSetDeviceId() {
		LogFile logFile = new LogFile();
		logFile.setDeviceId("33:44:33:88:88:66:99");
		assertEquals("33:44:33:88:88:66:99", logFile.getDeviceId());
	}

	@Test
	public void testGetName() {
		LogFile logFile = new LogFile();
		logFile.setName("Device-65");
		assertEquals("Device-65", logFile.getName());
	}

	@Test
	public void testSetName() {
		LogFile logFile = new LogFile();
		logFile.setName("Device-65");
		assertEquals("Device-65", logFile.getName());
	}

	@Test
	public void testGetIpAddress() {
		LogFile logFile = new LogFile();
		logFile.setIpAddress("33:44:33:88:88:66:99");
		assertEquals("33:44:33:88:88:66:99", logFile.getIpAddress());
	}

	@Test
	public void testSetIpAddress() {
		LogFile logFile = new LogFile();
		logFile.setIpAddress("33:44:33:88:88:66:99");
		assertEquals("33:44:33:88:88:66:99", logFile.getIpAddress());
	}

}
