package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("RegAddr")
public class RegAddrDTO {
	private int delivno;
	private String delivname;
	private String delivperson;
	private String phone1;
	private String phone2;
	private String post;
	private String address1;
	private String address2;
	private String userid;
	
	public RegAddrDTO() {
		// TODO Auto-generated constructor stub
	}

	public RegAddrDTO(int delivno, String delivname, String delivperson, String phone1, String phone2, String post,
			String address1, String address2, String userid) {
		this.delivno = delivno;
		this.delivname = delivname;
		this.delivperson = delivperson;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.post = post;
		this.address1 = address1;
		this.address2 = address2;
		this.userid = userid;
	}

	public int getDelivno() {
		return delivno;
	}

	public void setDelivno(int delivno) {
		this.delivno = delivno;
	}

	public String getDelivname() {
		return delivname;
	}

	public void setDelivname(String delivname) {
		this.delivname = delivname;
	}

	public String getDelivperson() {
		return delivperson;
	}

	public void setDelivperson(String delivperson) {
		this.delivperson = delivperson;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "RegAddrDTO [delivno=" + delivno + ", delivname=" + delivname + ", delivperson=" + delivperson
				+ ", phone1=" + phone1 + ", phone2=" + phone2 + ", post=" + post + ", address1=" + address1
				+ ", address2=" + address2 + ", userid=" + userid + "]";
	}

	
	
}
