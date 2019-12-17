package com.servicenow.test.automation.listener;

import static com.servicenow.test.automation.config.Constants.AUTOMATION_JOB_TABLE;
import static com.servicenow.test.automation.config.Constants.AUTOMATION_REPORT_TABLE;
import static com.servicenow.test.automation.config.Constants.INSTANCE_URL;
import static com.servicenow.test.automation.config.Constants.PASSWORD;
import static com.servicenow.test.automation.config.Constants.TABLE_API;
import static com.servicenow.test.automation.config.Constants.USERNAME;
import static com.servicenow.test.automation.config.Constants.ccMail;
import static com.servicenow.test.automation.config.Constants.toMail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.servicenow.test.automation.annotation.Report;
import com.servicenow.test.automation.config.Constants;
import com.servicenow.test.automation.model.EmailReport;
import com.servicenow.test.automation.util.GlideAPIUtils;
import com.servicenow.test.automation.util.SendEmail;

public class CustomJunitListener extends RunListener {

	boolean status;

	List<EmailReport> fEmailReportList;
	Map<String, Integer> passed;
	Map<String, Integer> failed;
	Map<String, Integer> skipped;

	List<JSONObject> fJSONObjectList;
	List<String> featureList;

	private static String getSerializedKey(Field field) {
		String annotationValue = field.getAnnotation(Report.class).testCaseName();
		if (annotationValue.isEmpty()) {
			return field.getName();
		} else {
			return annotationValue;
		}
	}

	public void getAnnotationValues(final Class<?> objectClass,
	                                final String methodName, final String status, final String exception) {
		try {
			System.out.println("==========================================================================================");
			for (Method method : objectClass.getDeclaredMethods()) {
				if (method.getName().equals(methodName)) {
					method.setAccessible(true);
					if (method.isAnnotationPresent(Report.class)) {
						Report report = method.getAnnotation(Report.class);

						if (status.equals("failed")) {
							if (!failed.isEmpty() && failed.containsKey(report.featureName())) {
								failed.put(report.featureName(), failed.get(report.featureName()) + 1);
							} else {
								failed.put(report.featureName(), 1);
							}
						} else if (status.equals("ignored")) {
							if (!skipped.isEmpty() && skipped.containsKey(report.featureName())) {
								skipped.put(report.featureName(), skipped.get(report.featureName()) + 1);
							} else {
								skipped.put(report.featureName(), 1);
							}
						} else {
							if (!passed.isEmpty() && passed.containsKey(report.featureName())) {
								passed.put(report.featureName(), passed.get(report.featureName()) + 1);
							} else {
								passed.put(report.featureName(), 1);
							}
						}

						if (!featureList.contains(report.featureName())) {
							featureList.add(report.featureName());
						}

						System.out.println("The Class Name is " + objectClass.getName() + " and the method name is :: " + methodName);
						System.out.println("Test case Name : " + report.testCaseName());
						System.out.println("Test case description : " + report.testCaseDescription());

						JSONObject jsonObject = new JSONObject();
						jsonObject.put("u_testcase_description", report.testCaseDescription());
						jsonObject.put("u_exception", exception);
						jsonObject.put("u_job_id", "1");
						jsonObject.put("u_status", status);
						jsonObject.put("u_testcase_name", report.testCaseName());
						jsonObject.put("u_feature_name", report.featureName());

						fJSONObjectList.add(jsonObject);
					}
				}
			}
			System.out.println("==========================================================================================");
			System.out.println();


		} catch (Exception error) {
			error.printStackTrace();
		}
	}

	private String toJsonString(Map<String, String> jsonMap) {
		String elementsString = jsonMap.entrySet()
				.stream()
				.map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
				.collect(Collectors.joining(","));
		return "{" + elementsString + "}";
	}

	/**
	 * Called before any tests have been run.
	 */
	public void testRunStarted(Description description) {
		passed = new HashMap<>();
		failed = new HashMap<>();
		skipped = new HashMap<>();
		fJSONObjectList = new ArrayList<>();
		featureList = new ArrayList<>();
		fEmailReportList = new ArrayList<>();
	}

