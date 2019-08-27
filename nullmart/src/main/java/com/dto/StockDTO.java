package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("stock")
public class StockDTO {
	public String sCode;
	public String pCode;
	public String pSize;
	public String pColor;
	public int pPrice;
	public int pAmount;
	public String deliverFee_YN;
	
	public StockDTO() {
		super();
	}

	public StockDTO(String sCode, String pCode, String pSize, String pColor, int pPrice, int pAmount,
			String deliverFee_YN) {
		super();
		this.sCode = sCode;
		this.pCode = pCode;
		this.pSize = pSize;
		this.pColor = pColor;
		this.pPrice = pPrice;
		this.pAmount = pAmount;
		this.deliverFee_YN = deliverFee_YN;
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
	
}
