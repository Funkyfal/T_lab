package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionUtil db = new ConnectionUtil();

        Connection conn = db.connect_to_db("TDB", "postgres", "DBPASSWORD");
        String query = "SELECT * FROM test";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        rs.next();
        System.out.println(rs.getString(1));
        rs.next();
        
    }
}