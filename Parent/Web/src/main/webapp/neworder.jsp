<%@ page language="java" contentType="text/html; charset=Windows-1251"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=Windows-1251">
	<title>Создать новую заявку</title>
</head>

<body>
<form action="neworder" method="post">
	<h2>Создайте заявку: </h2>		 
			<select name="curr1" id="curr1">
			<option disabled>Выберите валюту для продажи</option>
			  <option value="1">usd</option>
			  <option value="2">wmu</option>
			</select><br>
			Сумма <input type="text" name="sum1"><br>
			<select name="curr2" id="curr2">
			<option disabled>Выберите валюту для покупки</option>
			  <option value="1">usd</option>
			  <option value="2">wmu</option>
			</select><br>
			Сумма <input type="text" name="sum2"><br>
			<input type="submit" value="Создать заявку">
		</form>
</body>
</html>

