package edu.nyu.aws.fetchtweet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<RequestClass, String> {

    @Override
    public String handleRequest(RequestClass request, Context context) {
        context.getLogger().log("Input: " + request.toString());
     
        // TODO: implement your handler
        //return input.toString();
        if(request.type.equals("all"))
        	return Elasticsearch.ElasticFetchAll();
        
        if(request.type.equals("keyword"))
        	return Elasticsearch.ElasticFetchByKeywords(request.keyword);
        
        if(request.type.equals("location"))
        	return Elasticsearch.ElasticFetchByDistance(request.lat, request.lon, "100");
        
        if(request.type.equals("total"))
        	return Elasticsearch.ElasticFetchToatal();
        
        return "no data";
    }

}
