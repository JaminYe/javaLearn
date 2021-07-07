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
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	/**
	 * 文件读取获取Properties
	 *
	 * @param filePath
	 * @return {@link Properties}
	 * @author Jamin
	 * @date 2021/7/7 15:08
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

