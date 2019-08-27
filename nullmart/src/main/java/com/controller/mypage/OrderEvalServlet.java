package com.controller.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dto.EvalDTO;
import com.model.service.MyPageService;
import com.util.SessionCheckInterface;

import sun.util.logging.resources.logging;


@WebServlet("/OrderEvalServlet")
public class OrderEvalServlet extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = LoggerFactory.getLogger(OrderEvalServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "OrderEvalListServelt";
		HttpSession session = request.getSession();
		
		sessionCheck(session, request, response, url, SERVLET, ()->{
			String orderscore = request.getParameter("orderscore");
			String ordersatis = request.getParameter("ordersatis");
			String fastdelivery = request.getParameter("fastdelivery");
			String evalcontent = request.getParameter("evalcontent");
			String ono = request.getParameter("ono");
			String evalno = ono+"EVAL";
			logger.trace(evalno);
			System.out.println(orderscore);
			System.out.println(ordersatis);
			System.out.println(fastdelivery);
			System.out.println(evalcontent);
			System.out.println(ono);
			System.out.println(evalno);
			EvalDTO eval = new EvalDTO(evalno, Integer.parseInt(orderscore), Integer.parseInt(fastdelivery), Integer.parseInt(ordersatis), evalcontent, ono);
			MyPageService service = new MyPageService();
			int num = service.addEval(eval);
		});
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
