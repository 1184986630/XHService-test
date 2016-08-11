package cn.ffcs.xhService.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class GetConfig {
	// 属性文件的路径
	static String profilepath = Thread.currentThread().getContextClassLoader().getResource("/config.properties").getPath();
	
	public static String readValue(String filePath, String key) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			BufferedReader bf = new BufferedReader(new InputStreamReader(in, "utf-8"));
			props.load(bf);
			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public final static String PAY_METHOD_iNFO = readValue(profilepath,
			"payMethodInfo");
}
