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
<link href="${xgq }/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<script type="text/javascript" src="${xgq }/js/bootstrap.min.js"></script>
<link href="${xgq }/css/datetimepicker.css" rel="stylesheet"
	media="screen">
<script type="text/javascript"
	src="${xgq }/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
	$(function (){
		search();
	});
</script>
<script type="text/javascript">
	function search() {
		var aj = $
				.ajax({
					url : '${xgq}/server/getServerInfo.do',// 跳转到 action    
					type : 'POST',
					dataType : 'json',
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
														+ value.ip
														+ '</p></td>';
												html += '<td> <p class="long_txt">'
														+ value.totalMemory
														+ '</p></td>';
												html += '<td><p class="long_txt">'
														+ value.usedMemory
														+ '</p></td>';
												html += '<td><p class="long_txt">'
														+ value.freeMemory
														+ '</p></td>';
												
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
				<table class="table table-striped table-advance table-hover">
					<thead>
						<tr>
							<th><i class="icon-bookmark"></i>服务器IP</th>
							<th><i class="icon-bookmark"></i>总内存(M)</th>
							<th><i class="icon-bookmark"></i>已使用内存(M)</th>
							<th><i class="icon-bookmark"></i>空闲内存(M)</th>
							<th><i class="icon-edit"></i> 操作</th>
						</tr>
					</thead>
					<tbody class="taskResult" name="taskResult" id="taskResult">

					</tbody>
				</table>
			</section>
		</div>
	</div>
</body>
</html>