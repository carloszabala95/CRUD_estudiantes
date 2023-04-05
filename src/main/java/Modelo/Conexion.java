
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    
    private static final String JDBC_URL = "jdbc:mysql;//localhost:3306/control_estudiantes?useSSL=false&useTimezone=true&ServerTimeZone=UTC&allowPublicKeyRetrieval=true";
    
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";
    
    private static BasicDataSource dataSource;
    
    static{
        try {
            Class.forName("com.mysql.cj.jdbfc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar controlador deMysql");
        }
    }
    
    public static DataSource getDataSource(){
        if(dataSource == null){
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);
        }
        return dataSource;
    }
    
    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }
    
    public static void Close (ResultSet rs){
        try {
            rs.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public static void Close(PreparedStatement st){
        try {
            st.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
    }
    
    public static void Close(Connection con){
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
