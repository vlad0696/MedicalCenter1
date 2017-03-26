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
    	var speciality = $(this).closest('tr').find('input.speciality').val();
    	var departament = $(this).closest('tr').find('input.departament').val();
    	var category = $(this).closest('tr').find('input.category').val();
    	var post = $(this).closest('tr').find('input.post').val();
        $.ajax({
            type: 'POST',
            url: 'adminTables?dao=Doctors',
            data: {id: id, speciality: speciality, action: $(this).attr('class'), 
            	departament: departament,category: category,post: post},
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
			<td><input type="text" value="Speciality"   class="speciality"/></td>
			<td><input type="text" value="Departament" class="departament"/></td>
			<td><input type="text" value="Category" class="category" /></td>
			<td><input type="text" value="Post" class="post"/></td>
		</tr>
		<c:forEach var="arg" items="${args}">
			<tr>
				<td><input type="text"   value="${arg.id}"             class="id" /></td>
				<td><input type="text"   value="${arg.speciality}" 	   class="speciality" /></td>
				<td><input type="text"   value="${arg.category}"       class="category" /></td>
				<td><input type="text"   value="${arg.departament}"    class="departament" /></td>
				<td><input type="text"   value="${arg.post}" 		   class="post"/></td>
				<td><input type="button" value="Update"				   class="update"/></td>
				<td><input type="button" value="Delete" 			   class="delete"/></td>

			</tr>
		</c:forEach>
		<tr>
			<td><input type="text" value="" class="id" /></td>
			<td><input type="text" value="" class="speciality" /></td>
			<td><input type="text" value="" class="category" /></td>
			<td><input type="text" value="" class="departament" /></td>
			<td><input type="text" value="" class="post" /></td>
			<td><input type="button" value="Add" class="create" /></td>

		</tr>
	</table>
</body>
</html>