package com.uom.cse.central_node.test.androidagentservices;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uom.cse.central_node.androidagentservices.AndroidAgentServiceClient;

public class AndroidAgentServiceClientTest {
	
	private static String DEVICE_IP = "0.0.0.0";
	
	@Test
	public void testGetRunningProcess() {
		try{
			AndroidAgentServiceClient.getRunningProcess(DEVICE_IP);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetRAMUsageInfo() {
		try{
			AndroidAgentServiceClient.getRAMUsageInfo(DEVICE_IP);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetCPUUsageInfo() {
		try{
			AndroidAgentServiceClient.getCPUUsageInfo(DEVICE_IP);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetDeviceOverallInfo() {
		try{
			AndroidAgentServiceClient.getDeviceOverallInfo(DEVICE_IP);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetAllRunningProcessWithInfoString() {
		try{
			AndroidAgentServiceClient.getAllRunningProcessWithInfo(DEVICE_IP);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetAllRunningProcessWithInfoStringStringStringString() {
		try{
			AndroidAgentServiceClient.getAllRunningProcessWithInfo(DEVICE_IP);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetSensorDetails() {
		try{
			AndroidAgentServiceClient.getSensorDetails(DEVICE_IP);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testGetNetworkBandwidth() {
		try{
			AndroidAgentServiceClient.getNetworkBandwidth(DEVICE_IP);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testDeployCommand() {
		try{
			AndroidAgentServiceClient.deployCommand(DEVICE_IP, null);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

}
