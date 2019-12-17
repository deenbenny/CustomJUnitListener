package com.servicenow.test.automation.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

public class HTTPClientRestHelper {

	private static final Logger logger = LoggerFactory.getLogger(HTTPClientRestHelper.class);
	private static int retryCounter = 0;

	private HTTPClientRestHelper() {
		//do nothing
	}

	/**
	 * This method is used to call the API
	 *
	 * @param url
	 * @param apiName
	 * @param auth
	 * @param headers
	 * @param methodType
	 * @param queryParam
	 * @param payload
	 * @return
	 */
	private static HttpResponse callAPI(final String url, final String apiName,
	                                    final Map<String, String> auth,
	                                    final Map<String, Object> headers,
	                                    final RestMethod.TYPE methodType,
	                                    final Map<String, String> queryParam,
	                                    final Map<String, String> formData,
	                                    final String payload) {
		if (retryCounter < 3) {
			HttpResponse response = null;
			try {

				HttpClient client;

				URIBuilder uriBuilder = new URIBuilder(url + apiName);

				if (auth != null && !auth.isEmpty()) {
					String header = "Basic ";
					String headerValue = auth.get("username") + ":" + auth.get("password");
					String encodedHeaderValue = Base64.encodeBase64String(headerValue.getBytes());
					String headerBasic = header + encodedHeaderValue;

					Header authHeader = new BasicHeader("Authorization", headerBasic);
					ArrayList<Header> headerArrayList = new ArrayList<>();
					headerArrayList.add(authHeader);

					if (headers != null) {
						/*Iterating the header map*/
						for (Map.Entry<String, Object> headersMapEntry : headers.entrySet()) {
							/*Building the headers for the resource*/
							authHeader = new BasicHeader(headersMapEntry.getKey(), headersMapEntry.getValue().toString());
							headerArrayList.add(authHeader);
						}
					}
					client = HttpClients.custom().setDefaultHeaders(headerArrayList).build();
				} else {
					ArrayList headerArrayList = new ArrayList<>();
					if (headers != null) {
						/*Iterating the header map*/

						for (String key : headers.keySet()) {
							/*Building the headers for the resource*/
							Header authHeader = new BasicHeader(key, headers.get(key).toString());
							headerArrayList.add(authHeader);
						}
						client = HttpClients.custom().setDefaultHeaders(headerArrayList).build();
					} else {
						client = HttpClients.custom().setDefaultHeaders(new ArrayList<>()).build();
					}
				}

				/*Checking whether the query param is not null for get methods*/
				if (queryParam != null) {
					/*Assigning the url, api path and query parameter*/
					if (null != queryParam && !queryParam.isEmpty()) {
						for (String key : queryParam.keySet()) {
							uriBuilder.setParameter(key, queryParam.get(key));
						}
					}
				}

				switch (methodType) {
					case GET:
						HttpGet getRequest = new HttpGet(uriBuilder.build());
						response = client.execute(getRequest);
						break;
					case POST:
						HttpPost postRequest = new HttpPost(uriBuilder.build());

						if (formData != null) {
							MultipartEntityBuilder builder = MultipartEntityBuilder.create();
							builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
							for (String key : formData.keySet()) {
								StringBody stringBody1 = new StringBody(formData.get(key), ContentType.MULTIPART_FORM_DATA);
								builder.addPart(key, stringBody1);
							}
							HttpEntity entity = builder.build();
							postRequest.setEntity(entity);
						} else {
							StringEntity input = new StringEntity(payload);
							input.setContentType(MediaType.APPLICATION_JSON);
							postRequest.setEntity(input);
						}

						response = client.execute(postRequest);
						break;
					case PUT:
						HttpPut putRequest = new HttpPut(uriBuilder.build());
						StringEntity inputEntity = new StringEntity(payload);
						inputEntity.setContentType(MediaType.APPLICATION_JSON);
						putRequest.setEntity(inputEntity);
						response = client.execute(putRequest);
						break;
					case DELETE:
						HttpDelete deleteRequest = new HttpDelete(uriBuilder.build());
						response = client.execute(deleteRequest);
						break;
					default:
						break;
				}

				/*Checking whether the response code is not 200*/
				if (response != null && response.getStatusLine().getStatusCode() != 200) {
					if (response.getStatusLine().getStatusCode() != 201) {
						System.out.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
					}
				}
				retryCounter = 0;
				return response;
			} catch (Exception error) {
				error.printStackTrace();
				logger.error("The exception occurred is in this method is : " + error);
				return response;
			}
		} else {
			logger.error("The exception occurred ");
			retryCounter = 0;
			return null;
		}
	}

	/**
	 * This method is used to call the get method without authentication and returns the response
	 *
	 * @param url
	 * @param apiName
	 * @param headers
	 * @param queryParam
	 * @return
	 */
	public static HttpResponse callGetAPI(final String url, final String apiName,
	                                      final Map<String, Object> headers,
	                                      final Map<String, String> queryParam) {
		return callAPI(url, apiName, null, headers, RestMethod.TYPE.GET, queryParam, null, null);
	}

