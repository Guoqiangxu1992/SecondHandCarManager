<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<style type="text/css">
		.mask_layer { position: absolute; left: 0; top: 0; z-index: 810; width: 100%; height: 100%; background-color: #000; filter: alpha(opacity=70); opacity: 0.6; }
</style>
<title>普通用户列表</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
<%-- <script type="text/javascript"
	src="${xgq }/common/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${xgq }/common/bootstrap/bootstrap-timepicker/js/bootstrap-timepicker.js"></script> --%>
<script type="text/javascript"
	src="${xgq}/common/bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type='text/javascript'
	src="${xgq }/js/paginate/jquery.myPagination.js"></script>
<script type='text/javascript'
	src="${xgq }/js/paginate/commonPagination.js"></script>
<script type='text/javascript' src="${xgq }/js/user/userEdit.js"></script>
<link href="${xgq }/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<script type="text/javascript" src="${xgq }/js/bootstrap.min.js"></script>
<link href="${xgq }/css/datetimepicker.css" rel="stylesheet"
	media="screen">
<%-- <script type="text/javascript" src="${xgq }/js/bootstrap-datetimepicker.js" charset="UTF-8"></script> --%>
<script type="text/javascript"
	src="${xgq }/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
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
</script>
<script type="text/javascript">
	$('#datetimepicker').datetimepicker().on('changeDate', function(ev) {

		var nowDate = new Date();
		var selectDate = $('#datetimepicker').val();
		var select = new Date(selectDate);
		//alert(nowDate+','+select);
		if (select > nowDate) {
			alert(888)
		}
	});

	function DateFormat(time, format) {
		var t = new Date(time);
		var tf = function(i) {
			return (i < 10 ? '0' : '') + i
		};
		return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
			switch (a) {
			case 'yyyy':
				return tf(t.getFullYear());
				break;
			case 'MM':
				return tf(t.getMonth() + 1);
				break;
			case 'mm':
				return tf(t.getMinutes());
				break;
			case 'dd':
				return tf(t.getDate());
				break;
			case 'HH':
				return tf(t.getHours());
				break;
			case 'ss':
				return tf(t.getSeconds());
				break;
			}
		})
	}
	/* $(function(){
	 $(".form_date").datepicker({
	 format: "yyyy-mm",
	 weekStart:1,
	 startView:3,
	 minView:3,
	 forceParse:false,
	 lanague:'zh-cn',
	 autoclose: true,
	 });
	
	 }); */
	function search() {
		COMMON_PAGINATION.reset();
		var loginName = $('#loginName').val().trim();
		var roleId = $('#roleId option:selected').val();
		var startTime = null;
		var endTime = null;
		var param =null;
	
		if($('#datetimepicker').val()!=''&&$('#datetimepicker').val()!=null){
			  startTime = new Date($('#datetimepicker').val());
		}
		if($('#datetimepicker2').val()!=''&&$('#datetimepicker2').val()!=null){
			var endTime = new Date($('#datetimepicker2').val());
		}
		if($('#datetimepicker2').val()!=''&&$('#datetimepicker2').val()!=null){
			 param = {
					'loginName':loginName,
					'roleId':roleId,
					'startTime':startTime,
					'endTime':endTime
				};
		}else{
			 param = {
					'loginName':loginName,
					'roleId':roleId
				};
		}
	
		COMMON_PAGINATION.initParam(param);//@
		var aj = $
				.ajax({
					url : '${xgq}/user/loginUserList.do',// 跳转到 action    
					type : 'POST',
					dataType : 'json',
					data : param,
					success : function(data) {
						var str = eval(data);
						if (str != '') {
							$("#taskResult").empty();
							$
									.each(
											str.rows,
											function(n, value) {
												html = "";
												html += '<tr>';
												html += '<td><p class="long_txt">'
														+ value.id
														+ '</p></td>';
												html += '<td> <p class="long_txt">'
														+ value.loginName
														+ '</p></td>';
												html += '<td><p class="long_txt">'
														+ value.name
														+ '</p></td>';
												html += '<td><p class="long_txt">'
														+ value.phone
														+ '</p></td>';
												html += '<td><p class="long_txt">'
														+ value.email
														+ '</p></td>';
												if (value.roleId == 1) {
													html += '<td><p class="long_txt">'
															+ "管理员"
															+ '</p></td>';
												} else {
													html += '<td><p class="long_txt">'
															+ "普通用户"
															+ '</p></td>';
												}
												html += '<td><p class="long_txt">'
														+formartDate("yyyy-MM-dd HH:mm:ss",
																value.makeTime.time) + '</p></td>';
												html += '<td><button class="btn btn-primary btn-xs"onclick="editArticle(${p.id});"><i class="icon-edit" title="编辑">编辑</i></button></td>';
												html += '</tr>';
												$("#taskResult").append(html);
											});
							COMMON_PAGINATION.refresh(data);
						}
					}
				});
	}
	 
