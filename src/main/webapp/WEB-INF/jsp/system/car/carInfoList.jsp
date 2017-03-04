<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>

<title>普通用户列表</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
<script type="text/javascript"
	src="${xgq }/common/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
	src="${xgq }/common/bootstrap/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript"
	src="${xgq}/common/bootstrap/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type='text/javascript'
	src="${xgq }/js/paginate/jquery.myPagination.js"></script>
<script type='text/javascript'
	src="${xgq }/js/paginate/commonPagination.js"></script>
<%-- <script type='text/javascript' src="${xgq }/js/myCommon.js"></script> --%>
 <script type='text/javascript' src="${xgq }/js/car/carEdit.js"></script> 

<script type="text/javascript">
 $(function (){
	// 调用分页控件初始化函数
	COMMON_PAGINATION.init("pagination",10, search);
    search();

}); 
$(function(){
	$(".form_date").datepicker({
	    format: "yyyy-mm-dd",
	    showMeridian: true,
	    autoclose: true,
	    todayBtn: true,
	    startDate: "2015-02-14 ",
	});
	
});

function uploadPicture(carId){
	//alert(carId);
	var base =' ${xgq }/car/uploadCarInfoPic.do';
	var param = {
			carId:carId
	}
	common.loadPage(base,param);
}

 function addCarInfo(){
	 var base =' ${xgq }/car/addCarInfo.do';
		var param = {
				//carId:carId
		}
		common.loadPage(base,param);
} 
 
 function exportCarInfo(){
	 var url="${xgq }/car/exportCarInfoExcel.do";
     window.open(url);
 }
 
 
 function officeexportCarInfo(){
	 var url="${xgq }/car/test.do";
     window.open(url);
     async.setTimeout(0);
 }
function search(){
	//COMMON_PAGINATION.reset();
	// var loginName = $('#carName').val().trim();
	//var roleId = $('#variableBox option:selected') .val();
	//var startTime = $('#startTime').val();
	//var endTime = $('#endTime').val();
	var param = {
			// 'carInfoDto.carName': carName,
			//'carInfoDto.variableBox' : variableBox
		}; 
	COMMON_PAGINATION.initParam(param);//@
	var aj = $.ajax( {    
	    url:'${xgq}/car/getCarInformation.do',// 跳转到 action    
	    type:'POST',    
	    dataType:'json',  
	    data:param,  
	    success:function(data) {
	    	var str = eval(data);
	    	 if(str!=''){
        		 $("#taskResult").empty();
        		 $.each(str.rows,function(n,value){
        			    html = "";
	     	            html += '<tr>';
	     	            html += '<td><p class="long_txt">'+value.id+'</p></td>';
	     	            html += '<td> <p class="long_txt">'+value.carName+'</p></td>';
	     				html += '<td><p class="long_txt">'+value.carType+'</p></td>';
	     				html += '<td><p class="long_txt">'+value.travelAge+'</p></td>';
	     				if(value.variableBox==1){
	     					html += '<td><p class="long_txt">'+"手动挡"+'</p></td>';
	     				}else
	     					{
	     					html += '<td><p class="long_txt">'+"自动挡"+'</p></td>';
	     					}
	     				html += '<td><p class="long_txt">'+value.displaceMent+'</p></td>';
	     				html += '<td><p class="long_txt">'+value.color+'</p></td>';
	     				html += '<td><p class="long_txt">'+value.country+'</p></td>';
	     				html += '<td><p class="long_txt">'+value.price.carPrice+'</p></td>';
	     				html += '<td><p class="long_txt">'+value.registerUser.name+'</p></td>';
	     				html += '<td><p class="long_txt">'+value.carAge+'</p></td>';
	     					html += '<td><p class="long_txt">'+formartDate("yyyy-MM-dd HH:mm:ss",
									value.carDate.time)+'</p></td>';
	     					html+='<td><button class="btn btn-primary btn-xs" onclick="uploadPicture('+value.id+')"><i class="icon-edit" title="编辑">主图上传</i></button></td>';
	     					html+='<td><button class="btn btn-primary btn-xs"onclick="editArticle(${p.id});"><i class="icon-edit" title="编辑">编辑</i></button></td>';
	     				    html +='</tr>';
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
						<button style="margin-top: 23px;" class="btn btn-success"
								type="button" onclick="exportCarInfo('${xgq}')">导出</button>
							<button style="margin-top: 23px;" class="btn btn-success"
								type="button" onclick="addCarInfo('${xgq}')">增加</button>
								<button style="margin-top: 23px;" class="btn btn-success"
								type="button" onclick="officeexportCarInfo('${xgq}')">离线下载</button>
						</div>
					</div>
				</header>
				<div class="panel-body" style="display: none;">
					<form id="myForm" role="form" class="form-inline" method="post">
						<input type="hidden" name="" value="" />
						<div class="form-group">
							<label for="title">汽车种类</label> <input type="text"
								placeholder="汽车种类" name="carName" id="carName"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="approvestatus">变速箱</label> <select name="variableBox"
								id="variableBox" class="form-control">
								<option value="0">全部</option>
								<option value="1">手动</option>
								<option value="2">自动</option>
							</select>
						</div>

						<div class="form-group date " style="width: 150px"
							data-date="2012-12-21T15:25:00Z">
							<label>注册日期开始于</label> <input type="text" size="16"
								name="startTime" id="startTime" class="form-control form_date">
						</div>
						<div class="form-group date form_datetime-meridian"
							style="width: 150px">
							<label>注册日期结束于</label> <input type="text" size="16"
								name="EndTime" id="endTime" class="form-control form_date">
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

							<th><i class="icon-bookmark"></i>汽车编号</th>
							<th><i class="icon-bookmark"></i>汽车种类</th>
							<th><i class="icon-bookmark"></i>汽车类型</th>
							<th><i class="icon-bookmark"></i>行驶里程</th>
							<th><i class="icon-bookmark"></i>变速箱</th>
							<th><i class="icon-bookmark"></i>排量</th>
							<th><i class="icon-bookmark"></i>颜色</th>
							<th><i class="icon-bookmark"></i>国别</th>
							<th><i class="icon-bookmark"></i>汽车价格</th>
							<th><i class="icon-bookmark"></i>汽车拥有者</th>
							<th><i class="icon-bookmark"></i>车龄</th>
							<th><i class="icon-bookmark"></i>上牌时间</th>
							<th><i class="icon-bookmark"></i>主图上传</th>
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
	COMMON_PAGINATION.init("pagination",10, search);
});

</script>
</html>