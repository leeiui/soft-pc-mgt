<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="expires" content="0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<title>墨客</title>
<link rel="stylesheet" href="/mgt/css/bootstrap.min.css">
<script type="text/javascript" src="/mgt/js/jquery.min.js"></script>
<script type="text/javascript" src="/mgt/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/mgt/js/admin_pc.js"></script>
<script type="text/javascript" src="/mgt/js/jquery.form.js"></script>
<script type="text/javascript" src="/mgt/js/jquery.cookie.js"></script>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
</head>
<body>
<jsp:include page="/page/pop-window.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<nav class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">PC后台管理</a>
				</div>
				<div>
					<ul class="nav navbar-nav navbar-right">
						<li class="disabled"><a id="welcome_user"></a></li>
						<li class="active"><a href="/mgt/logout">退出登录</a></li>
					</ul>
				</div>
			</div>
			</nav>
		</div>
		<div class="row">
			<div class="col-md-2">
				<div class="panel-group table-responsive" role="tablist">
					<div id="pc_menu" class="panel panel-primary leftMenu">
						<!-- 利用data-target指定要折叠的分组列表 -->
						<div class="panel-heading" id="collapseListGroupHeading1"
							data-toggle="collapse" data-target="#collapseListGroup1"
							role="tab">
							<h4 class="panel-title">
								PC管理 <span class="glyphicon glyphicon-chevron-up right"></span>
							</h4>
						</div>
						<!-- .panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
						<div id="collapseListGroup1" class="panel-collapse collapse in"
							role="tabpanel" aria-labelledby="collapseListGroupHeading1">
							<ul class="list-group">
								<li class="list-group-item">
									<a href="javascript:void(0);" onclick="pc_list(1,7)">PC列表</a>
								</li>
								<li class="list-group-item">
									<a href="javascript:void(0);" onclick="pc_add()">添加PC</a>
								</li>
							</ul>
						</div>
					</div>
					<!--panel end-->
					<div id="user_menu" class="panel panel-primary leftMenu">
						<div class="panel-heading" id="collapseListGroupHeading2"
							data-toggle="collapse" data-target="#collapseListGroup2"
							role="tab">
							<h4 class="panel-title">
								人员管理 <span class="glyphicon glyphicon-chevron-down right"></span>
							</h4>
						</div>
						<div id="collapseListGroup2" class="panel-collapse collapse"
							role="tabpanel" aria-labelledby="collapseListGroupHeading2">
							<ul class="list-group">
								<li class="list-group-item"><a href="javascript:void(0);" onclick="users_list()">用户列表</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-9"><div id="mgt-content"></div></div>
		</div>
	</div>
</body>
</html>