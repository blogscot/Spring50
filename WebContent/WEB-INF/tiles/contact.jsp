<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Send Message</h2>

<sf:form method="post" commandName="message">

	<input type="hidden" name="_flowExecutionKey"
		value="${flowExecutionKey} /">
	<input type="hidden" name="_eventId" value="send" />

	<table id="messagetable">
		<tr>
			<td class="label">Your name:</td>
			<td><sf:input class="control" name="name" type="text"
					value="${fromName}" path="name" /><br />
				<div class="error">
					<sf:errors path="name"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your email:</td>
			<td><sf:input class="control" name="email" type="text"
					value="${fromEmail}" path="email" /><br />
				<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Subject:</td>
			<td><sf:input class="control" name="subject" type="text"
					path="subject" /><br />
				<div class="error">
					<sf:errors path="subject"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your Message:</td>
			<td><sf:textarea id="messagetextarea" class="control"
					name="content" type="text" path="content" /><br />
				<div class="error">
					<sf:errors path="content"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Submit" type="submit" /></td>
		</tr>
	</table>

</sf:form>
