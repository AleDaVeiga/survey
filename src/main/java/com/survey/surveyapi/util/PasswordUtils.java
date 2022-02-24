package com.survey.surveyapi.util;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;

public class PasswordUtils {
	private static final Logger logger = getLogger(PasswordUtils.class);

	public static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{6,16})";
	
	public static String getRandomPassword(int numMaxCarac) {
		Random ran = new Random();
		char[] caractetes = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
				'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z' };
		char[] result = new char[numMaxCarac];
		try {
			for (int i = 0; i < numMaxCarac; i++) {
				int a = ran.nextInt(caractetes.length);
				result[i] = caractetes[a];
			}
			// force an uppercase, lowercase and numeric character
			// uppercase at position 0 or 1
			result[ran.nextInt(2)] = caractetes[ran.nextInt(26) + 36];
			result[ran.nextInt(2) + 2] = caractetes[ran.nextInt(26) + 10];
			result[ran.nextInt(numMaxCarac - 4) + 4] = caractetes[ran.nextInt(10)];
			return new String(result);
		} catch (Exception e) {
			logger.info("Não foi possível gerar uma senha aleatória.");
			return "5Rv&y";
		}
	}

	public static String getValidRandomPassword() {
		String pass = getRandomPassword(6);
		for (int b = 0; b < 1000; b++) { // try 1000x another password
			if (isValidPassword(pass)) {
				return pass;
			}
		}
		return pass;
	}
	
	public static boolean isValidPassword(String pass) {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(pass);
		return matcher.matches();
	}
}
