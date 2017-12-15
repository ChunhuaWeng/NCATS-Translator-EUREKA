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
		<!-- <div class="page-header projects-header " style="margin-top: 100px">
			<h4>Test Results</h4>
		</div> -->
		<!-- <div class="panel panel-default">
  Default panel contents
  

 
</div> -->

	</div>


	<!-- <div class="container projects">
		<canvas id="myChart" class="col-lg-6"></canvas>
		 <button id="gobtn" type="button" class="btn btn-default">Execute Test</button>
		 <span id="number" style="font-size:100px">83%</span>

		</div> -->

	<div class="content-section-b" style="margin-top: 50px">

		<div class="container">

			<div class="row">
				<div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
					<hr class="section-heading-spacer">
					<div class="clearfix"></div>
					<h2 id="percent" class="section-heading" style="font-size: 36px">SynPuf
						1K:68%</h2>
					<p class="lead">68% type 1 diabetes patient in SynPuf 1K are
						using insulin</p>
				</div>
				<div class="col-lg-5 col-sm-pull-6  col-sm-6">
					<canvas id="myChart" width="80%" height="80%"
						style="width: 80%; height: 80%;"></canvas>
				</div>
			</div>

		</div>
		<!-- /.container -->

	</div>
	<div class="content-section-a">

		<div class="container">

			<div class="row">
				<div class="col-lg-5 col-sm-6">
					<hr class="section-heading-spacer">
					<div class="clearfix"></div>
					<h2 id="percent" class="section-heading" style="font-size: 36px">SynPuf
						1%:72%</h2>
					<p class="lead">72% type 1 diabetes patient in SynPuf 1% are
						using insulin</p>
				</div>
				<div class="col-lg-5 col-lg-offset-2 col-sm-6">
					<canvas id="pieChart" width="80%" height="80%"
						style="width: 80%; height: 80%;"></canvas>
				</div>
			</div>

		</div>
		<!-- /.container -->
	</div>
	<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<!--<ul class="list-inline">
                        <li>
                            <a href="#home">Home</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="#about">About</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="#services">Services</a>
                        </li>
                        <li class="footer-menu-divider">&sdot;</li>
                        <li>
                            <a href="#contact">Contact</a>
                        </li>
                    </ul>-->

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

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.js"></script>
	<script type="text/javascript">
	//var ctx = document.getElementById("myChart");
	var basePath = "<%=basePath%>";
		var allcount = 100;
		var casecount = 100;

		var data = {
			    datasets: [{
			        data: [
			            11,   
			            14
			        ],
			        backgroundColor: [
			            "#FF6384",			            
			            "#36A2EB"
			        ],
			        label: 'My dataset' // for legend
			    }],
			    labelFontSize: '28',
			    labels: [
			        "Red",
			        "Green",
			    ]
			};

			var pieOptions = {
			  events: false,
			  animation: {
			    duration: 500,
			    easing: "easeOutQuart",				   
			    onComplete: function () {
			      var ctx = this.chart.ctx;
			      ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontFamily, 'normal', Chart.defaults.global.defaultFontFamily);
			      ctx.textAlign = 'center';
			      ctx.textBaseline = 'bottom';
			      this.data.datasets.forEach(function (dataset) {
			        for (var i = 0; i < dataset.data.length; i++) {
			          var model = dataset._meta[Object.keys(dataset._meta)[0]].data[i]._model,
			              total = dataset._meta[Object.keys(dataset._meta)[0]].total,
			              mid_radius = model.innerRadius + (model.outerRadius - model.innerRadius)/2,
			              start_angle = model.startAngle,
			              end_angle = model.endAngle,
			              mid_angle = start_angle + (end_angle - start_angle)/2;
			          var x = mid_radius * Math.cos(mid_angle);
			          var y = mid_radius * Math.sin(mid_angle);
			          ctx.fillStyle = '#fff';
			          if (i == 3){ // Darker text color for lighter background
			            ctx.fillStyle = '#444';
			          }
			          var percent = String(Math.round(dataset.data[i]/total*100)) + "%";
			          ctx.fillText(dataset.data[i], model.x + x, model.y + y);
			          // Display percent in another line, line break doesn't work for fillText
			          ctx.fillText(percent, model.x + x, model.y + y + 15);
			        }
			      });               
			    }
			  }
			};

			var pieChartCanvas = $("#pieChart");
			var pieChart = new Chart(pieChartCanvas, {
			  type: 'pie', // or doughnut
			  data: data,
			  options: pieOptions
			});

		/* window.onload = function(){
			var ctx = document.getElementById("myChart").getContext("2d");
			window.myPie = new Chart(ctx).Pie(pieData);
		}; */
	</script>
</body>


</html>
