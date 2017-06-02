package com.comm.response;

/**
 * 
 * @author liang 公共response
 * @param <T>
 */
public class BaseResponse<T> {
	private boolean isSuccess;

	private String failMessage;

	private T result;

	public BaseResponse(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public BaseResponse(boolean isSuccess, String failMessage) {
		this(isSuccess);
		this.failMessage = failMessage;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getFailMessage() {
		return failMessage;
	}

	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
