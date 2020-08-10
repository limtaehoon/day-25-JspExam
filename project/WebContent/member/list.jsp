<%@page import="project.member.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file= "../include/header.jsp"%>

<div class ="container">
<br/>

	<h3>회원리스트(<span id="cntSpan">${count}</span>)</h3></br>
  <table class="table table-hover">
    <thead>
      <tr>
        <th>이름</th>
        <th>아이디</th>
        <th>전화번호</th>
        <th>이메일</th>
        <th>구분</th>
        <th>삭제</th>
      </tr>
    </thead>
    <tbody>
 
    <c:forEach items ="${members}" var="mem"><%/*list.jsp의 모든 값을 members로 보냄  mem:변수*/ %>
    
   
    
    <tr>
    <td>${mem.name}</td>
    <td>${mem.userid}</td>
    <td>${mem.phone}</td>
    <td>${mem.email}</td>
    <td>${mem.admin}</td>
    <c:if test="${mem.admin ==0 }">
     <td>일반회원</td>
    <td onclick="del('${mem.userid}')">삭제</td>
  	</c:if>
  	<c:if test="${mem.admin == 1 }">
    <td>관리자</td>
    <td>Admin</td>
    </c:if>
 	</tr>
    </c:forEach>
 
	</tbody>
  </table>
  </div>

  
  
  
  
  
  <%@ include file="../include/footer.jsp" %>