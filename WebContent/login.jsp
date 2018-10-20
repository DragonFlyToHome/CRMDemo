<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
</head>
<body>
<div align="center">
	<form action="login" method="post">
	<label>${loginError}</label>
		<div>
			账号<input type="text" id="user_name" name="user_name" placeholder="请输入">
		</div>
		<div>
			密码<input type="password" id="password" name="password">
		</div>
		<div>
			<input type="submit" value="登录">
			<input type="reset" value="重置">
		</div>
	</form>
</div>
</body>
</html>