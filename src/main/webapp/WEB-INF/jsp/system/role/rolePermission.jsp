<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色权限</title>
<%@include file="/common/jsp/taglib.jsp"%>
<script type="text/javascript">
</script>
<script type='text/javascript' src="${ctx }ass/basicmanage/js/rolePermission.js"></script>
</head>

<style type="text/css">
		.roleTable {
			margin: 15px 0px 20px 30px;
			width: 35%;
			text-align: left;
			float: left;
			border-right: 1px solid #cdcfcf;
			border-top: 1px solid #cdcfcf;
		}
		.contentright{
			margin: 15px 0px 20px 0px;
			float: left;
			width: 57%;
			padding-left: 10px;
			border-left: 1px solid #cdcfcf;
			border-top: 1px solid #cdcfcf;
		}
</style>

<body >
<div >
<button href="#myModal_editrole" data-toggle="modal" onclick="prepareNewRole();" type="button" class="btn btn-info" style="margin:10px 30px 0px 30px;">新建角色</button>
<button onclick="saveRolePermission();" type="button" class="btn btn-warning" style="margin:10px 30px 0px 10px;">保存当前角色的权限</button>
</div>
<div >
		<div class="roleTable">
			<div id="roleGrid">
				<table id="roletable_" class="table table-bordered table-hover table-condensed" >
					<thead>
					<tr>
					<th>序号</th>
					<th>名称</th>
					<th>操作</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${roleLst }" var="u" varStatus="ind" >
							<tr onclick="clickrole('${u.id }role_','success',${u.id });" id="${u.id }role_">
								<td>${ind.index+1 }</td>
								<td>${u.name }</td>
								<td> 
									<button href="#myModal_editrole" data-toggle="modal" onclick="prepareEditRole(${u.id },'${u.name }');" class="btn btn-info btn-xs" >
										<i class="icon-edit">编辑</i>
									</button>
									
									<button class="btn btn-danger btn-xs" onclick="deleteRole(${u.id})">
										<i class="icon-trash">删除</i>
									</button>
								 </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	
	
		<div class="contentright" id="contentright" >
		
			<div id="authDiv" >
				<form id="role" >
				<input type="hidden" id="roleid" name="roleid" value="" />
				<c:forEach items="${ allPermissionLst}" var="aPermissionLst" varStatus="ind" >
				<div>
				
					<input type="checkbox"  onclick="rolePermission.checkSibings(this);" name="pcCheckbox" id="${aPermissionLst.id }" value="${aPermissionLst.id }" />
					<label style="font-size: 16px;font-weight: normal;" class="text-danger" for="${aPermissionLst.id }">${aPermissionLst.text }</label>
					
					<br/>
					<c:forEach items="${ aPermissionLst.children}" var="aPermission" varStatus="ind2" >
						<input type="checkbox" onclick="rolePermission.checkParent(this);" name="pcCheckbox" id="${aPermission.id }" value="${aPermission.id }" value2="${aPermission.pid }" />
						<label style="font-size: 14px;font-weight: normal;"  class="text-info "  for="${aPermission.id }">${ aPermission.text}</label>&nbsp;&nbsp;
					</c:forEach>
					<br/>
				</div>
				</c:forEach>
				
				</form>
				</div>
		
		</div>
	
</div>
	
	
	
	




<div aria-hidden="false" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal_editrole" class="modal fade in" >
  <div class="modal-dialog">
	  <div class="modal-content">
		  <div class="modal-header">
			  <button aria-hidden="true" id="myModal_editrole_close"  data-dismiss="modal" class="close" type="button" >×</button>
			  <h4 class="modal-title" id="roleTitle">编辑角色</h4>
		  </div>
		  <div class="modal-body">
			  <form role="form" class="form-horizontal" id="editroleform" >
			  <input type='hidden' id="edit_roleid" name="edit_roleid"/>
					
              
              <div class="form-group">
                   <label class="col-lg-2 col-sm-2 control-label" for="edit_rolename">角色名称</label>
                   <div class="col-lg-10">
                       <input type="text" placeholder="" id="edit_rolename" name="edit_rolename" class="form-control">
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

</html>