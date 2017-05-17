<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<script type="text/javascript">
  var messageList = ${messageList};
  var ReportListLength =${ReportListLength};
  var messageList2 = ${messageList2};
  var messageSize  =${messageSize};
/* $(document).ready(function () {
	
	startRequest();
	//setInterval("startRequest()",10000);
	});
	function startRequest()
	{
		$.ajax({
			  async : false, //同步请求
			  url : '${xgq}/server/getMessage.do',
			  type : 'POST',
			  dataType : 'json',
			  timeout:1000,
			  success:function(data){
				  alert()
				  messageList = eval(data);
				  console.log(messageList);
				   $.each(messageList.rows,function(n,value){
	      			        html='<a href="#"><div class="task-info">';
                            html+='<div class="desc"></div>';
                            html+='<div class="percent">';
                            htl+='</div><div class="progress progress-striped">';
		     	            html+='<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%"><span class="sr-only">40% Complete (success)</span></div>';
		     	            html+='</div></a></li>'
	   				       $("#task").append(html);
		});  
					 
			  },
			  error: function() {
			         alert("失败，请稍后再试！");
			      }
			 });
	} */
</script>

<!--header start-->
      <header class="header white-bg">
            <div class="sidebar-toggle-box">
                <div data-original-title="隐藏/显示 菜单" data-placement="right" class="icon-reorder tooltips"></div>
            </div>
            <!--logo start-->
            <a href="index.html" class="logo">Flat<span>lab</span></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
                <!--  notification start -->
                <ul class="nav top-menu">
                    <!-- settings start -->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="icon-tasks"></i>
                            <span class="badge bg-success">${ReportListLength}</span>
                        </a>
                        <table id = "task">
                        	<ul class="dropdown-menu extended tasks-bar">
                            <div class="notify-arrow notify-arrow-green"></div>
                            <li>
                                <p class="green">You have ${ReportListLength} pending tasks</p>
                            </li>
                            <c:forEach  items="${messageList}" var="message" begin="0" end="5">
								<li>
                                <a href="#">
                                    <div class="task-info">
                                        <div class="desc">${message.name}</div>
                                        <div class="percent">${message.rate}</div>
                                    </div>
                                    <div class="progress progress-striped">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${message.rate}" aria-valuemin="0" aria-valuemax="100" style="width:${message.rate}%">
                                        </div>
                                    </div>
                                </a>
                            </li>
                            </c:forEach>
                            <li class="external">
                                <a href="#">See All Tasks</a>
                            </li>
                        </ul>
                        </table>
                    </li>
                    <!-- settings end -->
                    <!-- inbox dropdown start-->
                    <li id="header_inbox_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="icon-envelope-alt"></i>
                            <span class="badge bg-important">${messageSize}</span>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-red"></div>
                            <li>
                                <p class="red">You have ${messageSize} new messages</p>
                            </li>
                            <c:forEach  items="${messageList2}" var="message2" begin="0" end="5">
								<li>
                                <a href="#">
                                    <span class="photo"><img alt="avatar" src="./img/avatar-mini.jpg"></span>
                                    <span class="subject">
                                    <span class="from">Xuguoqiang</span>
                                    <span class="time">Just now</span>
                                    </span>
                                    <span class="message">${message2}</span>
                                </a>
                               </li>
                            </c:forEach>
                            
                            <li>
                                <a href="#">See all messages</a>
                            </li>
                        </ul>
                    </li>
                    <!-- inbox dropdown end -->
                    <!-- notification dropdown start-->
                    <li id="header_notification_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">

                            <i class="icon-bell-alt"></i>
                            <span class="badge bg-warning">7</span>
                        </a>
                        <ul class="dropdown-menu extended notification">
                            <div class="notify-arrow notify-arrow-yellow"></div>
                            <li>
                                <p class="yellow">You have 7 new notifications</p>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-danger"><i class="icon-bolt"></i></span>
                                    Server #3 overloaded.
                                    <span class="small italic">34 mins</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-warning"><i class="icon-bell"></i></span>
                                    Server #10 not respoding.
                                    <span class="small italic">1 Hours</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-danger"><i class="icon-bolt"></i></span>
                                    Database overloaded 24%.
                                    <span class="small italic">4 hrs</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-success"><i class="icon-plus"></i></span>
                                    New user registered.
                                    <span class="small italic">Just now</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-info"><i class="icon-bullhorn"></i></span>
                                    Application error.
                                    <span class="small italic">10 mins</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">See all notifications</a>
                            </li>
                        </ul>
                    </li>
                    <!-- notification dropdown end -->
                </ul>
                <!--  notification end -->
            </div>
            <div class="nav notify-row" id="top_menu">
            	<a target="_blank" href="http://localhost:8086/SecondHandCarManager/druid/index.html" class="logo">Druid<span>Monitor(查看数据库连接信息)</span></a>
            </div>
            
            <div class="top-nav ">
                <!--search & user info start-->
                <ul class="nav pull-right top-menu">
                    <li>
                        <input type="text" class="form-control search" placeholder="Search">
                    </li>
                    <!-- user login dropdown start-->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                           <!--  <img alt="" src="images/yangchunxue.jpg"> -->
                            <span class="username">Jhon Doue</span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li><a href="#"><i class=" icon-suitcase"></i>Profile</a></li>
                            <li><a href="#"><i class="icon-cog"></i> Settings</a></li>
                            <li><a href="#"><i class="icon-bell-alt"></i> Notification</a></li>
                          <li><a href="#" id="loginOut" name="loginOut"><i class="icon-key"></i> Log Out</a></li>
                        </ul>
                    </li>
                    <!-- user login dropdown end -->
                </ul>
                <!--search & user info end-->
            </div>
        </header>
        <!--header end-->