package edu.columbia.dbmi.ohdsims.tool;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Question question = new Question();
        question.getQuestion();
        question.getTemplates();
        question.analyzeQuestion();
        question.getResults();

        System.out.println("主要概念 "+question.outcome.majorConceptIDs.toString());
        System.out.println("时间 "+question.outcome.timeRels.timeVal);
        System.out.println("时间单位 "+question.outcome.timeRels.timeUnit);
        System.out.println("先发生事件 "+question.outcome.timeRels.priorEvent);
        System.out.println("后发生事件 "+question.outcome.timeRels.postEvent);
        System.out.println("分析方法 "+question.outcome.method.methodName);
        System.out.println("分子 "+question.outcome.method.numerator);
        System.out.println("分母 "+question.outcome.method.denominator);

        // find concept ID using the name
        System.out.println("insulin ID: "+question.outcome.majorConceptIDs.get("insulin").toString());
        System.out.println("type 1 diabetes mellitus ID: "+question.outcome.majorConceptIDs.get("type 1 diabetes mellitus").toString());
//        question.getConceptID();

//        System.out.println(question.keyConcepts);

//        String tempPath = "/Users/weiwei/Dropbox/Research/biotranslator_hackathon/eureka_local/src/edu/columbia/dbmi/ohdsi/raw_templates.txt";
//        NewReader reader = new NewReader();
//        reader.read(tempPath);
//        reader.analyze();

//        newTempTab = reader.newSplit();

    }
}
