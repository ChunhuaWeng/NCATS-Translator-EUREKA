package edu.columbia.dbmi.ohdsims.tool;
import java.util.*;

public class DisplayHashMap {
    public static void show(HashMap<String, String> hashMap) {
        Enumeration names = (Enumeration) hashMap.keySet();
        String key;
        while(names.hasMoreElements())
        {
            key = (String) names.nextElement();
            System.out.println("---key: " + key + " val: " + hashMap.get(key));
        }
    }
}
