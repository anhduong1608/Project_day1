package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/trainingmanagement";
    private static String USER = "root";
    private static String PASSWORD = "12345678";

    public static Connection openConnection() {
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (Exception e) {
            System.err.println("không thể kết nối driver");
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(CallableStatement callSt, Connection conn) {
        if (callSt != null) {
            try {
                callSt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
