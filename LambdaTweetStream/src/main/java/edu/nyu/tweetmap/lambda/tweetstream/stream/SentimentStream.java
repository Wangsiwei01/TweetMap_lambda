package edu.nyu.tweetmap.lambda.tweetstream.stream;
import edu.nyu.tweetmap.lambda.tweetstream.AWS.*;
import edu.nyu.tweetmap.lambda.tweetstream.TweetUtil.*;
import com.google.gson.Gson;

public class SentimentStream implements Runnable{
	public void run() {
		while(true) {
			String tweet_json = TweetSQS.receiveMessage();
			//System.out.println("tweet_json:" + tweet_json);
			if(tweet_json.equals("queue is empty")) {
					try {
						System.out.println(tweet_json);
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
			}
			Tweet t = new Gson().fromJson(tweet_json, Tweet.class);
			String sentiment = "";
			try {
				sentiment = Processing.sentimentAnalysis(t);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
	    	//System.out.println(tweet_json);
	    	TweetWithSentiment ts = new TweetWithSentiment(t.getId(), t.getUsername(), t.getText(), t.getDate(), t.getLocation(), sentiment);
			//System.out.println(sentiment);
			TweetSNS.publistToSNS(new Gson().toJson(ts));
		}
	}
}
