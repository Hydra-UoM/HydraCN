package com.uom.cse.central_node.dataobjects;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CEPRuleTable {
	private static String dbURL = "jdbc:derby:filterDB;create=true;";
	private static String TABLE_NAME = "cepRules";
	// jdbc Connection
	private static Connection conn = null;

	// queries
	private static String CREATE_LOG_RULE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME
			+ " (ID INTEGER not null primary key " + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
			+ "NAME VARCHAR(30),RULE VARCHAR(1000),MESSAGE VARCHAR(500) APPLY VARCHAR(1))";
	
	
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
			createRuleTable();
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	public static int insertRule(LogRule rule) {
		int returnValue = -1;
		
		try {
			Statement stmt = conn.createStatement();
			
			String insertStatement = "insert into " + TABLE_NAME + "(FILTERNAME, TIMEBOUND, LOGTYPE, SUMMARIZATIONLEVEL, "
					+ "ISPROCESSENABLE, PROCESSNAME, TYPE, SECURITYLEVEL, APPLY) values ('"+ rule.getFilterName() + "','" 
					+ rule.getTimeBound() + "','" + rule.getLogType() + "'," + rule.getSummarizationLevel() + "," 
					+ rule.isProcessLogEnable() + ",'" + rule.getProcessName() + "','" + rule.getType() + "','" 
					+ rule.getSecurityLevel() + "',"+ "'N')";
			
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
		
		return returnValue;
	}
	
	public static void applyRule(int id){
		
		try {
			Statement stmt = conn.createStatement();
			
			String updateStatement = "UPDATE " + TABLE_NAME + " SET APPLY = 'Y' WHERE ID = " + id ;
			
			stmt.execute(updateStatement);

			String disableApply = "UPDATE " + TABLE_NAME + " SET APPLY = 'N' WHERE ID != " + id ;
			
			stmt.execute(disableApply);
			
			// close statement
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown();
	}

	public static void disableAllRule(){
		
		try {
			Statement stmt = conn.createStatement();
			
			String updateStatement = "UPDATE " + TABLE_NAME + " SET APPLY = 'N'";
			
			stmt.execute(updateStatement);
			// close statement
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown();
	}
	
	public static LogRule getAppliedRule(){
		
		LogRule rule = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME + " where APPLY = 'Y'");
			
			while (results.next()) {

				rule = new LogRule();

				rule.setId(results.getInt(1));
				rule.setFilterName(results.getString(2));
				rule.setTimeBound(results.getString(3));
				rule.setLogType(results.getString(4));
				rule.setSummarizationLevel(results.getInt(5));
				rule.setProcessLogEnable(results.getBoolean(6));
				rule.setProcessName(results.getString(7));
				rule.setType(results.getString(8));
				rule.setSecurityLevel(results.getString(9));


			}
			results.close();

			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown(); 
		
		return rule;
	}

	public static List<LogRule> getAllLogRules() {
		List<LogRule> returnRules = new ArrayList<LogRule>();
		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME);

			while (results.next()) {

				LogRule rule = new LogRule();

				rule.setId(results.getInt(1));
				rule.setFilterName(results.getString(2));
				rule.setTimeBound(results.getString(3));
				rule.setLogType(results.getString(4));
				rule.setSummarizationLevel(results.getInt(5));
				rule.setProcessLogEnable(results.getBoolean(6));
				rule.setProcessName(results.getString(7));
				rule.setType(results.getString(8));
				rule.setSecurityLevel(results.getString(9));

				returnRules.add(rule);
			}

			results.close();

			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		

		return returnRules;
	}

	private static void createRuleTable() {
		try {
			
			// get filter table
			DatabaseMetaData metas = conn.getMetaData();
			ResultSet tables = metas.getTables(conn.getCatalog(), null, TABLE_NAME.toUpperCase(), null);

			// check table exists
			if (!tables.next()) {
				Statement stmt = conn.createStatement();
				stmt.execute(CREATE_LOG_RULE_TABLE_QUERY);
				stmt.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}



}
