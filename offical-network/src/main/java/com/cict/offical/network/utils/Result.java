package com.cict.offical.network.utils;

public class Result<T> {
	
	private int code;
	private T data;
	private String msg = "";

	public Result() {
	}

	private Result(int code) {
		this.code = code;
	}

	private Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private Result(int code, T data) {
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static <T> Result<T> returnResult() {
		return new Result<T>(CODE.OK.getValue());
	}

	public static <T> Result<T> returnResult(T data) {
		return new Result<T>(CODE.OK.getValue(), data);
	}	

	public static <T> Result<T> returnErrorResult(String msg) {
		return new Result<T>(CODE.ERROR.getValue(), msg);
	}
	
	public static <T> Result<T> returnResult(int code,String msg) {
		return new Result<T>(code,msg);
	}
}