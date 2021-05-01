<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp"%>
<%
	int offset = 3; //오프 셋 
	int content = twelve - 2 * offset; //12 - 2 * 오프셋
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrap Sample</title>
</head>
<body>
	<div class="container col-md-offset-<%=offset%> col-md-<%=content%>">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">
					${sessionScope.loginfo.name}(${sessionScope.loginfo.id})님의 주문 내역
				</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-condensed">
						<thead>
							<tr>
								<th class="text-center">주문 번호</th>
								
								<th class="text-center">주문 일자</th>
								
								<th class="text-center">상세 보기</th>
								
								<th class="text-center">비&nbsp;고</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="shopinfo" items="${requestScope.lists}">
								<tr class="record">
									<td align="center">${shopinfo.oid}</td>
									
									<td align="center">${shopinfo.orderdate}</td>
									
									<td align="center">
										<a href="<%=NoForm%>mallDetail&oid=${shopinfo.oid}">
											상세 보기										
										</a>	
									</td>
									
									<td align="center">${shopinfo.remark}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>

</html>