package edu.bsuir.mySQLTestLib.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ResourceBundleSettingSource {

	private ResourceBundleSettingSource() throws ConfigurationException {
		initializeProperties();
	}

	private static Properties properties;

	public static Properties getProperties() {
		return properties;
	}

	public static void initializeProperties() throws ConfigurationException {
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("application.properties");
		properties = new Properties();
		try {
			if (inputStream != null)
				properties.load(inputStream);
			else
				throw new FileNotFoundException();
		} catch (IOException e) {
			throw new ConfigurationException(e);
		}
	}
}
