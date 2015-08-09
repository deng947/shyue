<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="../common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <%@ include file="../common/meta.jsp" %>
        <title><decorator:title/></title>
        <script>
		var i=0;
        	function hidden(){
			$("#left").css("display","none");
			$("#mainContent").css("margin-left","0");
			$("#arrow").html(">");
			i=1;
		}
		
		function show(){
			$("#left").css("display","block");
			$("#mainContent").css("margin-left","130px");
			$("#arrow").html("<");
			i=0;
		}
		
		function clickBar(){
			if(i==0){
				hidden();
			}else{
				show();
			}
		}
		</script>
		<decorator:head/>
    </head>
<body>
    <div id="page">
        <div id="header" class="clearfix">
            <jsp:include page="/common/header.jsp"/>
        </div>

        <div id="content" class="clearfix">
        	<div id="left" >
                 <jsp:include page="/leftMenu.do"/>
            </div><!-- end nav -->
            <div id="navbar" onclick="javascript:clickBar();"><span id="arrow"><</span></div>
            <div id="mainContent">
                <%@ include file="/common/messages.jsp" %>
                <decorator:body/>
            </div>
        </div>

        <div id="footer" class="clearfix">
            <jsp:include page="/common/footer.jsp"/>
        </div>
    </div>
</body>
</html>
