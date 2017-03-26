package edu.bsuir.mySQLTestLib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import edu.bsuir.mySQLTestLib.config.ConfigurationException;
import edu.bsuir.mySQLTestLib.config.ResourceBundleSettingSource;

public abstract class AbstractDAO {

	private static BasicDataSource dataSource = new BasicDataSource();
	private static Properties properties = ResourceBundleSettingSource.getProperties();
	protected  final Logger log = LogManager.getLogger(AbstractDAO.class);

	static {
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(properties.getProperty("application.database.properties.url"));
		dataSource.setUsername(properties.getProperty("application.database.properties.user"));
		dataSource.setPassword(properties.getProperty("application.database.properties.password"));
		dataSource.setMaxActive(-1);
		dataSource.setMaxIdle(10);
		dataSource.setMaxWait(3);
		
	}

	public AbstractDAO() {

	}

	public abstract void Methods( Map<String, String> response);

	public abstract List<?> getAll() throws ConfigurationException, SQLException;

	public ResultSet select(String query) throws ConfigurationException, SQLException {
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			log.info("SELECT executed.");
		} catch (SQLException e) {
			log.warn("SELECT failed.");
			throw new ConfigurationException(e);
		} finally {
		
		}
		return rs;
	}
    
    public PreparedStatement prepareStatement(String query) throws ConfigurationException {
        PreparedStatement pstmt = null;
        Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			log.info("Query prepared.");
		} catch (SQLException e) {
			log.warn("Query preparation failed.");
			throw new ConfigurationException(e);
		}
        
        return pstmt;
    }


	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
