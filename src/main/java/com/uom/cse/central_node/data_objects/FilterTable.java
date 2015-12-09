package com.uom.cse.central_node.data_objects;

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
	private static String TABLE_NAME = "Filter";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	// queries
	private static String CREATE_FILTER_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME
			+ " (ID INTEGER not null primary key," + "CPU DOUBLE, RAM DOUBLE, NETWORK DOUBLE, PROCESSES VARCHAR(1000))";

	// initiate the database and table
	{
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();

			// Get a connection
			conn = DriverManager.getConnection(dbURL);

			// get metaData
			DatabaseMetaData metas = conn.getMetaData();

			// create statement
			stmt = conn.createStatement();

			// get filter table
			ResultSet tables = metas.getTables(conn.getCatalog(), null, TABLE_NAME, null);

			// check table exists
			if (!tables.next()) {
				stmt.execute(CREATE_FILTER_TABLE_QUERY);
			}

			// close statement
			stmt.close();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public static boolean insertFilter(Filter filter) {
		boolean returnFlag = false;

		try {
			stmt = conn.createStatement();
			stmt.execute("insert into " + TABLE_NAME + " values (" + filter.getCpuUsage() + ",'" + filter.getRamUsage()
					+ "','" + filter.getNetworkUsage() + "','" + filter.getProcessesStr() + "')");

			// close statement
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

		return returnFlag;
	}

	public static Filter getFilter(int id) {
		List<Filter> returnFilters = new ArrayList<Filter>();

		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME + "where ID = " + id);

			while (results.next()) {

				Filter filter = new Filter();

				filter.setId(results.getInt(1));
				filter.setCpuUsage(results.getDouble(2));
				filter.setRamUsage(results.getDouble(3));
				filter.setNetworkUsage(results.getDouble(4));
				filter.setProcesses(results.getString(6));

				returnFilters.add(filter);
			}

			results.close();

			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

		return null;
	}

	public static List<Filter> getAllFilters() {
		List<Filter> returnFilters = new ArrayList<Filter>();

		try {
			stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from " + TABLE_NAME);

			while (results.next()) {

				Filter filter = new Filter();

				filter.setId(results.getInt(1));
				filter.setCpuUsage(results.getDouble(2));
				filter.setRamUsage(results.getDouble(3));
				filter.setNetworkUsage(results.getDouble(4));
				filter.setProcesses(results.getString(6));

				returnFilters.add(filter);
			}

			results.close();

			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}

		return returnFilters;
	}
}
