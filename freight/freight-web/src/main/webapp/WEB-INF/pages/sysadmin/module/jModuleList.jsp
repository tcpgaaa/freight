<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script>
        function isOnlyChecked() {
            var checkBoxArray = document.getElementsByName('id');
            var count = 0;
            for (var index = 0; index < checkBoxArray.length; index++) {
                if (checkBoxArray[index].checked) {
                    count++;
                }
            }
            //jquery
            //var count = $("[input name='id']:checked").size();
            if (count == 1)
                return true;
            else
                return false;
        }

        function toView() {
            if (isOnlyChecked()) {
                formSubmit('${ctx}/module/toView', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //实现更新
        function toUpdate() {
            if (isOnlyChecked()) {
                formSubmit('${ctx}/module/toUpdate', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }


    </script>
</head>

<body>
<form id="frm" action="${ctx}/module/list" method="post">

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="view"><a href="#"
                                         onclick="toView();this.blur();">查看</a></li>
                        <li id="new"><a href="#"
                                        onclick="formSubmit('${ctx}/module/toCreate','_self');this.blur();">新增</a></li>
                        <li id="update"><a href="#" onclick="toUpdate();this.blur();">修改</a>
                        </li>
                        <li id="delete"><a href="#"
                                           onclick="formSubmit('${ctx}/module/delete','_self');this.blur();">删除</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    模块列表
                </div>
            </div>
        </div>
    </div>
    <div>


        <div class="eXtremeTable">
            <jsp:include page="${ctx}/common/page.jsp"></jsp:include>
            <table id="ec_table" class="tableRegion" width="98%">
                <thead>
                <tr>
                    <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                    <td class="tableHeader">模块名</td>
                    <td class="tableHeader">层数</td>
                    <td class="tableHeader">权限标识</td>
                    <td class="tableHeader">链接</td>
                    <td class="tableHeader">类型</td>
                    <td class="tableHeader">从属</td>
                    <td class="tableHeader">状态</td>
                </tr>
                </thead>
                <tbody class="tableBody">
                <c:forEach items="${pb.datas}" var="o" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                        <td><input type="checkbox" name="id" value="${o.moduleId}"/></td>
                        <td>${o.name}</td>
                        <td>${o.layerNum}</td>
                        <td>${o.cpermission}</td>
                        <td>${o.curl}</td>
                        <td>${o.ctype}</td>
                        <td>${o.belong}</td>
                        <td>${o.state==1?'启用':'停用'}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>


</form>
</body>
</html>

