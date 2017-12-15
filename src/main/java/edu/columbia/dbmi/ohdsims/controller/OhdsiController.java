package edu.columbia.dbmi.ohdsims.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.columbia.dbmi.ohdsims.pojo.CohortDisplay;
import edu.columbia.dbmi.ohdsims.pojo.ConceptSet;
import edu.columbia.dbmi.ohdsims.pojo.Criterion;
import edu.columbia.dbmi.ohdsims.pojo.QuestionModel;
import edu.columbia.dbmi.ohdsims.pojo.Site;
import edu.columbia.dbmi.ohdsims.pojo.User;
import edu.columbia.dbmi.ohdsims.tool.AppSQLTools;
import edu.columbia.dbmi.ohdsims.tool.CohortCreation;
import edu.columbia.dbmi.ohdsims.tool.Question;
import edu.columbia.dbmi.ohdsims.tool.Term;
import edu.columbia.dbmi.ohdsims.util.APIUtil;
import edu.columbia.dbmi.ohdsims.util.ATLASUtil;
import edu.columbia.dbmi.ohdsims.util.FileHelper;
import edu.columbia.dbmi.ohdsims.util.SQLUtil;
import edu.columbia.dbmi.ohdsims.util.WebUtil;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

@Controller
@RequestMapping("/ohdsi")
public class OhdsiController {

	@Autowired
	private Properties applicationProps;

	public Properties getApplicationProps() {
		return applicationProps;
	}

	public void setApplicationProps(Properties applicationProps) {
		this.applicationProps = applicationProps;
	}

	@RequestMapping("/main")
	public String showmain() {
		//
		return "main";
	}

	@RequestMapping("/cohort")
	public String showcohort() {
		return "cohort";
	}

	@RequestMapping("/input")
	public String inputPage() {
		return "input";
	}

	@RequestMapping("/tasks")
	public String tasksPage() {
		return "tasks";
	}

	@RequestMapping("/collaborate")
	public String collaboratePage() {
		return "collaborate";
	}

	@RequestMapping("/requestdetail")
	public String requestDetailPage() {
		return "requestdetail";
	}

	@RequestMapping("/results")
	public String resultsPage() {
		return "results";
	}