	/**
	 * This method is used to call the get method with authentication and returns the response
	 *
	 * @param url
	 * @param apiName
	 * @param auth
	 * @param headers
	 * @param queryParam
	 * @return
	 */
	public static HttpResponse callGetAPIWithAuth(final String url, final String apiName, final Map<String, String> auth,
	                                              final Map<String, Object> headers,
	                                              final Map<String, String> queryParam) {
		return callAPI(url, apiName, auth, headers, RestMethod.TYPE.GET, queryParam, null, null);
	}

	/**
	 * This method is used to call the get method with authentication and returns the response
	 *
	 * @param url
	 * @param apiName
	 * @param auth
	 * @param headers
	 * @param queryParam
	 * @return
	 */
	public static HttpResponse callDeleteAPIWithAuth(final String url, final String apiName, final Map<String, String> auth,
	                                                 final Map<String, Object> headers) {
		return callAPI(url, apiName, auth, headers, RestMethod.TYPE.DELETE, null, null, null);
	}

	/**
	 * This method is used to call the post method without authentication and returns the response
	 *
	 * @param url
	 * @param apiName
	 * @param headers
	 * @param payload
	 * @return
	 */
	public static HttpResponse callPostAPI(final String url, final String apiName,
	                                       final Map<String, Object> headers,
	                                       final Map<String, String> formData,
	                                       final String payload) {
		return callAPI(url, apiName, null, headers, RestMethod.TYPE.POST, null, formData, payload);
	}

	/**
	 * This method is used to call the put method without authentication and returns the response
	 *
	 * @param url
	 * @param apiName
	 * @param headers
	 * @param payload
	 * @return
	 */
	public static HttpResponse callPutAPI(final String url, final String apiName,
	                                      final Map<String, Object> headers,
	                                      final String payload) {
		return callAPI(url, apiName, null, headers, RestMethod.TYPE.PUT, null, null, payload);
	}

	/**
	 * This method is used to call the delete method without authentication and returns the response
	 *
	 * @param url
	 * @param apiName
	 * @param headers
	 * @return
	 */
	public static HttpResponse callDeleteAPI(final String url, final String apiName,
	                                         final Map<String, Object> headers,
	                                         final Map<String, String> queryParam) {
		return callAPI(url, apiName, null, headers, RestMethod.TYPE.DELETE, queryParam, null, null);
	}

	/**
	 * This method is used to call the get method with authentication and returns the response
	 *
	 * @param url
	 * @param apiName
	 * @param auth
	 * @param headers
	 * @param payload
	 * @return
	 */
	public static HttpResponse callPostAPIWithAuth(final String url, final String apiName, final Map<String, String> auth,
	                                               final Map<String, Object> headers,
	                                               final Map<String, String> formData,
	                                               final String payload) {
		return callAPI(url, apiName, auth, headers, RestMethod.TYPE.POST, null, formData, payload);
	}

	public static HttpResponse callPostAPIWithAuthParams(final String url, final String apiName, final Map<String, String> auth,
	                                                     final Map<String, Object> headers,
	                                                     final Map<String, String> queryParam,
	                                                     final Map<String, String> formData,
	                                                     final String payload) {
		return callAPI(url, apiName, auth, headers, RestMethod.TYPE.POST, queryParam, null, payload);
	}

	/**
	 * This method is used to call the post method with parameters
	 *
	 * @param url
	 * @param apiName
	 * @param headers
	 * @param queryParam
	 * @return
	 */
	public static HttpResponse callPostAPIWithParams(String url, String apiName, Map<String, Object> headers,
	                                                 Map<String, String> queryParam, final Map<String, String> formData) {
		return HTTPClientRestHelper.callAPI(url, apiName, null, headers, RestMethod.TYPE.POST, queryParam, formData, "");
	}

	public static String getPayloadFromResponse(final HttpResponse response) {
		try {
			/*Reading the response in the string format*/
			HttpEntity entity = response.getEntity();
			final InputStream instream = entity.getContent();
			StringBuilder str = new StringBuilder();
			Scanner sc = new Scanner(instream, "UTF-8");
			while (sc.hasNextLine()) {
				str.append(sc.nextLine());
			}
			return str.toString();
		} catch (Exception error) {
			error.printStackTrace();
			return null;
		}
	}

	public static LinkedTreeMap<?, ?> getResponseAsHashMap(String payload) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().create();
		return gson.fromJson(payload, LinkedTreeMap.class);
	}

	public static <T> T getResponseAsClass(String payload, Class<T> tClass) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.serializeNulls().create();
		return gson.fromJson(payload, tClass);
	}

	public static HttpResponse callPutAPI(final String url, final String apiName,
	                                      final Map<String, Object> headers, final Map<String, String> queryParam,
	                                      final String payload) {
		return callAPI(url, apiName, null, headers, RestMethod.TYPE.PUT, queryParam, null, payload);
	}

	public static HttpResponse callPutAPIWithAuthParams(final String url, final String apiName, final Map<String, String> auth,
	                                                    final Map<String, Object> headers,
	                                                    final Map<String, String> queryParam,
	                                                    final Map<String, String> formData,
	                                                    final String payload) {
		return callAPI(url, apiName, auth, headers, RestMethod.TYPE.PUT, queryParam, formData, payload);
	}

}

class RestMethod {

	public enum TYPE {
		POST, GET, PUT, DELETE, MULTIPARTPOST, MULTIPARTGET
	}
}
