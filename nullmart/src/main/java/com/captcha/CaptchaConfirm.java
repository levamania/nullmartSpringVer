package com.captcha;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;

@WebServlet("/CaptchaConfirm")
public class CaptchaConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
	   String answer = request.getParameter("answer"); //사용자가 입력한 문자열
	   System.out.println(captcha+answer);
	   if ( answer != null && !"".equals(answer) ) {
	
		   PrintWriter out = response.getWriter();
		   
	       if (captcha.isCorrect(answer)) { //사용자가 입력한 문자열과 CaptCha 클래스가 생성한 문자열
	           request.getSession().removeAttribute(Captcha.NAME);	           
	           out.print("0");
	       } else {
	           out.print("1");
	       }
	   } 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
