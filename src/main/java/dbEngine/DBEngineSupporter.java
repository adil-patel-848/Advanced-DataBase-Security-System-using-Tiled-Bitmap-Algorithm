/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author patel
 */
public class DBEngineSupporter {
    public ArrayList getClientData()
    {
        ArrayList data= new ArrayList();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement s=con.createStatement();
            
            String query="select * from client_data_storage";
            ResultSet rs=s.executeQuery(query);
            while(rs.next())
            {
                ArrayList row=new ArrayList();
                row.add(rs.getString("client"));
                row.add(rs.getString("data"));
                data.add(row);
            }
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return data;
    }
}
