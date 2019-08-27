package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("EvalDTO")
public class EvalDTO {
	private String evalno;
	private int orderscore;
	private int fastdelivery;
	private int ordersatis;
	private String evalcontent;
	private String ono;
	
	public EvalDTO() {
		
	}

	public EvalDTO(String evalno, int orderscore, int fastdelivery, int ordersatis, String evalcontent, String ono) {
		super();
		this.evalno = evalno;
		this.orderscore = orderscore;
		this.fastdelivery = fastdelivery;
		this.ordersatis = ordersatis;
		this.evalcontent = evalcontent;
		this.ono = ono;
	}

	public String getEvalno() {
		return evalno;
	}

	public void setEvalno(String evalno) {
		this.evalno = evalno;
	}

	public int getOrderscore() {
		return orderscore;
	}

	public void setOrderscore(int orderscore) {
		this.orderscore = orderscore;
	}

	public int getFastdelivery() {
		return fastdelivery;
	}

	public void setFastdelivery(int fastdelivery) {
		this.fastdelivery = fastdelivery;
	}

	public int getOrdersatis() {
		return ordersatis;
	}

	public void setOrdersatis(int ordersatis) {
		this.ordersatis = ordersatis;
	}

	public String getEvalcontent() {
		return evalcontent;
	}

	public void setEvalcontent(String evalcontent) {
		this.evalcontent = evalcontent;
	}

	public String getOno() {
		return ono;
	}

	public void setOno(String ono) {
		this.ono = ono;
	}

	@Override
	public String toString() {
		return "EvalDTO [evalno=" + evalno + ", orderscore=" + orderscore + ", fastdelivery=" + fastdelivery
				+ ", ordersatis=" + ordersatis + ", evalcontent=" + evalcontent + ", ono=" + ono + "]";
	}
	
}
