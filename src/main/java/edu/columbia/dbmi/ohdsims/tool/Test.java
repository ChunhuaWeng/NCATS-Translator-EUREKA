package edu.columbia.dbmi.ohdsims.tool;

import java.io.IOException;

import edu.columbia.dbmi.ohdsims.util.APIUtil;
import edu.columbia.dbmi.ohdsims.util.SQLUtil;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(APIUtil.querybyconceptcetid(100774));
		String[] str=CohortCreation.generateCohortSQL(null);
		Integer i=SQLUtil.executeSQL(str[0]);
		System.out.println("count="+i);
	}

}
