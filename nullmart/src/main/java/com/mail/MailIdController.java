package com.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.model.service.MemberService;

@Controller
public class MailIdController {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MemberService service;


	// mailSending 코드
	@RequestMapping(value = "/searchIdMail")
    public String mailSending(@RequestParam Map<String, String> map) {

		System.out.println(map + "map값");
		String mail1 = map.get("email1");
		String mail2 = map.get("email2");
		String username = map.get("username");
		MemberDTO dto = service.SearchID(map);
		String userid = dto.getUserid();
		
		String setfrom = "nullmart@naver.com";
		String tomail = mail1 + "@" + mail2;
		String title = "nullmart 아이디찾기 메일입니다.";
		String content = username + "님의 아이디는" + "\t" + userid + "\t" + "입니다.";

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/loginForm";
	}
}
