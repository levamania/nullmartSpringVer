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
	private String order_name;
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

	public String getOno() {
		return ono;
	}

	public void setOno(String ono) {
		this.ono = ono;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public int getPamount() {
		return pamount;
	}

	public void setPamount(int pamount) {
		this.pamount = pamount;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCaller_name() {
		return caller_name;
	}

	public void setCaller_name(String caller_name) {
		this.caller_name = caller_name;
	}

	public String getCaller_phone() {
		return caller_phone;
	}

	public void setCaller_phone(String caller_phone) {
		this.caller_phone = caller_phone;
	}

	public String getCaller_email1() {
		return caller_email1;
	}

	public void setCaller_email1(String caller_email1) {
		this.caller_email1 = caller_email1;
	}

	public String getCaller_email2() {
		return caller_email2;
	}

	public void setCaller_email2(String caller_email2) {
		this.caller_email2 = caller_email2;
	}

	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getOrder_mesg() {
		return order_mesg;
	}

	public void setOrder_mesg(String order_mesg) {
		this.order_mesg = order_mesg;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getOrder_date() {
		return order_date.substring(0, 10);
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "OrderDTO [ono=" + ono + ", scode=" + scode + ", pamount=" + pamount + ", pprice=" + pprice + ", userid="
				+ userid + ", caller_name=" + caller_name + ", caller_phone=" + caller_phone + ", caller_email1="
				+ caller_email1 + ", caller_email2=" + caller_email2 + ", order_name=" + order_name + ", phone=" + phone
				+ ", telephone=" + telephone + ", post=" + post + ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3="
				+ addr3 + ", order_mesg=" + order_mesg + ", paymethod=" + paymethod + ", order_date=" + order_date
				+ "]";
	}

	
}
