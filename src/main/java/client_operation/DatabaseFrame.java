
package client_operation;
import java.sql.*;
import java.util.*;

public class DatabaseFrame {
    
    boolean clientData(String n,String t,String tn,String e,String m,String u,String p)
    {
        boolean flag=false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            
            Statement st=con.createStatement();
//            String query="INSERT INTO student_info VALUES ('"+n+"','"+r+"','"+p+"')";
//            int rows=st.executeUpdate(query);
            String query = "INSERT INTO client_info VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, n);
            ps.setString(2, t);
            ps.setString(3, tn);
            ps.setString(4, e);
            ps.setString(5, m);
            ps.setString(6, u);
            ps.setString(7, p);
            int rows = ps.executeUpdate();
            if(rows>0)
                flag=true;
        }
        catch(Exception ex)
        {
            
        }
        return flag;
    }
    boolean isClient(String u,String p)
    {
        boolean flag=false;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement s=con.createStatement();
            String query="select * from client_info where username='"+u+"' AND password='"+p+"'";
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
    
    ArrayList<String> getClientDetails(String client)
    {
        ArrayList<String> a= new ArrayList<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement sc=con.createStatement();
            String query="select * from client_info where username='"+client+"'";
            ResultSet rs=sc.executeQuery(query);
            if(rs.next())
            {
                String n=rs.getString("name");
                String t=rs.getString("type");
                String tn=rs.getString("type_name");
                String e=rs.getString("email");
                String m=rs.getString("mobile");
                String u=rs.getString("username");
                String p=rs.getString("password");
                a.add(n);
                a.add(t);
                a.add(tn);
                a.add(e);
                a.add(m);
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
    boolean updateClient(String n,String t,String tn,String e,String m,String u,String p)
    {
        boolean flag=false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            
           Statement sc=con.createStatement();
           String query="update client_info set name='"+n+"',type='"+t+"',type_name='"+tn+"',email='"+e+"',mobile='"+m+"',password='"+p+"' where username='"+u+"'";
           int rows=sc.executeUpdate(query);
         
            if(rows>0)
                flag=true;
        }
        catch(Exception ex)
        {
            System.out.println("Error:"+ex);
        }
        return flag;
    }
    
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
        System.out.println(flag);
       return flag;
    }
    
    ArrayList<String> getClientData(String client)
    {
        ArrayList<String> data= new ArrayList<String>();
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement s=con.createStatement();
            
            String query="select * from client_data_storage where client='"+client+"'";
            ResultSet rs=s.executeQuery(query);
            while(rs.next())
            {
                String file=rs.getString("data");
                data.add(file);
            }
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return data;
    }
    
    String[] getColumn(String tname)
    {
        String []col=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement s=con.createStatement();
            
            String query="select * from "+tname;
            
            ResultSet rs=s.executeQuery(query);
            ResultSetMetaData rsmd= rs.getMetaData();
            
            int col_count=rsmd.getColumnCount();
            col=new String[col_count];
            
            for(int i=0;i<col_count;i++)
            {
                col[i]=rsmd.getColumnName(i+1);
            }
        }
        catch(Exception ex)
        {
            
        }
        return col;
    }
    
    String[][] getTable(String tname,int col_count)
    {
        String[][]matrix=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement s1=con.createStatement();
            Statement s2=con.createStatement();
            
            String query="select * from "+tname;
            
            ResultSet rs1=s1.executeQuery(query);
            ResultSet rs2=s2.executeQuery(query);
            
            int row_count=0;
            
            while(rs1.next())
                row_count++;
            
            matrix=new String[row_count][col_count];
            
            System.out.println(query);
            System.out.println(row_count);
            System.out.println(col_count);
            
            int i=0;
            while(rs2.next())
            {
                for(int j=0;j<col_count;j++)
                {
                    matrix[i][j]=rs2.getString(j+1);
                }
                i++;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return matrix;
    }
}