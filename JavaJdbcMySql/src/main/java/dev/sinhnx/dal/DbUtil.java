package dev.sinhnx.dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/OrderDB";
    private static String user = "sinhnx";
    private static String password = "sinhnx.dev";

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static Connection getConnection(String dbConfigFile) throws SQLException {
        try (FileInputStream f = new FileInputStream(dbConfigFile)) {
            // load the properties file
            Properties pros = new Properties();
            pros.load(f);
            // assign db parameters
            url = pros.getProperty("url");
            user = pros.getProperty("user");
            password = pros.getProperty("password");
            // create a connection to the database
            return getConnection();
        } catch (IOException e) {
            return null;
        }
    }

    public static Connection getConnection(String url, String user, String password) throws SQLException {
        DbUtil.url = url;
        DbUtil.user = user;
        DbUtil.password = password;
        return getConnection();
    }

    public static boolean executeStatement(String sql) {
        boolean exeResult = false;
        try (Connection con = DbUtil.getConnection();) {
            Statement stm = con.createStatement();
            exeResult = stm.execute(sql);
            stm.close();
        } catch (SQLException e) {
            exeResult = false;
        }
        return exeResult;
    }

    public static int executeBatch(String[] sqls) {
        int resultNo = 0;
        try (Connection con = DbUtil.getConnection();) {
            if (con == null) {
                return resultNo;
            }
            Statement stm = con.createStatement();
            for (String sql : sqls) {
                stm.addBatch(sql);
            }
            int[] executeResult = stm.executeBatch();
            for (int i : executeResult) {
                resultNo += i;
            }
        } catch (Exception e) {
        }
        return resultNo;
    }
}