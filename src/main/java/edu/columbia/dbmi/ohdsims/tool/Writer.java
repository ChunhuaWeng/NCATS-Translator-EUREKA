package edu.columbia.dbmi.ohdsims.tool;
import java.io.*;

public class Writer {
    public void write3(String FileName) throws IOException {
        String content = "this is a test. record it and display it. writer2";
        try (BufferedWriter bw = new BufferedWriter( new FileWriter( FileName, true ) )) {
            bw.write( content );
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write2(String FileName) throws IOException {
        String content = "this is a test. record it and display it. writer2";
        try (BufferedWriter bw = new BufferedWriter( new FileWriter( FileName ) )) {
            bw.write( content );
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String FileName) throws IOException {
        FileWriter fw = new FileWriter( FileName );
        BufferedWriter bw = new BufferedWriter( fw );
        String content = "this is a test. record it and display it.";
        try {
            bw.write( content );
            System.out.println( "done" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
