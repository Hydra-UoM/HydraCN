package com.uom.cse.central_node.data_objects;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppliedRuleTable {
	private static String dbURL = "jdbc:derby:filterDB;create=true;";
	private static String TABLE_NAME = "appliedRules";
	
	public static final String LOG_RULE = "LOG_RULE";
	public static final String PERFORMANCE_RULE = "PERFORMANCE_RULE";
	
	public static final String INDIVIDUAL_TARGET = "INDIVIDUAL";
	public static final String GROUP_TARGET = "GROUP";
	
	// jdbc Connection
	private static Connection conn = null;

	// queries
	private static String CREATE_APPLIED_RULE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME
			+ " (ID INTEGER not null primary key "
			+ "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
			+ "TARGET_TYPE VARCHAR(40), RULE_TYPE VARCHAR(40), RULE_ID INTEGER, GROUP_ID INTEGER, MAC VARCHAR(40)"
			+ " ACTIVE VARCHAR(1))";
	
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
			createAppliedRuleTable();
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	private static void createAppliedRuleTable() {
		try {
			
			// get filter table
			DatabaseMetaData metas = conn.getMetaData();
			ResultSet tables = metas.getTables(conn.getCatalog(), null, TABLE_NAME.toUpperCase(), null);

			// check table exists
			if (!tables.next()) {
				Statement stmt = conn.createStatement();
				stmt.execute(CREATE_APPLIED_RULE_TABLE_QUERY);
				stmt.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static int insertAppliedRule(AppliedRule rule) {
		int returnValue = -1;
		
		try {
			Statement stmt = conn.createStatement();
			
//			String updateStatement = "UPDATE " + TABLE_NAME + " SET APPLY = 'N' WHERE GROUP_ID = " + rule.getGroupId();
//			
//			stmt.execute(updateStatement);
			
			String insertStatement = "insert into " + TABLE_NAME + " TARGET_TYPE, RULE_TYPE, RULE_ID, GROUP_ID, MAC) "
					+ " values ('"+ rule.getTargetType() + "','" + rule.getRuleType() + "'," + rule.getRuleId() 
					+ "," + rule.getGroupId() + ",'" + rule.getMac() + "','Y')";
			
			stmt.execute(insertStatement, Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()){
				returnValue = rs.getInt(1);
			}
			
			// close statement
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown();
		return returnValue;
	}

	public static List<AppliedRule> getAppliedRulesForGroup(int groupId) {
		List<AppliedRule> returnValue = new ArrayList<AppliedRule>();
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME + " where APPLY = 'Y' and GROUP_ID = " 
			+ groupId);
			
			while (results.next()) {

				AppliedRule rule = new AppliedRule();

				rule.setId(results.getInt(1));
				rule.setTargetType(results.getString(2));
				rule.setRuleType(results.getString(3));
				rule.setRuleId(results.getInt(4));
				rule.setGroupId(results.getInt(5));
				rule.setMac(results.getString(6));

				returnValue.add(rule);
			}
			results.close();
			
			// close statement
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown();
		return returnValue;
	}
	
	public static List<AppliedRule> getAppliedRulesForIndividual(String mac) {
		List<AppliedRule> returnValue = new ArrayList<AppliedRule>();
		
		try {
			Statement stmt = conn.createStatement();
			
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME + " where APPLY = 'Y' and MAC = '" 
			+ mac + "'");
			
			while (results.next()) {

				AppliedRule rule = new AppliedRule();

				rule.setId(results.getInt(1));
				rule.setTargetType(results.getString(2));
				rule.setRuleType(results.getString(3));
				rule.setRuleId(results.getInt(4));
				rule.setGroupId(results.getInt(5));
				rule.setMac(results.getString(6));

				returnValue.add(rule);
			}
			results.close();
			
			// close statement
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown();
		return returnValue;
	}
	
	public static void applyFilter(int id){
		
		try {
			Statement stmt = conn.createStatement();
			
			String updateStatement = "UPDATE " + TABLE_NAME + " SET APPLY = 'Y' WHERE ID = " + id;
			
			stmt.execute(updateStatement);

			String disableApply = "UPDATE " + TABLE_NAME + " SET APPLY = 'N' WHERE ID != " + id;
			
			stmt.execute(disableApply);
			
			// close statement
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown();
	}
	
	public static void disableFilter(int id){
		
		try {
			Statement stmt = conn.createStatement();
			
			String updateStatement = "UPDATE " + TABLE_NAME + " SET APPLY = 'N' WHERE ID = " + id;
			
			stmt.execute(updateStatement);
			// close statement
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown();
	}

	public static Filter getFilter(int id) {
		List<Filter> returnFilters = new ArrayList<Filter>();
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME + " where ID = " + id);

			while (results.next()) {

				Filter filter = new Filter();

				filter.setId(results.getInt(1));
				filter.setCpuUsage(results.getDouble(2));
				filter.setRamUsage(results.getDouble(3));
				filter.setSentData(results.getDouble(4));
				filter.setReceivedData(results.getDouble(5));
				filter.setProcesses(results.getString(6));
				filter.setEventIdStr(results.getString(7));
				filter.setTimeBound(results.getInt(8));
				filter.setFilterName(results.getString(9));
				filter.setMessage(results.getString(10));

				returnFilters.add(filter);
			}

			results.close();

			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown();
		return null;
	}

	public static List<Filter> getAllFilters() {
		List<Filter> returnFilters = new ArrayList<Filter>();
		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME);

			while (results.next()) {

				Filter filter = new Filter();

				filter.setId(results.getInt(1));
				filter.setCpuUsage(results.getDouble(2));
				filter.setRamUsage(results.getDouble(3));
				filter.setSentData(results.getDouble(4));
				filter.setReceivedData(results.getDouble(5));
				filter.setProcesses(results.getString(6));
				filter.setEventIdStr(results.getString(7));
				filter.setTimeBound(results.getInt(8));
				filter.setFilterName(results.getString(9));
				filter.setMessage(results.getString(10));

				returnFilters.add(filter);
			}

			results.close();

			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		
		//shutdown();

		return returnFilters;
	}
	
//	private static void shutdown() {
//		Statement stmt = conn.createStatement();
//		try {
//			if (stmt != null) {
//				stmt.close();
//			}
//			if (conn != null) {
//				DriverManager.getConnection(dbURL + ";shutdown=true");
//				conn.close();
//			}
//		} catch (SQLException sqlExcept) {
//
//		}
//	}
}
