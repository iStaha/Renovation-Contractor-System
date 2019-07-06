<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Add Company</title>
</head>
<body>


	<div class="container">





		<c:if test="${company != null}">
			<h1>Update Company</h1>
			   <c:set var = "sub"  value = "Update"></c:set>
		
		</c:if>
		
		<c:if test="${company == null}">
		
			<h1>Add Company</h1>
			   <c:set var = "sub"  value = "Add"></c:set>
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

					<c:if test="${company != null}">
						<input type="hidden" name="id" value="${company.id}" />
					</c:if>
					<div class="form-group">
						<input type="text" class="form-control"
							value="${company.hdbRegistrationNo }" name="hdbRegisNo"
							placeholder="HDB Registration No" />
					</div>

					<div class="form-group">
						<input type="text" class="form-control"
							value="${company.companyName}" name="companyName"
							placeholder="Company Name" />
					</div>

					<div class="form-group">
						${company.modeOfBuisiness} <select class="form-control"
							name="buisinessMide">
							<option value="private">Private</option>
							<option value="partnership">Partnership</option>
						</select>
					</div>

					<div class="form-group">
						<input type="text" class="form-control"
							value="${company.buisinessRegistrationNo}" name="buisRegisNo"
							placeholder="Buis. Registration NO" />
					</div>

					<div class="form-group">
						<input type="text" class="form-control"
							value="${company.businessRegistrationEffectiveDate}"
							name="buisRegisNoEffectiveDate"
							placeholder="Buis. Registration NO Effective Date" />
					</div>


					<div class="form-group">
						<input type="text" class="form-control"
							value="${company.businessRegistrationExpiryDate}"
							name="buisRegisNoExpiryeDate"
							placeholder="Buis. Registration NO Expiry Date" />
					</div>


                

					<button type="submit" class="btn btn-primary"><c:out value = "${sub}"/></button>
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