<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>新增汽车信息</title>
<%@ include file="/WEB-INF/common/common.jsp"%>
<script type="text/javascript" charset="utf-8"
	src="${xgq }/common/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${xgq }/common/ueditor/ueditor.all.js"> </script>
<link rel="stylesheet" type="text/css"
	href="${xgq }/common/system/assets/bootstrap-fileupload/bootstrap-fileupload.css" />
<script type="text/javascript"
	src="${xgq }/common/system/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
<script type="text/javascript"
	src="${xgq }/common/system/assets/fuelux/js/spinner.min.js"></script>
	   <script type='text/javascript' src="${xgq }/js/myCommon.js"></script> 
<script type="text/javascript">

	function submitform(){
		var d = $("#jobInfoform").serialize();
		var url =  " ${xgq}/quartz/saveJobInfo.do";
		common.submitForm2Path('jobInfoform',url);
		return ;
	}
	
	function goBack(){
		 var base =' ${xgq }/quartz/job.do';
			var param = {
			}
			common.loadPage(base,param);
	}
	</script>
	
</head>
<body>
	<!-- 主体内容开始 -->
	<div class="row">
		<div class="col-lg-12">
			<section class="panel">
				<header class="panel-heading">
					<span class="label label-success label-mini"><b>添加任务</b></span>
				</header>
				<div class="panel-body">
					<form class="form-horizontal tasi-form" role="form"
						id="jobInfoform">
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><b>任务名称</b></label>
							<div class="col-sm-10">
								<input type="text" id="jobName" name="jobName" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><b>任务组</b></label>
							<div class="col-sm-10">
								<input type="text" id="jobGroup" name="jobGroup"
									class="form-control">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><b>con表达式</b></label>
							<div class="col-sm-10">
							<input type="text" id="cronExpression" name="cronExpression"
									class="form-control">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label"><b>任务描述</b></label>
							<div class="col-sm-10">
							<input type="text" id="desc" name="desc"
									class="form-control">
							</div>
						</div>
						<div class="form-group text-center">
							<button type="button" class="btn btn-info"
								onclick="submitform();">
								<i class="icon-ok"></i><b>&nbsp;提 交</b>
							</button>
							<button type="button" class="btn btn-info"
								onclick="goBack();">
								<i class="icon-reply"></i><b>&nbsp;返 回</b>
							</button>
						</div>
					</form>
				</div>
			</section>
		</div>
	</div>
	<!-- 主体内容结束 -->

	<script type="text/javascript">
   var ue = UE.getEditor('editor_content');
</script>
</body>
</html>