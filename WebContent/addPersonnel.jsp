<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Add Personnel</title>
</head>
<body>


	<div class="container">


		<c:if test="${personnel  != null}">
			<h1>Update Personnel</h1>
			<c:set var="sub" value="Update"></c:set>

		</c:if>

		<c:if test="${personnel  == null}">

			<h1>Add Personnel</h1>
			<c:set var="sub" value="Add"></c:set>
		</c:if>
		<hr />

		<c:if test="${violations != null}">
			<c:forEach items="${violations}" var="violation">
				<p>${violation.getMessage()}.</p>
			</c:forEach>
		</c:if>

		<div class="row">
			<div class="col-md-4">

				<form action="?action=insert" method="POST">

					<c:if test="${personnel != null}">
						<input type="hidden" name="id" value="${personnel .id}" />
					</c:if>
					<div class="form-group">
						<input type="text" class="form-control" value="${personnel.nameOfPersonnel}" name="name"
							placeholder="Name" />
					</div>



					<div class="form-group">
					 ${personnel.appointment}
						<select class="form-control" name="appointment">
							<option value="Partners">Partners</option>
							<option value="Director">Director</option>
							<option value="Employee">Employee</option>
						</select>
					</div>


					<div class="form-group">
						<input type="text" class="form-control"  value="${personnel.dateOfAppointment}"
							name="appointmentDate" placeholder="Appointment Date"  />
					</div>

					<div class="form-group">
						<input type="text" class="form-control"
							name="resignationDate" value="${personnel.dateOfResignation}"  placeholder="Resignation Date" />
					</div>


					<div class="form-group">
					  ${personnel.status}
						<select class="form-control" name="status">
							<option value="Employed">Employed</option>
							<option value="Resigned">Resigned</option>
						</select>
					</div>



					<div class="form-group">
						<input type="text" class="form-control" value="${personnel.mobileNo}" name="mobile"
							placeholder="mobile" />
					</div>


					<div class="form-group">
						<select class="form-control" name="company">
							<c:forEach items="${list}" var="company">
								<option value="${company.id}">${company.companyName}</option>
							</c:forEach>
						</select>
					</div>





					<button type="submit" class="btn btn-primary">
						<c:out value="${sub}" />
					</button>
				</form>

			</div>

		</div>

	</div>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>