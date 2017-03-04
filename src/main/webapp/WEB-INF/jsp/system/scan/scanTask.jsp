<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<style type="text/css">
.mask_layer {
	position: absolute;
	left: 0;
	top: 0;
	z-index: 810;
	width: 100%;
	height: 100%;
	background-color: #000;
	filter: alpha(opacity = 70);
	opacity: 0.6;
}
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
<script type="text/javascript" src="${xgq }/js/scan/scanTask.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${xgq }/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">

</script>
<script type="text/javascript">
	function search() {
		COMMON_PAGINATION.reset();
		var carName = $('#carName').val().trim();
		var status = $('#status option:selected').val();
		var param = null;
			param = {
				'carName' : carName,
				'status' : status
			};

		COMMON_PAGINATION.initParam(param);//@
		var aj = $
				.ajax({
					url : '${xgq}/scan/getScanTaskList.do',// 跳转到 action    
					type : 'POST',
					dataType : 'json',
					data : param,
					success : function(data) {
						var str = eval(data);
						if (str != '') {
							$("#taskResult").empty();
							$.each(
											str.rows,
											function(n, value) {
												html = "";
												html += '<tr>';
												html += '<td><p class="long_txt">'
														+ value.taskId
														+ '</p></td>';
												html += '<td> <p class="long_txt">'
														+ value.carName
														+ '</p></td>';
												html += '<td><p class="long_txt">'
														+ value.country
														+ '</p></td>';
												html += '<td><p class="long_txt">'
														+ value.ownerName
														+ '</p></td>';
												
												if (value.status == 1) {
													html += '<td><p class="long_txt">'
															+ "已创建"
															+ '</p></td>';
												} else if(value.status == 2){
													html += '<td><p class="long_txt">'
															+ "等待"
															+ '</p></td>';
												}else if(value.status == 3){
													html += '<td><p class="long_txt">'
														+ "执行中"
														+ '</p></td>';
											}else if(value.status == 4){
													html += '<td><p class="long_txt">'
															+ "已完成"
															+ '</p></td>';
												}
												html += '<td><p class="long_txt">'
													+ value.creator
													+ '</p></td>';
												html += '<td><p class="long_txt">'
														+ formartDate(
																"yyyy-MM-dd HH:mm:ss",
																value.updateTime.time)
														+ '</p></td>';
														html += '<td><p class="long_txt">'
															+ formartDate(
																	"yyyy-MM-dd HH:mm:ss",
																	value.createTime.time)
															+ '</p></td>';
												html += '<td color="red "><a href="javascript:startTask('+value.taskId+')"  ><i class="icon-edit" title="启动">启动</i></a></td>';
												html += '</tr>';
												$("#taskResult").append(html);
											});
							COMMON_PAGINATION.refresh(data);
						}
					}
				});
	}
	
	function startTask(taskId){
		 var base1 =' ${xgq }/scan/startScanTask.do';
		 var param = {
				 "taskId":taskId
			}
			 common.loadPage(base1,param);
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
							<label for="title">汽车种类</label> <input type="text"
								placeholder="carName" name="carName" id="carName"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="approvestatus">任务状态</label> <select
								placeholder="任务状态" name="status" id="status"
								class="form-control">
								<option value="">全部</option>
								<option value="1">已添加</option>
								<option value="2">等待</option>
									<option value="3">执行中</option>
										<option value="4">结束</option>
							</select>
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
							<th><i class="icon-bookmark"></i>任务ID</th>
							<th><i class="icon-bookmark"></i>汽车种类</th>
							<th><i class="icon-bookmark"></i>国别</th>
							<th><i class="icon-bookmark"></i>汽车拥有者</th>
							<th><i class="icon-bookmark"></i>任务状态</th>
							<th><i class="icon-bookmark"></i>操作者</th>
							<th><i class="icon-bookmark"></i>任务更新时间</th>
							<th><i class="icon-bookmark"></i>任务添加时间</th>
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
</html>