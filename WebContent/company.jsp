<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<%-- 	<%@ page contentType="text/html;charset=UTF-8" session="false" %> --%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Directory</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

</head>
<body>

	<div class="container-fluid">

		<h1>Company List</h1>
		<hr />

		<p>${NOTIFICATION}</p>

		<p>
			<button class="btn btn-primary"
				onclick="window.location.href = 'company?action=add'">Add
				Company</button>
			<button class="btn btn-primary"
				onclick="window.location.href = 'address?action=add'">Add
				Address</button>
			<button class="btn btn-primary"
				onclick="window.location.href = 'personnel?action=list'">Add
				Employee</button>
				<button class="btn btn-primary"
				onclick="window.location.href = 'logout'">Log out</button>
		</p>

		<table class="table table-striped table-bordered">

			<tr class="thead-dark">

				<th>Company Name</th>
				<th>Reg No.</th>
				<th>Mode of Buisness</th>
				<th>Buisiness Reg. No</th>
				<th>Buisiness Reg. No Effective</th>
				<th>Buisiness Reg. No Expiry</th>
				<th colspan="3">Personnel</th>
				<th>Actions</th>
			</tr>

			<c:forEach items="${list}" var="company">

				<tr>
					<td>${company.companyName}</td>
					<td>${company.hdbRegistrationNo}</td>
					<td>${company.modeOfBuisiness}</td>
					<td>${company.buisinessRegistrationNo}</td>
					<td>${company.businessRegistrationEffectiveDate}</td>
					<td>${company.businessRegistrationExpiryDate}</td>
					<td colspan="3">
						<table>
							<tr class="thead-dark">

								<th>Name</th>
								<th>Status</th>
								<th>Appointment</th>
								<th>Action</th>

							</tr>
							<c:forEach items="${company.personnel}" var="personnel">
							<tr>
								<td>${personnel.nameOfPersonnel}</td>
								<td>${personnel.status}</td>
								<td>${personnel.appointment}</td>
								<td><a
						href="${pageContext.request.contextPath}/personnel?action=edit&id=${personnel.id}"><i class="fa fa-edit"></i></a>
						| <a
						href="${pageContext.request.contextPath}/personnel?action=DELETE&id=${personnel.id}"><i class="fa fa-trash"></i></a> </td>
							</tr>
							</c:forEach>
						
						</table>
					</td>
					<td><a
						href="${pageContext.request.contextPath}/company?action=EDIT&id=${company.id}"><i class="fa fa-edit"></i></a>
						| <a
						href="${pageContext.request.contextPath}/company?action=DELETE&id=${company.id}"><i class="fa fa-trash"></i></a>
					</td>
				</tr>

			</c:forEach>

		</table>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>