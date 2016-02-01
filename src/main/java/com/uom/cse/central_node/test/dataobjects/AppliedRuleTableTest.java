package com.uom.cse.central_node.test.dataobjects;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.uom.cse.central_node.dataobjects.AppliedRule;
import com.uom.cse.central_node.dataobjects.AppliedRuleTable;

public class AppliedRuleTableTest {

	@Test
	public void testInsertAppliedRule() {
		AppliedRule rule = new AppliedRule();
		rule.setGroupId(10);
		rule.setMac("21:44:4E:43:21:74");
		rule.setRuleId(1000);
		rule.setRuleType("Performance Data");
		rule.setTargetType("Android");
		int id = AppliedRuleTable.insertAppliedRule(rule);
		List<AppliedRule> ruleList = AppliedRuleTable.getAppliedRulesForGroup(10);
		
		boolean flag = false;
		for (AppliedRule tempRule : ruleList) {
			if (tempRule.getGroupId() == 10 && tempRule.getRuleId() == 1000 && "21:44:4E:43:21:74".equals(tempRule.getMac()) &&
					"Performance Data".equals(rule.getRuleType()) && "Android".equals(rule.getTargetType())) {
				flag = true;
				break;
			}
		}
		
		assertEquals(true, flag);
		
	}

	@Test
	public void testGetAppliedRulesForGroup() {
		AppliedRule rule = new AppliedRule();
		rule.setGroupId(10);
		rule.setMac("21:44:4E:43:21:74");
		rule.setRuleId(1000);
		rule.setRuleType("Performance Data");
		rule.setTargetType("Android");
		int id = AppliedRuleTable.insertAppliedRule(rule);
		List<AppliedRule> ruleList = AppliedRuleTable.getAppliedRulesForGroup(10);
		
		boolean flag = false;
		for (AppliedRule tempRule : ruleList) {
			if (tempRule.getGroupId() == 10 && tempRule.getRuleId() == 1000 && "21:44:4E:43:21:74".equals(tempRule.getMac()) &&
					"Performance Data".equals(rule.getRuleType()) && "Android".equals(rule.getTargetType())) {
				flag = true;
				break;
			}
		}
		
		assertEquals(true, flag);
	}

	@Test
	public void testGetAppliedRulesForIndividual() {
		AppliedRule rule = new AppliedRule();
		rule.setGroupId(10);
		rule.setMac("21:44:4E:43:21:74");
		rule.setRuleId(1000);
		rule.setRuleType("Performance Data");
		rule.setTargetType("Android");
		int id = AppliedRuleTable.insertAppliedRule(rule);
		List<AppliedRule> ruleList = AppliedRuleTable.getAppliedRulesForGroup(10);
		
		boolean flag = false;
		for (AppliedRule tempRule : ruleList) {
			if (tempRule.getGroupId() == 10 && tempRule.getRuleId() == 1000 && "21:44:4E:43:21:74".equals(tempRule.getMac()) &&
					"Performance Data".equals(rule.getRuleType()) && "Android".equals(rule.getTargetType())) {
				flag = true;
				break;
			}
		}
		
		assertEquals(true, flag);
	}

}
