/**
 * 
 */
package cn.ctx.api.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author lenovo
 *
 */
public class GlobalConfig {

	private static Logger LOG = LoggerFactory.getLogger(GlobalConfig.class);

	private static GlobalConfig instance = null;

	private static final String GLOBAL_CONFIG_FILE = "/application.properties";

	private Properties props;

	private GlobalConfig() {
	}

	public static synchronized GlobalConfig getInstance() {
		if (instance == null) {
			instance = new GlobalConfig();
			try {
				instance.props = load(GLOBAL_CONFIG_FILE);
			} catch (IOException e) {
				LOG.error("Load global configuration file error: " + GLOBAL_CONFIG_FILE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public void loadConfiguration(String fileName) {
		InputStream in = null;
		try {
			in = new FileInputStream(fileName);
			props = new Properties();
			props.load(in);
		} catch (IOException e) {
			LOG.error("Load configuration file error: " + fileName, e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public String get(String key) {
		return (props != null) ? props.getProperty(key) : null;
	}

	public int getInt(String key) {
		String val = get(key);
		return (val != null) ? Integer.parseInt(val) : 0;
	}

	public boolean getBool(String key) {
		String val = get(key);
		return (val != null) ? "true".equals(val) : false;
	}
	
	public static Properties load(String file) throws Exception {
		InputStream in = null;
		try {
			in = Configuration.class.getResourceAsStream(file);
			if (in == null) {
				throw new Exception("File '" + file + "' not found");
			}
			Properties properties = new Properties();
			properties.load(in);
			return properties;
		} catch (IOException e) {
			throw e;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
