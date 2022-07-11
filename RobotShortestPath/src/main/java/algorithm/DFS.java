package algorithm;

public class DFS {
	
	private int[][] room;
	private int row;
	private int col;
	private int initx;
	private int inity;
	private int finx;
	private int finy;
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
	
	
	
}
