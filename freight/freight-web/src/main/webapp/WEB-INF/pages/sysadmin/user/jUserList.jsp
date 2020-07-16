<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title></title>
    <script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
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
                formSubmit('${ctx}/user/toView', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //实现更新
        function toUpdate() {
            if (isOnlyChecked()) {
                formSubmit('${ctx}/user/toUpdate', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //实现角色管理
        function toRole() {
            if (isOnlyChecked()) {
                formSubmit('${ctx}/user/toRole', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //实现删除
        function toDelete(){
            //获取选中的用户id
            var userIds  = new Array();
            var checkBoxs = $("input[name='id']:checked");
            if(checkBoxs.length==0){
                alert("请先选择一项，再进行操作！");
                return;
            }
            for (var i = 0; i <checkBoxs.length ; i++) {
                userIds.push(checkBoxs[i].value);
            }
            userIds = userIds.join(",");//1,2,3,4
            //ajax发出删除的请求
            $.ajax({
                type:"post",
                url:"${ctx}/user/delete",
                data:{"userIds":userIds},
                success:function(data){
                    if(data.status==200){
                        alert(data.message);
                    }else{
                        alert("名下有员工，不能删除：\n"+data.data);
                    }
                    formSubmit('/user/list','_self');
                }
            });

        }

        function exportData(){
            $("#frm").prop("action","${ctx}/user/exportUser")
            $("#frm").submit();
        }
    </script>
</head>

<body>
<form id="frm" action="${ctx}/user/list" method="post">
    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="view"><a href="javascript:toView()">查看</a></li>
                        <li id="new"><a href="#" onclick="formSubmit('${ctx}/user/toCreate','_self');this.blur();">新增</a>
                        </li>
                        <li id="update"><a href="#" onclick="toUpdate()">修改</a></li>
                        <li id="people"><a href="#" onclick="toRole()">角色</a></li>
                        <li id="delete"><a href="#" onclick="toDelete();this.blur();">删除</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    用户列表
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
                    <td class="tableHeader">用户名</td>
                    <td class="tableHeader">状态</td>
                </tr>
                </thead>
                <tbody class="tableBody">
                <c:forEach items="${pb.datas}" var="obj" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                        <td><input type="checkbox" name="id" value="${obj.userId}"/></td>
                        <td>${obj.userName}</td>
                        <td>${obj.state==1?'启用':'停用'}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>


</form>
</body>
</html>

