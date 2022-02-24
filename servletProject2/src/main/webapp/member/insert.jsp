<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 등록</h2>
	<form name="writeFrm" action="insert.do" method="post">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>연락처</td>
				<td><input type="text" name="phone"></td>
			</tr>
		</table>
		<input type="submit" value="저장">
		<input type="button" value="취소" onclick="location.href='list.do'">
	</form>
</body>
</html>