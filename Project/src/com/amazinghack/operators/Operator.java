package com.amazinghack.operators;

import java.util.Date;

public interface Operator {

	boolean check(String conditionValue, String itemFieldValue);

	boolean check(Date conditionValue, Date itemFieldValue);

	boolean check(int conditionValue, int itemFieldValue);

}
