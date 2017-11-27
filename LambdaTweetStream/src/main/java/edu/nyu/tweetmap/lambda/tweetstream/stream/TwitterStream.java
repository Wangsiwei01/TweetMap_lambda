package edu.nyu.tweetmap.lambda.tweetstream.stream;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import  edu.nyu.tweetmap.lambda.tweetstream.TweetUtil.*;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import edu.nyu.tweetmap.lambda.tweetstream.AWS.*;

public class TwitterStream implements Runnable{
	private final twitter4j.TwitterStream stream;
	private final TwitterStatusListener listener;
	public TwitterStream() {
		stream = this.getStream();
		listener = new TwitterStatusListener() {
            @Override
            public void onStatus(Status status) {
                if (status.getGeoLocation() != null) {
                    String date = TweetDateConverter.convert(status.getCreatedAt());
                    Tweet tweet = new Tweet(status.getId(), status.getUser().getScreenName(),
                            status.getText(), date, status.getGeoLocation());

                    //System.out.println(tweet.toString());
                    sendToSQS(tweet);
                }
            }
		};
		this.stream.addListener(listener);
	}
	
	private twitter4j.TwitterStream getStream(){
		 ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
	     configurationBuilder.setOAuthConsumerKey("")
	                		 .setOAuthConsumerSecret("")
	                		 .setOAuthAccessToken("")
	                		 .setOAuthAccessTokenSecret("");
	     return new TwitterStreamFactory(configurationBuilder.build()).getInstance();
	}
	
    @Override
    public void run() {
        stream.sample("en");
    }
    
    private void sendToSQS(Tweet t) {

        try {
            TweetSQS.sendMessage(new Gson().toJson(t));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
