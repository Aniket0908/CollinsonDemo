<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Customer</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<link href="<c:url value="/css/form.css" />" rel="stylesheet" />
<script src="<c:url value="/js/cities.js" />" type="text/javascript"></script>
<script src="<c:url value="/js/mobilevalidation.js" />" type="text/javascript"></script>
</head>
<body>

	<div align="center" class="container" style="height: 100vh">
		<h1>New/Edit Contact</h1>
		<form:form id="myform" class="form-inline" style=""
			action="saveCustomer" method="post" onsubmit="return validateForm()" modelAttribute="customer">

			<form:hidden path="id" />
			<div class="row">
				<div class="col-md-6">

					<div class="container my-3">
						<div class="row ">
							<div class="col-md-6">
								<label class="control-label" for="firstName">First Name:</label>
							</div>
							<div class="col-md-6">
								<form:input class="form-control" path="firstName" required="required" />
							</div>
						</div>
					</div>


					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="col" for="lastName">Last Name:</label>
							</div>
							<div class="col-md-6">
								<form:input class="form-control" path="lastName" required="required" />
							</div>
						</div>
					</div>
					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label  class="">Gender:</label>
							</div>
							
							<div class="col-md-6 justify-content-between">
								
								<form:radiobutton path="gender" value="M" label="Male"/>
								<form:radiobutton path="gender" value="F" label="Female"/>
								
							</div>
						</div>
					</div>



					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="" for="email">Email:</label>
							</div>
							<div class="col-md-6">
								<form:input class="form-control" path="email" required="required" />
							</div>
						</div>
					</div>

					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="" for="mobileNumber">Mobile Number:</label>
							</div>
							<div class="col-md-6">
								<form:input id="mobileNumber" class="form-control"
									path="mobileNumber" value="" />
								<span id="mobile-error" style="color: red;"></span>
							</div>
						</div>

					</div>


					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="" for="dob">Date of Birth:</label>
							</div>
							<div class="col-md-6">
								<fmt:parseDate value="${customer.dob}" var="formatedDate"
									pattern="dd-MM-yyyy" />
								<fmt:formatDate value="${formatedDate}" var="dateOfBirth"
									pattern="yyyy-MM-dd" />

								<form:input id="dob" class="form-control" value="${dateOfBirth}"
									type="date" path="dob" />
									
								<span id="dob-error" style="color: red;"></span>
							</div>
						</div>
					</div>

				</div>


				<div class="col-md-6">
					<div class="container my-3">
						<div class="row">
							<div class="col-md-6">
								<label class="" for="websiteURL">Website Url:</label>
							</div>
							<div class="col-md-6">
								<form:input class="form-control" path="websiteURL" />
							</div>
						</div>
					</div>

					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="">Address Line 1:</label>
							</div>
							<div class="col-md-6">
								<form:input class="form-control" type="text" path="addressLine1" />
							</div>
						</div>
					</div>

					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="">Address Line 2:</label>
							</div>
							<div class="col-md-6">
								<form:input class="form-control" type="text" path="addressLine2" />
							</div>
						</div>
					</div>

					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="">Address Line 3:</label>
							</div>
							<div class="col-md-6">
								<form:input class="form-control" type="text" path="addressLine3" />
							</div>
						</div>
					</div>

					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="">State :</label>
							</div>
							<div class="col-md-6">

								<form:select class="form-control" path="state.id" id="state"
									aria-label="Default select example" onchange="getCities()"
									>
									<form:option value="">Select your state</form:option>
									<form:options items="${statelist}" itemLabel="name"
										itemValue="id" />
								</form:select>
							</div>

						</div>
					</div>
					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="">City :</label>
							</div>
							<div class="col-md-6">

								<form:select id="city" class="form-control" path="city.id">
									<form:option value="">Select your City</form:option>
									<form:options items="${citylist}" itemLabel="name"
										itemValue="id" />
								</form:select>

							</div>
						</div>
					</div>

					<div class="container mb-3">
						<div class="row">
							<div class="col-md-6">
								<label class="">Pincode :</label>
							</div>
							<div class="col-md-6">
								<form:input class="form-control" type="text" path="pincode" />
							</div>
						</div>

					</div>
				</div>
			</div>
			<div>
				<input class="btn btn-light" type="submit" value="save"> 
				<a href="http://localhost:8080/Springmvc/" class="btn btn-light">Cancel</a>
			</div>




		</form:form>
	</div>

</body>
</html>