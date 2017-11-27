package edu.nyu.tweetmap.lambda.tweetstream.TweetUtil;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EntitiesOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;

public class Processing {
    public static String sentimentAnalysis(Tweet t) {
        String content = t.getText();
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                "",
                ""
              );

              List<String> targets = new ArrayList<>();
              targets.add("stocks");

              SentimentOptions sentiment = new SentimentOptions.Builder()
                .build();

              Features features = new Features.Builder()
                .sentiment(sentiment)
                .build();

              AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                //.text("I am a sutdent and I love study!")
                .text(content)
                .features(features)
                .build(); 
              AnalysisResults response = service
                .analyze(parameters)
                .execute();
              //System.out.println(response);
              return response.getSentiment().getDocument().getScore() > 0 ? "Positive" : "Negative";

    }
}