	/**
	 * Called when all tests have finished
	 */
	public void testRunFinished(Result result) {

		String componentSysId = GlideAPIUtils.getAutomationComponents();
		System.out.println("feature List is ::" + featureList);
		System.out.println("Number of tests executed : " + result.getRunCount());
		JSONObject jsonObject = new JSONObject();

		for (String feature : featureList) {
			String featureSysID = GlideAPIUtils.getAutomationFeature(feature, componentSysId);
			int total = 0;
			if (failed.containsKey(feature)) {
				jsonObject.put("u_total_failed", failed.get(feature));
				total = total + failed.get(feature);
			} else {
				jsonObject.put("u_total_failed", 0);
			}
			if (passed.containsKey(feature)) {
				jsonObject.put("u_total_passed", passed.get(feature));
				total = total + passed.get(feature);
			} else {
				jsonObject.put("u_total_passed", 0);
			}
			if (skipped.containsKey(feature)) {
				jsonObject.put("u_total_skipped", skipped.get(feature));
				total = total + skipped.get(feature);
			} else {
				jsonObject.put("u_total_skipped", 0);
			}

			jsonObject.put("u_total_testcases", total);
			//String actualFeatureName = jsonObject.getString("u_feature_name");
			jsonObject.put("u_feature_name", featureSysID);
			jsonObject.put("u_component_name", componentSysId);

			EmailReport emailReport = new EmailReport();
			emailReport.setTotal(total);
			emailReport.setPassed(jsonObject.getInt("u_total_passed"));
			emailReport.setSkipped(jsonObject.getInt("u_total_skipped"));
			emailReport.setFailed(jsonObject.getInt("u_total_failed"));
			emailReport.setFeatureName(feature);

			fEmailReportList.add(emailReport);

			JSONObject json = GlideAPIUtils.insertRecord(INSTANCE_URL,
					USERNAME, PASSWORD, TABLE_API, AUTOMATION_JOB_TABLE, jsonObject);
			if (json != null || !json.isEmpty()) {
				String sysId = json.getString("sys_id");
				System.out.println("The json is :: " + sysId);

				for (JSONObject jsonObj : fJSONObjectList) {
					if (feature.equals(jsonObj.getString("u_feature_name"))) {
						jsonObj.put("u_job_id", sysId);
						jsonObj.put("u_feature_name", featureSysID);
						jsonObj.put("u_comp_id", componentSysId);
						JSONObject jsonResult = GlideAPIUtils.insertRecord(INSTANCE_URL, USERNAME, PASSWORD, TABLE_API,
								AUTOMATION_REPORT_TABLE, jsonObj);
						System.out.println("The json is :: " + jsonResult);
					}
				}
			}
		}
		SendEmail.triggerMail("Automation Report for " + Constants.AUTOMATION_PROJECT_NAME,
				"Automation Test Results", "Please find the Automation Report", toMail, ccMail, fEmailReportList);
	}

	/**
	 * Called when an atomic test is about to be started.
	 */
	public void testStarted(Description description) {
		status = true;
	}

	/**
	 * Called when an atomic test has finished, whether the test succeeds or fails.
	 */
	public void testFinished(Description description) {
		if (status) {
			System.out.println("++++++++++++++++++++++++++++++++ Test case passed ++++++++++++++++++++++++++++++++");
			getAnnotationValues(description.getTestClass(), description.getMethodName(), "passed", "");
		}
	}

	/**
	 * Called when an atomic test fails.
	 */
	public void testFailure(Failure failure) {
		status = false;
		System.out.println("XXXxxxxxxxxxxxxxxxxxxx Test case failed XXXxxxxxxxxxxxxxxxxxxx");
		System.out.println("The failure message is : " + failure.getMessage());
		System.out.println("The exception is : " + failure.getException());
		getAnnotationValues(failure.getDescription().getTestClass(),
				failure.getDescription().getMethodName(), "failed",
				"The Message :: " + failure.getMessage() + "\nThe Exceeption is :: " + failure.getException().toString());
	}

	/**
	 * Called when a test will not be run, generally because a test method is annotated with Ignore.
	 */
	public void testIgnored(Description description) {
		status = false;
		System.out.println("-------------------- Test case ignored --------------------------------");
		getAnnotationValues(description.getTestClass(), description.getMethodName(), "ignored", "Test case ignored");
	}

}
