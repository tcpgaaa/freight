<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>厂家销售情况统计</title>
    <script src="${ctx}/js/jquery.min.js"></script>
    <script src="${ctx}/js/echarts.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    $(function(){
        $.ajax({
            url:'${ctx}/statChart/listFactorySaleData',
            type:'post',
            success:showChars
        });
    });
    function showChars(data) {
       // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            //标题
            title : {
                text: '厂家销售情况统计',
                x:'center'
            },
            //提示框
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            //图例组件的排版方式
            legend: {
                orient: 'vertical',
                left: 'left',
            },
            series : [
                {
                    name: '厂家销售情况',//a
                    type: 'pie',
                    radius : '55%',//半径
                    center: ['50%', '60%'],//坐标
                    data:data
                }
            ]
        };





        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
</script>
</body>
</html>
