<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<script type="text/javascript">
	$( function() {
		$("#checkall").click( function() {
			if($("#checkall").attr("checked")==undefined){
				$("[name='checkbox']").attr("checked", false);
			}else{
				$("[name='checkbox']").attr("checked", true);
			}			
			});
		
		$("#clearBtn").click( function() {
			$("[name='checkbox']").attr("checked", false);
			$("#checkall").attr("checked", false);
			});
		
		$("#reverseBtn").click( function() {
			$("[name='checkbox']").each(function(){
				var s=$(this).attr("checked");
				if(s==undefined) s=true;
				else s=false;
				$(this).attr("checked",s);
			});
		});

		$("#deleteBtn").click( function() {
			var d="";
			$("[name='checkbox']").each(function(){
				if($(this).attr("checked")!=undefined){
					d+=$(this).attr("value")+";";
				}
			});
			if (d.length>0) {
				if (confirm("确定要删除吗?")) {
					window.location.href = "roleList.do?deleteItems="
							+ d;
				}
			} else {
				alert("请选择要删除的角色！");
			}
		});
	});
</script>
</head>

<body>
<h1>权限管理&nbsp;→&nbsp;角色管理 </h1>

<form name="pageForm" action="roleList.do">
<fieldset> 
<div class="leftForm"> 
 <label>角色名称：</label><input name="queryName" type="text" size="15" maxlength="15" value="${queryName}"></td> 
</div>
<div class="rightForm">
<input name="query" type="submit" class="oabutton" value="查询" style="cursor:hand">
</div>
</fieldset>

<DIV id="displayTable"><display:table class="table"
	name="pageObject" requestURI="" id="roleList"
	uid="items" export="false">
	<display:column style="width:10%;" headerClass="dataHead"
		title="<input type='checkbox' name='checkall' id='checkall' value='all'/>">
		<input type="checkbox" name="checkbox" value="${items.id}" />
	</display:column>
	<display:column style="width:20%;" property="id" sortable="true"
		title="序号" />
	<display:column style="width:30%;" property="name"
		sortable="true" title="角色名称" />
<display:column style="width:30%;" property="description"
		sortable="true" title="角色描述" />
	<display:column style="width:10%;" title="编辑">
		<A href="roleForm.do?id=${items.id}"> <IMG
			src="images/edit.gif" border=0> </A>
	</display:column>
	<display:setProperty name="paging.banner.item_name" value="角色" />
	<display:setProperty name="paging.banner.items_name" value="角色" />
	<display:setProperty name="paging.banner.placement" value="bottom" />
</display:table> <input type="hidden" name="delCells" value="" /></DIV>
<div class="pageForm">每页显示<select name="pageSize"
	onChange="pageForm.submit();" style="margin-bottom: 0">
	<option <c:if test="${pageSize==10}">selected</c:if>>10</option>
	<option <c:if test="${pageSize==20}">selected</c:if>>20</option>
	<option <c:if test="${pageSize==50}">selected</c:if>>50</option>
</select>条 <input type="text" name="page" value="${page}" size="1" maxlength="5" />页
<input type="submit" name="go" value="go" />
</div>
<div id="displayButton"><INPUT id="reverseBtn" type="button"
	value="反选"> <INPUT id="clearBtn" type="button"
	value="清空"> <INPUT id="deleteBtn" type="button"
	value="删除"> <INPUT
	onClick="window.location.href='roleForm.do'" type="button" value="新增"> &nbsp;</div>
</form>
</body>
