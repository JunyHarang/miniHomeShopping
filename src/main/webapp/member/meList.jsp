<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>
<%
	/* position for grid system */	
	int offset = 2 ;
	int mywidth = twelve - 2 * offset ;
	int formleft = 3 ;
	int formright = twelve - formleft ;
	int rightButton = 2 ;
%> 
<!DOCTYPE html><html>
<head>
	<script>	
		function search(){
			alert('검색');
		}
		function searchAll(){
			alert('전체 검색');
		}
		function writeForm(){
			alert('글 작성');
		}
		
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
	<div class="container col-sm-offset-<%=offset%> col-sm-<%=mywidth%>">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>회원 목록 보기</h4>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>이름</th>
							<th>성별</th>							
							<th>결혼 여부</th>
							<th>포인트</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center" colspan="9">
								<form action="" class="form-inline" role="form" name="myform" method="get"> 
									<div class="form-group">
										<select id="mode" name="mode" class="form-control">
											<option value="all" selected="selected">-- 선택하세요.
											<option value="id">아이디
											<option value="name">이름
											<option value="gender">성별
										</select>
									</div>									
									<div class="form-group">
										<input type="text" class="form-control" name="keyword" id="keyword"> 
									</div>									
									&nbsp;&nbsp;
									<button class="btn btn-default" type="submit" onclick="search();">검색</button>
									&nbsp;&nbsp;
									<button class="btn btn-default" type="button" onclick="searchAll();">전체 검색</button>
								</form>
							</td>
						</tr>	
						
						<c:forEach var="bean" items="${requestScope.lists}">
							<tr>
								<td>${bean.id}</td>
								<td>${bean.name}</td>
								<td>${bean.gender}</td>
								<td>${bean.marriage}</td>
								<td>${bean.mpoint}</td>
							</tr>
						</c:forEach>	
						
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>