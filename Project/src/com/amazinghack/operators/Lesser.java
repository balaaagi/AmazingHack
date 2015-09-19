package com.amazinghack.operators;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Lesser implements Operator {

	@Override
	public boolean check(String conditionValue, String itemFieldValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean check(Date conditionValue, Date itemFieldValue) {
		
		Calendar condition = new GregorianCalendar();
		Calendar item = new GregorianCalendar();
		condition.setTime(conditionValue);
		item.setTime(itemFieldValue);
		
		if(condition.get(Calendar.YEAR)<item.get(Calendar.YEAR)){
			return true;
		}
		else if((condition.get(Calendar.YEAR)==item.get(Calendar.YEAR))&&(condition.get(Calendar.MONTH)<item.get(Calendar.MONTH))){
			return true;
		}
		else if((condition.get(Calendar.YEAR)==item.get(Calendar.YEAR))&&(condition.get(Calendar.MONTH)==item.get(Calendar.MONTH))&&(condition.get(Calendar.DATE)<item.get(Calendar.DATE))){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean check(int conditionValue, int itemFieldValue) {
		// TODO Auto-generated method stub
		if(conditionValue<itemFieldValue){
			return true;
		}
		return false;
	}

}
