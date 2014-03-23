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
			subjectSpan.appendChild(document.createTextNode("Subject: "
					+ message.subject));

			var contentSpan = document.createElement("span");
			contentSpan.setAttribute("class", "messagecontent");
			contentSpan.appendChild(document.createTextNode(message.content));

			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "messagename");
			nameSpan.appendChild(document.createTextNode("From: "
					+ message.name + " ("));

			var link = document.createElement("a");
			link.setAttribute("class", "replylink");
			link.setAttribute("href", "#");
			link.setAttribute("onclick", "showReply(" + i + ")");
			link.appendChild(document.createTextNode(message.email));

			nameSpan.appendChild(link);
			nameSpan.appendChild(document.createTextNode(")"));

			var replyForm = document.createElement("form");
			replyForm.setAttribute("class", "messagereplyform");
			replyForm.setAttribute("id", "replyform" + i);

			var textArea = document.createElement("textArea");
			textArea.setAttribute("class", "replyTextArea");
			textArea.setAttribute("id", "replyTextArea" + i);

			replyForm.appendChild(textArea);

			messageDiv.appendChild(subjectSpan);
			messageDiv.appendChild(contentSpan);
			messageDiv.appendChild(nameSpan);
			messageDiv.appendChild(replyForm);

			var replyButton = document.createElement("input");
			replyButton.setAttribute("class", "replybutton");
			replyButton.setAttribute("type", "button");
			replyButton.setAttribute("value", "Reply");
			replyButton.onclick = function(j, name, email) {
				return function() {
					sendReplyMessage(j, name, email);
				}
			}(i, message.name, message.email);

			replyForm.appendChild(textArea);
			replyForm.appendChild(replyButton);

			$("#outputmessages").append(messageDiv);
		}
	}

	var timer;

	function success(data) {

		$("#replyTextArea" + data.target).text('');
		$("#replyform" + data.target).toggle();
		alert("Message sent successfully");
		startTimer();
	}
	
	function sendReplyMessage(i, name, email) {

		var text = $("#replyTextArea" + i).val();

		$.ajax({
			type: 'POST',
			url: '<c:url value="/sendmessage"/>',
			data: JSON.stringify({"target": i, "text": text, "name": name, "email": email}),
			success: success,
			error: function(data) {alert("JSON error!");},
			contentType: "application/json",
			dataType: 'json'
		});
	}

	function showReply(i) {
		stopTimer();
		$("#replyform" + i).toggle();
	}

	function startTimer() {
		timer = window.setInterval(refreshMessages, 30000);
	}

	function stopTimer() {
		window.clearInterval(timer);
	}

	function refreshMessages() {
		$.getJSON("<c:url value="getmessages"/>", updateMessages);
	}

	$(document).ready(function() {
		refreshMessages();
		startTimer();
	});
</script>