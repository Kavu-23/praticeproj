package com.ninza.hrm.api.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	
	public int getRandomNum() {
		Random random = new Random();
		int randomNum = random.nextInt(5000);
		return randomNum;
	}
	
	public String getSystemDateYYYYDDMM() {
		
		Date d= new Date();
		SimpleDateFormat sim =new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(d);
		return date;
		
	}
	
	public String getRequireDateYYYYDDMM(int days) {
		
		Date d= new Date();
		SimpleDateFormat sim =new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(d);
		
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate =sim.format(cal.getTime());
		return reqDate;
	}
}
