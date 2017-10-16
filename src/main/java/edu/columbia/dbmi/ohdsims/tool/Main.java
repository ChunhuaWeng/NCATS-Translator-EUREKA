package edu.columbia.dbmi.ohdsims.tool;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

/* Sample questions
 * what percent of type 1 diabetes mellitus patients use insulin after 7 days of diagnosis?
 * What is the prevalence of insulin usage among type 1 diabetes mellitus patients?
 * Does insulin treat type 1 diabetes mellitus?
 * what drugs are the top two drugs used by hypertensive disorder patients?
 * what is the age distribution of Alzheimer’s disease patients?
 * what is the gender ratio among hypertensive disorder patients?
 */

public class Main {


    public static void main(String[] args) throws IOException, ParseException {
        Question question = new Question();
//        question.getQuestion();
//        question.setQuestion("what percent of type 1 diabetes mellitus patients use insulin after 7 days of diagnosis?");
//        question.setQuestion("what is the prevalence of insulin use among type 1 diabetes mellitus patients?");
        question.setQuestion("what 2 drugs are most commonly used by patients with hypertensive disorder?");
//        question.setQuestion("what is the age distribution of Alzheimer’s disease patients?"); 
//        question.setQuestion("what is the ratio of male and female hypertensive disorder patients?");
//        question.setQuestion("what is the distribution of time in between hemoglobin a1c testing for patients with diabetes?");
//        question.setQuestion("how many patients with depression were previous diagnosed with chronic pain?");
//        question.setQuestion("how many obese patients with hypertension have had sleep studies?");

        question.getTemplates();
        question.analyzeQuestion();
    		question.formatResult();
//    		
    		// display term objects
    		System.out.println("Analysis method: "+question.analysisMethodName+"\n");
    		if (!question.numTermList.isEmpty()) {
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
    		} else {
    			System.out.println("major terms");
    			for (Term item:question.termList) {
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
}
