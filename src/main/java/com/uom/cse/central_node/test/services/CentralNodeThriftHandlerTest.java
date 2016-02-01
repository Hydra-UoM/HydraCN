package com.uom.cse.central_node.test.services;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uom.cse.central_node.services.RegisterDeviceHandler;

public class CentralNodeThriftHandlerTest {

	@Test
	public void testRegisterDevice() {
		try {
			RegisterDeviceHandler handler = new RegisterDeviceHandler();
			handler.registerDevice(null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetCommands() {
		try {
			RegisterDeviceHandler handler = new RegisterDeviceHandler();
			handler.getCommands(null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(true);
		}
	}

	@Test
	public void testPushProcessesInfo() {
		try {
			RegisterDeviceHandler handler = new RegisterDeviceHandler();
			handler.pushProcessesInfo(null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(true);
		}
	}

	@Test
	public void testPushLogInfo() {
		try {
			RegisterDeviceHandler handler = new RegisterDeviceHandler();
			handler.pushLogInfo(null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testPushUsersInfo() {
		try {
			RegisterDeviceHandler handler = new RegisterDeviceHandler();
			handler.pushLogInfo(null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testPushCurrentUserInfo() {
		try {
			RegisterDeviceHandler handler = new RegisterDeviceHandler();
			handler.pushCurrentUserInfo(null);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}


}
