<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>${class.displayName}表单</title>
</head>
<body>
<h1>${class.displayName}表单</h1>

<form:form modelAttribute="${class.spell}">
<fieldset>
<#list class.fields as field>
	<#if field.type="pojo">
		<div class="commonForm"><label>${field.pojo.displayName}：</label><form:select path="${field.name}" items="<#noparse>${</#noparse>${field.name}List}" itemValue="id" itemLabel="${field.pojo.displayField}"/></div>
	<#else>
		<#if field.name="id">
		<c:if test="<#noparse>${</#noparse>${class.spell}.id>0}">
		<div class="commonForm"><label>${field.displayName}：</label><font><#noparse>${</#noparse>${class.spell}.${field.name}}</font>
		</c:if>
		<#else> 
		<#if field.type="boolean">
		<div class="commonForm"><label>${field.displayName}：</label><form:checkbox path="${field.name}"/></div>
		<#else>
		<div class="commonForm"><label>${field.displayName}：</label><form:input path="${field.name}" size="${field.size}" maxlength="${field.size}"/></div>
		</#if>	
		</#if>
	</#if>
</#list>
<div class="formButton"><input type="submit" name="save" value="保存" /> 
<c:if test="<#noparse>${</#noparse>${class.spell}.id>0}"><input type="submit" name="delete" value="删除" /> </c:if>
<input onClick="history.back();" type="button" value="返回 " />
</div>
</fieldset>
</form:form>