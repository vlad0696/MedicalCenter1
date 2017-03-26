<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="//code.jquery.com/jquery-3.2.0.min.js"></script>
<script>
$(document).ready(function() {
    $(document).on('click', '.create, .delete, .update', function() {
    	var id = $(this).closest('tr').find('input.id').val();
    	var cost = $(this).closest('tr').find('input.cost').val();
    	var date = $(this).closest('tr').find('input.date').val();
             $.ajax({
            type: 'POST',
            url: 'adminTables?dao=Payments',
            data: {id: id, cost: cost, action: $(this).attr('class'), 
            	date: date},
            success: function(data) {
                if ($(this).attr('class') != 'update') {
                    location.reload();
                    
                }
            },
            async: true
        });
    });
});
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/WebContent/styles/style.css"/>
<style>
    <%@include file="/styles/style.css"%>
</style>
</head>
<body>
	<table>
		<tr>
			<td><input type="text" value="Id"  class="id"/></td>
			<td><input type="text" value="Cost"   class="cost"/></td>
			<td><input type="text" value="Date" class="date"/></td>
		</tr>
		<c:forEach var="arg" items="${args}">
			<tr>
				<td><input type="text"   value="${arg.id}"             class="id" /></td>
				<td><input type="text"   value="${arg.cost}" 	       class="cost" /></td>
				<td><input type="text"   value="${arg.date}"       	   class="date" /></td>
				<td><input type="button" value="Update"				   class="update"/></td>
				<td><input type="button" value="Delete" 			   class="delete"/></td>

			</tr>
		</c:forEach>
		<tr>
			<td><input type="text" value="" class="id" /></td>
			<td><input type="text" value="" class="cost" /></td>
			<td><input type="text" value="" class="date" /></td>
			<td><input type="button" value="Add" class="create" /></td>

		</tr>
	</table>
</body>
</html>