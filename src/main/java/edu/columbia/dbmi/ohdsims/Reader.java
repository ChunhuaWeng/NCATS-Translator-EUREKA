package com.company;
import java.io.*;
import java.util.*;

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

    public Hashtable<String, String> split() {
        Hashtable<String, String> tempTab = new Hashtable<>(  );
        for (String temp:this.rec){
            String[] parts = temp.split( ":" );
            tempTab.put(parts[0], parts[1]);
        }
        return tempTab;
    }
}
