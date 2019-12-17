package com.servicenow.test.automation.config;

import java.io.FileReader;
import java.util.Properties;

public class Constants {
	public static final String INSTANCE_URL;
	public static final String USERNAME;
	public static final String PASSWORD;
	public static final String TABLE_API = "api/now/table/";
	public static final String AUTOMATION_JOB_TABLE = "u_automation_job";
	public static final String AUTOMATION_REPORT_TABLE = "u_automation_report";
	public static final String AUTOMATION_COMPONENTS_TABLE = "u_automation_components";
	public static final String AUTOMATION_FEATURE_TABLE = "u_automation_feature";
	public static final String AUTOMATION_PROJECT_NAME;
	public static final String toMail;
	public static final String ccMail;
	public static final String fromMail;
	public static final String mailUserName;
	public static final String mailPassword;

	static {
		Properties properties = new Properties();
		try {
			FileReader reader = new FileReader(System.getProperty("user.dir") + "/config/instance_config.properties");
			properties.load(reader);
		} catch (Exception error) {
			error.printStackTrace();
		} finally {
			INSTANCE_URL = properties.getProperty("instance_url");
			USERNAME = properties.getProperty("username");
			PASSWORD = properties.getProperty("password");
			AUTOMATION_PROJECT_NAME = properties.getProperty("project_name");
			toMail = properties.getProperty("to_mail");
			fromMail = properties.getProperty("from_mail");
			ccMail = properties.getProperty("cc_mail");
			mailUserName = properties.getProperty("username_mail");
			mailPassword = properties.getProperty("password_mail");
		}


	}


}
