package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("Stock_Product")
public class Stock_ProductDTO {
	private String sCode;
	private String pSize;
	private String pColor;
	private int pAmount;
	private String deliverFee_YN;
	private String pCode;
	private String styleTop;
	private String styleMid;
	private String styleBot;
	private String pName;
	private String pImage;
	private String pRegitDate;
	private int popularity;
	
	
	public Stock_ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stock_ProductDTO(String sCode, String pSize, String pColor, int pAmount, String deliverFee_YN, String pCode,
			String styleTop, String styleMid, String styleBot, String pName, String pImage, String pRegitDate,
			int popularity) {
		super();
		this.sCode = sCode;
		this.pSize = pSize;
		this.pColor = pColor;
		this.pAmount = pAmount;
		this.deliverFee_YN = deliverFee_YN;
		this.pCode = pCode;
		this.styleTop = styleTop;
		this.styleMid = styleMid;
		this.styleBot = styleBot;
		this.pName = pName;
		this.pImage = pImage;
		this.pRegitDate = pRegitDate;
		this.popularity = popularity;
	}
	public String getsCode() {
		return sCode;
	}
	public void setsCode(String sCode) {
		this.sCode = sCode;
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
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
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
		return pRegitDate;
	}
	public void setpRegitDate(String pRegitDate) {
		this.pRegitDate = pRegitDate;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	@Override
	public String toString() {
		return "Stock_ProductDTO [sCode=" + sCode + ", pSize=" + pSize + ", pColor=" + pColor + ", pAmount=" + pAmount
				+ ", deliverFee_YN=" + deliverFee_YN + ", pCode=" + pCode + ", styleTop=" + styleTop + ", styleMid="
				+ styleMid + ", styleBot=" + styleBot + ", pName=" + pName + ", pImage=" + pImage + ", pRegitDate="
				+ pRegitDate + ", popularity=" + popularity + "]";
	}
	
	
}
