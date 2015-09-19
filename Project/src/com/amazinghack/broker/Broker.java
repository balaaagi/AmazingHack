package com.amazinghack.broker;

import static com.amazinghack.data.DataStore.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Broker {

	public void notifySubscribers(HashMap<String,ArrayList<Integer>> modifiedFields) throws ParseException {
		Set<String> fieldList = modifiedFields.keySet();
		for(String fieldName:fieldList){
			ArrayList<Integer> modifiedItems = modifiedFields.get(fieldName);
			ArrayList<HashMap<String,Criteria>> fieldMap = fieldSubscription.get(fieldName);
			for(HashMap<String, Criteria> subscriberCriteria:fieldMap){
				validateCriteria(modifiedItems,subscriberCriteria,fieldName);
			}
		}
	}
	
	private void validateCriteria(ArrayList<Integer> modifiedItems,
			HashMap<String,Criteria> subscriberCriteria, String fieldName) throws ParseException {
		// TODO Auto-generated method stub
		for(Integer item:modifiedItems) {

			HashMap<Integer,ArrayList<String>> subscriberList = new HashMap<Integer, ArrayList<String>>();
			subscriberList.put(item, new ArrayList<String>());
			
			HashMap<String,String> itemData = dataValues.get(item);
			String itemFieldValue = itemData.get(fieldName);

			for(String subscriberName:subscriberCriteria.keySet()) {
				Criteria subscriptionCriteria = subscriberCriteria.get(subscriberName);
				if(subscriptionCriteria.isConditionTrueFor(fieldName, itemFieldValue)){
					subscriberList.get(item).add(subscriberName);
				}
			}
			if(subscriberList.get(item).size()>0) {
				addToQueue(subscriberList);
			}
		}
	}

	private void addToQueue(HashMap<Integer,ArrayList<String>> subscriberList) {
		// TODO Auto-generated method stub
		if(notificationQueue==null){
			notificationQueue = new LinkedList<HashMap<Integer, ArrayList<String>>>();
		}
		try {
			notificationQueue.add(subscriberList);
		}
		catch(IllegalStateException e){
			System.out.println("notificationQueue out of space");
		}
	}
}
