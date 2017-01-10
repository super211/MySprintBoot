package com.my.model;

import org.springframework.stereotype.Component;

@Component
public class MyResp {

	private String statusCd;
	private String statusDesc;

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public MyResp() {

	}

}
