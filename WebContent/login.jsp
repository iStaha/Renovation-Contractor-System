<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Registration Form</title>
</head>
<body>


	<div class="container">

		<h1>Login Form</h1>
		<hr />

		<div class="row">
		 <c:if test="${fail != null}">
			
				<p>${fail}.</p>
			
		</c:if>
			<div class="col-md-4">
				<form action="login" method="POST">

					<div class="form-group">
						<input type="text" class="form-control" name="username"
							placeholder="Enter Name" />
					</div>

					<div class="form-group">
						<input type="password" class="form-control" name="password" />
					</div>

					

					<button type="submit" class="btn btn-primary">Login</button>
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