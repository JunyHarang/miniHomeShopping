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
			var mode = $('#mode').val() ;
			var keyword = $('#keyword').val() ;
			location.href='<%=NoForm%>prList' + '&mode=' + mode + '&keyword=' + keyword ;
		}
		function searchAll(){
			location.href='<%=NoForm%>prList';
		}
		function writeForm(){
			location.href='<%=NoForm%>prInsert';
		}
		
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
	<div class="container col-sm-offset-<%=offset%> col-sm-<%=mywidth%>">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>상품 목록 보기</h4>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
					<tr>
						<th align="center">번호</th>
						<th align="center">상품명</th>
						<th align="center">제조 회사</th>
						<th align="center">이미지</th>
						<th align="center">재고</th>
						<th align="center">단가</th>
						<th align="center">카테고리</th>
						<th align="center">상품 설명</th>
						<th align="center">포인트</th>
						<th align="center">입고 일자</th>
						<th align="center">삭제</th>
						<th align="center">수정</th>
					</tr>
				</thead>
					<tbody>
						<tr>
							<td align="center" colspan="12">
								<form action="" class="form-inline" role="form" name="myform" method="get"> 
									<div class="form-group">
										<select id="mode" name="mode" class="form-control">
											<option value="all" selected="selected">-- 선택하세요.
											<option value="name">이름
											<option value="company">제조회사
											<option value="category">카테코리
										</select>
									</div>									
									<div class="form-group">
										<input type="text" class="form-control" name="keyword" id="keyword"> 
									</div>									
									&nbsp;&nbsp;
									<button class="btn btn-default" type="button" onclick="search();">검색</button>
									&nbsp;&nbsp;
									<button class="btn btn-default" type="button" onclick="searchAll();">전체 검색</button>
									<c:if test="${whologin == 2}">
										<button class="btn btn-default btn-info" type="button"
											onclick="writeForm();">상품 등록</button>
										</c:if>
									&nbsp;&nbsp;
									${pageInfo.pagingStatus}																	
								</form>
							</td>
						</tr>
				<c:forEach var="bean" items="${requestScope.lists}">
				<tr>
					<td>${bean.num}</td>
					<td>
						<a href="<%=NoForm%>prDetailView&num=${bean.num}&${requestScope.parameters}">
							${bean.name}
						</a>
					</td>
					<td>${bean.company}</td>
					<td>
						<img alt="image" src="upload/${bean.image}" width="200" height="200">
					</td>
					<td>${bean.stock}</td>
					<td>${bean.price}</td>
					<td>${bean.category}</td>
					<td>${bean.contents}</td>
					<td>${bean.point}</td>
					<td>${bean.inputdate}</td>
					<td>
						<c:if test="${whologin == 2}">
							<a href="<%=NoForm%>prDelete&num=${bean.num}&${requestScope.parameters}">
								삭제
							</a>
						</c:if>
						<c:if test="${whologin != 2}">
							삭제
						</c:if>				
					</td>
					<td>
						<c:if test="${whologin == 2}">
							<a href="<%=NoForm%>prUpdate&num=${bean.num}&${requestScope.parameters}">
								수정
							</a>
						</c:if>
						<c:if test="${whologin != 2}">
							수정
						</c:if>					
						
					</td>
				</tr>
				</c:forEach>											
					</tbody>
				</table>
			</div>
			<div align="center">
				<footer>${pageInfo.pagingHtml}</footer>
			</div>
		</div>
		<br><br><br><br>
		<script type="text/javascript">
		   /* 방금 전 선택한 콤보 박스를 그대로 보여 주기 */ 
			$('#mode option').each(function(){
				if( $(this).val() == '${pageInfo.mode}' ){
					$(this).attr('selected', 'selected') ;
				}
			});	
			/* 이전에 넣었던 값 그대로 보존 */
			$('#keyword').val( '${pageInfo.keyword}' ) ;		
		</script>	
	</div>
</body>
</html>