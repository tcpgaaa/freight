<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/left.css" media="all"/>
</head>
 
<body id="left_frame">
<div class="PositionFrame_black" id="PositionFrame"></div>
 
 
<!-- begin1  -->
<div id="sidebar" class="sidebar">
	<div class="sidebar_t">
		<div class="sidebar_t_l"></div>
		<div class="sidebar_t_c"></div>
		<div class="sidebar_t_r"></div>
	</div>
        <div class="panel">
        <div class="panel_icon"><img src="${ctx}/skin/default/images/icon/components.png"/></div>
        <div class="panel-header">
        <div class="panel-title">货运管理</div>
        <div class="panel-content">
			<ul>
                <shiro:hasPermission name="购销合同">
				    <li><a href="${ctx}/contract/list" onclick="linkHighlighted(this)" target="main" id="aa_1">购销合同</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="出货表">
				    <li><a href="${ctx}/contract/toEdit" onclick="linkHighlighted(this)" target="main" id="aa_2">出货表</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="报运单">
				    <li><a href="${ctx}/export/contractList" onclick="linkHighlighted(this)" target="main" id="aa_3">报运单</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="出口报运">
				    <li><a href="${ctx}/export/list" onclick="linkHighlighted(this)" target="main" id="aa_4">出口报运</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="装箱管理">
                    <li><a href="${ctx}/packingList/list" onclick="linkHighlighted(this)" target="main" id="aa_5">装箱管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="委托管理">
                    <li><a href="${ctx}/shippingOrder/list" onclick="linkHighlighted(this)" target="main" id="aa_6">委托管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="发票管理">
                    <li><a href="${ctx}/invoice/list" onclick="linkHighlighted(this)" target="main" id="aa_7">发票管理</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="财务管理">
                    <li><a href="${ctx}/finance/list" onclick="linkHighlighted(this)" target="main" id="aa_8">财务管理</a></li>
                </shiro:hasPermission>
			</ul>
        </div>
        </div>
    </div>
    <div class="sidebar_t">
		<div class="sidebar_b_l"></div>
		<div class="sidebar_t_c"></div>
		<div class="sidebar_b_r"></div>
	</div>  
</div>	


</body>
</html>
