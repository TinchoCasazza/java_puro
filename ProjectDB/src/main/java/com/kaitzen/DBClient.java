package com.kaitzen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBClient {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
   
    Connection dbConnection = null;

    public void init() {
    	if (dbConnection == null) {
    		connect();
    		generateDB();
    	}
    }

    public int insert(String name) throws SQLException {
    	init();
        PreparedStatement insertPreparedStatement = dbConnection.prepareStatement(INSERT_QUERY);
        insertPreparedStatement.setString(1, name);
        insertPreparedStatement.executeUpdate();
        ResultSet rs = insertPreparedStatement.getGeneratedKeys();
        Integer userId = null;
        if (rs.first()) {
            userId = rs.getInt(1);
        }
        insertPreparedStatement.close();
        return userId;
    }

    public Person select(int id) throws SQLException {
    	init();
    	PreparedStatement selectPreparedStatement = dbConnection.prepareStatement(SELECT_ID_QUERY);
        selectPreparedStatement.setInt(1,id);
        return select(selectPreparedStatement);
    }

    public Person select(String name) throws SQLException{
    	init();
    	PreparedStatement selectPreparedStatement = dbConnection.prepareStatement(SELECT_NAME_QUERY);
        selectPreparedStatement.setString(1,name);
        return select(selectPreparedStatement);
    }

    private Person select(PreparedStatement selectPreparedStatement) throws SQLException{
        Person result = null;
        ResultSet rs = selectPreparedStatement.executeQuery();
        if (rs.first()) {
            //System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            result = new Person(rs.getInt("id"), rs.getString("name"));
        }
        selectPreparedStatement.close();
        return result;
    }

    public List<Person> select() throws SQLException{
    	init();
    	List<Person> crowd = new ArrayList<Person>();
        PreparedStatement selectPreparedStatement = dbConnection.prepareStatement(SELECT_ALL_QUERY);
        ResultSet rs = selectPreparedStatement.executeQuery();
        while (rs.next()) {
            //System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
            crowd.add(new Person(rs.getInt("id"), rs.getString("name")));
        }
        selectPreparedStatement.close();
        return crowd;
    }

    private void connect() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void generateDB() {
        try {
            dbConnection.setAutoCommit(false);
            PreparedStatement createPreparedStatement = dbConnection.prepareStatement(CREATE_QUERY);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
        } catch(SQLException sqlEx) {
            System.out.println("ERROR: " + sqlEx.getMessage());
        }
    }
}