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
    <title>用户访问统计</title>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/echarts.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    $(function(){
        $.ajax({
            url:'${ctx}/statChart/listAccessLogData',
            type:'post',
            success:showChars
        });
    });
    function showChars(data) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
       /* var option = {
            //标题
            title : {
                text: '用户访问统计',
                x:'center'
            },
            xAxis: {
                type: 'category',
                data: data.xData
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: data.yData,
                type: 'line'
            }]
        };*/

        var option = {
            title : {
                text: '用户访问统计',
                x:'center'
            },
            xAxis: {
                type: 'category',
                data: data.xData
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: data.yData,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(220, 220, 220, 0.8)'
                }
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
</script>
</body>
</html>