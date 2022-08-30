package algorithm;

import java.util.Arrays;
import java.util.LinkedList;

public class BFS {

	private int[][] room;
	private int row;
	private int col;
	private int initx;
	private int inity;
	private int finx;
	private int finy;
	
	// right, down, left, up
	private int[][] dir;
	private int discnt;
	private int spacecnt;

	public BFS(int[][] room, int row, int col, int initx, int inity, int finx, int finy) {
		this.room = room;
		this.row = row;
		this.col = col;
		this.initx = initx;
		this.inity = inity;
		this.finx = finx;
		this.finy = finy;
		
		this.dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
		this.discnt = -1;
		this.spacecnt = -1;
	}

	private class Node {
		private int x;
		private int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}


	private int[][] bfs() {

		// Record path by a 2D array.
		int[][] dis = new int[row][col];
		for (int i = 0; i < row; i++) {
			// All path did not visit.
			Arrays.fill(dis[i], -1);
		}

		// Use Node to represent x and y as a pair.
		Node start = new Node(initx, inity);
		Node end = new Node(finx, finy);

		// Initiate LinkedList.
		LinkedList<Node> queue = new LinkedList<>();
		queue.offer(start);
		// Setup the start point.
		dis[start.x][start.y] = 0;

		boolean isFound = false;

		// If there still has unvisited node.
		while (queue.size() != 0) {

			Node pre = queue.poll();

			// Run through four directions.
			for (int i = 0; i < 4; i++) {
				// Setup a current node after turn a direction.
				Node cur = new Node(pre.x + dir[i][0], pre.y + dir[i][1]);

				// If coordinate are in bound, path is available and node is unvisited.
				if (cur.x >= 0 && cur.x < row && cur.y >= 0 && cur.y < col
						&& room[cur.x][cur.y] != 0 && dis[cur.x][cur.y] == -1) {
					dis[cur.x][cur.y] = dis[pre.x][pre.y] + 1;

					// If find the destination.
					if (cur.x == end.x && cur.y == end.y) {
						// Flag turn to true and break the loop.
						isFound = true;
						break;
					}

					// Push current node into queue.
					queue.offer(cur);
				}
			}

			// Jump out of the loop if reach the destination.
			if (isFound) {
				break;
			}
		}

		// Return the distance of the path.
		// return dis[end.x][end.y] == Integer.MAX_VALUE ? -1 : dis[end.x][end.y];
		// Return 2D array of distance.
		if (dis[end.x][end.y] == Integer.MAX_VALUE) {
			dis[end.x][end.y] = -1;
		}
		return dis;
	}

	public char[][] getPath() {

		// Setup an empty 2D array as path.
		char[][] path = new char[row][col];
		for (char[] p : path) {
			Arrays.fill(p, '-');
		}


		int[][] dis = bfs();
		
//		System.out.println();
//		for(int i = 0; i < row; i++) {
//			System.out.println();
//			for(int j = 0; j < col; j++) {
//				System.out.print(dis[i][j]);
//			}
//		}
		
		if (dis[finx][finy] == -1) {
			System.out.println("\nNo Way Founded.");

			return path;
		}



		// Use Node to represent x and y as a pair.
		Node start = new Node(initx, inity);
		Node end = new Node(finx, finy);

		// Setup a current node after turn a direction.
		int[] cur = new int[]{end.x, end.y};
		while (true) {
			// Set a '*' as past path.
			path[cur[0]][cur[1]] = '*';
			discnt++;
			// If reach the start point.
			if (cur[0] == start.x && cur[1] == start.y) {
				break;
			}

			// Run through four directions.
			for (int i = 0; i < 4; i++) {
				if (cur[0] + dir[i][0] >= 0 && cur[0] + dir[i][0] < row
						&& cur[1] + dir[i][1] >= 0 && cur[1] + dir[i][1] < col) {
					// Previous node should be distance - 1.
					if (dis[cur[0] + dir[i][0]][cur[1] + dir[i][1]] 
							== dis[cur[0]][cur[1]] - 1) {
						cur = new int[]{cur[0] + dir[i][0], cur[1] + dir[i][1]};
						break;
					}
				}
			}
		}

		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(dis[i][j] != -1) {
					spacecnt++;
				}
			}
		}

		return path;
	}
	
	public int getDiscnt() {
		return discnt;
	}

	public int getSpacecnt() {
		return spacecnt;
	}
}
