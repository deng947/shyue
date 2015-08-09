<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:forEach var="person" items="${personList}">
		${person.name}
</c:forEach>

