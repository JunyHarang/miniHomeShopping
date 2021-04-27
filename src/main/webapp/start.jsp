<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/common.jsp" %>
<!DOCTYPE><html><head></head>
<body> 	
<%
	String imsi = NoForm + "main" ;
	//response.sendRedirect( imsi ) ;
%>
<ul class = "pagination"><li><a href="boList&pageNumber=1&mode=writer&keyword=홍길동">맨 앞</a></li><li><a href="boList&pageNumber=10&mode=writer&keyword=홍길동">이전</a></li><li><a href="boList&pageNumber=11&mode=writer&keyword=홍길동">11</a></li><li><a href="boList&pageNumber=12&mode=writer&keyword=홍길동">12</a></li><li class="active"><a><font color='green'><b>13</b></font></a></li><li><a href="boList&pageNumber=14&mode=writer&keyword=홍길동">14</a></li><li><a href="boList&pageNumber=15&mode=writer&keyword=홍길동">15</a></li><li><a href="boList&pageNumber=16&mode=writer&keyword=홍길동">16</a></li><li><a href="boList&pageNumber=17&mode=writer&keyword=홍길동">17</a></li><li><a href="boList&pageNumber=18&mode=writer&keyword=홍길동">18</a></li><li><a href="boList&pageNumber=19&mode=writer&keyword=홍길동">19</a></li><li><a href="boList&pageNumber=20&mode=writer&keyword=홍길동">20</a></li><li><a href="boList&pageNumber=21&mode=writer&keyword=홍길동">다음</a></li><li><a href="boList&pageNumber=25&mode=writer&keyword=홍길동">끝</a></li></ul>
</body>
</html>