package edu.columbia.dbmi.ohdsims.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.columbia.dbmi.ohdsims.pojo.CohortDisplay;
import edu.columbia.dbmi.ohdsims.pojo.QuestionModel;
import edu.columbia.dbmi.ohdsims.pojo.Site;
import edu.columbia.dbmi.ohdsims.pojo.User;

public class AppSQLTools {
	final static String url = "jdbc:postgresql://45.76.6.224:5432/eureka";
	final static String user = "postgres";
	final static String password = "postgres";
	Connection connection;

	public AppSQLTools() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User getOneUserbyUsername(String username) throws SQLException {
		String sql = "select * from eurekauser where username = '" + username + "'";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		User u = new User();
		int i = 0;
		while (resultSet.next()) {
			String pwd = resultSet.getString("pwd");
			Integer researcher = resultSet.getInt("researcher");
			Integer dataowner = resultSet.getInt("dataowner");
			Integer question = resultSet.getInt("question");
			Integer project = resultSet.getInt("project");
			String institute = resultSet.getString("institute");
			String realname = resultSet.getString("realname");
			String contact = resultSet.getString("contact");
			u.setUsername(username);
			u.setPwd(pwd);
			u.setResearcher(researcher);
			u.setDataowner(dataowner);
			u.setQuestion(question);
			u.setProject(project);
			u.setInstitute(institute);
			u.setRealname(realname);
			u.setContact(contact);
			i++;
		}
		if (i > 0) {
			return u;
		} else {
			return null;
		}

	}

	public List<QuestionModel> getQuestionsbyUsername(String username) throws SQLException {
		String sql = "select * from question where username = '" + username + "'";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<QuestionModel> listquestion = new ArrayList<QuestionModel>();		
		while (resultSet.next()) {
			QuestionModel q = new QuestionModel();
			String content = resultSet.getString("content");
			String questionid = resultSet.getString("questionid");
			String testtype = resultSet.getString("testtype");
			q.setContent(content);
			q.setQuestionid(questionid);			
			q.setTesttype(testtype);
			listquestion.add(q);
		}
		return listquestion;
	}
	
	public Integer insertOneQuestion(String username,String content,String testtype,String questionid) throws SQLException {
		String sql = "insert into question (questionid,content,username,testtype) values ('"+questionid+"','"+content+"','"+username+"','"+testtype+"'"+")";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		statement.executeUpdate(sql);
		return 0;
	}
	
	public Integer insertCohort(String questionid,String cohortsql,Integer ohdsiid,String keyword) throws SQLException {
		String sql = "insert into cohort (questionid,cohortsql,ohdsicohortid,keyword) values ('"+questionid+"','"+cohortsql+"',"+ohdsiid+",'"+keyword+"');";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		statement.executeUpdate(sql);
		return 0;
	}
	
	public List<CohortDisplay> selectCohort(String questionid) throws SQLException{
		String sql = "select * from cohort where questionid = '" + questionid + "'";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<CohortDisplay> listcohort = new ArrayList<CohortDisplay>();		
		while (resultSet.next()) {
			CohortDisplay cd=new CohortDisplay();
			Integer ohdsicohortid = resultSet.getInt("ohdsicohortid");
			String keyword = resultSet.getString("keyword");
			Integer cohortid = resultSet.getInt("cohortid");
			cd.setCohortid(cohortid);
			cd.setOhdsicohortid(ohdsicohortid);
			cd.setQuestionid(questionid);
			cd.setKeyword(keyword);
			listcohort.add(cd);
		}
		return listcohort;
	}
	
	public List<QuestionModel> getCollobrateQuestionsbyUsername(String username) throws SQLException {
		String sql = "select * from question,result,eurekauser where eurekauser.username=question.username and result.questionid=question.questionid and result.username = '" + username + "'";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<QuestionModel> listquestion = new ArrayList<QuestionModel>();		
		while (resultSet.next()) {
			QuestionModel q = new QuestionModel();
			String content = resultSet.getString("content");
			String questionid = resultSet.getString("questionid");
			
			String testtype = resultSet.getString("testtype");
			String orgnizer=resultSet.getString("realname");
			q.setContent(content);
			q.setQuestionid(questionid);			
			q.setTesttype(testtype);
			q.setOrgnizer(orgnizer);
			listquestion.add(q);
		}
		return listquestion;
	}
	
