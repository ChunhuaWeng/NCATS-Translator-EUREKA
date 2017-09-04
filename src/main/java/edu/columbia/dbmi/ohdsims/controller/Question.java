package com.company;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Question {
    String question;
    String template;
    List<String> entityList = new ArrayList<>(  );
    Hashtable<String, String> tempTab;
    List<HashMap<String, ArrayList<String>>> keywordList = new ArrayList<>();

    //load a question
    public void getQuestion() {
        System.out.println("Enter a question:\n");
        Scanner scan = new Scanner( System.in );
        this.question = scan.nextLine();
    }

    //load templates
    public void getTemplates() throws IOException {
        String tempPath = "/Users/wei/Dropbox/Research/biotranslator_hackathon/java_script/raw_templates.txt";
        Reader reader = new Reader();
        reader.read(tempPath);
        this.tempTab = reader.split();
    }

    //analyze the question, return keywords and the associated domains
    public void analyzeQuestion() {
        Enumeration<String> enumKey = this.tempTab.keys();
        while(enumKey.hasMoreElements()) {
            String key = enumKey.nextElement();
            String val = tempTab.get(key);

            Pattern pat = Pattern.compile(val);
            Matcher mat = pat.matcher( this.question );
            if (mat.matches()) {
                this.template = val;
                for (int i = 1; i < (mat.groupCount() + 1); i++){
                    String m = mat.group( i );
                    this.entityList.add( m );
                }
            }
        }
    }

    //get associated domain names
    public void getDomain() {
        // load concept-domain dict file
        String csvFile = "/Users/wei/Dropbox/Research/biotranslator_hackathon/python_scripts/CONCEPT.csv";
        CSVreader csvreader = new CSVreader();
        HashMap<String, ArrayList<String>> termDomainDict = csvreader.csv2dict(csvFile);
        // find domain(s) for the given concept

        for (String entity: entityList){
            HashMap<String, ArrayList<String>> curr_item = new HashMap<>(  );
            curr_item.put(entity, termDomainDict.get(entity));
            keywordList.add(curr_item);
        }
    }
}
