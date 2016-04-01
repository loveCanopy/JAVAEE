package com.rice.util;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * 用来存储增删改查的结果POJO
 * @author Administrator
 *
 */
@DataTransferObject
public class Result {
	private boolean success;
	private String msg;
	
	public Result() {
		super();
	}
	
	public Result(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString(){
		return String.format("[success:%b,msg:%s]",success,msg);
	}
}
