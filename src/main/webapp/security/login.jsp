<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page
	import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>

<head>
    <title>login</title>
    <meta name="heading" content="fff"/>
    <meta name="menu" content="Login"/>
</head>
<body id="login"/>
<div align="center"><c:if test="${not empty param.login_error}">
	<font color="red"> 用户名或密码错，请重新登录.<BR>
	<BR>
	</font>
</c:if>
<form name="f" method="post" action="${ctx}/j_spring_security_check">
用户名: <c:if test="${not empty param.login_error}">
	<c:set var="userKey"
		value="<%=AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY%>" />
	<input type='text' name='j_username' value='${sessionScope[userKey]}' />
</c:if> <c:if test="${empty param.login_error}">
	<input type='text' name='j_username' />
</c:if> <br>
&nbsp;&nbsp;&nbsp;&nbsp;密码: <input type="password" name="j_password" /> <br>
<input type="checkbox" name="_spring_security_remember_me" /> 2周内记住密码 <br>
<input type="submit" value="登录" /> <input type="reset" value="重置" /></form>
</div>
