<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
	<script>
	     function isOnlyChecked(){
			 if($("input[name='id']:checked").length==1){
				 return true;
			 }
			 return false;
		 }
		 // 查看信息
	     function toView(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('${ctx}/dept/toView','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }
	     //实现更新
	     function toUpdate(){
	    	 if(isOnlyChecked()){
	    		 formSubmit('${ctx}/dept/toUpdate','_self');
	    	 }else{
	    		 alert("请先选择一项并且只能选择一项，再进行操作！");
	    	 }
	     }

	     //实现删除
	     function toDelete(){
             //获取选中的部门id
             var deptIds  = new Array();
             var checkboxs = $("input[name='id']:checked");
             if(checkboxs.length==0){
                 alert("请先选择一项，再进行操作！");
                 return;
             }
             for (var i = 0; i <checkboxs.length ; i++) {
                 deptIds.push(checkboxs[i].value);
             }
             deptIds = deptIds.join(",");//1,2,3,4
             //ajax发出删除的请求
             $.ajax({
                 type:"post",
                 url:"${ctx}/dept/delete",
                 data:{"deptId":deptIds},
                 success:function(data){
                     if(data.status==200){
                         alert(data.message);
                     }else{
                         alert("以下部门有子部门或有员工，不能删除：\n"+data.data);
                     }
                     formSubmit('${ctx}/dept/list','_self');
                 }
             });

         }
		 // 导出
         function exportData(){
             $("#frm").prop("action","${ctx}/dept/exportDept")
             $("#frm").submit();
         }
	</script>
</head>

<body>
<form name="frm" id="frm" action="${ctx}/dept/list" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="view"><a href="#" onclick="javascript:toView()">查看</a></li>
<li id="new"><a href="#" onclick="formSubmit('${ctx}/dept/toCreate','_self');this.blur();">新增</a></li>
<li id="update"><a href="#" onclick="javascript:toUpdate()">修改</a></li>
<li id="delete"><a href="#" onclick="javascript:toDelete();this.blur();">删除</a></li>
	<li id="search"><a href="#" onclick="formSubmit('${ctx}/dept/list','_self');this.blur();">查询</a></li>
</ul>
  </div>
</div>
</div>
</div>

<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    部门列表
  </div>
  </div>
  </div>
</div>
<div>


<div class="eXtremeTable" >
<jsp:include page="${ctx}/common/page.jsp"></jsp:include>
<table id="ec_table" class="tableRegion" width="98%" >
	<thead>
	<tr>
		<td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
		<td class="tableHeader">编号</td>
		<td class="tableHeader">上级</td>
		<td class="tableHeader">名称</td>
		<td class="tableHeader">状态</td>
	</tr>
	</thead>
	<tbody class="tableBody" >
	<c:forEach items="${pb.datas}" var="dept" varStatus="st">
		<tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
			<td><input type="checkbox" name="id" value="${dept.deptId }"/></td>
			<td>${dept.deptNo }</td>
			<td>${dept.parentName }</td>
			<td>${dept.deptName }</td>
			<td>${dept.state==1?'启用':'停用'}</td>
		</tr>
   </c:forEach>
	</tbody>
</table>
</div>

</div>

</form>
</body>
</html>

