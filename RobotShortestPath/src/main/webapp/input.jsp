<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="input" method="get" name="input_form">
		Room Row:
		<input type="text" name="row" id="row_id">
		<br> Room Col:
		<input type="text" name="col" id="col_id">
		<br> Starter Point:
		<input type="text" name="initx" id="initx_id">
		,
		<input type="text" name="inity" id="inity_id">
		<br> End Point:
		<input type="text" name="finx" id="finx_id">
		,
		<input type="text" name="finy" id="finy_id">
		<br> Algorithm Choosing: <select name="algo">
			<option value="Breadth First Searching">Breadth First
				Searching</option>
			<option value="Dijkstra">Dijkstra</option>
			<option value="A Star">A Star</option>
		</select><br> <br>

		<input type="submit">
	</form>

	<c:if test="${messages['Fail'] != null}">
		<div class="alert alert-success" role="alert">
			<c:out value="${messages['Fail']}" />
		</div>
	</c:if>

	<c:if test="${messages['Success'] != null}">
		<div class="alert alert-success" role="alert">
			Room Layout:
			<c:out value="${row}" />
			*
			<c:out value="${col}" />
			<br> Starter Point:
			<c:out value="${initx}" />
			,
			<c:out value="${inity}" />
			<br> End Point:
			<c:out value="${finx}" />
			,
			<c:out value="${finy}" />
			<br> You are choosing
			<c:out value="${algo}" />
			algorithm. <br> Note: Start Point set as yellow, End Point set
			as blue, visited set as green, final path set as red, obstacles set
			as black. <br>
		</div>
	</c:if>

	<div id="canvas">
		<!-- row -> height, col -> width -->
		<canvas id="cs" width="${col * 20}" height="${row * 20}"
			style="border: 2px solid green"></canvas>
	</div>
	<!-- <input type="submit" id="generate">Generate -->
	<button id="generate">Generate</button>

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
		drawColor(initx * w, inity * h, w, h, "yellow");
		drawColor(finx * w, finy * h, w, h, "blue");

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
		
		function bfsDis() {
			// Set directions array.
			let dir = [[0,1],[1,0],[0,-1],[-1,0]];
			
			// Record path by a 2D array.
			let visit = new Array();
			// All path set as unvisit
			for (let i = 0; i < row; i++) {
				visit[i] = new Array();
				for (let j = 0; j < col; j++) {
					visit[i][j] = -1;
				}
			}
			
			// Set x and y as a pair
			let start = {x: initx, y: inity};
			
			// Initiate LinkedList.
			let queue = new Array();
			queue.push(start);
			// Setup the start point.
			visit[start.x][start.y] = 0;
			
			let isFound = false;
			
			// If there still has unvisited node.
			while(queue.length != 0) {
				let pre = queue.pop();
				
				
				// Run through four directions.
				for (let i = 0; i < 4; i++) {
					// Setup a current node after turn a direction.
					let cur = {x: Number(pre.x) + Number(dir[i][0]), y: Number(pre.y) + Number(dir[i][1])};
					// If coordinate are in bound, path is available and node is unvisited.
					if (cur.x >= 0 && cur.x < row && cur.y >= 0 && cur.y < col
							&& room[cur.x][cur.y] != 0 && visit[cur.x][cur.y] == -1) {
						visit[cur.x][cur.y] =  Number(visit[pre.x][pre.y]) + 1;

						// If find the destination.
						if (cur.x == finx && cur.y == finy) {
							// Flag turn to true and break the loop.
							isFound = true;
							break;
						}

						// Push current node into queue.
						queue.push(cur);
					}
				}
				// Jump out of the loop if reach the destination.
				if (isFound) {
					break;
				}
			}
			return visit;
		}
		
		function bfs() {
			// Set directions array.
			let dir = [[0,1],[1,0],[0,-1],[-1,0]];
			let visit = bfsDis();
			
			let path = new Array();
			// All path set as unvisit
			for (let i = 0; i < row; i++) {
				path[i] = new Array();
				for (let j = 0; j < col; j++) {
					path[i][j] = -1;
				}
			}
			
			if (visit[finx][finy] == -1) {
				console.log("No way found!");
				window.alert("No way found!");
				return path;
			}
			
			let cur = {x: Number(finx), y: Number(finy)};
			
			while(true) {
				path[cur.x][cur.y] = 1;
				
				if (cur.x == initx && cur.y == inity) {
					break;
				}
				// Run through four directions.
				for (let i = 0; i < 4; i++) {
					if (Number(cur.x) + Number(dir[i][0]) >= 0 
							&& Number(cur.x) + Number(dir[i][0]) < row
							&& Number(cur.y) + Number(dir[i][1]) >= 0 
							&& Number(cur.y) + Number(dir[i][1]) < col) {
						// Previous node should be distance - 1.
						if (visit[Number(cur.x) + Number(dir[i][0])][Number(cur.y) + Number(dir[i][1])] 
								== Number(visit[cur.x][cur.y]) - 1) {
							cur = {x: Number(cur.x) + Number(dir[i][0]), y: Number(cur.y) + Number(dir[i][1])};
							break;
						}
					}
				}
			}
			return path;
		}
		
		function drawPath() {
			let visit;
			let path;
			if (algo == "Breadth First Searching") {
				visit = bfsDis();
				path = bfs();
				console.log("BFS visit in 2D Array：\n");
				console.log(JSON.stringify(visit));
				console.log("BFS Path in 2D Array：\n");
				console.log(JSON.stringify(path));
			}
			
			for (let i = 0; i < row; i++) {
				for (let j = 0; j < col; j++) {
					if (visit[i][j] > 0) {
						drawColor(i * w, j * h, w, h, "green");
					}
					if (path[i][j] > 0) {
						drawColor(i * w, j * h, w, h, "red");
					}
				}
			}
			drawColor(initx * w, inity * h, w, h, "yellow");
			drawColor(finx * w, finy * h, w, h, "blue");
		}
		
		generate.onclick = e => {
			console.log("Room Layout in 2D Array：\n");
			console.log(JSON.stringify(room));
			
			drawPath();
						
			$.ajax({
		        type: 'POST',
		        url: "/ShortPath/input",
		        dataType: 'json',
		        contentType: 'application/json',
		        traditional: true,
		        data: {room : "hello"},
		        success: function (data) {
		            // location.reload();
		            console.log(data);
		            alert("Success!");
		        },
		        error:function (data) {
		            alert("Fail!");
		        }
		    });
		}
	</script>
	

	


</body>
</html>