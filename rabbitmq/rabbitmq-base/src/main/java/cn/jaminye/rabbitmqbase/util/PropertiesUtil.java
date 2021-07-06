package cn.jaminye.rabbitmqbase.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * 读取配置文件
 *
 * @author Jamin
 * @date 2021/7/6 21:33
 */
public class PropertiesUtil {
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class.getName());

	/**
	 * 根据key读取value
	 *
	 * @param filePath
	 * @param key
	 * @return
	 */
	public static String readValue(String filePath, String key) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			if (in != null) {
				prop.load(in);
			}
			return prop.getProperty(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}


	/**
	 * 获取配置文件
	 *
	 * @param filePath
	 * @return
	 */
	public static Properties getProperties(String filePath) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			File file = new File(filePath);
//          直接读取文件
			if (file.canRead()) {
				in = new BufferedInputStream(new FileInputStream(file));
//          从当前路径中获取文件流
			} else {
				in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
			}
			if (in != null) {
				prop.load(in);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return prop;
	}

}

