/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PHUCHAU
 */
public class DBHelper {
    public static Connection getConnection()throws ClassNotFoundException, SQLException{
        //1 load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2tao connection string den dbms dua tren cu phap: protocol://ip:port;databaseName=
        String url = "jdbc:sqlserver://" //protocol
                + "localhost:1433;" //container
                + "databaseName=dbwebtrenlop";
        //3 getconnection tu Driver Manager
        Connection con = DriverManager.getConnection(url, "sa", "123456");
        //4 return connection
        return con;
    }
}
