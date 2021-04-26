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
				<h4>게시물 목록 보기</h4>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>글번호</th>
							<th>작성자</th>
							<th>비밀 번호</th>							
							<th>글제목</th>
							<th>글 내용</th>
							<th>조회수</th>
							<th>작성 일자</th>
							<th>비고</th>							
							<th>답글 쓰기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center" colspan="9">
								<form action="" class="form-inline" role="form" name="myform" method="get"> 
									<div class="form-group">
										<select id="mode" name="mode" class="form-control">
											<option value="all" selected="selected">-- 선택하세요.
											<option value="writer">작성자
											<option value="subject">제목
											<option value="content">글 내용
										</select>
									</div>									
									<div class="form-group">
										<input type="text" class="form-control" name="keyword" id="keyword"> 
									</div>									
									&nbsp;&nbsp;
									<button class="btn btn-default" type="submit" onclick="search();">검색</button>
									&nbsp;&nbsp;
									<button class="btn btn-default" type="button" onclick="searchAll();">전체 검색</button>
									&nbsp;&nbsp;
									<button class="btn btn-default" type="button" onclick="writeForm();">글쓰기</button>								
								</form>
							</td>
						</tr>	
							
						<c:forEach var="bean" items="${requestScope.lists}">
							<tr>
								<td>${bean.no}</td>
								<td>${bean.subject}</td>
								<td>${bean.writer}</td>
								<td>${bean.password}</td>
								<td>${bean.content}</td>
								<td>${bean.readhit}</td>
								<td>${bean.regdate}</td>
								<td>${bean.groupno}</td>
								<td>${bean.orderno}</td>
								<td>${bean.depth}</td>
								<td>${bean.remark}</td>
							</tr>
						</c:forEach>	
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>