package edu.nyu.tweetmap.lambda.tweetstream.AWS;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;

public class TweetSNS {
	private static AmazonSNS sns;
	private static String topicArn = "";
	static {
		sns = AmazonSNSClientBuilder.standard().withRegion("us-east-1").build();
	}
	public static void publistToSNS(String json) {
		PublishRequest publishRequest = new PublishRequest(topicArn, json);
		sns.publish(publishRequest);
	}

}
