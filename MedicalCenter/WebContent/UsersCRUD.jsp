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
    	var passwordHash = $(this).closest('tr').find('input.passwordHash').val();
    	var temporaryPasswordHash = $(this).closest('tr').find('input.temporaryPasswordHash').val();
    	var mail = $(this).closest('tr').find('input.mail').val();
    	var roleId = $(this).closest('tr').find('input.roleId').val();
        $.ajax({
            type: 'POST',
            url: 'adminTables?dao=Users',
            data: {id: id, passwordHash: passwordHash, action: $(this).attr('class'), 
            	temporaryPasswordHash: temporaryPasswordHash,mail:mail,roleId:roleId},
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
			<td><input type="text" value="Id"  class="head"/></td>
			<td><input type="text" value="PasswordHash" class="head"/></td>
			<td><input type="text" value="TempPasswordHash" class="head"/></td>
			<td><input type="text" value="Mail" class="head" /></td>
			<td><input type="text" value="RoleId" class="head"/></td>
		</tr>
		<c:forEach var="arg" items="${args}">
			<tr>
				<td><input type="text"   value="${arg.id}"                    class="id" /></td>
				<td><input type="text"   value="${arg.passwordHash}"          class="passwordHash" /></td>
				<td><input type="text"   value="${arg.temporaryPasswordHash}" class="temporaryPasswordHash" /></td>
				<td><input type="text"   value="${arg.mail}" 				  class="mail" /></td>
				<td><input type="text"   value="${arg.roleId}" 				  class="roleId"/></td>
				<td><input type="button" value="Update"						  class="update"/></td>
				<td><input type="button" value="Delete" 					  class="delete"/></td>

			</tr>
		</c:forEach>
		<tr>
			<td><input type="text" value="" class="id" /></td>
			<td><input type="text" value="" class="passwordHash" /></td>
			<td><input type="text" value="" class="temporaryPasswordHash" /></td>
			<td><input type="text" value="" class="mail" /></td>
			<td><input type="text" value="" class="roleId" /></td>
			<td><input type="button" value="Add" class="create" /></td>

		</tr>
	</table>
</body>
</html>