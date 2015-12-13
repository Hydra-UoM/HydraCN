package com.uom.cse.central_node.data_objects;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LogRuleTable {
	private static String dbURL = "jdbc:derby:filterDB;create=true;";
	private static String TABLE_NAME = "windowsLogRules";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	// queries
	private static String CREATE_LOG_RULE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME
			+ " (ID INTEGER not null primary key " + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
			+ "FILTERNAME VARCHAR(30),TIMEBOUND VARCHAR(5),LOGTYPE VARCHAR(30),SUMMARIZATIONLEVEL INTEGER,"
			+ "ISPROCESSENABLE BOOLEAN, PROCESSNAME VARCHAR(30), TYPE VARCHAR(30), SECURITYLEVEL VARCHAR(30))";
	
	
	private static void createConnection() {
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
		createConnection();
		try {
			stmt = conn.createStatement();
			
			String insertStatement = "insert into " + TABLE_NAME + "(FILTERNAME, TIMEBOUND, LOGTYPE, SUMMARIZATIONLEVEL, ISPROCESSENABLE, "
					+ "PROCESSNAME, TYPE, SECURITYLEVEL) values ('"+ rule.getFilterName() + "','" + rule.getTimeBound()
					+ "','" + rule.getLogType() + "'," + rule.getSummarizationLevel() + "," + rule.isProcessLogEnable() 
					+ ",'" + rule.getProcessName() + "','" + rule.getType() + "','" + rule.getSecurityLevel() + "')";
			
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
		shutdown();
		return returnValue;
	}
	public static List<LogRule> getAllLogRules() {
		List<LogRule> returnRules = new ArrayList<LogRule>();
		
		createConnection();
		
		try {
			stmt = conn.createStatement();
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
		
		shutdown();

		return returnRules;
	}

	private static void createRuleTable() {
		try {
			
			// get filter table
			DatabaseMetaData metas = conn.getMetaData();
			ResultSet tables = metas.getTables(conn.getCatalog(), null, TABLE_NAME.toUpperCase(), null);

			// check table exists
			if (!tables.next()) {
				stmt = conn.createStatement();
				stmt.execute(CREATE_LOG_RULE_TABLE_QUERY);
				stmt.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	private static void shutdown() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				DriverManager.getConnection(dbURL + ";shutdown=true");
				conn.close();
			}
		} catch (SQLException sqlExcept) {

		}
	}

}
