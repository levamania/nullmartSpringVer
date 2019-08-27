package com.controller.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.EvalDTO;
import com.model.service.MyPageService;
import com.util.SessionCheckInterface;

/**
 * Servlet implementation class ModifyOrderEvalServlet
 */
@WebServlet("/ModifyOrderEvalServlet")
public class ModifyOrderEvalServlet extends HttpServlet implements SessionCheckInterface{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "modifyordereval.jsp";
		HttpSession session = request.getSession();
		
		sessionCheck(session, request, response, url, DISPATCHER, ()->{
			String ono = request.getParameter("ono");
			MyPageService service = new MyPageService();
			EvalDTO eval = service.searchEvalByOno(ono);
			String ordername = service.searchOrdernameByOno(ono);
			request.setAttribute("ordername", ordername);
			request.setAttribute("eval", eval);
		});
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "OrderEvalListServelt";
		HttpSession session = request.getSession();
		
		
		sessionCheck(session, request, response, url, SERVLET, ()->{
			MyPageService service = new MyPageService();
			String orderscore = request.getParameter("orderscore");
			String ordersatis = request.getParameter("ordersatis");
			String fastdelivery = request.getParameter("fastdelivery");
			String evalcontent = request.getParameter("evalcontent");
			String ono = request.getParameter("ono");
			String evalno = request.getParameter("evalno");
			EvalDTO eval = new EvalDTO(evalno, Integer.parseInt(orderscore), Integer.parseInt(fastdelivery), Integer.parseInt(ordersatis), evalcontent, ono);
			int num = service.updateEval(eval);
		});
		
	}

}
