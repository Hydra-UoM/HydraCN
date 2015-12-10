package com.uom.cse.central_node.data_objects;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class FilterTable {
	private static String dbURL = "jdbc:derby:filterDB;create=true;";
	private static String TABLE_NAME = "filterDetail";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	// queries
	private static String CREATE_FILTER_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME
			+ " (ID INTEGER not null primary key "
			+ "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
			+ "CPU DOUBLE, RAM DOUBLE, SENTDATA DOUBLE, RECEIVEDDATA DOUBLE, PROCESSES VARCHAR(1000), EVENTID VARCHAR(1000), "
			+ "TIMEBOUND INTEGER, FILTERNAME VARCHAR(30), MESSAGE VARCHAR(1000))";

//	public static void main(String[] args) {
//		selectRestaurants();
//	}
	private static void selectRestaurants() {
		createConnection();
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME);
			ResultSetMetaData rsmd = results.getMetaData();
			int numberCols = rsmd.getColumnCount();
			for (int i = 1; i <= numberCols; i++) {
				// print Column Names
				System.out.print(rsmd.getColumnLabel(i) + "\t\t");
			}

			System.out.println("\n-------------------------------------------------");

			while (results.next()) {
				int id = results.getInt(1);
				String restName = results.getString(2);
				String cityName = results.getString(3);
				System.out.println(id + "\t\t" + restName + "\t\t" + cityName);
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
	private static void createConnection() {
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
				stmt = conn.createStatement();
				stmt.execute(CREATE_FILTER_TABLE_QUERY);
				stmt.close();
			}
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static int insertFilter(Filter filter) {
		int returnValue = -1;
		createConnection();
		try {
			stmt = conn.createStatement();
			
			String insertStatement = "insert into " + TABLE_NAME + "(CPU, RAM, SENTDATA, RECEIVEDDATA, PROCESSES, EVENTID, "
					+ "TIMEBOUND, FILTERNAME, MESSAGE) values ("+ filter.getCpuUsage() + "," + filter.getRamUsage()
					+ "," + filter.getSentData() + "," + filter.getReceivedData() + ",'" + filter.getProcessesStr() 
					+ "','" + filter.getEventIdStr()+ "'," + filter.getTimeBound() + ",'" + filter.getFilterName()
					 + "','" + filter.getMessage() + "')";
			
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

	public static Filter getFilter(int id) {
		List<Filter> returnFilters = new ArrayList<Filter>();
		createConnection();
		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME + "where ID = " + id);

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
		shutdown();
		return null;
	}

	public static List<Filter> getAllFilters() {
		List<Filter> returnFilters = new ArrayList<Filter>();
		
		createConnection();
		
		try {
			stmt = conn.createStatement();
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
		
		shutdown();

		return returnFilters;
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
