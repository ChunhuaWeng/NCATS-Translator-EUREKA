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
         
        <div class="content-section-b" style="margin-top: 50px">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 id="percent" class="section-heading" style="font-size:36px">SynPuf 1K : N/A</h2>
                    <p class="lead">What percent of hip replacement patients took enoxaparin and had gastrointestinal bleeding?</p>
                </div>
                <div class="col-lg-5 col-sm-pull-6  col-sm-6">
                   <canvas id="myChart" width="80%" height="80%" style="width: 80%; height: 80%;"></canvas>
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
                    <h2 id="percent" class="section-heading" style="font-size:36px">SynPuf 1% : 15%</h2>
                    <p class="lead">What percent of hip replacement patients took enoxaparin and had gastrointestinal bleeding?</p>
                </div>
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                     <canvas id="myChart2" width="80%" height="80%" style="width: 80%; height: 80%;"></canvas>
                </div>
            </div>
        </div>
        <!-- /.container -->
    </div>
	 <div class="content-section-b" style="margin-top: 50px">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 id="percent" class="section-heading" style="font-size:36px">NYP West : 11%</h2>
                    <p class="lead">What percent of hip replacement patients took enoxaparin and had gastrointestinal bleeding?</p>
                </div>
                <div class="col-lg-5 col-sm-pull-6  col-sm-6">
                   <canvas id="myChart3" width="80%" height="80%" style="width: 80%; height: 80%;"></canvas>
                </div>
            </div>

        </div>
        <!-- /.container -->
    </div>
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
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.js"></script>
	<script type="text/javascript">
	//var ctx = document.getElementById("myChart");
	var basePath = "<%=basePath%>";
	var allcount=100;
	var casecount=100;
	
	/* $.ajax({
		type : 'GET',
		url : basePath + "ohdsi/getnumbers",
		data : {},
		dataType : "json",
		success : function(data) {
			allcount = data['total'];
			casecount = data['case'];
			//alert(allcount);
			var data = [ {
				value : 1,
				color : "#E0E4CC"
			}, {
				value : 2,
				color : "#69D2E7"
			} ]
			var percentage = 190 / 250;
			//$('h1').html(percentage*100+'%');
			var ctx = document.getElementById("myChart").getContext("2d");
			window.myPie = new Chart(ctx).Pie(data);		
			
			var data = [ {
				value : 37,
				color : "#E0E4CC"
			}, {
				value : 135,
				color : "#69D2E7"
			} ]
			var percentage = 190 / 250;
			//$('h1').html(percentage*100+'%');
			var ctx = document.getElementById("myChart2").getContext("2d");
			window.myPie = new Chart(ctx).Pie(data);
			
			var data = [ {
				value : 88,
				color : "#E0E4CC",
					label: 'Sleep',
				    labelColor: 'white',
				    labelFontSize: '16'
			}, {
				value : 844,
				color : "#69D2E7",
				label: 'Bull',
			    labelColor: 'white',
			    labelFontSize: '16'
			} ]
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
					    }
					  }
			}
			var percentage = 190 / 250;
			//$('h1').html(percentage*100+'%');
			var ctx = document.getElementById("myChart3").getContext("2d");
			window.myPie = new Chart(ctx).Pie(data,pieOptions);

		}
	}) */

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

	var data = {
		    datasets: [{
		        data: [
		            0,   
		            0
		        ],
		        backgroundColor: [
		            "#FF6384",			            
		            "#36A2EB"
		        ],
		        /* label: 'My dataset' // for legend */
		    }],
		    labelFontSize: '28',
		    labels: [
		        "enoxaparin , hip replacement , gastrointestinal bleeding",
		        "enoxaparin , hip replacement ,no gastrointestinal bleeding",
		    ]
		};

		var pieChartCanvas = $("#myChart");
		var pieChart = new Chart(pieChartCanvas, {
		  type: 'pie', // or doughnut
		  data: data,
		  options: pieOptions
		});
		var data2 = {
			    datasets: [{
			        data: [
			            5,   
			            28
			        ],
			        backgroundColor: [
			            "#FF6384",			            
			            "#36A2EB"
			        ],
			        /* label: 'My dataset' // for legend */
			    }],
			    labelFontSize: '28',
			    labels: [
			        "enoxaparin , hip replacement , gastrointestinal bleeding",
			        "enoxaparin , hip replacement ,no gastrointestinal bleeding",
			    ]
			};
		var pieChartCanvas2 = $("#myChart2");
		var pieChart2 = new Chart(pieChartCanvas2, {
		  type: 'pie', // or doughnut
		  data: data2,
		  options: pieOptions
		});
			
		var data3 = {
			    datasets: [{
			        data: [
			            81,   
			            633
			        ],
			        backgroundColor: [
			            "#FF6384",			            
			            "#36A2EB"
			        ],
			        /* label: 'My dataset' // for legend */
			    }],
			    labelFontSize: '28',
			    labels: [
			        "enoxaparin , hip replacement , gastrointestinal bleeding",
			        "enoxaparin , hip replacement ,no gastrointestinal bleeding",
			    ]
			};
		var pieChartCanvas3 = $("#myChart3");
		var pieChart2 = new Chart(pieChartCanvas3, {
		  type: 'pie', // or doughnut
		  data: data3,
		  options: pieOptions
		});

	/* window.onload = function(){
		var ctx = document.getElementById("myChart").getContext("2d");
		window.myPie = new Chart(ctx).Pie(pieData);
	}; */

    </script>
  </body>
</html>
