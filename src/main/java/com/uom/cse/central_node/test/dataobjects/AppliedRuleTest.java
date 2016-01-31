package com.uom.cse.central_node.test.dataobjects;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uom.cse.central_node.dataobjects.AppliedRule;

public class AppliedRuleTest {
	
	@Test
	public void testGetId() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setId(1);
		assertEquals(1, appliedRule.getId(), 0);
		appliedRule.setId(100);
		assertEquals(100, appliedRule.getId(), 0);
		appliedRule.setId(1000);
		assertEquals(1000, appliedRule.getId(), 0);
		appliedRule.setId(10000);
		assertEquals(10000, appliedRule.getId(), 0);
	}

	@Test
	public void testSetId() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setId(1);
		assertEquals(1, appliedRule.getId(), 0);
		appliedRule.setId(100);
		assertEquals(100, appliedRule.getId(), 0);
		appliedRule.setId(1000);
		assertEquals(1000, appliedRule.getId(), 0);
		appliedRule.setId(10000);
		assertEquals(10000, appliedRule.getId(), 0);
	}

	@Test
	public void testGetTargetType() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setTargetType("Android");
		assertEquals("Android", appliedRule.getTargetType());
		appliedRule.setTargetType("Windows");
		assertEquals("Windows", appliedRule.getTargetType());
	}

	@Test
	public void testSetTargetType() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setTargetType("Android");
		assertEquals("Android", appliedRule.getTargetType());
		appliedRule.setTargetType("Windows");
		assertEquals("Windows", appliedRule.getTargetType());
	}

	@Test
	public void testGetRuleType() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setRuleType("Performance Log");
		assertEquals("Performance Log", appliedRule.getRuleType());
		appliedRule.setRuleType("Windows Log");
		assertEquals("Windows Log", appliedRule.getRuleType());
	}

	@Test
	public void testSetRuleType() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setRuleType("Performance Log");
		assertEquals("Performance Log", appliedRule.getRuleType());
		appliedRule.setRuleType("Windows Log");
		assertEquals("Windows Log", appliedRule.getRuleType());
	}

	@Test
	public void testGetRuleId() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setRuleId(1);
		assertEquals(1, appliedRule.getRuleId(), 0);
		appliedRule.setRuleId(100);
		assertEquals(100, appliedRule.getRuleId(), 0);
		appliedRule.setRuleId(1000);
		assertEquals(1000, appliedRule.getRuleId(), 0);
		appliedRule.setRuleId(10000);
		assertEquals(10000, appliedRule.getRuleId(), 0);
	}

	@Test
	public void testSetRuleId() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setRuleId(1);
		assertEquals(1, appliedRule.getRuleId(), 0);
		appliedRule.setRuleId(100);
		assertEquals(100, appliedRule.getRuleId(), 0);
		appliedRule.setRuleId(1000);
		assertEquals(1000, appliedRule.getRuleId(), 0);
		appliedRule.setRuleId(10000);
		assertEquals(10000, appliedRule.getRuleId(), 0);
	}

	@Test
	public void testGetGroupId() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setGroupId(1);
		assertEquals(1, appliedRule.getGroupId(), 0);
		appliedRule.setGroupId(100);
		assertEquals(100, appliedRule.getGroupId(), 0);
		appliedRule.setGroupId(1000);
		assertEquals(1000, appliedRule.getGroupId(), 0);
		appliedRule.setGroupId(10000);
		assertEquals(10000, appliedRule.getGroupId(), 0);
	}

	@Test
	public void testSetGroupId() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setGroupId(1);
		assertEquals(1, appliedRule.getGroupId(), 0);
		appliedRule.setGroupId(100);
		assertEquals(100, appliedRule.getGroupId(), 0);
		appliedRule.setGroupId(1000);
		assertEquals(1000, appliedRule.getGroupId(), 0);
		appliedRule.setGroupId(10000);
		assertEquals(10000, appliedRule.getGroupId(), 0);
	}

	@Test
	public void testGetMac() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setMac("74:86:7a:4d:ce:96");
		assertEquals("74:86:7a:4d:ce:96", appliedRule.getMac());
		appliedRule.setMac("84:38:38:a1:79:87");
		assertEquals("84:38:38:a1:79:87", appliedRule.getMac());
	}

	@Test
	public void testSetMac() {
		AppliedRule appliedRule = new AppliedRule();
		appliedRule.setMac("74:86:7a:4d:ce:96");
		assertEquals("74:86:7a:4d:ce:96", appliedRule.getMac());
		appliedRule.setMac("84:38:38:a1:79:87");
		assertEquals("84:38:38:a1:79:87", appliedRule.getMac());
	}

}
