package com.ah.commonlib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

import com.ah.commonlib.exceptions.CommonLibException;

public class StringConverter {

	/**
	 * スネークケースのStringをキャメルケースに変換する。
	 * @param snakeCaseStr
	 * @return
	 */
	public static String convertSnakeToCamel(String snakeCaseStr) {
		String[] parts = snakeCaseStr.split("_");
		StringBuilder camelCaseString = new StringBuilder(parts[0]);
		for (int i = 1; i < parts.length; i++) {
			camelCaseString.append(StringUtils.capitalize(parts[i]));
		}
		return camelCaseString.toString();
	}
	
	/**
	 * -(ハイフン)や/(スラッシュ)区切りに対応して、StringからLocalDateに変換する。
	 * @param str
	 * @return
	 */
	public static LocalDate convertToLocalDate(String str) {
		String hyphen = "-";
		String slash = "/";
		String splitStr;
		if(str.split(hyphen).length == 3) {
			splitStr = hyphen;
		}
		else if(str.split(slash).length == 3) {
			splitStr = slash;
		}
		else {
			//区切りが不正な時例外
			throw new CommonLibException(CommonLibException.DateConvertError);
		}
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy" + splitStr + "MM" + splitStr + "dd");
		return LocalDate.parse(str, format);
	}
}
