package edu.columbia.dbmi.ohdsims.util;
import java.io.*;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

public class Reader {
    public List<String> rec = new ArrayList<String>();
    public String sep = System.getProperty( "line.separator" );

    public void read(String FileName) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader( FileName ));
        while ((line=br.readLine())!=null){
            this.rec.add( line );
        }
    }

    public LinkedHashMap<String, List> newSplit() {
        LinkedHashMap<String, List> tempTab = new LinkedHashMap<>();
        for (String sent:this.rec) {
            List<Object> cont = new ArrayList<Object>();
            String[] comp = sent.split(":");
            String ini = comp[0];
            String[] val = comp[1].split("##");

            String pat = val[0];
            cont.add(pat);

            String rawIdx = val[1].substring(1, val[1].length() - 1);
            int[] idx = Arrays.stream(rawIdx.split(",")).mapToInt(Integer::parseInt).toArray();
            cont.add(idx);

            // add temp info
            String rawTempIdx = val[2].substring(1, val[2].length() - 1);
            if (StringUtils.isEmpty(rawTempIdx)==false){
                String[] tempIdx = rawTempIdx.split(",");
                int timeValIdx = Integer.parseInt(tempIdx[0]);
                cont.add(timeValIdx);
                int timeUnitIdx = Integer.parseInt(tempIdx[1]);
                cont.add(timeUnitIdx);
            } else {
                cont.add(null);
                cont.add(null);
            }

            //add temp relation
            String rawTempRel = val[3].substring(1, val[3].length() - 1);
            if (StringUtils.isEmpty(rawTempIdx)==false){
                String[] tempRel = rawTempRel.split(",");
                int priorEventIdx = Integer.parseInt(tempRel[0]);
                cont.add(priorEventIdx);
                int postEventIdx = Integer.parseInt(tempRel[1]);
                cont.add(postEventIdx);
            } else {
                cont.add(null);
                cont.add(null);
            }

            //add analysis method
            String rawAlsMtd = val[4].substring(1, val[4].length() - 1);
            List<Object> method = new ArrayList<>();
            String[] alsMtd = rawAlsMtd.split(",");
            String mtd = alsMtd[0];
            if (mtd.equals("ratio")) { // use the ratio method
                method.add(mtd);
                method.add(Integer.parseInt(alsMtd[1]));
                method.add(Integer.parseInt(alsMtd[2]));
                cont.add(method);
            } else {
                // more methods here
                method.add(null);
                cont.add(method);
            }
//            System.out.println(cont.toString());
            tempTab.put(ini, cont);
        }
        return tempTab;
    }


    public Hashtable<String, String> split() {
        Hashtable<String, String> tempTab = new Hashtable<>(  );
        for (String temp:this.rec){
            String[] parts = temp.split( ":" );
            tempTab.put(parts[0], parts[1]);
        }
        return tempTab;
    }
}
