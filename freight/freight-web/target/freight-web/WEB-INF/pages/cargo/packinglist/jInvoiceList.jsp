<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script>
        function isOnlyChecked() {
            var checkBoxArray = document.getElementsByName('packingListId');
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
                formSubmit('${ctx}/invoice/toView', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //实现更新
        function toUpdate() {
            if (isOnlyChecked()) {
                formSubmit('${ctx}/invoice/toUpdate', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //实现取消
        function toCal() {
            if (isOnlyChecked()) {
                formSubmit('${ctx}/invoice/cancel', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //提交
        function toSub() {
            if (isOnlyChecked()) {
                formSubmit('${ctx}/invoice/submit', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
        }

        //发票
        function toInvoice() {
            if (isOnlyChecked()) {
                formSubmit('${ctx}/invoice/invoice', '_self');
            } else {
                alert("请先选择一项并且只能选择一项，再进行操作！");
            }
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
                        <li id="view"><a href="#" onclick="toView();this.blur();">查看</a>
                        </li>
                        <li id="delete"><a href="#"
                                           onclick="toInvoice();this.blur();">发票</a>
                        </li>
                        <li id="view"><a href="#"
                                         onclick="toSub();this.blur();">提交</a>
                        </li>
                        <li id="view"><a href="#"
                                         onclick="toCal();this.blur();">取消</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox-title">
        <img src="${ctx }/skin/default/images/icon/currency_yen.png"/>
        装箱单列表
    </div>

    <div>


        <div class="eXtremeTable">
            <jsp:include page="${ctx}/common/page.jsp"></jsp:include>
            <table id="ec_table" class="tableRegion" width="98%">
                <thead>
                <tr>
                    <td class="tableHeader"><input type="checkbox" name="selid" onclick="checkAll('packingListId',this)"></td>
                    <td class="tableHeader">卖方</td>
                    <td class="tableHeader">买方</td>
                    <td class="tableHeader">发票号</td>
                    <td class="tableHeader">发票日期</td>
                    <td class="tableHeader">状态</td>
                    <td class="tableHeader">发票状态</td>
                </tr>
                </thead>
                <tbody class="tableBody">


                <c:forEach items="${pb.datas}" var="o" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'">
                        <td><input type="checkbox" name="packingListId" value="${o.packingListId}#${o.exportNos}"/></td>
                        <td>${o.seller}</td>
                        <td>${o.buyer}</td>
                        <td>${o.invoiceNo}</td>
                        <td><fmt:formatDate value="${o.invoiceDate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                        <td>
                            <c:if test="${o.state==0}">草稿</c:if>
                            <c:if test="${o.state==1}"><b><font color="green">已上报</font></b></c:if>
                        </td>
                        <td>
                            <c:if test="${o.invoiceState==0}">草稿</c:if>
                            <c:if test="${o.invoiceState==1}"><b><font color="green">已上报</font></b></c:if>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>

    </div>


</form>
</body>
</html>

