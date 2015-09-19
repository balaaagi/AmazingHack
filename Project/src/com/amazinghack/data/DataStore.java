package com.amazinghack.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class DataStore {
	public static HashMap<String , ArrayList<HashMap<String , Criteria>>> fieldSubscription;
	public static HashMap<Integer,HashMap<String,String>> dataValues;
	public static Queue<HashMap<Integer,ArrayList<String>>> notificationQueue;
}
