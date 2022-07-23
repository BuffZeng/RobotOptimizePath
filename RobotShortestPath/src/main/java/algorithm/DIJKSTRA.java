package algorithm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class DIJKSTRA {

	private int[][] room;
	private int row;
	private int col;
	private int initx;
	private int inity;
	private int finx;
	private int finy;

	public DIJKSTRA(int[][] room, int row, int col, int initx, int inity, int finx, int finy) {
		this.room = room;
		this.row = row;
		this.col = col;
		this.initx = initx;
		this.inity = inity;
		this.finx = finx;
		this.finy = finy;
	}

	private class Node {
		private int x;
		private int y;
		private int f;

		public Node(int x, int y, int f) {
			this.x = x;
			this.y = y;
			this.f = f;
		}
	}

	private Node[][] dijkstra() {
		// Set up father queue.
		Queue<Node> father = new ArrayDeque<>();
		// Set up child queue.
		Queue<Node> child = new ArrayDeque<>();

		// Set up a 2D array to record node has been visited or not
		int[][] visit = new int[row][col];
		for (int[] v: visit) {
			Arrays.fill(v, 0);
		}
		// Store previous node.
		Node[][] prev = new Node[row][col];

		// Create the start node.
		Node start = new Node(initx, inity, 0);
		// Set the start point as visited.
		visit[initx][inity] = 1;
		// Put start point into father queue.
		father.add(start);

		for(int i = 1; ! father.isEmpty(); i++) {
			// Find all neighbour nodes of current node.
			// Put neighbour nodes in the child queue.
			while(father.size() != 0) {
				Node cur = father.remove();
				int x = cur.x;
				int y = cur.y;

				// Run through four directions.
				// Right
				if (y + 1 < col && room[x][y + 1] == 1 && visit[x][y + 1] == 0) {
					// Save current node into child queue.
					child.add(new Node(x, y + 1, i));
					prev[x][y + 1] = new Node(x, y, i);
					visit[x][y + 1] = 1;
				}

				// Down
				if (x + 1 < row && room[x + 1][y] == 1 && visit[x + 1][y] == 0) {
					// Save current node into child queue.
					child.add(new Node(x + 1, y, i));
					prev[x + 1][y] = new Node(x, y, i);
					visit[x + 1][y] = 1;
				}

				// Left
				if (y - 1 >= 0 && room[x][y - 1] == 1 && visit[x][y - 1] == 0) {
					// Save current node into child queue.
					child.add(new Node(x, y - 1, i));
					prev[x][y - 1] = new Node(x, y, i);
					visit[x][y - 1] = 1;
				}

				// Up
				if (x - 1 >= 0 && room[x - 1][y] == 1 && visit[x - 1][y] == 0) {
					// Save current node into child queue.
					child.add(new Node(x - 1, y, i));
					prev[x - 1][y] = new Node(x, y, i);
					visit[x - 1][y] = 1;
				}
			}
			// Put value from child queue to father queue.
			while (! child.isEmpty()) {
				father.add(child.remove());
			}
		}

		System.out.print("\nSpace: " + getSpace(visit));

		return prev;
	}

	// Backtrack to getPath.
	private void queryPath(int x,int y, Node[][] prev, Queue<Node> pathNode) {
		if (x == initx && y == inity){
			return;
		} else if (prev[x][y] == null) {
			System.out.println("\nNo way found!");
		}else {
			Node node = new Node(prev[x][y].x,prev[x][y].y,prev[x][y].f - 1);
			pathNode.add(node);
			queryPath(node.x,node.y,prev,pathNode);
		}
	}

	public char[][] getPath() {
		// Setup an empty 2D array as path.
		char[][] path = new char[row][col];
		for (char[] p : path) {
			Arrays.fill(p, '-');
		}

		int discnt = 0;

		Node[][] prev = dijkstra();

		Queue<Node> q = new ArrayDeque<Node>();
		queryPath(finx, finy, prev, q);

		while(! q.isEmpty()) {
			Node node = q.remove();
			path[node.x][node.y] = '*';
			discnt++;
		}
		path[finx][finy] = '*';

		System.out.print("\nDistance: " + discnt);

		return path;
	}

	public int getSpace(int[][] visit) {
		int spacecnt = 0;
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(visit[i][j] == 1) {
					spacecnt++;
				}
			}
		}
		
		return spacecnt;
	}


}
