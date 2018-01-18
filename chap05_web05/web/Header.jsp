<%--
  Created by IntelliJ IDEA.
  User: nekisse
  Date: 2018. 1. 15.
  Time: PM 6:20
  To change this template use File | Settings | File Templates.
--%>
<%@page import="spms.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%--%>
<%--Member member = (Member)session.getAttribute("member");--%>
<%--%>--%>
<%--<jsp:useBean id="member"--%>
             <%--scope="session"--%>
             <%--class="spms.vo.Member"/>--%>
<div style="background-color:#00008b;color:#ffffff;height:20px;padding: 5px;">
    SPMS(Simple Project Management System)
    <c:if test="${!empty sessionScope.member and
                 !empty sessionScope.member.email}">
    <span style="float:right;">
        ${sessionScope.member.name}
<%--<%=member.getName()%>--%>
<a style="color:white;"
   href="<%=request.getContextPath()%>/auth/logout">로그아웃</a>
</span>
    </c:if>
</div>