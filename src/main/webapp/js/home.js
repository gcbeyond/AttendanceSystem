/**
 * Created by Administrator on 2017/11/8.
 */
$("#calder").calendar({
        height:200

})
$("#rightTab").tabs({
       height:250,
        border:false
})
$(function(){
        var myChart = echarts.init($("#chart01")[0]);
//app.title = '堆叠柱状图';

        option = {

                tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {

                        left: 'left',
                        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
                },
                series : [
                        {
                                name: '访问来源',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:[
                                        {value:335, name:'直接访问'},
                                        {value:310, name:'邮件营销'},
                                        {value:234, name:'联盟广告'},
                                        {value:135, name:'视频广告'},
                                        {value:1548, name:'搜索引擎'}
                                ],
                                itemStyle: {
                                        emphasis: {
                                                shadowBlur: 10,
                                                shadowOffsetX: 0,
                                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        }
                                }
                        }
                ]
        };
        myChart.setOption(option);
});
$(function(){
        var myChart = echarts.init($("#chart02")[0]);
//app.title = '堆叠柱状图';

        option = {

                tooltip: {
                        trigger: 'axis'
                },
                legend: {
                        data:['最高气温','最低气温']
                },

                xAxis:  {
                        type: 'category',
                        boundaryGap: false,
                        data: ['周一','周二','周三','周四','周五','周六','周日']
                },
                yAxis: {
                        type: 'value',
                        axisLabel: {
                                formatter: '{value} °C'
                        }
                },
                series: [
                        {
                                name:'最高气温',
                                type:'line',
                                data:[11, 11, 15, 13, 12, 13, 10],
                                markPoint: {
                                        data: [
                                                {type: 'max', name: '最大值'},
                                                {type: 'min', name: '最小值'}
                                        ]
                                },
                                markLine: {
                                        data: [
                                                {type: 'average', name: '平均值'}
                                        ]
                                }
                        },
                        {
                                name:'最低气温',
                                type:'line',
                                data:[1, -2, 2, 5, 3, 2, 0],
                                markPoint: {
                                        data: [
                                                {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                                        ]
                                },
                                markLine: {
                                        data: [
                                                {type: 'average', name: '平均值'},
                                                [{
                                                        symbol: 'none',
                                                        x: '90%',
                                                        yAxis: 'max'
                                                }, {
                                                        symbol: 'circle',
                                                        label: {
                                                                normal: {
                                                                        position: 'start',
                                                                        formatter: '最大值'
                                                                }
                                                        },
                                                        type: 'max',
                                                        name: '最高点'
                                                }]
                                        ]
                                }
                        }
                ]
        };
        myChart.setOption(option);
});
$(function(){
        var myChart = echarts.init($("#chart03")[0]);
//app.title = '堆叠柱状图';

        option = {
                tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                },
                legend: {
                        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎','百度','谷歌','必应','其他']
                },
                grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                },
                xAxis : [
                        {
                                type : 'category',
                                data : ['周一','周二','周三','周四','周五','周六','周日']
                        }
                ],
                yAxis : [
                        {
                                type : 'value'
                        }
                ],
                series : [
                        {
                                name:'直接访问',
                                type:'bar',
                                data:[320, 332, 301, 334, 390, 330, 320]
                        },
                        {
                                name:'邮件营销',
                                type:'bar',
                                stack: '广告',
                                data:[120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                                name:'联盟广告',
                                type:'bar',
                                stack: '广告',
                                data:[220, 182, 191, 234, 290, 330, 310]
                        },
                        {
                                name:'视频广告',
                                type:'bar',
                                stack: '广告',
                                data:[150, 232, 201, 154, 190, 330, 410]
                        },
                        {
                                name:'搜索引擎',
                                type:'bar',
                                data:[862, 1018, 964, 1026, 1679, 1600, 1570],
                                markLine : {
                                        lineStyle: {
                                                normal: {
                                                        type: 'dashed'
                                                }
                                        },
                                        data : [
                                                [{type : 'min'}, {type : 'max'}]
                                        ]
                                }
                        },
                        {
                                name:'百度',
                                type:'bar',
                                barWidth : 5,
                                stack: '搜索引擎',
                                data:[620, 732, 701, 734, 1090, 1130, 1120]
                        },
                        {
                                name:'谷歌',
                                type:'bar',
                                stack: '搜索引擎',
                                data:[120, 132, 101, 134, 290, 230, 220]
                        },
                        {
                                name:'必应',
                                type:'bar',
                                stack: '搜索引擎',
                                data:[60, 72, 71, 74, 190, 130, 110]
                        },
                        {
                                name:'其他',
                                type:'bar',
                                stack: '搜索引擎',
                                data:[62, 82, 91, 84, 109, 110, 120]
                        }
                ]
        };
        myChart.setOption(option);
});
function init() {

}
function notic() {
        $.ajax({
                url:'',
                type:'post',
                dataType:'json',
                success:function (data) {
                        if(data){
                                var res=data.data;
                                $.each(res,function (index) {
                                        var li='<li>'+res[index].value+'</li>';
                                        $("#notic").append(li);

                                })
                        }

                }
        })

}