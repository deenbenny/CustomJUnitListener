package com.servicenow.test.automation.util;

import static com.servicenow.test.automation.config.Constants.AUTOMATION_COMPONENTS_TABLE;
import static com.servicenow.test.automation.config.Constants.AUTOMATION_FEATURE_TABLE;
import static com.servicenow.test.automation.config.Constants.AUTOMATION_PROJECT_NAME;
import static com.servicenow.test.automation.config.Constants.INSTANCE_URL;
import static com.servicenow.test.automation.config.Constants.PASSWORD;
import static com.servicenow.test.automation.config.Constants.TABLE_API;
import static com.servicenow.test.automation.config.Constants.USERNAME;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class GlideAPIUtils {

	public static final Map<String, Object> HEADERS = new HashMap<>();
	public static final String SYS_LIMIT = "sysparm_limit";
	public static final String GLIDE_TABLE_API = "/api/now/table/";

	static {
		HEADERS.put("Content-Type", "application/json");
	}

	private GlideAPIUtils() {
		//do nothing
	}

	/**
	 * This method is used to get the authentication
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Map<String, String> getAuthentication(final String userName,
	                                                    final String password) {
		Map<String, String> authentication = new HashMap<>();
		authentication.put("username", userName);
		authentication.put("password", password);
		return authentication;
	}

	/**
	 * To create records to the table
	 *
	 * @param instanceURL
	 * @param userName
	 * @param password
	 * @param tableName
	 * @param jsonObject
	 * @return
	 */
	public static JSONObject insertRecord(final String instanceURL,
	                                      final String userName,
	                                      final String password,
	                                      final String apiPath,
	                                      final String apiMethod,
	                                      final JSONObject jsonObject) {
		try {
			HttpResponse response = HTTPClientRestHelper.callPostAPIWithAuthParams(instanceURL + apiPath,
					apiMethod, getAuthentication(userName, password), HEADERS, null, null, jsonObject.toString());
			String resp = HTTPClientRestHelper.getPayloadFromResponse(response);
			JSONObject json = new JSONObject(resp);
			if (json.has("result")) {
				return json.getJSONObject("result");
			} else {
				return json;
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return new JSONObject();
	}

	/**
	 * @param instanceURL
	 * @param userName
	 * @param password
	 * @param tableName
	 * @param queryParam
	 * @return
	 */
	public static String getSysIDFromTable(final String instanceURL,
	                                       final String userName,
	                                       final String password,
	                                       final String tableName,
	                                       final Map<String, String> queryParam) {
		try {
			JSONArray jsonArray = getListFromTable(instanceURL, userName, password, tableName, queryParam);
			if (jsonArray.length() != 0) {
				JSONObject json = (JSONObject) jsonArray.get(0);
				if (json.has("sys_id")) {
					return json.getString("sys_id");
				}
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return null;
	}

	/**
	 * @param instanceURL
	 * @param userName
	 * @param password
	 * @param tableName
	 * @param queryParam
	 * @return
	 */
	public static JSONArray getListFromTable(final String instanceURL,
	                                         final String userName,
	                                         final String password,
	                                         final String tableName,
	                                         final Map<String, String> queryParam) {
		return getListFromTable(instanceURL, userName, password, GLIDE_TABLE_API, tableName, queryParam);
	}

	/**
	 * This method is used to get the list of values from the instance
	 *
	 * @param instanceURL
	 * @param userName
	 * @param password
	 * @param apiUrl
	 * @param apiName
	 * @param queryParam
	 * @return
	 */
	public static JSONArray getListFromTable(final String instanceURL,
	                                         final String userName,
	                                         final String password,
	                                         final String apiUrl,
	                                         final String apiName,
	                                         final Map<String, String> queryParam) {
		try {
			HttpResponse response = HTTPClientRestHelper.callGetAPIWithAuth(instanceURL + apiUrl,
					apiName, getAuthentication(userName, password), HEADERS, queryParam);
			JSONObject jsonObject = new JSONObject(HTTPClientRestHelper.getPayloadFromResponse(response));
			if (jsonObject.has("result")) {
				return jsonObject.getJSONArray("result");
			} else {
				return new JSONArray();
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return new JSONArray();
	}


	public static String getAutomationComponents() {
		Map<String, String> queryPara = new HashMap<>();
		queryPara.put("u_component_name", AUTOMATION_PROJECT_NAME);
		HttpResponse response = HTTPClientRestHelper.callGetAPIWithAuth(INSTANCE_URL + TABLE_API + "/",
				AUTOMATION_COMPONENTS_TABLE, getAuthentication(USERNAME, PASSWORD), HEADERS, queryPara);
		JSONObject jsonObject = new JSONObject(HTTPClientRestHelper.getPayloadFromResponse(response));
		if (jsonObject.toString().equals("{\"result\":[]}")) {
			JSONObject payload = new JSONObject();
			payload.put("u_component_name", AUTOMATION_PROJECT_NAME);
			HttpResponse response1 = HTTPClientRestHelper.callPostAPIWithAuthParams(INSTANCE_URL + TABLE_API + "/",
					AUTOMATION_COMPONENTS_TABLE, getAuthentication(USERNAME, PASSWORD), HEADERS, null, null, payload.toString());
			String resp = HTTPClientRestHelper.getPayloadFromResponse(response1);
			JSONObject json = new JSONObject(resp);
			if (json.has("result")) {
				JSONObject childObject = json.getJSONObject("result");
				return childObject.get("sys_id").toString();
			}
		}
		if (jsonObject.has("result")) {
			JSONArray array = jsonObject.getJSONArray("result");
			JSONObject obj = array.getJSONObject(0);
			return obj.get("sys_id").toString();
		}
		return null;
	}


	public static String getAutomationFeature(String featureName, String componentSysID) {
		Map<String, String> queryPara = new HashMap<>();
		queryPara.put("u_feature_name", featureName);
		HttpResponse response = HTTPClientRestHelper.callGetAPIWithAuth(INSTANCE_URL + TABLE_API + "/",
				AUTOMATION_FEATURE_TABLE, getAuthentication(USERNAME, PASSWORD), HEADERS, queryPara);
		JSONObject jsonObject = new JSONObject(HTTPClientRestHelper.getPayloadFromResponse(response));
		if (jsonObject.toString().equals("{\"result\":[]}")) {
			JSONObject payload = new JSONObject();
			payload.put("u_component_name", componentSysID);
			payload.put("u_feature_name", featureName);
			HttpResponse response1 = HTTPClientRestHelper.callPostAPIWithAuthParams(INSTANCE_URL + TABLE_API + "/",
					AUTOMATION_FEATURE_TABLE, getAuthentication(USERNAME, PASSWORD), HEADERS, null, null, payload.toString());
			String resp = HTTPClientRestHelper.getPayloadFromResponse(response1);
			JSONObject json = new JSONObject(resp);
			if (json.has("result")) {
				JSONObject childObject = json.getJSONObject("result");
				return childObject.get("sys_id").toString();
			}
		}
		if (jsonObject.has("result")) {
			JSONArray array = jsonObject.getJSONArray("result");
			JSONObject obj = array.getJSONObject(0);
			return obj.get("sys_id").toString();
		}
		return null;
	}

	public static Set<String> getAutomationFeatureName() {
		Set<String> set = new HashSet<>();
		HttpResponse response = HTTPClientRestHelper.callGetAPIWithAuth(INSTANCE_URL + TABLE_API + "/",
				AUTOMATION_FEATURE_TABLE, getAuthentication(USERNAME, PASSWORD), HEADERS, null);
		JSONObject jsonObject = new JSONObject(HTTPClientRestHelper.getPayloadFromResponse(response));
		if (jsonObject.has("result")) {
			JSONArray array = jsonObject.getJSONArray("result");
			for (int i = 0; i <= array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				set.add(obj.get("u_feature_name").toString());

			}
			return set;
		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println(getAutomationComponents());
	}

}
