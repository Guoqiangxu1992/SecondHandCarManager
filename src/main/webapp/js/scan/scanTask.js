	$(document).ready(function() {
		// 调用分页控件初始化函数
		COMMON_PAGINATION.init("pagination", 10, search);
	});

	
	$(function (){
		search();
	});
/*
	 * 解析json中date对象
	 */
	function formartDate(dataFormate, time) {
		var date = new Date();
		date.setTime(time);
		return date.pattern(dataFormate);
	}

	/*
	 * date格式化
	 */
	Date.prototype.pattern = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, // 月份
			"d+" : this.getDate(), // 日
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
			"H+" : this.getHours(), // 小时
			"m+" : this.getMinutes(), // 分
			"s+" : this.getSeconds(), // 秒
			"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
			"S" : this.getMilliseconds()
		// 毫秒
		};
		var week = {
			"0" : "日",
			"1" : "一",
			"2" : "二",
			"3" : "三",
			"4" : "四",
			"5" : "五",
			"6" : "六"
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		if (/(E+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1,
					((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周")
							: "")
							+ week[this.getDay() + ""]);
		}
		if (/(e+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1,
					((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周")
							: "")
							+ this.getDay());
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	}
	
	
	$('#datetimepicker').datetimepicker({
		language : 'zh-CN',
		autoclose : 1,
		format : 'yyyy-mm-dd',
		weekStart : 5,
		startDate : '2008:12:1',
		endDate : '2020:12:1',
		startView : 2,
		minView : 4,
		todayBtn : 1,
		todayHighlight : 1,
		keyboardNavigation : 1,
		minuteStep : 6,
		showMeridian : 1,
		forceParse : 0
	});

	$('#datetimepicker2').datetimepicker({
		language : 'zh-CN',
		autoclose : 1,
		format : 'yyyy-mm-dd',
		weekStart : 5,
		startDate : '2008:12:1',
		endDate : '2020:12:1',
		startView : 2,
		minView : 4,
		todayBtn : 1,
		todayHighlight : 1,
		keyboardNavigation : 1,
		minuteStep : 6,
		showMeridian : 1,
		forceParse : 0
	});