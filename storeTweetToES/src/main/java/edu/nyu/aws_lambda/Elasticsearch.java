package edu.nyu.aws_lambda;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;

import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.apache.http.entity.ContentType;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.nio.entity.NStringEntity;

public class Elasticsearch {
    private static final String host = ""; // e.g. my-test-domain.us-east-1.es.amazonaws.com
    //private static final RestClient client = RestClient.builder(new HttpHost(host, 443, "https")).build();//start the client,  has the same lifecycle as the application
    
    public static String ElasticIndex(String json) throws IOException {
        String index = "tweets_sentiment";
        String type = "tweet_sentiment";
        

        RestClient client = RestClient.builder(new HttpHost(host, 443, "https")).build();

        HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);

        Response response = client.performRequest("POST", "/" + index + "/" + type + "/",
            Collections.<String, String>emptyMap(), entity);
        
        client.close();

        return response.toString();
    }
    
}