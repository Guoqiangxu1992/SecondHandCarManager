<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>

<title>pingdao</title>
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
<%-- <script type='text/javascript' src="${xgq }/js/car/carEdit.js"></script> --%>

<script type="text/javascript">
 $(function (){
	// 调用分页控件初始化函数
	COMMON_PAGINATION.init("pagination",10, search);
    search();
    startGetMessage();

}); 
 
 function addJobInfo(){
	 var base =' ${xgq }/quartz/addJobInfo.do';
		var param = {
		}
		common.loadPage(base,param);
} 

 
 function managerJob(jobId,status){
	 var base1 =' ${xgq }/quartz/managerJob.do';
	 var param = {
			 "jobId":jobId
		}
		 common.loadPage(base1,param);
 }
 
 function startGetMessage(jobId,status){
	 var base1 =' ${xgq }/message/startGetMessage.do';
	 var param = {
		}
		 common.loadPage(base1,param);
 }
 
 function managerCron(jobid,cron){
	 $("#cronExpression").val(cron);
	 $("#jobId").val(jobid);
 }
 
 
 function submitForm(){
     if($('#cronExpression').val()==''){
    	 common.alertMes("cron表达式不合法",null);
			return false;
     }	 
     var data = $("#editJobform").serialize();
 	var url =' ${xgq }/quartz/editJob.do';
 	var aj = $.ajax( {    
	    url:url,// 跳转到 action    
	    type:'POST',    
	    dataType:'json',  
	    data:data,  
	    success:function(data) {
	    	if(data==1){
	    		$("#myModal_editrole_close").click();
	    		common.loadPage('${xgq }/quartz/job.do', null);
	    	}
	    }
 	});
 	return ;
 }
 
 
function search(){
	var param = {
		}; 
	COMMON_PAGINATION.initParam(param);//@
	var aj = $.ajax( {    
	    url:'${xgq}/message/getChannelList.do',// 跳转到 action    
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
	     	            html += '<td> <p class="long_txt">'+value.channelId+'</p></td>';
	     				html += '<td><p class="long_txt">'+value.channelName+'</p></td>';
	     				if(value.status==1){
	     					html += '<td><a color="red" href="javascript:managerJob('+value.jobId+','+value.jobStatus+')" class="long_txt">'+"开启"+'</a></td>';
	     				}else
	     					{
	     					html += '<td color="red "><a color="red " href="javascript:managerJob('+value.jobId+','+value.jobStatus+')"  class="long_txt">'+"关闭"+'</a></td>';
	     					}
	     					html+='<td><button href="#myModal_editrole" data-toggle="modal"  onclick="managerCron('+value.jobId+',\''+value.cronExpression+'\');" class="btn btn-info btn-xs"><i class="icon-edit">编辑</i></button></td>';
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
								type="button" onclick="addJobInfo('${xgq}')">增加</button>
						</div>
					</div>
				</header>
				<div class="panel-body" style="display: none;">
					<form id="myForm" role="form" class="form-inline" method="post">
						<input type="hidden" name="" value="" />
						<div class="form-group">
							<label for="title">任务组</label> <input type="text"
								placeholder="汽车种类" name="carName" id="carName"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="approvestatus">运行状态</label> <select name="variableBox"
								id="variableBox" class="form-control">
								<option value="">全部</option>
								<option value="1">开启</option>
								<option value="2">关闭</option>
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
							<th><i class="icon-bookmark"></i>ID</th>
							<th><i class="icon-bookmark"></i>CHANNEL_ID</th>
							<th><i class="icon-bookmark"></i>CHANNEL_NAME</th>
							<th><i class="icon-bookmark"></i>STATUS</th>
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
	
	
	
<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal_editrole" class="modal fade in" >
  <div class="modal-dialog">
	  <div class="modal-content">
		  <div class="modal-header">
			  <button aria-hidden="true" id="myModal_editrole_close"  data-dismiss="modal" class="close" type="button" >×</button>
			  <h4 class="modal-title" id="roleTitle">编辑cronExpression</h4>
		  </div>
		  <div class="modal-body">
			  <form role="form" class="form-horizontal" id="editJobform" >
              <div class="form-group">
                   <label class="col-lg-2 col-sm-2 control-label" >任务ID</label>
                   <div class="col-lg-10">
                       <input type="text" placeholder="" id="jobId" name="jobId" class="form-control">
                   </div>
               </div>
                <div class="form-group">
                   <label class="col-lg-2 col-sm-2 control-label" >cron表达式</label>
                   <div class="col-lg-10">
                       <input type="text" placeholder="" id="cronExpression" name="cronExpression" class="form-control">
                   </div>
               </div>
				  <div class="form-group">
				  <div class="col-lg-5" ></div>
				  		<div class="col-lg-5" >
						  <button class="btn btn-primary" type="button" onclick="submitForm();">确认</button>
						  </div>
				  </div>
			  </form>
		  </div>
	  </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>	
</body>
<script type="text/javascript">
$(document).ready(function() {
	// 调用分页控件初始化函数
	COMMON_PAGINATION.init("pagination",10, search);
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