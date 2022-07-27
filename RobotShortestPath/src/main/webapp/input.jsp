<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="input" method="get">
		Room Row: <input type="text" name="row"><br>
		Room Col: <input type="text" name="col"><br>
	
		<input type="submit">
	</form>
	
	<c:if test="${messages['Fail'] != null}">
		<div class="alert alert-success" role="alert">
			<c:out value="${messages['Fail']}" />
		</div>
	</c:if>
	
	<c:if test="${messages['Success'] != null}">
		<div class="alert alert-success" role="alert">
			Row is <c:out value="${row}" /><br>
			Col is <c:out value="${col}" /><br>
		</div>
	</c:if>
	
</body>
</html>