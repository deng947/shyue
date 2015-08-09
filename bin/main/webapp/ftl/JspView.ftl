<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<title>${class.displayName}查看</title>
</head>
<body>
<h1>${class.displayName}查看</h1>
<fieldset>
<#list class.fields as field>
<#if field.type="pojo">
	<div class="commonForm"><label>${field.pojo.displayName}：</label><font><#noparse>${</#noparse>${class.spell}.${field.name}.${field.pojo.displayField}}</font></div>
<#else>
	<div class="commonForm"><label>${field.displayName}：</label><font><#noparse>${</#noparse>${class.spell}.${field.name}}</font></div>	
</#if>
</#list>
<fieldset>
<div class="formButton"><a href=${class.spell}Form.do?id=<#noparse>${</#noparse>${class.spell}.id}>修改</a>
<a href="javascript:history.back();">返回列表 </a>
</div>