	@RequestMapping("/loginverify")
	@ResponseBody
	public Map<String, Object> loginV(HttpSession httpSession, HttpServletRequest request, String username,
			String pwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("login");
		User user;
		try {
			AppSQLTools appdb = new AppSQLTools();
			user = appdb.getOneUserbyUsername(username);
			System.out.println("==>" + user.getPwd());
			if (pwd.equals(user.getPwd())) {
				System.out.println("username=" + username);
				System.out.println("pwd=" + pwd);
				httpSession.setAttribute("username", username);
				return map;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping("/regnewdataset")
	public String regNewDataSet(HttpSession httpSession, HttpServletRequest request,String sitename,String desc, String available) throws SQLException{
		String username = (String) httpSession.getAttribute("username");
		System.out.println("sitename="+sitename);
		System.out.println("desc="+desc);
		System.out.println("available="+available);
		System.out.println("username="+username);
		AppSQLTools cnn = new AppSQLTools();
		if(available.equals("Available")){
			cnn.insertOneSite(username, sitename, desc, 1);
		}else{
			cnn.insertOneSite(username, sitename, desc, 0);
		}
		
		return "collaborate";
	}

	@RequestMapping("/parse")
	public String parseSentence(HttpSession httpSession, HttpServletRequest request, String sentence) throws Exception {
		System.out.println("sentence=" + sentence);
		String username = (String) httpSession.getAttribute("username");
		Question question = new Question();
		// question.getQuestion();
		question.setQuestion(sentence);
		question.getTemplates();
		question.analyzeQuestion();
		question.formatResult();
		// display term objects
		System.out.println("Analysis method: " + question.analysisMethodName + "\n");
		StringBuffer numeratorsb = new StringBuffer();
		System.out.print("numerator\n");
		List<Criterion> numerator = new ArrayList<Criterion>();
		Map<String, Integer> conceptSetmap1 = new HashMap<String, Integer>();
		for (Term item : question.numTermList) {
			numeratorsb.append(item.term + ",");
			Criterion c = new Criterion();
			c.setConceptSetName(item.term);
			Integer conceptSetID = ATLASUtil.getSimilarConceptSetByEntity(item.term);
			if (conceptSetID == 0) {
				conceptSetID = CohortCreation.createConceptByConceptName(item.term, item.domain.get(0));
			}
			System.out.println("====1====" + conceptSetID);
			conceptSetmap1.put(item.term, conceptSetID);
			c.setConceptSetId(conceptSetID);
			c.setDomain(item.domain.get(0));
			c.setInitialEvent(item.iniEvt);
			if (item.timeRel == null) {

			} else if (item.timeRel.equals("after")) {
				c.setTemporaltype(1);
				if (item.timeVal != null) {
					c.setAfterDays(Integer.valueOf(item.timeVal));
				} else {
					c.setAfterDays(-1);
				}
				c.setBeforeDays(-1);
				c.setTemporaltype(1);
			} else if (item.timeRel.equals("before")) {
				c.setTemporaltype(2);
				if (item.timeVal != null) {
					c.setBeforeDays(Integer.valueOf(item.timeVal));
				} else {
					c.setBeforeDays(-1);
				}
				c.setAfterDays(-1);
				c.setTemporaltype(2);
			}
			c.setInclusionCriterion(true);
			System.out.println("term: " + item.term);
			System.out.println("term ID: " + item.termID);
			System.out.println("domain: " + item.domain);
			System.out.println("initial event status: " + item.iniEvt);
			System.out.println("before or after: " + item.timeRel);
			System.out.println("time: " + item.timeVal);
			System.out.println("time unit: " + item.timeUnit);
			System.out.println("\n");
			numerator.add(c);
		}
		String[] sql = CohortCreation.generateCohortSQL(numerator, conceptSetmap1);
		// Integer all1 = SQLUtil.executeSQL(sql2);
		System.out.println(sql[1]);

		System.out.print("\ndenominator\n");
		StringBuffer denominatorsb = new StringBuffer();
		Map<String, Integer> conceptSetmap2 = new HashMap<String, Integer>();
		List<Criterion> denominator = new ArrayList<Criterion>();
		for (Term item : question.denTermList) {
			Criterion c = new Criterion();
			denominatorsb.append(item.term + ",");
			c.setConceptSetName(item.term);
			Integer conceptSetID = ATLASUtil.getSimilarConceptSetByEntity(item.term);
			if (conceptSetID == 0) {
				conceptSetID = CohortCreation.createConceptByConceptName(item.term, item.domain.get(0));
			}
			System.out.println("====2====" + conceptSetID);
			conceptSetmap2.put(item.term, conceptSetID);
			c.setConceptSetId(conceptSetID);
			c.setDomain(item.domain.get(0));
			c.setInitialEvent(item.iniEvt);
			if (item.timeRel == null) {

			} else if (item.timeRel.equals("after")) {
				if (item.timeVal != null) {
					c.setAfterDays(Integer.valueOf(item.timeVal));
				} else {
					c.setAfterDays(-1);
				}
				c.setBeforeDays(-1);
				c.setTemporaltype(1);
			} else if (item.timeRel.equals("before")) {
				if (item.timeVal != null) {
					c.setBeforeDays(Integer.valueOf(item.timeVal));
				} else {
					c.setBeforeDays(-1);
				}
				c.setAfterDays(-1);
				c.setTemporaltype(2);
			}
			c.setInclusionCriterion(true);
			System.out.println("term: " + item.term);
			System.out.println("term ID: " + item.termID);
			System.out.println("domain: " + item.domain);
			System.out.println("initial event status: " + item.iniEvt);
			System.out.println("before or after: " + item.timeRel);
			System.out.println("time: " + item.timeVal);
			System.out.println("time unit: " + item.timeUnit);
			denominator.add(c);
		}
		String sql2[] = CohortCreation.generateCohortSQL(denominator, conceptSetmap2);
		System.out.println(sql2[1]);
		// submit to db
		long t1 = System.currentTimeMillis();
		String questionid = String.valueOf(t1);
		AppSQLTools cnn = new AppSQLTools();
		cnn.insertOneQuestion(username, sentence, "t", questionid);
		System.out.println(sql[0] + "\t" + sql2[0]);

		cnn.insertCohort(questionid, "", Integer.valueOf(sql[0]), numeratorsb.toString());
		cnn.insertCohort(questionid, "", Integer.valueOf(sql2[0]), denominatorsb.toString());
		httpSession.setAttribute("prequestionid", questionid);

		return "formulate";
	}

	@RequestMapping("/getParseResult")
	@ResponseBody
	public Map<String, Object> getParseResult(HttpSession httpSession) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		String questionid = (String) httpSession.getAttribute("prequestionid");
		AppSQLTools cnn = new AppSQLTools();
		try {
			List<CohortDisplay> cohortlist = cnn.selectCohort(questionid);
			map.put("rows", cohortlist);
			map.put("total", cohortlist.size());
			return map;
		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	@RequestMapping("/seedetail")
	@ResponseBody
	public Map<String, Object> seeRequestDetail(HttpSession httpSession,String questionid) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		 httpSession.setAttribute("prequestionid",questionid);
		return map;

	}

	@RequestMapping("/setRequestSites")
	@ResponseBody
	public Map<String, Object> setRequestSites(HttpSession httpSession, String sites) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(sites);
		JSONArray ja = JSONArray.fromObject(sites);
		

		String questionid = (String) httpSession.getAttribute("prequestionid");
		AppSQLTools cnn = new AppSQLTools();
		try {
			for (int i = 0; i < ja.size(); i++) {
				Integer cohortlist = cnn.insertRequest(ja.getInt(i), questionid);
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	@RequestMapping("/getSites4Question")
	@ResponseBody
	public Map<String, Object> getSites4Question(HttpSession httpSession) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		String questionid = (String) httpSession.getAttribute("prequestionid");
		List<Site> slist;
		try {
			AppSQLTools appdb = new AppSQLTools();
			slist = appdb.getSitesbyQuestion(questionid);
			map.put("rows", slist);
			map.put("total", slist.size());
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


	@RequestMapping("/getnumbers")
	@ResponseBody
	public Map<String, Object> getNumbers(HttpSession httpSession) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		Integer t = (Integer) httpSession.getAttribute("allcount");
		Integer t1 = (Integer) httpSession.getAttribute("casecount");
		map.put("total", t);
		map.put("case", t1);
		return map;
	}

	@RequestMapping("/getallsites")
	@ResponseBody
	public Map<String, Object> getSites(HttpSession httpSession) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		List<Site> listsite = new ArrayList<Site>();

		AppSQLTools cnn = new AppSQLTools();
		try {
			listsite = cnn.getAllSites();
			map.put("rows", listsite);
			map.put("total", 1);
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// String sitefile = "/Users/cy2465/Documents/sites.txt";
		// String siteslist = FileHelper.Readfile(sitefile);
		// System.out.println(siteslist);
		// Site site3 = new Site();
		// site3.setId(1);
		// site3.setPatientCount(2000000);
		// site3.setSiteName("SynPuf 1K");
		// site3.setDesc("1000 Samples of Medicare Claims Synthetic Public Use
		// Files");
		// site3.setStatus(0);
		// site3.setContact("N/A");
		// site3.setEmail("N/A");
		// listsite.add(site3);
		//
		// Site site4 = new Site();
		// site4.setId(2);
		// site4.setPatientCount(2000000);
		// site4.setSiteName("SynPuf 1%");
		// site4.setDesc("1% of Medicare Claims Synthetic Public Use Files");
		// site4.setStatus(0);
		// site4.setContact("N/A");
		// site4.setEmail("N/A");
		// listsite.add(site4);
		//
		// // Site site=new Site();
		// // site.setId(3);
		// // site.setPatientCount(10000);
		// // site.setSiteName("OHDSI East");
		// // site.setDesc("Cornell Medical Center");
		// // site.setStatus(0);
		// // site.setContact("Chi Yuan");
		// // site.setEmail("cy2465@cumc.columbia.edu");
		// // listsite.add(site);
		//
		// Site site2 = new Site();
		// site2.setId(3);
		// site2.setPatientCount(2000000);
		// site2.setSiteName("NYP West");
		// site2.setDesc("NewYork–Presbyterian Hospital & Columbia University
		// Medical Center");
		// site2.setStatus(0);
		// site2.setContact("Wei Wei");
		// site2.setEmail("ww1111@cumc.columbia.edu");
		// listsite.add(site2);

	}

	@RequestMapping("/getMyCollaborations")
	@ResponseBody
	public Map<String, Object> getMyCollaborations(HttpSession httpSession) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		List<QuestionModel> qlist;
		try {
			AppSQLTools appdb = new AppSQLTools();
			qlist = appdb.getCollobrateQuestionsbyUsername(username);
			map.put("rows", qlist);
			map.put("total", qlist.size());
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/getMyDateSet")
	@ResponseBody
	public Map<String, Object> getMyDataSet(HttpSession httpSession) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		List<Site> slist;
		try {
			AppSQLTools appdb = new AppSQLTools();
			slist = appdb.getSitesbyUsername(username);
			map.put("rows", slist);
			map.put("total", slist.size());
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/getRequests")
	@ResponseBody
	public Map<String, Object> getRequests(HttpSession httpSession) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		List<QuestionModel> qlist;
		try {
			AppSQLTools appdb = new AppSQLTools();
			qlist = appdb.getRequestsbyUsername(username);
			map.put("rows", qlist);
			map.put("total", qlist.size());
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping("/getMyQuestions")
	@ResponseBody
	public Map<String, Object> getMyQuestions(HttpSession httpSession) {
		//
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		List<QuestionModel> qlist;
		try {
			AppSQLTools appdb = new AppSQLTools();
			qlist = appdb.getQuestionsbyUsername(username);
			map.put("rows", qlist);
			map.put("total", qlist.size());
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping("/sites")
	public String showsites() {
		//
		return "sites";
	}

	@RequestMapping("/detail")
	public String showDetails() {
		//
		return "detail";
	}

	@RequestMapping("/review")
	public String showReview() {
		//
		return "review";
	}

	@RequestMapping("/ft2query")
	public String showft2query() {
		String jumpUrl = applicationProps.getProperty("nermodel");
		String metalite = applicationProps.getProperty("metalite");
		System.out.println("metalite=" + metalite);
		return "freetext2sql";
	}

	@RequestMapping("/reformulate")
	public String reformulate() {
		System.out.println("~~!!@#!@#~~");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main";
	}

	@RequestMapping("/conceptset")
	public String showConceptSet() {
		//
		return "conceptSetPage";
	}

	@RequestMapping("/vtest2")
	public String showVtest2() {
		//
		return "vtest2";
	}

	@RequestMapping("/welcome")
	public String showwelcome() {
		//
		return "welcome";
	}

	@RequestMapping("/visualization")
	public String showvtest() {
		//
		return "vtest";
	}

	@RequestMapping("/confirmation")
	public String showconfirm() {
		//
		return "confirmation";
	}

	@RequestMapping("/account")
	public String showAccountPage() {
		//

		return "account";
	}

	@RequestMapping("/accountInfo")
	@ResponseBody
	public Map<String, Object> initAccountPage(HttpSession httpSession) {
		Map<String, Object> map = new HashMap<String, Object>();
		String username = (String) httpSession.getAttribute("username");
		if (username == null) {
			return null;
		}
		// query for personal info
		User user;
		try {
			AppSQLTools appdb = new AppSQLTools();
			user = appdb.getOneUserbyUsername(username);
			map.put("data", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// query for questions
		return map;
	}

	@RequestMapping("/question")
	public String showQuestionPage() {
		//

		return "question";
	}

	@RequestMapping("/formulate")
	public String showFormulatePage() {
		//
		return "formulate";
	}

	@RequestMapping("/request")
	public String showRequestPage() {
		//
		return "request";
	}

	@RequestMapping("/generateSQL")
	@ResponseBody
	public String getallConceptSets(HttpSession httpSession) throws Exception {// Map<String,
																				// Object>
		System.out.println("conceptsets=" + 123);

		Map<String, Object> map = new HashMap<String, Object>();
		int inlength = 0;
		JSONObject jcs = new JSONObject();
		JSONArray totalconceptsetarr = new JSONArray();
		totalconceptsetarr.add(APIUtil.querybyconceptcetid(1232133));
		JSONArray jaa = totalconceptsetarr;
		JSONArray janull = new JSONArray();
		JSONObject jonull = new JSONObject();
		JSONObject initialevent = APIUtil.anyVisitforInitialEvent();
		JSONObject jofirst = new JSONObject();
		jofirst.accumulate("Type", "First");
		jcs.accumulate("ConceptSets", jaa);
		jcs.accumulate("PrimaryCriteria", initialevent);// any visit
		JSONObject joaddc = APIUtil.setAdditionalCriteria();// Set additional
															// criteria
		jcs.accumulate("AdditionalCriteria", joaddc);
		jcs.accumulate("QualifiedLimit", jofirst);
		jcs.accumulate("ExpressionLimit", jofirst);
		jcs.accumulate("InclusionRules", janull);
		jcs.accumulate("CensoringCriteria", janull);
		System.out.println(jcs);
		httpSession.setAttribute("jsonresult", jcs.toString());
		return jcs.toString();
	}

	@RequestMapping("/download")
	public void saveFile(HttpServletRequest request, HttpServletResponse response, String cohortid) {
		StringBuffer xmlsb = new StringBuffer();
		xmlsb.append("# \n");

		xmlsb.append("# \n");
		// 导出txt文件
		response.setContentType("text/plain");
		String fileName = "Cohort" + cohortid;
		try {
			fileName = URLEncoder.encode("Criteria2XML_result", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xml");
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String enter = "\r\n";
		write.append("test");
		ServletOutputStream outSTr = null;
		try {
			outSTr = response.getOutputStream();
			buff = new BufferedOutputStream(outSTr);
			buff.write(xmlsb.toString().getBytes("UTF-8"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
