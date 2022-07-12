package algorithm;

import java.util.Arrays;
import java.util.Stack;

public class DFS {

	private int[][] room;
	private int row;
	private int col;
	private int initx;
	private int inity;
	private int finx;
	private int finy;
	// right, down, left, up
	private int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
	private boolean flag = false;

	public DFS(int[][] room, int row, int col, int initx, int inity, int finx, int finy) {
		this.room = room;
		this.row = row;
		this.col = col;
		this.initx = initx;
		this.inity = inity;
		this.finx = finx;
		this.finy = finy;
	}

	public class Node {
		private int x;
		private int y;
		// Record next direction.
		private int direction;

		public Node(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}
	}

	public void dfs(int x, int y, char[][] record, int[][] visit) {
		// Reach the destination
		if (x == finx && y == finy) {
			flag = true;
			return;
		}

		// If did not find the destination, use recursion keep searching
		if (flag == false) {
			// Run through four directions.
			for (int i = 0; i < 4; i++) {
				// Find next coordinate
				int nextx = x + dir[i][0];
				int nexty = y + dir[i][1];
				// If next coordinate out of bound or has been visited.
				if (nextx < 0 || nextx >= row || nexty < 0 || nexty >= col 
						|| visit[nextx][nexty] == 1) {
					// Keep searching...
					continue;
				}
				if (room[nextx][nexty] == 1) {
					visit[nextx][nexty] = 1;
					dfs(nextx, nexty, record, visit);
					record[nextx][nexty] = '*';
					// Set visit as 0 after backtracking.
					visit[nextx][nexty] = 0;

				}
			}
		}
	}

	public Stack<Node> findPath() {
		Stack<Node> s = new Stack<>();
		int[][] visit = room; 
		// Present x, y and direction.
		int x = initx, y = inity, direction = 0;
		// Next x, y.
		int nextx = initx, nexty = inity;
		// Change the first position as -1.
		visit[initx][inity] = -1;
		Node temp = new Node(nextx, nexty, -1);

		// Push temp into stack.
		s.push(temp);
		// While there still have nodes in the stack.
		while (!s.isEmpty()) {
			// Pop the peek node.
			temp = s.pop();
			// Renew the present position.
			x = temp.x;
			y = temp.y;
			direction = temp.direction + 1;

			// Run through four directions.
			while (direction < 4) {
				// Change to next x and y.
				nextx = x + dir[direction][0];
				nexty = y + dir[direction][1];

				if (nextx < 0 || nextx >= row || nexty < 0 || nexty >= col 
						|| visit[nextx][nexty] == -1) {
					// Keep searching...
					if (direction < 4) {
						direction++;
					} else {
						direction = 0;
					}
					continue;
				} else if (room[nextx][nexty] == 1) {
					// Renew temp to new x, y, direction.
					temp = new Node(x, y, direction);
					s.push(temp);
					x = nextx;
					y = nexty;
					// -1 in visit means already passed.
					visit[nextx][nexty] = -1;
					// If reach the destination.
					if (x == finx && y == finy) {
						// Push the destination to the stack.
						temp = new Node(x, y, direction);
						s.push(temp);
						// Jump out of the loop;
						return s;
					}
					else {
						direction = 0;
					}
				}
				else {
					direction++;
				}
			}
		}
		return s;
	}

	public char[][] getPath() {
		Stack<Node> s = findPath();
		
		// Setup an empty 2D array as path.
		char[][] path = new char[row][col];
		for (char[] p : path) {
			Arrays.fill(p, '-');
		}
		
		// Backtrack stack.
		int i = 0;
		Node[] data = new Node[s.size()];
		while (!s.isEmpty()) {
			// Delete head.
			data[i++] = s.peek();
			s.pop();
		}
		
		// Backtrack array.
		for(int j = s.size() - 1; j >= 0; j--) {
			path[data[j].x][data[j].y] = '*';
	     }

		return path;
	}

}
