package edu.nyu.tweetmap.lambda.tweetstream.TweetUtil;

import twitter4j.GeoLocation;

public class TweetWithSentiment extends Tweet{
	
	private final String sentiment;

	public TweetWithSentiment(long id, String username, String text, String date, double[] location, String sentiment) {
		super(id, username, text, date, location);
		this.sentiment = sentiment;
		// TODO Auto-generated constructor stub
	}
	
	public String getSentiment() {
		return this.sentiment;
	}
}