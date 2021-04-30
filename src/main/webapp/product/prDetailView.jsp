<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>
<%
	/* position for grid system */	
	int offset = 2 ;
	int mywidth = twelve - 2 * offset ;
	int leftside = 4 ;
	int rightside = twelve - leftside ;
	
	//int formleft = 3 ;
	//int formright = twelve - formleft ;
	//int rightButton = 2 ;
%> 
<!DOCTYPE html><html>
<head>
	<script>			
		$(document).ready(function(){
			$('[data-toggle="popover"]').popover();
		});
	</script>
</head>
<body>
	<div class="container col-sm-offset-<%=offset%> col-sm-<%=mywidth%>">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>상품 상세 보기</h4>
			</div>
			<div class="panel-body">
				<div class="col-sm-<%=leftside%>">
					<table>
						<tr>
							<td>
								<p>이미지 경로</p>
								${applicationScope.uploadedPath}/${bean.image}
								<img src="${applicationScope.uploadedPath}/${bean.image}" class="img-rounded" alt="사과" width="200" height="200">		
							</td>
						</tr>
					</table>
				</div>
				
				<div class="col-sm-<%=rightside%>">
					<table class="table table-bordered">
						<tr>
							<td width="25%" align="center">상품명(번호)</td>
							<td width="75%" align="left">${bean.name}(${bean.num})</td>
						</tr>
						<tr>
							<td width="25%" align="center">제조 회사</td>
							<td width="75%" align="left">${bean.company}</td>
						</tr>
						<tr>
							<td width="25%" align="center">재고 수량</td>
							<td width="75%" align="left">${bean.stock}</td>
						</tr>
						<tr>
							<td width="25%" align="center">가격</td>
							<td width="75%" align="left">${bean.price}</td>
						</tr>
						<tr>
							<td width="25%" align="center">설명</td>
							<td width="75%" align="left">${bean.contents}</td>
						</tr>
						<tr>
							<td width="25%" align="center">주문 수량</td>
							
							<td width="75%" align="left">
								<form action="<%=YesForm%>" class="form-inline" role="form" method="post">
								 
									<input type="hidden" name="command" value="mallInsert">
									
									<input type="hidden" name="num" value="${bean.num}">
									
									<input type="hidden" name="stock" value="${bean.stock}">
									
									<div class="form-group">
										<input type="number" name="qty" id="qty" class="form-control"
											data-toggle="popover" title="수량 입력란" 
											data-trigger="hover"
											data-placement="auto top"
											data-content="구매하시고자 하는 수량을 정수로 입력하세요.">  
									</div>
									<button type="submit" class="btn btn-danger">주문</button>
									 
								</form>
							</td>
						</tr>
						<tr>
							<td width="25%" align="center">포인트</td>
							<td width="75%" align="left">${bean.point}</td>
						</tr>						
						<tr>
							<td width="25%" align="center">입고 일자</td>
							<td width="75%" align="left">${bean.inputdate}</td>
						</tr>
					</table>
				</div>
				<hr>
				<div class="col-sm-offset-5 col-sm-4" >
					<button class="btn btn-primary" onclick="history.back();">
						돌아 가기
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>









