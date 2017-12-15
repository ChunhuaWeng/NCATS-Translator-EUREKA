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
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
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
            <li><a href="<%=basePath%>ohdsi/account">Account</a></li>
           <!--  <li><a href="/bill/getbill">设备消费统计</a></li> -->
          </ul>
         <ul class="nav nav-sidebar">
            <li class="active"><a href="<%=basePath%>ohdsi/question">Pose a Hypothesis</a></li>
            
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="<%=basePath%>ohdsi/collaborate">Join a Group</a></li>
           
          </ul> 
          <ul class="nav nav-sidebar">
            <li><a href="<%=basePath%>ohdsi/results">See Results</a></li>
          </ul> 
			</div>
			<div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-2 main">
				<div class="panel-body" style="padding-bottom: 0px;">

					<div class="container projects">
						<div class="page-header projects-header ">
							<h4>Send Data Request</h4>
						</div>
						<div class="panel panel-default">
							<!-- Default panel contents -->
							<table id="table"></table>
						</div>
						
					</div>
					<div class="container projects">
						<div class="page-header projects-header "></div>
						<button id="execute" type="button" class="btn btn-default">Next
							Step</button>
					</div>
				</div>

				<!-- Bootstrap core JavaScript
    ================================================== -->
				<!-- Placed at the end of the document so the pages load faster -->
				<script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>

				<!-- Bootstrap Core JavaScript -->
				<script src="<%=basePath%>js/bootstrap.min.js"></script>
				<script src="<%=basePath%>js/bootstrap-table.min.js"></script>
				<!-- Custom Theme JavaScript -->

				<script type="text/javascript">
    var basePath = "<%=basePath%>";
    $(function() {
    	$('#table').bootstrapTable({
    		url: basePath + "ohdsi/getallsites",
    		responseHandler:function (res) {
                return res.rows;
            },
    		columns: [ {
				checkbox : true
			}, {
    	        field: 'id',
    	        title: 'id'
    	    }, {
    	        field: 'siteName',
    	        title: 'Site Name'
    	    },{
    	        field: 'desc',
    	        title: 'Description'
    	    }, {
    	        field: 'status',
    	        title: 'Status',
    	        formatter: processStatus
    	        
    	    }]
    	});
    	
  	    $("#execute").click(function() {
  		     goButton();
  		});
  	   
  
    })
    
    function processStatus(value, row, index) {
    	if(value==0){
    		return "<span class=\"glyphicon glyphicon-remove text-danger\" aria-hidden=\"true\"></span>"; 	
    	}else{
    		return "<span class=\"glyphicon text-success glyphicon-ok\" aria-hidden=\"true\"></span>";
    	}
      }
   
    function goButton(){
    	var sites = [];
			zdata = $("#table").bootstrapTable('getAllSelections');
			//alert(zdata)
			for ( var o in zdata) {	
				sites.push(zdata[o]['id']);	
			};
			alert(sites);
			$.ajax({
				type : 'POST',
				url : basePath + "ohdsi/setRequestSites",
				data :{
					sites:JSON.stringify(sites)},
				dataType : "json",
				success : function(data) {
					//alert('success!');
					window.location.href=basePath + "ohdsi/review";
				},
				error: function(e) { 
					alert("Error! Please check your network!");
				} 
			});
			//window.location.href = basePath + "ohdsi/review";
	
    }
    </script>
</body>
</html>
