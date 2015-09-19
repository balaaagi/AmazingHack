package com.amazinghack.subscriber;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Criteria {
	
	Operator condition;
	String conditionValue;
	
	public Criteria(String condition,String conditionValue) {
		this.conditionValue = conditionValue;
		this.condition = (Operator)getOperator(condition);
	}
	public boolean isConditionTrueFor(String itemFieldValue, String fieldName) throws ParseException {
		if(fieldName.equals("title")||fieldName.equals("publisher")||fieldName.equals("authors")) {
			if(condition.check(conditionValue,itemFieldValue)) {
				return true;
			}
		}
		else if(fieldName.equals("release date")) {
			if(condition.check(toDate(conditionValue),toDate(itemFieldValue))) {
				return true;
			}
		}
		else if(fieldName.equals("list price")) {
			if(condition.check(Integer.parseInt(conditionValue),Integer.parseInt(itemFieldValue))) {
				return true;
			}
		}
		return false;
	}
	
	public static Operator getOperator(String condition) {
		if(condition.equals("=")){
			return new Equals();
		}
		else if(condition.equals(">")){
			return new Greater();
		}
		else if(condition.equals("<")){
			return new Lesser();
		}
		else if(condition.equals(">=")){
			return new GreaterAndEquals();
		}
		else if(condition.equals("<=")){
			return new LesserAndEquals();
		}
		return null;
	}
	
	private Date toDate(String itemFieldValue) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		Date date = format.parse(itemFieldValue);
		return date;
	}
}
