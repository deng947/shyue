<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<h1>权限管理&nbsp;→&nbsp;资源维护</h1>

<form:form modelAttribute="resource">
<fieldset>
<div class="commonForm"><label>资源代码：</label><form:input path="code" size="40" maxlength="40"/></div>
<div class="commonForm"><label>资源描述：</label><form:textarea path="description" cols="20" rows="5" /></div>
<div class="formButton"><input type="submit" value="保存" /> <INPUT
		onClick="history.back();" type="button" value="返回 " /></div>
</div>
</fieldset>
</form:form>