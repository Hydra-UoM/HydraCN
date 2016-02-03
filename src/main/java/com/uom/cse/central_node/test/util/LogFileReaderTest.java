package com.uom.cse.central_node.test.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.uom.cse.central_node.model.Device;
import com.uom.cse.central_node.util.LogFileReader;

public class LogFileReaderTest {

	@Test
	public void testReadProcessInfoDataDevice() {
		Device device = new Device("33:33:33:33:33:44", "192.143:1:1", "Android");
		
		try {
			LogFileReader.readProcessInfoData(device);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
		
	}

	@Test
	public void testReadProcessInfoDataListOfDevice() {
		Device device1 = new Device("33:33:33:33:33:44", "192.143:1:1", "Android");
		Device device2 = new Device("33:33:33:33:33:44", "192.143:1:1", "Android");
		
		List<Device> devicelist = new ArrayList<>();
		devicelist.add(device1);
		devicelist.add(device2);
		
		try {
			LogFileReader.readProcessInfoData(devicelist);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testReadUserInfoData() {
		Device device = new Device("33:33:33:33:33:44", "192.143:1:1", "Android");
		
		try {
			LogFileReader.readUserInfoData(device);
			assertTrue(true);
		} catch (Exception ex) {
			assertTrue(false);
		}
		
	}

}
