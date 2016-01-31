package com.uom.cse.central_node.test.dataobjects;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.model.FilterData;

public class FilterTest {

	@Test
	public void testFilter() {
		try{
			Filter filter = new Filter();
			assertTrue(true);
		}catch (NullPointerException ex){
			assertTrue(false);
		}
	}
	
	@Test
	public void testFilterFilterData() {
		try{
			Filter filter = new Filter(new FilterData(new Filter()));
			assertTrue(true);
		}catch (NullPointerException ex){
			assertTrue(false);
		}
	}

	@Test
	public void testGetFilterName() {
		Filter filter = new Filter();
		filter.setFilterName("FilterName");
		assertEquals("FilterName", filter.getFilterName());
		filter.setFilterName("Filter Name");
		assertEquals("Filter Name", filter.getFilterName());
	}

	@Test
	public void testSetFilterName() {
		Filter filter = new Filter();
		filter.setFilterName("FilterName");
		assertEquals("FilterName", filter.getFilterName());
		filter.setFilterName("Filter Name");
		assertEquals("Filter Name", filter.getFilterName());
	}

	@Test
	public void testGetTimeBound() {
		Filter filter = new Filter();
		filter.setTimeBound(0);
		assertEquals(0, filter.getTimeBound());
		filter.setTimeBound(10);
		assertEquals(10, filter.getTimeBound());
		filter.setTimeBound(1000);
		assertEquals(1000, filter.getTimeBound());
	}

	@Test
	public void testSetTimeBound() {
		Filter filter = new Filter();
		filter.setTimeBound(0);
		assertEquals(0, filter.getTimeBound());
		filter.setTimeBound(10);
		assertEquals(10, filter.getTimeBound());
		filter.setTimeBound(1000);
		assertEquals(1000, filter.getTimeBound());
	}

	@Test
	public void testGetEventIdStr() {
		Filter filter = new Filter();
		filter.setEventIdStr("0,1,13,15");
		assertEquals("0,1,13,15", filter.getEventIdStr());
	}

	@Test
	public void testGetEventId() {
		Filter filter = new Filter();
		filter.setEventIdStr("0,1,13,15");
		assertEquals(4, filter.getEventId().size());
		assertEquals(0, filter.getEventId().get(0).intValue());
		assertEquals(1, filter.getEventId().get(1).intValue());
		assertEquals(13, filter.getEventId().get(2).intValue());
		assertEquals(15, filter.getEventId().get(3).intValue());
	}

	@Test
	public void testSetEventIdStr() {
		Filter filter = new Filter();
		filter.setEventIdStr("0,1,13,15");
		assertEquals("0,1,13,15", filter.getEventIdStr());
	}

	@Test
	public void testGetMessage() {
		Filter filter = new Filter();
		filter.setMessage("Alert For Rule");
		assertEquals("Alert For Rule", filter.getMessage());
	}

	@Test
	public void testSetMessage() {
		Filter filter = new Filter();
		filter.setMessage("Alert For Rule");
		assertEquals("Alert For Rule", filter.getMessage());
	}

	@Test
	public void testGetReturnValue() {
		Filter filter = new Filter();
		filter.setReturnValue ("Return Value");
		assertEquals("Return Value", filter.getReturnValue());
	}

	@Test
	public void testSetReturnValue() {
		Filter filter = new Filter();
		filter.setReturnValue ("Return Value");
		assertEquals("Return Value", filter.getReturnValue());
	}

	@Test
	public void testGetId() {
		Filter filter = new Filter();
		filter.setId(0);
		assertEquals(0, filter.getId());
		filter.setId(10);
		assertEquals(10, filter.getId());
		filter.setId(1000);
		assertEquals(1000, filter.getId());
	}

	@Test
	public void testSetId() {
		Filter filter = new Filter();
		filter.setId(0);
		assertEquals(0, filter.getId());
		filter.setId(10);
		assertEquals(10, filter.getId());
		filter.setId(1000);
		assertEquals(1000, filter.getId());
	}

	@Test
	public void testGetCpuUsage() {
		Filter filter = new Filter();
		filter.setCpuUsage(0);
		assertEquals(0, filter.getCpuUsage(), 0.0001);
		filter.setCpuUsage(10);
		assertEquals(10, filter.getCpuUsage(), 0.0001);
		filter.setCpuUsage(100);
		assertEquals(100, filter.getCpuUsage(), 0.0001);
	}

