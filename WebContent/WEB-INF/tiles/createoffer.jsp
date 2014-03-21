<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">

$(document).ready(function() { 
    $("#deletecontrol").click(function(event) {
        var doDelete = confirm("Are you sure?");

        if (!doDelete) {
        	event.preventDefault();
		}
		});
	});
</script>

<h2>Create Offer</h2>
<sf:form method="post"
	action="${pageContext.request.contextPath}/docreate"
	commandName="offer">

	<sf:input type="hidden" name="id" path="id" />
	<table class="formtable">
		<tr>
			<td class="label">Your offer:</td>
			<td><sf:textarea class="control" path="text" name="text"
					rows="10" cols="10"></sf:textarea><br /> <sf:errors path="text"
					cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Save" type="submit" />&nbsp;<c:if test="${offer.id !=0}"><input
				id="deletecontrol" name="delete" value="Delete" type="submit" /></c:if></td>

		</tr>
	</table>

</sf:form>
