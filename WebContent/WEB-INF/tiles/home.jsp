<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<table class="offerstable">
	<thead>
		<tr>
			<td>Send Message</td>
			<td>Name</td>
			<td>Offer</td>
		</tr>
	</thead>
	<c:forEach var="offer" items="${offers}">
		<tr>
			<td><a href="<c:url value='/message?uid=${offer.username}' />">SendTo</a></td>
			<td><c:out value="${offer.user.name}"></c:out></td>
			<td><c:out value="${offer.text}"></c:out></td>
		</tr>
	</c:forEach>
</table>

<c:choose>
	<c:when test="${hasOffer}">
		<p>
			<a href="${pageContext.request.contextPath}/createoffer">Edit Offer</a>
		</p>
	</c:when>
	<c:otherwise>
		<p>
			<a href="${pageContext.request.contextPath}/createoffer">Add New Offer.</a>
		</p>
	</c:otherwise>
</c:choose>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href="<c:url value='/admin'/> ">Admin.</a>
	</p>
</sec:authorize>

