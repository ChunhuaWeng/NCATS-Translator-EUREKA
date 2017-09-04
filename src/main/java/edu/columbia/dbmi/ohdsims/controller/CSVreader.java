package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVreader {
    public HashMap<String, ArrayList<String>> csv2dict(String csvFile) {
        String line = "";
        String cvsSplitBy = "\t";
        HashMap<String, ArrayList<String>> rec = new HashMap<>(  );

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] cont = line.split(cvsSplitBy);
                String term = cont[1].toLowerCase();
                String domain = cont[2].toLowerCase();
                if (rec.get( term )==null) {
                    rec.put(term, new ArrayList<String>(Arrays.asList( domain )));
                } else {
                    List<String> currList = rec.get(term);
                    if (!currList.contains( domain )){
                        rec.get( term ).add(domain);
                    }
                }
            }
//            for (String term:rec.keySet()){
//                if (rec.get(term).size()>1){
//                    System.out.println(term+" "+rec.get(term).toString());
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rec;
    }
}