	@Test
	public void testSetCpuUsage() {
		Filter filter = new Filter();
		filter.setCpuUsage(0);
		assertEquals(0, filter.getCpuUsage(), 0.0001);
		filter.setCpuUsage(10);
		assertEquals(10, filter.getCpuUsage(), 0.0001);
		filter.setCpuUsage(100);
		assertEquals(100, filter.getCpuUsage(), 0.0001);
	}

	@Test
	public void testGetRamUsage() {
		Filter filter = new Filter();
		filter.setRamUsage(0);
		assertEquals(0, filter.getRamUsage(), 0.0001);
		filter.setRamUsage(10);
		assertEquals(10, filter.getRamUsage(), 0.0001);
		filter.setRamUsage(100);
		assertEquals(100, filter.getRamUsage(), 0.0001);
	}

	@Test
	public void testSetRamUsage() {
		Filter filter = new Filter();
		filter.setRamUsage(0);
		assertEquals(0, filter.getRamUsage(), 0.0001);
		filter.setRamUsage(100);
		assertEquals(100, filter.getRamUsage(), 0.0001);
		filter.setRamUsage(10000);
		assertEquals(10000, filter.getRamUsage(), 0.0001);
	}

	@Test
	public void testGetReceivedData() {
		Filter filter = new Filter();
		filter.setReceivedData(0);
		assertEquals(0, filter.getReceivedData(), 0.0001);
		filter.setReceivedData(100);
		assertEquals(100, filter.getReceivedData(), 0.0001);
		filter.setReceivedData(10000);
		assertEquals(10000, filter.getReceivedData(), 0.0001);
	}

	@Test
	public void testSetReceivedData() {
		Filter filter = new Filter();
		filter.setReceivedData(0);
		assertEquals(0, filter.getReceivedData(), 0.0001);
		filter.setReceivedData(100);
		assertEquals(100, filter.getReceivedData(), 0.0001);
		filter.setReceivedData(10000);
		assertEquals(10000, filter.getReceivedData(), 0.0001);
	}

	@Test
	public void testGetSentData() {
		Filter filter = new Filter();
		filter.setSentData(0);
		assertEquals(0, filter.getSentData(), 0.0001);
		filter.setSentData(100);
		assertEquals(100, filter.getSentData(), 0.0001);
		filter.setSentData(10000);
		assertEquals(10000, filter.getSentData(), 0.0001);
	}

	@Test
	public void testSetSentData() {
		Filter filter = new Filter();
		filter.setSentData(0);
		assertEquals(0, filter.getSentData(), 0.0001);
		filter.setSentData(100);
		assertEquals(100, filter.getSentData(), 0.0001);
		filter.setSentData(10000);
		assertEquals(10000, filter.getSentData(), 0.0001);
	}

	@Test
	public void testGetProcesses() {
		Filter filter = new Filter();
		filter.setProcesses("WINWORD, WINPOINT, WINEXCEL");
		assertEquals(3, filter.getProcesses().size());
		assertEquals("WINWORD", filter.getProcesses().get(0));
		assertEquals(" WINPOINT", filter.getProcesses().get(1));
		assertEquals(" WINEXCEL", filter.getProcesses().get(2));
	}

	@Test
	public void testGetProcessesAsArray() {
		Filter filter = new Filter();
		filter.setProcesses("WINWORD, WINPOINT, WINEXCEL");
		assertEquals(3, filter.getProcessesAsArray().length);
		assertEquals("WINWORD", filter.getProcessesAsArray()[0]);
		assertEquals(" WINPOINT", filter.getProcessesAsArray()[1]);
		assertEquals(" WINEXCEL", filter.getProcessesAsArray()[2]);
	}

	@Test
	public void testSetProcesses() {
		Filter filter = new Filter();
		filter.setProcesses("WINWORD, WINPOINT, WINEXCEL");
		assertEquals(3, filter.getProcesses().size());
		assertEquals("WINWORD", filter.getProcesses().get(0));
		assertEquals(" WINPOINT", filter.getProcesses().get(1));
		assertEquals(" WINEXCEL", filter.getProcesses().get(2));
	}

	@Test
	public void testGetProcessesStr() {
		Filter filter = new Filter();
		filter.setProcesses("WINWORD, WINPOINT, WINEXCEL");
		assertEquals("WINWORD, WINPOINT, WINEXCEL", filter.getProcessesStr());
	}

	@Test
	public void testGetProcessesToQuery() {
		Filter filter = new Filter();
		filter.setProcesses("WINWORD, WINPOINT, WINEXCEL");
		assertEquals("'WINWORD',' WINPOINT',' WINEXCEL'", filter.getProcessesToQuery());
	}

}
