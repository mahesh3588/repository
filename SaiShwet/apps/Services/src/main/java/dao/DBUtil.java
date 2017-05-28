package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DBUtil {
	private static Connection con=null;
	static 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/saishwet","root","root");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getCon()
	{
		return con;
	}

	public Boolean deleteAllTables()
	{
		String qry="show tables from saishwet";
		try
		{
			if(getBackUp()){
				ResultSet rs=con.createStatement().executeQuery(qry);
				while(rs.next())
				{
					String table=rs.getString(1);
					qry="delete from "+table;
					
					con.createStatement().executeUpdate(qry);
				}
				//con.commit();
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	
	
	public Boolean  getBackUp()
	{
		//String   mysqldumpPath="D:/MY SQL CURRENT DATABASE/bin";
		//String   mysqldumpPath="D:/MY SQL DATABASE CLGERP/bin";
		String   mysqldumpPath="C:/Program Files/MySQL/MySQL Server 5.1/bin";
		Date dd=new Date();
		
		
		Calendar cl=Calendar.getInstance();
		
		
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String sysdate = df.format(dd);
		
		//String dsdsd=dd.toGMTString();
		
	    Boolean status = false;
	    String username = "root";
	    String password = "root";
	    String database = "saishwet";
	    
	    
	  //  String command = "/" + mysqldumpPath + "/mysqldump -u " + username + " -p" + password + " " + database + " -r " +"D:/BACKUP/nb_"+sysdate+"_"+cl.get(Calendar.HOUR)+"-"+cl.get(Calendar.MINUTE)+"-"+cl.get(Calendar.SECOND);
	    String command = "/" + mysqldumpPath + "/mysqldump -u"+username+" -p"+password+" "+database+ " -r " +"F:/DataBackup/SaiShwet_"+sysdate+"_"+cl.get(Calendar.HOUR)+"-"+cl.get(Calendar.MINUTE)+"-"+cl.get(Calendar.SECOND);
	    
	    //String command1 = "/" + mysqldumpPath + "/mysqldump -u " + username + " -p" + password + " " + database1 + " -r " +"D:/BACKUP/nb1_"+sysdate+"_"+cl.get(Calendar.HOUR)+"-"+cl.get(Calendar.MINUTE)+"-"+cl.get(Calendar.SECOND);
	   // String command1 = "/" + mysqldumpPath + "/mysqldump -u"+username+" -p"+password+" "+database1+" -r " +"D:/BACKUP/nb1_"+sysdate+"_"+cl.get(Calendar.HOUR)+"-"+cl.get(Calendar.MINUTE)+"-"+cl.get(Calendar.SECOND);
	    try 
	    {
	        Process runtimeProcess = Runtime.getRuntime().exec(command);
	        int processComplete = runtimeProcess.waitFor();
	        if (processComplete == 0)
	        {
	            System.out.println("DatabaseManager.backup: Backup Successfull");
	            status=true;
	        }
	        else
	        {
	            System.out.println("DatabaseManager.backup: Backup Failure!");
	            status=false;
	        }
	    } 
	    catch (IOException  ioe) 
	    {
	        ioe.printStackTrace();
	        status=false;
	    }
	    catch (Exception e) 
	    {
	        status=false;
	        e.printStackTrace();
	    }
	    
		return status;
	}
	
	public static void main(String[] args) {
		System.out.println(new DBUtil().deleteAllTables());
	}
}
