package com.centurylink.git.crawler.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GenericErrorInfo {

	public GenericErrorInfo() {
	}
	
	private int errorCode;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	private String errorMessage;
	
		
}
