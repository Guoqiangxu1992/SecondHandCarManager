var myCommon ={
	
	enUrl : function(url){
		var url1 = encodeURI(encodeURI(url));
		if(url1.indexOf("?")>0){
			url1 = url1 + '&wt_=' + Math.random();
		}else{
			url1 = url1 + '?wt_=' + Math.random();
		}
		return url1;
	},
	/**
	 * 加载页面的通用方法
	 */
	loadPage: function(url,data1){
		var url1 = common.enUrl(url);
		//调用的时候得传 带项目名称(ctx)的地址. 例: var url = ctx + "common/s.do";
		$.ajax({
			type:"post",
			contentType:"application/x-www-form-urlencoded;charset=UTF-8",
			async:true,///////////////////
			url: url,
			datatype:"html",
			data:data1 == null ? '' : data1,
			success: function(data){
				//$("#mainSection").html(data);
			}
		});
	},
	//提交表单到指定地址
	submitFormToPath : function(formId,url){
		alert();
		var data = $("#"+formId).serialize();
		common.loadPage(url,data);
		return ;
	},
}