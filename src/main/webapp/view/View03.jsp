<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<%
	int myoffset = 2;
	int mywidth = twelve - 2 * myoffset;
	int formleft = 3 ;
	int formright = twelve - formleft ; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrap Sample</title>
</head>
<body>
	<div class="container col-sm-offset-<%=myoffset%> col-sm-<%=mywidth%>">
		<div class="panel panel-default panel-warning">
			<div class="panel-heading"><h4>회원과 게시물 조인</h4></div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>고객 이름</th>
						<th>상품 이름</th>
						<th>수량</th>
						<th>가격</th>
					</tr>
				</thead>
				<c:forEach var="bean" items="${requestScope.lists}">
					<tr>
						<td>${bean.mname}</td>
						<td>${bean.pname}</td>
						<td>${bean.qty}</td>
						<td>${bean.price}</td>						
					</tr>
				</c:forEach>				
			</table>
		</div>
	</div>
</body>
</html>