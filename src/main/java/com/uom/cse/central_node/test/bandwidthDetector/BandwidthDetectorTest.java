package com.uom.cse.central_node.test.bandwidthDetector;

import static org.junit.Assert.*;

import org.junit.Test;

import com.uom.cse.central_node.bandwidthDetector.BandwidthDetector;

public class BandwidthDetectorTest {

	@Test
	public void testCalculate() {
		try {
			double bandwidth = BandwidthDetector.calculate("0.0.0.0", 10, 5);
			assertTrue(true);
			assertEquals(0, bandwidth, 0);
		} catch (Exception ex) {
			assertTrue(false);
		}
	}

}
