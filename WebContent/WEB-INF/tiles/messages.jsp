<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="outputmessages"></div>

<script type="text/javascript">
	function updateMessages(data) {

		$("#outputmessages").html("");

		for (var i = 0; i < data.messages.length; i++) {
			var message = data.messages[i];

			var messageDiv = document.createElement("div");
			messageDiv.setAttribute("class", "usermessage");

			var subjectSpan = document.createElement("span");
			subjectSpan.setAttribute("class", "messagesubject");
			subjectSpan.appendChild(document.createTextNode(message.subject));

			var contentSpan = document.createElement("span");
			contentSpan.setAttribute("class", "messagecontent");
			contentSpan.appendChild(document.createTextNode(message.content));

			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "messagename");
			nameSpan.appendChild(document.createTextNode(message.name + " (" + message.email + ")"));							

			messageDiv.appendChild(subjectSpan);
			messageDiv.appendChild(contentSpan);
			messageDiv.appendChild(nameSpan);
			
			$("#outputmessages").append(messageDiv);
		}
	}

	function refreshMessages() {
		$.getJSON("<c:url value="getmessages"/>", updateMessages);
	}

	$(document).ready(function() {
		refreshMessages();
		window.setInterval(refreshMessages, 30000);
	});
</script>