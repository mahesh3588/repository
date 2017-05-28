package dao;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Backup {
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
	new Backup().getBackUp();	
	}
}
