<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link href="${pageContext.request.contextPath}/static/css/create.css"
	rel="stylesheet" type="text/css" />
<div class="headertitle" ><a id="title" href="<c:url value='/'/>">Get Rich using Software</a></div>
<br />
<sec:authorize access="!isAuthenticated()">
  <a class="login" href="<c:url value='/login'/> ">Log in.</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
  <a class="login" href="<c:url value='/j_spring_security_logout'/> ">Log out.</a>
</sec:authorize>