package com.uom.cse.central_node.test.windowsagentservices;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uom.cse.central_node.windowsagentservices.ProcessStatsClient;

public class ProcessStatsClientTest {

	private static String DEVICE_IP = "0.0.0.0";
	
	@Test
	public void testGetDeviceOverallInfo() {
		try {
			ProcessStatsClient.getDeviceOverallInfo(DEVICE_IP);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetProcessesInfoString() {
		try {
			ProcessStatsClient.getProcessesInfo(DEVICE_IP);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetProcessesInfoStringDoubleDoubleDoubleDoubleString() {
		try {
			ProcessStatsClient.getProcessesInfo(DEVICE_IP);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetCurrentLoggedInUser() {
		try {
			ProcessStatsClient.getCurrentLoggedInUser(DEVICE_IP);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetAllAvgProcessInfo() {
		try {
			ProcessStatsClient.getAllAvgProcessInfo (DEVICE_IP, null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetTotalCpu() {
		try {
			ProcessStatsClient.getTotalCpu(DEVICE_IP);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetTotalRam() {
		try {
			ProcessStatsClient.getTotalRam(DEVICE_IP);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testSendWindowsLogInfo() {
		try {
			ProcessStatsClient.sendWindowsLogInfo (DEVICE_IP, null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testStopWindowsLogInfo() {
		try {
			ProcessStatsClient.stopWindowsLogInfo(DEVICE_IP);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

}
