package edu.nyu.tweetmap.lambda.tweetstream.TweetUtil;

import twitter4j.GeoLocation;


public class Tweet {
    private final long id;
    private final String text;
    private final String username;
    private final String date;
    //private final String lat;
    //private final String lon;
    private final double[] location;
    public Tweet(long id, String username, String text, String date, GeoLocation location) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.date = date;
        //this.lat = String.valueOf(location.getLatitude());
        //this.lon = String.valueOf(location.getLongitude());
        this.location = new double[] {location.getLatitude(), location.getLongitude()};
    }
    public Tweet(long id, String username, String text, String date,  double[] location) {
        this.id = id;
        this.username = username;
        this.text = text;
        this.date = date;
        //this.lat = String.valueOf(location.getLatitude());
        //this.lon = String.valueOf(location.getLongitude());
        this.location = location;
    }


    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public double[] getLocation() {
        return location;
    }
}
