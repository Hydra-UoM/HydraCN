package com.uom.cse.central_node.test.dataobjects;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.uom.cse.central_node.dataobjects.LogRule;
import com.uom.cse.central_node.dataobjects.LogRuleTable;

public class LogRuleTableTest {

	@Test
	public void testInsertRule() {
		LogRule rule = new LogRule();
		rule.setFilterName("Test Log Rule");
		rule.setLogType("Security");
		rule.setProcessLogEnable(false);
		rule.setProcessName("Test Process Name");
		rule.setSecurityLevel("0");
		rule.setSummarizationLevel(0);
		rule.setTimeBound("10");
		rule.setType("Warning");
		
		int ruleId = LogRuleTable.insertRule(rule);
		
		LogRuleTable.applyRule(ruleId);
		
		LogRule actualRule = LogRuleTable.getAppliedRule();
		
		assertEquals(rule.getFilterName(), actualRule.getFilterName());
		
		assertEquals(rule.getLogType(), actualRule.getLogType());
		
		assertEquals(rule.isProcessLogEnable(), actualRule.isProcessLogEnable());
		
		assertEquals(rule.getProcessName(), actualRule.getProcessName());
		
		assertEquals(rule.getSecurityLevel(), actualRule.getSecurityLevel());
		
		assertEquals(rule.getSummarizationLevel(), actualRule.getSummarizationLevel());
		
		assertEquals(rule.getTimeBound(), actualRule.getTimeBound());
		
		assertEquals(rule.getType(), actualRule.getType());
		
		LogRuleTable.disableAllRule();
	}

	@Test
	public void testApplyRule() {
		LogRule rule = new LogRule();
		rule.setFilterName("Test Log Rule");
		rule.setLogType("Security");
		rule.setProcessLogEnable(false);
		rule.setProcessName("Test Process Name");
		rule.setSecurityLevel("0");
		rule.setSummarizationLevel(0);
		rule.setTimeBound("10");
		rule.setType("Warning");
		
		int ruleId = LogRuleTable.insertRule(rule);
		
		LogRuleTable.applyRule(ruleId);
		
		LogRule actualRule = LogRuleTable.getAppliedRule();
		
		assertEquals(rule.getFilterName(), actualRule.getFilterName());
		
		assertEquals(rule.getLogType(), actualRule.getLogType());
		
		assertEquals(rule.isProcessLogEnable(), actualRule.isProcessLogEnable());
		
		assertEquals(rule.getProcessName(), actualRule.getProcessName());
		
		assertEquals(rule.getSecurityLevel(), actualRule.getSecurityLevel());
		
		assertEquals(rule.getSummarizationLevel(), actualRule.getSummarizationLevel());
		
		assertEquals(rule.getTimeBound(), actualRule.getTimeBound());
		
		assertEquals(rule.getType(), actualRule.getType());
		
		LogRuleTable.disableAllRule();
	}

	@Test
	public void testDisableAllRule() {
		LogRule rule = new LogRule();
		rule.setFilterName("Test Log Rule");
		rule.setLogType("Security");
		rule.setProcessLogEnable(false);
		rule.setProcessName("Test Process Name");
		rule.setSecurityLevel("0");
		rule.setSummarizationLevel(0);
		rule.setTimeBound("10");
		rule.setType("Warning");
		
		int ruleId = LogRuleTable.insertRule(rule);
		
		LogRuleTable.applyRule(ruleId);
		
		LogRuleTable.disableAllRule();
		
		LogRule actualRule = LogRuleTable.getAppliedRule();
		
		assertEquals(null, actualRule);
	}

	@Test
	public void testGetAppliedRule() {
		LogRule rule = new LogRule();
		rule.setFilterName("Test Log Rule");
		rule.setLogType("Security");
		rule.setProcessLogEnable(false);
		rule.setProcessName("Test Process Name");
		rule.setSecurityLevel("0");
		rule.setSummarizationLevel(0);
		rule.setTimeBound("10");
		rule.setType("Warning");
		
		int ruleId = LogRuleTable.insertRule(rule);
		
		LogRuleTable.applyRule(ruleId);
		
		LogRule actualRule = LogRuleTable.getAppliedRule();
		
		assertEquals(rule.getFilterName(), actualRule.getFilterName());
		
		assertEquals(rule.getLogType(), actualRule.getLogType());
		
		assertEquals(rule.isProcessLogEnable(), actualRule.isProcessLogEnable());
		
		assertEquals(rule.getProcessName(), actualRule.getProcessName());
		
		assertEquals(rule.getSecurityLevel(), actualRule.getSecurityLevel());
		
		assertEquals(rule.getSummarizationLevel(), actualRule.getSummarizationLevel());
		
		assertEquals(rule.getTimeBound(), actualRule.getTimeBound());
		
		assertEquals(rule.getType(), actualRule.getType());
		
		LogRuleTable.disableAllRule();
	}

	@Test
	public void testGetAllLogRules() {
		LogRule rule = new LogRule();
		rule.setFilterName("Test Log Rule");
		rule.setLogType("Security");
		rule.setProcessLogEnable(false);
		rule.setProcessName("Test Process Name");
		rule.setSecurityLevel("0");
		rule.setSummarizationLevel(0);
		rule.setTimeBound("10");
		rule.setType("Warning");
		
		LogRuleTable.insertRule(rule);
		
		List<LogRule> ruleList = LogRuleTable.getAllLogRules();
		
		assertTrue(ruleList.size() >= 1);
	}

}
