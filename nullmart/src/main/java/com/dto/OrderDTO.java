package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("OrderDTO")
public class OrderDTO {
	private String ono;
	private String cno;
	private String userid;
	private String ordername;
	private String post;
	private String addr1;
	private String addr2;
	private String addr3;
	private String phone;
	private String paymethod;
	private String order_date;
	
	public OrderDTO() {
	
	}

	public OrderDTO(String ono, String cno, String userid, String ordername, String post, String addr1, String addr2,
			String addr3, String phone, String paymethod, String order_date) {
		super();
		this.ono = ono;
		this.cno = cno;
		this.userid = userid;
		this.ordername = ordername;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.addr3 = addr3;
		this.phone = phone;
		this.paymethod = paymethod;
		this.order_date = order_date;
	}

	public String getOno() {
		return ono;
	}

	public void setOno(String ono) {
		this.ono = ono;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		return "OrderDTO [ono=" + ono + ", cno=" + cno + ", userid=" + userid + ", ordername=" + ordername + ", post="
				+ post + ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3=" + addr3 + ", phone=" + phone
				+ ", paymethod=" + paymethod + ", order_date=" + order_date + "]";
	}
	
	
}
