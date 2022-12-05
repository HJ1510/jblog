<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<h1 class="logo">JBlog</h1>
<ul class="menu">
	<c:choose>
		<c:when test="${empty authUser }">
			<li><a href="${pageContext.request.contextPath }">메인으로</a></li>
			<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
			<li><a href="${pageContext.request.contextPath }/blog/index">내블로그</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath }">메인으로</a></li>
			<li><a href="${pageContext.request.contextPath }/user/logout"> ${authUser.name } 로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath }/i${id } a${authUser.id } uV${userVo.id } b${blogVo.id } fd${findUser.id }" >내블로그</a></li>
		</c:otherwise>
	</c:choose>
</ul>