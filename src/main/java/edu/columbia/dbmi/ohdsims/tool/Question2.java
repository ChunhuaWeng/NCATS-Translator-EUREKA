//package edu.columbia.dbmi.ohdsims.tool;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Map.Entry;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.json.simple.parser.ParseException;
//
//public class Question2 {
//	
//	// general fields
//    public String question;
//    public List<Template> newTempTab;
//    public List<String> majorTerms = new ArrayList<>();
//    public String timeVal;
//    public String timeUnit;
//    public String timeRel;
//    public String priorEvt;
//    public String postEvt;
//    public String analysisMethodName;
//    public AnalysisMethod method = new AnalysisMethod();
//    public LinkedHashMap<String, String> domainDict = new LinkedHashMap<String, String>();
////    public LinkedHashMap<String, int[]> domainDict = new LinkedHashMap<String, int[]>();
//    public String keyEntity; // single major term, for entity, dist, gender_ratio questions
//    
//    // for percent questions
//    public List<String> numeratorList = new ArrayList<>();
//    public List<String> denominatorList = new ArrayList<>();
//    public List<Term> numTermList = new ArrayList<Term>();
//    public List<Term> denTermList = new ArrayList<Term>();
//    
//    // for entity type questions
//    public String rankCutoff;
//    
//    // for gender ratio questions
//    
//    // for age distribution questions
// 
//	//get a question
//    public void getQuestion() {
//        System.out.println("Enter a question:\n");
//        Scanner scan = new Scanner( System.in );
//        this.question = scan.nextLine().toLowerCase();
//    }
//
//    // set a question for testing
//    public void setQuestion(String sentence) {
//    		this.question = sentence;
//    }
//    
//    //load templates
//    public void getTemplates() throws IOException, ParseException {
//        String tempPath = "/Users/wei/Dropbox/Research/biotranslator_hackathon/raw_templates.json";
//        Reader reader = new Reader();
////        reader.read(tempPath);
////        this.newTempTab = reader.parseTemplate(); // template list
////        this.newTempTab = reader.readJson(tempPath);
////        for (Template item: this.newTempTab) {
////        		System.out.println(item.template);
////        		System.out.println(item.majorConceptIdx.toString());
////        }
//    }
//
//    //apply templates to the question, return keywords and the associated attributes
//    public void analyzeQuestion() {
//        for (Template val:this.newTempTab) {
//        		String rawPat = val.getTemplate();
//        		Pattern pat = Pattern.compile(rawPat);
////        		System.out.println("\nraw template: "+rawPat);        		
//            
////            int[] majorConceptIdx = val.getMajorConceptIdx();
//            List<Integer> majorConceptIdx = val.getMajorConceptIdx();            
//            Integer timeValIdx = val.timeValIdx;
//        		Integer timeUnitIdx = val.timeUnitIdx;
//        		Integer timeRelIdx = val.timeRelIdx;
//        		Integer priorEvtIdx = val.priorEvtIdx;
//        		Integer postEvtIdx = val.postEvtIdx;
//        		LinkedHashMap<String, String> idxDomainDict = val.idxDomainDict;
//        		        		
//            Matcher mat = pat.matcher( this.question );
//            if (mat.matches()==true) {
//                if (timeRelIdx!=null&&timeValIdx!=null&&timeUnitIdx!=null&&priorEvtIdx!=null&&postEvtIdx!=null) {
////                    for (int i=0;i<majorConceptIdx.size();i++){ //idx={1,3,4,5,1,3}, tempValIdx=4
//                    	for (Integer i:majorConceptIdx){ //idx={1,3,4,5,1,3}, tempValIdx=4                    	
//                    		// add major terms
//                    		String tempTerm = mat.group(i);
//                    		System.out.println(tempTerm);
//                    		this.majorTerms.add(tempTerm);
//                    }
//                    this.timeRel = mat.group(timeRelIdx);
//                    this.timeVal = mat.group(timeValIdx);
//                    this.timeUnit = mat.group(timeUnitIdx).replaceAll("s$", "");          
//                    this.priorEvt = mat.group(priorEvtIdx);
//                    this.postEvt = mat.group(postEvtIdx);
//
//                } else {
//                    for (int i=0;i<majorConceptIdx.size();i++){
//                    		this.majorTerms.add(mat.group(majorConceptIdx.get(i)));
//                    }
//                }
//                // display major terms
//                System.out.println("display major terms");
//                for (String term: this.majorTerms) {
//                		System.out.println(term);
//                }
//                
//                // domain info
//                System.out.println("\ndisplay domain info");
//	        		for (Entry<String, String> entry : idxDomainDict.entrySet()) {
//	        		    String keyIdx = entry.getKey();
//	        		    String value = entry.getValue();
//	        		    String key = mat.group(Integer.valueOf(keyIdx));
//	        		    this.domainDict.put(key,  value);
//	        		}
//	        		for (Entry<String, String> entry: this.domainDict.entrySet()) {
//	        			String key = entry.getKey();
//	        			System.out.println(key+": "+this.domainDict.get(key));
//	        		}
//                
//                
//                //analysis method info here
//                this.analysisMethodName = val.method.methodName;
//                if (this.analysisMethodName.equals("ratio")) {
//                	    this.method.numeratorIdx = val.method.numeratorIdx;
//                	    this.method.denominatorIdx = val.method.denominatorIdx;
//
//                    for (int i=0; i<this.method.numeratorIdx.size();i++) {
//                    		this.numeratorList.add(mat.group(this.method.numeratorIdx.get(i)));
//                    }
//                    for (int j=0; j<this.method.denominatorIdx.size();j++ ) {
//                    		this.denominatorList.add(mat.group(this.method.denominatorIdx.get(j)));
//                    }
//                    // add key entities
//                    
//                } else if(this.analysisMethodName.equals("entity")) {
//                	 	this.method.rankCutoffIdx = val.method.rankCutoffIdx;
//                	 	this.rankCutoff = mat.group(this.method.rankCutoffIdx);                	 
//                } else if (this.analysisMethodName.equals("dist")) {
//
//                } else if (this.analysisMethodName.equals("gender_ratio")){
//
//                } else {
//	            		System.out.println("New methods required for "+this.analysisMethodName);
//	//            		scan = new Scanner( System.in );
//	//            		String pause = scan.nextLine();                	
//                }
//                System.out.println("\ndisplay key entity");
//                System.out.println(this.keyEntity);
//                System.out.println(this.rankCutoff);
//            }
//        }
//    }
//    
//    
//    // rewrite this part, call postgresql instead of load csv
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
//
//		// for a question about percentage, return this.numTermList and this.denTermList, method name is ratio
////    		if (this.analysisMethodName.equals("ratio")) {
////        		for (String num:this.numeratorList) {
////        			this.numTermList.add(termDict.get(num));
////        		} 
////        		for (String den:this.denominatorList) {
////        			// modify the initial event status, set to null
////        			Term denTerm = new Term();
////        			Term ori = termDict.get(den);
////        			denTerm.term = ori.term;
////        			denTerm.termID = ori.termID;
////        			denTerm.domain = ori.domain;
////        			denTerm.iniEvt = ori.iniEvt;
////        			denTerm.negation = ori.negation;
////        			denTerm.timeVal = ori.timeVal;
////        			denTerm.timeUnit = ori.timeUnit;
////        			denTerm.timeRel = ori.timeRel;
////        			denTerm.resetIniEvt();
////        			this.denTermList.add(denTerm);
////        		}    			
////    		}
//    		// for another type of question
//    }
//}