	public List<Site> getSitesbyUsername(String username) throws SQLException {
		String sql = "select * from site where username = '" + username + "'";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Site> listsites = new ArrayList<Site>();		
		while (resultSet.next()) {
			Site s = new Site();
			String siteName = resultSet.getString("sitename");
			Integer siteid = resultSet.getInt("siteid");
			String desc = resultSet.getString("description");
			
			Integer status=resultSet.getInt("status");
			s.setSiteName(siteName);
			s.setId(siteid);
			s.setDesc(desc);
			s.setStatus(status);
			//s.setContact(contact);
			listsites.add(s);
		}
		return listsites;
	}
	
	public Integer insertOneSite(String username,String sitename,String desc, int available) throws SQLException {
		String sql = "insert into site (username,sitename,description,status) values ('"+username+"','"+sitename+"','"+desc+"',"+available+")";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();				
		return statement.executeUpdate(sql);
	}
	
	public List<Site> getAllSites() throws SQLException {
		String sql = "select * from site";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Site> listsites = new ArrayList<Site>();		
		while (resultSet.next()) {
			Site s = new Site();
			String siteName = resultSet.getString("sitename");
			Integer siteid = resultSet.getInt("siteid");
			String desc = resultSet.getString("description");
			Integer status=resultSet.getInt("status");
			s.setSiteName(siteName);
			s.setId(siteid);
			s.setDesc(desc);
			s.setStatus(status);
			listsites.add(s);
		}
		return listsites;
	}
	
	public Integer insertRequest(Integer siteid,String questionid) throws SQLException {
		String sql = "insert into request (questionid,siteid,accept) values ('"+questionid+"',"+siteid+","+0+")";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();	
		return statement.executeUpdate(sql);
	}
	public List<Site> getSitesbyQuestion(String questionid) throws SQLException{
		String sql="select * from request,site where site.siteid=request.siteid and questionid='"+questionid+"'";
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Site> listsites = new ArrayList<Site>();		
		while (resultSet.next()) {
			Site s = new Site();
			String siteName = resultSet.getString("sitename");
			Integer siteid = resultSet.getInt("siteid");
			String desc = resultSet.getString("description");
			Integer status=resultSet.getInt("status");
			s.setSiteName(siteName);
			s.setId(siteid);
			s.setDesc(desc);
			s.setStatus(status);
			//s.setContact(contact);
			listsites.add(s);
		}
		return listsites;
	}
	
	public List<QuestionModel> getRequestsbyUsername(String username) throws SQLException {
		String sql = "select request.questionid as qid,sitename,content,realname,requestid from question,request,eurekauser,site "
				+ "where"
				+ " request.questionid=question.questionid "
				+ "and accept=0 "
				+ "and request.siteid=site.siteid "
				+ "and eurekauser.username=question.username "
				+ "and site.username = '" + username + "'";
		System.out.println(sql);
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<QuestionModel> listquestion = new ArrayList<QuestionModel>();		
		while (resultSet.next()) {
			QuestionModel q = new QuestionModel();
			String content = resultSet.getString("content");
			String questionid = resultSet.getString("qid");
			Integer requestid=resultSet.getInt("requestid");
			String orgnizer=resultSet.getString("realname");
			String sitename=resultSet.getString("sitename");
			q.setContent(content);
			q.setQuestionid(questionid);
			q.setOrgnizer(orgnizer);
			q.setSitename(sitename);
			q.setRequestid(requestid);
			listquestion.add(q);
		}
		return listquestion;
	}

	public static void main(String[] args) throws SQLException {
		AppSQLTools ast = new AppSQLTools();
		ast.getOneUserbyUsername("dataowner1@cumc.columbia.edu");
		// TODO Auto-generated method stub
		// Connection connection=null;
		// Statement statement =null;
		// String count="0";
		// try{
		// Class.forName("org.postgresql.Driver");
		// connection= DriverManager.getConnection(url, user, password);
		// //System.out.println("是否成功连接pg数据库"+connection);
		// String sql;
		// statement=connection.createStatement();
		// sql="select * from eurekauser where username = '" + "yuanchi"+"'";
		// ResultSet resultSet= statement.executeQuery(sql);
		// while(resultSet.next()){
		// String name=resultSet.getString(1);
		// String content=resultSet.getString("pwd");
		// String content2=resultSet.getString(3);
		// String content3=resultSet.getString(4);
		// System.out.println(content);
		// count=name;
		// }
		// }catch(Exception e){
		// throw new RuntimeException(e);
		// }finally{
		// try{
		// statement.close();
		// }
		// catch(SQLException e){
		// e.printStackTrace();
		// throw new RuntimeException(e);
		// }finally{
		// try{
		// connection.close();
		// }
		// catch(SQLException e){
		// e.printStackTrace();
		// throw new RuntimeException(e);
		// }
		// }
		// }
		//
	}

}
