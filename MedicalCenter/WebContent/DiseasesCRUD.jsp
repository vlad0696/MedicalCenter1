<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Disease</title>
<script type="text/javascript" src="//code.jquery.com/jquery-3.2.0.min.js"></script>
<script>
$(document).ready(function() {
    $(document).on('click', '.create, .delete, .update', function() {
        var id = $(this).closest('tr').find('input.id').val();
        var disease = $(this).closest('tr').find('input.disease').val();
        
        $.ajax({
            type: 'POST',
            url: 'adminTables?dao=Diseases',
            data: {id: id, disease: disease, action: $(this).attr('class')},
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
	<!--form action="adminTables?dao=Diseases" method="POST"-->
		<table>
			<tr>
				<th><input type="text" value="Id"  class="id"/></th>
				<th><input type="text" value="Disease"  class="disease"/></th>
			</tr>
			<c:forEach var="arg" items="${args}">
				<tr>
					<td><input type="text"   value="${arg.id}"      class="id" /></td>
					<td><input type="text"   value="${arg.disease}" class="disease" /></td>
					<td><input type="button" value="Update"         class="update"></td>
					<td><input type="button" value="Delete"         class="delete"></td>
					
				</tr>
			</c:forEach>
			<tr>
				<td><input type="text"   value=""    class="id" /></td>
				<td><input type="text"   value=""  	  class="disease" /></td>
				<td><input type="button" value="Add" class="create" /></td>
			</tr>
		</table>
	<!--/form-->
	
</body>
</html>