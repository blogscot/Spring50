<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="${pageContext.request.contextPath}/static/css/create.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery.js"></script>

<script type="text/javascript">
	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password != confirmpass) {
			$("#matchmsg").text(
					"<fmt:message key='UnmatchedPasswords.user.password' />");
		} else {
			$("#matchmsg").text("");
		}
	}

	$(document).ready(function() {
		$("#confirmpass").blur(checkPasswordsMatch);
	});
</script>

<sf:form method="post"
	action="${pageContext.request.contextPath}/createaccount"
	commandName="user">

	<h2>Create New Account</h2>

	<table id="newAccounttable">
		<tr>
			<td class="label">Username:</td>
			<td><input class="control" name="username" type="text" /><br />
				<div class="error">
					<sf:errors path="username"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Email:</td>
			<td><sf:input class="control" path="email" name="email"
					type="text" /><br />
				<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Password:</td>
			<td><sf:input id="password" class="control" path="password"
					name="password" type="password" /><br />
				<div class="error">
					<sf:errors path="password"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Confirm Password:</td>
			<td><input id="confirmpass" class="control" name="confirmpass"
				type="password" />
				<div id="matchmsg"></div></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Create Account" type="submit" /></td>
		</tr>
	</table>

</sf:form>
