<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Path Result</title>
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
}

.container_text {
	width: 75%;
	height: auto;
	background-color: #D3D3D3;
	margin: 5px;
	line-height: 25px;
	margin-right: auto;
	margin-left: auto;
	color: black;
	margin-right: auto
}

@media screen and (max-width: 600px) {
	.topnav a, .topnav img, .container, .container_text {
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
		<img style="float: left" src="robot.png"> <a href="input.jsp">Restart</a>
		<a href="about.jsp">About</a> <a href="index.jsp">Home</a>
	</div>
	<div class="container_text">
		<p>
			This is the robot path for the unique room you set up. <br>Room
			(Row * Col): <b><c:out value="${row}" /> * <c:out value="${col}" /></b>
			<br> Start Point (x, y): <b><c:out value="${initx}" /> , <c:out
					value="${inity}" /></b> <br> End Point (x,y): <b><c:out
					value="${finx}" /> , <c:out value="${finy}" /></b> <br> You are
			choosing to use
			<c:if test="${algo == 'bfs'}">
				<b>Breadth First Searching</b>
			</c:if>
			<c:if test="${algo == 'dijkstra'}">
				<b>Dijkstra</b>
			</c:if>
			<c:if test="${algo == 'astar'}">
				<b>A Star</b>
			</c:if>
			algorithm. <br>
		</p>

		<b>Note: </b>
		<ul>
			<li>The red squares are the result path, if there is no red
				square means no path found.</li>
			<li>The black squares are obstacles, if there is no black square
				means no obstacles.</li>
			<li>The pink square is the start point.</li>
			<li>The blue square is the end point.</li>
			<li>Time is in unite of nanosecond.</li>
			<li>Distance count from the next of starter point.</li>
		</ul>

	</div>

	<div id="canvas" class="container">
		<!-- row -> height, col -> width -->
		<canvas id="cs" width="${col * 20}" height="${row * 20}" style=""></canvas>
	</div>

	<div class="container">

		<c:if test="${disCnt != -1 && disCnt != 0}">
			<div class="alert alert-success" role="alert">
				<b>Time: </b>
				<c:out value="${time}" />
				ns <br> <b>Distance:</b>
				<c:out value="${disCnt}" />
				<%-- <br> Actual Space:
			<c:out value="${spaceCnt}" />
			<br> --%>
			</div>
		</c:if>

		<c:if test="${disCnt == -1 || disCnt == 0}">
			<div class="alert alert-success" role="alert">There is no path
				founded.</div>
		</c:if>
	</div>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript">
		'use strict'

		let canvas = document.getElementById("cs");
		let ctx = canvas.getContext("2d");

		let row = "${row}";
		let col = "${col}";
		let initx = "${initx}";
		let inity = "${inity}";
		let finx = "${finx}";
		let finy = "${finy}";
		let algo = "${algo}";

		let time = "${time}";
		let dis = "${disCnt}";

		// get room json string from servlet
		let room = JSON.parse("${room}");
		// get path json string from servlet
		let path = JSON.parse("${path}");

		// Set background as transparent.
		let w = 20, h = 20; // w and h for single cell

		// Draw Room.
		for (let i = 0; i < row; i++) {
			for (let j = 0; j < col; j++) {
				drawRect(h * j, w * i, w, h);

				// set obstacles
				if (room[i][j] == 0) {
					drawColor(i * w, j * h, w, h, "black");
				}

				// set path
				if (path[i][j] == 1) {
					drawColor(i * w, j * h, w, h, "#FF6666");
				}
			}
		}

		drawColor(initx * w, inity * h, w, h, "#FF99FF");
		drawColor(finx * w, finy * h, w, h, "#66CCFF");

		/*
		Fill color at specific position (x, y).
		 */
		function drawRect(x, y, w, h) {
			ctx.rect(x, y, w, h);
			ctx.stroke();
		}

		/* Fill color for starter point.*/
		function drawColor(y, x, w, h, color) {
			ctx.clearRect(x, y, w, h);
			ctx.fillStyle = color;
			ctx.fillRect(x, y, w, h);
			ctx.stroke();
		}
	</script>

</body>
</html>