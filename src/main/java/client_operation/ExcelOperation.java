/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client_operation;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jxl.Cell;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author patel
 */
public class ExcelOperation {
    ArrayList<String> getColumnNames(String path)
    {
        ArrayList<String> column_name=new ArrayList<String>();
        try
        {
            File file=new File(path);
            Workbook w= Workbook.getWorkbook(file);
            Sheet sheet=w.getSheet(0);
            int rows=sheet.getRows();
            int col= sheet.getColumns();
            for(int i=0;i<col;i++)
            {
                Cell cl=sheet.getCell(i,0);
                String content=cl.getContents().trim();
                column_name.add(content);
            }
            w.close();
            System.out.println(column_name);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Excel Sheet exception");
            System.out.println(ex);
        }
        return column_name;
        
    }
    public boolean isTableCreated(String tname,ArrayList<String> col_name)
    {
        boolean flag=false;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement s=con.createStatement();
            
            String query1="create table IF NOT EXISTS "+tname+"(";
            String query2="";
            for(int i=0;i<col_name.size();i++)
            {
                String col=col_name.get(i);
                query2=query2+col+" VARCHAR(70) ,";
            }
            String query3=" PRIMARY KEY("+col_name.get(0)+"))";
            String query=query1+query2+query3;
            System.out.println(query);
            
            s.executeUpdate(query);
            flag=true;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return flag;
    }
    public boolean isDataStored(String tname,String path,ArrayList<String> col_name)
    {
        boolean flag=false;
        try
        {
            String query1="INSERT INTO "+tname+"(";
            String query2="";
            String query3=") VALUES(";
            for(int i=0;i<col_name.size();i++)
            {
                String col=col_name.get(i);
                query2=query2+col+", ";
                query3=query3+"?, ";
            }
            query2=query2.substring(0,query2.length()-2);
            query3=query3.substring(0,query3.length()-2)+")";
            
            String fquery=query1+query2+query3;
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            PreparedStatement ps=con.prepareStatement(fquery);
            
            File file=new File(path);
            Workbook wkb= Workbook.getWorkbook(file);
            Sheet s=wkb.getSheet(0);
            
            int rows=s.getRows();
            int col=s.getColumns();
            
            for(int i=1;i<rows;i++)
            {
                for(int j=0;j<col;j++)
                {
                    Cell c=s.getCell(j,i);
                    String content=c.getContents();
                    ps.setString(j+1,content);
                }
                ps.addBatch();
            }
            ps.executeBatch();
            flag=true;
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return flag;
    }
    public boolean clientDetails(String client,String tname,String date_time)
    {
        boolean flag=false;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/client_db","root","root123");
            Statement s=con.createStatement();
            
            String query="insert into client_data_storage(client, data, date_time) values('"+client+"','"+tname+"','"+date_time+"')";
            s.executeUpdate(query);
            flag=true;  
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return flag;
               
    }
    
    public boolean isFileDownloaded(ArrayList download_data,String dpath)
    {
        boolean flag=false;
        try
        {
            File file=new File(dpath);
            WritableWorkbook wbk= Workbook.createWorkbook(file);
            WritableSheet ws=wbk.createSheet("Sheet 1", 0);
            WritableFont cell_font= new WritableFont(WritableFont.TIMES,11);
            WritableCellFormat wcf=new WritableCellFormat(cell_font);
            Label l=null;
            for(int i=0;i<download_data.size();i++)
            {
                ArrayList row=(ArrayList)download_data.get(i);
                for(int j=0;j<row.size();j++)
                {
                    String cell_content=(String)row.get(j);
                    l=new Label(j,i,cell_content,wcf);
                    ws.addCell(l);
                }
            }
            wbk.write();
            wbk.close();
            flag=true;
            
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return flag;
    }
    
}
