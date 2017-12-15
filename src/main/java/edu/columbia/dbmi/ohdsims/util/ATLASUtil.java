package edu.columbia.dbmi.ohdsims.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.RequestEntity;
//import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import edu.columbia.dbmi.ohdsims.pojo.Concept;
import edu.columbia.dbmi.ohdsims.pojo.ConceptSet;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

/*
* 使用的类：
* HttpClient
* HttpPost
* FileBody
* FormBodyPart
* MultipartEntity
* HttpResponse
* EntityUtils
* 
*/

public class ATLASUtil {
	// 接口地址
	private final static String recordcounturl = "http://api.ohdsi.org/WebAPI/cdmresults/1PCT/conceptRecordCount";
	private final static String vocubularyurl = "http://api.ohdsi.org/WebAPI/vocabulary/1PCT/search";
	private final static String conceptseturl = "http://api.ohdsi.org/WebAPI/conceptset/";
	private final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
	private HttpClient httpClient = null;
	private HttpPost method = null;
	private static long startTime = 0L;
	private static long endTime = 0L;
	private int status = 0;

	public static void main(String[] args) throws IOException {
		// Concept concept=getMaxCountConcept("type 2 diabetes","Condition");
		// System.out.println("----->"+concept.getCONCEPT_NAME());
		// System.out.println("conceptid="+conceptidindex.get(rindex));
		//searchConcept("insulin", "Drug");
		//searchConceptByNameAndDomain("","");
	}



	public static String getConcept(JSONObject jjj)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		HttpPost httppost = new HttpPost(vocubularyurl);
		// httppost.setHeader("X-GWT-Permutation",
		// "3DE824138FE65400740EC1816A73CACC");
		httppost.setHeader("Content-Type", "application/json");
		StringEntity se = new StringEntity(jjj.toString());
		httppost.setEntity(se);
		startTime = System.currentTimeMillis();
		HttpResponse httpresponse = new DefaultHttpClient().execute(httppost);
		endTime = System.currentTimeMillis();
		System.out.println("statusCode:" + httpresponse.getStatusLine().getStatusCode());
		System.out.println("Call API time (unit:millisecond)：" + (endTime - startTime));

		if (httpresponse.getStatusLine().getStatusCode() == 200) {
			// System.out.println("succeed!");
			String strResult = EntityUtils.toString(httpresponse.getEntity());
			return strResult;
			// httppost.
		} else {
			return null;
		}
	}

	public static String getRecordCount(JsonArray array)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		HttpPost httppost = new HttpPost(recordcounturl);
		httppost.setHeader("Content-Type", "application/json");
		StringEntity se = new StringEntity(array.toString());
		httppost.setEntity(se);
		startTime = System.currentTimeMillis();
		HttpResponse httpresponse = new DefaultHttpClient().execute(httppost);
		endTime = System.currentTimeMillis();
		System.out.println("statusCode:" + httpresponse.getStatusLine().getStatusCode());
		System.out.println("Call API time (unit:millisecond)：" + (endTime - startTime));

		if (httpresponse.getStatusLine().getStatusCode() == 200) {
			String strResult = EntityUtils.toString(httpresponse.getEntity());
			return strResult;
		} else {
			return null;
		}
	}

	
	
	
	public static JSONObject querybyconceptSetid(int conceptid){
		JSONObject jot=new JSONObject();
    	String re2=HttpUtil.doGet("http://api.ohdsi.org/WebAPI/conceptset/"+conceptid);
    	JSONObject jore2=JSONObject.fromObject(re2);
    	jot.accumulateAll(jore2);
    	String re3=HttpUtil.doGet("http://api.ohdsi.org/WebAPI/conceptset/"+conceptid+"/expression");
    	
    	JSONObject expression=JSONObject.fromObject(re3);
    	jot.accumulate("expression",expression);
    	
    	return jot;
	}

	public static LinkedHashMap<ConceptSet, Integer> matchConceptSetByEntity(String entityname) {
		HashMap<ConceptSet, Integer> candidatecs = new HashMap<ConceptSet, Integer>();
		// arrange by length similarity
		int distance = 0;
		List<ConceptSet> cslist = getallConceptSet();
		for (int k = 0; k < cslist.size(); k++) {
			if (cslist.get(k).getName().toLowerCase().contains(entityname.toLowerCase().trim())) {
				System.out.println("match=" + cslist.get(k).getName());
				distance = cslist.get(k).getName().length() - entityname.trim().length();
				candidatecs.put(cslist.get(k), distance);
			}

		}
		List<Map.Entry<ConceptSet, Integer>> sort = new ArrayList<>();
		sort.addAll(candidatecs.entrySet());
		ValueComparator vc = new ValueComparator();
		Collections.sort(sort, vc);
		LinkedHashMap<ConceptSet, Integer> lm = new LinkedHashMap<ConceptSet, Integer>();
		for (int i = sort.size() - 1; i >= 0; i--) {
			lm.put(sort.get(i).getKey(), sort.get(i).getValue());
		}
		return lm;

	}
	
	public static Integer getSimilarConceptSetByEntity(String entityname) {
		HashMap<ConceptSet, Integer> candidatecs = new HashMap<ConceptSet, Integer>();
		// arrange by length similarity
		int distance = 0;
		List<ConceptSet> cslist = getallConceptSet();
		for (int k = 0; k < cslist.size(); k++) {
			if (cslist.get(k).getName().toLowerCase().contains(entityname.toLowerCase().trim())) {
				System.out.println("match=" + cslist.get(k).getName());
				distance = cslist.get(k).getName().length() - entityname.trim().length();
				candidatecs.put(cslist.get(k), distance);
			}
		}
		List<Map.Entry<ConceptSet, Integer>> sort = new ArrayList<>();
		sort.addAll(candidatecs.entrySet());
		ValueComparator vc = new ValueComparator();
		Collections.sort(sort, vc);
		LinkedHashMap<ConceptSet, Integer> lm = new LinkedHashMap<ConceptSet, Integer>();
		for (int i = sort.size() - 1; i >= 0; i--) {
			lm.put(sort.get(i).getKey(), sort.get(i).getValue());
		}
		
		if(sort.size()>0){
			return sort.get(sort.size() - 1).getKey().getId();
		}else{
			return 0;
		}
	}
	
	public static List<ConceptSet> getallConceptSet() {
		String strResult = HttpUtil.doGet(conceptseturl);
		JSONArray array = JSONArray.fromObject(strResult);
		// System.out.println("-->"+array);
		List<ConceptSet> list = JSONArray.toList(array, ConceptSet.class);// 过时方法
		return list;
	}
	
	public static List<Concept> searchConceptByNameAndDomain(String entity, String domainid)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		JSONObject queryjson = new JSONObject();
		queryjson.accumulate("QUERY", entity);
		queryjson.accumulate("DOMAIN_ID", "['" + domainid + "']");
		queryjson.accumulate("STANDARD_CONCEPT","S");//only standard concept
//		queryjson.accumulate("VOCABULARY_ID", "['SNOMED']");
		System.out.println("queryjson:" + queryjson);
		String vocabularyresult = getConcept(queryjson);
		System.out.println("vocabularyresult  length=" + vocabularyresult.length());
		Gson gson = new Gson();
		JsonArray ja = new JsonParser().parse(vocabularyresult).getAsJsonArray();
		if (ja.size() == 0) {
			System.out.println("size=" + ja.size());
			return null;
		}
		List<Concept> list = gson.fromJson(ja, new TypeToken<List<Concept>>() {
		}.getType());
		return list;
	}
	
	
	

}