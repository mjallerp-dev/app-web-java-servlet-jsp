package com.mjallerp.appwebjavaservletjsp.drivers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
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

    public DatabaseConnection() throws Exception{
        loadProperties();
        this.conectar();
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

    public void conectar() throws Exception {
        try {
            Class.forName(driver);
        }
        catch (ClassNotFoundException ex) {
            throw new Exception("Error de driver :" + ex.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex) {
            throw new Exception("Error de conexion \n Codigo:"
                    + ex.getErrorCode() + " Explicación: " + ex.getMessage());
        }
    }

    public int actualizar(PreparedStatement sentence) throws Exception {
        try {
            int res = sentence.executeUpdate();
            return res;
        }
        catch (SQLException ex) {
            throw new Exception("Error al ejecutar la sentencia BD conexión \n Codigo:"
                    + ex.getErrorCode() + " Explicación: " + ex.getMessage());
        }
    }

    public ResultSet consultar(PreparedStatement sentence) throws Exception {
        try {
            ResultSet bdrows = sentence.executeQuery();
            return bdrows;
        }
        catch (SQLException ex) {
            throw new Exception("Error al ejecutar la sentencia BD conexión: "
                    + ex.getMessage());
        }
    }

    public void desconectar() {
        try {
            connection.close();
        }
        catch (SQLException ex) {
            connection = null;
        }
    }

    public PreparedStatement crearSentencia(String sql) throws Exception{
        try {
            PreparedStatement sentence = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return sentence;
        }
        catch (SQLException ex) {
            throw new Exception("Error al crear la sentencia DB \n Codigo:"
                    + ex.getErrorCode() + " Explicación: " + ex.getMessage());
        }
    }

    public String getDriver() {
        return driver;
    }

    public String getHost() {
        return host;
    }

    public String getUrl() {
        return url;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDbname() {
        return dbname;
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getSentence() {
        return sentence;
    }

    public ResultSet getResultRows() {
        return resultRows;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setSentence(PreparedStatement sentence) {
        this.sentence = sentence;
    }

    public void setResultRows(ResultSet resultRows) {
        this.resultRows = resultRows;
    }
}
