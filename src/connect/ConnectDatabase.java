package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDatabase {
	public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/qlsb?verifyServerCertificate=false&useSSL=true";
    public static String user = "root";
    public static String pass = "132671";
    
    public static Connection cnn;//địt mẹ mày???????? :D ???????? ãyem t fix nèok tok aok ofix đi
     
    public static boolean open() {
        try {
            if (cnn == null || cnn.isClosed()) {
                Class.forName(driver);
                cnn = DriverManager.getConnection(url, user, pass);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
    public ConnectDatabase() {
    }

    public static void close() {
        try {
            if (cnn != null) {
                cnn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void close(PreparedStatement ps){
        try {
            if (ps !=null) {
            ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
    public static void close(Statement ps){
        try {
            if (ps !=null) {
            ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
    public static void close(Statement ps, ResultSet rs){
        try {
            if (rs !=null) {
            rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        close(ps);
    }
}
