package edu.bsuir.mySQLTestLib.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class ConfigurationInisialisator
 *
 */
public final class ConfigurationInisialisator implements ServletContextListener {

    public ConfigurationInisialisator() {
        
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	try {
			ResourceBundleSettingSource.initializeProperties();
		} catch (ConfigurationException e) {
			new RuntimeException(e);
		}
    }
	
}
