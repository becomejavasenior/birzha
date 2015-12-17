
<%@ page language="java" contentType="text/html; charset=windows-1251"
    pageEncoding="UTF-8"%>
    <%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Windows-1251">
		<title>Страница регистрации</title>
	</head>
	<body>
		<h3>Введите свои данные для регистрации:</h3><br>
		<form action="registration" method="post">
			Nickname* <input type="text" name="nickname"><br>
			E-mail* <input type = "text" name="email"><br>
			Пароль* <input type="password" name="password"><br>
			Повтор пароля* <input type = "password" name="password2"><br>
			<input type="checkbox" name="rulesOk" checked> С правилами ведения деятельности на этом ресурсе ознакомлен <br>
			<input type="submit" value="Подтвердить">
		</form>
	</body>
</html>



