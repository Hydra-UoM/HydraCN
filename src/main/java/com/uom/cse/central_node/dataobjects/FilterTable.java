package com.uom.cse.central_node.dataobjects;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FilterTable {
	private static String dbURL = "jdbc:derby:filterDB;create=true;";
	private static String TABLE_NAME = "filterDetail";
	// jdbc Connection
	private static Connection conn = null;

	// queries
	private static String CREATE_FILTER_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME
			+ " (ID INTEGER not null primary key "
			+ "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
			+ "CPU DOUBLE, RAM DOUBLE, SENTDATA DOUBLE, RECEIVEDDATA DOUBLE, PROCESSES VARCHAR(1000), EVENTID VARCHAR(1000), "
			+ "TIMEBOUND INTEGER, FILTERNAME VARCHAR(30), MESSAGE VARCHAR(1000), APPLY VARCHAR(1))";
	
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
			createFilterTable();
		} catch (Exception except) {
			except.printStackTrace();
		}
	}

	private static void createFilterTable() {
		try {
			
			// get filter table
			DatabaseMetaData metas = conn.getMetaData();
			ResultSet tables = metas.getTables(conn.getCatalog(), null, TABLE_NAME.toUpperCase(), null);

			// check table exists
			if (!tables.next()) {
				Statement stmt = conn.createStatement();
				stmt.execute(CREATE_FILTER_TABLE_QUERY);
				stmt.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static int insertFilter(Filter filter) {
		int returnValue = -1;
		
		try {
			Statement stmt = conn.createStatement();
			
			String insertStatement = "insert into " + TABLE_NAME + "(CPU, RAM, SENTDATA, RECEIVEDDATA, PROCESSES, EVENTID, "
					+ "TIMEBOUND, FILTERNAME, MESSAGE, APPLY) values ("+ filter.getCpuUsage() + "," + filter.getRamUsage()
					+ "," + filter.getSentData() + "," + filter.getReceivedData() + ",'" + filter.getProcessesStr() 
					+ "','" + filter.getEventIdStr()+ "'," + filter.getTimeBound() + ",'" + filter.getFilterName()
					 + "','" + filter.getMessage() + "','N')";
			
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
	
	public static void applyFilter(int id){
		
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
	
	public static void disableAllFilter(){
		
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
	
	public static Filter getAppliedFilter(){
		
		Filter filter = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME + " where APPLY = 'Y'");
			
			while (results.next()) {

				filter = new Filter();

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

			}
			results.close();

			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		//shutdown(); 
		
		return filter;
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

}
