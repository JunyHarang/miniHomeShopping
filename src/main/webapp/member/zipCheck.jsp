<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	int twelve = 12;
	int myoffset = 3;
	int mywidth = twelve - 2 * myoffset;
	int formleft = 3;
	int formright = twelve - formleft;
%>

<%
	String contextPath = request.getContextPath();
	String mappingName = "/Shopping";
	String YesForm = contextPath + mappingName;
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>우편번호 검색</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	
		function myzipcheck() {
			var dong = document.myform.dong.value();
				if (dong.length < 1) {
					alert('동네 이름은 2글자 이상 입력 하시기 바랍니다.');
					document.myform.dong.focus();
					return false;
				}
		}
		
		function sendAddress(zipcode, address) {
			opener.myform.fakezipcode.value = zipcode;
			opener.myform.zipcode.value = zipcode;
			
			opener.myform.fakeaddress1.value = address;
			opener.myform.address1.value = address;
			
			opener.myform.address2.focus();
			
			self.close();
		}
		
	</script>
</head>
<body>
	<div class="container col-sm-offset-<%=myoffset%> col-sm-<%=mywidth%>">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>우편 번호 검색</h4>
			</div>
			<div class="panel-body">
				<form name="myform" class="form-inline" action="<%=YesForm%>" method="post">
					<input type="hidden" name="command" value="meZipcheck">
					
					<table class="table table-hover">
						<tr>
							<td colspan="2" align="center">
								<p class="from-control-static">동이름 입력 : </p>
								
								<div class="form-group">
									<input type="text" class="form-control" name="dong" id="dong" placeholder="검색할 동네를 입력 해 주시기 바랍니다!" value="${requestScope.dong}">
								</div>
								
								<button class="btn btn-default" type="submit" onclick="return myzipcheck();">
									<p>검색</p>
								</button>
							</td>
							<td width="75%"></td>
						</tr>
						
						<c:forEach var="oneitem" items="${lists}">
						
							<c:set var="address" value="${fn:trim(oneitem.si_nm)} ${fn:trim(oneitem.sgg_nm)} ${fn:trim(oneitem.rd_nm)} ${fn:trim(oneitem.bd_ma_sn)} (${fn:trim(oneitem.emd_nm)} ${fn:trim(oneitem.search_word)})" />
							
							<tr>
								<td width="25%" align="center">
									<a href="#" onclick="sendAddress('${oneitem.area_cd}','${address}');">
										${oneitem.area_cd}
									</a>
									
								</td>
								<td width="75%">
									<a href="#" onclick="sendAddress('${oneitem.area_cd}','${address}');">
										${address}
									</a>
								</td>
							</tr>
						
						</c:forEach>
						
					</table>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>