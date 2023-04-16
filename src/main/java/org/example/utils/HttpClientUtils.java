package org.example.utils;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {

	/**
	 * 创建HttpClient对象(连接池)
	 * 
	 * @return httpClient对象
	 */
	public static CloseableHttpClient getHttpClient() {
		// 定义HttpClient对象
		CloseableHttpClient httpClient = null;
		try {
			LayeredConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(SSLContext.getDefault());
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("https", socketFactory).register("http", PlainConnectionSocketFactory.INSTANCE).build();
			// 创建连接池对象
			PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager(registry);
			// 设置最大连接数
			pool.setMaxTotal(200);
			// 设置每个路由基础的连接数
			pool.setDefaultMaxPerRoute(20);

			httpClient = HttpClients.custom().setConnectionManager(pool).build();
			// 设置代理
			// HttpHost proxy = new HttpHost("10.1.27.102", 8080);
			// DefaultProxyRoutePlanner routePlanner = new
			// DefaultProxyRoutePlanner(proxy);
			// 将目标主机的最大连接数增加到50
			// pool.setMaxPerRoute(new HttpRoute(proxy), 50);
			// httpClient =
			// HttpClients.custom().setRoutePlanner(routePlanner).setConnectionManager(pool).build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return httpClient;
	}

	/**
	 * HTTP GET请求方法
	 * 
	 * @param url
	 *            请求路径
	 * @param param
	 *            请求参数
	 * @return JSON 响应结果
	 */
	public static String doGet(String url, Map<String, String> param) {
		String json = null;
		CloseableHttpResponse response = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			// 创建uri
			URI uri = builder.build();
			// 创建HTTP GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 执行请求
			response = getHttpClient().execute(httpGet);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				json = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}

	/**
	 * HTTP GET请求方法
	 * 
	 * @param url
	 *            请求路径
	 * @param param
	 *            请求参数
	 * @return JSON 响应结果
	 */
	public static String doGetForm(String url, Map<String, String> param, String sessionId) {
		String json = null;
		CloseableHttpResponse response = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			// 创建uri
			URI uri = builder.build();
			// 创建HTTP GET请求
			HttpGet httpGet = new HttpGet(uri);
			httpGet.addHeader("Cookie", "PHPSESSID=" + sessionId);
			// 执行请求
			response = getHttpClient().execute(httpGet);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				json = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}
	/**
	 * HTTP POST请求方法
	 * 
	 * @param url
	 *            请求路径
	 * @param param
	 *            请求参数
	 * @return JSON 响应结果
	 */
	public static String doPostForm(String url, Map<String, String> param, String sessionId) {
		String json = null;
		CloseableHttpResponse response = null;
		try {
			// 创建HTTP POST请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Cookie", "PHPSESSID=" + sessionId);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
				httpPost.setEntity(entity);
			}
			// 执行HTTP请求
			response = getHttpClient().execute(httpPost);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				json = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}

	/**
	 * HTTP POST请求方法
	 * 
	 * @param url
	 *            请求路径
	 * @param param
	 *            请求参数
	 * @return JSON 响应结果
	 */
	public static String doPost(String url, Map<String, String> param) {
		String json = null;
		CloseableHttpResponse response = null;
		try {
			// 创建HTTP POST请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
				httpPost.setEntity(entity);
			}
			// 执行HTTP请求
			response = getHttpClient().execute(httpPost);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				json = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}

	/**
	 * 通过Https往API post xml数据
	 *
	 * @param url
	 *            API地址
	 *            要提交的XML数据对象
	 * @return API回包的实际数据
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */

	public static String sendPost(String url, String postDataXML) throws IOException, KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {

		String result = null;

		HttpPost httpPost = new HttpPost(url);

		StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.setEntity(postEntity);

		//httpPost.setConfig(requestConfig);

		try {
			HttpResponse response = getHttpClient().execute(httpPost);

			HttpEntity entity = response.getEntity();

			result = EntityUtils.toString(entity, "UTF-8");

		} catch (ConnectionPoolTimeoutException e) {
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.abort();
		}

		return result;
	}

	/**
	 * HTTP GET请求方法
	 * 
	 * @param url
	 *            请求路径
	 * @return XML 响应结果
	 */
	public static String doGetRequestXML(String url) {
		String xml = null;
		CloseableHttpResponse response = null;
		try {
			// 创建HTTP GET请求
			HttpGet httpGet = new HttpGet(url);
			// 执行请求
			response = getHttpClient().execute(httpGet);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				StringBuffer buffer = new StringBuffer();
				InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
				Reader reader = new BufferedReader(isr);
				int ch;
				while ((ch = reader.read()) > -1) {
					buffer.append((char) ch);
				}
				xml = buffer.toString().trim();
				buffer = null;
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return xml;
	}

	/**
	 * HTTP GET请求方法
	 * 
	 * @param url
	 *            请求路径
	 * @return JSON 响应结果
	 */
	public static String doGetRequestJson(String url) {
		String json = null;
		CloseableHttpResponse response = null;
		try {
			// 创建HTTP GET请求
			HttpGet httpGet = new HttpGet(url);
			// 执行请求
			response = getHttpClient().execute(httpGet);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				json = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}
	/**
	 * HTTP GET请求方法
	 * 
	 * @param url
	 *            请求路径
	 * @param param
	 *            请求参数
	 * @return JSON 响应结果
	 */
	public static String doGet(String url,String hearder, Map<String, String> param) {
		String json = null;
		CloseableHttpResponse response = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			// 创建uri
			URI uri = builder.build();
			// 创建HTTP GET请求
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("apikey", hearder);
			// 执行请求
			response = getHttpClient().execute(httpGet);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				json = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}
	
	/**
	 * HTTP POST请求方法
	 * 
	 * @param url
	 *            请求路径
	 * @param json
	 *            请求参数
	 * @return
	 */
	public static String doPostJson(String url, String json) {
		String result = null;
		CloseableHttpResponse response = null;
		try {
			// 创建HTTP POST请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			if (!StringUtils.isBlank(json)) {
				StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
				httpPost.setEntity(entity);
			}
			// 执行HTTP请求
			response = getHttpClient().execute(httpPost);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				result = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("user", "dadi");
		map.put("token", "1234567890");
		String json = doPost("http://www.kangronghui.com/external.php/External/getSessionId", map);
		Map<String, Object> map2 = new Gson().fromJson(json, HashMap.class);
		String sessionId = (String) ((Map<String, Object>) map2.get("data")).get("sessionId");
		// System.out.println(sessionId);
		json = doGetForm("http://www.kangronghui.com/external.php/External/getGoodsList", null, sessionId);
		System.out.println(json);
		Map<String, Object> map3 = new Gson().fromJson(json, HashMap.class);
		System.out.println(map3);

		/*
		 * json = doGetForm(
		 * "http://www.kangronghui.com/external.php/External/getCategoryList",
		 * null, sessionId); //System.out.println(json); Map<String, Object>
		 * map3 = new Gson().fromJson(json, HashMap.class);
		 * //System.out.println(map3); List<Map<String, Object>> list =
		 * (List<Map<String, Object>>) map3.get("data");
		 * //System.out.println(list); for (int i = 0; i < list.size(); i++) {
		 * Map map4 = new HashMap(); map4.put("categoryId",
		 * list.get(i).get("id")); //System.out.println(map5); json = doGetForm(
		 * "http://www.kangronghui.com/external.php/External/getSubcategoryList",
		 * map4, sessionId); //System.out.println(json); Map<String, Object>
		 * map5 = new Gson().fromJson(json, HashMap.class);
		 * //System.out.println(map6); if (map5.get("data").toString().length()
		 * > 0) { List<Map<String, Object>> list2 = (List<Map<String, Object>>)
		 * map5.get("data"); //System.out.println(list2); for (int j = 0; j <
		 * list2.size(); j++) { //System.out.println(list2.get(j)); json =
		 * doGetForm(
		 * "http://www.kangronghui.com/external.php/External/getGoodsList",
		 * null, sessionId); System.out.println(json); } } }
		 */
	}

	public static String guDongGet(String url, Map<String, String> param , String token) {
		String json = null;
		CloseableHttpResponse response = null;
		try {
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			// 创建uri
			URI uri = builder.build();
			// 创建HTTP GET请求
			HttpGet httpGet = new HttpGet(uri);
			httpGet.addHeader("Authorization", token);
			httpGet.addHeader("Content-Type", "application/json");
			// 执行请求
			response = getHttpClient().execute(httpGet);
			InputStream inputStream = null;
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				inputStream = response.getEntity().getContent();
			}
			if (inputStream != null) {
				json = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;
	}

}