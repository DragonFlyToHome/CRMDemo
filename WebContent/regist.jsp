<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
</head>
<body>
<div align="center">
	<form action="regist" method="post">
		<div>
			账号<input type="text" id="user_name" name="user_name">
		</div>
		<div>
			密码<input type="password" id="password" name="password">
		</div>
		<div>
			<input type="submit" value="登录">
			<input type="reset" value="重置">
		</div>
	</form>
	<a href="login.jsp">已有账号?前去登录!</a>
</div>
</body>
</html>