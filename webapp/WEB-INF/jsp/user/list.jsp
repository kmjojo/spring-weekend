<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<title>User List Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

	
</head>
<body>


<div class="container">
	<div class="row">
		<div class="col-md-6">
			<button class="btn btn-success">버튼</button>
		</div>
		<div class="col-md-6">
			<button class="btn btn-danger">버튼</button>
		</div>
	</div>
	
	<div class="row">
		<table class="table table-bordered">
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>이메일</td>
			</tr>
			<tr>
				<td>1</td>
				<td>3</td>
				<td>2</td>
			</tr>
		</table>
	</div>
</div>





<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script src="/resources/js/jquery-3.1.1.js"></script>
<script src="/resources/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript">
$(function() {

	function addUser(userId, userEmail, userTel) {
		
		var rowHTML = "<tr>";
		rowHTML += "<td>" + userId + "</td>";
		rowHTML += "<td>" + userEmail + "</td>";
		rowHTML += "<td>" + userTel + "</td>";
		rowHTML += "</tr>";
			
			$(".table tbody").append(rowHTML);

	}
	
	$.ajax({
		
		url: "/user/list"
		
	}).done(function(users) {
		
		for (var i = 0; i < users.length; i++) {
				var user = users[i];
				
				addUser(user.userId, user.userEmail, user.userTel);
		}
		console.log(users);
		
	}).fail(function(jqXHR) {
		var error = JSON.parse(jqXHR.responseText);
		
		alert(error.errorMsg);
	});

});
</script>

</body>
</html>