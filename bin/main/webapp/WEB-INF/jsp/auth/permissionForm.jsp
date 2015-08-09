<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<h1>权限管理&nbsp;→&nbsp;赋权管理</h1>
<form:form modelAttribute="user">
<fieldset>
<div class="leftForm"><label>用户姓名：</label><form:input path="name" size="20" maxlength="40"
				readonly="true" /></div>
	<div class="rightForm"><label>用户职位：</label><form:input path="position" size="20"
				maxlength="40" readonly="true" /></div>
<div class="leftForm"><label>角色列表：</label><div style="float:right;"><c:forEach var="roleList"
				items="${roleList}" varStatus="status">
				<span style="float:left;clear:left;width:100%;">
				<input name="roleListIds" type="checkbox"
					value="${roleList.id}"
					<c:forEach var="userRole"
				items="${user.roles}" varStatus="status">
<c:if test="${userRole.id==roleList.id}"> checked </c:if> </c:forEach>>
				${roleList.name}
			</span></c:forEach></div></div>
	<div class="formButton"><input type="submit" value="保存" /> <INPUT
		onClick="history.back();" type="button" value="返回 " /></div>
	</fieldset>
</form:form>
