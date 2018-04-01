package validate;

import java.util.regex.Pattern;

public class Validation {
	public boolean EmailVali(String email) {
		String pattern = "^[a-zA-Z0-9\\-_]+@[a-zA-Z0-9\\-_\\.]+\\.[a-zA-Z]+$";
		boolean a = Pattern.matches(pattern, email);				
		
		return a;
	}
	public boolean PassVali(String pass) {
		String pattern = "^([a-zA-Z0-9!@#%&*()^$]){8,16}+$";
		boolean a = Pattern.matches(pattern, pass);
		return a;
	}
	public boolean DateVali(String date) {
		String pattern = "^[1|2]{1}[0|9]{1}[0-9]{2}[0|1]{1}[0-9]{1}[0|1|2|3]{1}[0-9]{1}$";
		boolean a =  Pattern.matches(pattern, date);
		return a;
	}
	public boolean PhoneVali(String phone) {
		String pattern = "^01([0-1]|[6-9]{1})([0-9]{7,8})$";
		boolean a = Pattern.matches(pattern, phone);
		return a;
	}
}
