package edu.columbia.dbmi.ohdsims.tool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import edu.columbia.dbmi.ohdsims.tool.CohortCreation;

public class LocalQueryPSQL {
	// JDBC driver name and database URL
	final static String JDBC_DRIVER = "org.postgresql.Driver";  
	final static String DB_URL = "jdbc:postgresql://localhost:5432/testdb";
	//  Database credentials
	final static String USER = "postgres";
	final static String PASS = "granite";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			//STEP 2: Register JDBC driver
//			Class.forName(JDBC_DRIVER);
			
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			//STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();

		      System.out.println("Fetching records with condition...");
		      String sql = "SELECT concept_id, vocabulary_id FROM concept" +
		                   " WHERE concept_name like 'Hypertensive disorder' ";
		      ResultSet rs = stmt.executeQuery(sql);

		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("concept_id");
		         String vocab = rs.getString("vocabulary_id");

		         //Display values
		         System.out.print("concept_id: " + id);
		         System.out.print(", vocabulary_id: " + vocab);
		      }
		      rs.close();
			
		} catch (SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if (stmt!=null) 
					stmt.close();
			} catch (SQLException se2) {
				
			}
			try{
				if (conn!=null)
					conn.close();
				} catch (SQLException se) {
						se.printStackTrace();
						}
			}
		}
	
//	public static Integer executeSQL(String possql){
//		Connection connection=null;
//        Statement statement =null;
//        String count="0";
//        try{
//           
//            Class.forName("org.postgresql.Driver");
//            connection= DriverManager.getConnection(url, user, password);
//            System.out.println("connected database: "+connection.getCatalog());
//            
//            String sql = possql;
////            String sql=possql.replace("@cdm_database_schema", "public");
////            sql=sql.replace("@target_database_schema", "public");
////            sql=sql.replace("@target_cohort_table", "cohort");
////            sql=sql.replace("@target_cohort_id", "1");
////            int x=sql.indexOf("DELETE FROM");
////            System.out.println("--->"+x);
////            sql=sql.substring(0, x);
//            //sql=sql+"select * from cohort_ends;";
//            System.out.println("sql: "+sql);
//            //String sql="select count(*) from drug_exposure";
//            statement=connection.createStatement(); 
////            statement.executeUpdate(sql);
////            sql="select count(*) from cohort_ends;";
////            ResultSet resultSet= statement.executeQuery(sql);
//          
////            while(resultSet.next()){
////                String name=resultSet.getString(1);
////                System.out.println(name);
////                count=name;
////            }
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }finally{
//            try{
//                statement.close();
//            }
//            catch(SQLException e){
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }finally{
//                try{
//                    connection.close();
//                }
//                catch(SQLException e){
//                    e.printStackTrace();
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        System.out.println("return val: "+Integer.valueOf(count));
//		return Integer.valueOf(count);
//	}

}
