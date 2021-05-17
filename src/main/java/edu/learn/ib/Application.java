package edu.learn.ib;

import java.util.TimeZone;

public class Application {
	public static void main(String[] args) {
		System.out.println(TimeZone.getTimeZone("EST"));
		System.out.println("-----------");
		System.out.println(TimeZone.getTimeZone("EDT"));
		System.out.println("-----------");
		System.out.println(TimeZone.getTimeZone("America/New_York"));
		System.out.println("-----------");
		System.out.println(TimeZone.getTimeZone("EST5EDT"));
		System.out.println("-----------");
		System.out.println(TimeZone.getTimeZone("GMT-4"));
	}
}