package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class ManagerDTO {

	private String masteruserid;
	private String masterpasswd;
	private String masterusername;
	private String masterphone1;
	private String masterphone2;
	private String masterphone3;

	private String masteremail1;
	private String masteremail2;
	public ManagerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ManagerDTO(String masteruserid, String masterpasswd, String masterusername, String masterphone1,
			String masterphone2, String masterphone3, String masteremail1, String masteremail2) {
		super();
		this.masteruserid = masteruserid;
		this.masterpasswd = masterpasswd;
		this.masterusername = masterusername;
		this.masterphone1 = masterphone1;
		this.masterphone2 = masterphone2;
		this.masterphone3 = masterphone3;
		this.masteremail1 = masteremail1;
		this.masteremail2 = masteremail2;
	}
	public String getMasteruserid() {
		return masteruserid;
	}
	public void setMasteruserid(String masteruserid) {
		this.masteruserid = masteruserid;
	}
	public String getMasterpasswd() {
		return masterpasswd;
	}
	public void setMasterpasswd(String masterpasswd) {
		this.masterpasswd = masterpasswd;
	}
	public String getMasterusername() {
		return masterusername;
	}
	public void setMasterusername(String masterusername) {
		this.masterusername = masterusername;
	}
	public String getMasterphone1() {
		return masterphone1;
	}
	public void setMasterphone1(String masterphone1) {
		this.masterphone1 = masterphone1;
	}
	public String getMasterphone2() {
		return masterphone2;
	}
	public void setMasterphone2(String masterphone2) {
		this.masterphone2 = masterphone2;
	}
	public String getMasterphone3() {
		return masterphone3;
	}
	public void setMasterphone3(String masterphone3) {
		this.masterphone3 = masterphone3;
	}
	public String getMasteremail1() {
		return masteremail1;
	}
	public void setMasteremail1(String masteremail1) {
		this.masteremail1 = masteremail1;
	}
	public String getMasteremail2() {
		return masteremail2;
	}
	public void setMasteremail2(String masteremail2) {
		this.masteremail2 = masteremail2;
	}
	@Override
	public String toString() {
		return "ManagerDTO [masteruserid=" + masteruserid + ", masterpasswd=" + masterpasswd + ", masterusername="
				+ masterusername + ", masterphone1=" + masterphone1 + ", masterphone2=" + masterphone2
				+ ", masterphone3=" + masterphone3 + ", masteremail1=" + masteremail1 + ", masteremail2=" + masteremail2
				+ "]";
	}
	

}
