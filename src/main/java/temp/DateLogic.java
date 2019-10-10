package temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateLogic {

	public static void main(String[] args) throws Exception {
		Date d = new Date();
		System.out.println(d.toString());
		String d1 = "03/04/2019";//dd/MM/yyyy
		
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		Date d2 = sd.parse(d1);
		System.out.println(d2.compareTo(d));
		System.out.println(d2.toString());
		
		
		sd = new SimpleDateFormat("dd");
		String day = sd.format(d2);
		System.out.println(day);

		sd = new SimpleDateFormat("MMM");
		String month = sd.format(d2);
		System.out.println(month);

		sd = new SimpleDateFormat("yyyy");
		String year = sd.format(d2);
		System.out.println(year);
	}

}
