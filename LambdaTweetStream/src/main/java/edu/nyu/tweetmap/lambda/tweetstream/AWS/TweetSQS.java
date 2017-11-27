package edu.nyu.tweetmap.lambda.tweetstream.AWS;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

import java.util.List;
import java.util.Map.Entry;

public class TweetSQS {

    private static final AmazonSQS sqs;
    private static String queue_url = "";

    static {   
        sqs = AmazonSQSClientBuilder.standard()
        							.withRegion("us-east-1")
        							.build();
    }

    private static void createQueue() {
        System.out.println("Creating new Querue.");
        CreateQueueRequest create_request = new CreateQueueRequest("Tweets");
        try {
            sqs.createQueue(create_request);
        } catch (AmazonSQSException e) {
        	System.err.println(e.getMessage());
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }
        queue_url = sqs.getQueueUrl("Tweets").getQueueUrl();
        System.out.println("Created a new SQS: " + queue_url);
    }

    private static void fetchMessageQueue() {
        System.out.println("Fetch a SQS queue if exists, otherwise create a new one.");
        while (queue_url.equals("")) {
            List<String> queue_urls = sqs.listQueues("Tweets").getQueueUrls();
            if (queue_urls.isEmpty()) {
                createQueue();
            }
            else {
            	queue_url = queue_urls.get(0);
            }
        }
        System.out.println("Fetch a SQS queue: " + queue_url);
    }

    public static void sendMessage(String json) {
        if (queue_url.equals("")) {
            fetchMessageQueue();
        }
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queue_url)
                .withMessageBody(json);
        sqs.sendMessage(send_msg_request);
    }
    
    public static String receiveMessage() {
        if (queue_url.equals("")) {
            fetchMessageQueue();
        }
        System.out.println("Receiving messages from MyQueue.\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queue_url);
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        //System.out.println(messages.size());
        if(messages!= null && messages.size() > 0){
        	Message message = messages.get(0);
        	String message_body = message.getBody();
        	String messageReceiptHandle = message.getReceiptHandle();
        	sqs.deleteMessage(new DeleteMessageRequest(queue_url, messageReceiptHandle));
        	System.out.println("message_body:" + message_body);
        	return message_body;
        } else {
        	return "queue is empty";
        }

    }
}