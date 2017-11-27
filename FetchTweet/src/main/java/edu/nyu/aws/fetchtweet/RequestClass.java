package edu.nyu.aws.fetchtweet;


public class RequestClass {
   String type;
   String keyword;
   String lat;
   String lon;

   public String getType() {
       return type;
   }

   public void setType(String type) {
       this.type = type;
   }

   public String getKeyword() {
       return keyword;
   }

   public void setKeyword(String keyword) {
       this.keyword = keyword;
   }
   
   public String getLat() {
       return lat;
   }

   public void setLat(String lat) {
       this.lat = lat;
   }

   public String getLon() {
       return lon;
   }

   public void setLon(String lon) {
       this.lon = lon;
   }

   public RequestClass(String type, String keyword, String lat, String lon) {
       this.type = type;
       this.keyword = keyword;
       this.lat = lat;
       this.lon = lon;
   }

   public RequestClass() {
   }
}