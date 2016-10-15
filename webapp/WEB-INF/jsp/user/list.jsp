<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<title>User List Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css" type="text/css"> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<style type="text/css">
.signup-form {
	width: 50%;'
}

</style>
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
				<td>이메일</td>
				<td>전화번호</td>
			</tr>
			<tr>
				<td>1</td>
				<td>3</td>
				<td>2</td>
			</tr>
		</table>
	</div>

		<div class="signup-form">
			<form>
				<div class="form-group">
					<label for="userEmail">Email address</label> 
					<input type="email" class="form-control" id="userEmail" placeholder="Email">
				</div>
				<div class="form-group">
					<label for="userPassword">Password</label>
					<input type="password" class="form-control" id="userPassword" placeholder="Password">
				</div>
				<div class="form-group">
					<label for="userPhoto">File input</label>
					<input type="file" id="userPhoto">
					<p class="help-block">사진을 첨부하시오</p>
				</div>
				
				<button id="btnSignup" type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>


	</div>





<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<!-- <script src="/resources/js/jquery-3.1.1.js"></script>
<script src="/resources/bootstrap/bootstrap.min.js"></script> -->
<script src="/resources/js/common.js"></script>

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
	
	/*
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
	});*/

	callAjax({
		url: "/user/list",
		success: function(users) {
			for (var i = 0; i < users.length; i++) {
				var user = users[i];
				
				addUser(user.userId, user.userEmail, user.userTel);
			}
		}
	});

	$("#btnSignup").on("click", function() {
		
		var userEmail = $("#userEmail").val();
		var userPassword = $("#userPassword").val();
		
		if (userEmail.trim() == "") {	// trim() 앞뒤 공백 없앰
			alert("이메일 입력");
			$("#userEmail").focus();
			return;
			
		} else if (userPassword.trim() == "") {
			alert("비밀번호 입력");
			$("#userPassword").focus();
			return;
		}
		
		var userData = {
				userEmail: userEmail,
				userPassword: userPassword
		};
		
		/* callAjax({
			url: "/user/add",
			method: "POST",
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			data: JSON.stringify(userData),
			success: function(user) {
				console.log(user);
			}
		
		}); */
		
		var formData = new FormData();
		formData.append("userEmail", userEmail);
		formData.append("userPassword", userPassword);
		
		// jQuery 함수이므로 [0]을 붙이면 element가 옴
		var userPhoto = $("#userPhoto")[0].files[0];
		
		if (userPhoto) {
			
			formData.append("userPhoto", userPhoto);
			
			callAjax({
				url: "/user/add",
				method: "POST",
				contentType: false,
				processData: false,
				data: formData,
				success: function(user) {
					console.log(user);
				}
			
			});
		} else {
			
			callAjax({
				url: "/user/add",
				method: "PUT",
				contentType: "application/json;charset=utf-8",
				dataType: "json",
				data: JSON.stringify(userData),
				success: function(user) {
					alert(user.userEmail + " 가입 완료");
				}
			
			});
		}
		
	});
	
});
</script>

</body>
</html>