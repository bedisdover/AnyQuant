/**
 * Created by zmj on 2016/5/21.
 */
var myChart = ec.init(document.getElementById('chart'));
var option = {
    title: {
        text: '2013年上半年上证指数'
    },
    tooltip: {
        trigger: 'axis',
        formatter: function (params) {
            var res = params[0].seriesName + ' ' + params[0].name;
            res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
            res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
            return res;
        }
    },
    legend: {
        data: ['上证指数']
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataZoom: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    dataZoom: {
        show: true,
        realtime: true,
        start: 50,
        end: 100
    },
    xAxis: [
        {
            type: 'category',
            boundaryGap: true,
            axisTick: {onGap: false},
            splitLine: {show: false},
            data: [
                "2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30",
                "2013/1/31", "2013/2/1",

            ]
        }
    ],
    yAxis: [
        {
            type: 'value',
            scale: true,
            boundaryGap: [0.01, 0.01]
        }
    ],
    series: [
        {
            name: '上证指数',
            type: 'k',
            data: [ // 开盘，收盘，最低，最高
                [2320.26, 2302.6, 2287.3, 2362.94],
                [2300, 2291.3, 2288.26, 2308.38],
                [2295.35, 2346.5, 2295.35, 2346.92],
                [2347.22, 2358.98, 2337.35, 2363.8],
                [2360.75, 2382.48, 2347.89, 2383.76],
                [2383.43, 2385.42, 2371.23, 2391.82],
                [2377.41, 2419.02, 2369.57, 2421.15],

            ]
        }
    ]
};
myChart.setOption(option);