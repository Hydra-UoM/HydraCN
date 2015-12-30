package com.uom.cse.central_node.bandwidthDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BandwidthDetector {
	
	public static double calculate(String ipAddress, int noOfTimes, int size){
		double bandwidth = 0d;
		try {
			String str1 = null, str2 = null;
			String[] str = new String[] { "ping.exe", ipAddress, "-n", noOfTimes + "", "-l", size + "" };

			Process p = Runtime.getRuntime().exec(str);

			StringBuilder sbx = new java.lang.StringBuilder();
			for (String x : str)
				sbx.append(x);

			System.out.println(sbx.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			while ((str1 = br.readLine()) != null) {
				System.out.println(str1);
				if (str1.contains("Minimum")) {
					str2 = str1;
					break;
				}
			}

			/* str2 will obviously have Minimum, Maximum, Average */
			if (str2 != null) {
				if (str2.contains("Minimum")) {
					str2 = str2.trim();
					int avg = Integer.parseInt(str2.substring(str2.indexOf("Average") + 9,
									str2.length()).replace("ms", "").trim());
					System.out.println("Average: " + avg);
					bandwidth = (size * 1000000 / (avg)) / 1024;
				} else {
					System.out.println("Exception ONE");
					System.out.println("Could not reach target server. Try to increase interval number.");
					System.out.println("Please retry");
				}
			} else {
				System.out.println("Exception TWO");
				System.out.println("Could not reach target server. Try to increase interval number.");
				System.out.println("Please retry");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bandwidth;
	}
}
