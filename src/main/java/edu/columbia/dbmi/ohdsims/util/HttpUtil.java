package edu.columbia.dbmi.ohdsims.util;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpUtil {
	public static String doPost(String url, JSONObject jjj) {
		try {
			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("Content-Type", "application/json");
			StringEntity se = new StringEntity(jjj.toString());
			httppost.setEntity(se);
			HttpResponse httpresponse = new DefaultHttpClient().execute(httppost);

			if (httpresponse.getStatusLine().getStatusCode() == 200) {
				String strResult = EntityUtils.toString(httpresponse.getEntity());
				return strResult;
			} else {
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}

	}

	public static String doGet(String url) {
		try {
			HttpGet httpget = new HttpGet(url);
			HttpResponse httpresponse = new DefaultHttpClient().execute(httpget);
			if (httpresponse.getStatusLine().getStatusCode() == 200) {
				String strResult = EntityUtils.toString(httpresponse.getEntity());
				return strResult;
			} else {
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}

	}

	public static void doPut(String urlstr,String json) {
		try {
	        URL url = new URL(urlstr);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("PUT");
	        connection.setDoOutput(true);
	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.setRequestProperty("Accept", "application/json");
	        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
	        osw.write(json);
	        osw.flush();
	        osw.close();
	        System.err.println(connection.getResponseCode());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}
	}
}
