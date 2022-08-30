<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
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
	margin: 5px;
	line-height: 25px;
	margin-right: auto;
	margin-left: auto;
	color: black;
	line-height: 25px;
	margin-right: auto;
	margin-right: auto;
	font-size: 20px;
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
	.topnav a, .topnav img, .container {
		float: none;
		display: block;
		text-align: left;
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
		<a class="active" href="about.jsp">About</a> <a href="index.jsp">Home</a>
	</div>

	<div class="container">
		<h2>Robot Optimize Path(ROP) Problem</h2>
		<p>In a room of size (m*n) (a matrix of (m*n)) there is a robot
			walking around. Each tile in the room has two possibilities: passable
			and blocked. The robot can move forward, turn left or turn right, at
			90 degrees per turn. This means that the robot can not move forward
			along the diagonal path. The robot need to avoid obstacles and pass
			through passable tiles in order to reach their destination.</p>

		<p>This web application presents a visualisation of the results of
			the ROP problem. You can set up your preferred room and choose your
			preferred algorithm for robot path-finding in the settings. We offer
			three algorithms: Breadth-First Search (BFS), Dijkstra, and A-Star
			(A*).</p>

		<p>
			Click Here to enjoy your path-finding! <a href="input.jsp"
				class="button button1">Start</a>
		</p>
	</div>

	<div class="container">
		<h2>About Project</h2>
		<p>Hi, I'm Yi. this web application is a visualisation of the
			results of my Glasgow postgraduate final design research. Supported
			by my supervisor professor Sofiat Olaosebikan and developed by myself
			full stack.</p>
	</div>

	<div class="container">
		<h2>Further Work</h2>
		<p>The project took two months. Due to the time constraint and my
			lack of front-end experience, there is still a lot of room for
			optimisation and improvement in the web application. Although the
			project has been completed, I will be making further improvements to
			the web application in the future.</p>
		<ul>
			<li>Introduction to the algorithm through animation</li>
			<li>Add more algorithm demonstrations</li>
			<li>Output of the path results in animated form</li>
			<li>Detailed path formation process</li>
			<li>Mobile version of the web application</li>
		</ul>
	</div>

	<div class="container">
		<h2>Feedback</h2>
		<p>
			Feel free to contact me if you have any questions or suggestions of
			improvement about this web application. Here is my email address: <b>2660688Z@student.gla.ac.uk</b>
		</p>
	</div>

</body>
</html>