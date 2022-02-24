<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<MemberVO> alist = (ArrayList<MemberVO>)request.getAttribute("alist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>member의 목록</h2>
	<table border="1">
		<thead>
			<tr>
				<th>회원 번호</th>
				<th>이름</th>
				<th>아이디</th>
			</tr>
		</thead>
		<tbody>
		<%	for(int i=0; i<alist.size(); i++){
				MemberVO vo = alist.get(i);
		%>		
			<tr>
				<td><%=vo.getMidx() %></td>
				<td><%=vo.getName() %></td>
				<td><%=vo.getId() %></td>
			</tr>
		<%	}
		%>
		</tbody>
	</table>
</body>
</html>