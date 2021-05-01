<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="./../common/common.jsp"%>
<%
	int offset = 2; //오프 셋 
	int content = twelve - 2 * offset; //12 - 2 * 오프셋
%>
<html>
<head>
</head>
<body>
	<div class="container col-md-offset-<%=offset%> col-md-<%=content%>">
		<h1>${sessionScope.loginfo.name}(${sessionScope.loginfo.id})주문 상세 내역</h1>
		<hr>
		<p>
			${sessionScope.loginfo.name} 고객님께서 
			<strong>${order.orderdate}</strong> 구매하신 상품에 대한 상세 결제 내역입니다. 
		</p>
		<hr>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">주문 내역</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-condensed">
						<thead>
							<tr>
								<th class="text-center">제품명</th>
								<th class="text-center">이미지</th>								
								<th class="text-center">단가</th>
								<th class="text-center">수량</th>
								<th class="text-center">금액</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach var="shopinfo" items="${requestScope.lists}">
								<tr>
									<td class="text-center">${shopinfo.pname}</td>
									
									<td class="text-center">${shopinfo.image}</td>
									
									<td class="text-center">${shopinfo.price}</td>
									
									<td class="text-center">${shopinfo.qty}</td>
									
									<td class="text-center">${shopinfo.qty}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">결제 정보</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered">
						표 그리기
					</table>
				</div>
			</div>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">배송 정보</h3>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td class="text-center gr"></td>
								<td></td>
							</tr>							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>