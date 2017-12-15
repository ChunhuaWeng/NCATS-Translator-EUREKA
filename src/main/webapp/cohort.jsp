<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	-webkit-transition: background .5s ease-in-out, padding .5s ease-in-out;
	-moz-transition: background .5s ease-in-out, padding .5s ease-in-out;
	transition: background .5s ease-in-out, padding .5s ease-in-out;
}

.navbar-customyc {
	margin-bottom: 0;
	border-bottom: 1px solid rgba(255, 255, 255, .3);
	text-transform: uppercase;
	font-family: Montserrat, "Helvetica Neue", Helvetica, Arial, sans-serif;
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

.top-nav-collapseyc {
	padding: 0;
	background-color: rgba(0, 0, 0, .6);
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

	<nav
		class="navbaryc navbar-customyc navbar-fixed-top top-nav-collapseyc"
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
				<!-- Hidden li included to remove active class from about link when scrolled up past about section -->

				<!--   <li>
                        <a class="page-scroll" href="#myModal" data-toggle="modal">Create</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#resultModal" data-toggle="modal">Result</a>
                    </li> -->
				<li><a class="page-scroll" href="" target="_blank">Contact</a>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container --> </nav>
	<div class="container projects">
		<div class="page-header projects-header " style="margin-top: 100px">
			<h4>SQL queries for creating cohorts</h4>
		</div>
		<div class="panel panel-default">
			<!-- Default panel contents -->


			<table class="table">
				<thead>
					<tr>
						<th>Cohort</th>
						<th>Description</th>
						<th>SQL file</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>Type 1 diabetes mellitus Patients</td>
						<td><button id="gobtn" type="button" class="btn btn-default">download
								file</button></td>
						<td><button id="gotoATLAS" type="button"
								class="btn btn-default">GoTo ATLAS</button></td>
					</tr>
					<tr>
						<td>2</td>
						<td>Type 1 diabetes mellitus patients who are using insulin</td>
						<td><button id="gobtn" type="button" class="btn btn-default">download
								file</button></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="container projects">
		<div class="page-header projects-header ">
			<h4>Analysis scripts</h4>
		</div>
		<form role="form">
			<div class="form-group">
				<label class="col-sm-2">Choose a test method</label>
				<div class="col-sm-4">
					<select class="form-control">
						<!--  <option>Chi-Square Test</option> -->
						<option>Percentage Calculation</option>
						<option>Age distribution</option>
					</select>
					<p>A chi-square test is any statistical hypothesis test wherein
						the sampling distribution of the test statistic is a chi-squared
						distribution when the null hypothesis is true. Without other
						qualification, 'chi-squared test' often is used as short for
						Pearson's chi-squared test.</p>


				</div>
				<button id="gobtn" type="button" class="btn btn-default col-sm-2">Download R script</button>
			</div>
			<div class="form-group col-sm-12">
				<button id="gobtn" type="button" class="btn btn-default col-sm-2">Upload your test script</button>
			</div>		
		</form>
	</div>

	<div class="container projects">
		<div class="page-header projects-header "></div>
		<button id="gotosites" type="button" class="btn btn-default">Next Step</button>
	</div>
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
	<!-- Custom Theme JavaScript -->
	<%-- <script src="<%=basePath%>js/grayscale.js"></script> --%>
	<script type="text/javascript">
    var basePath = "<%=basePath%>";
		$(function() {
			$("#gotosites").click(function() {
				goButton();
			});
		})
		function goButton() {
			window.location.href = basePath + "ohdsi/sites";
		}
	</script>
</body>


</html>
