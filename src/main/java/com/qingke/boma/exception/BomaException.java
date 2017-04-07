package com.qingke.boma.exception;

/**
 * 
 * @time ����7:33:44
 * @Description �Զ����쳣
 */
public class BomaException extends Exception{
	/**
	 * @Description: serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public BomaException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
