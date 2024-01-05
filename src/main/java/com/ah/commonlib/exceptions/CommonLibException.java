package com.ah.commonlib.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CommonLibException extends RuntimeException{
	
	public static final String DateConvertError = "日付型のフォーマットが不正です。";

	public CommonLibException(String msg) {
		super(msg);
	}
}
