public class LeapYear {
	public static void main(String[] args) {
		int year = 2004;
		if (year%400 == 0) {
			System.out.println(year+" is a leap year.");
		} else if ((year%4 == 0)||(year%100 == 0)) {
			System.out.println(year+" is a leao year..");
		}
	}
}