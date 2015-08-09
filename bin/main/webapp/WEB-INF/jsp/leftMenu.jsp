<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${authority}">
<ul>
<a href="#">权限管理</a>
<li><a href="userList.do">用户管理</a></li>
<li><a href="roleList.do">角色管理</a></li>
<li><a href="permissionList.do">赋权管理</a></li>
<li><a href="resourceList.do">资源管理</a></li>
<li><a href="deptList.do">部门管理</a></li>
<li><a href="orgList.do">公司管理</a></li>
</ul>
</c:if>
<c:if test="${desktop}">
<ul>
<a href="#">个人工作台</a>
<li><a href="personList.do">人员列表</a></li>
<c:if test="${baoxiaoApply}">
<li><a href="#">报销申请</a></li>
</c:if>
<c:if test="${baoxiaoAudit}">
<li><a href="#">报销审批</a></li>
</c:if>
</ul>
</c:if>
