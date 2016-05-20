<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 16-5-20
  Time: 下午3:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AnyQuant</title>
    <meta name="description" content="AnyQuant是一个在线电话交易平台"/>
    <meta name="keyword" content="AnyQuant,股票,电话交易"/>
    <meta name="author" content="Ultraviolet"/>
    <link href="../images/icon.png" rel="icon"/>
</head>
<body>
<div id="main"></div>
<!-- ECharts单文件引入 -->
<script src="../page/js/dist/echarts.js"></script>
<script type="text/javascript" src="../page/js/radarchart.js"></script>

<script>
    // 路径配置
    require.config({
        paths: {
            echarts: '../page/js/dist'
        }
    });
    // 使用
    require(
            [
                'echarts',
                'echarts/chart/radar' // 使用雷达图就加载radar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'), 'helianthus');

                var option = {
                    title: {
                        text: '华夏银行 vs 南京银行',
                        subtext: '银行股指标对比'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        x: 'center',
                        data: ['华夏银行', '南京银行']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: false},
                            dataView: {show: false, readOnly: false},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    polar: [
                        {
                            indicator: [
                                {text: '乖离率', max: 100},
                                {text: '相对强弱指标', max: 100},
                                {text: '威廉超买超卖指标', max: 100},
                                {text: '人气指标', max: 100},
                                {text: '意愿指标', max: 100}
                            ],
                            radius: 130
                        }
                    ],
                    series: [
                        {
                            name: '银行股指标数据',
                            type: 'radar',
                            itemStyle: {
                                normal: {
                                    areaStyle: {
                                        type: 'default'
                                    }
                                }
                            },
                            data: [
                                {
                                    value: [97, 42, 88, 94, 90],
                                    name: '华夏银行'
                                },
                                {
                                    value: [97, 32, 74, 95, 88],
                                    name: '南京银行'
                                }
                            ]
                        }
                    ]
                };

                // 为echarts对象加载数据
                myChart.setOption(option);
            }
    );
</script>

</body>
</html>
