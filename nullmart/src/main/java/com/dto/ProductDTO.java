package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("product")
public class ProductDTO {

	private String pCode;
	private String styleTop;
	private String styleMid;
	private String styleBot;
	private String pName;
	private String pImage;
	private String pRegitDate;
	private int pPrice;
	private int popularity;

	public ProductDTO() {
	}

	public ProductDTO(String pCode, String styleTop, String styleMid, String styleBot, String pName, String pImage,
			String pRegitDate, int pPrice, int popularity) {
		super();
		this.pCode = pCode;
		this.styleTop = styleTop;
		this.styleMid = styleMid;
		this.styleBot = styleBot;
		this.pName = pName;
		this.pImage = pImage;
		this.pRegitDate = pRegitDate;
		this.pPrice = pPrice;
		this.popularity = popularity;
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

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
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
		return "ProductDTO [pCode=" + pCode + ", styleTop=" + styleTop + ", styleMid=" + styleMid + ", styleBot="
				+ styleBot + ", pName=" + pName + ", pImage=" + pImage + ", pRegitDate=" + pRegitDate + "]";
	}

}
