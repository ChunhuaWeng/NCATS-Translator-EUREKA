package edu.columbia.dbmi.ohdsims.tool;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVreader {
	
	HashMap<String, ArrayList<String>> term2idDict = new HashMap<>(  );
	HashMap<String, ArrayList<String>> term2domainDict = new HashMap<>(  );
	
    public void csv2dict(String csvFile) {
        String line = "";
        String cvsSplitBy = "\t";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] cont = line.split(cvsSplitBy);
                String term = cont[1].toLowerCase();
                String conceptID = cont[0];//.toLowerCase();
                if (this.term2idDict.get( term )==null) {
                    this.term2idDict.put(term, new ArrayList<String>(Arrays.asList( conceptID )));
                } else {
                    List<String> currList = this.term2idDict.get(term);
                    if (!currList.contains( conceptID )){
                        this.term2idDict.get( term ).add(conceptID);
                    }
                }
                String domain = cont[2].toLowerCase();
                if (this.term2domainDict.get( term )==null) {
                    this.term2domainDict.put(term, new ArrayList<String>(Arrays.asList( domain )));
                } else {
                    List<String> currList = this.term2domainDict.get(term);
                    if (!currList.contains( domain )){
                        this.term2domainDict.get( term ).add(domain);
                    }
                }                
            }

//            for (String term:this.term2idDict.keySet()){
//                if (this.term2idDict.get(term).size()>1){
//                    System.out.println(term+" "+this.term2idDict.get(term).toString());
//                }
//            }  
//            
//            for (String term:this.term2domainDict.keySet()){
//                if (this.term2domainDict.get(term).size()>1){
//                    System.out.println(term+" "+this.term2domainDict.get(term).toString());
//                }
//            }
          
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
