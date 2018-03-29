package validate;

import java.util.regex.Pattern;

public class Validation {
	public boolean EmailVali(String email) {
		String pattern = "^[a-zA-Z0-9\\-_]+@[a-zA-Z0-9\\-_.]+$";
		boolean a = Pattern.matches(pattern, email);				
		
		return a;
	}
	public static boolean PassVali(String pass) {
		String pattern = "^([a-zA-Z0-9!@#%&*()^$]){8,16}+$";
		boolean a = Pattern.matches(pattern, pass);
		return a;
	}
}



