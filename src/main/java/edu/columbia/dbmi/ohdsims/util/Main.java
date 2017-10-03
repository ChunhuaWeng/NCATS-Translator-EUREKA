package edu.columbia.dbmi.ohdsims.util;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {


    public static void main(String[] args) throws IOException {
        Question question = new Question();
        question.getQuestion();
        question.getTemplates();
        question.analyzeQuestion();
        question.getConceptID();
        System.out.println(question.keyConcepts);
    }
}
