package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("OrderEvalListDTO")
public class OrderEvalListDTO {
	private String order_date;
	private String scode;
	private String ono;
	private String order_name;
	private String evalno;
	public OrderEvalListDTO() {
	}

	public OrderEvalListDTO(String order_date, String scode, String ono, String order_name, String evalno) {
		this.order_date = order_date;
		this.scode = scode;
		this.ono = ono;
		this.order_name = order_name;
		this.evalno = evalno;
	}

	public String getOrder_date() {
		return order_date.substring(0, 10);
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	
	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getOno() {
		return ono;
	}
	public void setOno(String ono) {
		this.ono = ono;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getEvalno() {
		return (evalno==null)?"없음":evalno;
	}
	public void setEvalno(String evalno) {
		this.evalno = evalno;
	}
	@Override
	public String toString() {
		return "OrderEvalListDTO [order_date=" + order_date + ", ono=" + ono + ", ordername=" + order_name + ", evalno="
				+ evalno + "]";
	}
	
	
	
}
