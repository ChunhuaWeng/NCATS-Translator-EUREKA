package edu.columbia.dbmi.ohdsims.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question {

    public String question;
    public List<Template> newTempTab;
    public List<String> majorTerms = new ArrayList<>();
    public String timeVal;
    public String timeUnit;
    public String timeRel;
    public String priorEvt;
    public String postEvt;
    public String analysisMethodName;
    public AnalysisMethod method = new AnalysisMethod();
    public LinkedHashMap<String, String> domainDict = new LinkedHashMap<String, String>();
    public List<Term> termList = new ArrayList<Term>(); // for general results
//    public String keyEntity; // single major term, for entity, dist, gender_ratio questions
    
	// for percent questions
    public List<String> numeratorList = new ArrayList<>();
    public List<String> denominatorList = new ArrayList<>();
    public List<Term> numTermList = new ArrayList<Term>();
    public List<Term> denTermList = new ArrayList<Term>();
    
    // for entity type questions
    public String topRank;
    // for gender ratio questions
    
    // for age distribution questions

	//get a question
    public void getQuestion() {
        System.out.println("Enter a question:\n");
        Scanner scan = new Scanner( System.in );
        this.question = scan.nextLine().toLowerCase();
    }

    // set a question for testing
    public void setQuestion(String sentence) {
    		this.question = sentence;
    }
    
    //load templates
    public void getTemplates() throws IOException {
        String tempPath = "/Users/wei/Dropbox/Research/biotranslator_hackathon/raw_templates.txt";
        Reader reader = new Reader();
        reader.read(tempPath);
        this.newTempTab = reader.parseTemplate(); // template list
        
//        // display templates
//        for (Template temp:this.newTempTab) {
//        		System.out.println(temp.template);
//        }
    }
    
    

    //apply templates to the question, return keywords and the associated attributes
    public void analyzeQuestion() {
        for (Template val:this.newTempTab) {
        		String rawPat = val.getTemplate();
        		Pattern pat = Pattern.compile(rawPat);
            int[] majorConceptIdx = val.getMajorConceptIdx();
            Integer timeValIdx = val.timeValIdx;
        		Integer timeUnitIdx = val.timeUnitIdx;
        		Integer timeRelIdx = val.timeRelIdx;
        		Integer priorEvtIdx = val.priorEvtIdx;
        		Integer postEvtIdx = val.postEvtIdx;
        		LinkedHashMap<String, String> idxDomainDict = val.idxDomainDict;
        		        		
            Matcher mat = pat.matcher( this.question );
            if (mat.matches()==true) {
                if (timeRelIdx!=null&&timeValIdx!=null&&timeUnitIdx!=null&&priorEvtIdx!=null&&postEvtIdx!=null) {
                    for (int i=0;i<majorConceptIdx.length;i++){ //idx={1,3,4,5,1,3}, tempValIdx=4
                    		// add major terms
                    		this.majorTerms.add(mat.group(majorConceptIdx[i]));
                    }
                    if (timeRelIdx>0) {
                    		this.timeRel = mat.group(timeRelIdx);
                    		if (Arrays.asList("previous","previously","before").contains(this.timeRel)) {
                    			this.timeRel = "before";
                    		}
                    		if (Arrays.asList("after").contains(this.timeRel)) {
                    			this.timeRel = "after";
                    		}
                    }
                    if (timeValIdx>0) {
                    		this.timeVal = mat.group(timeValIdx);
                    }
                    if (timeUnitIdx>0) {
                    		this.timeUnit = mat.group(timeUnitIdx).replaceAll("s$", "");
                    }
                    if (priorEvtIdx>0) {
                    		this.priorEvt = mat.group(priorEvtIdx);
                    }          
                    if (postEvtIdx>0) {
                    		this.postEvt = mat.group(postEvtIdx);
                    }
                } else {
                    for (int i=0;i<majorConceptIdx.length;i++){
                    		this.majorTerms.add(mat.group(majorConceptIdx[i]));
                    }
                }
                // display major terms
//                System.out.println("display major terms");
//                for (String term: this.majorTerms) {
//                		System.out.println(term);
//                }
                
                // domain info
	        		for (Entry<String, String> entry : idxDomainDict.entrySet()) {
	        		    String keyIdx = entry.getKey();
	        		    String value = entry.getValue();
	        		    String key = mat.group(Integer.valueOf(keyIdx));
	        		    this.domainDict.put(key,  value);
	        		}
	        		
	        		// display domain info
//	        		System.out.println("\ndisplay domain info");	        		
//	        		for (Entry<String, String> entry: this.domainDict.entrySet()) {
//	        			String key = entry.getKey();
//	        			System.out.println(key+": "+this.domainDict.get(key));
//	        		}
                
                
                //analysis method info here
                this.analysisMethodName = val.method.methodName;
                if (this.analysisMethodName.equals("ratio")) {
                	    this.method.numeratorIdx = val.method.numeratorIdx;
                	    this.method.denominatorIdx = val.method.denominatorIdx;

                    for (int i=0; i<this.method.numeratorIdx.length;i++) {
                    		this.numeratorList.add(mat.group(this.method.numeratorIdx[i]));
                    }
                    for (int j=0; j<this.method.denominatorIdx.length;j++ ) {
                    		this.denominatorList.add(mat.group(this.method.denominatorIdx[j]));
                    }
                    // add key entities
                    
                } else if(this.analysisMethodName.equals("entity")) {
//                	 	this.method.keyEntityIdx = val.method.keyEntityIdx;
//            	 		this.keyEntity = mat.group(this.method.keyEntityIdx);
                	this.method.rankCutoffIdx = val.method.rankCutoffIdx;
                	 	this.topRank = mat.group(this.method.rankCutoffIdx);                	 
                } else if (this.analysisMethodName.equals("dist")) {
//                		this.method.keyEntityIdx = val.method.keyEntityIdx;
//                		this.keyEntity = mat.group(this.method.keyEntityIdx);
                } else if (this.analysisMethodName.equals("gender_ratio")){
//                		this.method.keyEntityIdx = val.method.keyEntityIdx;
//                		this.keyEntity = mat.group(this.method.keyEntityIdx);
                } else if (this.analysisMethodName.equals("count")) {
                	
                } else {
	            		System.out.println("New methods required for "+this.analysisMethodName);
	//            		scan = new Scanner( System.in );
	//            		String pause = scan.nextLine();                	
                }
            }
        }
    }

	  public void formatResult() {
		  
	  		HashMap<String, Term> termDict = new HashMap<>();
	  		
	  		for (String term:this.majorTerms) {
	  			Term mterm = new Term();
	  			// set term
	  			mterm.setTerm(term);
	  			// set domain
	  			List<String> domainArray = new ArrayList<>();
	  			domainArray.add(this.domainDict.get(term));
	  			mterm.setTermDomain(domainArray);
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
	      			// modify the initial event status, set to null
	      			Term denTerm = new Term();
	      			Term ori = termDict.get(den);
	      			denTerm.term = ori.term;
	      			denTerm.termID = ori.termID;
	      			denTerm.domain = ori.domain;
	      			denTerm.iniEvt = ori.iniEvt;
	      			denTerm.negation = ori.negation;
	      			denTerm.timeVal = ori.timeVal;
	      			denTerm.timeUnit = ori.timeUnit;
	      			denTerm.timeRel = ori.timeRel;
	      			denTerm.resetIniEvt();
	      			this.denTermList.add(denTerm);
	      		}    			
	  		} else if (this.analysisMethodName.equals("entity")) {
	  			for (String entry:termDict.keySet()) {
	  				if (this.domainDict.get(entry)!=null) {
//	  					System.out.println(entry);
//	  					System.out.println(this.domainDict.get(entry));
//	  					System.out.println(termDict.get(entry));
	  					this.termList.add(termDict.get(entry));
	  				}
//	  				System.out.println(this.termList.toString());
	  			}
	  		} else if (this.analysisMethodName.equals("gender_ratio")) {
	  			for (String entry:termDict.keySet()) {
	  				if (this.domainDict.get(entry)!=null) {
	  					this.termList.add(termDict.get(entry));
	  				}
	  			}	  			
	  		} else if (this.analysisMethodName.equals("dist")) {
	  			for (String entry:termDict.keySet()) {
	  				if (this.domainDict.get(entry)!=null) {
	  					this.termList.add(termDict.get(entry));
	  				}
	  			}	  			
	  		} else if (this.analysisMethodName.equals("count")) {
	  			for (String entry:termDict.keySet()) {
	  				if (this.domainDict.get(entry)!=null) {
	  					this.termList.add(termDict.get(entry));
	  				}
	  			}	  	  			
	  		} else {
	  			
	  		}
	  }    
    
    // rewrite this part, call postgresql instead of load csv
