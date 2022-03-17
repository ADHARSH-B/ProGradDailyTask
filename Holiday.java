class Holiday{
	private String name;
	private int day;
	private String month;
	Holiday(String name,int day,String month){
		this.name=name;
		this.day=day;
		this.month=month;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public static double avgDate(Holiday arr[]) {
		int sum_=0;
		for(int i=0;i<arr.length;i++)
			sum_+=arr[i].getDay();
		return sum_/arr.length;
	}
	public static boolean isSameMonth(Holiday h1,Holiday h2) {
		return h1.getMonth().equals(h2.getMonth());
	}
}
public class HolidayMain {
	
	public static void main(String[] args) {
		Holiday h1 = new Holiday("Independence Day",4,"January");
		Holiday h2 = new Holiday("Childrens Day",3,"February");
		Holiday h3 = new Holiday("Teachers Day",2,"March");
		Holiday h4 = new Holiday("Sunday",1,"January");
		
		Holiday arr[]= {h1,h2,h3,h4};
		System.out.println(Holiday.avgDate(arr));
		System.out.println(Holiday.isSameMonth(h1, h4));
		
	}

}
