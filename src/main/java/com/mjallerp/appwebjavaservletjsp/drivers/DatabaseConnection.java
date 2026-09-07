package com.mjallerp.appwebjavaservletjsp.drivers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DatabaseConnection {

    protected String driver = "org.postgresql.Driver";
    protected String host;
    protected String url = "jdbc:postgresql://";
    protected int port;
    protected String user;
    protected String password;
    protected String dbname;
    private Connection connection;
    private PreparedStatement sentence;
    private ResultSet resultRows;

    public DatabaseConnection() {
        loadProperties();
    }

    private void loadProperties() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("application.properties not found");
            }
            props.load(input);
            this.host = props.getProperty("dbHost");
            this.port = Integer.parseInt(props.getProperty("dbPort"));
            this.user = props.getProperty("dbUser");
            this.password = props.getProperty("dbPassword");
            this.dbname = props.getProperty("dbName");
            this.url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
    }
}
