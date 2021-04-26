<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./../common/common.jsp" %>
<%
	/* position for grid system */	
	int offset = 2 ;
	int mywidth = twelve - 2 * offset ;
	int formleft = 3 ;
	int formright = twelve - formleft ;
%> 
<!DOCTYPE html><html>
<head>
	<script>
		$(document).ready(function(){
			$('[data-toggle="tooltip"]').tooltip();	
		});
	</script>
</head>
<body>
	<div class="container col-sm-offset-<%=offset%> col-sm-<%=mywidth%>">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>회원 로그인</h4>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" action="<%=YesForm%>" method="post">
					<input type="hidden" name="command" value="meLogin">
				    <div class="form-group">
				      	
				      	<label class="control-label col-sm-<%=formleft%>" for="id">아이디</label>
				      	
				      	<div class="col-sm-<%=formright%>">
				       
				        	<input type="text" class="form-control" id="id" placeholder="아이디를 넣어 주세요." name="id" data-toggle="tooltip" title="아이디는 4글자 이상 10글자 이하로 입력해주세요." value="${id}">
				       
				        		<span class="form-control-static err">
				        			${errid}
				       
				        		</span> 	
				      	</div>
				    </div>
				    <div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="password">비밀 번호</label>
				      	<div class="col-sm-<%=formright%>">          
				        	<input type="password" class="form-control" id="password" placeholder="비밀 번호를 넣어 주세요." name="password" value="${password}">
				      		
				      		<span class="form-control-static err">
				        			${errpassword}
				       
				        		</span>
				      	
				      	</div>
				    </div>				    
				    <div class="form-group">        
				      	<div class="col-sm-offset-<%=offset%> col-sm-<%=mywidth%>">
				        	<button type="submit" class="btn btn-default">전송</button>
				        	&nbsp;&nbsp;&nbsp;
				        	<button type="reset" class="btn btn-default">초기화</button>
				        	&nbsp;&nbsp;&nbsp;
				        	<a href="#">회원 가입</a> 
				      	</div>
				    </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>