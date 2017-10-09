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
<link href="<%=basePath%>css/font-awesome.min.css" rel="stylesheet" type="text/css">
<style>
.input-group
{
margin-top:3px;
margin-bottom:3px;
}
.model-bgcol 
{ 
background-color:#428bca;
}
.inputField{
border-top-width:0px;
border-bottom-width:0px;
border-left-width:0px;
border-right-width:0px;
opacity:.7;
}
.custinput
{ 
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
    <!-- Navigation -->
    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">
                    <i class="fa fa-play-circle"></i>  <span class="light">EUREKA</span>
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
                <ul class="nav navbar-nav">
                    <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                  <!--   <li>
                        <a class="page-scroll" href="#myModal" data-toggle="modal">Create</a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#resultModal" data-toggle="modal">Result</a>
                    </li> -->
                    <li>
                        <a class="page-scroll" href="" target="_blank">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Header -->
    <div class="intro-header">

        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1>EUREKA</h1>
                        <!-- <h3>....</h3> -->
                        <hr class="intro-divider">
                        <div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3">
                        <div class="input-group input-group-lg">
        				<input type="text" placeholder="Search" class="form-control inputField" id="sentence">
        				<span class="input-group-btn">
        				<button id="gobtn" type="button" class="btn btn-primary">Go</button>
        				<button id="gobtn" type="button" class="btn btn-success">Example</button>
        				</span>
        				
    					</div>
     					</div>
                        <!-- <ul class="list-inline intro-social-buttons">
                            <li>
                                <a href="#" class="btn btn-default btn-lg" data-toggle="modal" data-target="#myModal"><i class="fa fa-align-left fa-fw"></i> <span class="network-name">开始统计</span></a>
							</li>
                            <li>
                                <a href="#" class="btn btn-default btn-lg" data-toggle="modal" data-target="#resultModal"><i class="fa fa-search fa-fw"></i> <span class="network-name">结果查询</span></a>
                            </li>
                            <li>
                                <a href=""><i class="fa fa-question-circle fa-fw"></i> <span class="network-name">使用说明</span></a>
                            </li>
                        </ul> -->
                    </div>
			
                </div>

            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->

    <!-- Page Content -->


    <div class="content-section-a">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">Concept Standardization</h2>
                    <p class="lead">The concept standardization module takes users’ questions as input (e.g., “Will PTSD increase the risks of Hypothyroidism?”), and recognizes standardized OMOP CDM v5 concepts (e.g., PTSD, Hypothyroidism). This module employs natural language processing techniques to parse users’ free-text questions. </p>
                </div>
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                    <img class="img-responsive" src="" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-a -->

    <div class="content-section-b">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">Query Formulation</h2>
                    <p class="lead">The query formulation module provides users with pre-designed templates based on the OHDSI ATLAS tool to capture the relations between concepts in the free text questions and generate executable database queries (e.g., SQL queries). </p>
                </div>
                <div class="col-lg-5 col-sm-pull-6  col-sm-6">
                    <img class="img-responsive" src="" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-b -->

    <div class="content-section-a">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">Scientific Workflow Support</h2>
                    <p class="lead">The scientific workflow support module helps users connect with OHDSI data contributors that are interested in the questions, obtain permissions, and deliver the SQL queries and data analysis scripts to the contributors..</p>
                </div>
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                    <img class="img-responsive" src="" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-a -->

    <div class="content-section-b">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">Patient Retrieval</h2>
                    <p class="lead">Each participating contributor identifies qualified patients from an OHDSI dataset and generates case and control groups and their results.</p>
                </div>
                <div class="col-lg-5 col-sm-pull-6  col-sm-6">
                    <img class="img-responsive" src="" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-b -->
    <div class="content-section-a">

        <div class="container">

            <div class="row">
                <div class="col-lg-5 col-sm-6">
                    <hr class="section-heading-spacer">
                    <div class="clearfix"></div>
                    <h2 class="section-heading">Evidence Aggregation.</h2>
                    <p class="lead">Finally, the participating contributors will execute the same analysis scripts and return the results to users through the analysis and aggregation module.  </p>
                </div>
                <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                    <img class="img-responsive" src="" alt="">
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.content-section-a -->
    <div class="banner">

        <div class="container">

            <div class="row">
                <div class="col-lg-8">
                    <h2>Happy to test your hypothesis!</h2>
                </div>
                <div class="col-lg-4">
                    <ul class="list-inline banner-social-buttons">
                        <li>
                                <a href="#" class="btn btn-default btn-lg" data-toggle="modal" data-target="#myModal"><i class="fa fa-align-left fa-fw"></i> <span class="network-name">Let's test</span></a>
                        </li>
                           <!--  <li>
                                <a href="#" class="btn btn-default btn-lg" data-toggle="modal" data-target="#resultModal"><i class="fa fa-search fa-fw"></i> <span class="network-name">结果查询</span></a>
                            </li>
                            -->
                    </ul>
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
					
                    <p class="copyright text-muted small"> This project developed by Chi Yuan, Wei Wei and Chunhua Weng </p>
                </div>
            </div>
        </div>
    </footer>
    <script src="<%=basePath%>js/jquery-1.11.3.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
<!-- Custom Theme JavaScript -->
    <script src="<%=basePath%>js/grayscale.js"></script>
    <script type="text/javascript">
    var basePath = "<%=basePath%>";
    $(function() {
  	  $("#gobtn").click(function() {
  		goButton();
  		});
    })
    function goButton(){
    	var sentence = $("#sentence").val();
		$.ajax({
			type : 'POST',
			url : basePath + "ohdsi/parse",
			data : {
				'sentence' : sentence
			},
			dataType : "json",
			success : function(data) {
				alert("Success!"); 
			},
			error : function() {
				//alert("Please check your NCTID");
			}

		});
    	window.location.href=basePath + "ohdsi/cohort";
    }
    
    </script>
</body>
</html>
