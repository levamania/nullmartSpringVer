package com.util;

import java.util.ArrayList;
import java.util.List;

public class CreatePaging2 {
	/*
	 * cursors: 페이지를 나타내는 인덱스 번호 
	 * rows: 한페이지에 표시하는 결과값 
	 * cols: 페이지 인텍스 갱신 단위 
	 * totalPage: 전체 페이지 검사 
	 * cur: 현재 페이지 번호  
	 * searchRow: rowbound 시작 위치 
	 * */
	private int initCursor;
	private int endCourser;;
	private List<Integer> curors;
	private int rows;
	private int cols;
	private int totalPage;
	private int cur;
	private int searchRow;
	
	//기본값이 있는 생성자
	public CreatePaging2(int cur) {
		this.cur=cur;
		this.rows=10;
		this.cols=10;
		
	}
	
	//rows와 cols 초기화 생성자
	public CreatePaging2(int cur, int rows ,int cols) {
		this.cur=cur;
		this.rows=rows;
		this.cols=cols;
	}
	
	private void createCursors() {
		int result = cur/cols;
		this.initCursor = result*cols+1;
		this.endCourser = (result+1)*cols;
		this.curors = new ArrayList<Integer>();
		for(int i=initCursor;i<=endCourser&&i<=totalPage;i++) {
			curors.add(i);
		}
		this.searchRow=(this.cur-1)*rows;
	}

	public int getInitCursor() {
		return initCursor;
	}

	public void setInitCursor(int initCursor) {
		this.initCursor = initCursor;
	}

	public int getEndCourser() {
		return endCourser;
	}

	public void setEndCourser(int endCourser) {
		this.endCourser = endCourser;
	}

	public List<Integer> getCurors() {
		return curors;
	}

	public void setCurors(List<Integer> curors) {
		this.curors = curors;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalRows) {
		this.totalPage=(totalRows/rows)+(((totalRows%rows)>0)?1:0);
		this.createCursors();
	}

	public int getCur() {
		return cur;
	}

	public void setCur(int cur) {
		this.cur = cur;
	}

	public int getSearchRow() {
		return searchRow;
	}

	public void setSearchRow(int searchRow) {
		this.searchRow = searchRow;
	}

	@Override
	public String toString() {
		return "CreatePaging [initCursor=" + initCursor + ", endCourser=" + endCourser + ", curors=" + curors
				+ ", rows=" + rows + ", cols=" + cols + ", totalPage=" + totalPage + ", cur=" + cur + ", searchRow="
				+ searchRow + "]";
	}
	
	
}
