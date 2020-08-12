<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function checkValue(){
	var uiId = document.querySelector('#ui_id');
	if(uiId.value.trim().length<4)	{
		alert('Check ID');
		uiId.focus();
		return false;
	}
	var uipwd = document.querySelector('#ui_password');
	if(uipwd.value.trim().length<6){
		alert('Check pwd');
		uipwd.focus();
		return false;
	}
	var uiname = document.querySelector('#ui_name');
	if(uiname.value.trim().length<2){
		alert('Check name');
		uiname.focus();
		return false;
	}
	var uiage = document.querySelector('#ui_age');
	if(uiage.value>150 || uiage.value<1){
		alert('Check age');
		uiage.focus();
		return false;
	}
	var uinickname = document.querySelector('#ui_nickname');
	if(uinickname.value.trim().length<4){
		alert('Check nickname');
		uinickname.focus();
		return false;
	}
	var uibirth = document.querySelector('#ui_birth');
	if(!uibirth.value){
		alert('Check birth');
		uibirth.focus();
		return false;
	}
}

</script>
	<form action="/user/join" method="post" onsubmit="return checkValue()">
ID : <input type="text" name="ui_id" id="ui_id"><button>중복확인</button>	<br>
PASSWORD : <input type="password" name="ui_password" id="ui_password"><br>
NAME : <input type= "text" name="ui_name" id="ui_name"><br>
AGE : <input type= "text" name="ui_age" id="ui_age"><br>
BIRTH : <input type= "date" name="ui_birth" id="ui_birth"><br>
PHONE : <input type= "number" name="ui_phone" id="ui_phone"><br>
EMAIL : <input type= "text" name="ui_email" id="ui_email"><br>
NICKNAME: <input type= "text" name="ui_nickname" id="ui_nickname"><br>
<button>SIGN UP</button>
	</form>
</body>
</html>