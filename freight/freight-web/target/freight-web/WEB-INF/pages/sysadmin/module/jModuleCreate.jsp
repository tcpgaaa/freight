<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../base.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" href="${ctx }/components/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
    <link rel="stylesheet" href="${ctx }/components/zTree/css/demo.css" type="text/css">
    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
    <script type="text/javascript">
        var zTreeObj;
        var setting = {
            check: {
                enable: true, //显示checkbox/radio
                chkStyle: "radio",
                radioType: "all"
            },
            data: {
                simpleData: {
                    enable: true//使用简单的数据模式
                }
            },
            callback: {
                onClick: onClick,
                onCheck: onCheck
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
                url:'${ctx}/module/listModuleOfTreeBean',
                type:'post',
                success:initzTree
            });
        });
        function onClick(e, treeId, treeNode) {
            zTreeObj.checkNode(treeNode, !treeNode.checked, null, true);
            return false;
        }
        function onCheck(e, treeId, treeNode) {
            var nodes = zTreeObj.getCheckedNodes(true);
            //选中的节点的名字显示到父级框
            $("#moduleSel").val(nodes[0].name);
            //选中的节点的id赋值到隐藏框
            $("#parentId").val(nodes[0].id);
            //选中的节点的layerNum赋值到层级
            $("#layerNum").val(nodes[0].layerNum);
        }
        function showMenu() {
            var moduleObj = $("#moduleSel");
            var moduleOffset = $("#moduleSel").offset();
            //设置zTree显示的位置
            $("#menuContent").css({left:moduleOffset.left + "px", top:moduleOffset.top + moduleObj.outerHeight() + "px"}).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "moduleSel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                hideMenu();
            }
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function initzTree(zNodes){
            /**
             obj：显示数的heml元素
             setting：树的配置信息
             zNodes：树的节点数据
             */
            zTreeObj = $.fn.zTree.init($("#jTree"), setting, zNodes);
        }
    </script>
</head>

<body>
<form name="icform" method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#"
                                         onclick="formSubmit('${ctx}/module/create','_self');this.blur();">保存</a></li>
                        <li id="back"><a href="#" onclick="history.go(-1);">返回</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    新增模块
                </div>
            </div>
        </div>


        <div>
            <table class="commonTable" cellspacing="1">
                <tr>
                    <td class="columnTitle">父级：</td>
                    <td class="tableContent" colspan="3">
                        <input id="moduleSel" type="text" style="width:150px" readonly="readonly" onclick="showMenu();" />
                        <input id="parentId" name="parentId" type="hidden">
                        &nbsp;<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择父级菜单</a>
                    </td>

                </tr>
                <tr>
                    <td class="columnTitle">模块名：</td>
                    <td class="tableContent"><input type="text" name="name" value=""/></td>
                    <td class="columnTitle">层数：</td>
                    <td class="tableContent"><input type="text" id="layerNum" name="layerNum" value=""/></td>
                </tr>
                <tr>
                    <td class="columnTitle">权限标识：</td>
                    <td class="tableContent"><input type="text" name="cpermission" value=""/></td>
                    <td class="columnTitle">链接：</td>
                    <td class="tableContent"><input type="text" name="curl" value=""/></td>
                </tr>
                <tr>
                    <td class="columnTitle">类型：</td>
                    <td class="tableContentAuto">
                        <input type="radio" name="ctype" value="0" class="input"/>主菜单
                        <input type="radio" name="ctype" value="1" class="input"/>左侧菜单
                        <input type="radio" name="ctype" value="2" class="input"/>按钮
                        <input type="radio" name="ctype" value="3" class="input"/>链接
                    </td>
                    <td class="columnTitle">状态：</td>
                    <td class="tableContentAuto">
                        <input type="radio" name="state" value="1" checked class="input"/>启用
                        <input type="radio" name="state" value="0" class="input"/>停用
                    </td>
                </tr>
                <tr>
                    <td class="columnTitle">说明：</td>
                    <td class="tableContent">
                        <textarea name="remark" style="height:120px;"></textarea>
                    </td>
                    <td class="columnTitle">排序号：</td>
                    <td class="tableContent"><input type="text" name="orderNo" value=""/></td>
                </tr>
            </table>
        </div>

    </div>
    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="jTree" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
    </div>
</form>
</body>
</html>

