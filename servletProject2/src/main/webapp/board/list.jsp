<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="vo.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<BoardVO> alist = (ArrayList<BoardVO>)request.getAttribute("alist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>board의 목록</h2>
	<table border="1">
		<thead>
			<tr>
				<th>글 번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회 수</th>
			</tr>
		</thead>
		<tbody>
		<%	for(int i=0; i<alist.size(); i++){
				BoardVO vo = alist.get(i);
		%>		
			<tr>
				<td><%=vo.getBidx() %></td>
				<td><a href="view.do?bidx=<%=vo.getBidx() %>"><%=vo.getTitle() %></a></td>
				<td><%=vo.getWriter() %></td>
				<td><%=vo.getWdate() %></td>
				<td><%=vo.getHit() %></td>
			</tr>
		<%	}
		%>
		</tbody>
	</table>
	<br>
	<input type="button" value="등록" onclick="location.href='insert.do'">
	<input type="button" value="뒤로" onclick="location.href='index.do'">
</body>
</html>