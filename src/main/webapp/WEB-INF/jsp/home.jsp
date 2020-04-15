<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
</head>
<body>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Amount</th>
			<th>Price</th>
			<th>Calories</th>
			<th>Life Time</th>
			<th>Delete</th>
			<c:forEach var="tempProduct" items="${products}">
				<c:url var="deleteLink" value="/admin/deleteProductFromDB">
					<c:param name="productId" value="${tempProduct.id}"></c:param>
				</c:url>
				<tr>
					<td>${tempProduct.id}</td>
					<td>${tempProduct.name}</td>
					<td>${tempProduct.amount}</td>
					<td>${tempProduct.price}</td>
					<c:catch var="error1">
						<td>${tempProduct.calories}</td>
					</c:catch>
					<c:if test="${error1!=null}">
					</c:if>
					<c:catch var="error2">
						<td>${tempProduct.lifeTime}</td>
					</c:catch>
					<c:if test="${error2!=null}">
					</c:if>
					<td><a href="${deleteLink}">Delete</a> 
				</tr>
			</c:forEach>
		</tr>
	</table>
	<br>
	Add Food
	<br>
	<form:form action="addFoodToDB" modelAttribute="food" method="POST">
		<form:hidden path="id" />
		<br>
		<table>
			<tbody>
				<tr>
					<td><label>Name:</label></td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td><label>Amount:</label></td>
					<td><form:input path="amount" /></td>
				</tr>
				<tr>
					<td><label>Price:</label></td>
					<td><form:input path="price" /></td>
				</tr>
				<tr>
					<td><label>Calories:</label></td>
					<td><form:input path="calories" /></td>
				</tr>
			</tbody>
		</table>
		<br>
		<input type="submit" value="Submit" />
	</form:form>
	<br>
	Add Nonfood
	<br>
	<form:form action="addNonfoodToDB" modelAttribute="nonfood" method="POST">
		<form:hidden path="id" />
		<br>
		<table>
			<tbody>
				<tr>
					<td><label>Name:</label></td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td><label>Amount:</label></td>
					<td><form:input path="amount" /></td>
				</tr>
				<tr>
					<td><label>Price:</label></td>
					<td><form:input path="price" /></td>
				</tr>
				<tr>
					<td><label>Life Time:</label></td>
					<td><form:input path="lifeTime" /></td>
				</tr>
			</tbody>
		</table>
		<br>
		<input type="submit" value="Submit" />
	</form:form>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout"/>
	</form:form>
</body>
</html>