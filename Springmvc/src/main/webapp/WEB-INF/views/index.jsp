<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact Manager Home</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
	integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="path/to/font-awesome/css/font-awesome.min.css">

</head>

<body>
	<div class="container align-items-center" style="height: 100vh"
		align="center">
		<h1>Customer List</h1>
		<a class="btn mb-3 btn-light"
			href="http://localhost:8080/Springmvc/newCustomer">Add New
			Customer</a>

		<div class="container">
			<form action="searchCustomer" method="get">
				<div class="row gap-1 my-3">
					<select class="col-md-2 mr-1 col-sm-12" name="searchField"
						aria-label="Default select example">
						<option selected>select</option>
						<option value="firstName"
							<c:if test="${searchField eq 'firstName'}">selected="selected"</c:if>>First
							Name</option>
						<option value="lastName"
							<c:if test="${searchField eq 'lastName'}">selected="selected"</c:if>>Last
							Name</option>
						<option value="gender"
							<c:if test="${searchField eq 'gender'}">selected="selected"</c:if>>Gender</option>
					</select> <input type="text" value="${searchValue}" name="searchValue"
						class="col-md-3 col-sm-12" />
					<button class="col-md-1 col-sm-12 btn btn-light" type="submit">Search</button>

					<a class="col-md-1 col-sm-12 btn btn-light"
						href="http://localhost:8080/Springmvc/">Reset</a>
				</div>
			</form>
		</div>
		<c:choose>
			<c:when test="${not empty param['colName'] && not empty param['order'] }">
			</c:when>
			<c:otherwise>
			
			</c:otherwise>
		</c:choose>

		<table class="table col-md-8 col-sm-12" border="1">
			<th>No</th>
			<th>First Name<a href="?p=1&colName=first_name&order=asc">&#8593;</a><a
				href="?p=1&colName=first_name&order=desc">&darr;</a></th>
			<th>Last Name<a href="?p=1&colName=last_name&order=asc">&#8593;</a><a
				href="?p=1&colName=last_name&order=desc">&darr;</a></th>
			<th>Gender<a href="?p=1&colName=gender&order=asc">&#8593;</a><a
				href="?p=1&colName=gender&order=desc">&darr;</a></th>
			<th>Email<a href="?p=1&colName=email&order=asc">&#8593;</a><a
				href="?p=1&colName=email&order=desc">&darr;</a></th>
			<th>Mobile Number</th>
			<th>Date of Birth</th>
			<th>Age</th>
			<th>Website URL</th>

			<th>State</th>
			<th>City</th>
			<th>Pincode</th>
			<th>Action</th>

			<c:forEach var="cust" items="${listCustomer}" varStatus="status">
				<tr>
					<td>${pageid+status.index}</td>
					<td>${cust.firstName}</td>
					<td>${cust.lastName}</td>
					<td>${cust.gender}</td>
					<td>${cust.email}</td>
					<td>${cust.mobileNumber}</td>
					<td>${cust.dob}</td>
					<fmt:parseDate value="${cust.dob}" var="formatedDate1" type="date"
						pattern="dd-MM-yyyy" />
					<fmt:formatDate value="${formatedDate1}" var="birthYear"
						type="date" pattern="yyyy" />
					<jsp:useBean id="now" class="java.util.Date" />
					<fmt:formatDate value="${now}" var="currentYear" type="date"
						pattern="yyyy" />
					<td><fmt:parseNumber type="number" integerOnly="true"
							value="${currentYear-birthYear}" /></td>
					<%-- <td > <fmt:parseNumber type="number" integerOnly = "true" value="${(now.time - formatedDate1.time)/(1000 * 60 * 60 * 24 * 365)}" /> </td>--%>



					<td>${cust.websiteURL}</td>
					<td>${cust.state.name}</td>
					<td>${cust.city.name}</td>
					<td>${cust.pincode}</td>

					<td><a href="editCustomer?id=${cust.id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteCustomer?id=${cust.id}"
						onclick="return confirm('Are you sure to delete?')">Delete</a></td>

				</tr>
			</c:forEach>
		</table>
		<br>


		<c:choose>
			<c:when
				test="${not empty param['searchField'] && not empty param['searchValue']}">

				<c:choose>
					<c:when
						test="${not empty param['colName'] && not empty param['order'] }">

						<c:set var="url"
							value="searchCustomer?searchField=${searchField}&searchValue=${searchValue}&colName=${colName}&order=${order}&" />
					</c:when>
					<c:otherwise>
						<c:set var="url"
							value="searchCustomer?searchField=${searchField}&searchValue=${searchValue}&" />

					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>

				<c:choose>
					<c:when
						test="${not empty param['colName'] && not empty param['order'] }">

						<c:set var="url" value="?colName=${colName}&order=${order}&" />
					</c:when>
					<c:otherwise>
						<c:set var="url" value="?" />

					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>

		<c:forEach var="i" begin="1" end="${totalPages}">
			<a class="btn btn-light" href="${url}p=${i}">${i}</a>

		</c:forEach>

	</div>
</body>
</html>