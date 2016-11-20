package lee.HttpClient;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Repository;

@Repository
public class TestListener implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("TestListener start");
	}
 
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
