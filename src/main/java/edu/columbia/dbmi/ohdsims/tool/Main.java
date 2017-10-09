package edu.columbia.dbmi.ohdsims.tool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Sample questions
 * what percent of type 1 diabetes mellitus patients use insulin after 7 days of diagnosis?
 * What is the prevalence of insulin usage among type 1 diabetes mellitus patients?
 * Does insulin treat type 1 diabetes mellitus?
 */

public class Main {


    public static void main(String[] args) throws IOException {
        Question question = new Question();
//        question.getQuestion();
        question.setQuestion("what percent of type 1 diabetes mellitus patients use insulin after 7 days of diagnosis?");
        question.getTemplates();
        question.analyzeQuestion();
    		question.formatResult();
    		
    		// display term objects
    		System.out.println("Analysis method: "+question.analysisMethodName+"\n");
    		System.out.print("numerator\n");
    		for (Term item:question.numTermList) {
    			System.out.println("term: "+item.term);
    			System.out.println("term ID: "+item.termID);
    			System.out.println("domain: "+item.domain);
    			System.out.println("initial event status: "+item.iniEvt);
    			System.out.println("before or after: "+item.timeRel);
    			System.out.println("time: "+item.timeVal);
    			System.out.println("time unit: "+item.timeUnit);
    			System.out.println("\n");
    		}
    		System.out.print("\ndenominator\n");
    		for (Term item:question.denTermList) {
    			System.out.println("term: "+item.term);
    			System.out.println("term ID: "+item.termID);
    			System.out.println("domain: "+item.domain);
    			System.out.println("initial event status: "+item.iniEvt);
    			System.out.println("before or after: "+item.timeRel);
    			System.out.println("time: "+item.timeVal);
    			System.out.println("time unit: "+item.timeUnit);			
    		}
    }
}
