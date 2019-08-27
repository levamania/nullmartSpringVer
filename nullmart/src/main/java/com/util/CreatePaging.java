package com.util;

import java.util.ArrayList;
import java.util.List;

public class CreatePaging {
	private int startCur=1;
	private int endCur=0;
	private int cur;
	private int rows;
	private int cols;
	private List<Integer> nums;
	private int maxPage;
	private int curColumn;
	
	public CreatePaging(int maxColumn) {
		this.rows=10;
		this.cols=10;
		setMaxPage(maxColumn);
	}
	public CreatePaging(int rows, int cols, int maxColumn) {
		this.rows=rows;
		this.cols=cols;
		setMaxPage(maxColumn);
	}
	
	public void setCur(int cur, int startCur, int endCur) {
		this.cur=cur;
		this.startCur=startCur;
		this.endCur=endCur;
		this.setNums();
		this.curColumn=(cur-1)*rows;
	}
	
	
	//cur위치가 양옆인 경우 범위 변경
	public void setNums() {
		if(cur==1) {
			this.endCur=cols;
			creatRangeNum();
		}else {
			if(cur==startCur) {
				this.endCur=startCur;
				this.startCur-=(cols-1);
			}else if(cur==endCur) {
				this.startCur=endCur;
				this.endCur+=(cols-1);
			}
			//num 범위 생성 
			creatRangeNum();
			System.out.println(startCur);
			System.out.println(this.nums);
		}
	}
	
	private void creatRangeNum(){
		nums = new ArrayList<Integer>();
		for(int i=startCur;i<=endCur&&i<=maxPage;i++) {
			nums.add(i);
		}
	}
	
	private void setMaxPage(int maxColumn) {
		this.maxPage=(maxColumn-1)/rows+1;
	}
	
	//startCur 반환
	public int getStartCur() {
		return startCur;
	}
	
	//endCur반환
	public int getEndCur() {
		return endCur;
	}
	
	//cur위치 반환
	public int getCur() {
		return cur;
	}
	
	//범위 반환
	public List<Integer> getNums() {
		return nums;
	}
	//rowBound 시작 index
	public int getCurColumn() {
		return curColumn;
	}
	
	
	//검색 범위
	public int getRows() {
		return rows;
	}
	@Override
	public String toString() {
		return "CreatePaging [startCur=" + startCur + ", endCur=" + endCur + ", cur=" + cur + ", rows=" + rows
				+ ", cols=" + cols + ", nums=" + nums + ", maxPage=" + maxPage + ", curColumn=" + curColumn + "]";
	}
	
	
}
