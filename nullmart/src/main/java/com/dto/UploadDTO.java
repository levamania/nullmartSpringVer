package com.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class UploadDTO {

	private String stylebotSelect;
	private String stylemidSelect;
	private CommonsMultipartFile theFile;
	
	public UploadDTO() {
		
	}
	public UploadDTO(String stylebotSelect, String stylemidSelect, CommonsMultipartFile theFile) {
		super();
		this.stylebotSelect = stylebotSelect;
		this.stylemidSelect = stylemidSelect;
		this.theFile = theFile;
	}

	public String getStylebotSelect() {
		return stylebotSelect;
	}

	public void setStylebotSelect(String stylebotSelect) {
		this.stylebotSelect = stylebotSelect;
	}

	public String getStylemidSelect() {
		return stylemidSelect;
	}

	public void setStylemidSelect(String stylemidSelect) {
		this.stylemidSelect = stylemidSelect;
	}

	public CommonsMultipartFile getTheFile() {
		return theFile;
	}


	public void setTheFile(CommonsMultipartFile theFile) {
		this.theFile = theFile;
	}

	@Override
	public String toString() {
		return "UploadDTO [stylebotSelect=" + stylebotSelect + ", stylemidSelect=" + stylemidSelect + ", theFile="
				+ theFile + "]";
	}



}
