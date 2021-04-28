<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<%
	/* position for grid system */	
	int offset = 2 ;
	int mywidth = twelve - 2 * offset ;
	int formleft = 3 ;
	int formright = twelve - formleft ;
	//int rightButton = 2 ;
%> 
<!DOCTYPE html><html>
<head>
	<script>
		$(document).ready(function(){
			
		});
		
		function gotolist() {
			location.href = '<%=NoForm%>boList' ;
		}
	</script>
</head>
<body>
	<div class="container col-sm-offset-<%=offset%> col-sm-<%=mywidth%>">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>게시물 상세 보기</h4>
			</div>
			<div class="panel-body">
					<div class="col-sm-12">
					
						<table class="table table-borderd">
							<tr>
								<td width="25%" align="center">글 번호</td>
								<td width="75%" align="left">${bean.no}</td>
							</tr>
							
							<tr>
								<td width="25%" align="center">작성자</td>
								<td width="75%" align="left">${bean.writer}</td>
							</tr>
							
							<tr>
								<td width="25%" align="center">글 제목</td>
								<td width="75%" align="left">${bean.subject}</td>
							</tr>
							
							<tr>
								<td width="25%" align="center">비밀번호</td>
								<td width="75%" align="left">${bean.password}</td>
							</tr>
							
							<tr>
								<td width="25%" align="center">글 내용</td>
								<td width="75%" align="left">${bean.content}</td>
							</tr>
							
							<tr>
								<td width="25%" align="center">조회수</td>
								<td width="75%" align="left">${bean.readhit}</td>
							</tr>
							
							<tr>
								<td width="25%" align="center">작성일자</td>
								<td width="75%" align="left">${bean.regdate}</td>
							</tr>
						
							<tr>
								<td width="25%" align="center">비고</td>
								<td width="75%" align="left">${bean.remark}</td>
							</tr>
						</table>
						
						</div>
						
						<hr>
						
						<div class="col-sm-offset-5 col-sm-4">
							<button class="btn btn-primary" onclick="gotolist();">
								돌아가기
							</button>
						</div>
				</div>
			</div>
		</div>
</body>
</html>