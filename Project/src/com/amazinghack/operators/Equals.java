package com.amazinghack.operators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Equals implements Operator {

	@Override
	public boolean check(String conditionValue, String itemFieldValue) {
		// TODO Auto-generated method stub
		if(conditionValue.equals(itemFieldValue)){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean check(Date conditionValue, Date itemFieldValue) {

		Calendar condition = new GregorianCalendar();
		Calendar item = new GregorianCalendar();
		condition.setTime(conditionValue);
		item.setTime(itemFieldValue);

		if((condition.get(Calendar.DATE)==item.get(Calendar.DATE))&&
				(condition.get(Calendar.MONTH)==item.get(Calendar.MONTH))&&
				(condition.get(Calendar.YEAR)==item.get(Calendar.YEAR))) {
			return true;
		}
		return false;
	}

	@Override
	public boolean check(int conditionValue, int itemFieldValue) {

		if(conditionValue==itemFieldValue){
			return true;
		}
		return false;
	}

}
