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
<title>EUREKA</title>
<!-- Bootstrap Core CSS -->
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=basePath%>css/landing-page.css" rel="stylesheet">
<link href="<%=basePath%>css/grayscale.css" rel="stylesheet">
<link href="<%=basePath%>css/bootstrap-table.min.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<style>
.input-group {
	margin-top: 3px;
	margin-bottom: 3px;
}

.model-bgcol {
	background-color: #428bca;
}

.navbaryc {
    padding: 0px 0;
    border-bottom: 0;
    letter-spacing: 1px;
    background: 0 0;
    -webkit-transition: background .5s ease-in-out,padding .5s ease-in-out;
    -moz-transition: background .5s ease-in-out,padding .5s ease-in-out;
    transition: background .5s ease-in-out,padding .5s ease-in-out; 
}
.navbar-customyc {
    margin-bottom: 0;
    border-bottom: 1px solid rgba(255,255,255,.3);
    text-transform: uppercase;
    font-family: Montserrat,"Helvetica Neue",Helvetica,Arial,sans-serif;
    background-color: #000;
}
.navbar-customyc .navbar-brand {
    font-weight: 700;
}

.navbar-customyc a {
    color: #f9f7f7;
}
.navbar-fixed-top {
    top: 0;
    border-width: 0 0 1px;
}

.table tbody tr td{
            vertical-align: middle;
        }
.top-nav-collapseyc {
    padding: 0;
    background-color: rgba(0,0,0,.6);
}
.inputField {
	border-top-width: 0px;
	border-bottom-width: 0px;
	border-left-width: 0px;
	border-right-width: 0px;
	opacity: .7;
}

.custinput {
	display: block;
	height: 34px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px
}
</style>


</head>
<body>

	<nav class="navbaryc navbar-customyc navbar-fixed-top top-nav-collapseyc"
		role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-main-collapse">
				<i class="fa fa-bars"></i>
			</button>
			 <a class="navbar-brand page-scroll" href="<%=basePath%>"> <i
				class="fa fa-play-circle"></i> <span class="light">EUREKA</span>
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div
			class="collapse navbar-collapse navbar-right navbar-main-collapse">
			<ul class="nav navbar-nav">
				<li><a class="page-scroll" href="" target="_blank">Contact</a>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>
	
		<div class="container projects">
		<div class="page-header projects-header"  style="margin-top: 100px">
			<h4>My Data Set</h4>	
		</div>
		<div class="panel panel-default">
  <!-- Default panel contents -->
  
<table id="table"></table>
      
</div>
<!-- <button id="gobtn" type="button" class="btn btn-default">Register a new Set</button> -->
		 <a href="#" class="btn btn-default" data-toggle="modal" data-target="#resultModal"><i class="fa fa-search fa-fw"></i>Register a new Set</a>
	</div>
	<div class="container projects">
		<div class="page-header projects-header ">
			<h4>Request Queue</h4>
		</div>
		<div class="panel panel-default">
  <!-- Default panel contents -->
  

  <table class="table">
        <thead>
          <tr>
            <th>#</th>
            <th>Questions</th>
            <th>Researcher</th>
            <th>Detail</th>
            <th>Operation</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td scope="row">1</td>
            <td>what are the top 2 drugs used by hypertensive disorder patients?</td>
            <td>Chi Yuan</td>
            <td> <button id="gotoATLAS" type="button" class="btn btn-default">Detail</button></td>
            <td>
            <button id="gotoATLAS" type="button" class="btn btn-success">Accept</button>
            <button id="gotoATLAS" type="button" class="btn btn-danger">Decline</button>
            </td>
          </tr>
          <tr>
           <td scope="row">2</td>
            <td>what is the age distribution of Alzheimer’s disease patients?</td>
             <td>Chi Yuan</td>
              <td> <button id="gotoATLAS" type="button" class="btn btn-default">Detail</button></td>
            <td>
            <button id="gotoATLAS" type="button" class="btn btn-success">Accept</button>
            <button id="gotoATLAS" type="button" class="btn btn-danger">Decline</button>
            </td>
          </tr>
        </tbody>
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

	
	<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
			
				<p class="copyright text-muted small">This project developed by
					Chi Yuan, Wei Wei and Chunhua Weng</p>
			</div>
		</div>
	</div>
	</footer>
	<script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<script src="<%=basePath%>js/bootstrap-table.min.js"></script>
	<script type="text/javascript">
    var basePath = "<%=basePath%>";
    $(function() {
    	$('#table').bootstrapTable({
    		url: basePath + "ohdsi/getMyData",
    		responseHandler:function (res) {
                return res.rows;
            },
    		columns: [{
    	        field: 'id',
    	        title: 'id'
    	    }, {
    	        field: 'siteName',
    	        title: 'Site Name'
    	    },{
    	        field: 'desc',
    	        title: 'Description'
    	    }, {
    	        field: 'patientCount',
    	        title: 'Patient Count'
    	    }, {
    	        field: 'email',
    	        title: 'Email'
    	    }, {
    	        field: 'contact',
    	        title: 'Contact'
    	    },{
    	        field: 'status',
    	        title: 'Available for collaboration',
    	        formatter: processStatus   
    	    }],
    	});
  	  $("#gotosites").click(function() {
  		goButton();
  		});
    })
    function processStatus(value, row, index) {
    	if(value==1){
    		return "<span class=\"glyphicon glyphicon-remove text-danger\" aria-hidden=\"true\"></span>"; 	
    	}else{
    		return "<span class=\"glyphicon text-success glyphicon-ok\" aria-hidden=\"true\"></span>";
    	}
      }
    function goButton(){
    	window.location.href=basePath + "ohdsi/sites";
    }
    </script>
</body>


</html>
