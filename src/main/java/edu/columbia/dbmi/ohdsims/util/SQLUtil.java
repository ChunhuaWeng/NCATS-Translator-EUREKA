package edu.columbia.dbmi.ohdsims.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.columbia.dbmi.ohdsims.tool.CohortCreation;

public class SQLUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection=null;
        Statement statement =null;
        try{
            String url="jdbc:postgresql://45.76.6.224:5432/synpuf1000";
            String user="postgres";
            String password = "postgres";
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection(url, user, password);
            //System.out.println("是否成功连接pg数据库"+connection);
            String[] str=CohortCreation.generateCohortSQL(null);
            
            String sql=str[0];
            //String sql="select count(*) from drug_exposure";
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                String name=resultSet.getString(1);
                System.out.println(name);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            try{
                statement.close();
            }
            catch(SQLException e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }finally{
                try{
                    connection.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
	}

}
