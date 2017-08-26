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
<title>EUREKA</title>
	<meta charset="utf-8" />
	<!-- jQuery -->
	<script src="<%=basePath%>/js/jquery-1.11.3.min.js"></script>
	<!-- EnjoyHint JS and CSS files -->
	<script src="<%=basePath%>/js/enjoyhint.min.js"></script>
	<link href="<%=basePath%>/css/enjoyhint.css" rel="stylesheet">
	<!-- Bootstrap -->
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="<%=basePath%>/js/bootstrap.min.js"></script>
	<!-- My style -->
	<link rel="stylesheet" href="<%=basePath%>/css/style.css">

	
</head>
<body>



<div class="featurette">
   <div class="featurette-inner text-center">
     <div class="input-group input-group-lg">
        <input type="text" placeholder="Search" class="form-control" id="mySearchButton">
        <span class="input-group-btn">
        <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Options <span class="caret"></span></button>
		    <ul class="dropdown-menu" role="menu">
		      <li><a href="">Images</a></li>
		      <li><a href="">Videos</a></li>
		      <li><a href="">News Articles</a></li>
		      <li class="divider"></li>
		      <li><a href="">Default</a></li>
		    </ul>
        </span>
     </div>
   </div>
</div>

 <div class="featurette">
   <div class="featurette-inner text-center">
     <div class="input-group input-group-lg">
        <input type="text" placeholder="Search" class="form-control" id="mySearchButton">
        <span class="input-group-btn">
        <button type="button" class="btn btn-primary">EUREKA</button>
        </span>
     </div>
   </div>
</div>

<!-- <footer class="footer">
  <div class="container">
		<div class="row footer-top">
			<div class="col-sm-6 col-lg-6">
				<p>This website was developed by ChiYuan, Wei Wei,Chunhua Weng</p>
			</div>
		</div>
	</div>
</footer> -->

</body>



</html>
