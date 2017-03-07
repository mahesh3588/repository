package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
	private static SessionFactory sessionFactory;
	
	static{
		sessionFactory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		if(sessionFactory!=null)
			System.out.println("session factory created");
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
