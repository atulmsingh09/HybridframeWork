package com.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties prop;

	public ReadConfig() {
		File src = new File("./Configuration/config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getApplicationURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}

	public String getUserID() {
		String userID = prop.getProperty("username");
		return userID;
	}

	public String getPassword() {
		String password = prop.getProperty("password");
		return password;
	}

	public String getChromePath() {
		String chromPath = prop.getProperty("chromepath");
		return chromPath;
	}

	public String getGeckoPath() {
		String geckoPath = prop.getProperty("firefoxpath");
		return geckoPath;
	}

	public String getIEPath() {
		String iePath = prop.getProperty("iepath");
		return iePath;
	}

}
