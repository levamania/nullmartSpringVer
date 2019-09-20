<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="ATOM" items="${WHOLE_LIST}">

<section class="body featured" >
	<header style="background-image: url('/null/Content/img/shoes/${ATOM.STYLEMID}/header.png')">
		OWNER'S PICK - ${ATOM.TITLE}
	</header>
	<section>
		<div style="background-image: url('/null/Content/img/shoes/${ATOM.STYLEMID}/representative.png')">
			
		</div>
		<c:forEach var="ITEM" items="${ATOM.featured_list}" varStatus="STAT"  >		
			<c:if test="${STAT.count<6}">
				<div style="background-image: url('/null/Content/img/shoes/${ITEM.STYLEMID}/frame.png');"
						data-pcode="${ITEM.PCODE}">
					<img class="lich" src = "/null/Content/img/common/loaded.gif"
					data-src="/null/Content/img/shoes/${ITEM.STYLEMID}/${ITEM.STYLEBOT}/${ITEM.PIMAGE}">
					<span>${ITEM.PNAME}</span><br>
					<span style="color:black">${ITEM.MIN_PRICE}</span>
				</div>			
			</c:if>
		</c:forEach>
		
	</section>
</section>

</c:forEach>