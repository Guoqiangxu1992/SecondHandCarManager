
$(function (){
	draw();
}); 
var dataArray = [Math.random() * 150];
var date = [];
function getmemory(){
	var url =' ${xgq }/server/getMemoryData.do';
 	var aj = $.ajax( {    
	    url:url,// 跳转到 action    
	    type:'POST',    
	    dataType:'json',  
	    success:function(data) {
	    	var data = data;
	    	alert(data);
	    	dataArray.push(data);
	    }
 	});
}

function draw(){
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));
	var oneDay = 24 * 3600 * 1000;
	var date = [];
	
	var now = new Date();

	function addData(shift) {
		var now = new Date();
	    now1 = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/');
		now2 = [now.getHours(),now.getMinutes(),now.getSeconds()].join(':');
		now = now1+' '+now2;
	    date.push(now);
	    getmemory();
	    /*if (shift) {
	        date.shift();
	        dataArray.shift();
	    }*/
	    
	}

	var option = {
			 title: {
			        text: '使用内存(M)'
			    },
		    xAxis: {
		    	name:'时间',
		        type: 'category',
		        boundaryGap: false,
		        data: date
		    },
		    yAxis: {
		    	name:'使用',
		        boundaryGap: [0, '50%'],
		        type: 'value'
		    },
		    series: [
		        {
		            name:'使用',
		            type:'line',
		            smooth:true,
		            symbol: 'none',
		            stack: 'a',
		            areaStyle: {
		                normal: {}
		            },
		            data: dataArray
		        }
		    ]
		};
	
		setInterval(function () {
			addData(true);
			myChart.hideLoading();
			myChart.setOption(option);
		}, 1000);
}



