package com.dto;

public class OrderEvalListDTO {
	private String order_date;
	private String ono;
	private String ordername;
	private String evalno;
	public OrderEvalListDTO() {
	}
	public OrderEvalListDTO(String order_date, String ono, String ordername, String evalno) {
		this.order_date = order_date;
		this.ono = ono;
		this.ordername = ordername;
		this.evalno = evalno;
	}
	public String getOrder_date() {
		return order_date.substring(0, 10);
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOno() {
		return ono;
	}
	public void setOno(String ono) {
		this.ono = ono;
	}
	public String getOrdername() {
		return ordername;
	}
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public String getEvalno() {
		return (evalno==null)?"없음":evalno;
	}
	public void setEvalno(String evalno) {
		this.evalno = evalno;
	}
	@Override
	public String toString() {
		return "OrderEvalListDTO [order_date=" + order_date + ", ono=" + ono + ", ordername=" + ordername + ", evalno="
				+ evalno + "]";
	}
	
	
	
}
