<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
</head>

<body>
<form name="icform" method="post">
      <input type="hidden" name="deptId" value="${dept.deptId}"/>

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="formSubmit('${ctx}/dept/update','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>

  <div class="textbox-title">
	<img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
   修改部门
  </div>



    <div>
		<table class="commonTable" cellspacing="1">
	        <tr>
	            <td class="columnTitle">上级部门：</td>
	            <td class="tableContent">
						<select name="parentId" id="parentId">
							<option value="">--请选择--</option>
							<c:forEach items="${depts}" var="obj">
								<c:if test="${dept.deptId!=obj.deptId}">
									<option value="${obj.deptId}">${obj.deptName}</option>
								</c:if>
							</c:forEach>
						</select>
	            </td>
	        </tr>
	        <tr>
	            <td class="columnTitle">部门名称：</td>
	            <td class="tableContent"><input type="text" name="deptName" value="${dept.deptName}"/>
	            </td>

	        </tr>
            <tr>
                <td class="columnTitle">部门状态：</td>
                <td >
                    <input type="radio" name="state" ${dept.state==1?'checked':''} value="1"/>启用
                    <input type="radio" name="state" ${dept.state==0?'checked':''}  value="0"/>停用
                	<input type="hidden" name="deptNo" value="${dept.deptNo}">
				</td>

            </tr>
		</table>
	</div>
 </form>
</body>
<script type="text/javascript">
	//上级部门回显
	$(function(){
		$("#parentId").val('${dept.parentId}');
	});
</script>
</html>