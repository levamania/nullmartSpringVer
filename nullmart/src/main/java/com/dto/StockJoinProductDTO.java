package com.dto;

import com.util.CreatePaging;

public class StockJoinProductDTO {
	public String sCode;
	public String pCode;
	public String pSize;
	public String pColor;
	public int pPrice;
	public int pAmount;
	public String deliverFee_YN;
	private String styleTop;
	private String styleMid;
	private String styleBot;
	private String pName;
	private String pImage;
	private String pRegitDate;
	

	
	
	public StockJoinProductDTO() {
	}

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getpSize() {
		return pSize;
	}

	public void setpSize(String pSize) {
		this.pSize = pSize;
	}

	public String getpColor() {
		return pColor;
	}

	public void setpColor(String pColor) {
		this.pColor = pColor;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getpAmount() {
		return pAmount;
	}

	public void setpAmount(int pAmount) {
		this.pAmount = pAmount;
	}

	public String getDeliverFee_YN() {
		return deliverFee_YN;
	}

	public void setDeliverFee_YN(String deliverFee_YN) {
		this.deliverFee_YN = deliverFee_YN;
	}

	public String getStyleTop() {
		return styleTop;
	}

	public void setStyleTop(String styleTop) {
		this.styleTop = styleTop;
	}

	public String getStyleMid() {
		return styleMid;
	}

	public void setStyleMid(String styleMid) {
		this.styleMid = styleMid;
	}

	public String getStyleBot() {
		return styleBot;
	}

	public void setStyleBot(String styleBot) {
		this.styleBot = styleBot;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpImage() {
		return pImage;
	}

	public void setpImage(String pImage) {
		this.pImage = pImage;
	}

	public String getpRegitDate() {
		return pRegitDate.substring(0, 10);
	}

	public void setpRegitDate(String pRegitDate) {
		this.pRegitDate = pRegitDate;
	}

	@Override
	public String toString() {
		return "StockJoinProductDTO [sCode=" + sCode + ", pCode=" + pCode + ", pSize=" + pSize + ", pColor=" + pColor
				+ ", pPrice=" + pPrice + ", pAmount=" + pAmount + ", deliverFee_YN=" + deliverFee_YN + ", styleTop="
				+ styleTop + ", styleMid=" + styleMid + ", styleBot=" + styleBot + ", pName=" + pName + ", pImage="
				+ pImage + ", pRegitDate=" + pRegitDate + "]";
	}
	
	
	
}
