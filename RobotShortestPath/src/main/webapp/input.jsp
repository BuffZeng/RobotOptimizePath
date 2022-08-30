<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Input</title>
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
	margin-right: auto;
	font-size: 20px;
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
	font-size: 15px;
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
		<img style="float: left" src="robot.png"> <a class="active"
			href="input.jsp">Start</a> <a href="about.jsp">About</a> <a
			href="index.jsp">Home</a>
	</div>


	<div class="container_text">
		<p>
			Please enter the number you want. Click the <b>SUBMIT</b> button when
			you have finished entering.<br>
		</p>
		<b>Note: </b>
		<ul>
			<li>All inputs must be integer.</li>
			<li>row and column range from 2 to 100.</li>
			<li>x ranges from 0 to row-1.</li>
			<li>y ranges from 0 to column-1.</li>
			<li>The start and end points cannot be thesame point.</li>
		</ul>
	</div>

	<div class="container">
		<form action="input" method="get" name="input_form">
			Room(Row * Col): <input type="number" name="row" id="row_id" size="5">
			, <input type="number" name="col" id="col_id" size="5"> <br>
			Start Point(x, y): <input type="number" name="initx" id="initx_id"
				size="5"> , <input type="number" name="inity" id="inity_id"
				size="5"> <br> End Point(x,y): <input type="number"
				name="finx" id="finx_id" size="5"> , <input type="number"
				name="finy" id="finy_id" size="5"> <br> Algorithm
			Choosing: <select name="algo">
				<option value="bfs">Breadth First Searching</option>
				<option value="dijkstra">Dijkstra</option>
				<option value="astar">A Star</option>
			</select> <br> <input type="submit" value="SUBMIT" class="button button1"><br>
		</form>
	</div>
	<div class="container">
		<c:if test="${messages['Fail'] != null}">
			<div>
				<c:out value="${messages['Fail']}" />
			</div>
		</c:if>
	</div>
	<div class="container">
		<c:if test="${messages['Success'] != null}">
			<div>
				<br>Room (Row * Col): <b><c:out value="${row}" /> * <c:out
						value="${col}" /></b> <br> Start Point (x, y): <b><c:out
						value="${initx}" /> , <c:out value="${inity}" /></b> <br> End
				Point (x,y): <b><c:out value="${finx}" /> , <c:out
						value="${finy}" /></b> <br> You are choosing to use
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
			</div>
		</c:if>
	</div>
	<div class="container_text">
		<p>
			Please set up obstacles. Click on a white square to place an
			obstacle, when the square turns black it means the square is
			impassable. Click on the black square to cancel the obstacle setup,
			when the square turns white it means the square is passable. Click on
			the <b>GENERATE</b> button when you have finished setting up the
			obstacles.<br>
		</p>
		<b>Note: </b>
		<ul>
			<li>The start and end point must not be set up as obstacles.</li>
			<li>The black squares are obstacles.</li>
			<li>The pink square is the start point.</li>
			<li>The blue square is the end point.</li>
		</ul>
	</div>

	<div id="canvas" class="container">
		<!-- row -> height, col -> width -->
		<canvas id="cs" width="${col * 20}" height="${row * 20}" style=""></canvas>
	</div>
	<!-- <input type="submit" id="generate">Generate -->

	<div class="container">
		<form action="input" method="post" name="input_form">

			<input type="hidden" name="room" id="get_room_string" value="abc">
			<button type="submit" id="generate" class="button button1">GENERATE</button>
		</form>
	</div>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript">
		'use strict'

		let room = new Array();

		let canvas = document.getElementById("cs");
		let ctx = canvas.getContext("2d");
		
		let row = "${row}";
		let col = "${col}";
		let initx = "${initx}";
		let inity = "${inity}";
		let finx = "${finx}";
		let finy = "${finy}";
		let algo = "${algo}";
		
		// Set background as transparent.
		let w = 20, h = 20; // w and h for single cell

		// Initialize array.
		for (let i = 0; i < row; i++) {
			room[i] = new Array();
			for (let j = 0; j < col; j++) {
				room[i][j] = 1;
				drawRect(h * j, w * i, w, h);
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
		
		canvas.onmousedown = e => {
			let x = e.pageY - canvas.offsetTop;
			let y = e.pageX - canvas.offsetLeft;
			let obj = computePosition(x, y, w, h);
			
			// First click as black, second click return to blank.
			let color = "black";
			if (room[obj.i][obj.j] == 0) {
				color = "white";
				room[obj.i][obj.j] = 1;
			} else if (room[obj.i][obj.j] == 1) {
				room[obj.i][obj.j] = 0;
			}
			drawColor(obj.i * w, obj.j * h, w, h, color);
		}
		
		/* Get x, y of starter point.*/
		function computePosition(x, y, w, h) {
			let pos_i = parseInt(x / h);
			let pos_j = parseInt(y / w);
			console.log("Room Position: (%d, %d)", pos_i, pos_j);
			return {i: pos_i, j: pos_j};
		}
		
		/* Fill color for starter point.*/
		function drawColor(y, x, w, h, color) {
			ctx.clearRect(x, y, w, h);
			ctx.fillStyle = color;
			ctx.fillRect(x, y, w, h);
			ctx.stroke();
		}
		
		generate.onclick = e => {
			console.log("Room Layout in 2D Array：\n");
			console.log(JSON.stringify(room));
			document.getElementById("get_room_string").value = JSON.stringify(room);
			
		}
	</script>
</body>
</html>