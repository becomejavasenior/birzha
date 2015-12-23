<%@ page language="java" contentType="text/html; charset=Windows-1251"
    pageEncoding="UTF-8"%>
    <%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="Windows-1251">
<title>Вход пользователей</title>
</head>
<body>
	<h1>Добро пожаловать!</h1><br><br>
	Введите свои данные:<br><br>
		<form action="login" method="post">
			Логин(email): <input type = "text" name="email"><br>
			Пароль: <input type="password" name="password"><br>
					<input type="submit" value="Login"><br>
			<a href="forgetpassword.jsp">Забыли пароль?</a>
		</form>
</body>
</html>