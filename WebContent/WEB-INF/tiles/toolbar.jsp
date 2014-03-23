<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${hasOffer}">
		<a href="${pageContext.request.contextPath}/createoffer">Edit
			Offer</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath}/createoffer">Add New
			Offer.</a>
	</c:otherwise>
</c:choose>
&nbsp;
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/> ">Admin.</a>
</sec:authorize>
&nbsp;
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/messages'/> ">Messages(<span
		id="numberMessages">0</span>)</a>
</sec:authorize>

<script type="text/javascript">
	function refreshMessageNumber() {
		$.getJSON("<c:url value="getmessages"/>", updateMessageLink);
	}

	function updateMessageLink(data) {
		$("#numberMessages").text(data.number);
	}

	$(document).ready(function() {
		refreshMessageNumber();
		window.setInterval(refreshMessageNumber, 30000);
	});
</script>