package Practice;

import java.util.Date;

public class GetSystemDate {
	public static void main(String[] args) {
		
	
	Date date = new Date();
	String currentDate = date.toString();
	System.out.println(currentDate);
	String[] arr = currentDate.split(" ");
	String yyyy = arr[5];
	String dd = arr[2];
	@SuppressWarnings("deprecation")
	int mm = date.getMonth()+1;

		String formate = yyyy+"-"+mm+"-"+dd;

}}
