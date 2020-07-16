<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<link rel="stylesheet" href="${ctx }/components/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css" />

	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript">
		var zTreeObj;
		var setting = {
			check: {
				enable: true //显示checkbox/radio
			},
			data: {
				simpleData: {
					enable: true//使用简单的数据模式
				}
			}
		};
		//树的节点数据
	/*	var zNodes = [
			{"id":1, "pId":0, "name":"系统管理",open:true},
			{"id":11, "pId":1, "name":"用户管理",open:true},
			{"id":12, "pId":1, "name":"角色管理",open:true},
			{"id":111, "pId":11, "name":"新增",open:true,checked:true }
		];*/
		$(function(){
			$.ajax({
				url:'${ctx}/role/listModuleOfTreeBeanByRoleId',
				type:'post',
				data:{"roleId":'${role.roleId}'},
				success:initzTree
			});
		});
		function initzTree(zNodes){
			/**
			 obj：显示数的heml元素
			 setting：树的配置信息
			 zNodes：树的节点数据
			 */
			zTreeObj = $.fn.zTree.init($("#jTree"), setting, zNodes);
		}

		//获取所有选择的节点
		function submitCheckedNodes() {
			var nodes = new Array();//装选中的节点
			//获得选中的节点
			nodes = zTreeObj.getCheckedNodes(true);
			//选中的id
			var ids = new Array();
			//遍历选中的节点数组，并把节点对象的id属性装到ids数组中
			for (var i = 0; i < nodes.length; i++) {
				ids.push(nodes[i].id);
			}
			//把数组ids转换成1,2,3,4这种形式
			ids = ids.join(",")
			$("#moduleIds").val(ids);
		}
	</script>
</head>

<body>
<form name="icform" method="post">
	<input type="hidden" name="roleId" value="${role.roleId}"/>
	<input type="hidden" id="moduleIds" name="moduleIds" />
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="submitCheckedNodes();formSubmit('${ctx}/role/role','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="formSubmit('/role/list','_self');this.blur();">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    配置 [${role.name}] 角色的模块
  </div> 
  </div>
  </div>
  
<%-- <div>


<div style="text-align:left">
	<c:forEach items="${moduleList}" var="o">
		<div style="padding:3px;">
		<input type="checkbox" name="moduleIds" value="${o.id}" class="input"
			<c:if test="${fn:contains(roleModuleStr,o.id)}">checked</c:if>
		>
		${o.name}
		</div>
	</c:forEach>
</div>
 
</div> --%>
<div>
	<%--显示zTree的dom节点--%>
	<ul id="jTree" class="ztree"></ul>
</div>
 
 
</form>
</body>
</html>

