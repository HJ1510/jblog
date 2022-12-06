<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<div id="header">
	<h1>Spring 이야기${title }</h1>
	<ul>
		<c:choose>
			<c:when test="${empty authUser }">
				<li><a href="${pageContext.request.contextPath }">메인으로</a></li>
				<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath }">메인으로</a></li>
				<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				<c:if test="${authUser.id == id }">
				<li><a href="${pageContext.request.contextPath }/${id}/admin">블로그 관리</a></li>
				</c:if>				
			</c:otherwise>
		</c:choose>
	</ul>
</div>