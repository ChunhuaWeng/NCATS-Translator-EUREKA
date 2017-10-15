package edu.columbia.dbmi.ohdsims.tool;
/* Test postgresql connection
 * */

public class TestConn {
	
	public static void main(String[] args) {
		TestConn tc = new TestConn();
		String sql = "select * from concept limit 5;";
		tc.test(sql);
	}
	public void test(String sql) {
		LocalQueryPSQL conn = new LocalQueryPSQL();
//		conn.executeSQL(sql);
	}
}
