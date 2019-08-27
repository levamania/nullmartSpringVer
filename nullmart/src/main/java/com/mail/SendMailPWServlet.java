package com.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dto.MemberDTO;
import com.model.service.MemberService;

/**
 * Servlet implementation class SendMailServlet
 */
@WebServlet("/SendMailPWServlet")
public class SendMailPWServlet extends HttpServlet {
	Logger logger = LoggerFactory.getLogger(SendMailPWServlet.class);
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tempPassword = ""; 
		for(int i=0; i<8; i++) {
			int rndVal = (int)(Math.random() * 62);
			if(rndVal < 10) { tempPassword += rndVal; 
			}else if(rndVal > 35) {
			tempPassword += (char)(rndVal + 61); 
			} else { tempPassword += (char)(rndVal + 55); } 
			}
			
		MemberService service = new MemberService();
		
		
		
		String mailTo= (String)request.getAttribute("mailTo");
		String userid2 = (String)request.getAttribute("userid");
		String userid = request.getParameter("userid");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("tempPassword", tempPassword);
	
		int num=service.updatePw(map);
		System.out.println(userid+tempPassword+"service보낸 후");
		String email1 =(String)request.getParameter("email1");
		String email2 =(String)request.getParameter("email2");


	  logger.debug(mailTo+"\t"+userid2);
		String host = "smtp.naver.com";
	    String subject = "Null Mart 임시비밀번호"; //제목
	    String from = "nullmart@naver.com"; //보내는 메일
	   String fromName = "Admin";
	    String to = email1+"@"+email2; //받는 메일
	    String content = "임시 비밀번호는:&nbsp;[" +tempPassword+ "]&nbsp;입니다."; //내용

	   try{
	     //프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
	     Properties props = new Properties();
	     //네이버 SMTP 사용시
	    props.put("mail.smtp.starttls.enable","true");
	     props.put("mail.transport.protocol","smtp");
	     props.put("mail.smtp.host", host);
	     
	     props.put("mail.smtp.port","465");  // 보내는 메일 포트 설정
	    props.put("mail.smtp.user", from);
	     props.put("mail.smtp.auth","true");
	     props.put("mail.smtp.debug", "true");
	     props.put("mail.smtp.socketFactory.port", "465");
	     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	     props.put("mail.smtp.socketFactory.fallback", "false");


	     Authenticator auth = new SendMail();
	     Session mailSession = Session.getDefaultInstance(props,auth);
	   
	     Message msg = new MimeMessage(mailSession);
	     msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B"))); //보내는 사람 설정
	    InternetAddress[] address = {new InternetAddress(to)};
	    
	     msg.setRecipients(Message.RecipientType.TO, address); //받는 사람설정
	   
	     msg.setSubject(subject); //제목설정
	    msg.setSentDate(new java.util.Date()); //보내는 날짜 설정
	    msg.setContent(content,"text/html; charset=UTF-8"); //내용 설정(MIME 지정-HTML 형식)
	    
	     Transport.send(msg); //메일 보내기

	     }catch(MessagingException ex){
	      System.out.println("mail send error : "+ex.getMessage());
	       ex.printStackTrace();
	     }catch(Exception e){
	      System.out.println("error : "+e.getMessage());
	       e.printStackTrace();
	     }
	   
	     response.sendRedirect("/null/LoginUIServlet");
	   
	}//end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
