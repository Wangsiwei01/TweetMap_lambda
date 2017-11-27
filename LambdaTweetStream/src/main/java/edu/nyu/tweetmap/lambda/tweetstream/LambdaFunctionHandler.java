package edu.nyu.tweetmap.lambda.tweetstream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.nyu.tweetmap.lambda.tweetstream.stream.*;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

    @Override
    public String handleRequest(Object input, Context context) {

        // TODO: implement your handler
	    TwitterStream stream = new TwitterStream();
	    Thread tweetStream = new Thread(stream);
	    tweetStream.start();
	      
		SentimentStream s = new SentimentStream();
		Thread sentimentStream = new Thread(s);
		sentimentStream.start();
		

		while (true);
        
    }

}
