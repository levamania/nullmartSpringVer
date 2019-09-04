package com.sms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.service.MemberService;

import net.nurigo.java_sdk.Coolsms;


@Controller
public class SMSController {
	
	@Autowired
	MemberService service;
	//문자를 보낼때 맵핑되는 메소드
    @RequestMapping(value = "/sendSms")
      public String sendSms(HttpServletRequest request ,@RequestParam Map<String,String> map, String phone1, String phone2, String phone3) throws Exception {
    	String tempPassword = ""; 
		for(int i=0; i<8; i++) {
			int rndVal = (int)(Math.random() * 62);
			if(rndVal < 10) { tempPassword += rndVal; 
			}else if(rndVal > 35) {
			tempPassword += (char)(rndVal + 61); 
			} else { tempPassword += (char)(rndVal + 55); } 
			}
    	
        String api_key = "NCSVB3SQHP8GM4YN"; //위에서 받은 api key를 추가
        String api_secret = "SLQUDZPGA2N9QNPSMIOOYMHYGRJXGFKG";  //위에서 받은 api secret를 추가

        com.sms.Coolsms coolsms = new com.sms.Coolsms(api_key, api_secret);
        
	//이 부분은 홈페이지에서 받은 자바파일을 추가한다음 그 클래스를 import해야 쓸 수 있는 클래스이다.
 
String p1 = map.get("phone1");
String p2 = map.get("phone2");
String p3 = map.get("phone3");
System.out.println(map+"map");
System.out.println(p1+p2+p3);
map.put("tempPassword", tempPassword);

       int n = service.UpdatePwPhone(map);
       
       HashMap<String, String> set = new HashMap<String, String>();
        
       set.put("to", p1+p2+p3); // 수신번호

       set.put("from", "01097961116"); // 발신번호, jsp에서 전송한 발신번호를 받아 map에 저장한다.
       set.put("text", "nullmart입니다. 귀하의 임시비밀번호는 ["+tempPassword+"]입니다."); // 문자내용, jsp에서 전송한 문자내용을 받아 map에 저장한다.
       set.put("type", "sms"); // 문자 타입

        System.out.println(set);

        JSONObject result = coolsms.send(set); // 보내기&전송결과받기

        if ((boolean)result.get("status") == true) {

          // 메시지 보내기 성공 및 전송결과 출력
          System.out.println("성공");
          System.out.println(result.get("group_id")); // 그룹아이디
          System.out.println(result.get("result_code")); // 결과코드
          System.out.println(result.get("result_message")); // 결과 메시지
          System.out.println(result.get("success_count")); // 메시지아이디
          System.out.println(result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
        } else {

          // 메시지 보내기 실패
          System.out.println("실패");
          System.out.println(result.get("code")); // REST API 에러코드
          System.out.println(result.get("message")); // 에러메시지
        }

       return "redirect:/Content/account/loginForm.jsp";
      }

}
