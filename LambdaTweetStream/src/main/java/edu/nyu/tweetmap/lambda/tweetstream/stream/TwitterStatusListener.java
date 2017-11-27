package edu.nyu.tweetmap.lambda.tweetstream.stream;

import twitter4j.*;


public abstract class TwitterStatusListener implements StatusListener {
	   @Override
	    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		   // do nothing
	    }

	    @Override
	    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	    }

	    @Override
	    public void onScrubGeo(long userId, long upToStatusId) {
	        // do nothing
	    }

	    @Override
	    public void onStallWarning(StallWarning warning) {
	        // do nothing
	    }

	    @Override
	    public void onException(Exception e) {
	    	 System.out.println("Exception occured:" + e.getMessage());
             e.printStackTrace();
	    }

}
