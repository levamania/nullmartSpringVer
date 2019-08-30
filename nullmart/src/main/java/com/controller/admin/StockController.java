package com.controller.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.StockDTO;
import com.model.service.AdminService;

@Controller
public class StockController {
	@Autowired
	AdminService service;

	/*
	 * 임시 이동 주소 inputStock: 재고 등록 페이지
	 */
	@RequestMapping(value = "/gostock")
	public String inputStock() {
		return "/Content/admin/inputStock";
	}

	/*
	 * pname, pcode ajax로 검색 조회 버튼으로 검색 검색 결과에 따라 특정 기능 수행
	 */
	@RequestMapping(value = "/admin/checkpcode" ,method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String searchProduct(String pname,String pcode)  {
		if(pcode.equals("")) {
			pcode = service.searchPcodeByPname(pname);
			if(pcode==null) {
				return "pnamenone";
			}else {
				return pcode;
			}
			
		}else if(pname.equals("")) {
			
			pname = service.searchPnameByPcode(pcode);
			System.out.println(pname);
			if(pname==null) {
				return "pcodenone";
			}else {
				return pname;
			}
		}else {
			
			String searchPcode = service.searchPcodeByPname(pname);
			String searchPname = service.searchPnameByPcode(pcode);
			if(searchPcode==null && searchPname==null) {
				return "bothnosearch";
			}else if(searchPcode==null) {
				return "pnamenosearch";
			}else if(searchPname==null) {
				return "pcodenosearch";
			}else {
				if(!pcode.equals(searchPcode)||!pname.equals(searchPname)) {
					return "eachnotequal";
				}
				else {
					return "both";
				}
			}
			
		}
	}

	/*
	 * 재고 등록 처리
	 * 임시 페이지로 이동 
	 */
	@RequestMapping(value = "/admin/inputStock", method = RequestMethod.POST)
	public String addStock(@RequestParam Map<String,String> map) {
		String pName = map.get("pname");
		String pCode = map.get("pcode");
		String pSize = map.get("psize");
		String pColor = map.get("pcolor");
		String pAmount = map.get("pamount");
		String pPrice = map.get("pprice");
		String deliverFee_YN = map.get("deliverfee_yn");
		String sCode = pColor+pSize+pName;
		StockDTO stock = new StockDTO(sCode, pCode, pSize, pColor, Integer.parseInt(pPrice), Integer.parseInt(pAmount), deliverFee_YN);
		service.insertStock(stock);
		return "redirect:/gostock";
	}
	
	/*
	 * 상품 검색 페이지 초기화 
	 * 
	 * 
	 * */
	@RequestMapping(value = "/admin/initSearchPage",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject initSearchPage() {
		System.out.println("1212");
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		return new JSONObject();
	}
}
