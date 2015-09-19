package com.amazinghack.broker;

import static com.amazinghack.data.DataStore.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.util.*;

public class Broker {

  private static final String EXCHANGE_NAME = "topic_logs";

  public static void main(String[] argv) {
    Connection connection = null;
    Channel channel = null;
    try {
      ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("localhost");

      connection = factory.newConnection();
      channel = connection.createChannel();

      channel.exchangeDeclare(EXCHANGE_NAME, "topic");
      Integer productId=12788;
      HashMap<String,Object> attributes=new HashMap<String,Object>();
      attributes.put("LISTPRICE",123.45);
      attributes.put("AUTHOR","ChetanBhagat");
      attributes.put("TITLE","TWOSTATES");
      attributes.put("DATE","21122010");
      attributes.put("PUBLISHER","RaiPublications");
     
    
      String routingKey = frameRoutingKey(attributes,productId);
      String message = productId.toString();

      channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
      System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

    }
    catch  (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (connection != null) {
        try {
          connection.close();
        }
        catch (Exception ignore) {}
      }
    }
  }

  private static String getRouting(String[] strings){
    if (strings.length < 1)
    	    return "anonymous.info";
    return strings[0];
  }

  public static String frameRoutingKey(HashMap<String,Object> attributes,Integer productId){
    String outputMessage=productId.toString();
    Set<String> productAttributes=attributes.keySet();
    outputMessage=outputMessage+"."+attributes.get("TITLE");
    outputMessage=outputMessage+"."+attributes.get("AUTHOR");
    outputMessage=outputMessage+"."+attributes.get("DATE").toString();
    outputMessage=outputMessage+"."+attributes.get("PUBLISHER");
    outputMessage=outputMessage+"."+attributes.get("LISTPRICE").toString();
    return outputMessage;
  } 

  private static String getMessage(String[] strings){
    if (strings.length < 2)
    	    return "Hello World!";
    return joinStrings(strings, " ", 1);
  }

  private static String joinStrings(String[] strings, String delimiter, int startIndex) {
    int length = strings.length;
    if (length == 0 ) return "";
    if (length < startIndex ) return "";
    StringBuilder words = new StringBuilder(strings[startIndex]);
    for (int i = startIndex + 1; i < length; i++) {
        words.append(delimiter).append(strings[i]);
    }
    return words.toString();
  }
}