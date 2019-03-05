
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Customers</title>
<link type="css/text" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2> CRM- Customer Relationship Manager</h2>
		</div>
		<div id="container">
			<div id="content">
				  <!--  add a search box -->
	            
				<input type="button" value="Add Student" onclick="window.location.href='showFormForAdd'; 
				return false;" class="add-button"/>
				<form:form action="search" method="POST">
	                Search customer: <input type="text" name="theSearchName" />
	                
	                <input type="submit" value="Search" class="add-button" />
	                
	            </form:form>
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email ID</th>
					</tr>	
					<c:set var="length" value="0" scope="page"/>
					<c:forEach var="tempCustomer" items="${customers}" varStatus="status">
						
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${tempCustomer.id}"/>
						</c:url>
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${tempCustomer.id}"/>
						</c:url>
						
						<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td><a href="${updateLink }">Update</a>
							|
							<a href="${deleteLink }" onclick = "if(!(confirm('Are you sure you want to delete'))) return false">Delete</a></td>
						<td></td>
						</tr>
					
						
					<c:set var="length" value="${status.count}"/>	
					</c:forEach>
					
					
					<c:if test="${length == 0}">
							<h3>No Result Found</h3>
						</c:if>
						
						
				</table>
				
			</div>
					<p>
			<a href="${pageContext.request.contextPath}/customer/list">Home</a>
			</p>
		</div>
	</div>	

</body>
</html>