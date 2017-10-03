package edu.columbia.dbmi.ohdsi;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Question {

    String question;
    String template;
    List<String> entityList = new ArrayList<>();
    List<String> tempList = new ArrayList<>();
    Hashtable<String, String> tempTab;
    LinkedHashMap<String, List> newTempTab;
//    List<HashMap<String, ArrayList<String>>> keywordList = new ArrayList<>();
//    List<HashMap<String, ArrayList<String>>> keyConcepts = new ArrayList<>();
    LinkedHashMap<String, List<String>> keyConcepts = new LinkedHashMap<>();
    List<Object> results = new ArrayList<>();

    //load a question
    public void getQuestion() {
        System.out.println("Enter a question:\n");
        Scanner scan = new Scanner( System.in );
        this.question = scan.nextLine().toLowerCase();
    }

    //load templates
    public void getTemplates() throws IOException {
        String tempPath = "/Users/weiwei/Dropbox/Research/biotranslator_hackathon/eureka_local/src/edu/columbia/dbmi/ohdsi/raw_templates.txt";
        Reader reader = new Reader();
        reader.read(tempPath);
//        this.tempTab = reader.split();
        this.newTempTab = reader.newSplit();
//        this.newTempTab.forEach((String key, List value) -> {
//                    System.out.println(key);
//                    System.out.println("template: "+value.get(0));
//                    System.out.println("key concept ids");
//                    int[] idx = (int[]) value.get(1);
//                    for (int i=0; i<idx.length; i++) {
//                        System.out.println(idx[i]);
//                    }
//                    System.out.println("temp info "+value.get(2));
//                    System.out.println("temp unit "+value.get(3));
//                    System.out.println("temp rel prior "+value.get(4));
//                    System.out.println("temp rel post "+value.get(5));
//                }
//        );
//        System.out.println("==========");
    }

    //analyze the question, return keywords and the associated domains
    public void analyzeQuestion() {

        for (String key:this.newTempTab.keySet()) {
            List<Object> val = this.newTempTab.get(key);
            String rawPat = (String) val.get(0);
//            System.out.println("raw pat: "+rawPat);
            Pattern pat = Pattern.compile(rawPat);

            int[] idx = (int[]) val.get(1);
//            for (int i=0; i<idx.length;i++) {
//                System.out.println(idx[i]);
//            }

            Integer tempValIdx = (Integer) val.get(2);
            Integer tempUnitIdx = (Integer) val.get(3);
            Integer priorEventIdx = (Integer) val.get(4);
            Integer postEventIdx = (Integer) val.get(5);

            Matcher mat = pat.matcher( this.question );
            if (mat.matches()==true) {
                if (tempValIdx!=null&&tempUnitIdx!=null&&priorEventIdx!=null&&postEventIdx!=null) {
                    for (int i=0;i<idx.length;i++){ //idx={1,3,4,5,1,3}, tempValIdx=4
                        if (i<tempValIdx){
                            this.entityList.add(mat.group(idx[i]));
                        }
                    }
                    String tempVal = mat.group(tempValIdx);
                    String tempUnit = mat.group(tempUnitIdx);
                    String priorEvent = mat.group(priorEventIdx);
                    String postEvent = mat.group(postEventIdx);
                    this.tempList.add(tempVal);
                    this.tempList.add(tempUnit);
                    this.tempList.add(priorEvent);
                    this.tempList.add(postEvent);
                } else {
                    for (int i=0;i<idx.length;i++){
                        this.entityList.add(mat.group(idx[i]));
                    }
                }
            }
        }
//        System.out.println("entityList: "+this.entityList.toString());
//        System.out.println("tempList: "+this.tempList.toString());
    }

    //get associated conceptID
    public void getConceptID() {
        // load concept-domain dict file
        String csvFile = "/Users/weiwei/Dropbox/Research/biotranslator_hackathon/eureka_local/src/edu/columbia/dbmi/ohdsi/CONCEPT.csv";
        CSVreader csvreader = new CSVreader();
        HashMap<String, ArrayList<String>> termConceptIDDict = csvreader.csv2dict(csvFile);

        // find concept ID or the given term
        for (String entity: this.entityList){
//            System.out.println(entity);
//            System.out.println(termConceptIDDict.get(entity));
            this.keyConcepts.put(entity, termConceptIDDict.get(entity));
        }
        this.keyConcepts.put("tempRel", this.tempList);
    }
}


//    //analyze the question, return keywords and the associated domains
//    public void analyzeQuestion() {
//        Enumeration<String> enumKey = this.tempTab.keys();
//        while(enumKey.hasMoreElements()) {
//            String key = enumKey.nextElement();
//            String val = tempTab.get(key);
//            Pattern pat = Pattern.compile(val);
//            Matcher mat = pat.matcher( this.question );
//            if (mat.matches()) {
////                this.template = val;
////                System.out.println(mat.group(2));
////                System.out.println(mat.group(4));
//                for (int i = 1; i < (mat.groupCount() + 1); i++){
//                    String m = mat.group( i );
//                    System.out.println(m);
//                    this.entityList.add( m );
//                }
//                System.out.println(this.entityList.toString());
//            }
//        }
//    }

//    //analyze the question, return keywords and the associated domains
//    public void analyzeQuestion() {
//        Enumeration<String> enumKey = this.tempTab.keys();
//        while(enumKey.hasMoreElements()) {
//            String key = enumKey.nextElement();
//            String val = tempTab.get(key);
//            Pattern pat = Pattern.compile(val);
//            Matcher mat = pat.matcher( this.question );
//            if (mat.matches()) {
////                this.template = val;
////                System.out.println(mat.group(2));
////                System.out.println(mat.group(4));
//                for (int i = 1; i < (mat.groupCount() + 1); i++){
//                    String m = mat.group( i );
//                    System.out.println(m);
//                    this.entityList.add( m );
//                }
//                System.out.println(this.entityList.toString());
//            }
//        }
//    }