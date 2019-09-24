package com.dto;

import org.apache.ibatis.type.Alias;

public class StyleBotAndPamountDTO {
	private String stylebot;
	private int  hap;
	
	public StyleBotAndPamountDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public StyleBotAndPamountDTO(String stylebot, int hap) {
		this.stylebot = stylebot;
		this.hap = hap;
	}



	public String getStylebot() {
		return stylebot;
	}

	public void setStylebot(String stylebot) {
		this.stylebot = stylebot;
	}

	public int getHap() {
		return hap;
	}

	public void setHap(int hap) {
		this.hap = hap;
	}

	
}
