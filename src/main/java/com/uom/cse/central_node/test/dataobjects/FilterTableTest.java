package com.uom.cse.central_node.test.dataobjects;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.dataobjects.FilterTable;

public class FilterTableTest {

	@Test
	public void testInsertFilter() {
		Filter filter = new Filter();
		filter.setCpuUsage(10.0);
		filter.setEventIdStr("17,25");
		filter.setFilterName("Test Filter");
		filter.setMessage("Test Filter Alert");
		filter.setProcesses("Process1,process2");
		filter.setRamUsage(100.0);
		filter.setReceivedData(10.0);
		filter.setSentData(10.0);
		
		int filterId = FilterTable.insertFilter(filter);
		
		Filter actualFilter = FilterTable.getFilter(filterId);
		
		assertEquals(filter.getCpuUsage(), actualFilter.getCpuUsage(), 0);
		
		assertEquals(filter.getEventIdStr(), actualFilter.getEventIdStr());
		
		assertEquals(filter.getFilterName(), actualFilter.getFilterName());
		
		assertEquals(filter.getMessage(), actualFilter.getMessage());
		
		assertEquals(filter.getProcessesStr(), actualFilter.getProcessesStr());
		
		assertEquals(filter.getRamUsage(), actualFilter.getRamUsage(), 0);
		
		assertEquals(filter.getReceivedData(), actualFilter.getReceivedData(), 0);
		
		assertEquals(filter.getSentData(), actualFilter.getSentData(), 0);
	}

	@Test
	public void testApplyFilter() {
		Filter filter = new Filter();
		filter.setCpuUsage(10.0);
		filter.setEventIdStr("17,25");
		filter.setFilterName("Test Filter");
		filter.setMessage("Test Filter Alert");
		filter.setProcesses("Process1,process2");
		filter.setRamUsage(100.0);
		filter.setReceivedData(10.0);
		filter.setSentData(10.0);
		
		int filterId = FilterTable.insertFilter(filter);
		
		FilterTable.applyFilter(filterId);
		
		Filter actualFilter = FilterTable.getAppliedFilter();
		
		assertEquals(filter.getCpuUsage(), actualFilter.getCpuUsage(), 0);
		
		assertEquals(filter.getEventIdStr(), actualFilter.getEventIdStr());
		
		assertEquals(filter.getFilterName(), actualFilter.getFilterName());
		
		assertEquals(filter.getMessage(), actualFilter.getMessage());
		
		assertEquals(filter.getProcessesStr(), actualFilter.getProcessesStr());
		
		assertEquals(filter.getRamUsage(), actualFilter.getRamUsage(), 0);
		
		assertEquals(filter.getReceivedData(), actualFilter.getReceivedData(), 0);
		
		assertEquals(filter.getSentData(), actualFilter.getSentData(), 0);
		
		FilterTable.disableAllFilter();
	}

	@Test
	public void testDisableAllFilter() {
		FilterTable.disableAllFilter();
		
		Filter actualFilter = FilterTable.getAppliedFilter();
		
		assertEquals(null, actualFilter);
	}

	@Test
	public void testGetAppliedFilter() {
		Filter filter = new Filter();
		filter.setCpuUsage(10.0);
		filter.setEventIdStr("17,25");
		filter.setFilterName("Test Filter");
		filter.setMessage("Test Filter Alert");
		filter.setProcesses("Process1,process2");
		filter.setRamUsage(100.0);
		filter.setReceivedData(10.0);
		filter.setSentData(10.0);
		
		int filterId = FilterTable.insertFilter(filter);
		
		FilterTable.applyFilter(filterId);
		
		Filter actualFilter = FilterTable.getAppliedFilter();
		
		assertEquals(filter.getCpuUsage(), actualFilter.getCpuUsage(), 0);
		
		assertEquals(filter.getEventIdStr(), actualFilter.getEventIdStr());
		
		assertEquals(filter.getFilterName(), actualFilter.getFilterName());
		
		assertEquals(filter.getMessage(), actualFilter.getMessage());
		
		assertEquals(filter.getProcessesStr(), actualFilter.getProcessesStr());
		
		assertEquals(filter.getRamUsage(), actualFilter.getRamUsage(), 0);
		
		assertEquals(filter.getReceivedData(), actualFilter.getReceivedData(), 0);
		
		assertEquals(filter.getSentData(), actualFilter.getSentData(), 0);
		
		FilterTable.disableAllFilter();
	}

	@Test
	public void testGetFilter() {
		Filter filter = new Filter();
		filter.setCpuUsage(10.0);
		filter.setEventIdStr("17,25");
		filter.setFilterName("Test Filter");
		filter.setMessage("Test Filter Alert");
		filter.setProcesses("Process1,process2");
		filter.setRamUsage(100.0);
		filter.setReceivedData(10.0);
		filter.setSentData(10.0);
		
		int filterId = FilterTable.insertFilter(filter);
		
		Filter actualFilter = FilterTable.getFilter(filterId);
		
		assertEquals(filter.getCpuUsage(), actualFilter.getCpuUsage(), 0);
		
		assertEquals(filter.getEventIdStr(), actualFilter.getEventIdStr());
		
		assertEquals(filter.getFilterName(), actualFilter.getFilterName());
		
		assertEquals(filter.getMessage(), actualFilter.getMessage());
		
		assertEquals(filter.getProcessesStr(), actualFilter.getProcessesStr());
		
		assertEquals(filter.getRamUsage(), actualFilter.getRamUsage(), 0);
		
		assertEquals(filter.getReceivedData(), actualFilter.getReceivedData(), 0);
		
		assertEquals(filter.getSentData(), actualFilter.getSentData(), 0);
	}

	@Test
	public void testGetAllFilters() {
		Filter filter = new Filter();
		filter.setCpuUsage(10.0);
		filter.setEventIdStr("17,25");
		filter.setFilterName("Test Filter");
		filter.setMessage("Test Filter Alert");
		filter.setProcesses("Process1,process2");
		filter.setRamUsage(100.0);
		filter.setReceivedData(10.0);
		filter.setSentData(10.0);
		
		FilterTable.insertFilter(filter);
		
		List<Filter> allFilters = FilterTable.getAllFilters();
		
		assertTrue(allFilters.size() >= 1);
	}

}