//    public void formatResult() {
//		// load concept-domain dict file
//    		String csvFile = "/Users/wei/Dropbox/Research/biotranslator_hackathon/CONCEPT.csv";
//    		CSVreader csvreader = new CSVreader();
//    		csvreader.csv2dict(csvFile);
//    		
//    		HashMap<String, ArrayList<String>> term2idDict = csvreader.term2idDict;
//    		HashMap<String, ArrayList<String>> term2domainDict = csvreader.term2domainDict;
//    		HashMap<String, Term> termDict = new HashMap<>();
//    		
//    		for (String term:this.majorTerms) {
//    			Term mterm = new Term();
//    			// set term
//    			mterm.setTerm(term);
//    			// set term ID
//    			mterm.setTermID(term, term2idDict);
//    			// set domain
//    			mterm.setTermDomain(term, term2domainDict);
//    			// set initial event status and time value
//    			if (term.equals(this.priorEvt)) {
//    				mterm.iniEvt=true;
//    				mterm.setTimeVal("0");
//    				mterm.setTimeUnit(this.timeUnit);
//    			} else {
//    				mterm.iniEvt=false;
//    				mterm.setTimeVal(this.timeVal);
//    				mterm.setTimeUnit(this.timeUnit);
//    				mterm.setTimeRel(this.timeRel);
//    			}
//    			termDict.put(term, mterm);
//    		}

		// for a question about percentage, return this.numTermList and this.denTermList, method name is ratio
//    		if (this.analysisMethodName.equals("ratio")) {
//        		for (String num:this.numeratorList) {
//        			this.numTermList.add(termDict.get(num));
//        		} 
//        		for (String den:this.denominatorList) {
//        			// modify the initial event status, set to null
//        			Term denTerm = new Term();
//        			Term ori = termDict.get(den);
//        			denTerm.term = ori.term;
//        			denTerm.termID = ori.termID;
//        			denTerm.domain = ori.domain;
//        			denTerm.iniEvt = ori.iniEvt;
//        			denTerm.negation = ori.negation;
//        			denTerm.timeVal = ori.timeVal;
//        			denTerm.timeUnit = ori.timeUnit;
//        			denTerm.timeRel = ori.timeRel;
//        			denTerm.resetIniEvt();
//        			this.denTermList.add(denTerm);
//        		}    			
//    		}
    		// for another type of question
//    }
}
