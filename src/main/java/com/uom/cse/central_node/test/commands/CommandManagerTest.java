package com.uom.cse.central_node.test.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uom.cse.central_node.commands.CommandManager;

public class CommandManagerTest {

	@Test
	public void testCheckAndDeployCommandForWindows() {
		try{
			CommandManager.checkAndDeployCommandForAndorid(null);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

	@Test
	public void testCheckAndDeployCommandForAndorid() {
		try{
			CommandManager.checkAndDeployCommandForWindows(null);
			assertTrue(true);
		}catch (Exception ex) {
			assertTrue(false);
		}
	}

}
