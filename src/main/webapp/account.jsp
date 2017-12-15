<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>EUREKA</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">
  <link rel="stylesheet" href="../css/bootstrap-table.min.css">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../js/ie-emulation-modes-warning.js"></script>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../js/ie10-viewport-bug-workaround.js"></script>
<script src="../js/bootstrap-table.min.js"></script>
<script src="../js/bootstrap-table-zh-CN.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">EUREKA</a>
        </div>
        <!-- <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Settings</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Help</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div> -->
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
         <ul class="nav nav-sidebar">
            <li  class="active"><a href="<%=basePath%>ohdsi/account">Account</a></li>
           <!--  <li><a href="/bill/getbill">设备消费统计</a></li> -->
          </ul>
         <ul class="nav nav-sidebar">
            <li><a href="<%=basePath%>ohdsi/question">Pose a Hypothesis</a></li>
            
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="<%=basePath%>ohdsi/collaborate">Join a Group</a></li>
           
          </ul> 
          <ul class="nav nav-sidebar">
            <li><a href="<%=basePath%>ohdsi/results">See Results</a></li>
          </ul> 
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
         <div class="panel-body" style="padding-bottom:0px;">
  <div class="panel panel-default">
   <div class="panel-heading">Personal Information</div>
   <div class="panel-body">
    <form id="formSearch" class="form-horizontal">
     <div class="form-group" style="margin-top:15px">
      <label class="control-label col-sm-2" for="txt_search_departmentname">Account</label>
       <label id="username" class="control-label col-sm-3" style="text-align:left;" for="txt_search_departmentname">do0001</label>
     </div>
    <div class="form-group" style="margin-top:15px">
      <label class="control-label col-sm-2" for="txt_search_departmentname">Name</label>
       <label id="realname" class="control-label col-sm-3" style="text-align:left;" for="txt_search_departmentname">DataOwner1</label>
     </div>
     <div class="form-group" style="margin-top:15px">
      <label class="control-label col-sm-2" for="txt_search_departmentname">Institute</label>
       <label id="institute" class="control-label col-sm-3" style="text-align:left;" for="txt_search_departmentname">Columbia University Medical Center</label>
     </div>
     <div class="form-group" style="margin-top:15px">
      <label class="control-label col-sm-2" for="txt_search_departmentname">Contact</label>
       <label id="contact" class="control-label col-sm-3" style="text-align:left;" for="txt_search_departmentname">dataowner1@cumc.columbia.edu	</label>
     </div>
    </form>
   </div>
  </div>  
  
   <div class="panel panel-default">
   <div class="panel-heading">User Role</div>
   <div class="panel-body">
    <form id="formSearch" class="form-horizontal">
     <div class="form-group" style="margin-top:15px">
      <label class="control-label col-sm-2" for="txt_search_departmentname">Rearcher</label>
       <label id="researcher" class="control-label col-sm-3" style="text-align:left;" for="txt_search_departmentname">Activated</label> 
       <label class="control-label col-sm-2" for="txt_search_statu">Posted Hypotheses #</label>
       <label id="question" class="control-label col-sm-3" style="text-align:left;" for="txt_search_departmentname">0</label>
     </div>
     <div class="form-group" style="margin-top:15px">
      <label class="control-label col-sm-2" for="txt_search_departmentname">Data Owner</label>
       <label id="dataowner" class="control-label col-sm-3" style="text-align:left;" for="txt_search_departmentname">Activated</label>
      <label class="control-label col-sm-2" for="txt_search_statu">Joined Groups #</label>
       <label id="project" class="control-label col-sm-3" style="text-align:left;" for="txt_search_departmentname">2</label>
     </div>
    </form>
   </div>
  </div>  

 <!--  <div id="toolbar" class="btn-group">
   <button id="btn_add" type="button" class="btn btn-default">
    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
   </button>
   <button id="btn_edit" type="button" class="btn btn-default">
    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
   </button>
   <button id="btn_delete" type="button" class="btn btn-default">
    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
   </button>
  </div>
  <table id="tb_departments"></table> -->
 </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
  <script type="text/javascript">
  var basePath = "<%=basePath%>";
$(function () {
init();
});
function init(){
	$.ajax({
		type : 'GET',
		url : basePath + "ohdsi/accountInfo",
		dataType : "json",
		success : function(data) {
			$("#username").html(data['data'].username); 
			$("#realname").html(data['data'].realname); 
			$("#institute").html(data['data'].institute); 
			$("#contact").html(data['data'].contact); 
			if(data['data'].researcher=='1'){
				$("#researcher").html("Activated"); 
			}else{
				$("#researcher").html("Inactivate"); 
			}
			if(data['data'].dataowner=='1'){
				$("#dataowner").html("Activated"); 
			}else{
				$("#dataowner").html("Inactivate"); 
			}
			$("#question").html(data['data'].question); 
			$("#project").html(data['data'].project); 
		},
		error : function() {
			alert('Your session has expired! please login');
			window.location.href = basePath;
		}

	});
}

	
</script>
  </body>
</html>
