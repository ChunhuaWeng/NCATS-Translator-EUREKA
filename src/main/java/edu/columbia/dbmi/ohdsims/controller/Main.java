package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Question question = new Question();
        question.getQuestion();
        question.getTemplates();
        question.analyzeQuestion();
        question.getDomain();
//        System.out.println(question.keywordList);
    }
}
