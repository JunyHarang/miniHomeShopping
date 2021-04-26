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
		function idCheck(){
//			alert('아이디 중복 체크') ;
			var id = document.myform.id.value;
				if (id.length < 4) {
					alert('아이디는 최소 4자리 이상이어야 합니다!');
					document.myform.id.focus();
					return false;
				}
				var url = '<%=NoForm%>meIdcheck&id='+id;
				window.open(url,'mywin','height=150,width=300');
		}
		
		function zipfind(){
			var url = '<%=NoForm%>meZipcheck';
			window.open(url,'mywin','height=600,width=720,scrollbars=yes');
		}
		
		function isCheckFalse() {
			document.myform.isCheck.falue = false;
		};
		
		function checkForm() {
			var isCheck = document.myform.isCheck.value;
				if (isCheck == 'false') {
					alert('아이디 중복 체크를 해 주세요!');
					return false;
				}
		}
		
		$(document).ready(function(){
			$('[data-toggle="tooltip"]').tooltip();	
		});
	</script>
</head>
<body>
	<div class="container col-sm-offset-<%=offset%> col-sm-<%=mywidth%>">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>회원 가입</h4>
			</div>
			<div class="panel-body">
				<form name="myform" class="form-horizontal" action="" method="post">
				    <div class="form-group">
				      	<label class="control-label col-sm-<%=formleft%>" for="id">아이디</label>
				      	
				      		<input type="hidden" name="command" value="meInsert">
				      		<input type="hidden" name="isCheck" value="false">
				      	
				      	<div class="col-sm-<%=formright-rightButton%>">
				      	
				      	<%-- value=${bean.id} 이 부분은 회원 가입 버튼을 누르고 에러가 났을 시 입력 했던 값이 초기화 되지 않게 잡아주는 기능을 위해 작성 --%> 
				      	
				        	<input type="text" class="form-control" id="id" placeholder="아이디를 넣어 주세요." 
				        		name="id" data-toggle="tooltip" onkeyup="isCheckFalse();" value="${bean.id}" pattern="[A-Za-z]+" title="아이디는 4글자 이상 10글자 이하로 입력해주세요.">
				        		
				      		<span class="err">${errid}</span>
				      		
				      	</div>
				      	<div class="col-sm-<%=rightButton%>">
				        	<input type="button" value="중복 체크" 
				        		class="btn btn-info" onclick="idCheck();"> 
				      	</div>
				    </div>
					<div class="form-group">
				      	<label class="control-label col-sm-<%=formleft%>" for="name">이름</label>
				      	<div class="col-sm-<%=formright%>">
				        	<input type="text" class="form-control" id="name" name="name" value="${bean.name}">
				        	
				        	<span class="err">${errname}</span>
				        	
				      	</div>
				    </div>				    
				    <div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="password">비밀 번호</label>
				      	<div class="col-sm-<%=formright%>">          
				        	<input type="password" class="form-control" id="password" placeholder="비밀 번호를 넣어 주세요." name="password">
				      	
				      		<span class="err">${errpassword}</span>
				      	
				      	</div>
				    </div>	
	    			<div class="form-group">
				      	<label class="control-label col-sm-<%=formleft%>" for="gender">성별</label>
				      	<div class="col-sm-<%=formright%>">				        	
				        	<label class="radio-inline">
				        		<input type="radio" name="gender" value="남자">남자
				        	</label>
							<label class="radio-inline">
								<input type="radio" name="gender"  value="여자">여자
							</label>
				      	</div>
				      	<span class="err">${errgender}</span>
				    </div>
					<div class="form-group">
				      	<label class="control-label col-sm-<%=formleft%>" for="birth">생일</label>
				      	<div class="col-sm-<%=formright%>">
				        	<input type="date" class="form-control" id="birth" name="birth" value="${bean.birth}">
				        	
				        	<span class="err">${errbirth}</span>
				        	
				      	</div>
				    </div>		
				    		    
				    <div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="marriage">결혼 여부</label>
				      	<div class="col-sm-<%=formright%>">
				        	<select class="form-control" id="marriage" name="marriage">
				        		<option value="-">--- 결혼 여부를 선택하세요.</option>
							    <option value="결혼">결혼</option>
							    <option value="이혼">이혼</option>
							    <option value="미혼">미혼</option>
						  	</select>
						  	<span class="err">${errmarriage}</span>
				      	</div>
				    </div>	
				    
	    			<div class="form-group">
				      	<label class="control-label col-sm-<%=formleft%>" for="salary">급여</label>
				      	<div class="col-sm-<%=formright%>">
				        	<input type="number" class="form-control" id="salary" name="salary" value="${bean.salary}">
				        	
				        	<span class="err">${errsalary}</span>
				        	
				      	</div>
				    </div>
				    
					<div class="form-group">
				      	<label class="control-label col-sm-<%=formleft%>" for="zipcode">우편 번호</label>
				      	<div class="col-sm-<%=formright-rightButton%>">
				        	<input type="text" class="form-control" disabled="disabled" 
				        		id="fakezipcode" name="fakezipcode">
				        	<input type="hidden" name="zipcode" value="${bean.zipcode}">
				      	</div>
				      	
				      	<div class="col-sm-<%=rightButton%>">
				        	<input type="button" value="우편 번호 찾기" class="btn btn-info"
				        		onclick="zipfind();">
				        		
				        		<span class="err">${errzipcode}</span>
				        		
				      	</div>
				    </div>	
				    			    
					<div class="form-group">
				      	<label class="control-label col-sm-<%=formleft%>" for="address1">주소</label>
				      	<div class="col-sm-<%=formright%>">
				        	<input type="text" class="form-control" disabled="disabled" 
				        		id="fakeaddress1" name="fakeaddress1">
				        	<input type="hidden" name="address1" value="${bean.address1}">
				        	
				        	<span class="err">${erraddress1}</span>
				        	
				      	</div>
				    </div>		
				    
				    <div class="form-group">
				      	<label class="control-label col-sm-<%=formleft%>" for="address2">상세 주소</label>
				      	<div class="col-sm-<%=formright%>">
				        	<input type="text" class="form-control" id="address2" name="address2" value="${bean.address2}">
				      	</div>
				    </div>	
				        
				    <div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="manager">매니저</label>
				      	<div class="col-sm-<%=formright%>">          
				        	<select class="form-control" id="manager" name="manager" value="${bean.manager}">
				        		<option value="-">--- 매니저를 선택하세요.</option>
				        		
									<c:forEach var="item" items="${requestScope.managers}">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>			
										        		
						  	</select>
						  	
						  	<span class="err">${errmanager}</span>
						  					        	
				      	</div>
				    </div>					    
				    <div class="form-group">        
				      	<div class="col-sm-offset-<%=offset%> col-sm-<%=mywidth%>">
				        	<button type="submit" class="btn btn-default" onclick="return checkForm();">회원 가입</button>
				        	&nbsp;&nbsp;&nbsp;
				        	<button type="reset" class="btn btn-default">초기화</button>				        	
				      	</div>
				    </div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>