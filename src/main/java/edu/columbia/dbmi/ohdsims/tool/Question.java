package edu.columbia.dbmi.ohdsims.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question {

    String question;
	List<Template> newTempTab;
	List<String> majorTerms = new ArrayList<>();
	String timeVal;
	String timeUnit;
	String timeRel;
	String priorEvt;
	String postEvt;
	AnalysisMethod method = new AnalysisMethod();

	// for percent questions
	String analysisMethodName;
	List<String> numeratorList = new ArrayList<>();
	List<String> denominatorList = new ArrayList<>();
	List<Term> numTermList = new ArrayList<Term>();
	List<Term> denTermList = new ArrayList<Term>();
	private Scanner scan;
	
	//get a question
    public void getQuestion() {
        System.out.println("Enter a question:\n");
        Scanner scan = new Scanner( System.in );
        this.question = scan.nextLine().toLowerCase();
    }

    //load templates
    public void getTemplates() throws IOException {
        String tempPath = "/Users/weiwei/Dropbox/Research/biotranslator_hackathon/eureka/src/main/java/edu/columbia/dbmi/ohdsims/tool/raw_templates.txt";
        Reader reader = new Reader();
        reader.read(tempPath);
        this.newTempTab = reader.parseTemplate();
    }

    //apply templates to the question, return keywords and the associated attributes
    public void analyzeQuestion() {
        for (Template val:this.newTempTab) {
        		String rawPat = val.getTemplate();
        		Pattern pat = Pattern.compile(rawPat);
//        		System.out.println("raw template: "+rawPat);        		
            
            int[] majorConceptIdx = val.getMajorConceptIdx();
            Integer timeValIdx = val.timeValIdx;
        		Integer timeUnitIdx = val.timeUnitIdx;
        		Integer timeRelIdx = val.timeRelIdx;
//        		System.out.println("ck1: "+timeRelIdx);
        		Integer priorEvtIdx = val.priorEvtIdx;
        		Integer postEvtIdx = val.postEvtIdx;

            Matcher mat = pat.matcher( this.question );
            if (mat.matches()==true) {
                if (timeRelIdx!=null&&timeValIdx!=null&&timeUnitIdx!=null&&priorEvtIdx!=null&&postEvtIdx!=null) {
                    for (int i=0;i<majorConceptIdx.length;i++){ //idx={1,3,4,5,1,3}, tempValIdx=4
                    		// add major terms
                    		this.majorTerms.add(mat.group(majorConceptIdx[i]));
//                    		if (i<timeValIdx){
////                    			majorTerm.setTerm(mat.group(majorConceptIdx[i]));
//                        }
                    }
                    this.timeRel = mat.group(timeRelIdx);
                    this.timeVal = mat.group(timeValIdx);
                    this.timeUnit = mat.group(timeUnitIdx).replaceAll("s$", "");          
                    this.priorEvt = mat.group(priorEvtIdx);
                    this.postEvt = mat.group(postEvtIdx);

                } else {
                    for (int i=0;i<majorConceptIdx.length;i++){
                    		this.majorTerms.add(mat.group(majorConceptIdx[i]));
                    }
                }
                //analysis method info here
                this.analysisMethodName = val.method.methodName; // default is "ratio"
                if (this.analysisMethodName.equals("ratio")) {
                	    this.method.numeratorIdx = val.method.numeratorIdx;
                	    this.method.denominatorIdx = val.method.denominatorIdx;

                    for (int i=0; i<this.method.numeratorIdx.length;i++) {
                    		this.numeratorList.add(mat.group(this.method.numeratorIdx[i]));
                    }
                    for (int j=0; j<this.method.denominatorIdx.length;j++ ) {
                    		this.denominatorList.add(mat.group(this.method.denominatorIdx[j]));
                    }                		
                } else {
                		System.out.println("new analysis method required.");
                		System.out.println("method name: "+this.analysisMethodName);
//                		scan = new Scanner( System.in );
//                		String pause = scan.nextLine();
                }
            }
        }
    }
    
    public void formatResult() {
		// load concept-domain dict file
    		String csvFile = "/Users/weiwei/Dropbox/Research/biotranslator_hackathon/eureka/src/main/java/edu/columbia/dbmi/ohdsims/tool/CONCEPT.csv";
    		CSVreader csvreader = new CSVreader();
    		csvreader.csv2dict(csvFile);
    		HashMap<String, ArrayList<String>> term2idDict = csvreader.term2idDict;
    		HashMap<String, ArrayList<String>> term2domainDict = csvreader.term2domainDict;
    		HashMap<String, Term> termDict = new HashMap<>();
    		
    		for (String term:this.majorTerms) {
    			Term mterm = new Term();
    			// set term
    			mterm.setTerm(term);
    			// set term ID
    			mterm.setTermID(term, term2idDict);
    			// set domain
    			mterm.setTermDomain(term, term2domainDict);
    			// set initial event status and time value
    			if (term.equals(this.priorEvt)) {
    				mterm.iniEvt=true;
    				mterm.setTimeVal("0");
    				mterm.setTimeUnit(this.timeUnit);
    			} else {
    				mterm.iniEvt=false;
    				mterm.setTimeVal(this.timeVal);
    				mterm.setTimeUnit(this.timeUnit);
    				mterm.setTimeRel(this.timeRel);
    			}
    			termDict.put(term, mterm);
    		}

		// for a question about percentage, return this.numTermList and this.denTermList, method name is ratio
    		if (this.analysisMethodName.equals("ratio")) {
        		for (String num:this.numeratorList) {
        			this.numTermList.add(termDict.get(num));
        		} 
        		for (String den:this.denominatorList) {
        			this.denTermList.add(termDict.get(den));
        		}    			
    		}
    		// for another type of question
    }
}
