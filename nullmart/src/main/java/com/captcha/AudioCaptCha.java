package com.captcha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.servlet.CaptchaServletUtil;

@WebServlet("/AudioCaptCha")
public class AudioCaptCha extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        
        //Captcha.NAME = 'simpleCaptcha'
        Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
        String getAnswer = captcha.getAnswer(); 

        if ( getAnswer == null || getAnswer.equals("") ) getAnswer = captcha.getAnswer();
 
        AudioCaptcha audiocaptcha = new AudioCaptcha.Builder()
                           //.addAnswer(new DefaultTextProducer(6, getAnswer.toCharArray())) 또는 다음과 같이...
                           .addAnswer(new SetTextProducer(getAnswer))
                           .addNoise() //잡음추가
                           .build();

        CaptchaServletUtil.writeAudio(response, audiocaptcha.getChallenge());
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
