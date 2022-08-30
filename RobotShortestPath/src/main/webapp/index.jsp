<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<style type="text/css">
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: white;
}

.topnav a {
	float: right;
	display: block;
	color: grey;
	text-align: center;
	padding: 50px 50px;
	text-decoration: none;
	font-size: 25px;
}

.topnav a:hover {
	color: black;
}

.topnav a.active {
	/* background-color: #2196F3; */
	color: black;
}

.topnav img {
	width: 100px;
	height: 100px;
	overflow: hidden;
	margin-top: 15px;
	margin-left: 30px;
}

.container {
	width: 75%;
	height: auto;
	background-color: white;
	margin: 10px;
	line-height: 25px; margin-right : auto;
	margin-left: auto;
	color: black;
	line-height: 25px;
	text-align: center;
	font-size: 20px;
}

.container img {
	max-width: 75%;
	height: auto;
	display: block;
	margin: auto;
}

.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 5px 15px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 18px;
  margin: auto;
  transition-duration: 0.3s;
  cursor: pointer;
  border-radius: 12px;
}

.button1 {
  background-color: white;
  color: black;
  border: 2px solid #555555;
}

.button1:hover {
  background-color: #555555;
  color: white;
}

@media screen and (max-width: 600px) {
	.topnav a, .topnav img, .container, .container img {
		float: none;
		display: block;
		text-align: center;
		width: 100%;
		margin: 0;
		padding: 14px;
	}
}
</style>
<body>

	<div class="topnav">
		<!-- <a href="#contact">Contact</a> -->
		<img style="float: left" src="robot.png"> <a href="input.jsp">Start</a>
		<a href="about.jsp">About</a> <a class="active"
			href="index.jsp">Home</a>
	</div>

	<div class="container">
		<h2>Set up your favourite maze. Find the optimal path.</h2>
		<img style="float: center" src="room.png"> <br>Click Here
		to enjoy your path-finding! <a href="input.jsp" class="button button1">Start</a>
	</div>

</body>
</html>