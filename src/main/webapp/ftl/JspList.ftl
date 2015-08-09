<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<script type="text/javascript">  
$(function() { 
	$("#checkall").click( function() {
		if($("#checkall").attr("checked")==undefined){
			$("[name='item']").attr("checked", false);
		}else{
			$("[name='item']").attr("checked", true);
		}			
		});

	$("#deleteBtn").click( function() {
		if (!confirm("确定要删除吗?")) {
			return false;
		}else{
			$("#${class.spell}Form").attr("action","delete${class.name}List.do");
			$("#${class.spell}Form").submit();
		}
	});
});
</script>
<title>${class.displayName}列表</title>
</head>
<body>
<h1>${class.displayName}列表</h1>
<form name="${class.spell}Form" id="${class.spell}Form" action="${class.spell}List.do">
<div id="displayTable"><display:table class="table"
	name="pageObject" requestURI="" id="${class.spell}List" uid="items"
	export="false">
	<display:column style="width:5%;" headerClass="dataHead"
		title="<input type='checkbox' name='checkall' id='checkall' value='all'/>">
		<input type="checkbox" name="item" value="<#noparse>${items.id}</#noparse>" />
	</display:column>
	<#list class.fields as field>
		<#if field.name="id">
			<display:column style="width:10%;" property="${field.name}" url="${class.spell}.do?<#noparse>id=${items.id}</#noparse>" sortable="true" title="${field.displayName}"/>
		<#else>
			<#if field.type="pojo">
			<display:column style="width:10%;" property="${field.name}.${field.pojo.displayField}" sortable="true" title="${field.pojo.displayName}" />
			<#else>
			<display:column style="width:10%;" property="${field.name}" sortable="true" title="${field.displayName}" />
			</#if>
		</#if>
	</#list>
	<display:column style="width:5%;" title="编辑">
		<a href="${class.spell}Form.do?<#noparse>id=${items.id}</#noparse>"><img src="images/edit.gif"></a>
	</display:column>
	<display:setProperty name="paging.banner.placement" value="bottom" />
	</display:table>
</div>
<div class="pageForm">每页显示<select name="pageSize"
	onChange="${class.spell}Form.submit();" style="margin-bottom: 0">
	<option <c:if test="<#noparse>${pageSize==10}</#noparse>">selected</c:if>>10</option>
	<option <c:if test="<#noparse>${pageSize==20}</#noparse>">selected</c:if>>20</option>
	<option <c:if test="<#noparse>${pageSize==50}</#noparse>">selected</c:if>>50</option>
</select>条  <input type="text" name="page" value="<#noparse>${page}</#noparse>" size="1" maxlength="5" />页
<input type="submit" name="go" value="go" /></div>
<div id="displayButton">
<input id="deleteBtn" type="button" value="删除"/> 
<input onClick="window.location.href='${class.spell}Form.do'" type="button" value="新增"/>
</div>
</form>
</body>