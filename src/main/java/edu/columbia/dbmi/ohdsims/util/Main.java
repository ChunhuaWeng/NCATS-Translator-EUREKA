package edu.columbia.dbmi.ohdsims.util;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.nio.file.Paths.*;

public class Main {


    public static void main(String[] args) throws IOException {
//        String curr = System.getProperty("user.dir");
//        System.out.println(curr);
////        "src/edu/columbia/dbmi/ohdsi/"
//        Path currentRelativePath = Paths.get("");
//        Path currentDir = currentRelativePath.toAbsolutePath(); // <-- Get the Path and use resolve on it.
//        String filename = "data" + File.separatorChar + "foo.txt";
//        Path filepath = currentDir.resolve(filename);
        Question question = new Question();
        question.getQuestion();
        question.getTemplates();
        question.analyzeQuestion();
        question.getConceptID();
        System.out.println(question.keyConcepts);
    }
}
