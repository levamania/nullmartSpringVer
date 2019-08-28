package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("OrderDTO")
public class OrderDTO {
	private String ono;
	private String scode;
	private int pamount;
	private int pprice;
	private String userid;
	private String caller_name;
	private String caller_phone;
	private String caller_email1;
	private String caller_email2;
	private String ordername;
	private String phone;
	private String telephone;
	private String post;
	private String addr1;
	private String addr2;
	private String addr3;
	private String order_mesg;
	private String paymethod;
	private String order_date;
	
	public OrderDTO() {
	
	}

	public OrderDTO(String ono, String scode, int pamount, int pprice, String userid, String caller_name,
			String caller_phone, String caller_email1, String caller_email2, String ordername, String phone,
			String telephone, String post, String addr1, String addr2, String addr3, String order_mesg,
			String paymethod, String order_date) {
		this.ono = ono;
		this.scode = scode;
		this.pamount = pamount;
		this.pprice = pprice;
		this.userid = userid;
		this.caller_name = caller_name;
		this.caller_phone = caller_phone;
		this.caller_email1 = caller_email1;
		this.caller_email2 = caller_email2;
		this.ordername = ordername;
		this.phone = phone;
		this.telephone = telephone;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.addr3 = addr3;
		this.order_mesg = order_mesg;
		this.paymethod = paymethod;
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "OrderDTO [ono=" + ono + ", scode=" + scode + ", pamount=" + pamount + ", pprice=" + pprice + ", userid="
				+ userid + ", caller_name=" + caller_name + ", caller_phone=" + caller_phone + ", caller_email1="
				+ caller_email1 + ", caller_email2=" + caller_email2 + ", ordername=" + ordername + ", phone=" + phone
				+ ", telephone=" + telephone + ", post=" + post + ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3="
				+ addr3 + ", order_mesg=" + order_mesg + ", paymethod=" + paymethod + ", order_date=" + order_date
				+ "]";
	}
	
	
	
}
