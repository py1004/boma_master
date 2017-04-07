package com.qingke.boma.util;

import java.util.regex.Pattern;

public class CheckMethod {
	
	public static String validatePassword(String password) {
		// 1.length>5
		Pattern lengthPattern = Pattern.compile("[0-9a-zA-Z]{6,}");
		if (!lengthPattern.matcher(password).find()) {
			return "���볤������Ҫ6λ����!";
		}
		// 2.not all number
		Pattern numPattern = Pattern.compile("[0-9]+");
		if (numPattern.matcher(password).matches()) {
			return "���벻��Ϊȫ������!";
		}
		// 3.not all word
		Pattern wordPattern = Pattern.compile("[a-zA-Z]+");
		if (wordPattern.matcher(password).matches()) {
			return "���벻��Ϊȫ����ĸ";
		}
		return "ok";
	}

}
