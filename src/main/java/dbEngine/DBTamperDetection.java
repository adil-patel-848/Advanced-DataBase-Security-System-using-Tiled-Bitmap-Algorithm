/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbEngine;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author patel
 */
public class DBTamperDetection extends Thread {
    public String client;
    public String table;
    public boolean flag;
    public void run()
    {
        while(flag)
        {
            try
            {
                Thread.sleep(10000);
                Date d=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("dd:MM:YYYY hh:mm:ss");
                String date_time=sdf.format(d);
                System.out.println("Visited data "+table+" of client "+client+" at "+date_time);
                
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
    }
    
}
