package com.uom.cse.central_node.test.dataobjects;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uom.cse.central_node.dataobjects.Filter;
import com.uom.cse.central_node.dataobjects.LogRule;
import com.uom.cse.central_node.model.WindowsLogData;

public class LogRuleTest {

	@Test
	public void testLogRule() {
		try{
			LogRule rule = new LogRule();
			assertTrue(true);
		}catch (NullPointerException ex){
			assertTrue(false);
		}
	}

	@Test
	public void testLogRuleWindowsLogData() {
		try{
			LogRule rule = new LogRule();
			assertTrue(true);
		}catch (NullPointerException ex){
			assertTrue(false);
		}
	}

	@Test
	public void testGetSecurityLevel() {
		LogRule rule = new LogRule();
		rule.setSecurityLevel("0");
		assertEquals("0", rule.getSecurityLevel());
		rule.setSecurityLevel("1");
		assertEquals("1", rule.getSecurityLevel());
		rule.setSecurityLevel("2");
		assertEquals("2", rule.getSecurityLevel());
	}

	@Test
	public void testSetSecurityLevel() {
		LogRule rule = new LogRule();
		rule.setSecurityLevel("0");
		assertEquals("0", rule.getSecurityLevel());
		rule.setSecurityLevel("1");
		assertEquals("1", rule.getSecurityLevel());
		rule.setSecurityLevel("2");
		assertEquals("2", rule.getSecurityLevel());
	}

	@Test
	public void testGetId() {
		LogRule rule = new LogRule();
		rule.setId(0);
		assertEquals(0, rule.getId());
		rule.setId(100);
		assertEquals(100, rule.getId());
		rule.setId(1000);
		assertEquals(1000, rule.getId());
	}

	@Test
	public void testSetId() {
		LogRule rule = new LogRule();
		rule.setId(0);
		assertEquals(0, rule.getId());
		rule.setId(100);
		assertEquals(100, rule.getId());
		rule.setId(1000);
		assertEquals(1000, rule.getId());
	}

	@Test
	public void testGetFilterName() {
		LogRule rule = new LogRule();
		rule.setFilterName("Filter Name");
		assertEquals("Filter Name", rule.getFilterName());
	}

	@Test
	public void testSetFilterName() {
		LogRule rule = new LogRule();
		rule.setFilterName("Filter Name");
		assertEquals("Filter Name", rule.getFilterName());
	}

	@Test
	public void testGetTimeBound() {
		LogRule rule = new LogRule();
		rule.setTimeBound("0");
		assertEquals("0", rule.getTimeBound());
		rule.setTimeBound("100");
		assertEquals("100", rule.getTimeBound());
		rule.setTimeBound("1000");
		assertEquals("1000", rule.getTimeBound());
	}

	@Test
	public void testSetTimeBound() {
		LogRule rule = new LogRule();
		rule.setTimeBound("0");
		assertEquals("0", rule.getTimeBound());
		rule.setTimeBound("100");
		assertEquals("100", rule.getTimeBound());
		rule.setTimeBound("1000");
		assertEquals("1000", rule.getTimeBound());
	}

	@Test
	public void testGetLogTypeNames() {
		LogRule rule = new LogRule();
		rule.setLogType("17,25,29,36");
		assertEquals("Logons Failures,Success Login Information,Group Policy Editors,Application Crash Events"
				, rule.getLogTypeNames());
	}

	@Test
	public void testGetLogType() {
		LogRule rule = new LogRule();
		rule.setLogType("17,25,29,36");
		assertEquals("17,25,29,36", rule.getLogType());
	}

	@Test
	public void testGetLogTypeAsList() {
		LogRule rule = new LogRule();
		rule.setLogType("17,25,29,36");
		assertEquals(4, rule.getLogTypeAsList().size());
		assertEquals("17", rule.getLogTypeAsList().get(0));
		assertEquals("25", rule.getLogTypeAsList().get(1));
		assertEquals("29", rule.getLogTypeAsList().get(2));
		assertEquals("36", rule.getLogTypeAsList().get(3));
	}

	@Test
	public void testGetLogTypeAsIntList() {
		LogRule rule = new LogRule();
		rule.setLogType("17,25,29,36");
		assertEquals(4, rule.getLogTypeAsIntList().size());
		assertEquals(17, rule.getLogTypeAsIntList().get(0).intValue());
		assertEquals(25, rule.getLogTypeAsIntList().get(1).intValue());
		assertEquals(29, rule.getLogTypeAsIntList().get(2).intValue());
		assertEquals(36, rule.getLogTypeAsIntList().get(3).intValue());
	}

	@Test
	public void testSetLogType() {
		LogRule rule = new LogRule();
		rule.setLogType("17,25,29,36");
		assertEquals("17,25,29,36", rule.getLogType());
	}

	@Test
	public void testGetSummarizationLevel() {
		LogRule rule = new LogRule();
		rule.setSummarizationLevel(0);
		assertEquals(0, rule.getSummarizationLevel());
		rule.setSummarizationLevel(1);
		assertEquals(1, rule.getSummarizationLevel());
		rule.setSummarizationLevel(2);
		assertEquals(2, rule.getSummarizationLevel());
	}

	@Test
	public void testSetSummarizationLevel() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsProcessLogEnable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetProcessLogEnable() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProcessName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetProcessName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetType() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetType() {
		fail("Not yet implemented");
	}

}
