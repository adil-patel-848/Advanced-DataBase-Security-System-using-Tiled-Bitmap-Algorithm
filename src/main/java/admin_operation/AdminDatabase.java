/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin_operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author patel
 */
public class AdminDatabase {
    boolean isAdmin(String u,String p)
    {
        boolean flag=false;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement s=con.createStatement();
            String query="select * from admin_info where username='"+u+"' AND password='"+p+"'";
            ResultSet rs=s.executeQuery(query);
            if(rs.next())
            {
                flag=true;
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception: "+ex);
        }
       return flag;
    }
    ArrayList<String> getAdminDetails(String admin)
    {
        ArrayList<String> a= new ArrayList<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement sc=con.createStatement();
            String query="select * from admin_info where username='"+admin+"'";
            ResultSet rs=sc.executeQuery(query);
            if(rs.next())
            {
                String u=rs.getString("username");
                String p=rs.getString("password");
                a.add(u);
                a.add(p);    
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception:"+ex);
        }
        return a;
    }
    boolean updateAdmin(String u,String p)
    {
        boolean flag=false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            
            Statement st=con.createStatement();
            String query="UPDATE admin_info set password='"+p+"' where username='"+u+"'";
            int rows=st.executeUpdate(query);
            if(rows>0)
                flag=true;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return flag;
    }
    
}
