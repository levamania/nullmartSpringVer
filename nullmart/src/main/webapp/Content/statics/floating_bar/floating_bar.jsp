<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.net.CookieStore"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ObjectMapper mapper = new ObjectMapper();

	Cookie[] cookies = request.getCookies();
	List<HashMap<String, String>> LIFO_COOKIES = new ArrayList<HashMap<String, String>>();
	List<Cookie> temp = new ArrayList<Cookie>();

	if (cookies != null) {
		for (Cookie c : cookies) {	
			if (c.getName().contains("Product")) {
				temp.add(c);
			}
		}
	}
	//정렬 
	if (temp.size() != 0) {
		Collections.sort(temp, new Comparator<Cookie>() {
			@Override
			public int compare(Cookie o1, Cookie o2) {
					String alpha_time = o1.getName().substring(7);
					String beta_time = o2.getName().substring(7);
				return alpha_time.compareTo(beta_time)*-1;
			}
		});

		for (Cookie c : temp) {
			LIFO_COOKIES.add(mapper.readValue(URLDecoder.decode(c.getValue(), "utf-8"), HashMap.class));
		}

		//저장
		pageContext.setAttribute("LIFO_COOKIES", LIFO_COOKIES);
	}
%>
<script src="/null/Content/statics/floating_bar/js/floating_bar.js"></script>
<link rel="stylesheet" type="text/css"
	href="/null/Content/statics/floating_bar/css/floating_bar.css">
<div id="floating_bar">
	<div id="head">
		<div>최근 본 상품</div>
	</div>
	<div id="body">
	<c:if test="${! empty LIFO_COOKIES  }">
		<c:forEach var="ITEM" items="${LIFO_COOKIES}">
			<div>
				<img
					src="/null/Content/img/shoes/${ITEM.STYLEMID}/${ITEM.STYLEBOT}/${ITEM.PIMAGE}.jpg">
				<div class="noun">${ITEM.PNAME}</div>
				<span style="display: none">${ITEM.PCODE}</span>
			</div>
		</c:forEach>
	</c:if>
	</div>
	<div id="tail">
		<div>top</div>
	</div>
</div>