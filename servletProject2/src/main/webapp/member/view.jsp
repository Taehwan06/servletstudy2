<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="vo.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	MemberVO vo = (MemberVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function delFn(){
		document.delFrm.submit();
	}
</script>
</head>
<body>
	<h2>상세페이지</h2>
	<table border="1">
		<tr>
			<td>회원번호</td>
			<td><%=vo.getMidx() %></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=vo.getName() %></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=vo.getId() %></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><%=vo.getEmail() %></td>
		</tr>
		<tr>
			<td>연락처</td>
			<td><%=vo.getPhone() %></td>
		</tr>
	</table>
	<br>
	<input type="button" value="수정" onclick="location.href='modify.do?midx=<%=vo.getMidx() %>'">
	<input type="button" value="목록" onclick="location.href='list.do'">
	<input type="button" value="삭제" onclick="delFn()">
	<form name="delFrm" action="delete.do" method="post">
		<input type="hidden" name="midx" value="<%=vo.getMidx() %>">
	</form>
</body>
</html>