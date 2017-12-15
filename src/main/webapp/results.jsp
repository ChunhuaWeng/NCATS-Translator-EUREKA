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
            <li><a href="<%=basePath%>ohdsi/account">Account</a></li>
           <!--  <li><a href="/bill/getbill">设备消费统计</a></li> -->
          </ul>
         <ul class="nav nav-sidebar">
            <li><a href="<%=basePath%>ohdsi/question">Pose a Hypothesis</a></li>
            
          </ul>
          <ul class="nav nav-sidebar">
            <li><a href="<%=basePath%>ohdsi/collaborate">Join a Group</a></li>
           
          </ul> 
          <ul class="nav nav-sidebar">
            <li class="active"><a href="<%=basePath%>ohdsi/results">See Results</a></li>
          </ul> 
        </div>
        <div class="col-sm-9 col-sm-offset-2 col-md-10 col-md-offset-2 main">
         <div class="panel-body" style="padding-bottom:0px;">
         
       	<div class="container projects">
		<div class="page-header projects-header" >
			<h4>My Hypotheses</h4>	
		</div>
		<div class="panel panel-default">
  <!-- Default panel contents -->
  
<table id="table2" class="table">
       <!--  <thead>
          <tr>
            <th>#</th>
            <th>Hypotheses</th>
            <th>Detail</th>
          </tr>
        </thead>
        <tbody>
         <tr>
            <td scope="row">1</td>
            <td>What percent of hip replacement patients took warfarin and had gastrointestinal bleeding?</td>
            <td> <button id="gotoATLAS" type="button" class="btn btn-default">Detail</button></td>       
          </tr>    
          <tr>
            <td scope="row">1</td>
            <td>What percent of hip replacement patients took enoxaparin and had gastrointestinal bleeding?</td>
            <td> <button id="gotoATLAS" type="button" class="btn btn-default">Detail</button></td>       
          </tr>    
        </tbody> -->
      </table>
      
</div>
<!-- <button id="gobtn" type="button" class="btn btn-default">Register a new Set</button> -->
	</div>
	<div class="container projects" style="margin-top: 50px">
		<div class="page-header projects-header ">
			<h4>My Collaborations</h4>
		</div>
		<div class="panel panel-default">
  <!-- Default panel contents -->
  

  <table id="table1" class="table">
        <!-- <thead>
          <tr>
            <th>#</th>
            <th>Questions</th>
            <th>Organizer</th>
            <th>Detail</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td scope="row">1</td>
            <td>what are the top 2 drugs used by hypertensive disorder patients?</td>
            <td>ResearcherX</td>
            <td> <button id="gotoATLAS" type="button" class="btn btn-default">Detail</button></td>
          </tr>
           <tr>
            <td scope="row">1</td>
            <td>What percent of hip replacement patients took warfarin and had gastrointestinal bleeding?</td>
            <td>Researcher1</td>
            <td> <button id="gotoATLAS" type="button" class="btn btn-default">Detail</button></td>      
          </tr>
        </tbody> -->
      </table>
</div>
	</div>
<!--model--->
<!-- Modal -->
<div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Register</h4>
      </div>
      <div class="modal-body">
	   <form role="form" name="form1" id="myForm" class="form-horizontal" action="" method="post">
	   <div class="form-group">
        <label for="IDCard" class="col-sm-3 control-label">Site name</label>
		<div class="col-sm-8">	
		<input type="text" class="form-control col-sm-6" id="tjget" name="tjget" placeholder="" value=''>
		</div>
		</div>
		 <div class="form-group">
        <label for="IDCard" class="col-sm-3 control-label">Description</label>
		<div class="col-sm-8">	
		<input type="text" class="form-control col-sm-6" id="tjget" name="tjget" placeholder="" value=''>
		</div>
		</div>
		 <div class="form-group">
        <label for="IDCard" class="col-sm-3 control-label">Patient Count</label>
		<div class="col-sm-8">	
		<input type="text" class="form-control col-sm-6" id="tjget" name="tjget" placeholder="" value=''>
		</div>
		</div>
		 <div class="form-group">
        <label for="IDCard" class="col-sm-3 control-label">Contact Email</label>
		<div class="col-sm-8">	
		<input type="text" class="form-control col-sm-6" id="tjget" name="tjget" placeholder="" value=''>
		</div>
		</div>
		 <div class="form-group">
        <label for="IDCard" class="col-sm-3 control-label">Available for collaboration</label>
		<div class="col-sm-8">	
		<select class="form-control"> 
	       <option>Available</option> 
	       <option>Unavailable</option> 
	      </select>
		</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary" >Submit</button>
      </div>
	  </form>
    </div>
  </div>
</div>	
<!--modal end-->		

 </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
 <script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/bootstrap-table.min.js"></script>
	<script type="text/javascript">
    var basePath = "<%=basePath%>";
    $(function() {
    	$('#table1').bootstrapTable({
    		url: basePath + "ohdsi/getMyCollaborations",
    		responseHandler:function (res) {
                return res.rows;
            },
    		columns: [{
    	        field: 'questionid',
    	        title: 'id'
    	    }, {
    	        field: 'content',
    	        title: 'Question'
    	    }, {
    	        field: 'orgnizer',
    	        title: 'orgnizer'
    	    },{
    	        field: 'testtype',
    	        title: 'Detail',
    	        formatter: processStatus   
    	    }],
    	});
    	$('#table2').bootstrapTable({
    		url: basePath + "ohdsi/getMyQuestions",
    		responseHandler:function (res) {
                return res.rows;
            },
    		columns: [{
    	        field: 'questionid',
    	        title: 'id'
    	    }, {
    	        field: 'content',
    	        title: 'Question'
    	    },{
    	        field: 'testtype',
    	        title: 'Detail',
    	        formatter: processStatus   
    	    }],
    	});
  	  $("#gotosites").click(function() {
  		goButton();
  		});
    })
    function processStatus(value, row, index) { 	
    		return "<button id=\"gotoATLAS\" type=\"button\" class=\"btn btn-default\">Detail</button>";
    	
      }
    function goButton(){
    	window.location.href=basePath + "ohdsi/sites";
    }
    </script>
  </body>
</html>
