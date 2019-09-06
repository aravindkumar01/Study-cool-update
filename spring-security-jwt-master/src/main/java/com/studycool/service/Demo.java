package com.studycool.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Demo {
	
	public static void main(String[] args) {
		
		   GregorianCalendar gcalendar = new GregorianCalendar();		   
		   		System.out.print( gcalendar.get(Calendar.DATE) + " "+gcalendar.get(Calendar.MONTH)+" "+gcalendar.get(Calendar.YEAR));
		      
	}

}
