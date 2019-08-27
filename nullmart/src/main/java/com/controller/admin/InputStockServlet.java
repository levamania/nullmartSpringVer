package com.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.StockDTO;
import com.model.service.AdminService;


@WebServlet("/InputStockServlet")
public class InputStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
	
		String pcode = request.getParameter("pcode");
		String pname = request.getParameter("pname");
		System.out.println(pcode);
		PrintWriter out = response.getWriter();
		AdminService service = new AdminService();
		if(pcode.equals("")) {
			pcode = service.searchPcodeByPname(pname);
			if(pcode==null) {
				out.print("pnamenone");
			}else {
				out.print(pcode);
			}
			
		}else if(pname.equals("")) {
			
			pname = service.searchPnameByPcode(pcode);
			if(pname==null) {
				out.print("pcodenone");
			}else {
				out.print(pname);
			}
		}else {
			
			String searchPcode = service.searchPcodeByPname(pname);
			String searchPname = service.searchPnameByPcode(pcode);
			if(searchPcode==null && searchPname==null) {
				out.print("bothnosearch");
			}else if(searchPcode==null) {
				out.print("pnamenosearch");
			}else if(searchPname==null) {
				out.print("pcodenosearch");
			}else {
				if(!pcode.equals(searchPcode)||!pname.equals(searchPname)) {
					out.print("eachnotequal");
				}
				else {
					out.print("both");
				}
			}
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pName = request.getParameter("pname");
		String pCode = request.getParameter("pcode");
		String pSize = request.getParameter("psize");
		String pColor = request.getParameter("pcolor");
		String pAmount = request.getParameter("pamount");
		String pPrice = request.getParameter("pprice");
		String deliverFee_YN = request.getParameter("deliverfee_yn");
		String sCode = pColor+pSize+pName;
		AdminService service = new AdminService();
		StockDTO stock = new StockDTO(sCode, pCode, pSize, pColor, Integer.parseInt(pPrice), Integer.parseInt(pAmount), deliverFee_YN);
		int num = service.insertStock(stock);
		
		
	}

}
