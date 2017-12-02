package Controller;

import java.sql.*;

public class connect{
    public Connection con;
    public Statement stm;
    public ResultSet rs;
    public String sql;
    
    public connect(){
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/busapp", "root", "");
            stm = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error Connecting to Database");
        }
    }
}