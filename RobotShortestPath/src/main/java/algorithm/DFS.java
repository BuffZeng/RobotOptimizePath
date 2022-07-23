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
	private int[][] dir;
	private Stack<Node> temp;
	private Stack<Node> Mtemp;
	private Stack<Node> path;
	private int discnt;
	private int spacecnt;

	public DFS(int[][] room, int row, int col, int initx, int inity, int finx, int finy) {
		this.room = room;
		this.row = row;
		this.col = col;
		this.initx = initx;
		this.inity = inity;
		this.finx = finx;
		this.finy = finy;
		
		this.dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
		this.temp = new Stack<>();
		this.Mtemp = new Stack<>();
		this.path = new Stack<>();
		this.discnt = Integer.MAX_VALUE;
		this.spacecnt = 0;
	}

	private class Node {
		private int x;
		private int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public char[][] initdfs() {
		int[][] visit = new int[row][col];
		for(int[] v : visit) {
			Arrays.fill(v, 0);
		}
		visit[initx][inity] = 1;
		
		int[][] space = new int[row][col];
		for(int[] s : space) {
			Arrays.fill(s, 0);
		}
		space[initx][inity] = 1;

		dfs(initx,inity,0,visit,space);
		return getPath(space);
	}

	private void dfs(int x, int y, int step, int[][] visit, int[][] space) {
		// Run through 4 directions
		for (int i = 0; i < 4; i++) {
			// If reach the destination.
			if (x == finx && y == finy) {
				// If step < min, this is the optimal solution for now.
				if (step < discnt) {
					while(!temp.empty()) {
						temp.pop();
					}
					while(!Mtemp.empty()) {
						Mtemp.pop();
					}
					while(!path.empty()) {
						Node p1=path.peek();
						temp.push(p1);
						Mtemp.push(p1);
						path.pop();	
					} 
					while(!temp.empty()) {
						Node p1=temp.peek();
						path.push(p1);
						temp.pop();
					}
					discnt = step;
				} 
				step--;
				return;
			}

			int nextx = x + dir[i][0];
			int nexty = y + dir[i][1];
			// If coordinate are in bound, path is available and node is unvisited.
			if (nextx < 0 || nextx >= row || nexty < 0 || nexty >= col 
					|| room[nextx][nexty] == 0 && visit[nextx][nexty] == 1) {
				continue;
			} else if(room[nextx][nexty] == 1 && visit[nextx][nexty] == 0){
				visit[nextx][nexty] = 1;
				space[nextx][nexty] = 1;
				Node next = new Node(nextx, nexty);
				path.push(next);
				dfs(nextx,nexty,step + 1,visit, space);

				// Backtrack.
				visit[nextx][nexty] = 0;
				path.pop();
			}
		}
	}

	private char[][] getPath(int[][] space) {
		// Setup an empty 2D array as path.
		char[][] path = new char[row][col];
		for (char[] p : path) {
			Arrays.fill(p, '-');
		}
				
		if (Mtemp.isEmpty()) {
			System.out.println("\nNo Way Founded.");
			
			return path;
		}
		
		path[initx][inity] = '*';
		
		while (!Mtemp.isEmpty()){
			Node node = Mtemp.pop();
			path[node.x][node.y] = '*';
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if (space[i][j] == 1) {
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