</script>
<link rel="stylesheet" type="text/css"
	href="${xgq }/js/paginate/myPagination.css" />
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading">
					<span class="tools pull-left"> <a class="icon-search"
						style="padding: 10px;" href="javascript:void(0);"
						onclick="goUp(this);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
					</span>
					<div class="clearfix">
						<div class="pull-right">
							<span class="label btn label-success green"
								onclick="editMenu('');">&nbsp;增加&nbsp;<i
								class="icon-plus"></i>&nbsp;
							</span>
						</div>
					</div>
				</header>
				<div class="panel-body" style="display: none;">
					<form id="myForm" role="form" class="form-inline" method="post">
						<input type="hidden" name="" value="" />
						<div class="form-group">
							<label for="title">登录名</label> <input type="text"
								placeholder="loginName" name="loginName" id="loginName"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="approvestatus">用户角色</label>
							 <select placeholder="用户角色" name="roleId" id="roleId"class="form-control">
							 <option value="0">全部</option>
								<option value="1">管理员</option>
								<option value="2">普通用户</option>
							</select>
						</div>

						<div class="form-group date " style="width: 150px">
							<label>注册日期开始于</label><input class="form-control" type="text"
								id="datetimepicker">
						</div>
						<div class="form-group date " style="width: 150px">
							<label>注册结束 </label><input class="form-control" type="text"
								id="datetimepicker2">
						</div>

						<button style="margin-top: 23px;" class="btn btn-success"
							type="button" onclick="search()">查找</button>
						<button style="margin-top: 23px;" class="btn btn-default"
							type="button" onclick="reset()">重置</button>
					</form>
				</div>
				<table class="table table-striped table-advance table-hover">
					<thead>
						<tr>
							<th><i class="icon-bookmark"></i>用户ID</th>
							<th><i class="icon-bookmark"></i>登录名</th>
							<th><i class="icon-bookmark"></i>真实姓名</th>
							<th><i class="icon-bookmark"></i>电话</th>
							<th><i class="icon-bookmark"></i>邮箱</th>
							<th><i class="icon-bookmark"></i>用户角色</th>
							<th><i class="icon-bookmark"></i>注册时间</th>
							<th><i class="icon-edit"></i> 操作</th>
						</tr>
					</thead>
					<tbody class="taskResult" name="taskResult" id="taskResult">

					</tbody>
				</table>
			</section>
		</div>
	</div>
	<div class="table_handle page_box clearfix" style="float: right">
		<div class="page-model clearfix financial-record-page fr">
			<div class="yhdbk-page clearfix">
				<div id="pagination" class="pg_right"></div>
			</div>
		</div>
	</div>

	<!-- 主体内容结束 -->
	<!-- 分页 -->
	<input type="hidden" value="searchMenuForm" id="currentFormId" />

</body>
<script type="text/javascript">
	$(document).ready(function() {
		// 调用分页控件初始化函数
		COMMON_PAGINATION.init("pagination", 10, search);
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
</script>
</html>